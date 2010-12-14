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

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IAdminThemeManager;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.model.AdminTheme;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.action.WWAction;

/**
 * 站点主题管理
 * 
 * @author lzf
 *         <p>
 *         2009-12-30 上午11:01:08
 *         </p>
 * @version 1.0
 */
public class SiteAdminThemeAction extends WWAction {

	private IAdminThemeManager adminThemeManager;
	private ISiteManager siteManager;
	
	private List<AdminTheme> listTheme;
	private AdminTheme adminTheme;
	private EopSite eopSite;
	private String previewpath;
	private String previewBasePath;
	private Integer themeid;
	
	
	
	
	public String execute() throws Exception {
		EopSite site  = EopContext.getContext().getCurrentSite();
		String contextPath = EopContext.getContext().getContextPath();
		previewBasePath = EopSetting.IMG_SERVER_DOMAIN + contextPath+ "/adminthemes/";
		adminTheme = adminThemeManager.get( site.getAdminthemeid());
		listTheme = adminThemeManager.list();
		previewpath = previewBasePath + adminTheme.getPath() + "/preview.png";
		return SUCCESS;
	}
	
	public String change()throws Exception {
		siteManager.changeAdminTheme(themeid);
		return this.execute();
	}

	public EopSite getEopSite() {
		return eopSite;
	}

	public void setEopSite(EopSite eopSite) {
		this.eopSite = eopSite;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public String getPreviewpath() {
		return previewpath;
	}

	public void setPreviewpath(String previewpath) {
		this.previewpath = previewpath;
	}

	public String getPreviewBasePath() {
		return previewBasePath;
	}

	public void setPreviewBasePath(String previewBasePath) {
		this.previewBasePath = previewBasePath;
	}

	public Integer getThemeid() {
		return themeid;
	}

	public void setThemeid(Integer themeid) {
		this.themeid = themeid;
	}

	public List<AdminTheme> getListTheme() {
		return listTheme;
	}

	public void setListTheme(List<AdminTheme> listTheme) {
		this.listTheme = listTheme;
	}

	public AdminTheme getAdminTheme() {
		return adminTheme;
	}

	public void setAdminTheme(AdminTheme adminTheme) {
		this.adminTheme = adminTheme;
	}

	public IAdminThemeManager getAdminThemeManager() {
		return adminThemeManager;
	}

	public void setAdminThemeManager(IAdminThemeManager adminThemeManager) {
		this.adminThemeManager = adminThemeManager;
	}

}
