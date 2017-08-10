package com.frame.test.gp.webservice;

import javax.jws.WebService;

/**
 * author chenqi
 * time 2017/7/31 10:12
 */
@WebService
public class HelloWorldWsImpl implements HelloWorldWs{
	public String sayHello(String name) {
		return "Hello "+name;
	}
}
