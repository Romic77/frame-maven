package com.frame.test.gp.webservice;

import javax.xml.ws.Endpoint;

/**
 * author chenqi
 * time 2017/7/31 14:57
 */
public class HelloWorldService {
	public static void main(String[] args) {
		//http://localhost:8888/demo/hello?wsdl 查看wsdl文档
		Endpoint.publish("http://localhost:8888/demo/hello",new HelloWorldWsImpl());

		System.out.println("success");
	}
}
