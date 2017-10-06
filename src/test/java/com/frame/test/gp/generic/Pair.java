package com.frame.test.gp.generic;

/**
 * @author Administrator
 * @CREATE 2017/9/17 1:26
 */
public class Pair<T> {
	public Pair(){

	}

	private T value;

	public Pair(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public static void main(String[] args){
		Pair<String> s=new Pair<>("Hello");
		System.out.println(s.getValue());

		Pair<Integer> s1=new Pair<>(1);
		System.out.println(s1.getValue());
	}
}
