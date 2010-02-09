package net.trust.datacollection.timer;

import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.utils.sms.SMProxySendFacadeImplTimeTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SmsCollectionTimerTask extends TimerTask {
	private Log log = LogFactory.getLog(SmsCollectionTimerTask.class);
	private ServletContext servletContext;
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	private DataSource dataSource;
	
	
	private SMProxySendFacadeImplTimeTask smpsend=new SMProxySendFacadeImplTimeTask();
	
	
	public SmsCollectionTimerTask(ServletContext servletContext){
		this.servletContext = servletContext;
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		dataSource = (DataSource)context.getBean("sqlServerDataSource");	// 获取SqlServer数据连接池
		baseSqlMapDAO = (BaseSqlMapDAO)context.getBean("baseSqlMapDAO");// 获取mysqlserver服务器数据
		if (log.isDebugEnabled())
			log.debug("短信采集器已运行");
	}
	
	//测试
	public SmsCollectionTimerTask(){
	}
	/**
	 * 1：短信接收，mysql数据库增加数据。 2:
	 * 短信转发，sqlserver查询相关数据管理人员，然后发送短信给管理人员。mysql中增加短信信息。
	 */
	@Override
	public void run() {

		String connState = ""; 
		try { 
		do { 
			connState = smpsend.myProxy.getConnState();
		} while (true); 
		} catch (Exception e) { 
		e.printStackTrace(); 
			return; 
		}
		// baseSqlMapDAO.update("SmsManage.insertInceptSms", param);
	}
	
	//测试接收短信
	public static void main(String[] args)
	{
		SmsCollectionTimerTask sm=new SmsCollectionTimerTask() ;
		sm.run();
	}
}
