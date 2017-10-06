package com.frame.test.gp.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 * @CREATE 2017/9/26 23:23
 */
public class Test4 {
	public static void main(String[] args) {
		//通过工厂方法创建对象示例
		PersonInfoFactory personInfoFactory = new PersonInfoFactory(PersonInfo::new);
		List<PersonInfo> personInfos=new ArrayList<>();
		PersonInfo p1=personInfoFactory.getPerson();
		p1.setName("mic");
		personInfos.add(p1);

		PersonInfo p3=personInfoFactory.getPerson();
		p3.setName("abc");
		personInfos.add(p3);

		PersonInfo p2=personInfoFactory.getPerson();
		p2.setName("james");
		personInfos.add(p2);




		print(personInfos);

		/*personInfos.sort(p1::compare);

		print(personInfos);*/

		personInfos.sort(PersonInfo::compareTo);

		print(personInfos);
	}

	private static void print(List<PersonInfo> personInfos){
		personInfos.forEach(personInfo -> {
			System.out.println(personInfo.getName());
		});
	}
}
