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
 * 下拉框字段插件
 * @author kingapex
 * 2010-7-6下午05:20:59
 */
public class SelectFieldPlugin extends AbstractFieldPlugin {

	
	public int getHaveSelectValue() {
	
		return 1;
	}

 

	
	public String onDisplay(DataField field, Object value) {
		StringBuffer html = new StringBuffer();
		html.append("<select name=\"");
		
		html.append(field.getEnglish_name());
		html.append("\"");
		html.append(this.wrappValidHtml(field)) ;
		html.append(">");
		
		html.append("<option value=\"0\">全部</option>");
		String values = field.getSave_value();
		int i=0;
		if(values!=null){
			String[] valueAr = values.split(",");
			for(String v :valueAr){
				html.append("<option ");
				html.append(" value=\"");
				html.append(i);
				html.append("\"");
				
				if(value!=null && i==Integer.valueOf(""+value)){
					html.append(" selected=\"true\"");
				}
		 
				html.append(" >");	
				html.append(v);
				html.append("</option>");
				i++;
			}
			
		}
		
		html.append("</select>");
		
		return html.toString();
	}


	
	public Object onShow(DataField field, Object value) {
		if(value!=null)
		{
			int index =  Integer.valueOf(value.toString());
			String valueStr = field.getSave_value();
			if(valueStr!=null){
				String[] values  = valueStr.split(",");
				return values[index];
			}
			return "";
		}
		else return "";
	}	
	
	
	
	public String getAuthor() {
	
		return "kingapex";
	}

	
	public String getId() {
	
		return "select";
	}

	
	public String getName() {
	
		return "下拉框";
	}

	
	public String getType() {
	
		return "field";
	}

	
	public String getVersion() {
	
		return "1.0";
	}

}