package com.frame.test.gp.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author Administrator
 * @CREATE 2017/7/29 23:35
 */
public class HelloServer {
	/**
	 *@author: chenqi
	 *@date: 2017-07-29 23:45:05
	 *@description: 首先通过LocateRegister.createRegister(port)注入端口，然后Naming绑定URL和接口实现对象
	*/
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
	    ISayHello hello=new SayHelloImpl();
		LocateRegistry.createRegistry(8888);
		Naming.bind("rmi://localhost:8888/sayHello",hello);
		System.out.println("server start success;"+hello);
	}
}
