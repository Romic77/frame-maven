package com.frame.test.gp.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * @CREATE 2017/7/22 22:37
 * socket服务端的代码
 */
public class SocketServerTest {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8888);
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(() -> {
					try {
						//读取数据
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						//发送数据
						PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
						while (true) {
							String clientData = bufferedReader.readLine();
							if (clientData == null) {
								break;
							}
							System.out.println("服务端接受的数据：" + clientData);
							writer.println();
							writer.flush();
						}
					} catch (IOException e) {

					}
				}).start();
			}
		}catch (Exception e){

		}finally {
			if(serverSocket!=null){
				serverSocket.close();
			}
		}

	}
}
