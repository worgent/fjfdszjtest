package com.qzgf.datacollection.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.datacollection.timer.ImmigrationTimerTask;
import com.qzgf.datacollection.timer.Yd12580TimerTask;
/**
 * 数据采集定时监听器,通过监听器来调用(web.xml)
 *
 */
public class DataCollectionTimerListener implements ServletContextListener {
	private Log log = LogFactory.getLog(DataCollectionTimerListener.class);
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		
		if (log.isDebugEnabled())
			log.debug("数据采集定时器已启动");
		
		java.util.Timer timer  = new java.util.Timer(true);

		//2010-06-21 福建省公安出入境(每隔5分钟) 5 * 60 * 1000
		ImmigrationTimerTask immigrationTimerTask = new ImmigrationTimerTask(servletContext);
		timer.schedule(immigrationTimerTask, 30 * 1000, 5 * 60 * 1000);

		
		
		//2010-09-13 中国移动12580速递邮政(每隔5分钟) 5 * 60 * 1000
		Yd12580TimerTask yd12580TimerTask = new Yd12580TimerTask(servletContext);
		timer.schedule(yd12580TimerTask,60 * 1000, 5* 60 * 1000);

		if (log.isDebugEnabled())
			log.debug("数据采集已经添加任务调度表");
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
