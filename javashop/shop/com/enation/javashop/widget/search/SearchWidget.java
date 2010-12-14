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
package com.enation.javashop.widget.search;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.service.IBrandManager;
import com.enation.javashop.core.service.IGoodsCatManager;

/**
 * 高级搜索
 * @author lzf<br/>
 * 2010-4-23上午11:12:59<br/>
 * version 1.0
 */
public class SearchWidget extends AbstractWidget {
	
	private IBrandManager brandManager;
	private IGoodsCatManager goodsCatManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void execute(Map<String, String> params) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		if(action == null || "".equals(action)){
			this.setPageName("search");
			List<Cat> cat_tree =  goodsCatManager.listAllChildren(0);
			this.putData("cat_tree", cat_tree);	
		}
		
		if("brand".equals(action)){
			this.brandList();
		}

	}
	
	private void brandList(){
		
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		Integer catid = Integer.valueOf(request.getParameter("cat_id"));
		List brandList  = this.brandManager.listByCatId(catid);
		this.putData("brandList", brandList);
		this.setPageName("brandList");
	}
	

	public IBrandManager getBrandManager() {
		return brandManager;
	}

	public void setBrandManager(IBrandManager brandManager) {
		this.brandManager = brandManager;
	}

	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

}
