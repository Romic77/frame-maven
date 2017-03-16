package com.frame.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.dao.AdminMapper;
import com.frame.entity.Admin;
import com.frame.service.AdminService;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long> implements AdminService{
	@Resource  
	private AdminMapper adminMapper;  
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(adminMapper);
	}

	@Override
	public Admin login(Map<String,Object> param)  throws Exception{
		return adminMapper.login(param);
	}
}
