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
package com.enation.framework.plugin.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插件页面上下文件<br/> 用于存储插件页面及其插件类型的对应关系。
 * 
 * @author apexking
 * 
 */
public class PluginPageContext {
	private static Map<String, List<String>> plugin_pages;

	static {
		plugin_pages = new HashMap<String, List<String>>();
	}

	/**
	 * 向上下文件中加入一个页面路径。
	 * 
	 * @param type
	 *            插件页面类型
	 * @param page
	 *            插件页面路径
	 */
	public static void addPage(String type, String page) {
		List<String> pagelist = plugin_pages.get(type);
		if (pagelist == null)
			pagelist = new ArrayList<String>();
		pagelist.add(page);
		plugin_pages.put(type, pagelist);
		System.out.println("加载" + type + " 类页面： " + page);
	}

	/**
	 * 获取某插件类型的页面路径集合
	 * 
	 * @param page_type
	 *            插件页面类型
	 * @return
	 */
	public static List<String> getPages(String page_type) {
		return plugin_pages.get(page_type);
	}

}
