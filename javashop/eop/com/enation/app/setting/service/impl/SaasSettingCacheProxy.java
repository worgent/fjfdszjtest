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
package com.enation.app.setting.service.impl;

import java.util.List;
import java.util.Map;

import com.enation.app.setting.service.ISettingService;
import com.enation.app.setting.service.SettingRuntimeException;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.cache.ICache;

/**
 * SAAS式的设置缓存代理类。
 * @author kingapex
 * 2010-1-15下午03:12:29
 */
public class SaasSettingCacheProxy implements ISettingService {

	private ISettingService settingService;
	private ICache<Map<String,Map<String,String>>> cache;
	public SaasSettingCacheProxy(ISettingService settingService){
		this.settingService = settingService;
	}
	
	
	public Map<String,Map<String ,String>>  getSetting() {
		String uKey =   this.getCurrentUserid() +"_"+ this.getCurrentSiteid() ;
		Map<String,Map<String,String>> settings  = cache.get(uKey);
		//未命中，由库中取出设置并压入缓存
		if(settings==null || settings.size()<=0){
			settings= this.settingService.getSetting();
			cache.put(uKey,settings);
		}
		return settings;
	}
 
	
	//保存库同时保存缓存
	
	public void save(Map<String,Map<String,String>> settings ) throws SettingRuntimeException {
		String uKey =   this.getCurrentUserid() +"_"+ this.getCurrentSiteid() ;
		this.settingService.save(settings);
		cache.put(uKey,  settingService.getSetting());
	}
	
	
	/**
	 * 获取当前用户id
	 * @return
	 */
	protected Integer getCurrentUserid(){
		EopSite site = EopContext.getContext().getCurrentSite();
//		IUserService  userService = UserServiceFactory.getUserService();
		Integer userid = site.getUserid();
		return userid;
	}
	
	
	/**
	 * 获取当前站点
	 * @return
	 */
	protected Integer getCurrentSiteid(){
		EopSite site = EopContext.getContext().getCurrentSite();
		return site.getId();
	}

	
	public String getSetting(String group, String code) {
		Map<String,Map<String ,String>> settings  = this.getSetting();
		if(settings==null) return null;
		
		Map<String ,String> setting = settings.get(group);		
		if(setting== null )return null;
		
		return setting.get(code);
	}

	public void setCache(ICache<Map<String, Map<String, String>>> cache) {
		this.cache = cache;
	}

	
	public List<String> onInputShow() {
		
		return this.settingService.onInputShow();
	}
	
	
}
