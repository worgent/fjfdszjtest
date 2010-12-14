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

import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.facade.widget.IWidgetParamParser;
import com.enation.eop.core.facade.widget.WidgetXmlUtil;
import com.enation.eop.core.resource.IThemeManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.Theme;

/**
 *  SAAS式的xml挂件参数解析器
 * @author kingapex
 * 2010-2-4下午04:05:18
 */
public class XmlWidgetParamParser implements IWidgetParamParser {

	 
	private IThemeManager themeManager;
	
	public Map<String, Map<String, Map<String,String>>> parse() {
		EopSite  site  =EopContext.getContext().getCurrentSite();
		Theme theme = themeManager.getTheme(site.getThemeid());
		String contextPath  = EopContext.getContext().getContextPath();
		String path =
		EopSetting.EOP_PATH	
		+contextPath
		+ EopSetting.THEMES_STORAGE_PATH+
		"/" + theme.getPath()  + "/widgets.xml"; 
		
		
		path="file:///"+path;
		
		
		return WidgetXmlUtil.parse(path);
	}
	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}
	
	
}
