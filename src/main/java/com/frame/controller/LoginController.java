package com.frame.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.frame.activemq.MessageSender;
import com.frame.entity.Admin;
import com.frame.exception.TipRuntimeException;
import com.frame.exception.VP;
import com.frame.service.AdminService;
import com.frame.utils.encrypt.MD5Utils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private AdminService adminService;
	
	@Resource
	private MessageSender messageSender;
	
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)  
    public ModelAndView toLogin(HttpServletRequest request,Model model){
        return new ModelAndView("login");
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
		logger.info("start login");
		Map<String,Object> param=new HashMap<String,Object>();
		password=MD5Utils.getMD5Str(password).toUpperCase();
		param.put("username", username);
		param.put("password", password);
	    Admin admin=adminService.login(param);
	    if(admin==null){
		    throw new TipRuntimeException(VP.ERROR_LOGIN_ACC_PWD);
	    }
	    //messageSender.userLogin(admin.getAdminId(), admin.getAdminName());
	    session.setAttribute("admin", admin);
	    JSONObject json=new JSONObject();
	    json.put("status", 1);
		logger.info("end login");
        return json;  
	}

	/**
	 *
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public ModelAndView exit(HttpServletRequest request, Model model, HttpSession session) throws Exception{
		session.invalidate();
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		
        return new ModelAndView("index");
	}
	
	 
}
