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
package com.enation.eop.core.resource.dto;

import com.enation.eop.core.EopException;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteAdmin;
import com.enation.eop.core.resource.model.EopSiteDomain;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-12-10 下午06:21:25
 *         </p>
 * @version 1.0
 */
public class SiteDTO {
	private EopSite site;
	private EopSiteDomain domain;
	private EopSiteAdmin siteAdmin;
	
//	public SiteDTO(){
//		site = new EopSite();
//		domainList = new ArrayList<EopSiteDomain>();
//		listSiteApp = new ArrayList<EopSiteApp>();
//		siteAdminList = new ArrayList<EopSiteAdmin>();
//	}
	
	public void vaild(){
		if(this.domain==null){
			throw new IllegalArgumentException("站点至少要有一个域名！");
		}		
		
		if(this.siteAdmin==null){
			throw new IllegalArgumentException("站点至少应该指定一位管理员！");
		}
	}
	
	public void setUserId(Integer userid){
		site.setUserid(userid);
		domain.setUserid(userid);
		siteAdmin.setUserid(userid);
	}
	
	public void setSiteId(Integer siteid){
		domain.setSiteid(siteid);
		siteAdmin.setSiteid(siteid);
	}
	
	public void setManagerid(Integer managerid){
		siteAdmin.setManagerid(managerid);
	}

	public EopSite getSite() {
		return site;
	}

	public void setSite(EopSite site) {
		this.site = site;
	}

	public EopSiteDomain getDomain() {
		return domain;
	}

	public void setDomain(EopSiteDomain domain) {
		this.domain = domain;
	}

	public EopSiteAdmin getSiteAdmin() {
		return siteAdmin;
	}

	public void setSiteAdmin(EopSiteAdmin siteAdmin) {
		this.siteAdmin = siteAdmin;
	}

}
