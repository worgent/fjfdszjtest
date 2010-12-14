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

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.model.EopUser;
import com.enation.eop.utils.UploadUtil;
import com.enation.framework.action.WWAction;

/**
 * User基本信息维护
 * 
 * @author lzf
 *         <p>
 *         created_time 2009-12-4 上午10:37:58
 *         </p>
 * @version 1.0
 */
public class BaseInfoAction extends WWAction {

	private EopUser eopUser;
	private Integer userid;

	private IUserManager userManager;
	private File cologo;		
	public File getCologo() {
		return cologo;
	}

	public void setCologo(File cologo) {
		this.cologo = cologo;
	}

	public String getCologoFileName() {
		return cologoFileName;
	}

	public void setCologoFileName(String cologoFileName) {
		this.cologoFileName = cologoFileName;
	}

	private String cologoFileName;
	private File license;		//营业执照
	private String licenseFileName;

	public File getLicense() {
		return license;
	}

	public void setLicense(File license) {
		this.license = license;
	}

	public String getLicenseFileName() {
		return licenseFileName;
	}

	public void setLicenseFileName(String licenseFileName) {
		this.licenseFileName = licenseFileName;
	}
 

	public EopUser getEopUser() {
		return eopUser;
	}

	public void setEopUser(EopUser eopUser) {
		this.eopUser = eopUser;
	}

 
	
	public String execute() throws Exception {
		this.userid =EopContext.getContext().getCurrentSite().getUserid();
		eopUser = userManager.get(userid);
		return "input";
	}

	public String save() throws Exception {

		
		try {
			if (cologo != null) {
				String logoPath = UploadUtil.upload(cologo, cologoFileName,
						"user");
				eopUser.setLogofile(logoPath);

			}

			if (license != null) {

				String licensePath = UploadUtil.upload(license,
						licenseFileName, "user");
				eopUser.setLicensefile(licensePath);

			}

			this.userManager.edit(eopUser);
			this.msgs.add("修改成功");
			
		} catch (RuntimeException e) {
			this.msgs.add(e.getMessage());
		}
		this.urls.put("用户信息页面", "baseInfo.do");
		return "message";
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	
}
