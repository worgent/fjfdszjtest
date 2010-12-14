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
package com.enation.javashop.widget.partzone;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.model.Article;
import com.enation.javashop.core.service.IArticleManager;

/**
 * 显示区域挂件
 * 
 * @author lzf<br/>
 *         2010-4-16下午06:21:12<br/>
 *         version 1.0
 */
public class PartZoneWidget extends AbstractWidget {
	
	private IArticleManager articleManager;

	
	protected void config(Map<String, String> params) {
		this.setPageName("partzone_comfig");
		List articleList = articleManager.listByCatId(9999);
		this.putData("articleList", articleList);

	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("partzone");
		String article_id = params.get("article_id");
		article_id = article_id == null ? "0" : article_id;
		Article article = articleManager.get(Integer.valueOf(article_id));
		this.putData("articleContent", article.getContent());

	}

	public IArticleManager getArticleManager() {
		return articleManager;
	}

	public void setArticleManager(IArticleManager articleManager) {
		this.articleManager = articleManager;
	}

}
