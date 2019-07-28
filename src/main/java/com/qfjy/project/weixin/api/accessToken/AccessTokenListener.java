package com.qfjy.project.weixin.api.accessToken;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessTokenListener implements ServletContextListener {
	
	public static final Logger LOG = LoggerFactory.getLogger(AccessTokenListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//开启线程
				new AccessTokenThread().start();
				LOG.info("access_toke线程开启了");
			//	System.out.println("access_toke线程开启了");
		
	}


}
