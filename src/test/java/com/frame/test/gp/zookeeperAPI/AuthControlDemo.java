package com.frame.test.gp.zookeeperAPI;

import org.apache.zookeeper.Watcher;

import com.frame.test.gp.zookeeperAPI.ZookeeperAPIDemo;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AuthControlDemo implements Watcher {
	private final static String CONNECTSTRING="192.168.202.133:2181,192.168.202.134:2181,192.168.202.135:2181";
	private static CountDownLatch countDownLatch=new CountDownLatch(1);
	private static CountDownLatch countDownLatch2=new CountDownLatch(1);

	private static ZooKeeper zookeeper;
	private static Stat stat=new Stat();
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException, NoSuchAlgorithmException {
		zookeeper=new ZooKeeper(CONNECTSTRING, 5000, new AuthControlDemo());
		countDownLatch.await();


		ACL acl=new ACL(ZooDefs.Perms.CREATE, new Id("digest","root:root"));
		ACL acl2=new ACL(ZooDefs.Perms.CREATE, new Id("ip","192.168.1.1"));

		List<ACL> acls=new ArrayList<>();
		acls.add(acl);
		//acls.add(acl2);
		zookeeper.create("/auth1","123".getBytes(),acls,CreateMode.PERSISTENT);
		zookeeper.addAuthInfo("digest","root:root".getBytes());

		zookeeper.create("/auth1","123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL,CreateMode.PERSISTENT);

		zookeeper.create("/auth1/auth1-1","123".getBytes(),ZooDefs.Ids.CREATOR_ALL_ACL,CreateMode.PERSISTENT);

		ZooKeeper zooKeeper1= new ZooKeeper(CONNECTSTRING, 5000, new AuthControlDemo());
		countDownLatch.wait();;
		zooKeeper1.delete("/auth1",-1);

		// acl (create /delete /admin /read/write)
		//权限模式： ip/Digest（username:password）/world/super

	}
	public void process(WatchedEvent watchedEvent) {
		//如果当前的连接状态是连接成功的，那么通过计数器去控制
		if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
			if(Event.EventType.None==watchedEvent.getType()&&null==watchedEvent.getPath()){
				countDownLatch.countDown();
				System.out.println(watchedEvent.getState()+"-->"+watchedEvent.getType());
			}
		}

	}
}
