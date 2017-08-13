package com.frame.test.gp.zookeeperAPI.queue;

import com.frame.entity.Admin;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * @author Administrator
 * @CREATE 2017/8/12 22:33
 */
public class QueueTest {
	private final static String CONNECTSTRING = "192.168.202.133:2181,192.168.202.134:2181,192.168.202.135:2181";

	public static void main(String[] args) {
		ZkClient zkClient = new ZkClient(CONNECTSTRING, 5000, 5000, new SerializableSerializer());
		zkClient.deleteRecursive("/queue");

		DistributedSimpleQueue<Admin> adminDistributedSimpleQueue = new DistributedSimpleQueue<>(zkClient, "/queue");
		for (int i = 0; i < 10; i++){
			Admin admin=new Admin();
			admin.setAdminName("队列"+i);
			admin.setAdminId((long) i);
			adminDistributedSimpleQueue.offer(admin);
			System.out.println("size: "+adminDistributedSimpleQueue.size());
		}

		Admin admin=adminDistributedSimpleQueue.poll();
		System.out.println(admin.getAdminName());
	}
}
