package com.frame.test;

import com.eascs.es.param.ParamObj;
import com.eascs.es.service.ESService;
import com.eascs.es.service.impl.ESServiceImpl;
import com.frame.entity.Admin;
import com.frame.service.AdminService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 若写classpath*:spring-mvc.xml 和 classpath*:spring-mybatis.xml代表所有资源文件遍历，首先会查main资源，之后查找test资源
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:spring-mvc.xml", "classpath*:spring-mybatis.xml"})
public class TestMybatis {
	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(TestMybatis.class);

	@Resource
	private AdminService adminService;


	@Test
	public void addIndex1() throws Exception{
		Product product=new Product();
		product.setProduct_name("番茄1");
		product.setProduct_date(new Date());
		logger.info("插入");
		ESService es = new ESServiceImpl();
		ParamObj p = new ParamObj();
		p.setPojoData(product);
		try {
			es.addIndex("yiyatong", "xinglian", String.valueOf(10086), p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addMember() throws Exception {
		for (int i = 0; i < 10000000; i++) {
			Admin admin = new Admin();
			admin.setAdminName("chenqi" + i);
			admin.setPassword("1234");
			adminService.save(admin);
			logger.info("插入" + i);
			ESService es = new ESServiceImpl();
			ParamObj p = new ParamObj();
			p.setPojoData(admin);
			try {
				es.addIndex("admin1", "xlsh", String.valueOf(admin.getAdminId()), p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	  *@author: Administrator
	  *@date: 2017/8/2 9:38
	  *@description: 查询
	  */
	@Test
	public void search() throws Exception{
		ESService es = new ESServiceImpl();
		//String index,String type,String searchField,String keyword,Integer pageNum,Integer pageSize,String orderField,String order
		System.out.println(es.search("admin1","xlsh","","hello我是的",1,10,"adminName","desc"));
	}

	/**
	 *@author: Administrator
	 *@date: 2017/8/2 9:38
	 *@description: 同义词查询
	 */
	@Test
	public void searchSynonym()throws Exception{
		ESService es =new ESServiceImpl();
		System.out.println(es.search("yiyatong","xinglian","product_name","西红柿",0,0,"",""));

	}

	@Ignore
	@Test
	public void logTest() throws Exception {
		logger.info("start");
		Admin admin = new Admin();
		admin.setAdminName("陈奇");
		admin.setPassword("1234");
		adminService.save(admin);
		logger.error("end");
	}
	@Ignore
	@Test
	public void selectTest() {
		Admin admin = adminService.selectByKey(10001l);
		System.out.println(admin.getAdminName());
	}

	/**
	  *@author: Administrator
	  *@date: 2017/8/2 9:35
	  *@description: 添加索引
	  */
	@Test
	public void addIndex(){
		Example example=new Example(Admin.class);
		List<Admin> adminList=adminService.selectByExample(example);
		String[] ids=new String[adminList.size()];
		for (int i=0;i<adminList.size();i++){
			Admin admin=adminList.get(i);
			ids[i]=admin.getAdminId().toString();
		}
		ESService es = new ESServiceImpl();
		ParamObj p = new ParamObj();
		p.setPojoData(adminList);
		try {
			es.bulkAddIndex("admin1", "xlsh", ids, adminList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteIndex() throws Exception {
		ESService es=new ESServiceImpl();
		System.out.println(es.deleteIndex("yiyatong","xinglian","12830"));
	}
}