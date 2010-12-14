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

import com.enation.eop.core.facade.widget.IWidgetHtmlGetter;
import com.enation.eop.core.facade.widget.IWidgetPaser;
import com.enation.eop.impl.facade.LocalWidgetPaser;
import com.enation.eop.impl.facade.WidgetEditModeWrapper;
import com.enation.eop.sdk.user.UserServiceFactory;

/**
 * 挂件Html获取器</br>
 * 通过 IWidgetPaser接口 解析挂件html</br>
 * </br>
 * 分为两种模式：浏览模式和编辑模式</br>
 * 浏览模式
 * @see #IWidgetPaser
 * 
 * @author kingapex
 * 2010-2-8下午10:17:58
 */
public class WidgetHtmlGetter implements IWidgetHtmlGetter {

	
	public String process(Map<String, String> params) {
		
		IWidgetPaser widgetPaser = new LocalWidgetPaser();
		widgetPaser = new BorderWrapper(widgetPaser);
		
		if(UserServiceFactory.getUserService().isUserLoggedIn() &&"edit".equals(params.get("mode"))){
			widgetPaser = new WidgetEditModeWrapper(widgetPaser); 
		}
		return widgetPaser.pase(params);
	}

}
