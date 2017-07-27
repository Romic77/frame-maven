package com.frame.controller;

import com.frame.entity.Admin;
import com.frame.service.AdminService;
import com.frame.utils.encrypt.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.frame.utils.PropertiesUtils.read;

@Controller
@RequestMapping("/admin")
public class AdminController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	 @RequestMapping("/showAdmin")  
     public String toIndex(int id,HttpServletRequest request,Model model) throws Exception{  
        Admin admin = this.adminService.selectByKey(10000l);
        model.addAttribute("admin", admin); 
        return "showUser";  
	 }
	 
	 @RequestMapping("/insertUser")  
     public String save(HttpServletRequest request,Model model) throws Exception{
		Admin admin=new Admin();
		admin.setAdminName("admin1");
		admin.setPassword(MD5Utils.getGBKMD5Str("123456"));
		adminService.save(admin);
        return "showUser";
	 }

	 /**
	   *@author: Administrator
	   *@date: 2017/7/25 22:26
	   *@description: disconf回调
	   */
	 @ResponseBody
	 @RequestMapping("/getProperties")
	 public String getProperties() throws Exception {
		LOGGER.debug("read cfg.peoperties="+read("redis.properties"));
	 	return "read="+read("cfg.properties");
	 }
}
