package com.frame.dao;

import java.util.Map;

import com.frame.entity.Admin;

public interface AdminMapper extends BaseMapper<Admin, Long>{

	Admin login(Map<String,Object> param) throws Exception;
    
}