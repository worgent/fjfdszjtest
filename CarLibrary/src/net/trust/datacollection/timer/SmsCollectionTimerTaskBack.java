package net.trust.datacollection.timer;

import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SmsCollectionTimerTaskBack extends TimerTask {
	private Log log = LogFactory.getLog(SmsCollectionTimerTaskBack.class);
	private ServletContext servletContext;
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	
	public SmsCollectionTimerTaskBack(ServletContext servletContext){
		this.servletContext = servletContext;
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		baseSqlMapDAO = (BaseSqlMapDAO)context.getBean("baseSqlMapDAO");
		
		if (log.isDebugEnabled())
			log.debug("短信采集器已运行");
	}
	
	@Override
	public void run() {
		/*
		 * 短信猫返回的数据格式
		 * [OK,3]
		 * [059187986616,2008-07-20 17:04:00,测试1]
		 * [059187986616,2008-07-20 17:04:27,测试2]
		 * [059187986616,2008-07-20 17:05:00,测试3]
		 */
		//List smsList = GSMSms.getGSMSms();	//获取短信列表
//		List smsList = new java.util.ArrayList();
//		smsList.add("OK,3");
//		smsList.add("059187986616,2008-07-20 17:04:00,测试1");
//		smsList.add("059187986616,2008-07-20 17:04:27,测试2");
//		smsList.add("059187986616,2008-07-20 17:05:00,测试3");
		
		//判断短信列表中的长度是否大于1，因为列表中第一条为确认信息
//		if (smsList.size() > 1){
//			HashMap param = null;
//			String tmp = "";
//			String sms[];
//			//从列表中第二条开始取
//			for (int i=1; i < smsList.size(); i++){
//				sms = ((String)smsList.get(i)).split(",");	//截取成数组
//				
//				if (sms[0].length() == 13){
//					tmp = sms[0].substring(0, 2);
//					if ("86".equals(tmp)){
//						sms[0] = sms[0].substring(2, sms[0].length());
//					}
//				}
//
//				param = new HashMap();
//				param.put("phoneCode", sms[0]);
//				param.put("inceptDate", sms[1]);
//				param.put("smsContent", sms[2]);
//				baseSqlMapDAO.update("SmsManage.insertInceptSms", param);
//			}
//		}
	}
}
