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
package com.enation.app.base.service;

import com.enation.eop.EopSetting;
import com.enation.eop.sdk.App;
import com.enation.framework.database.IDBRouter;

public class BaseApp implements App {
	private IDBRouter baseDBRouter;
	private IDBRouter baseStandaloneRouter;
	
	public void install() {
		if("2".equals(EopSetting.RUNMODE)){ //只有saas版安 装数据库
			baseDBRouter.createTable( "menu");
			baseDBRouter.createTable( "theme");
			baseDBRouter.createTable( "widgetbundle");
			baseDBRouter.createTable( "themeuri");
			baseDBRouter.createTable( "admintheme");
			baseDBRouter.createTable( "border");
			baseDBRouter.createTable( "access");
		}
	}
	
	
	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}


	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}


	public IDBRouter getBaseSaasDBRouter() {
		return baseDBRouter;
	}
	public void setBaseSaasDBRouter(IDBRouter baseSaasDBRouter) {
		this.baseDBRouter = baseSaasDBRouter;
	}
	public IDBRouter getBaseStandaloneRouter() {
		return baseStandaloneRouter;
	}
	public void setBaseStandaloneRouter(IDBRouter baseStandaloneRouter) {
		this.baseStandaloneRouter = baseStandaloneRouter;
	}


	public String dumpSql() {
		return "";
	}
 
	

}
