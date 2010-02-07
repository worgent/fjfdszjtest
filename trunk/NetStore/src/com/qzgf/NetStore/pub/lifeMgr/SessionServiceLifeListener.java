package com.qzgf.NetStore.pub.lifeMgr;

import com.qzgf.NetStore.pub.ThreadVariableManager;
import com.qzgf.NetStore.pub.sessionMgr.SessionManager;

public class SessionServiceLifeListener implements IServiceLifeListener {
	public void beforeInvoke(String sessionId) {
		// 对于登录服务等系统服务来说是无需而且也无法得到sessionId的,因为当时还没有一个sessionId
		// 所以要进行非空判断
		if (sessionId != null && !"".equals(sessionId)) {
			SessionManager sessionMgr = SessionManager.getInstance();
			boolean valid = sessionMgr.isValid(sessionId);
			// 判断sessionId是否合法
			if (!valid) {
				System.out.println("sessionId不合法");
				//
			}
			sessionMgr.sessionVisit(sessionId);
			ThreadVariableManager.getInstance().setCurrentSessionId(sessionId);
		}
	}

	public void afterInvoke(String sessionId) {
		ThreadVariableManager.getInstance().clear();
	}

}
