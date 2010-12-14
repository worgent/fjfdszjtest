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
package com.enation.app.saler.action;

import com.enation.app.saler.service.IProductService;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.dto.SiteDTO;
import com.enation.eop.core.resource.dto.UserDTO;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteAdmin;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.eop.core.resource.model.EopUser;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.core.resource.model.EopUserDetail;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;

/**
 * 用户注册action
 * 
 * @author kingapex
 *         <p>
 *         2009-12-23 下午03:52:47
 *         </p>
 * @version 1.0
 */
public class RegisterAction extends WWAction {

	private String username;
	private String password;
	private String email;

	private IUserManager userManager;
	private IProductService productService;

	public String execute() {

		try {
			UserDTO userDTO = new UserDTO();
			EopUser eopUser = new EopUser();
			eopUser.setUsername(username);
			eopUser.setLinkman(username);
			eopUser.setEmail(email);
			userDTO.setUser(eopUser);

			EopUserDetail eopUserDetail = new EopUserDetail();
			eopUserDetail.setRegdate(System.currentTimeMillis());
			userDTO.setUserDetail(eopUserDetail);

			EopUserAdmin userAdmin = new EopUserAdmin();
			userAdmin.setUsername(username);
			userAdmin.setPassword(StringUtil.md5(password));
			userDTO.setUserAdmin(userAdmin);

			// /////////////
			SiteDTO siteDTO = new SiteDTO();
			EopSite site = new EopSite();
			site.setThemeid(1);
			site.setAdminthemeid(1);// 给新建的站点写入默认的adminTheme，注意：对于其它如Skin等可能也要考虑到相应的情况
			site.setSitename(username + "的默认站点");
			siteDTO.setSite(site);

			EopSiteDomain domain = new EopSiteDomain();
			domain.setDomain(username + ".eop.com");
			siteDTO.setDomain(domain);

			EopSiteAdmin siteAdmin = new EopSiteAdmin();
			siteDTO.setSiteAdmin(siteAdmin);

			// /////////////////////////////////////////////////

			userDTO.setSiteDTO(siteDTO);

			Integer userid = userManager.createUser(userDTO);

			//installService.install(userid, userDTO.getSiteid());
			productService.install(userid, userDTO.getSiteid(), "base");

			return this.SUCCESS;
		} catch (Exception e) {
			this.msgs.add(e.getMessage());
			return this.MESSAGE;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
}
