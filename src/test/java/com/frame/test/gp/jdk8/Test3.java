package com.frame.test.gp.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Administrator
 * @CREATE 2017/9/26 23:15
 */
public class Test3 {

	//(parameters)->express | {}lamada
	public static void main(String[] args) {
		List<String> words = Arrays.asList("a", "b", "c");
		/*words.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});*/

		//words.sort(Comparator.reverseOrder());  //倒序
		/*words.sort((o1, o2) -> {
			return o2.compareTo(o1);
		});*/

		words.sort((o1, o2) -> o2.compareTo(o1));

		for (String str : words) {
			System.out.println(str);
		}
	}
}
