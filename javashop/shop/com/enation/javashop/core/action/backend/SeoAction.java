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
package com.enation.javashop.core.action.backend;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Seo;
import com.enation.javashop.core.service.ISeoManager;

/**
 * SEO
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-19 上午10:41:04<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class SeoAction extends WWAction {
	
	private ISeoManager seoManager;
	private Seo seo;

	
	public String execute() throws Exception {
		seo = seoManager.getSeo();
		return this.SUCCESS;
	}
	
	public String save() throws Exception {
		seoManager.update(seo);
		this.msgs.add("SEO信息修改成功");
		this.urls.put("SEO信息", "seo.do");
		return this.MESSAGE;
	}

	public ISeoManager getSeoManager() {
		return seoManager;
	}

	public void setSeoManager(ISeoManager seoManager) {
		this.seoManager = seoManager;
	}

	public Seo getSeo() {
		return seo;
	}

	public void setSeo(Seo seo) {
		this.seo = seo;
	}

	
}
