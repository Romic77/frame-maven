package com.frame.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.frame.entity.Admin;

public class BaseController {
	/** 
     * 获取请求属性封装为Map类型 
     * @param request 
     * @return 
     */  
    protected HashMap<String, Object> getRequestMap(HttpServletRequest request) {  
        HashMap<String, Object> conditions = new HashMap<String, Object>();  
        Map map = request.getParameterMap();  
        for (Object o : map.keySet()) {  
            String key = (String) o;  
            conditions.put(key, ((String[]) map.get(key))[0]);  
        }  
        return conditions;  
    }  
    
  //① 获取保存在Session中的用户对象  
    protected Admin getSessionUser(HttpServletRequest request) {  
        return (Admin) request.getSession().getAttribute("admin");  
    }  
     
     //②将用户对象保存到Session中  
    protected void setSessionUser(HttpServletRequest request,Admin admin) {  
        request.getSession().setAttribute("admin",  
        		admin);  
    }  
      
    //③ 获取基于应用程序的url绝对路径  
    public final String getAppbaseUrl(HttpServletRequest request, String url) {  
        Assert.hasLength(url, "url不能为空");  
        Assert.isTrue(url.startsWith("/"), "必须以/打头");  
        return request.getContextPath() + url;  
    }  
      
}
