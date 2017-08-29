package com.frame.test.gp.activemq1;

import com.sun.corba.se.impl.oa.toa.TOA;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @CREATE 2017/8/15 16:32
 * 订阅持久化
 */
public class ActiveMQTopicSubscriber {
	public static final String USER = ActiveMQConnectionFactory.DEFAULT_USER;
	public static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	/**
	 * 默认就是tcp://localhost:61616
	 */
	public static final String BIND_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL;

	public static void main(String[] args) throws JMSException, InterruptedException, IOException {
		ConnectionFactory connectionFactory = getConnectionFactory();
		Connection connection = connectionFactory.createConnection();  //connection代表了应用程序和消费服务器之间的通信链路,获得连接工厂后，就可以创建connection
		connection.start(); //2.创建连接的connection，注意连接默认是关闭的，因此需要start开启
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

		//4.通过session创建Destination对象，在PTP模式下是queue, 在pub/sub模式下是topic
		//所谓消息目标 就是发送和接收消息的地点，要么是queue,要么topic
		Topic topic=session.createTopic("first-topic");
		MessageConsumer messageConsumer=session.createConsumer(topic);
		messageConsumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				TextMessage tm = (TextMessage) message;
				try {
					System.out.println("Received message: " + tm.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		//if (connection != null) connection.close();
	}

	/**
	 * @author: chenqi
	 * @date: 2017-08-15 16:34:58
	 * @description: 1.创建连接工厂connectionFactory, 需要username/password/url
	 */
	public static ConnectionFactory getConnectionFactory() {
		return new ActiveMQConnectionFactory(USER, PASSWORD, BIND_URL);

	}
}
