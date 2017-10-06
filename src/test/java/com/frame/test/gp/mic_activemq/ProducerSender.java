package com.frame.test.gp.mic_activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Administrator
 * @CREATE 2017/9/24 11:09
 */
public class ProducerSender {
	public static final String USER = ActiveMQConnectionFactory.DEFAULT_USER;
	public static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;

	public static final String BIND_URL = "failover:(tcp://192.168.202.133:61616,tcp://192.168.202.134:61616,tcp://192.168.202.135:61616)?Randomize=false";

	/**
	 * @author: chenqi
	 * @date: 2017-08-15 16:34:58
	 * @description: 1.创建连接工厂connectionFactory, 需要username/password/url
	 */
	public static ConnectionFactory getConnectionFactory() {
		return new ActiveMQConnectionFactory(USER, PASSWORD, BIND_URL);

	}


	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = getConnectionFactory();
		Connection connection = connectionFactory.createConnection();  //connection代表了应用程序和消费服务器之间的通信链路,获得连接工厂后，就可以创建connection
		connection.start(); //2.创建连接的connection，注意连接默认是关闭的，因此需要start开启
		//3.前面2步的操作就是为了创建session
		//创建session有一些配置参数 比如是否开启事务 签收模式
		//session用于发送和接手消息，而且是单线程的，支持事务的，如果session开启事务支持，那么session将保存一组信息
		//要么commit到MQ，要么回滚这些消息，session可以创建MessageProducer/MessageConsumer
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

		//4.通过session创建Destination对象，在PTP模式下是queue, 在pub/sub模式下是topic
		//所谓消息目标 就是发送和接收消息的地点，要么是queue,要么topic
		Destination destination = session.createQueue("queue1");

		//5.通过session创建 发送消息的生产者/接收消息的消费者
		MessageProducer messageProducer = session.createProducer(destination);

		//6.设置持久化/费持久化特性 如果非持久化，那么意味着MQ的重启会导致消息丢失,默认是持久化的
		//如果持久化到kahdb/leveldb/jdbc方式的话，意味着消息持久化
		//messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		//7.定义jms规范的消息类型
		//生产者和消费者传递对象，由3个补分构成：
		//消息头(路由)+消息属性（消息选择器）+消息体（5种）
		//StreamMessage Java原始的数据流
		//MapMessage 键值对
		//TextMessage 字符串
		//ObjectMessage 一个序列化的Java对象
		//BytesMessage 一个未解释的数据流
		TextMessage textMessage = session.createTextMessage();
		textMessage.setText("Hello,ActiveMQ");
		messageProducer.send(textMessage);

		session.commit();
		session.close();
		if (connection != null) connection.close();

	}
}
