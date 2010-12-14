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
package com.enation.eop.impl.facade;

import java.util.Map;

import com.enation.eop.core.Response;
import com.enation.eop.core.facade.widget.IWidgetPaser;
import com.enation.eop.core.facade.widget.WidgetWrapper;

/**
 * 挂件编辑模式包装器
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 16:36:13
 */
public class WidgetEditModeWrapper extends WidgetWrapper {

	/**
	 * 
	 * @param widget
	 * @param request
	 */
	public WidgetEditModeWrapper(IWidgetPaser paser) {
		super(paser);
	}

	
	
	
	public String pase(Map<String, String> params) {
		 String content= super.pase(params);
		return wrap(content,params);
	}



	
	private String wrap(String content,Map<String,String> params){
		
		content = "<div  class=\"handle\" ><span><a href=\"javascript:;\"  class=\"edit\">设置</a></span><span><a href=\"javascript:;\" class=\"delete\">删除</a></span>"
			+  "<span class=\"adjust\"><input type=\"checkbox\"  />微调</span><span class=\"lockwidth\"><input type=\"checkbox\"  />锁定宽</span></div><div class=\"wrapHelper\"></div>"+content;
		content="<div class=\"widget\" eop_type=\"widget\" id=\""+ params.get("id")+"\">"+content+"</div>";
		return content;
	}

}