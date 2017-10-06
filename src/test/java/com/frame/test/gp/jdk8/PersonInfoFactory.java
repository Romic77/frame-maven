package com.frame.test.gp.jdk8;

import java.util.function.Supplier;

/**
 * @author Administrator
 * @CREATE 2017/9/26 23:26
 */
public class PersonInfoFactory {
	private Supplier<PersonInfo> supplier;

	public PersonInfoFactory(Supplier<PersonInfo> supplier) {
		this.supplier = supplier;
	}

	public PersonInfo getPerson() {
		return supplier.get();
	}
}
