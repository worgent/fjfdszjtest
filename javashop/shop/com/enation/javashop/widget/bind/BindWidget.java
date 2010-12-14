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
package com.enation.javashop.widget.bind;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.widget.nav.Nav;

/**
 * 捆绑商品
 * 
 * @author lzf<br/>
 *         2010-4-8 上午09:43:49<br/>
 *         version 1.0<br/>
 */
public class BindWidget extends AbstractWidget {
	
	private IGoodsManager goodsManager;

	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("bind");
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		String page  = request.getParameter("page");
		page = (page == null || page.equals("")) ? "1" : page;
		int pageSize = 20;
		Page bindPage = goodsManager.searchBindGoods(null, null, null, Integer.valueOf(page), pageSize);
		Long pageCount = bindPage.getTotalPageCount();
		List bindList = (List)bindPage.getResult();
		bindList = bindList == null ? new ArrayList() : bindList;
		this.putData("pageSize", pageSize);
		this.putData("page", page);
		this.putData("bindList", bindList);
		this.putData("pageCount", pageCount);
		
		Nav nav = new Nav();
		nav.setTitle("捆绑促销");
		this.putNav(nav);
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

}
