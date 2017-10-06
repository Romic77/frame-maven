package com.frame.test.gp.activemq1;

import javax.jms.*;

/**
 * @author Administrator
 * @CREATE 2017/8/15 17:22
 * 消息监听
 */
public class Listener2 implements MessageListener {
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				TextMessage tm = (TextMessage) message;
				System.out.println("reciver2:"+tm.getText());
				tm.acknowledge();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}else if(message instanceof MapMessage){
			//TODO 使用instanceof判断message对象
			try {
				MapMessage mm = (MapMessage) message;

				System.out.println("用户名:"+mm.getStringProperty("name")+";年龄："+mm.getIntProperty("age"));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			System.out.println("进入else");
		}
	}
}
