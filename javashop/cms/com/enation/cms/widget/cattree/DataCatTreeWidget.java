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
package com.enation.cms.widget.cattree;

import java.util.List;
import java.util.Map;

import com.enation.cms.core.service.IDataCatManager;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.util.StringUtil;

/**
 * 数据类别树挂件
 * @author kingapex
 * 2010-7-7下午06:35:12
 */
public class DataCatTreeWidget extends AbstractWidget {

	private IDataCatManager dataCatManager;
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void execute(Map<String, String> params) {
		Integer catid  =0;
		String catidstr = params.get("catid");
		if(StringUtil.isEmpty(catidstr)){
			catid = Integer.valueOf(catidstr);
		}
		List catList  = dataCatManager.listAllChildren(catid);
		
		String url = params.get("url");
		url =StringUtil.isEmpty(url) ?"data" :url;
		this.putData("url", url);
		this.putData("cat_tree", catList);
	}

	public IDataCatManager getDataCatManager() {
		return dataCatManager;
	}

	public void setDataCatManager(IDataCatManager dataCatManager) {
		this.dataCatManager = dataCatManager;
	}

}
