package com.frame.test.gp.serializable;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.io.Serializable;

/**
 * @author Administrator
 * @CREATE 2017/7/23 20:18
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 6477679579054450313L;

    //transient 是不序列化某属性
    @Protobuf(fieldType = FieldType.STRING,order = 1)
   //private transient String name;
    private  String name;
    @Protobuf(fieldType = FieldType.INT32,order = 2)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
