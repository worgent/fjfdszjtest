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
package com.enation.eop.impl.facade.widget;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.facade.widget.IWidgetPaser;
import com.enation.eop.core.facade.widget.WidgetWrapper;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.utils.FreeMarkerUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 挂件边框包装器
 * 根据参数给挂件加上合适的边框
 * @author kingapex
 * 2010-1-27下午01:35:30
 */
public class BorderWrapper extends WidgetWrapper {

	public BorderWrapper(IWidgetPaser paser) {
		 super(paser);
	}

	
	public String pase(Map<String, String> params) {
		
		String content = super.pase(params);		
		String border = params.get("border");
		String widgetid ="widget_"+ params.get("id");
		if(border ==null ||border.equals("") || border.equals("none")){ 
			
			if("yes".equals(ThreadContextHolder.getHttpRequest().getParameter("ajax"))
			|| "widget_header".equals(widgetid)){
				return content;
			}else
			
			return "<div id=\""+widgetid+"\">"+content+"</div>";
			
		}
		
		EopSite site  =   EopContext.getContext().getCurrentSite();
		String contextPath  = EopContext.getContext().getContextPath();
		String borderPath =contextPath +"/themes/"+site.getThemepath()+"/borders/";
		borderPath= EopSetting.EOP_PATH+borderPath;
		try{
				
			Map<String,String> data = new HashMap<String, String>();
			
			data.put("widgetid", widgetid);
			data.put("body", content);
			data.put("title", params.get("bordertitle"));
			
			Configuration cfg  = FreeMarkerUtil.getFolderCfg(borderPath);
			Template temp = cfg.getTemplate(border+".html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			out.flush();
			return stream.toString();
		}catch(Exception e){
			e.printStackTrace();
			return content;
		}
		
	}

}
