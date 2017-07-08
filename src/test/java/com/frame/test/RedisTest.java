package com.frame.test;

import com.frame.cache.redis.JedisUtil;
import org.junit.Ignore;

/**
 * Created by Administrator on 2017/6/23.
 */

public class RedisTest {
    public static void main(String[] args) throws Exception {
        JedisUtil.set("name","zhangsan");
        System.out.println(JedisUtil.get("name"));
    }
}
