package com.frame.test;

import com.frame.entity.Admin;
import com.frame.service.AdminService;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 *若写classpath*:spring-mvc.xml 和 classpath*:spring-mybatis.xml代表所有资源文件遍历，首先会查main资源，之后查找test资源
 */
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:spring-mvc.xml","classpath*:spring-mybatis.xml"})
public class TestMybatis {
    private static Logger logger = Logger.getLogger(TestMybatis.class);
    private ApplicationContext ac = null;

    @Resource
    private AdminService adminService;

    @Ignore
    @Test
    public void test1() throws Exception {
       Admin admin = adminService.selectByPrimaryKey(10000l);
       System.out.println(admin.getAdminId());
    }

    @Test
    public void addMember()throws Exception{
       Admin admin=new Admin();
       admin.setAdminName("陈奇");
       admin.setPassword("1234");
       adminService.insert(admin);
       //throw new Exception("测试事务！");
    }
}  