package com.frame.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@SuppressWarnings("rawtypes")
public interface BaseMapper<T, ID extends Serializable> {
	int deleteByPrimaryKey(ID id) throws Exception;

	int insert(T record) throws Exception;

	int insertSelective(T record) throws Exception;

	T selectByPrimaryKey(ID id) throws Exception;

	int updateByPrimaryKeySelective(T record) throws Exception;

	int updateByPrimaryKeyWithBLOBs(T record) throws Exception;

	int updateByPrimaryKey(T record) throws Exception;
}
