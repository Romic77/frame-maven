package com.frame.test.gp.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2017/7/29.
 */

public interface ISayHello extends Remote{
	 String sayHello(String name)throws RemoteException;
}
