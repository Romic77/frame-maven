package com.frame.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.entity.Admin;
import com.frame.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);    
	
	@Resource
	private AdminService adminService;
	
	 @RequestMapping("/showAdmin")  
     public String toIndex(int id,HttpServletRequest request,Model model) throws Exception{  
        Admin admin = this.adminService.selectByPrimaryKey(new Long(id));
        model.addAttribute("admin", admin); 
        return "showUser";  
	 }
	 
	 @RequestMapping("/insertUser")  
     public String save(HttpServletRequest request,Model model) throws Exception{
		Admin admin=new Admin();
		admin.setAdminName("张三");
		admin.setPassword("123");
		adminService.insert(admin);
        return "showUser";  
	 }
}
