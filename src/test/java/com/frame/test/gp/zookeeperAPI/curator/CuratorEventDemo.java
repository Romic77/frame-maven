package com.frame.test.gp.zookeeperAPI.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/8/8 23:31
 */
public class CuratorEventDemo {
	/**
	 * 三种watcher来做节点的监听
	 * pathCache  监视一个路径下子节点的创建、删除、节点数据更新
	 * NodeCache  监视一个节点的创建、更新、删除
	 * TreeCache  pathCache + NOdeCache(监视路径下的创建、更新、删除事件)，缓存路径下所有子节点的数据
	 */

	public static void main(String[] args) throws Exception {
		CuratorFramework curatorFramework = CuratorClientUtils.getInstance();

		/**
		 * NodeCache
		 */
		/*
		NodeCache cache=new NodeCache(curatorFramework,"/curator",false);
		cache.start();

		cache.getListenable().addListener(()-> System.out.println("节点数据发生变化，变化后的结果为："+new String(cache.getCurrentData().getData())));

		curatorFramework.setData().forPath("/curator","update".getBytes());*/


		/**
		 * PathChildrenCache 监听/event 下的子节点
		 */

		PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, "/event", true);
		//NORMAL: 初始时为空。
		//BUILD_INITIAL_CACHE: 在这个方法返回之前调用rebuild()。
		//POST_INITIALIZED_EVENT: 当Cache初始化数据后发送一个PathChildrenCacheEvent.Type #INITIALIZED事件
		childrenCache.start(PathChildrenCache.StartMode.NORMAL);
		childrenCache.getListenable().addListener((curatorFramework1, pathChildrenCacheEvent) -> {
			switch (pathChildrenCacheEvent.getType()) {
				case CHILD_ADDED:
					System.out.println("增加子节点");
					break;
				case CHILD_REMOVED:
					System.out.println("删除子节点");
					break;
				case CHILD_UPDATED:
					System.out.println("更新子节点");
					break;
				default:
					break;
			}
		});

		curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/event","events".getBytes());
		TimeUnit.SECONDS.sleep(1);
		System.out.println(1);

		//pathChildrenCache 会递归监听子节点时间,所以子节点创建打印出来了
		curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/event/event1","1".getBytes());
		TimeUnit.SECONDS.sleep(1);

		curatorFramework.setData().forPath("/event/event1","666".getBytes());
		TimeUnit.SECONDS.sleep(1);

		curatorFramework.delete().deletingChildrenIfNeeded().forPath("/event");
		System.in.read();
	}


}
