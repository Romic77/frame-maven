package com.frame.service.impl;

import java.io.Serializable;

import com.frame.dao.BaseMapper;
import com.frame.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
	private BaseMapper<T, ID> baseMapper;
	
	public void setBaseMapper(BaseMapper<T, ID> baseMapper) {
		this.baseMapper = baseMapper;
	}
	@Override
	public int deleteByPrimaryKey(ID id) throws Exception{
		return baseMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insertSelective(T record) throws Exception{
		return baseMapper.insertSelective(record);
	}
	@Override
	public T selectByPrimaryKey(ID id) throws Exception{
		return baseMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(T record) throws Exception{
		return baseMapper.updateByPrimaryKey(record);
	}
	@Override
	public int updateByPrimaryKeyWithBLOBs(T record) throws Exception{
		return baseMapper.updateByPrimaryKeyWithBLOBs(record);
	}
	@Override
	public int updateByPrimaryKey(T record) throws Exception{
		return baseMapper.updateByPrimaryKey(record);
	}
	@Transactional
	@Override
	public int insert(T record) throws Exception{
		return baseMapper.insert(record);
	}
}
