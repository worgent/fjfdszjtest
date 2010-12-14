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
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.dto.SessionDTO;
import com.enation.eop.core.resource.dto.SiteDTO;
import com.enation.eop.core.resource.dto.UserDTO;
import com.enation.eop.core.resource.model.EopProduct;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteAdmin;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.eop.core.resource.model.EopUser;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.core.resource.model.EopUserDetail;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserContext;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.action.WWAction;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.util.StringUtil;

/**
 * 成品站
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-21 下午04:01:23<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class ProductAction extends WWAction {

	private IProductService productService;
	private EopProduct eopProduct;
	private String order;
	private Integer id;
	private String productid;
	private Integer newuser;
	private String username;
	private String password;
	private String domain;
	private Integer userid;
	private Integer defaultsiteid;
	private IUserManager userManager;
	private ISiteManager siteManager;

	
	public String execute() {
		this.webpage = productService.pageProduct(order, this.getPage(), this .getPageSize());
		return this.SUCCESS;
	}

	public String detail() {
		eopProduct = productService.getProduct(productid);
		return "detail";
	}

	public String install() {
		IUserService userService = UserServiceFactory.getUserService();
		if (!userService.isUserLoggedIn()) {
			return "reglog";
		}
		else {
			productService.install(this.userid, this.defaultsiteid, productid);
			this.json="{result:1,message:'安装成功'}";
			return this.JSON_MESSAGE;
		}
	}
	
	public String registerlogin(){
		if(newuser == 0){//是新注册用户
			try {
				UserDTO userDTO = new UserDTO();
				EopUser eopUser = new EopUser();
				eopUser.setUsername(username);
				eopUser.setLinkman(username);
				userDTO.setUser(eopUser);

				EopUserDetail eopUserDetail = new EopUserDetail();
				userDTO.setUserDetail(eopUserDetail);

				EopUserAdmin userAdmin = new EopUserAdmin();
				userAdmin.setUsername(username);
				userAdmin.setPassword(StringUtil.md5(password));
				userDTO.setUserAdmin(userAdmin);

				// /////////////
				SiteDTO siteDTO = new SiteDTO();
				EopSite site = new EopSite();
				site.setThemeid(1);
				site.setThemepath("dgfc");
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
				
				site.setId(userDTO.getSiteid());
				
				productService.install(userid, userDTO.getSiteid(), "base");
				productService.install(userid, userDTO.getSiteid(), productid);
				return "go";
			} catch (Exception e) {
				this.msgs.add(e.getMessage());
				return this.MESSAGE;
			}
		}else{//是老用户
			SessionDTO sessionDTO = userManager.login_bysystem(username, password);
			//Integer siteid  = EopContext.getContext().getCurrentSite().getId();
			SiteDTO siteDTO = new SiteDTO();
			
			EopSite site = new EopSite();
			site.setThemeid(1);
			site.setAdminthemeid(1);// 给新建的站点写入默认的adminTheme，注意：对于其它如Skin等可能也要考虑到相应的情况
			site.setSitename(productid + "新建站点");
			site.setUserid(sessionDTO.getUserid());
			siteDTO.setSite(site);
			

			EopSiteDomain siteDomain = new EopSiteDomain();
			siteDomain.setDomain(domain);
			siteDTO.setDomain(siteDomain);

			EopSiteAdmin siteAdmin = new EopSiteAdmin();
			siteDTO.setSiteAdmin(siteAdmin);
			
			//将用户信息写入session，注意：此时对应的域名是www.eop.com
			WebSessionContext<UserContext> sessonContext = ThreadContextHolder
			.getSessionContext();	
			UserContext userContext = new UserContext(sessionDTO.getUserid(),
					sessionDTO.getDefaultsiteid(), sessionDTO.getManagerid());
			sessonContext.setAttribute(UserContext.CONTEXT_KEY, userContext);
			
			siteDTO.setUserId(sessionDTO.getUserid());
			siteDTO.setManagerid(sessionDTO.getManagerid());
			Integer siteid = siteManager.add(siteDTO);
			
			
			//切换当前站点的上 下文为安装完的站点
			site.setId(siteid);
			productService.install(sessionDTO.getUserid(), siteid, "base");
			productService.install(sessionDTO.getUserid(), siteid, productid);
			return this.SUCCESS;
		}
		
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EopProduct getEopProduct() {
		return eopProduct;
	}

	public void setEopProduct(EopProduct eopProduct) {
		this.eopProduct = eopProduct;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public Integer getNewuser() {
		return newuser;
	}

	public void setNewuser(Integer newuser) {
		this.newuser = newuser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
}
