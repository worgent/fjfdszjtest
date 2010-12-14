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
package com.enation.framework.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 自动注册插件桩
 * 
 * @author apexking
 * 
 */
public abstract class AutoRegisterPluginsBundle implements IPluginBundle {
	protected static final Log loger = LogFactory
			.getLog(AutoRegisterPluginsBundle.class);

	protected List<IPlugin> plugins;

	public void registerPlugin(IPlugin plugin) {
		if (plugins == null) {
			plugins = new ArrayList<IPlugin>();
		}

		if (loger.isDebugEnabled()) {
			loger.debug("为插件桩" + getName() + "注册插件：" + plugin.getName()
					+ "，id：" + plugin.getId() + "，版本：" + plugin.getVersion()
					+ "，作者：" + plugin.getAuthor() + "。");
//			System.out.println("为插件桩" + getName() + "注册插件：" + plugin.getName()
//					+ "，id：" + plugin.getId() + "，版本：" + plugin.getVersion()
//					+ "，作者：" + plugin.getAuthor() + "。");
		}
		plugins.add(plugin);
	}

	abstract public String getName();

}
