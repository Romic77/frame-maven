package com.frame.test.gp.zookeeperAPI.javaapilock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @CREATE 2017/8/10 23:41
 */
public class LockWatcher implements Watcher {

	private CountDownLatch countDownLatch;

	public LockWatcher(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void process(WatchedEvent watchedEvent) {
		if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
			countDownLatch.countDown();
		}
	}
}
