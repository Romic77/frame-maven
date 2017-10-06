package com.frame.test.gp.jdk8;

/**
 * @author Administrator
 * @CREATE 2017/9/26 23:24
 */
public class PersonInfo implements Comparable<PersonInfo> {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(PersonInfo o) {
		return o.getName().compareTo(this.getName());
	}

	public int compare(PersonInfo p1,PersonInfo p2){
		return p1.getName().compareTo(p2.getName());
	}
}
