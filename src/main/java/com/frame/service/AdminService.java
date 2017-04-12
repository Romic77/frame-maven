package com.frame.service;

import java.util.Map;

import javax.jms.Destination;

import com.frame.entity.Admin;

public interface AdminService extends BaseService<Admin, Long>{

	Admin login(Map<String,Object> param)  throws Exception;

	void sendMessage(Destination destination, final String message);
}
