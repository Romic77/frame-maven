package com.frame.cache;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;

import com.frame.cache.redis.JedisUtil;


/**
 * Mybatis Redis 缓存实现
 * 
 * @createTime 2015年10月15日 下午3:53:07
 * @author yxl
 */
public class MybatisRedisCache implements Cache {

	private static Logger logger = Logger.getLogger(MybatisRedisCache.class);
	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	/** ID （Mapper） */
	private String id;

	/**
	 * 构造方法
	 * 
	 * @date 2015年10月15日 下午6:23:24
	 * @author yxl
	 */
	public MybatisRedisCache(final String id) {
		if (id == null) throw new IllegalArgumentException("初始化MybatisRedisCache必须传入Mapper ID");
		this.id = id;
	}

	/**
	 * 组合key
	 * 
	 * @date 2015年10月15日 下午6:22:57
	 * @author yxl
	 */
	private String getKey(Object key) {
		StringBuilder sb = new StringBuilder();
		String methodStr = key.toString();
		try{
			methodStr = methodStr.substring(methodStr.indexOf(id) + id.length());
			methodStr = methodStr.substring(0, methodStr.indexOf(":"));
		} catch(Exception e){
			methodStr = "";
		}
		sb.append(id+methodStr).append(".");
		sb.append(DigestUtils.md5Hex(String.valueOf(key)));
		return sb.toString();
	}

	/**
	 * 获取ID（Mapper）
	 * 
	 * @date 2015年10月15日 下午6:23:24
	 * @author yxl
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * 获取缓存数量
	 * 
	 * @date 2015年10月15日 下午6:23:24
	 * @author yxl
	 */
	@Override
	public int getSize() {
		int result = 0;
		try {
			String jedisKey = id + "*";
			Set<byte[]> keys = JedisUtil.getKeys(jedisKey);
			if (null != keys && !keys.isEmpty()) result = keys.size();
			logger.info("MybatisRedisCache getSize：" + jedisKey);
		} catch (Exception e) {
			logger.error("查询缓存size发生异常[" + id + "]: " + e.getMessage());
		}
		return result;
	}

	/**
	 * 添加缓存
	 * 
	 * @date 2015年10月15日 下午6:23:24
	 * @author yxl
	 */
	@Override
	public void putObject(Object key, Object value) {
		try {
			String jedisKey = getKey(key);
			JedisUtil.set(jedisKey, value);
			logger.info("MybatisRedisCache putObject：" + jedisKey);
		} catch (Exception e) {
			logger.error("添加缓存发生异常[" + id + "]: " + e.getMessage());
		}
	}

	/**
	 * 获取缓存
	 * 
	 * @date 2015年10月15日 下午6:23:24
	 * @author yxl
	 */
	@Override
	public Object getObject(Object key) {
		Object value = null;
		try {
			String jedisKey = getKey(key);
			value = JedisUtil.get(jedisKey);
			logger.info("MybatisRedisCache getObject：" + jedisKey);
		} catch (Exception e) {
			logger.error("获取缓存发生异常[" + id + "]: " + e.getMessage());
		}
		return value;
	}

	/**
	 * 移除缓存
	 * 
	 * @date 2015年10月15日 下午6:23:24
	 * @author yxl
	 */
	@Override
	public Object removeObject(Object key) {
		try {
			String jedisKey = getKey(key);
			JedisUtil.delete(jedisKey);
			logger.info("MybatisRedisCache removeObject：" + jedisKey);
		} catch (Exception e) {
			logger.error("移除缓存发生异常[" + id + "]: " + e.getMessage());
		}
		return null;
	}

	/**
	 * 清理缓存（CUD）
	 * 
	 * @date 2015年10月15日 下午6:23:24
	 * @author yxl
	 */
	@Override
	public void clear() {
		try {
			String jedisKey = id + "*";
			logger.info("MybatisRedisCache clear：" + jedisKey);
			JedisUtil.delete(jedisKey);
		} catch (Exception e) {
			logger.error("清理缓存发生异常[" + id + "]: " + e.getMessage());
		}
	}

	/**
	 * 加锁
	 * 
	 * @date 2015年10月15日 下午6:23:24
	 * @author yxl
	 */
	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}
}