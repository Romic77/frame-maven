package com.frame.test.gp.jdk8;

import java.lang.reflect.AccessibleObject;

/**
 * @author Administrator
 * @CREATE 2017/9/26 22:54
 */
public class Test1 {
	public static void main(String[] args) {
		FunctionInterfaceDemo fi = new FunctionInterfaceDemo() {
			@Override
			public void sayHello() {
				System.out.println("Say hello");
			}
		};
		fi.sayHello();
		fi.doAnything();
		FunctionInterfaceDemo.SayHi();
	}
}
