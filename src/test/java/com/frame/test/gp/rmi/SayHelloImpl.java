package com.frame.test.gp.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Administrator
 * @CREATE 2017/7/29 22:48
 */
public class SayHelloImpl extends UnicastRemoteObject implements ISayHello {
	protected SayHelloImpl() throws RemoteException {
	}

	public String sayHello(String name) throws RemoteException {
		return "Hello mic-->" + name;
	}
}
