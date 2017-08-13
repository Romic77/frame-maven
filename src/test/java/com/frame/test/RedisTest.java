package com.frame.test;

import com.frame.cache.redis.JedisUtil;
import org.junit.Ignore;

/**
 * Created by Administrator on 2017/6/23.
 */

public class RedisTest {
    public static void main(String[] args) throws Exception {
		do1();
    }

    static void do1(){
		System.out.println("sb");
		do1();
	}

}
