package com.frame.service;

import java.io.Serializable;

public interface BaseService<T, ID extends Serializable> {

	int deleteByPrimaryKey(ID id) throws Exception;

	int insertSelective(T record) throws Exception;

	T selectByPrimaryKey(ID id) throws Exception;

	int updateByPrimaryKeySelective(T record) throws Exception;

	int updateByPrimaryKeyWithBLOBs(T record) throws Exception;

	int updateByPrimaryKey(T record) throws Exception;

	int insert(T record) throws Exception;

}
