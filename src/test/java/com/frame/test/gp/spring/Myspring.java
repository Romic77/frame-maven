package com.frame.test.gp.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author Administrator
 * @CREATE 2017/8/29 13:39
 */
public class Myspring implements InitializingBean,DisposableBean,ApplicationContextAware,ApplicationListener,BeanNameAware{
	private static ApplicationContext context = null;

	@Override
	public void destroy() throws Exception {
		System.out.println("Init method destroy; " );
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Init method after properties ; " );
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context=applicationContext;
	}

	public static Object getBean(String name){
		return context.getBean(name);
	}

	@Override
	public void setBeanName(String s) {
		System.out.println("回调setBeanName方法  id属性是"+s);
	}

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		System.out.println("what fuck?");
	}
}
