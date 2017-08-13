package com.frame.test.gp.socket.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author Administrator
 * @CREATE 2017/7/23 20:08
 */
public class MulticastClient1 {
    public static void main(String[] args) throws Exception{
        InetAddress group =InetAddress.getByName("224.5.6.7");
        try{
            MulticastSocket socket=new MulticastSocket(8888);
            socket.joinGroup(group);
            byte[] buf=new  byte[256];
            while (true){
                DatagramPacket msgPacket=new DatagramPacket(buf,buf.length);
                socket.receive(msgPacket);

                String msg=new String(msgPacket.getData());
                System.out.println("接手的数据："+msg);

            }
        }catch (Exception e){

        }
    }
}
