package net.trust.datacollection.timer;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 预警生成器
 *
 */
public class ExecCreateWarningLogProcTimerTask extends TimerTask {
	private Log log = LogFactory.getLog(ExecCreateWarningLogProcTimerTask.class);
	private ServletContext servletContext;
	private BaseSqlMapDAO baseSqlMapDAO;
	
	private static boolean isRunning = false;		//是否运行
	private static boolean flag = true;				
	private int hour = 21;	//定时在每天晚上9点开始


	public ExecCreateWarningLogProcTimerTask(ServletContext servletContext){
		this.servletContext = servletContext;
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		baseSqlMapDAO = (BaseSqlMapDAO)context.getBean("baseSqlMapDAO");
		
		try {
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream("/net/trust/datacollection/timer/ExecCreateWarningLogProcTimerTask.properties"));//加载Properties文件流
			hour = Integer.valueOf(properties.get("Timer.hour").toString());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		if (log.isDebugEnabled())
			log.debug("预警生成器已运行");
	}
	
	@Override
	public void run() {
		Calendar cal = Calendar.getInstance();
		if (!isRunning) {
			if (hour == cal.get(Calendar.HOUR_OF_DAY) && flag) {
				isRunning = true;
				
				try{
					baseSqlMapDAO.update("WarningLogManage.execCreateWarningLogProc", null);
				}catch (Exception e){
					e.printStackTrace();
				}
				isRunning = false;
				flag = false;
			}
		}else {
			
		}
		if (hour != cal.get(Calendar.HOUR_OF_DAY)) {
			flag = true;
		}
	}
}
