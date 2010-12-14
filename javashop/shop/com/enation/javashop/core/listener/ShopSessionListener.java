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
package com.enation.javashop.core.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.ICartManager;

public class ShopSessionListener implements HttpSessionListener {

	
	public void sessionCreated(HttpSessionEvent se) {
		
	}

	
	public void sessionDestroyed(HttpSessionEvent se) {
		String sessionid = se.getSession().getId();
		EopSite site  =(EopSite)ThreadContextHolder.getSessionContext().getAttribute("site_key");

		if(site!=null){
		ICartManager cartManager = SpringContextHolder.getBean("cartManager");
		cartManager.clean(sessionid,site.getUserid(),site.getId());
		//System.out.println("cart clean...");
		}
	}

}
