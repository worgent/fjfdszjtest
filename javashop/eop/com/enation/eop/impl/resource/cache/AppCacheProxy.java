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
package com.enation.eop.impl.resource.cache;

import java.util.List;

import com.enation.eop.core.EopException;
import com.enation.eop.core.resource.IAppManager;
import com.enation.eop.core.resource.model.EopApp;
import com.enation.eop.core.resource.model.EopAppSiteView;
import com.enation.eop.core.resource.model.EopSiteApp;
import com.enation.framework.cache.AbstractCacheProxy;
import com.enation.framework.cache.CacheFactory;

/**
 * App Manager的缓存代理
 * @author kingapex
 * <p>2009-12-16 下午05:15:28</p>
 * @version 1.0
 */
public class AppCacheProxy extends AbstractCacheProxy<List<EopApp>> implements IAppManager {
	
	
	private IAppManager appManager;
	private static final String APP_LIST_CACHE_KEY = "applist";
	
	public  AppCacheProxy(IAppManager appManager){
		super(CacheFactory.APP_CACHE_NAME_KEY  );
		this.appManager = appManager;
	}
	
	
	
	public void add(EopApp app) {
		cache.clear();
	    appManager.add(app);
	}

	
	public EopApp get(String appid) {
		
		if(logger.isDebugEnabled()){
			logger.debug("get app : "+ appid);
		}
		List<EopApp> appList = this.list();
	 
		for(EopApp app :appList){
			if(app.getAppid().equals(appid)){
				return app;
			 
			}
		}
		
		throw new  EopException("App not found");
	}

	
	public List<EopApp> list() {
		
		List<EopApp> appList = this.cache.get(APP_LIST_CACHE_KEY);
		
		if(appList==null){
			if(logger.isDebugEnabled()){
				logger.debug("get applist from database");
			}
			appList = appManager.list();
			 this.cache.put(APP_LIST_CACHE_KEY, appList);
		}else{
			if(logger.isDebugEnabled()){
				logger.debug("get applist from cache");
			}
		}
		return appList;
	}
	 
}
