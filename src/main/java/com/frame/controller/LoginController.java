package com.frame.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
private static final Logger logger = LoggerFactory.getLogger(LoginController.class);    
	
	@Resource
	private UserService userService;
	
	 @RequestMapping("/toLogin")  
     public String toLogin(HttpServletRequest request,Model model){  
         
        return "login";  
	 }
	 
}
