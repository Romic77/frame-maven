package com.frame.test.gp.activemq1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Administrator
 * @CREATE 2017/8/15 17:09
 * 使用listener被动监听消息（非阻塞的）（观察者模式）
 */
public class ActimeMQConsumer2 {
	public static final String USER = ActiveMQConnectionFactory.DEFAULT_USER;
	public static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	/**
	 * 默认就是tcp://localhost:61616
	 */
	public static final String BIND_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL;

	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = getConnectionFactory();
		Connection connection = connectionFactory.createConnection();  //connection代表了应用程序和消费服务器之间的通信链路,获得连接工厂后，就可以创建connection
		connection.start(); //2.创建连接的connection，注意连接默认是关闭的，因此需要start开启
		//3.前面2步的操作就是为了创建session
		//创建session有一些配置参数 比如是否开启事务 签收模式
		//session用于发送和接手消息，而且是单线程的，支持事务的，如果session开启事务支持，那么session将保存一组信息
		//要么commit到MQ，要么回滚这些消息，session可以创建MessageProducer/MessageConsumer
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

		//4.通过session创建Destination对象，在PTP模式下是queue, 在pub/sub模式下是topic
		//所谓消息目标 接收消息的地点，要么是queue,要么topic
		Destination destination = session.createQueue("queue1");

		//5.通过session创建 发送消息的生产者/接收消息的消费者
		MessageConsumer messageConsumer = session.createConsumer(destination);

		try {
			messageConsumer.setMessageListener(new Listener()); //注册消息监听
		} catch (Exception e) {
			e.printStackTrace();
		}


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
