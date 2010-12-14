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
import com.enation.framework.util.StringUtil;

/**
 * 复选框字段插件
 * 
 * @author kingapex 2010-7-6下午05:23:47
 */
public class CheckboxFieldPlugin extends AbstractFieldPlugin {

	
	public int getHaveSelectValue() {

		return 1;
	}

	
	public String onDisplay(DataField field, Object value) {
		StringBuffer html = new StringBuffer();

		String[] haveValue = null;

		if (value != null)
			haveValue = value.toString().split(",");

		String values = field.getSave_value();
		int i = 0;
		if (values != null) {
			String[] valueAr = values.split(",");
			for (String v : valueAr) {
				html.append("<input type=\"checkbox\"");
				html.append(" name=\"");
				html.append(field.getEnglish_name());
				html.append("\" value=\"");
				html.append(i);
				html.append("\"");

				if (haveValue != null) {
					if (checkValue(haveValue, ""+i)) {
						html.append(" checked=\"true\"");
					}
				}
				html.append(" />");
				html.append(v);
				i++;
			}

		}

		return html.toString();
	}

	
	public void onSave(Map article, DataField field) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String[] values = request.getParameterValues(field.getEnglish_name());
		article.put(field.getEnglish_name(), StringUtil.arrayToString(values,
				","));
	}

	
	public Object onShow(DataField field, Object value) {
		String valueStr = field.getSave_value();
		if (value != null && valueStr != null) {
			StringBuffer result = new StringBuffer();
			String[] haveValues = value.toString().split(",");
			int i = 0;
			for (String v : haveValues) {
				if (i != 0)
					result.append("、");

				String[] values = valueStr.split(",");
				result.append(values[Integer.valueOf(v)]);
				i++;
			}

			return result.toString();
		} else
			return "";
	}

	private boolean checkValue(String[] haveValue, String value) {
		if (haveValue == null)
			return false;
		for (String v : haveValue) {
			if (value.equals(v))
				return true;
		}
		return false;
	}

	
	public String getAuthor() {

		return "kingapex";
	}

	
	public String getId() {

		return "checkbox";
	}

	
	public String getName() {

		return "复选框";
	}

	
	public String getType() {

		return "field";
	}

	
	public String getVersion() {

		return "1.0";
	}

}
