package com.qzgf.NetStore.pub;
import com.qzgf.NetStore.pub.sessionMgr.ServerUserContext;
import com.qzgf.NetStore.pub.sessionMgr.SessionManager;

/**
 * 线程变量管理器,我们的框架中的大部分关键变量,
 *比如当前用户 当前数据库连接等都是与当前线程相关,
 *因此这些变量都要通过此管理器取得
 * @author 林圣如
 *
 */
public class ThreadVariableManager
{

	private static ThreadVariableManager instance;
	@SuppressWarnings("unchecked")
	private static final ThreadLocal sessionId = new ThreadLocal();
    
	private ThreadVariableManager()
	{
		super();
	}

	public static ThreadVariableManager getInstance()
	{
		if (instance == null)
		{
			instance = new ThreadVariableManager();

		}

		return instance;
	}

	
	@SuppressWarnings("unchecked")
	public void setCurrentSessionId(String id)
	{
		sessionId.set(id);
	}
	
	public String getCurrentSessionId()
	{
		return (String) sessionId.get();
	}
	
	public ServerUserContext getCurrentServerUserContext()
	{
		String sessionId = getCurrentSessionId();
		if(sessionId==""||"".equals(sessionId))
		{
			return null;
		} 
		return SessionManager.getInstance().getServerUserContext(sessionId); 
	}

	/**
	 * 清除线程变量,关闭相关资源
	 * @
	 */
	@SuppressWarnings("unchecked")
	public void clear() 
	{
		sessionId.set(null);		
	}

}
