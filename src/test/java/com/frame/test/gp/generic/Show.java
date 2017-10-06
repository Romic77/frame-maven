package com.frame.test.gp.generic;

import java.util.Date;

/**
 * @author Administrator
 * @CREATE 2017/9/17 1:29
 */
public interface Show<T, V> {
	void show(T t, V v);
}

class ShowTest<T, V> implements Show<T, V> {

	@Override
	public void show(T t, V v) {
		System.out.println(t + ":" + v);
	}

	public static void main(String[] args) {
		ShowTest s = new ShowTest();
		s.show("hello", new Date());

		String str=get1("Hello", "World");
		System.out.println(str);

		System.out.println(get2(1,2));
	}

	public static <T, U> T get1(T t, U u) {
		if (u != null) {
			return t;
		}else{
			return null;
		}
	}

	public static <T extends Comparable>T get2(T t1,T t2){
		if (t1.compareTo(t2)>=0){
			return t1;
		}
			return t2;
	}


}


