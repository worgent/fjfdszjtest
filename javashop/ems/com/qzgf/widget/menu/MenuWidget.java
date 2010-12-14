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
package com.qzgf.widget.menu;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.util.StringUtil;
import com.qzgf.core.service.ISiteMenuManager;

/**
 * 菜单挂件
 * @author kingapex
 * 2010-1-30下午07:03:14
 */
public class MenuWidget  extends AbstractWidget{
	private ISiteMenuManager emssiteMenuManager;
	
	protected void execute(Map<String, String> params) {
		List menuList  =emssiteMenuManager.list(0);
		this.putData("menuList", menuList);
		
		String isDropDown =  params.get("isDropDown");
		isDropDown =StringUtil.isEmpty(isDropDown)?"off":"on";
		this.putData("isDropDown", isDropDown);
	}
	
	protected void config(Map<String, String> params) {

		 this.setPageName("menu_config");	
	}

	/**
	 * Purpose      : 说明
	 * @return the emssiteMenuManager
	 */
	public ISiteMenuManager getEmssiteMenuManager() {
		return emssiteMenuManager;
	}

	/**
	 * Purpose      : 说明
	 * @param emssiteMenuManager the emssiteMenuManager to set
	 */
	
	public void setEmssiteMenuManager(ISiteMenuManager emssiteMenuManager) {
		this.emssiteMenuManager = emssiteMenuManager;
	}

}
