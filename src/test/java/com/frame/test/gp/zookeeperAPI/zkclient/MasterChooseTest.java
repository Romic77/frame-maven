package com.frame.test.gp.zookeeperAPI.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/8/11 21:47
 */
public class MasterChooseTest {
	private final static String CONNECTSTRING = "192.168.202.133:2181,192.168.202.134:2181,192.168.202.135:2181";

	public static void main(String[] args) {
		List<MasterSelector> selectorList = new ArrayList<>();
		try {
			for (int i = 0; i < 10; i++) {
				ZkClient zkClient = new ZkClient(CONNECTSTRING, 5000, 5000, new SerializableSerializer());

				UserCenter userCenter = new UserCenter();
				userCenter.setMc_id(i);
				userCenter.setMc_name("客户端" + i);
				MasterSelector selector = new MasterSelector(zkClient, userCenter);
				selectorList.add(selector);
				selector.start();

				TimeUnit.SECONDS.sleep(4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			for (MasterSelector selector : selectorList) {
				selector.stop();
			}
		}
	}
}
