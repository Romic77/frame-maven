package com.frame.utils.disconf;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author liaoqiqi
 * @version 2014-6-17
 */
public class JedisUtil {
    protected static final Logger LOGGER = LoggerFactory.getLogger(JedisUtil.class);
    public static Jedis createJedis() {
        Jedis jedis = new Jedis("172.30.8.205");
        return jedis;
    }

    public static Jedis createJedis(String host, int port) {
        Jedis jedis = new Jedis(host, port);
        if (jedis==null){
            LOGGER.info("jedis is null");
            return null;
        }
        return jedis;
    }


    public static Jedis createJedis(String host, int port, String passwrod) {
        Jedis jedis = new Jedis(host, port);

        if (!StringUtils.isNotBlank(passwrod))
            jedis.auth(passwrod);

        return jedis;
    }

    public static void setKey(String key) {
        Jedis jedis = createJedis();
        if (jedis == null) {
            LOGGER.info("jedis is null");
            return;
        }
        jedis.set("zhangsan", key);
    }

    public static Object getKey(String key) {
        Jedis jedis = createJedis();
        if (jedis == null) {
            return null;
        }
        return jedis.get(key);
    }
}