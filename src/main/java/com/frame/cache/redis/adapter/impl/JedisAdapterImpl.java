package com.frame.cache.redis.adapter.impl;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.apache.log4j.Logger;

import com.frame.cache.SerializeUtil;
import com.frame.cache.redis.JedisUtil;
import com.frame.cache.redis.adapter.JedisAdapter;

import redis.clients.jedis.Jedis;



/**
 * Jedis适配器实现
 * @createTime 2015年10月26日 上午11:01:49 
 * @author yxl
 */
public class JedisAdapterImpl implements JedisAdapter {
	
	private static Logger logger = Logger.getLogger(JedisAdapterImpl.class);

	/**
	 * 设置缓存
	 * @param key 关键字
	 * @param value 可以把任何对象存放到缓存中
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 */
	@Override
	public String set(String key, Object value) throws Exception {
		Jedis jedis = null;
		String result = "";
		try {
			jedis = JedisUtil.jedisPool.getResource();
			jedis.select(JedisUtil.dbIndex);
			byte[] jedisKeyByte = key.getBytes(JedisUtil.charsert);
			result = jedis.set(jedisKeyByte, SerializeUtil.serialize(value));
			if(JedisUtil.expireTime>0) jedis.expire(jedisKeyByte, JedisUtil.expireTime);
		} finally {
			if (jedis != null) JedisUtil.jedisPool.returnResource(jedis);
		}
		return result;
	}

	/**
	 * get缓存
	 * @param key 关键字
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 * @throws Exception 
	 */
	@Override
	public Object get(String key) throws Exception {
		Jedis jedis = null;
		Object value = null;
		try {
			jedis = JedisUtil.jedisPool.getResource();
			jedis.select(JedisUtil.dbIndex);
			value = SerializeUtil.unserialize(jedis.get(key.getBytes(JedisUtil.charsert)));
		} finally {
			if (jedis != null) JedisUtil.jedisPool.returnResource(jedis);
		}
		return value;
	}

	/**
	 * delete缓存
	 * @param key 关键字，可以使用*匹配
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 */
	@Override
	public String delete(String key) throws UnsupportedEncodingException {
		Jedis jedis = null;
		Object value = null;
		try {
			jedis = JedisUtil.jedisPool.getResource();
			jedis.select(JedisUtil.dbIndex);
			value = jedis.del(key.getBytes(JedisUtil.charsert));
		} finally {
			if (jedis != null) JedisUtil.jedisPool.returnResource(jedis);
		}
		return value.toString();
	}

	/**
	 * 根据条件查询key
	 * @param key 关键字，可以使用*匹配
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 */
	@Override
	public Set<byte[]> getKeys(String key) throws UnsupportedEncodingException {
		Jedis jedis = null;
		Set<byte[]> value = null;
		try {
			jedis = JedisUtil.jedisPool.getResource();
			jedis.select(JedisUtil.dbIndex);
			value = jedis.keys(key.getBytes(JedisUtil.charsert));
		} finally {
			if (jedis != null) JedisUtil.jedisPool.returnResource(jedis);
		}
		return value;
	}

	/**
	 * 清除redis所有缓存
	 * 
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 */
	@Override
	public void flushAll() throws Exception {
		Jedis jedis = null;
		try {
			jedis = JedisUtil.jedisPool.getResource();
			jedis.select(JedisUtil.dbIndex);
			jedis.flushAll();
		} finally {
			if (jedis != null) JedisUtil.jedisPool.returnResource(jedis);
		}
	}
}