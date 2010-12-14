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
package com.enation.eop.impl.facade.widget.config;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.core.facade.widget.config.IWidgetCfgHtmlParser;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.eop.utils.FreeMarkerUtil;
import com.enation.framework.context.spring.SpringContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 本地挂件配置html解析器
 * @see com.enation.eop.sdk.widget.IWidget
 * @author kingapex
 * 2010-2-15下午01:48:18
 */
public class LocalWidgetCfgHtmlPaser implements IWidgetCfgHtmlParser{
 
	public String pase(Map<String,String> widgetParams){
		
		String type = widgetParams.get("type");
	
		//挂件类
		IWidget widget =SpringContextHolder.getBean(type);
		if(widget==null) throw new RuntimeException("widget["+type+"]not found");
 		//解析出挂件config的html
 		String content = widget.setting(widgetParams);
 		widgetParams.put("content", content);

		try {
			String fld = EopSetting.EOP_PATH + "/eop/";
			Configuration cfg = FreeMarkerUtil.getFolderCfg(fld);
			Template temp = cfg.getTemplate("widget_setting.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(widgetParams, out);
			out.flush();
			content = stream.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
 
 
	
	
}
