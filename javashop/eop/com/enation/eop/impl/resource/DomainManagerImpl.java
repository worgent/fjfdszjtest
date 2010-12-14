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
package com.enation.eop.impl.resource;

import java.util.List;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IDomainManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.framework.database.IDaoSupport;

/**
 * 域名管理
 * 
 * @author kingapex 2010-5-9下午08:14:11
 */
public class DomainManagerImpl implements IDomainManager {

	private IDaoSupport<EopSiteDomain> daoSupport;

	
	
	public EopSiteDomain get(Integer id) {
		String sql = "select * from eop_sitedomain where id = ?";
		return daoSupport.queryForObject(sql, EopSiteDomain.class, id);
	}

	
	public List<EopSiteDomain> listUserDomain() {
		Integer userid = EopContext.getContext().getCurrentSite().getUserid();
		String sql = "select * from eop_sitedomain where userid=?";
		return this.daoSupport.queryForList(sql, EopSiteDomain.class, userid);
	}

	
	public List<EopSiteDomain> listSiteDomain() {
		EopSite site = EopContext.getContext().getCurrentSite();
		String sql = "select * from eop_sitedomain where userid=? and siteid =?";
		return this.daoSupport.queryForList(sql, EopSiteDomain.class, site
				.getUserid(), site.getId());
	}
	
	public List<EopSiteDomain> listSiteDomain(Integer userid,Integer siteid) {
		String sql = "select * from eop_sitedomain where userid=? and siteid =?";
		return this.daoSupport.queryForList(sql, EopSiteDomain.class, userid,siteid);
	}
	
	public void edit(EopSiteDomain domain) {
		this.daoSupport.update("eop_sitedomain", domain, " id = "
				+ domain.getId());
	}

	public IDaoSupport<EopSiteDomain> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopSiteDomain> daoSupport) {
		this.daoSupport = daoSupport;
	}

	
	public int getDomainCount(Integer siteid) {
		String sql = "select count(0)  from eop_sitedomain where  siteid =?";
		return this.daoSupport.queryForInt(sql, siteid);
	}




}
