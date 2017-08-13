package com.frame.test.gp.serializable.clone;

import com.frame.test.gp.serializable.Student;

import java.io.IOException;

/**
 * @author Administrator
 * @CREATE 2017/7/23 23:03
 */
public class CloneTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Teacher teacher=new Teacher();
        teacher.setName("mic");
        Student student=new Student();
        student.setName("feifei");
        student.setAge(18);
        student.setTeacher(teacher);

        Student student2= (Student) student.deelClone();
        System.out.println(student2);

        student2.getTeacher().setName("james");
        System.out.println(student2);
    }
}
