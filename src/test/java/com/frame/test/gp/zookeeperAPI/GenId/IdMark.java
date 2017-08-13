package com.frame.test.gp.zookeeperAPI.GenId;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/8/13 0:16
 * 分布式ID生成器，实际上是产生持久化有序节点，然后获取有序节点，作为ID
 */
public class IdMark {
	private ZkClient zkClient;

	private final String server;  //记录服务器的地址
	private final String root;    //记录父节点的路径
	private final String nodeName;  //几点名称
	private volatile boolean runing = false;
	private ExecutorService cleanExector;

	public enum RemoveMethod {
		NONE,  //不
		IMMEDIATELY,  //立即
		DELAY   //延期
	}

	public IdMark(String server, String root, String nodeName) {
		this.server = server;
		this.root = root;
		this.nodeName = nodeName;
	}

	public void start() throws Exception {
		if (runing) {
			throw new Exception("server has started ...");
		}
		runing = true;

		init();
	}

	public void stop() throws Exception {
		if (!runing) {
			throw new Exception("server has stopped ...");
		}
		runing = false;

		freeResource();
	}

	/**
	 * 初始化服务器资源
	 */
	private void init() {
		zkClient = new ZkClient(server, 5000, 5000, new BytesPushThroughSerializer());
		cleanExector = Executors.newFixedThreadPool(10);
		try {
			zkClient.createPersistent(root, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 释放资源
	 */
	private void freeResource() {
		cleanExector.shutdown();
		try {
			cleanExector.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			cleanExector = null;
		}
		if (zkClient != null) {
			zkClient.close();
			zkClient = null;
		}

	}

	/**
	 * 检查服务是否运行
	 *
	 * @throws Exception
	 */
	public void checkRunning() throws Exception {
		if (!runing) {
			throw new Exception("请先调用start ");
		}
	}

	private String extractId(String str) {
		int index = str.lastIndexOf(nodeName);
		if (index >= 0) {
			index += nodeName.length();
			return index <= str.length() ? str.substring(index) : "";
		}
		return str;
	}

	/**
	 * 获取ID
	 *
	 * @param removeMethod
	 * @return
	 */
	public String generateId(RemoveMethod removeMethod) throws Exception {
		checkRunning();
		final String fullNodePath = root.concat("/").concat(nodeName);
		//创建顺序节点每个父节点会为他的第一级子节点维护一份时序，会记录每个子节点创建的先后顺序。
		//基于这个特性,在创建子节点的时候，可以设置这个属性,呢么在创建节点过程,
		//Zookeeper会自动为给定节点名添加后缀，作为新节点名
		final String outPath = zkClient.createPersistentSequential(fullNodePath, null);
		if (removeMethod.equals(RemoveMethod.IMMEDIATELY)) {  //立即删除
			zkClient.deleteRecursive(outPath);
		} else if (removeMethod.equals(RemoveMethod.DELAY)) { //延期删除
			cleanExector.execute(new Runnable() {
				@Override
				public void run() {
					zkClient.delete(outPath);
				}
			});
		}
		return extractId(outPath);
	}

	public static void main(String[] args) throws Exception {
		IdMark idMaker=null;
		try {
			 idMaker = new IdMark("192.168.202.133:2181,192.168.202.134:2181,192.168.202.135:2181",
					"/NameService/IdGen", "ID-");
			 idMaker.start();
			for (int i = 0; i < 2; i++) {
				String id = idMaker.generateId(RemoveMethod.DELAY);
				System.out.println(id);
			}
		}finally {
			idMaker.stop();
		}
	}
}
