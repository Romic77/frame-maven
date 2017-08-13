package com.frame.test.gp.rmi.demo;

import java.io.IOException;

/**
 * @author Administrator
 * @CREATE 2017/7/30 0:11
 */
public class UserClient {
	public static void main(String[] args) throws IOException {
		User user =new User_Stub();
		System.out.println(user.getAge());

	}
}
