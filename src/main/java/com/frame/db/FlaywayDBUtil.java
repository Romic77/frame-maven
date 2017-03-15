package com.frame.db;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

public class FlaywayDBUtil {
	// 读取数据库配置参数
	private static Properties config = new Properties();
	static {
		try {
			config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 执行数据库版本升级
	public static void migration() {
		// Create the Flyway instance
		Flyway flyway = new Flyway();

		// Point it to the database
		flyway.setDataSource(config.getProperty("url"),
				config.getProperty("username"),
				config.getProperty("password"));
		
		
		flyway.setEncoding("UTF-8"); // 设置sql脚本文件的编码  
		
		flyway.setBaselineOnMigrate(true);
		// Start the migration
		flyway.migrate();
	}
	
	public static void main(String[] args) {
		migration();
	}
}
