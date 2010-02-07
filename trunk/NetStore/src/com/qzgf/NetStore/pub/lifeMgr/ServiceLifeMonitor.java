package com.qzgf.NetStore.pub.lifeMgr;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务生命周期监听器
 * @author lsr
 *
 */
public class ServiceLifeMonitor {
	@SuppressWarnings("unchecked")
	private List listenerList;

	private String sessionId;

	@SuppressWarnings("unchecked")
	public ServiceLifeMonitor(String sessionId) {
		listenerList = new ArrayList();
		this.sessionId = sessionId;
	}
 
	@SuppressWarnings("unchecked")
	public void addServiceLifeListener(IServiceLifeListener listener) {
		listenerList.add(listener);
	}

	public void removeListener(IServiceLifeListener listener) {
		listenerList.remove(listener);
	}

	public void removeAllListener() {
		listenerList.clear();
	}

	public void beforeInvoke() {
		for (int i = 0, n = listenerList.size(); i < n; i++) {
			IServiceLifeListener listener = (IServiceLifeListener) listenerList
					.get(i);
			listener.beforeInvoke(sessionId);
		}
	}

	public void afterInvoke() {
		for (int i = 0, n = listenerList.size(); i < n; i++) {
			IServiceLifeListener listener = (IServiceLifeListener) listenerList
					.get(i);
			listener.afterInvoke(sessionId);
		}
		removeAllListener();
	}

}
