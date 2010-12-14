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

import java.util.List;
import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.facade.IPageUpdater;
import com.enation.eop.core.facade.widget.IWidgetParamUpdater;
import com.enation.eop.core.facade.widget.WidgetXmlUtil;
import com.enation.eop.core.resource.IThemeManager;
import com.enation.eop.core.resource.IThemeUriManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.ThemeUri;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.util.FileUtil;

/**
 * 前台页面更新器
 * @author kingapex
 * 2010-2-10下午03:54:41
 */
public class FacadePageUpdater implements IPageUpdater {
	private IThemeManager themeManager ;
	private IWidgetParamUpdater widgetParamUpdater;
	
	
	public void update(String uri,String pageContent,String paramJson) {
		//去掉uri问号以后的东西
		if(uri.indexOf('?')>0)
			uri = uri.substring(0, uri.indexOf('?') );

		//站点使用模板
		EopSite site = EopContext.getContext().getCurrentSite();

		//rewrite url，即pageId
		IThemeUriManager themeUriManager =  SpringContextHolder.getBean("themeUriManager");
		ThemeUri themuri  =themeUriManager.getPath(uri);
		uri = themuri.getPath();
		
		//将json字串转为map
		List<Map<String,String>> mapList= WidgetXmlUtil.jsonToMapList(paramJson);	
		
		//调用接口更新挂件参数
		widgetParamUpdater.update(uri, mapList);
		
		//前当使用主题路径
		String themePath = themeManager.getTheme(site.getThemeid()).getPath();
		String contextPath  = EopContext.getContext().getContextPath();
		String pagePath = 
			 EopSetting.EOP_PATH
			+contextPath
			+"/"+EopSetting.THEMES_STORAGE_PATH
			+"/"+themePath
			+"/"+uri;
//		 
//		String oldContent  = FileUtil.read(pagePath, "UTF-8");
//		Pattern p = Pattern.compile("(.*)<div([^<|^>]*?)id=\"AllWrap\"([^<|^>]*?)>(.*)</div>(.*)", 2 | Pattern.DOTALL);
//		Matcher m = p.matcher(oldContent);  
//		if(m.find()){
//			pageContent=StringUtil.filterDollarStr(pageContent);
//			String s = m.replaceAll("$1<body>"+pageContent+"</body>$3");		
//			//更新模板文件内容
//			FileUtil.write(pagePath, s);		
//		}		
	
		pageContent ="<#include 'common/header.html' />"+pageContent+"<#include 'common/footer.html' />";
		FileUtil.write(pagePath, pageContent);
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

	public void setWidgetParamUpdater(IWidgetParamUpdater widgetParamUpdater) {
		this.widgetParamUpdater = widgetParamUpdater;
	}
	
	

}
