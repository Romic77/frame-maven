package com.frame.test.gp.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Administrator
 * @CREATE 2017/7/29 22:49
 */
public class HelloClient {
	/**
	 *@author: chenqi
	 *@date: 2017-07-29 23:42:25
	 *@description: 客户端通过Naming.lookup注入，rmi://ip:port/method
	*/
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
	    ISayHello hello= (ISayHello) Naming.lookup("rmi://localhost:8888/sayHello");
	    System.out.println(hello);
	    System.out.println(hello.sayHello("hello 菲菲"));
	}
}
