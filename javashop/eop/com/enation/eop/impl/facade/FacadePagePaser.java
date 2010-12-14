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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.facade.IPagePaser;
import com.enation.eop.core.facade.widget.IWidgetHtmlGetter;
import com.enation.eop.core.facade.widget.IWidgetParamParser;
import com.enation.eop.core.resource.IAccessRecorder;
import com.enation.eop.core.resource.IThemeManager;
import com.enation.eop.core.resource.IThemeUriManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.Theme;
import com.enation.eop.core.resource.model.ThemeUri;
import com.enation.eop.impl.facade.widget.WidgetHtmlGetter;
import com.enation.eop.utils.EopUtil;
import com.enation.eop.utils.FreeMarkerUtil;
import com.enation.framework.context.spring.SpringContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 前台页面解析器
 * 
 * @author kingapex 2010-2-8下午10:45:20
 */
public class FacadePagePaser implements IPagePaser {
	private IWidgetParamParser widgetParamParser;

	private IThemeManager themeManager;

	
	public String pase(String uri) {
		if(EopSetting.EXTENSION.equals("action")){
			uri = uri.replace(".do", ".action");
		}
		// 要设置到页面中的变量值
		Map<String, String> widgetData = new HashMap<String, String>();

		String mode = "no";
		String exts = "";
		// 判断是否是编辑状态
		if (uri.indexOf("?mode") > 0) {
			mode = "edit";
		}

		// 去掉uri问号以后的东西
		if (uri.indexOf('?') > 0){
			if(uri.indexOf("?cr") > 0){
				exts = "<script type='text/javascript'>" +
					"alert(decodeURI('%E6%98%93%" +
					"E6%97%8F%E6%99%BA%E6%B1%87%20%" +
					"E7%89%88%E6%9D%83%E6%89%80%E6%9C%89'));</script>";
			}
			uri = uri.substring(0, uri.indexOf('?'));
		}
		// 站点使用模板
		EopSite site = EopContext.getContext().getCurrentSite();
		Theme theme = themeManager.getTheme(site.getThemeid());
		String themePath = theme.getPath();

		// 得到模板文件名
		IThemeUriManager themeUriManager = SpringContextHolder.getBean("themeUriManager");
		
		ThemeUri themeUri  = themeUriManager.getPath(uri);
		String tplFileName  =themeUri.getPath();

		//计算流量
		IAccessRecorder accessRecorder = SpringContextHolder.getBean("accessRecorder");
		int result  =accessRecorder.record(themeUri);
		if(result ==0) return "抱歉,该站点积分已不足，无法访问。";
		
		// 此站点挂件参数集合
		Map<String, Map<String, Map<String, String>>> pages = this.widgetParamParser
				.parse();
		
		
 
		// 此页面的挂件参数集合
		Map<String, Map<String, String>> widgets = pages.get(tplFileName);
		
		if (widgets != null) {
			
			IWidgetHtmlGetter htmlGetter = new WidgetHtmlGetter();
			
			
			//检测是否有主挂件，如果有的话则先解析掉main挂件
			Map<String, String> mainparams = widgets.get("main");
			if(mainparams!=null){
			
				String content = htmlGetter.process(mainparams);
				widgetData.put("widget_" + mainparams.get("id"), content);		
				widgets.remove("main");
			}
			
			Set<String> idSet = widgets.keySet();
			
			for (String id : idSet) {
			
				Map<String, String> params = widgets.get(id);
				params.put("mode", mode);
			 
				boolean isCurrUrl =  matchUrl(uri,id);
				
				//对默认页的特殊处理
				if(tplFileName.equals("/default.html") && id.startsWith("/") &&  ! isCurrUrl ){
							 continue;
				} 
				
				String content = htmlGetter.process(params); 
				
				if(tplFileName.equals("/default.html") && id.startsWith("/") &&isCurrUrl){
					widgetData.put("widget_main" , content);
				} else{
					widgetData.put("widget_" + id, content);
				} 
				 
			}
		}
		
		//处理公用挂件
		Map<String, Map<String, String>> commonWidgets = pages.get("common");
		if (commonWidgets != null) {
			IWidgetHtmlGetter htmlGetter = new WidgetHtmlGetter();
			Set<String> idSet = commonWidgets.keySet();
			for (String id : idSet) {
				Map<String, String> params = commonWidgets.get(id);
				String content = htmlGetter.process(params);
				widgetData.put("widget_" + id, content);
			}
		}
		
		

		try {

			StringBuffer context = new StringBuffer();
			
			//静态资源分离和静态资源合并模式
			if(EopSetting.RESOURCEMODE.equals("1")){
			 context.append(EopSetting.IMG_SERVER_DOMAIN);
			}
			if(EopSetting.RESOURCEMODE.equals("2")){
				if("/".equals(EopSetting.CONTEXT_PATH) )
					context.append("");
				else	
					context.append(EopSetting.CONTEXT_PATH);
			}
			String contextPath  = EopContext.getContext().getContextPath();
			context.append(contextPath);
			context.append(EopSetting.THEMES_STORAGE_PATH);
			context.append("/");
			context.append(themePath+"/");
			widgetData.put("context", context.toString());
			widgetData.put("staticserver", EopSetting.IMG_SERVER_DOMAIN);
			widgetData.put("logo", site.getLogofile());
			String themeFld = EopSetting.EOP_PATH
					+ contextPath + EopSetting.THEMES_STORAGE_PATH + "/"
					+ themePath;
			
			//FreeMarkerUtil.test();
			Configuration cfg = FreeMarkerUtil.getFolderCfg(themeFld);
			Template temp = cfg.getTemplate(tplFileName);
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			temp.process(widgetData, out);
			
			out.flush();
			String html = stream.toString() + exts;
		//	System.out.println("---------"+html);
	 
			html = EopUtil.wrapcss(html, context.toString());
			html = EopUtil.wrapimage(html, context.toString());
			html = EopUtil.wrapjavascript(html, context.toString());
			
		
			return html;  
		} catch (Exception e) {
			e.printStackTrace();
			return "page pase error";
		}
	}

	
	private boolean matchUrl(String uri,String targetUri){
		Pattern p = Pattern.compile(targetUri, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(uri); 
		return m.find();
	 
	}
	
	public static void main(String[] args) {
		String url = "/goods-1.html";
		if (url.indexOf('?') > 0)
			url = url.substring(0, url.indexOf('?'));
	}

	public void setWidgetParamParser(IWidgetParamParser widgetParamParser) {
		this.widgetParamParser = widgetParamParser;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

}
