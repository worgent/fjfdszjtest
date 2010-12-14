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
package com.enation.app.saler.service;

/**
 * 根据类型返回合适的Installer,此文件必须工作于spring 容器下
 * @author kingapex
 * 2010-1-20下午07:20:34
 */
public class InstallerFactory {
	public static final String TYPE_APP ="apps";
	public static final String TYPE_MENU ="menus";
	public static final String TYPE_ADMINTHEME ="adminThemes";
	public static final String TYPE_THEME ="themes";
	public static final String TYPE_URL ="urls";
	public static final String TYPE_WIDGET ="widgets";
 
	
	private IInstaller menuInstaller;
	private IInstaller adminThemeInstaller;
	private IInstaller themeInstaller;
	private IInstaller uriInstaller;
	private IInstaller widgetInstaller;
	private IInstaller appInstaller;
 
	
	public IInstaller getInstaller(String type){
		
		
		if(TYPE_APP.equals(type)){
			return this.appInstaller;
		}
		
		if(TYPE_MENU.equals(type)){
			return this.menuInstaller;
		}
		

		if(TYPE_ADMINTHEME.equals(type)){
			return this.adminThemeInstaller;
		}
		
		if(TYPE_THEME.equals(type)){
			return this.themeInstaller;
			
		}

		if(TYPE_URL.equals(type)){
			return this.uriInstaller;
		}

		if(TYPE_WIDGET.equals(type)){
			return this.widgetInstaller;
		}
		
		
 
		throw new  RuntimeException(" get Installer instance error[incorrect type param]");
	}

	public void setMenuInstaller(IInstaller menuInstaller) {
		this.menuInstaller = menuInstaller;
	}

	public void setAdminThemeInstaller(IInstaller adminThemeInstaller) {
		this.adminThemeInstaller = adminThemeInstaller;
	}

	public void setThemeInstaller(IInstaller themeInstaller) {
		this.themeInstaller = themeInstaller;
	}

	public void setUriInstaller(IInstaller uriInstaller) {
		this.uriInstaller = uriInstaller;
	}

	public void setWidgetInstaller(IInstaller widgetInstaller) {
		this.widgetInstaller = widgetInstaller;
	}

	public IInstaller getAppInstaller() {
		return appInstaller;
	}

	public void setAppInstaller(IInstaller appInstaller) {
		this.appInstaller = appInstaller;
	}
	
}
