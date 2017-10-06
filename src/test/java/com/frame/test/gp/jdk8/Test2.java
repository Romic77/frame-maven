package com.frame.test.gp.jdk8;

/**
 * @author Administrator
 * @CREATE 2017/9/26 23:07
 */
public class Test2 {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("I'm thread test");
			}
		}).start();

		new Thread(() -> System.out.println("I'm thread test1")).start();
	}
}
