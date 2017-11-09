package com.frame.test;

import com.frame.cache.redis.JedisUtil;
import org.junit.Ignore;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */

public class RedisTest {
	public static void main(String[] args) throws Exception {
		JedisUtil.set("i", 0);
		Thread t1 = new Thread(new Job());
		Thread t2 = new Thread(new Job());
		t1.start();
		t2.start();

	}

	public static class Job implements Runnable {
		@Override
		public void run() {
			try {
				for (int i = 0; i < 5000; i++) {
					int num = (int) JedisUtil.get("i");
					JedisUtil.set("i", num + 1);
				}
				System.out.println(JedisUtil.get("i"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
