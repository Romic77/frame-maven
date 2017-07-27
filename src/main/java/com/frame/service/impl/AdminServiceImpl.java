package com.frame.service.impl;

import com.frame.mapper.AdminMapper;
import com.frame.entity.Admin;
import com.frame.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Map;

/**
 * @author:AdminServiceImpl
 * @Date:2017/7/27 14:35
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    public Admin login(Map<String, Object> param) throws Exception {

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
