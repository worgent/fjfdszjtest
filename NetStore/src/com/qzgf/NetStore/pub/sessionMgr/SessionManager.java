package com.qzgf.NetStore.pub.sessionMgr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.cownew.ctk.common.DateUtils;
import com.qzgf.NetStore.persistence.Administrator;
import com.qzgf.NetStore.pub.RandomGUID;
import com.qzgf.NetStore.pub.ServerConfig;

/**
 * 会话管理器
 * @author 林圣如
 *
 */
public class SessionManager
{
	private static SessionManager instance = null;	
	
	//sessionId为key,ServerUserContext为value
	@SuppressWarnings("unchecked")
	private Map sessionMap = Collections.synchronizedMap(new HashMap());
	
	//sessionId为key,会话自上次活动以来的时间(分钟)为value
	@SuppressWarnings("unchecked")
	private Map sessionActiveMap = Collections.synchronizedMap(new HashMap());

	private SessionManager()
	{
		super();
		
		//要设置成后台线程,否则会造成服务器无法正常关闭
		Timer sessionClearTimer = new Timer(true);
		//一分钟
		int oneMin = DateUtils.ONE_MINUTE; 
		//1分钟以后开始,每隔一分钟探测一次
		sessionClearTimer.schedule(new SessionCleanerTimerTask(),oneMin,oneMin);
		
	}

	public static SessionManager getInstance()
	{
		if (instance == null)
		{
			instance = new SessionManager();
		}
		return instance;
	}

	/**
	 * 根据会话id得到用户上下文
	 * @param sessionId
	 * @return
	 */
	public ServerUserContext getServerUserContext(String sessionId)
	{
		return (ServerUserContext) sessionMap.get(sessionId);
	}	

	/**
	 * 获得管理员信息
	 * @param sessionId
	 * @return
	 */
	public Administrator getAdministrator(String sessionId)
	{
		return ((ServerUserContext) sessionMap.get(sessionId)).getAdmin();
	}
	
	/**
	 * 得到所有会话id的集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set getSessionIdSet()
	{
		return Collections.unmodifiableSet(sessionMap.entrySet());
	}
	
	/**
	 * sessionId是否合法(存在并且没有超时)
	 * @param sessionId
	 * @return
	 */
	public boolean isValid(String sessionId)
	{
		return sessionMap.containsKey(sessionId);
	}

	/**
	 * 清除session
	 * @param object
	 */
	public void removeSession(String sessionId)
	{
		sessionMap.remove(sessionId);
		sessionActiveMap.remove(sessionId);
		
	}

	/**
	 * 清除所有session
	 *
	 */
	public void removeAll()
	{
		sessionMap.clear();

	}

	/**
	 * 请求一个会话Id
	 * @param acName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String requestSessionId()
	{
		String sessionId = new RandomGUID().toString();

		ServerUserContext ctx = new ServerUserContext();
		ctx.setSessionId(sessionId);

		sessionMap.put(sessionId, ctx);
		sessionVisit(sessionId);
		return sessionId;
	}

	/**
	 * session产生活动了
	 * @param sessionId
	 */
	@SuppressWarnings("unchecked")
	public void sessionVisit(String sessionId)
	{
		if (!sessionMap.containsKey(sessionId))
		{
			return;
		}
		sessionActiveMap.put(sessionId, new Integer(0));
	}	
	
	protected class SessionCleanerTimerTask extends TimerTask
	{ 
		private int timeOut = ServerConfig.getInstance().getSessionTimeOut();
        
		@SuppressWarnings("unchecked")
		public void run()
		{
			Set idSet = sessionActiveMap.keySet();
			Iterator idIt = idSet.iterator();
			
			// 已经失效的session的id列表
			List invalidIdList = new ArrayList();
			while (idIt.hasNext())
			{
				String id = (String) idIt.next();

				// 自上次访问以来的时长
				Integer lastSpan = (Integer) sessionActiveMap.get(id);
				if (lastSpan.intValue() > timeOut)
				{
					invalidIdList.add(id);
				}
				sessionActiveMap.put(id, new Integer(lastSpan.intValue() + 1));
			}
			for (int i = 0, n = invalidIdList.size(); i < n; i++)
			{
				String id = (String) invalidIdList.get(i);
				removeSession(id);
				sessionActiveMap.remove(id);
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	public String requestSessionId(ServerUserContext ctx)
	{
		String sessionId = new RandomGUID().toString();
		ctx.setSessionId(sessionId);
		sessionMap.put(sessionId, ctx);
		sessionVisit(sessionId);
		return sessionId;
	}
}
