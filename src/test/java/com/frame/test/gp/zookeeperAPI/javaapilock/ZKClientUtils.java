package com.frame.test.gp.zookeeperAPI.javaapilock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @CREATE 2017/8/10 23:04
 */
public class ZKClientUtils {
	private final static String CONNECTSTRING = "192.168.202.133:2181,192.168.202.134:2181,192.168.202.135:2181";

	public static int getSessionTimeOut() {
		return sessionTimeOut;
	}

	public static final int sessionTimeOut = 5000;

	//获取链接
	public static ZooKeeper getInstance() throws IOException, InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		ZooKeeper zooKeeper = new ZooKeeper(CONNECTSTRING, sessionTimeOut, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				if (watchedEvent.getState()== Event.KeeperState.SyncConnected){
					countDownLatch.countDown();
				}
			}
		});
		countDownLatch.await();
		return zooKeeper;
	}
}
