package com.frame.test.gp.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 * @CREATE 2017/8/29 13:50
 */
public class MySpringTest {
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		context.start();

		//ApplicationContextAware 获得applicationContext，然后获得bean
		/*HelloWorld helloWorld=(HelloWorld)Myspring.getBean("helloWorld");
		helloWorld.say();*/

		//beanNameAware接口
		HelloWorld helloWorld1=context.getBean("helloWorld",HelloWorld.class);
		helloWorld1.say();
	}
}
