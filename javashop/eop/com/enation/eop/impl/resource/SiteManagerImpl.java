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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IDomainManager;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.IThemeManager;
import com.enation.eop.core.resource.dto.SiteDTO;
import com.enation.eop.core.resource.model.Dns;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.eop.core.resource.model.Theme;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.Page;

/**
 * 站点管理
 * @author kingapex
 *2010-5-9下午07:56:03
 */
public class SiteManagerImpl implements ISiteManager {

	private IDaoSupport<EopSite> daoSupport;
	private IDomainManager  domainManager;
	private IThemeManager themeManager;
	
	
	public int addDomain(EopSiteDomain eopSiteDomain) {
		//UserUtil.validUser(eopSiteDomain.getUserid());
		if ( checkInDomain( eopSiteDomain.getDomain())) {
			throw new IllegalArgumentException("域名[" + eopSiteDomain.getDomain() + "]已存在！");
		}
		daoSupport.insert("eop_sitedomain", eopSiteDomain);
		return daoSupport.getLastId("eop_sitedomain");
	}

	
	public Boolean checkInDomain(String domain) {
		String sql ="select count(0) from eop_sitedomain where domain = ?";
		int count = this.daoSupport.queryForInt(sql, domain);
		return count==0?false:true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	
	public Integer add(SiteDTO siteDTO) {
		
		//验证参数合法性
		siteDTO.vaild();
		
		EopSite site = siteDTO.getSite();
		
		if(site.getIcofile()==null)
		site.setIcofile(EopSetting.IMG_SERVER_DOMAIN+"/images/default/favicon.ico");
		if(site.getLogofile()==null)
		site.setLogofile(EopSetting.IMG_SERVER_DOMAIN+"/images/default/logo.gif");
		
		site.setPoint(1000);
		//添加站点并得到id
		this.daoSupport.insert("eop_site", siteDTO.getSite());
		Integer siteid =this.daoSupport.getLastId("eop_site");
		siteDTO.setSiteId(siteid);
		//为站点添加域名
		this.addDomain(siteDTO.getDomain());
		
		//为站点添加管理员
		this.daoSupport.insert("eop_siteadmin", siteDTO.getSiteAdmin());
		
		return siteid;
	}
	
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	
	public void deleteDomain(Integer domainid) {
		EopSiteDomain domain = domainManager.get(domainid);
		UserUtil.validUser(domain.getUserid());
		int domainCount = this.domainManager.getDomainCount(domain.getSiteid());
		if(domainCount<=1){
			throw new RuntimeException("此站点只有一个域名不可删除，删除后将不可访问");
		}
		String sql ="delete from eop_sitedomain where id=?";
		this.daoSupport.execute(sql, domainid);
		 
	}

	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	
	public void delete(Integer id) {
		EopSite site = this.get(id);
		UserUtil.validUser(site.getUserid());
		
		String sql ="delete from eop_sitedomain where siteid = ?";
		this.daoSupport.execute(sql, id);
		sql ="update eop_site set deleteflag = 1 where id = ?";
		this.daoSupport.execute(sql, id);
	}

	
	
	
	public List getDnsList() {
	
		String sql  ="select a.domain as domain,a.siteid as siteid, b.sitename, b.adminthemeid, b.themeid, b.userid, b.keywords, b.descript,b.themepath,b.icofile as icofile,b.logofile as logofile ,b.point as point from eop_sitedomain a left join eop_site b on b.id = a.siteid";
		return this.daoSupport.queryForList(sql, new DnsMapper());
		  
	}
	
	
	private static class DnsMapper implements ParameterizedRowMapper<Dns> {

		private String getContext(EopSite site){
			if("2".equals(EopSetting.RUNMODE )){
				return EopSetting.IMG_SERVER_DOMAIN+"/user/"+ site.getUserid() +"/"+site.getId();
			}else{
				return EopSetting.IMG_SERVER_DOMAIN;
			}
		}
		
		
		public Dns mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dns dns = new Dns();
			dns.setDomain(rs.getString("domain"));
			EopSite site = new EopSite();
			site.setId(rs.getInt("siteid"));
			site.setAdminthemeid(rs.getInt("adminthemeid"));
			site.setThemeid(rs.getInt("themeid"));
			site.setSitename(rs.getString("sitename"));
			site.setUserid(rs.getInt("userid"));
			site.setKeywords(rs.getString("keywords"));
			site.setDescript(rs.getString("descript"));
			site.setThemepath(rs.getString("themepath"));
			site.setPoint(rs.getInt("point"));
			String icofile  = rs.getString("icofile");
			if(icofile!=null) {
				 
					icofile = icofile.replaceAll(EopSetting.FILE_STORE_PREFIX, getContext(site));
				 
			}
			site.setIcofile(icofile); 
			
			String logofile = rs.getString("logofile");
			if(logofile!=null ){ 
				logofile = logofile.replaceAll(EopSetting.FILE_STORE_PREFIX,  getContext(site));
			}
			site.setLogofile(logofile);
			dns.setSite(site);
			return dns;
		}

	}

	
	
	public EopSite get(Integer id) {
		String sql ="select * from eop_site where  id = ?";
		EopSite site  = this.daoSupport.queryForObject(sql, EopSite.class, id);
		String icofile  = site.getIcofile();
		if(icofile!=null) 	icofile  =UploadUtil.replacePath(icofile); 
		site.setIcofile(icofile);
		
		String logofile  = site.getLogofile();
		if(logofile!=null ) logofile = logofile.replaceAll(EopSetting.FILE_STORE_PREFIX, EopSetting.IMG_SERVER_DOMAIN);
		site.setLogofile(logofile);
		return site;
	}

	
	public EopSite get(String domain) {
		 
		String sql ="select a.* from eop_site a join eop_sitedomain b on b.siteid = a.id   and b.domain= ?";
		List<EopSite> sitelist =this.daoSupport.queryForList(sql, EopSite.class, domain);
		if(sitelist==null || sitelist.isEmpty()) return null;
		EopSite site = sitelist.get(0);
		String icofile  = site.getIcofile();
		if(icofile!=null) icofile  =UploadUtil.replacePath(icofile); 
		site.setIcofile(icofile);
		
		String logofile  = site.getLogofile();
		if(logofile!=null ) logofile = logofile.replaceAll(EopSetting.FILE_STORE_PREFIX, EopSetting.IMG_SERVER_DOMAIN);
		site.setLogofile(logofile);
		return site;
	}

	public Page list(int pageNo,int pageSize){
		
		
		return this.daoSupport.queryForPage("select * from eop_site", pageNo, pageSize);
	}

	
	public Page list(int pageNo, int pageSize,  String order,
			String search) {
			Integer userid  = EopContext.getContext().getCurrentSite().getUserid();
		 List<EopSiteDomain> listdomain = this.domainManager.listUserDomain();
			if (search == null)
				search = "";
			else
				search = " and sitename like '%" + search + "%'";
			if (order == null)
				order = "";
			else
				order = " order by " + order.replace(":", " ");
			
			Page page =  daoSupport.queryForPage( "select * from eop_site where deleteflag = 0 and userid = "
					+ userid + search + order, pageNo, pageSize);
		 
		  List<Map> listsite = (List<Map>) (page.getResult());

			for (Map site : listsite) {
				List<EopSiteDomain> domainList = new ArrayList<EopSiteDomain>();

				for (EopSiteDomain siteDomain : listdomain) {
					if (site.get("id").toString().equals(
							siteDomain.getSiteid().toString())) {
						domainList.add(siteDomain);
					}
				}
				site.put("eopSiteDomainList", domainList);
			}

			return page;
	}

	
	
	public void edit(EopSite eopSite) {
		this.daoSupport.update("eop_site", eopSite, "id = " + eopSite.getId());
	 
	}

	
	public void setSiteProduct(Integer userid, Integer siteid, String productid) {
		String sql  ="update eop_site set productid=? where userid=? and id=?";
		this.daoSupport.execute(sql,productid,userid,siteid);
	}

	
	public void changeAdminTheme(Integer themeid) {
		EopSite site  = EopContext.getContext().getCurrentSite();
		String sql ="update eop_site set adminthemeid=? where userid=? and id=?";
		this.daoSupport.execute(sql, themeid,site.getUserid(),site.getId());
		EopContext.getContext().getCurrentSite().setAdminthemeid(themeid);
		
	}

	
	public void changeTheme(Integer themeid) {
		EopSite site  = EopContext.getContext().getCurrentSite();
		Theme theme = themeManager.getTheme(themeid);
		String sql ="update eop_site set themeid=?,themepath=? where userid=? and id=?";
		this.daoSupport.execute(sql, themeid,theme.getPath(),site.getUserid(),site.getId());
		site.setThemeid(themeid);
		site.setThemepath(theme.getPath());
	}

	
	public List listDomain(Integer userid, Integer siteid) {
		String sql ="select * from eop_sitedomain where userid=? and siteid=?";
		return this.daoSupport.queryForList(sql, EopSiteDomain.class,userid,siteid);
	}
	
	public void updatePoint(Integer id,int point){
		if(point==-1){
			this.daoSupport.execute("update eop_site set point=? where id=?",point, id);
		}else{
			this.daoSupport.execute("update eop_site set point=point+? where id=?", point,id);
		}
	}

	public IDaoSupport<EopSite> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopSite> daoSupport) {
		this.daoSupport = daoSupport;
	}

	public IDomainManager getDomainManager() {
		return domainManager;
	}

	public void setDomainManager(IDomainManager domainManager) {
		this.domainManager = domainManager;
	}

	public IThemeManager getThemeManager() {
		return themeManager;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

}
