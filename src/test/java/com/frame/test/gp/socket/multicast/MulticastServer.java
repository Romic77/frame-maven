package com.frame.test.gp.socket.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/7/23 20:03
 */
public class MulticastServer {
    public static void main(String[] args) throws Exception {
        try {
            InetAddress group =InetAddress.getByName("224.5.6.7");

            MulticastSocket socket=new MulticastSocket();

            for (int i =0;i<10;i++){
                String data="Hello mic";
                byte[] bytes=data.getBytes();
                socket.send(new DatagramPacket(bytes,bytes.length,group,8888));
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
