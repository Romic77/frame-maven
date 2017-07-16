package com.frame.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @ClassName SerializeUtils
 * @Description 序列化工具
 * @author K
 * @Date 2016年6月24日 上午9:44:38
 * @version 1.0.0
 */
public class SerializeUtil {

	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

	/**
	 * 
	 * @Description 序列化
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static byte[] serialize(Object object) throws Exception {
		if(object == null) return null;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			logger.error(String.valueOf(e));
			throw e;
		}
	}

	/**
	 * 
	 * @Description 反序列化
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static Object unserialize(byte[] bytes) throws Exception {
		if(bytes == null) return null;
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			logger.error(String.valueOf(e));
			throw e;
		}
	}
}
