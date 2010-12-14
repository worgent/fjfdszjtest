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
package com.enation.javashop.widget.brand;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.service.IBrandManager;
import com.enation.javashop.widget.nav.Nav;

/**
 * 品牌
 * 
 * @author lzf<br/>
 *         2010-4-9下午05:29:09<br/>
 *         version 1.0
 */
public class BrandWidget extends AbstractWidget {
	
	private IBrandManager brandManager;

	
	protected void config(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("brand");
		List listBrand = brandManager.list();
		this.putData("listBrand", listBrand);
		Nav nav = new Nav();
		nav.setTitle("品牌专区");
		this.putNav(nav);
	}

	public IBrandManager getBrandManager() {
		return brandManager;
	}

	public void setBrandManager(IBrandManager brandManager) {
		this.brandManager = brandManager;
	}

}
