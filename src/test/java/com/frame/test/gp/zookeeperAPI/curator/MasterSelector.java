package com.frame.test.gp.zookeeperAPI.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/8/12 20:12
 */
public class MasterSelector {

	private final static String MASTER_PATH = "/curator_master_path";

	public static void main(String[] args) {
		CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
		LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, MASTER_PATH, new LeaderSelectorListener() {
			@Override
			public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
				System.out.println("获得leader成功");
				TimeUnit.SECONDS.sleep(2);
			}

			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState) {

			}
		});
		leaderSelector.autoRequeue();
		leaderSelector.start(); //开始选举
	}
}
