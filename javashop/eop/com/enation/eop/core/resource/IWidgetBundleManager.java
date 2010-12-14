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
package com.enation.eop.core.resource;

import java.util.List;

import com.enation.eop.core.resource.model.WidgetBundle;

/**
 * 挂件管理
 * @author lzf
 *         <p>
 *         2009-12-16 上午11:33:25
 *         </p>
 * @version 1.0
 */
public interface IWidgetBundleManager {

	
	public void add(WidgetBundle widgetBundle);
	public List<WidgetBundle> getWidgetbundleList();
	public WidgetBundle getWidgetBundle(String widgetType);
}
