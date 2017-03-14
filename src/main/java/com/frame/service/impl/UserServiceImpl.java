package com.frame.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.dao.UserMapper;
import com.frame.entity.User;
import com.frame.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService{
	@Resource  
	private UserMapper userMapper;  
	
	@Autowired
	public void setBaseMapper(){
		super.setBaseMapper(userMapper);
	}
	
	public User getUserById(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

}
