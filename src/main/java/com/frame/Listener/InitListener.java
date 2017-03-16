package com.frame.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.frame.db.FlaywayDBUtil;

/**
 * 
 * @ClassName: InitListener
 * @Description: Listener - 初始化
 * @author: chenqi
 * @date: 2017年3月16日 上午9:17:48
 */

public class InitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.exit(0);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		FlaywayDBUtil.migration();
	}

}
