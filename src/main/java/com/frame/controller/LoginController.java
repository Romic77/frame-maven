package com.frame.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.frame.entity.Admin;
import com.frame.exception.TipRuntimeException;
import com.frame.exception.VP;
import com.frame.service.AdminService;
import com.frame.utils.encrypt.MD5Utils;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{
	
private static final Logger logger = LoggerFactory.getLogger(LoginController.class);    
	
	@Resource
	private AdminService adminService;
	
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)  
    public String toLogin(HttpServletRequest request,Model model){  
		
        return "login";  
	}
	
	/**
	 * 
	 * @Title: login
	 * @Description: 登录
	 * @param request
	 * @param model
	 * @return
	 * @return: String
	 * @throws Exception 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
    public JSONObject login(String username,String password,HttpServletRequest request,Model model,HttpSession session) throws Exception{
		Map<String,Object> param=new HashMap<String,Object>();
		password=MD5Utils.getMD5Str(password).toUpperCase();
		param.put("username", username);
		param.put("password", password);
	    Admin admin=adminService.login(param);
	    if(admin==null){
		    throw new TipRuntimeException(VP.ERROR_LOGIN_ACC_PWD);
	    }
	    session.setAttribute("admin", admin);
	    JSONObject json=new JSONObject();
	    json.put("status", 1);
        return json;  
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		
        return "index";  
	}
	
	 
}
