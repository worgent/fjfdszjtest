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

import java.util.Map;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.facade.IPageParamJsonGetter;
import com.enation.eop.core.facade.widget.IWidgetParamParser;
import com.enation.eop.core.facade.widget.WidgetXmlUtil;
import com.enation.eop.core.resource.IThemeManager;
import com.enation.eop.core.resource.IThemeUriManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.ThemeUri;
import com.enation.framework.context.spring.SpringContextHolder;

/**
 * 页面挂件json格式参数获取器
 * @author kingapex
 * 2010-2-10下午04:59:57
 */
public class PageParamJsonGetter implements IPageParamJsonGetter {
	private IWidgetParamParser widgetParamParser;
	 
	private IThemeManager themeManager ;
	
	
	
	public String getJson(String uri) {
		//去掉uri问号以后的东西
		if(uri.indexOf('?')>0)
			uri = uri.substring(0, uri.indexOf('?') );
		
		//站点使用模板
		EopSite site = EopContext.getContext().getCurrentSite();
	 
		
		//rewrite url = pageId
		IThemeUriManager themeUriManager =  SpringContextHolder.getBean("themeUriManager");
		ThemeUri themeUri  = themeUriManager.getPath(  uri);
		uri = themeUri.getPath();
		
		//此站点挂件参数集合
		Map<String, Map<String, Map<String, String>>> pages = this.widgetParamParser
				.parse();
		
		//此页面的挂件参数集合
		Map<String, Map<String,String>> params=pages.get(uri);
		String json = WidgetXmlUtil.mapToJson(params);
		json="{'pageId':'"+uri+"',params:"+json+"}";
		return json;
	}
	
	
	public void setWidgetParamParser(IWidgetParamParser widgetParamParser) {
		this.widgetParamParser = widgetParamParser;
	}
 

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

}
