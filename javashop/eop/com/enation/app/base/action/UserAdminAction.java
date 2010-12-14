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

import java.util.List;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IAdminUserManager;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.core.resource.model.SiteManagerView;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;

public class UserAdminAction extends WWAction {

	private IAdminUserManager adminUserManager;
	private EopUserAdmin eopUserAdmin;
	private Integer id;
	private Integer managerid;
	private List<SiteManagerView> siteManagerList;
	private Integer[] sites;
	private IUserManager userManager;
	private String oldpassword;
	private String password;

	private String order;
	private String search;

	
	public String execute() throws Exception {
		this.webpage = this.adminUserManager.list(this.getPage(), this
				.getPageSize(), order, search);
		return SUCCESS;
	}

	public String add() throws Exception {
		siteManagerList = adminUserManager.getViewList(-1);
		return "add";
	}

	public String addSave() throws Exception {

		try {
			eopUserAdmin.setPassword(StringUtil.md5("123456"));// 设置初始密码为123456
			eopUserAdmin.setUserid(EopContext.getContext().getCurrentSite().getUserid());
			Integer adminid = adminUserManager.add(eopUserAdmin);
			adminUserManager.putView(adminid, sites);
			this.msgs.add("新增管理员成功");
		} catch (RuntimeException e) {
			this.msgs.add(e.getMessage());

		}
		this.urls.put("管理员列表", "userAdmin.do");

		return this.MESSAGE;
	}

	public String edit() throws Exception {
		eopUserAdmin = this.adminUserManager.get(id);
		siteManagerList = adminUserManager.getViewList(id);
		return "edit";
	}

	public String editSave() throws Exception {
		try {
			adminUserManager.putView(eopUserAdmin.getId(), sites);
			this.adminUserManager.edit(eopUserAdmin);
			this.msgs.add("修改管理员成功");
		} catch (RuntimeException e) {
			this.msgs.add(e.getMessage());
		}
		this.urls.put("管理员列表", "userAdmin.do");

		return this.MESSAGE;
	}

	public String delete() throws Exception {
		try {
			this.adminUserManager.delete(id);
			this.msgs.add("删除成功");
		} catch (RuntimeException e) {
			this.msgs.add(e.getMessage());
		}
		this.urls.put("管理员列表", "userAdmin.do");

		return this.MESSAGE;
	}

	public String editPassword() throws Exception {
		return "editPassword";
	}

	public String initPassword() throws Exception {

		try {
			adminUserManager.changePassword(id, "123456");
			this.msgs.add("密码初始化成功");
		} catch (RuntimeException e) {
			this.msgs.add(e.getMessage());
		}
		this.urls.put("管理员列表", "userAdmin.do");

		return MESSAGE;
	}

	public String changePassword() throws Exception {
	 
		try {
			managerid = UserServiceFactory.getUserService().getCurrentManagerId();
			adminUserManager.changePassword(managerid, password, oldpassword);
			this.msgs.add("密码初修改成功");
		} catch (RuntimeException e) {
			this.msgs.add(e.getMessage());

		}
		this.urls.put("管理员列表", "userAdmin.do");
		return MESSAGE;
	}

	public IAdminUserManager getAdminUserManager() {
		return adminUserManager;
	}

	public void setAdminUserManager(IAdminUserManager adminUserManager) {
		this.adminUserManager = adminUserManager;
	}

	public EopUserAdmin getEopUserAdmin() {
		return eopUserAdmin;
	}

	public void setEopUserAdmin(EopUserAdmin eopUserAdmin) {
		this.eopUserAdmin = eopUserAdmin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getManagerid() {
		return managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

	public List<SiteManagerView> getSiteManagerList() {
		return siteManagerList;
	}

	public void setSiteManagerList(List<SiteManagerView> siteManagerList) {
		this.siteManagerList = siteManagerList;
	}

	public Integer[] getSites() {
		return sites;
	}

	public void setSites(Integer[] sites) {
		this.sites = sites;
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	
}
