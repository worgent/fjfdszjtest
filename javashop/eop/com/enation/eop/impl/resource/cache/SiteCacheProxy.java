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

import com.enation.eop.EopSetting;
import com.enation.eop.core.EopException;
import com.enation.eop.core.resource.IDomainManager;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.dto.SiteDTO;
import com.enation.eop.core.resource.model.Dns;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.eop.core.resource.model.SiteAppView;
import com.enation.framework.cache.AbstractCacheProxy;
import com.enation.framework.cache.CacheFactory;
import com.enation.framework.database.Page;

/**
 * 站点缓存代理
 * @author kingapex
 * <p>2009-12-17 上午11:38:56</p>
 * @version 1.0
 */
public class SiteCacheProxy extends AbstractCacheProxy<EopSite> implements ISiteManager {
	
	private ISiteManager siteManager;
	private IDomainManager domainManager;
	private static final String SITE_LIST_CACHE_KEY ="eopDNS";
	public SiteCacheProxy(ISiteManager siteManager) {
		super(CacheFactory.SITE_CACHE_NAME_KEY);
		this.siteManager  = siteManager;
	}

	
	public int addDomain(EopSiteDomain eopSiteDomain) {
		EopSite site  =siteManager.get(eopSiteDomain.getSiteid());
		this.cache.put(eopSiteDomain.getDomain(),site);
		return siteManager.addDomain(eopSiteDomain);
	}
	

	
	public Integer add(SiteDTO siteDTO) {
		
		EopSite  site  = cache.get(siteDTO.getDomain().getDomain());
		if(site  == null){
			return siteManager.add(siteDTO);
		}
		throw new EopException("域名:"+ siteDTO.getDomain().getDomain() +" 已经存在！");
	}

	
	public void deleteDomain(Integer domainid) {
		EopSiteDomain siteDomain = this.domainManager.get(domainid);
		siteManager.deleteDomain(domainid);
		this.cache.remove(siteDomain.getDomain());
	}

	
	public void delete(Integer id) {
		//移除缓存对应的site映射
		List<EopSiteDomain > list = domainManager.listSiteDomain();
		for(EopSiteDomain siteDomain :list){
			this.cache.remove(siteDomain.getDomain());
		}
		this.siteManager.delete(id);

		
	}

	
	
	/**
	 * 读取所有的dns列表并压入到Cache中<br/>
	 * 
	 */
	
	public List<Dns> getDnsList() {

		if(logger.isDebugEnabled()){
			logger.debug("query all DNS form datagae,then put them in cache ");
		}
		List<Dns>  dnsList = this.siteManager.getDnsList();
		for(Dns dns: dnsList){
			cache.put(dns.getDomain(), dns.getSite());
		}
		if(logger.isDebugEnabled()){
			logger.debug("DNS put in cache complete!");
		}		
		return dnsList;
	}

	
	
	
	public EopSite get(Integer id) {
		
		return this.siteManager.get(id);
	}

	
	/**
	 * 由dns缓存中找到site
	 */
	
	public EopSite get(String domain) {
		
		
		if(logger.isDebugEnabled()){
			logger.debug("parse domain["+domain+"]...");
		}
		
		EopSite  site  = cache.get(domain);
		if(site  == null){
			
			if(logger.isDebugEnabled()){
				logger.debug("domain["+domain+"] not in cache, then query from database");
			}
			
			site  =  this.siteManager.get(domain);
			
		}
		
		if(site==null){
			if(logger.isDebugEnabled()){
				logger.debug("domain["+domain+"] not exist");
			}			
			throw new EopException("域名"+domain+"不存在");
		}
		
		
		
		if(logger.isDebugEnabled()){
			logger.debug("finded and siteid is : "+ site.getId());
		}
		cache.put(domain, site);
		return site;
	}
	
	
	public Boolean checkInDomain(String domain){
		return this.siteManager.checkInDomain(domain);
	}
 

	public Page list(int pageNo,int pageSize){
		return this.siteManager.list(pageNo, pageSize);
	}
	
	public Page list(int pageNo, int pageSize, String order,
			String search) {
		return siteManager.list(pageNo, pageSize, order, search);
	}

	
	public void edit(EopSite eopSite) {
		siteManager.edit(eopSite);
		EopSite site = siteManager.get(eopSite.getId());
		refreshCache(site);
		this.siteManager.edit(eopSite);
	}

	
	private void refreshCache(EopSite site){
		List<EopSiteDomain > list = domainManager.listSiteDomain();
		for(EopSiteDomain siteDomain :list){
			this.cache.put( siteDomain.getDomain() ,site);
		}
	}
	
	public void updatePoint(Integer id,int point){
		this.siteManager.updatePoint(id, point);
		
		//更新缓存中的point
		EopSite site  =this.get(id);
		List<EopSiteDomain> list= this.domainManager.listSiteDomain(site.getUserid(), id);
		if(list!=null && !list.isEmpty()){
			String domain  = list.get(0).getDomain();
			site  =this.get(domain);
			if(site!=null) {
				if(point ==-1)
					site.setPoint(point);
				else{
					site.setPoint(site.getPoint()+point);
				}
			}
		}
		
	}	
	
	public void setSiteProduct(Integer userid, Integer siteid, String productid) {
		this.siteManager.setSiteProduct(userid, siteid, productid);
	}

	
	public void changeAdminTheme(Integer themeid) {
		this.siteManager.changeAdminTheme(themeid);
	}

	
	public void changeTheme(Integer themeid) {
		this.siteManager.changeTheme(themeid);
	}

	
	public List listDomain(Integer userid, Integer siteid) {
		 
		return this.siteManager.listDomain(userid, siteid);
	}

	public IDomainManager getDomainManager() {
		return domainManager;
	}

	public void setDomainManager(IDomainManager domainManager) {
		this.domainManager = domainManager;
	}
	
	

}
