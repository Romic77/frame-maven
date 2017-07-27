package com.frame.mapper;

import com.frame.entity.Admin;
import com.frame.utils.MyMapper;

import java.util.Map;

public interface AdminMapper extends MyMapper<Admin> {

	Admin login(Map<String,Object> param) throws Exception;
    
}