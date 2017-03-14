package com.frame.service;

import java.io.Serializable;

public interface BaseService<T, ID extends Serializable> {

	int deleteByPrimaryKey(ID id);

	int insertSelective(T record);

	T selectByPrimaryKey(ID id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKeyWithBLOBs(T record);

	int updateByPrimaryKey(T record);

	int insert(T record);

}
