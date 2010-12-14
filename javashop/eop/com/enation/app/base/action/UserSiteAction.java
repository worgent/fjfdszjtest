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
package com.enation.app.base.action;

import java.io.File;
import java.util.List;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IDomainManager;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.action.WWAction;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-12-9 上午10:14:56
 *         </p>
 * @version 1.0
 */
public class UserSiteAction extends WWAction {

	private ISiteManager siteManager;
 
	private IDomainManager domainManager;
	
	private EopSite eopSite;
	private Integer id;
	private Integer domainid;
	private Integer statusid;
	private File cologo;
	private String cologoFileName;
	private File ico;
	private String icoFileName;
	private List<EopSiteDomain> siteDomainList;
	private EopSiteDomain eopSiteDomain;
	private String sitedomain;
	private IUserManager userManager;
 
	private Integer siteid;

 

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public EopSiteDomain getEopSiteDomain() {
		return eopSiteDomain;
	}

	public void setEopSiteDomain(EopSiteDomain eopSiteDomain) {
		this.eopSiteDomain = eopSiteDomain;
	}

	public List<EopSiteDomain> getSiteDomainList() {
		return siteDomainList;
	}

	public void setSiteDomainList(List<EopSiteDomain> siteDomainList) {
		this.siteDomainList = siteDomainList;
	}

 

	public File getIco() {
		return ico;
	}

	public void setIco(File ico) {
		this.ico = ico;
	}

	public String getIcoFileName() {
		return icoFileName;
	}

	public void setIcoFileName(String icoFileName) {
		this.icoFileName = icoFileName;
	}

	public String getCologoFileName() {
		return cologoFileName;
	}

	public void setCologoFileName(String cologoFileName) {
		this.cologoFileName = cologoFileName;
	}

	public File getCologo() {
		return cologo;
	}

	public void setCologo(File cologo) {
		this.cologo = cologo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EopSite getEopSite() {
		return eopSite;
	}

	public void setEopSite(EopSite eopSite) {
		this.eopSite = eopSite;
	}

	private String order;
	private String search;
	
	
	public String execute() {
		this.webpage =siteManager.list(this.getPage(), this
				.getPageSize(),   order, search);
		return SUCCESS;
	}

	public String add()   {
		return "add";
	}

	public String edit()  {
		eopSite = siteManager.get(id);
		siteDomainList =domainManager.listSiteDomain();
		return "edit";
	}

	public String domainlist()  {
		siteDomainList =domainManager.listSiteDomain();
		return "domainlist";
	}

	public String deleteDomain()  {
			try{
				this.siteManager.deleteDomain(domainid);
				this.json="{result:1,message:'删除成功'}";
			}catch(RuntimeException e){
				this.logger.error(e.getMessage(), e);
				this.json="{result:0,message:'"+e.getMessage()+"'}";
			}
		return this.JSON_MESSAGE;
	}

	public String editdomain() throws Exception {
		
		if (statusid == 0) {
			statusid = 1;
		} else {
			statusid = 0;
		}
		
		eopSiteDomain = new EopSiteDomain();
		eopSiteDomain.setStatus(statusid);
		eopSiteDomain.setId(domainid);
		
		try{
			this.domainManager.edit(eopSiteDomain);
			this.json="{result:1,message:'修改成功'}";
		}catch(RuntimeException e){
			this.logger.error(e.getStackTrace());
			this.json="{result:0,message:'修改失败'}";
		}
	 
		return this.JSON_MESSAGE;
	}

/*	public String addSave() throws Exception {

		if (cologo != null) {
			
			String logo = UploadUtil.upload(cologo, cologoFileName, "user");
			eopSite.setLogofile(logo);

		}

		if (ico != null) {
			String  icoPath =  UploadUtil.upload(ico, icoFileName, "user"); 
			eopSite.setIcofile(icoPath);

		}
		SiteDTO siteDTO = new SiteDTO();
		eopSite.setThemeid(1);
		eopSite.setAdminthemeid(1);
		siteDTO.setSite(eopSite);

		eopSiteDomain = new EopSiteDomain();
		eopSiteDomain.setDomain(sitedomain);
		siteDTO.setDomain(eopSiteDomain);

		EopSiteAdmin siteAdmin = new EopSiteAdmin();
		siteAdmin.setManagerid(managerid);
		siteDTO.setSiteAdmin(siteAdmin);

		siteDTO.setUserId(userid);
		Integer result = siteManager.add(siteDTO);

		// installService.setJdbcTemplate(this.jdbcTemplate); // lzy add
		//installService.install(userid, result);
		productService.install(userid, result, "base");

		if (result > 0) {
			this.setMessage("新建站点成功！");
		} else {
			this.setMessage("新建站点失败，请重试！");
		}
		this.setBackUrl("userSite.do");
		return FORM_RETURN_HTMLMESSAGE;
	}*/

	public String editSave() throws Exception {
		if (cologo != null) {
			String logo = UploadUtil.upload(cologo, cologoFileName, "user");
			eopSite.setLogofile( logo);

		}

		if (ico != null) {
			String  icoPath =  UploadUtil.upload(ico, icoFileName, "user"); 
			eopSite.setIcofile(icoPath);

		}
		siteManager.edit(eopSite);
		
		this.msgs.add("修改成功");
		this.urls.put("站点列表", "userSite.do");
	
		return this.MESSAGE;
	}

	public String delete() throws Exception {
		try{
			siteManager.delete(id);
			this.msgs.add("删除成功");
		}catch(RuntimeException e){
			e.printStackTrace();
			this.msgs.add(e.getMessage());
		}
		this.urls.put("站点列表", "userSite.do");
		return this.MESSAGE;
	}

	public String adddomain() throws Exception {
		return "adddomain";
	}

	public String addDomainSave() throws Exception {
		Integer userid  = EopContext.getContext().getCurrentSite().getUserid();
		eopSiteDomain = new EopSiteDomain();
		eopSiteDomain.setUserid( userid);
		eopSiteDomain.setDomain(sitedomain);
		eopSiteDomain.setSiteid(siteid);
		int result = -1;
		try {
			result = siteManager.addDomain(eopSiteDomain);
		} catch (RuntimeException e) {
			this.json ="{result:0,message:'"+ e.getMessage()+"'}";
			return this.JSON_MESSAGE;
		}

		if (result > 0) {
			this.json ="{result:1,message:'增加成功'}";
		} else {
			this.json ="{result:0,message:'新增域名失败'}";
			 
		}
		return JSON_MESSAGE;
	}

	private Integer managerid;
	public String changeDefaultSite() throws Exception {
		Integer userid  = EopContext.getContext().getCurrentSite().getUserid();
		userManager.changeDefaultSite(userid, managerid, id);
 
		return this.MESSAGE;
	}

	public Integer getDomainid() {
		return domainid;
	}

	public void setDomainid(Integer domainid) {
		this.domainid = domainid;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getSitedomain() {
		return sitedomain;
	}

	public void setSitedomain(String sitedomain) {
		this.sitedomain = sitedomain;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public IDomainManager getDomainManager() {
		return domainManager;
	}

	public void setDomainManager(IDomainManager domainManager) {
		this.domainManager = domainManager;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getManagerid() {
		return managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

}
