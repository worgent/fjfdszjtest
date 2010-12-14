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
package com.enation.cms.widget;

import java.util.Map;

import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.cms.core.service.IDataManager;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.widget.nav.Nav;

/**
 *  静态数据详细挂件
 * @author kingapex
 * 2010-7-6上午11:33:11
 */
public class StaticDataDetailWidget extends AbstractWidget {

	private IDataManager dataManager;
	private IDataCatManager dataCatManager;
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void execute(Map<String, String> params) {
		String id = params.get("id");
		String catid = params.get("catid");
		Map data  = this.dataManager.get(Integer.valueOf(id), Integer.valueOf(catid),true);
		this.putData("data", data);
		
		
		DataCat cat  =this.dataCatManager.get(Integer.valueOf(catid));
		
		Nav nav = new Nav();
		nav.setTitle("首页");
		nav.setLink("index.html");
		nav.setTips("首页");
		this.putNav(nav);
		
		Nav nav1 = new Nav();
		nav1.setTitle(cat.getName() );
		nav1.setTips(cat.getName());
		this.putNav(nav1);		
		
	}

	public IDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IDataManager dataManager) {
		this.dataManager = dataManager;
	}

	public IDataCatManager getDataCatManager() {
		return dataCatManager;
	}

	public void setDataCatManager(IDataCatManager dataCatManager) {
		this.dataCatManager = dataCatManager;
	}
	

}
