package com.frame.test.gp.zookeeperAPI;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/8/8 20:51
 */
public class zkClientApiOperatorDemo {
	/*private final static String CONNECTSTRING="192.168.202.133:2181,192.168.202.134:2181,192.168.202.135:2181";

	private static ZkClient getInstance(){

		return new ZkClient(CONNECTSTRING);
	}

	public static void main(String[] args) throws InterruptedException {
		ZkClient zkClient=getInstance();
		//zkclient 提供递归创建父节点功能
		zkClient.createPersistent("/zkclient/zkclient1",true);
		System.out.println("success");

		//删除节点
		//zkClient.delete("/auth1");
		//递归删除节点
		//zkClient.deleteRecursive("/zkclient");

		//获取父节点下的子节点
		List<String> list =zkClient.getChildren("/zkclient");
		System.out.println(list);

		//订阅数据修改事件
		zkClient.subscribeDataChanges("/zkclient", new IZkDataListener() {
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println(dataPath+"->"+data);
			}

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("delete path"+dataPath);
			}
		});

		//修改节点数据
		zkClient.writeData("/zkclient","zkclient-data");
		TimeUnit.SECONDS.sleep(2);

		zkClient.deleteRecursive("/zkclient");
		TimeUnit.SECONDS.sleep(2);


	}*/
}
