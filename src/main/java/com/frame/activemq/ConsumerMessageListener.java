package com.frame.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 消费者监听类
 * @see http://localhost:8161/admin/
 * @author Mafly
 */
@Component
public class ConsumerMessageListener implements MessageListener {
	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(ConsumerMessageListener.class);

	@Override
	public void onMessage(Message arg0) {
		// 监听发送到消息队列的文本消息，作强制转换。
		TextMessage textMessage = (TextMessage) arg0;
		try {
			System.out.println("接收到的消息内容是：" + textMessage.getText());

			// TODO: 你喜欢的任何事情...

		} catch (JMSException e) {
			logger.error("", e);
		}

	}

}
