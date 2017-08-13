package com.frame.test.gp.zookeeperAPI.javaapilock;

import com.frame.test.gp.thread.thread15.Lock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/8/10 23:10
 */
public class DistributeLock {
	private static final String ROOT_LOCKS = "/LOCKS";  //根节点

	private ZooKeeper zooKeeper;

	private int sessionTimeOut; //会话超时时间

	private String lockID; //记录锁节点ID

	public static final byte[] data = {1, 2}; //节点数据

	public CountDownLatch countDownLatch = new CountDownLatch(1);

	public DistributeLock() throws IOException, InterruptedException {
		this.zooKeeper = ZKClientUtils.getInstance();
		this.sessionTimeOut = ZKClientUtils.getSessionTimeOut();
	}

	//获取锁的方法,有序节点最小的获得锁
	public boolean lock() {
		try {
			lockID = zooKeeper.create(ROOT_LOCKS + "/", data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

			System.out.println(Thread.currentThread().getName() + "->成功创建了lock节点[" + lockID + "],开始竞争锁;");

			List<String> childreaNodes = zooKeeper.getChildren(ROOT_LOCKS, true); //获取根节点下的所有子节点
			//排序 从小到大
			SortedSet<String> sortedSet = new TreeSet<String>();
			for (String children : childreaNodes) {
				sortedSet.add(ROOT_LOCKS + "/" + children);
			}
			String first = sortedSet.first();
			if (lockID.equalsIgnoreCase(first)) {
				//表示当前就是最小的节点
				System.out.println(Thread.currentThread().getName() + "->成功获得锁,lock节点为:[" + lockID + "]");
				return true;
			}
			//获得锁的下一个节点 监控锁节点，当锁释放之后下一个节点获取锁
			SortedSet<String> lessThanLockId = sortedSet.headSet(lockID);  //handSet 返回从开始节点到指定元素的集合
			if (!lessThanLockId.isEmpty()) {
				String prevLockID = lessThanLockId.last(); //拿到比当前LOCKID这个节点更小的上一个节点
				zooKeeper.exists(prevLockID, new LockWatcher(countDownLatch));
				countDownLatch.await(sessionTimeOut, TimeUnit.MILLISECONDS);
				//上面这段代码意味着如果会话超时或者节点被删除(释放)
				System.out.println(Thread.currentThread().getName() + "成功获取锁:[" + lockID + "];下一个监控节点："+prevLockID);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean unlock() {
		System.out.println(Thread.currentThread().getName() + "->开始释放锁:[" + lockID + "]");
		try {
			zooKeeper.delete(lockID, -1);
			System.out.println("节点[" + lockID + "]成功被删除");
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				DistributeLock lock = null;
				try {
					lock = new DistributeLock();
					countDownLatch.countDown();
					countDownLatch.await();
					lock.lock();
					Thread.sleep(random.nextInt(500));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (lock != null) {
						lock.unlock();
					}
				}

			}).start();
		}
	}

}
