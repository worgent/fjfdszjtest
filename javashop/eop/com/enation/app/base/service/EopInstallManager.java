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
package com.enation.app.base.service;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.enation.app.saler.service.IProductService;
import com.enation.eop.context.ConnectType;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.IAppManager;
import com.enation.eop.core.resource.IUserManager;
import com.enation.eop.core.resource.dto.SiteDTO;
import com.enation.eop.core.resource.dto.UserDTO;
import com.enation.eop.core.resource.model.EopApp;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.resource.model.EopSiteAdmin;
import com.enation.eop.core.resource.model.EopSiteDomain;
import com.enation.eop.core.resource.model.EopUser;
import com.enation.eop.core.resource.model.EopUserAdmin;
import com.enation.eop.core.resource.model.EopUserDetail;
import com.enation.framework.database.ISqlFileExecutor;
import com.enation.framework.util.StringUtil;

public class EopInstallManager {
	private JdbcTemplate jdbcTemplate;
	private IAppManager appManager;
	private ApplicationContext context;
	private  ISqlFileExecutor sqlFileExecutor;
	private IProductService productService;
	private IUserManager userManager ;
	public void install(String username,String password,String domain,String productid){
		
		EopSite site  = new EopSite();
		site.setUserid(1);
		site.setId(1);
		EopContext context = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);
		
		this.createDatabase();
		this.installApp();
		this.installUser( username, password, domain,productid);
	//	this.installThemeUri();
		this.addProducts();
	}
	

	

	/*
	 * 创建数据库
	 */	
	private void createDatabase(){
	
	//	String sql  ="drop database if exists `eop`";
	//	this.jdbcTemplate.execute(sql);
	//	this.jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS `eop` DEFAULT CHARACTER SET UTF8");
	//	this.jdbcTemplate.execute("use eop");
		sqlFileExecutor.execute("file:com/enation/eop/eop_empty.sql");
		sqlFileExecutor.execute("file:com/enation/javashop/javashop.sql");
		sqlFileExecutor.execute("file:com/enation/cms/cms.sql");
	}
	
	private void addProducts(){ 
		//this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('bookstore', '书店','http://localhost:8080/eop_static/user/1/1/attachment/product/1.jpg');");
		this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('moonbasa', '时尚内衣','http://static.eop.com:8080/eop_static/user/1/1/attachment/product/2.png');");
		this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('it360', '新潮电子','http://static.eop.com:8080/eop_static/user/1/1/attachment/product/3.png');");
	}
	
	/*
	 * 接入应用
	 */
	private void installApp(){
//		String sql ="truncate table eop_app";
//		this.jdbcTemplate.execute(sql);
//		

		EopApp appEop = new EopApp();
		appEop.setApp_name("eop");
		appEop.setAppid("eop");
		appEop.setAuthor("enaiton");
		appEop.setAuthorizationcode("752513");
		appEop.setPath("/eop");
		appEop.setDeployment(ConnectType.local);
		appEop.setDescript("eop应用"); 		
		
		
		EopApp appCore = new EopApp();
		appCore.setApp_name("core");
		appCore.setAppid("core");
		appCore.setAuthor("enaiton");
		appCore.setAuthorizationcode("752513");
		appCore.setPath("/core");
		appCore.setDeployment(ConnectType.local);
		appCore.setDescript("核心应用"); 
		

		EopApp appShop = new EopApp();
		appShop.setApp_name("shop");
		appShop.setAppid("shop");
		appShop.setAuthor("enaiton");
		appShop.setAuthorizationcode("752513");
		appShop.setPath("/shop");
		appShop.setDeployment(ConnectType.local);
		appShop.setInstalluri("/app!install.do");
		appShop.setDescript("shop应用"); 	
		
		EopApp appCms = new EopApp();
		appCms.setApp_name("cms");
		appCms.setAppid("cms");
		appCms.setAuthor("enaiton");
		appCms.setAuthorizationcode("752513");
		appCms.setPath("/cms");
		appCms.setDeployment(ConnectType.local);
		appCms.setInstalluri("/app!install.do");
		appCms.setDescript("shop应用"); 		
		this.appManager.add(appEop);
		this.appManager.add(appCore);
		this.appManager.add(appShop);
		this.appManager.add(appCms);
	}
	

	/*
	 * 初始化EOP 平台的用户
	 */
	public void installUser(String username,String password,String domain,String productid){
		
		UserDTO userDTO = new UserDTO();
		
		EopUser eopUser = new EopUser();
		eopUser.setAddress("单机版用户");
		eopUser.setUsername("单机版用户");
		eopUser.setLinkman("单机版用户");
		eopUser.setUsertype(1);
		userDTO.setUser(eopUser);
		
		EopUserDetail eopUserDetail = new EopUserDetail();
		userDTO.setUserDetail(eopUserDetail);
		
		EopUserAdmin userAdmin = new EopUserAdmin();
		userAdmin.setUsername(username);
		userAdmin.setPassword(StringUtil.md5(password));
		userDTO.setUserAdmin(userAdmin);
		
		///////////////
		SiteDTO siteDTO = new SiteDTO();
		EopSite site = new EopSite();
		site.setSitename("javashop");
		siteDTO.setSite(site);
		
		EopSiteDomain sitedomain = new EopSiteDomain();
		sitedomain.setDomain(domain);
		siteDTO.setDomain(sitedomain);
		
		EopSiteAdmin siteAdmin = new EopSiteAdmin();
		siteDTO.setSiteAdmin(siteAdmin);
		
		userDTO.setSiteDTO(siteDTO);		
		
		Integer userid = userManager.createUser(userDTO);
		productService.install(userid, userDTO.getSiteid(), "base");
		productService.install(userid, userDTO.getSiteid(), productid);
		
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public IAppManager getAppManager() {
		return appManager;
	}
	public void setAppManager(IAppManager appManager) {
		this.appManager = appManager;
	}
	public ApplicationContext getContext() {
		return context;
	}
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
 
	public IProductService getProductService() {
		return productService;
	}
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}




	public ISqlFileExecutor getSqlFileExecutor() {
		return sqlFileExecutor;
	}




	public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
		this.sqlFileExecutor = sqlFileExecutor;
	}




	public IUserManager getUserManager() {
		return userManager;
	}




	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}
	
	
	
	
}
