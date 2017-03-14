package com.frame.cache.redis.adapter.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClusterCommand;

import com.frame.cache.SerializeUtil;
import com.frame.cache.redis.JedisUtil;
import com.frame.cache.redis.adapter.JedisAdapter;



/**
 * Jedis适配器集群实现
 * @createTime 2015年10月26日 上午11:01:49 
 * @author yxl
 */
public class JedisAdapterClusterImpl implements JedisAdapter {

	private static Logger logger = Logger.getLogger(JedisAdapterClusterImpl.class);
	
	/**
	 * 设置缓存
	 * @param key 关键字
	 * @param value 可以把任何对象存放到缓存中
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 */
	@Override
	public String set(final String key, final Object value) throws UnsupportedEncodingException {
		final byte[] keyByte = key.getBytes(JedisUtil.charsert);
		return new JedisClusterCommand<String>(JedisUtil.connectionHandler, JedisUtil.maxRedirections) {
			@Override
			public String execute(Jedis connection){
				try {
					String result = connection.set(keyByte, SerializeUtil.serialize(value));
					if (JedisUtil.expireTime > 0) connection.expire(keyByte, JedisUtil.expireTime);
				return result;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		}.run(key);
	}

	/**
	 * get缓存
	 * @param key 关键字
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 */
	@Override
	public Object get(final String key) throws UnsupportedEncodingException {
		final byte[] keyByte = key.getBytes(JedisUtil.charsert);
		return new JedisClusterCommand<Object>(JedisUtil.connectionHandler, JedisUtil.maxRedirections) {
			@Override
			public Object execute(Jedis connection) {
				try {
					return SerializeUtil.unserialize(connection.get(keyByte));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}.run(key);
	}

	/**
	 * delete缓存
	 * @param key 关键字，可以使用*匹配
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 */
	public String delete(final String key) throws UnsupportedEncodingException {
		final byte[] keyByte = key.getBytes(JedisUtil.charsert);
		return new JedisClusterCommand<String>(JedisUtil.connectionHandler, JedisUtil.maxRedirections) {
			@Override
			public String execute(Jedis connection) {
				return connection.del(keyByte).toString();
			}
		}.run(key);
	}

	/**
	 * 根据条件查询key
	 * @param key 关键字，可以使用*匹配
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 */
	@Override
	public Set<byte[]> getKeys(final String key) throws UnsupportedEncodingException {
		final byte[] keyByte = key.getBytes(JedisUtil.charsert);
		return new JedisClusterCommand<Set<byte[]>>(JedisUtil.connectionHandler, JedisUtil.maxRedirections) {
			@Override
			public Set<byte[]> execute(Jedis connection) {
				Set<byte[]> result = new HashSet<byte[]>();
				for(Jedis jedis : JedisUtil.clusterConnections) result.addAll(jedis.keys(keyByte));
				//去重
				Set<String> resultStrSet = new HashSet<String>();
				for(byte[] resultByte : result) resultStrSet.add(new String(resultByte));
				result.clear();
				for(String resultStr : resultStrSet) result.add(resultStr.getBytes());
				return result;
			}
		}.run(key);
	}

	/**
	 * 清除redis所有缓存
	 * 
	 * @date 2015年10月23日 下午6:15:33
	 * @author yxl
	 */
	@Override
	public void flushAll() throws Exception {
		new JedisClusterCommand<String>(JedisUtil.connectionHandler, JedisUtil.maxRedirections) {
			@Override
			public String execute(Jedis connection) {
				for(Jedis jedis : JedisUtil.clusterConnections) jedis.flushAll();
				return null;
			}
		}.run("flushAll");
	}
}