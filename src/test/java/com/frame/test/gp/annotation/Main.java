package com.frame.test.gp.annotation;

import java.lang.annotation.Annotation;

/**
 * @author Administrator
 * @CREATE 2017/10/15 11:30
 */
public class Main {
	public static void main(String[] args) {
		Class clazz = TestAnnotation.class;
		Annotation a = clazz.getAnnotation(Controller.class);
		System.out.println(a.annotationType());

		//java中有标准的API
		//定义了一个接口
		//Annotation a= null;
	}

	@GPAnnotation(value="123")
	public void sb(){

	}
	@GPAnnotation("123")
	public String name="123";
}
