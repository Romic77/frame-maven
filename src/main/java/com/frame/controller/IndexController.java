package com.frame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

	@RequestMapping(value = "/index_v1", method = RequestMethod.GET)
	public ModelAndView index_v1(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		ModelAndView mv =new ModelAndView("index_v1");
        return mv;  
	}

	@RequestMapping(value = "/index_v2", method = RequestMethod.GET)
	public ModelAndView index_v2(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		ModelAndView mv =new ModelAndView("index_v2");
        return mv;  
	}
	
	@RequestMapping(value = "/index_v3", method = RequestMethod.GET)
	public ModelAndView index_v3(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		ModelAndView mv =new ModelAndView("index_v3");
        return mv;  
	}
	
	@RequestMapping(value = "/index_v4", method = RequestMethod.GET)
	public ModelAndView index_v4(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		ModelAndView mv =new ModelAndView("index_v4");
        return mv;  
	}
	
	@RequestMapping(value = "/index_v5", method = RequestMethod.GET)
	public ModelAndView index_v5(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		ModelAndView mv =new ModelAndView("index_v5");
        return mv;  
	}

	@RequestMapping(value = "/redisSession", method = RequestMethod.GET)
	public ModelAndView redisSession(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		ModelAndView mv =new ModelAndView("redis-session");
		return mv;
	}
}

