package com.frame.test.gp.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author Administrator
 * @CREATE 2017/7/22 23:14
 */
public class SocketClientTest {
    public static void main(String[] args)throws Exception{
        System.out.println("尝试链接服务器Ip端口 127.0.0.1:8899");
        Socket socket=new Socket("127.0.0.1",8899);
        System.out.println("客户端成功链接到服务器");

        //发送消息
        String ipRequest="Hello 菲菲";
       // String requsetLength=String.format("%04d",ipRequest.length());

        //ipRequest=ipRequest+requsetLength;
        System.out.println("发送给服务端的消息是："+ipRequest);
        InputStream in=new ByteArrayInputStream(ipRequest.getBytes());
        BufferedReader line =new BufferedReader(new InputStreamReader(in));

        PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
        out.println(line.readLine());
        out.close();
        in.close();
        socket.close();

    }
}
