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
package com.enation.eop.sdk.webapp.taglib.html.support;

import java.util.ArrayList;

import java.util.List;
import javax.servlet.jsp.PageContext;
import com.enation.framework.database.Page;
import com.enation.eop.sdk.webapp.bean.Grid;
import com.enation.eop.sdk.webapp.taglib.IListTaglibParam;
import com.enation.eop.sdk.webapp.taglib.IListTaglibProvider;

public class GridBodyProvider implements IListTaglibProvider {
	public List getData(IListTaglibParam _param, PageContext pageContext) {

		GridBodyParam param = (GridBodyParam) _param;
		String from = param.getFrom();

		Object obj = pageContext.getAttribute(from);
		if (obj == null){
			obj = pageContext.getRequest().getAttribute(from);
			if (obj == null){
				return new ArrayList();
			}
		}
		//	from	即可以是Page对象，也可以是Grid对象。
		Page page = null;
		List list = null;
		if(obj instanceof Page){
			page = (Page)obj;
			list  = (List) page.getResult();
		}
		else if(obj instanceof Grid ){
			page = ((Grid)obj).getWebpage();
			list  = (List) page.getResult();
		}else if(obj instanceof List){
			list = (List)obj;
		}
		
		return list;
	}
}
