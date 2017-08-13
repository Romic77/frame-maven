package com.frame.test.gp.zookeeperAPI;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/8/5 21:21
 */
public class ZookeeperAPIDemo implements Watcher {
	private static final String CONNECTSTRING = "192.168.202.133:2181,192.168.202.134:2181,192.168.202.135:2181";

	private static ZooKeeper zookeeper;
	//concurrent 原子并发包的工具，需要查看
	private static CountDownLatch countDownLatch = new CountDownLatch(1);

	private static Stat stat = new Stat();

	public static ZooKeeper getConnection() throws IOException, InterruptedException {
		zookeeper = new ZooKeeper(ZookeeperAPIDemo.CONNECTSTRING, 5000, new ZookeeperAPIDemo());
		countDownLatch.await();
		return zookeeper;
	}

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		zookeeper = getConnection();
		//创建节点
		String result =zookeeper.create("/node1","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
		/*zookeeper.getData("/node1",new ZookeeperAPIDemo(),stat);
		System.out.println("创建成功:"+result);

		//修改节点
		zookeeper.setData("/node1","666".getBytes(),-1);
		System.out.println(zookeeper.getData("/node1",new ZookeeperAPIDemo(),stat));

		//删除节点，先从子节点删除，不然会报错，
		zookeeper.delete("/node1",-1);

		//创建持久化节点和子节点
		String path="/node11";
		zookeeper.create(path,"123".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		TimeUnit.SECONDS.sleep(1);

		Stat stat=zookeeper.exists(path+"/node1",true);
		if (stat == null){
			zookeeper.create(path+"/node1","123".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			TimeUnit.SECONDS.sleep(1);
		}

		//修改子路径
		zookeeper.setData(path+"/node1","mic123".getBytes(),-1);
		TimeUnit.SECONDS.sleep(1);*/

		//获取指定节点下的子节点
		List<String> list=zookeeper.getChildren("/heibai",true);
		System.out.println(list);

	}

	@Override
	public void process(WatchedEvent watchedEvent) {
		//如果当前的连接状态时成功的，那么通过计数器去控制
		if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
			if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
				countDownLatch.countDown();
				System.out.println("process:"+watchedEvent.getState() + "-->" + watchedEvent.getType());
			} else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
				try {
					System.out.println("数据变更触发路径：" + watchedEvent.getPath() + "->改变后的值" + zookeeper.getData(watchedEvent.getPath(), true, stat));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged){  //子节点数据变化会触发
				try {
					System.out.println("子节点数据变更路径："+watchedEvent.getPath()+"->节点的值："+ zookeeper.getData(watchedEvent.getPath(),true,stat));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else if (watchedEvent.getType()==Event.EventType.NodeCreated){   //创建子节点的时候回触发
				try {
					System.out.println("节点创建路径："+watchedEvent.getPath()+"->节点的值："+ zookeeper.getData(watchedEvent.getPath(),true,stat));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else if (watchedEvent.getType()== Event.EventType.NodeDeleted){  //子节点删除会触发
				System.out.println("节点删除路径："+watchedEvent.getPath());
			}
			System.out.println("process:"+watchedEvent.getType());
		}
	}

}
