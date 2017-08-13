package com.frame.test.gp.zookeeperAPI.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author Administrator
 * @CREATE 2017/8/8 22:17
 */
public class CuratorClientUtils {

	private static CuratorFramework curatorFramework;

	private final static String CONNECTSTRING = "192.168.202.133:2181,192.168.202.134:2181,192.168.202.135:2181";

	public static CuratorFramework getInstance() {
		//fluent 风格
		 curatorFramework=CuratorFrameworkFactory.builder().connectString(CONNECTSTRING).sessionTimeoutMs(5000).retryPolicy(
				new ExponentialBackoffRetry(1000,3)).build();
		//normal
		//curatorFramework = CuratorFrameworkFactory.newClient(CONNECTSTRING, 5000, 5000, new ExponentialBackoffRetry(1000, 3));
		curatorFramework.start();
		return curatorFramework;
	}
}
