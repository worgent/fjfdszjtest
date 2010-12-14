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
package com.qzgf.widget.goodscat;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.qzgf.core.model.Cat;
import com.qzgf.core.service.IGoodsCatManager;

public class GoodsCat extends AbstractWidget {
	 
	private IGoodsCatManager emsgoodsCatManager;
	
	protected void execute(Map<String, String> params) {
		List<Cat> cat_tree =  emsgoodsCatManager.listAllChildren(0);
		String catimage = params.get("catimage");
		boolean showimage  = catimage!= null &&catimage.equals("on") ?true:false;
		//System.out.println(catimage);
		this.putData("showimg", showimage);
		this.putData("cat_tree", cat_tree);
	 
	}
	
	
	protected void config(Map<String, String> params) {
		
	}


	/**
	 * Purpose      : 说明
	 * @return the emsgoodsCatManager
	 */
	public IGoodsCatManager getEmsgoodsCatManager() {
		return emsgoodsCatManager;
	}


	/**
	 * Purpose      : 说明
	 * @param emsgoodsCatManager the emsgoodsCatManager to set
	 */
	
	public void setEmsgoodsCatManager(IGoodsCatManager emsgoodsCatManager) {
		this.emsgoodsCatManager = emsgoodsCatManager;
	}
}
