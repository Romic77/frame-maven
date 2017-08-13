package com.frame.test.gp.serializable.clone;

import java.io.Serializable;

/**
 * @author Administrator
 * @CREATE 2017/7/23 22:59
 */
public class Teacher implements Serializable{

    private static final long serialVersionUID = -4023362094527660472L;

    private String name;

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
