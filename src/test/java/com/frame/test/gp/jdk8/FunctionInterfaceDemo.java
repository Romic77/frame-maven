package com.frame.test.gp.jdk8;

/**
 * Created by Administrator on 2017/9/26.
 */

@FunctionalInterface
public interface FunctionInterfaceDemo {
	void sayHello();

	static void SayHi(){
		System.out.println("say hi");
	}

	default void doAnything(){
		System.out.println("say doAnything");
	}

	boolean equals(Object object);
}
