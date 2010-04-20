/**
 *
 */
package com.qzgf.utils;
  
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@SuppressWarnings("serial")
public class SysListener extends HttpServlet implements ServletContextListener {

	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(SysListener.class);

	

	public void contextDestroyed(ServletContextEvent sce) {
	}

	
	public void contextInitialized(ServletContextEvent sce) {
		Properties prop = System.getProperties();
		prop.put("file.encoding", "utf-8");

	}

}
