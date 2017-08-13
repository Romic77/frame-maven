package com.frame.test.gp.rmi.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Administrator
 * @CREATE 2017/7/30 0:12
 */
public class User_Stub extends User {
	private Socket socket;

	public User_Stub() throws IOException {
		socket = new Socket("localhost", 8888);
	}

	public int getAge() {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject("age");
			objectOutputStream.flush();

			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			return inputStream.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
