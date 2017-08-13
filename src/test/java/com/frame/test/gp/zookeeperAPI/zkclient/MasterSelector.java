package com.frame.test.gp.zookeeperAPI.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/8/11 18:32
 */
public class MasterSelector {
	private ZkClient zkClient;

	private final static String MASTER_PATH = "/master"; //需要争抢的节点

	private IZkDataListener dataListener; //注册节点内容变化

	private UserCenter server; //其他服务器

	private UserCenter master; //master节点

	private static boolean isRunning = false;

	ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

	public MasterSelector(ZkClient zkClient,UserCenter server) {
		System.out.println("["+server+"]争抢master权限");
		this.server = server;
		this.zkClient=zkClient;

		this.dataListener = new IZkDataListener() {
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {

			}

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				//节点如果被删除,发起选主操作
				chooseMaster();
			}
		};
	}

	private void releaseMaster() {
		//释放锁
		//判断当前是不是master,只有master需要释放
		if(checkIsMaster()){
			zkClient.deleteRecursive(MASTER_PATH); //删除
		}
	}

	//选举开始
	public void start() {
		if(!isRunning){
			isRunning=true;
			zkClient.subscribeDataChanges(MASTER_PATH,dataListener); //注册节点事件
			chooseMaster();
		}
	}

	public void stop() {
		//停止
		if(isRunning){
			isRunning=false;
			scheduledExecutorService.shutdown();
			zkClient.unsubscribeDataChanges(MASTER_PATH,dataListener);
			releaseMaster();
		}
	}

	//具体选master的实现逻辑
	private void chooseMaster() {
		if (!isRunning) {
			System.out.println("当前服务没有启动");
			return;
		}
		try {
			zkClient.createEphemeral(MASTER_PATH, server);
			master = server; //server赋值给master

			System.out.println(master.getMc_name() + "我现在已经是master,你们得听我的");

			//定时器
			//master释放
			scheduledExecutorService.schedule(() -> {
				releaseMaster();
			}, 5, TimeUnit.SECONDS);
		} catch (ZkNodeExistsException e) {
			//master 已经存在
			UserCenter userCenter = zkClient.readData(MASTER_PATH, true);
			if (userCenter == null) {
				chooseMaster();
			}else{
				master=userCenter;
			}
		}
	}

	private boolean checkIsMaster() {
		//判断当前server是否为master
		UserCenter userCenter=zkClient.readData(MASTER_PATH);
		if (userCenter.getMc_name().equalsIgnoreCase(server.getMc_name())){
			master=userCenter;
			return true;
		}
		return false;
	}

}
