package com.qzgf.webutils.listener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.systemManage.manager.dto.UserInfo;

/**
 * Session监听器
 * @author lsr
 * @date 20081102
 * @说明: 监听用户会话情况：当用户登录时把用户的状态保存入库，
 *		  当用户退出或页面非法关闭、超时时将用户状态保存入库
 *	
 *		捕获Session事件的意义：
 *			1、记录网站的客户登录日志（登录，退出信息等）   
 *			2、统计在线人数
 *			3、等等还有很多
 *
 */
public class SessionListener implements HttpSessionAttributeListener  {
	Log log = LogFactory.getLog(SessionListener.class);
	private BaseSqlMapDAO baseSqlMapDAO = null;
	
	public SessionListener(){
//		if (log.isDebugEnabled())
//			log.debug("Start Up Session Listener");
	}
	//创建会话时
	public void attributeAdded(HttpSessionBindingEvent arg0) {
//		if (log.isDebugEnabled())
//			log.debug("Creating Session Listener");
	}
	//删除会话或超时
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
//		if (log.isDebugEnabled())
//			log.debug("Clean Out Session Listener");
		//判断清除的是否是为com.qzgf.application.systemManage.manager.dto.UserInfo对象
		if (arg0.getValue() != null){
			if (arg0.getValue().getClass().getName().equals("com.qzgf.application.systemManage.manager.dto.UserInfo")){
				UserInfo userInfo = (UserInfo)arg0.getValue();//获得Session中的UserInfo对象
				
				ServletContext servletContext = arg0.getSession().getServletContext();//获取spring运用上下文
				ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext); 
				
				baseSqlMapDAO = (BaseSqlMapDAO)context.getBean("baseSqlMapDAO");
				if (baseSqlMapDAO != null){
					UserInfo temp = new UserInfo();
					temp.setIsOnline("0");
					temp.setStaffId(userInfo.getStaffId());
					
					baseSqlMapDAO.update("ManagerInfo.updateManagerInfo", temp);//更新用户在线状态
					
				}
			}
		}
	}
	//重设会话值
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
//		if (log.isDebugEnabled())
//			log.debug("Reseting Session Listener");
	}

}
