package com.qzgf.NetStore.pub.lifeMgr;

public interface IServiceLifeListener
{
	public void beforeInvoke(String sessionId);

	public void afterInvoke(String sessionId);
}
