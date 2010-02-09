package net.trust.webutils.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 上下文件监听器
 * 说明：
 * 		当容器启动时加载程序的时候自动初始化一些操作
 * 		相应的当容器关闭之前先执行一些操作
 *
 */
public class ContextListener implements ServletContextListener{
	private Log log = LogFactory.getLog(ContextListener.class);
	private BaseSqlMapDAO baseSqlMapDAO = null;
	public void contextInitialized(ServletContextEvent arg0) {
		if (log.isDebugEnabled()){
			log.debug("++++++++++++contextInitialized++++++++++++++");
		}
	}
	/**
	 * 当容器被关闭前将所有在线人数的状态设为不在线
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		if (log.isDebugEnabled()){
			log.debug("++++++++++++contextDestroyed++++++++++++++");
		}
		
		ServletContext servletContext = arg0.getServletContext();//获取spring运用上下文
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		baseSqlMapDAO = (BaseSqlMapDAO)context.getBean("baseSqlMapDAO");
		int i = baseSqlMapDAO.update("ManagerInfo.updateManagerIsOnline", null);
		if (i>0){
			if (log.isDebugEnabled())
				log.debug("清除所有在线人数，操作成功");
		}
	}

}
