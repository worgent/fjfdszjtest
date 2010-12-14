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
package com.qzgf.core.service.impl.cache;

import java.util.List;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.cache.AbstractCacheProxy;
import com.qzgf.core.model.SiteMenu;
import com.qzgf.core.service.ISiteMenuManager;


public class SiteMenuCacheProxy extends AbstractCacheProxy<List<SiteMenu>> implements ISiteMenuManager {
	private static final String MENU_LIST_CACHE_KEY = "siteMenuList";
	private ISiteMenuManager emssiteMenuManager;
	public SiteMenuCacheProxy(ISiteMenuManager emssiteMenuManager) {
		super(MENU_LIST_CACHE_KEY);
		this.emssiteMenuManager = emssiteMenuManager;
		 
	}

	private void cleanCache(){
		EopSite site  = EopContext.getContext().getCurrentSite();
		this.cache.remove( MENU_LIST_CACHE_KEY+"_"+site.getUserid() +"_"+site.getId());
	}
	
	
	public void add(SiteMenu siteMenu) {
		this.emssiteMenuManager.add(siteMenu);
		this.cleanCache();
	}

	
	public void delete(Integer id) {
		this.emssiteMenuManager.delete(id);
		this.cleanCache();
	 
	}

	
	public void edit(SiteMenu siteMenu) {
		this.emssiteMenuManager.edit(siteMenu);
		this.cleanCache();
	}

	
	public SiteMenu get(Integer menuid) {
		return this.emssiteMenuManager.get(menuid);
	}

	
	public List<SiteMenu> list(Integer parentid) {
		EopSite site  = EopContext.getContext().getCurrentSite();
		List<SiteMenu> menuList  =  this.cache.get( MENU_LIST_CACHE_KEY+"_"+site.getUserid() +"_"+site.getId());
		
		
		if(menuList== null ){
			menuList = this.emssiteMenuManager.list(parentid);
			this.cache.put( MENU_LIST_CACHE_KEY+"_"+site.getUserid() +"_"+site.getId(),menuList);
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load sitemenu from database");
			}
		}else{
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load sitemenu from cache");
			}
		}
		
		return menuList;
	}

	
	public void updateSort(Integer[] menuid, Integer[] sort) {
		this.emssiteMenuManager.updateSort(menuid, sort);
		this.cleanCache();
	}

}
