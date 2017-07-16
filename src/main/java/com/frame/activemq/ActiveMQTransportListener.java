package com.frame.activemq;

import java.io.IOException;

import org.apache.activemq.transport.TransportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 消息传输监听
 * @author Mafly
 *
 */
public class ActiveMQTransportListener implements TransportListener {
	
	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(ActiveMQTransportListener.class);

	/**     
	 * 对消息传输命令进行监控     
	 */
	@Override     
	public void onCommand(Object o) {
		
	}     
     
	/**     
	 * 对监控到的异常进行触发     
	 * @param error     
	 */     
	@Override     
	public void onException(IOException error) {
		logger.error("onException -> 消息服务器连接错误......", error);
	}     
     
	/**     
	 * 当failover时触发     
	 */     
	@Override     
	public void transportInterupted() {
		logger.warn("transportInterupted -> 消息服务器连接发生中断...");
		//这里就可以状态进行标识了
		
	}     
     
	/**     
	 * 监控到failover恢复后进行触发     
	 */     
	@Override     
	public void transportResumed() {
		logger.info("transportResumed -> 消息服务器连接已恢复...");
		//这里就可以进行状态标识了
	}
}
