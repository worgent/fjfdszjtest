/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.framework.context.webcontext;

import java.util.Set;

import javax.servlet.http.HttpSession;

public interface WebSessionContext<T> {

	public static String sessionAttributeKey = "EOPSessionKey";

	public abstract HttpSession getSession();

	public abstract void setSession(HttpSession session);

	public abstract void invalidateSession();

	public abstract void setAttribute(String name, T value);

	public abstract T getAttribute(String name);

	public abstract Set<T> getAttributeNames();

	public abstract void removeAttribute(String name);
	
	public abstract void destory();

}