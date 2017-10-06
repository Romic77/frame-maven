package com.frame.test.gp.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Administrator
 * @CREATE 2017/9/26 23:54
 */
public class Test5 {
	static List<Person> personList = new ArrayList<Person>() {
		{
			add(new Person("mic", 20));
			add(new Person("james", 40));
		}
	};

	public static void main(String[] args) {
		/*personList.forEach((person) -> {
			person.setAge(person.getAge()+5);
			System.out.println(person.toString());
		});*/

		personList.stream().filter(person -> person.getAge()>20).forEach(person -> {
			System.out.println(person);
		});
	}

}
