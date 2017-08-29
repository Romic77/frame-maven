package com.frame.test.gp.activemq1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author Administrator
 * @CREATE 2017/8/16 13:45
 */
public class ActiveMQPublish {
	public static final String USER = ActiveMQConnectionFactory.DEFAULT_USER;
	public static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	/**
	 * 默认就是tcp://localhost:61616
	 */
	public static final String BIND_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL;

	public static void main(String[] args) throws JMSException, IOException {
		ConnectionFactory connectionFactory = getConnectionFactory();
		Connection connection = connectionFactory.createConnection();  //connection代表了应用程序和消费服务器之间的通信链路,获得连接工厂后，就可以创建connection
		connection.start(); //2.创建连接的connection，注意连接默认是关闭的，因此需要start开启
		//3.前面2步的操作就是为了创建session
		//创建session有一些配置参数 比如是否开启事务 签收模式
		//session用于发送和接手消息，而且是单线程的，支持事务的，如果session开启事务支持，那么session将保存一组信息
		//要么commit到MQ，要么回滚这些消息，session可以创建MessageProducer/MessageConsumer
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

		Topic topic = session.createTopic("first-topic");
		MessageProducer messageProducer = session.createProducer(topic);
		TextMessage textMessage=session.createTextMessage();
		textMessage.setText("Hello,I'm Topic");
		messageProducer.send(textMessage);

		if (connection != null) connection.close();

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
