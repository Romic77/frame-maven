package com.frame.test;

import com.frame.entity.Admin;
import com.frame.service.AdminService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 *若写classpath*:spring-mvc.xml 和 classpath*:spring-mybatis.xml代表所有资源文件遍历，首先会查main资源，之后查找test资源
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:spring-mvc.xml","classpath*:spring-mybatis.xml"})
public class TestMybatis {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(TestMybatis.class);

    @Resource
    private AdminService adminService;

    @Ignore
    @Test
    public void addMember()throws Exception{
        Admin admin=new Admin();
        admin.setAdminName("陈奇");
        admin.setPassword("1234");
        adminService.save(admin);
    }

    @Ignore
    @Test
    public void logTest()throws Exception{
        logger.info("start");
        Admin admin=new Admin();
        admin.setAdminName("陈奇");
        admin.setPassword("1234");
        adminService.save(admin);
        logger.error("end");
    }

    @Test
    public void selectTest(){
        Admin admin=adminService.selectByKey(10001l);
        System.out.println(admin.getAdminName());
    }
}