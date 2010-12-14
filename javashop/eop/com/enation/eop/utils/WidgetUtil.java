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
package com.enation.eop.utils;

import com.enation.eop.model.Widget;

public class WidgetUtil {
	
	public static  String toHtml(Widget widget, String content) {

		StringBuffer htmlBuffer = new StringBuffer();

		// 以下是符合《挂件规范.doc》中的挂件html片段
		htmlBuffer.append("<div ");

		htmlBuffer.append("class=\"widget\"");
		htmlBuffer.append(" ");
		

		htmlBuffer.append("eop_type=\"widget\"");
		htmlBuffer.append(" ");
		
		//在2.1版本的架构中去掉了widgetId属性
		/*htmlBuffer.append("id=\"widget");
		htmlBuffer.append(widget.getWidgetId());
		htmlBuffer.append("\"");
		htmlBuffer.append(" ");*/

		htmlBuffer.append("appId=\"");
		htmlBuffer.append(widget.getApp().getId());
		htmlBuffer.append("\"");
		htmlBuffer.append(" ");
		
		htmlBuffer.append("widgetType=\"");
		htmlBuffer.append(widget.getWidgetType());
		htmlBuffer.append("\"");
		htmlBuffer.append(" ");
		
		htmlBuffer.append(">");

		htmlBuffer.append(content); // 填充上挂件的内容

		htmlBuffer.append("</div>");

		return htmlBuffer.toString();

	}
}
