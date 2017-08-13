package com.frame.test.gp.serializable;

import java.io.*;

/**
 * @author Administrator
 * @CREATE 2017/7/23 20:23
 */
public class SerializeDemo {
    public static void main(String[] args)throws Exception{
        serializePerson();

        deSerializePerson();
    }

    /**
     *@author: chenqi
     *@date: 2017-07-23 20:24:55
     *@description: 序列化person对象
    */
    private static void serializePerson() throws IOException {
        Person person=new Person();
        person.setName("mic");
        person.setAge(18);

        FileOutputStream fs=new FileOutputStream("person");
        ObjectOutputStream os=new ObjectOutputStream(fs);
        os.writeObject(person);
        os.close();
    }

    /**
     *@author: chenqi
     *@date: 2017-07-23 20:26:46
     *@description: 反序列化person对象
    */
    private static void deSerializePerson() throws IOException, ClassNotFoundException {
        FileInputStream fs=new FileInputStream("person");
        ObjectInputStream os=new ObjectInputStream(fs);
        Person person= (Person) os.readObject();
        System.out.println("反序列化成功:"+person);
        os.close();
    }
}
