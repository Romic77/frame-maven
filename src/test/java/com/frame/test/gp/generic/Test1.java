package com.frame.test.gp.generic;

import java.util.ArrayList;

/**
 * @author Administrator
 * @CREATE 2017/9/17 1:40
 */
public class Test1 {

	/**
	 * 泛型擦除，我们运行这段代码会发现arrayList1 等于 arrayList2 这说明在运行过程中泛型被擦除了，只剩下原始类型：ArrayList
	 * @param args
	 */
	public static void main(String[] args){
		ArrayList<String> arrayList1=new ArrayList<>();
		arrayList1.add("add1");

		ArrayList<Integer> arrayList2=new ArrayList<>();
		arrayList2.add(2);
		System.out.println(arrayList1.getClass()==arrayList2.getClass());
	}
}
