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
package com.enation.app.saler.widget.product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.saler.service.IProductService;
import com.enation.app.saler.service.InstallUtil;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.EopException;
import com.enation.eop.core.resource.ISiteManager;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.dto.SiteDTO;
import com.enation.eop.core.resource.model.EopProduct;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteAdmin;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.eop.core.resource.model.EopUser;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.EncryptionUtil;

/**
 * 产品安装挂件
 * @author kingapex
 *2010-3-22上午10:56:36
 */
public class ProductInstallWidget extends AbstractWidget {
	private IProductService productService;
	private ISiteManager siteManager;
	private IUserManager userManager;
	private HttpServletRequest  request  ;
	
	protected void config(Map<String, String> params) {

	}

	
	protected void execute(Map<String, String> params) {
		  request  = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		
		
	//	this.setPageFolder("/widgets/product");
		
		String productid= request.getParameter("productid");
		
		if("confirm".equals(action)){
			if(productid==null) throw new RuntimeException("product is null!");
			EopProduct product  = productService.getProduct( productid);
			EopUserAdmin adminUser  = (EopUserAdmin)ThreadContextHolder.getSessionContext().getAttribute("userAdmin");
			String username = adminUser.getUsername();
			if(adminUser.getDefaultsiteid()!=null){
				username=username+"1";
			}
			String str =  adminUser.getUsername()+","+adminUser.getPassword()+","+ new Date().getTime();
			str =EncryptionUtil.authCode(str, "ENCODE");
			this.putData("username", username);
			this.putData("endata", str);
			this.putData("product", product);
			this.setPageName("install");
		}
		
		if("install".equals(action)){
			 this.install(productid);
		
		}
		
		if("getMsg".equals(action)){
			List msgList = (List)ThreadContextHolder.getSessionContext().getAttribute("installMsg");
			this.putData("msgList", msgList);
			this.setPageName("installMsg");
		}
	}
	
	private void  install(String productid){
		 
		try{ 
			InstallUtil.putMessaage("检测安装条件...");
			String domain = request.getParameter("domain");
			domain=domain+".enationsoft.com";
			IUserService userService  = UserServiceFactory.getUserService();
			SiteDTO siteDTO = new SiteDTO();
			
			EopSite site = new EopSite();
			site.setThemeid(1);
			site.setAdminthemeid(1);
			site.setSitename(productid + "新建站点");
			site.setUserid(userService.getCurrentUserId());
			siteDTO.setSite(site);
			
	
			EopSiteDomain siteDomain = new EopSiteDomain();
			siteDomain.setDomain(domain);
			siteDTO.setDomain(siteDomain);
	
			EopSiteAdmin siteAdmin = new EopSiteAdmin();
			siteDTO.setSiteAdmin(siteAdmin);
			
		 
			siteDTO.setUserId(userService.getCurrentUserId());
			siteDTO.setManagerid(userService.getCurrentManagerId());
			Integer siteid = siteManager.add(siteDTO);
			EopSite eopSite = EopContext.getContext().getCurrentSite();
			eopSite.setUserid(userService.getCurrentUserId());
			eopSite.setId(siteid);
			userManager.changeDefaultSite(userService.getCurrentUserId(), userService.getCurrentManagerId(), siteid);
			
	
			
			productService.install(userService.getCurrentUserId(), siteid, "base");
			productService.install(userService.getCurrentUserId(), siteid, productid);
			eopSite.setUserid(1);
			eopSite.setId(1);
			this.putData("json", "{result:0}");
				
		}catch(RuntimeException e){
			e.printStackTrace();
			InstallUtil.putMessaage(e.getMessage());
			this.putData("json", "{result:1,message:'"+e.getMessage()+"'}");
			 
		}
		ThreadContextHolder.getSessionContext().removeAttribute("installMsg");	
		this.setPageName("install_json");
	 
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

 

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
	
	

}
