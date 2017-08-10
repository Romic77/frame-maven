package com.frame.test.gp.Lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

/**
 * author chenqi
 * time 2017/8/9 11:11
 */
public class Lock2 {
	static final String CONNECTIONSTR = "";
	static final int SESSION_OUTTIME = 5000;

	static int count = 10;

	static void genarNo() {
		try {
			count--;
			System.out.println(count);
		} finally {
		}
	}

	public static void main(String[] args) throws InterruptedException {
		//1.重试策略： 初试时间1s，重试10次
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
		//2.通过工场创建链接
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTIONSTR).sessionTimeoutMs(SESSION_OUTTIME)
				.retryPolicy(retryPolicy).build();
		//3.打开连接
		curatorFramework.start();
		//分布式锁
		final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/super");
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		for (int i = 0; i < 10; i ++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						countDownLatch.await();
						//加锁
						lock.acquire();
						//------开始业务-----
						genarNo();
						//------结束业务-----
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						//释放锁
						try {
							lock.release();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			},"t"+i).start();
		}
		Thread.sleep(1000);
		countDownLatch.countDown();

	}
}
