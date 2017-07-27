package com.frame.service;

import com.frame.entity.Admin;

import javax.jms.Destination;
import java.util.Map;

public interface AdminService extends BaseService<Admin> {

	Admin login(Map<String,Object> param)  throws Exception;

	void sendMessage(Destination destination, final String message);
}
