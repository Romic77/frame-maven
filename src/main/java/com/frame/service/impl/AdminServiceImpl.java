package com.frame.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.frame.dao.AdminMapper;
import com.frame.entity.Admin;
import com.frame.service.AdminService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long> implements AdminService{
	@Resource  
	private AdminMapper adminMapper;  
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(adminMapper);
	}

	@Override
	public Admin login(Map<String,Object> param)  throws Exception{
		return adminMapper.login(param);
	}

	private JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendMessage(javax.jms.Destination destination,final String message) {
    	
        /**
         * 发送消息,这里使用一个内部类实现,内部类引用的外层类的属性值需要被定义为final
         */
        jmsTemplate.send(destination, new MessageCreator() {
            
            @Override
            public Message createMessage(Session session) throws JMSException {
                
                return session.createTextMessage(message);
            }
			
        });
    }
    
}
