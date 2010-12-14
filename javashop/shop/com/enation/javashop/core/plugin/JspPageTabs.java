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
package com.enation.javashop.core.plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 商品维护时的选项卡上下文
 * @author kingapex
 *
 */
public class JspPageTabs {
	
	private static final Log loger = LogFactory.getLog(JspPageTabs.class);
	
	private static Map<String,Map> tabs;
	static{
		tabs = new HashMap<String, Map>();
		
	}
	
	
	public static void addTab(String plugintype,Integer tabid,String tabname){
		Map<Integer,String> plugin_tab = tabs.get(plugintype)==null?new TreeMap<Integer, String>(): (Map)tabs.get(plugintype) ;
		plugin_tab.put(tabid, tabname);
		
		tabs.put(plugintype,plugin_tab);
		if(loger.isDebugEnabled()){
			loger.debug("添加"+ plugintype  +"选项卡" +  tabid + " tabname is  " + tabname);
		}
	}
	
	public static Map getTabs(String plugintype){
		
		return tabs.get(plugintype);
	}
	
	
	
}
