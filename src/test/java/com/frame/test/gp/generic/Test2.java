package com.frame.test.gp.generic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author Administrator
 * @CREATE 2017/9/17 1:43
 */
public class Test2 {
	/**
	 * 理论上来说规定了泛型只能存储Integer类型，但是我们通过反射方法调用add，却发现可以存储字符串，这说明Integer泛型编译之后被擦除了，只保留了原始类型
	 * @param args
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		arrayList.getClass().getMethod("add", Object.class).invoke(arrayList, "asd");
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i));
		}

	}
}
