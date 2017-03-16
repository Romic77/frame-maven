package com.frame.service;

import java.util.Map;

import com.frame.entity.Admin;

public interface AdminService extends BaseService<Admin, Long>{

	Admin login(Map<String,Object> param)  throws Exception;

}
