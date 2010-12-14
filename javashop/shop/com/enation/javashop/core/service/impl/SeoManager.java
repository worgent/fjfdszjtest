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
package com.enation.javashop.core.service.impl;

import com.enation.eop.impl.support.BaseSupport;
import com.enation.javashop.core.model.Seo;
import com.enation.javashop.core.service.ISeoManager;

/**
 * SEO管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-19 上午10:39:52<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class SeoManager extends BaseSupport<Seo> implements ISeoManager {

	
	public Seo getSeo() {
		String sql = "select * from seo where id=1";
		Seo seo = null;
		try {
			seo = baseDaoSupport.queryForObject(sql, Seo.class);
		} catch (Exception e) {
			seo = new Seo();
			seo.setId(1);
			baseDaoSupport.insert("seo", seo);
		}
		return seo;
	}

	
	public void update(Seo seo) {
		this.baseDaoSupport.update("seo", seo, "id=1");
	}

}
