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

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;

/**
 * 富文本编辑器字段插件
 * @author kingapex
 * 2010-7-7上午10:28:06
 */
public class RichEditFieldPlugin extends AbstractFieldPlugin {

	
	public int getHaveSelectValue() {
		
		return 0;
	}

	
	public String onDisplay(DataField field, Object value) {
		StringBuffer html = new StringBuffer();
		html.append("<textarea id=\""+field.getEnglish_name()+"\" name=\""+field.getEnglish_name()+"\">");
		if(value!=null){
			html.append(value);
		}
		html.append("</textarea>");
		html.append("<script type=\"text/javascript\">");
		html.append("$('#"+field.getEnglish_name()+"' ).ckeditor( );");
		html.append("</script>");
		
		return html.toString();
	}

	
	public int getDataType() {
		 
		return 2;
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "richedit";
	}

	
	public String getName() {
		
		return "富文本编辑器";
	}

	
	public String getType() {
		
		return "field";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

}
