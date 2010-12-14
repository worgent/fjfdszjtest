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
package com.enation.javashop.widget.article;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.service.IArticleCatManager;

public class ArticleCatWidget extends AbstractWidget {
	
	private IArticleCatManager articleCatManager;

	
	protected void config(Map<String, String> params) {
		

	}

	
	protected void execute(Map<String, String> params) {
		this.setPageName("articlecat");
		String cat_id = params.get("cat_id");
		List articleCatList = articleCatManager.listHelp(Integer.valueOf(cat_id));
		this.putData("articleCatList", articleCatList);
		this.putData("cat_id", cat_id);
	}

	public IArticleCatManager getArticleCatManager() {
		return articleCatManager;
	}

	public void setArticleCatManager(IArticleCatManager articleCatManager) {
		this.articleCatManager = articleCatManager;
	}

}
