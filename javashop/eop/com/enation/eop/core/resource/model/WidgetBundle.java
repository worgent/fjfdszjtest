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
package com.enation.eop.core.resource.model;

/**
 * 挂件束
 * @author kingapex<br/>
 * <b>Edited by lzf 2009-11-18</b>
 *
 */
public class WidgetBundle extends Resource{
	
	private String widgetname;
	private String widgettype;
	private String settingurl;
	public String getWidgetname() {
		return widgetname;
	}
	public void setWidgetname(String widgetname) {
		this.widgetname = widgetname;
	}
	public String getWidgettype() {
		return widgettype;
	}
	public void setWidgettype(String widgettype) {
		this.widgettype = widgettype;
	}
	public String getSettingurl() {
		return settingurl;
	}
	public void setSettingurl(String settingurl) {
		this.settingurl = settingurl;
	}
	
	
	
}
