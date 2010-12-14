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
package com.enation.cms.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 单行文本框插件
 * @author kingapex
 * 2010-7-5下午02:55:26
 */
public class InputFieldPlugin extends AbstractFieldPlugin {

  
	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "input";
	}

	
	public String getName() {
		
		return "单行文本框";
	}

	
	public String getType() {
		
		return "field";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

 

	
	public String onDisplay(DataField field, Object value) {
		StringBuffer html = new StringBuffer("<input type=\"text\" name=\"");
		html.append(field.getEnglish_name());
		html.append("\"");
		
		if(value!=null){
			html.append(" value=\"");
			html.append(value);
			html.append("\"");
		}

		html.append(this.wrappValidHtml(field));
		
		html.append(" />");
		
		return html.toString();
	}

	
	public int getHaveSelectValue() {
		 
		return 0;
	}



}
