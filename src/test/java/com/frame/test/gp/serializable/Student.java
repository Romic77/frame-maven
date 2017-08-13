package com.frame.test.gp.serializable;

import com.frame.test.gp.serializable.clone.Teacher;

import java.io.*;

/**
 * @author Administrator
 * @CREATE 2017/7/23 22:58
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 7985996175783631986L;

    private String name;

    private int age;

    private Teacher teacher;

    public Object deelClone() throws IOException,ClassNotFoundException{
        //序列化
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(baos);

        oos.writeObject(this);

        //反序列化
        ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois=new ObjectInputStream(bais);
        return ois.readObject();
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", teacher=" + teacher +
                '}';
    }

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
