package com.frame.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.frame.cache.redis.JedisUtil;
import com.frame.entity.User;
import com.frame.service.UserService;  

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMybatis{  
    private static Logger logger = Logger.getLogger(TestMybatis.class);  
    private ApplicationContext ac = null;  
    
    @Resource  
    private UserService userService;  
  
  /*@Before  
  public void before() {  
      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
      userService = (UserService) ac.getBean("userService");  
  }  */
  
    /*@Test  
    public void test1() {  
        User user = userService.selectByPrimaryKey(1l);
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(user));  
    }  */
   
}  