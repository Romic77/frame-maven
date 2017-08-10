package com.frame.test.gp.Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author chenqi
 * time 2017/8/9 11:04
 */
public class Lock1 {
	static ReentrantLock reentrantLock = new ReentrantLock();

	static int count = 10;

	static void genarNo() {
		try {
			reentrantLock.lock();
			;
			count--;
			System.out.println(count);
		} finally {
			reentrantLock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		for (int i = 0; i < 10; i++) {
	      	new Thread(new Runnable() {
		        @Override
		        public void run() {
			        try{
			        	countDownLatch.await();
			        	genarNo();
			        } catch (InterruptedException e) {
				        e.printStackTrace();
			        }

		        }
	        },"t"+i).start();
		}
		Thread.sleep(50);
		countDownLatch.countDown();
	}
}
