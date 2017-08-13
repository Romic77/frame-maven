package com.frame.test.gp.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;

/**
 * @author Administrator
 * @CREATE 2017/8/6 21:03
 */
public class CuratorCreateSessionDemo {
	private static final String CONNECTSTRING = "192.168.202.133:2181,192.168.202.134:2181,192.168.202.135:2181";
	public static void main(String[] args){
		CuratorFrameworkFactory.builder().connectString(CONNECTSTRING);
	}
}
