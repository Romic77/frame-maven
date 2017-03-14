package com.frame.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.entity.User;
import com.frame.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);    
	
	@Resource
	private UserService userService;
	
	 @RequestMapping("/showUser")  
     public String toIndex(int id,HttpServletRequest request,Model model){  
        User user = this.userService.selectByPrimaryKey(new Long(id));
        model.addAttribute("user", user); 
        return "showUser";  
	 }
	 
	 @RequestMapping("/insertUser")  
     public String save(HttpServletRequest request,Model model){
		User user=new User();
		user.setUserName("张三");
		user.setPassword("123");
		user.setAge(18);
		userService.insert(user);
        return "showUser";  
	 }
}
