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

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.facade.IPagePaser;
import com.enation.eop.core.facade.PageWrapper;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.utils.FreeMarkerUtil;
import com.enation.eop.utils.HtmlUtil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 前台页面编辑模式包装器
 * @author kingapex
 * 2010-2-9上午01:27:30
 */
public class PageEditModeWrapper extends PageWrapper {

	public PageEditModeWrapper(IPagePaser paser) {
		super(paser);
	}

	
	public String pase(String url) {
		String content  = super.pase(url);
		String script= this.getToolBarScript();
		String html = this.getToolBarHtml();
		
		content =wrapPageMain(content);
		content =HtmlUtil.appendTo(content, "head", script);
		content =HtmlUtil.insertTo(content, "body", html);
		//System.out.println(content);
		return content;
	}
	
	private String getToolBarScript(){
		String eopFld= EopSetting.EOP_PATH+"/eop/";
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("staticserver", EopSetting.IMG_SERVER_DOMAIN) ;	
			data.put("ctx", EopSetting.CONTEXT_PATH) ;
			EopSite site = EopContext.getContext().getCurrentSite();
			data.put("userid",""+site.getUserid() ) ;
			data.put("siteid",""+site.getId() ) ;
			
			Configuration cfg = FreeMarkerUtil.getFolderCfg(eopFld);
			Template temp = cfg.getTemplate("widget_tool_script.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			out.flush();
			return stream.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	private String getToolBarHtml(){
		//widget_toolbar.html
		String eopFld= EopSetting.EOP_PATH+"/eop/";
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("staticserver", EopSetting.IMG_SERVER_DOMAIN) ;	
			data.put("ctx", EopSetting.CONTEXT_PATH) ;
			EopSite site = EopContext.getContext().getCurrentSite();
			data.put("userid",""+site.getUserid() ) ;
			data.put("siteid",""+site.getId() ) ;
			Configuration cfg = FreeMarkerUtil.getFolderCfg(eopFld);
			Template temp = cfg.getTemplate("widget_toolbar.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			out.flush();
			return stream.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private String wrapPageMain(String content){
		content = HtmlUtil.insertTo(content, "body", "<div id=\"pagemain\">");
		content =HtmlUtil.appendTo(content, "body", "</div>");
		return content;
	}

}
