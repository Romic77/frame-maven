package com.frame.test.gp.serializable.json;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.frame.test.gp.serializable.Person;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Administrator
 * @CREATE 2017/7/23 23:20
 */
public class JsonDemo {

    private static Person init(){
        Person person=new Person();
        person.setName("mic");
        person.setAge(18);
        return person;
    }
    
    public static void main(String[] args) throws Exception {
        executeWithJack();

        executeWithProtoBuf();

        executeWithHissian();
    }

    /**
     *@author: chenqi
     *@date: 2017-07-23 23:27:03
     *@description: fastjson
    */
    private static void executeWithJack() throws IOException{
        Person person=init();

        ObjectMapper mapper=new ObjectMapper();
        byte[] writeBytes=null;
        Long start=System.currentTimeMillis();
        for (int i=0;i<10000;i++){
            writeBytes=mapper.writeValueAsBytes(person);
        }
        System.out.println("Json序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "总大小->"+writeBytes.length);

        Person person1=mapper.readValue(writeBytes,Person.class);
        System.out.println(person1);
    }

    /**
     *@author: chenqi
     *@date: 2017-07-23 23:35:10
     *@description: 基于google的protobuf
    */
    private static void executeWithProtoBuf() throws IOException{
        Person person=init();
        Codec<Person> personCodec= ProtobufProxy.create(Person.class,false);

        Long start=System.currentTimeMillis();
        byte[] bytes=null;
        for(int i=0;i<10000;i++){
            bytes=personCodec.encode(person);
        }
        System.out.println("protobuf序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "总大小->"+bytes.length);

        Person person1=personCodec.decode(bytes);
        System.out.println(person1);
    }

    /**
     *@author: chenqi
     *@date: 2017-07-23 23:36:28
     *@description:  基于hessian
    */
    private static void executeWithHissian() throws Exception{
        Person person =init();
        ByteArrayOutputStream os =new ByteArrayOutputStream();
        HessianOutput ho=new HessianOutput(os);
        Long start=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            ho.writeObject(person);
            if (i==0){
                System.out.println(os.toByteArray().length);
            }
        }
        System.out.println("Hessian序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "总大小->"+os.toByteArray().length);
        HessianInput hi=new HessianInput(new ByteArrayInputStream(os.toByteArray()));
        Person person1= (Person) hi.readObject();
        System.out.println(person1);
    }

}
