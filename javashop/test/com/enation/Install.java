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
package com.enation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.database.ISqlFileExecutor;
import com.enation.framework.util.StringUtil;

/**
 * 安装Eop平台
 * @author kingapex
 * <p>2009-12-23 下午05:25:10</p>
 * @version 1.0
 */
public class Install {
	
	private JdbcTemplate jdbcTemplate;
	private IAppManager appManager;
	private ApplicationContext context;
	private  ISqlFileExecutor sqlFilExecutor;
	private IProductService productService;
	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext(
				new String[] { "spring/*.xml" });
		jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
		sqlFilExecutor =(ISqlFileExecutor)context.getBean("sqlFileExecutor");
		appManager =  SpringContextHolder.getBean("appManager");
		productService = (IProductService)context.getBean("productService");
		
		
		EopSite site  = new EopSite();
		site.setUserid(1);
		site.setId(1);
		EopContext context = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);

	}
	
	
	@Test
	public void install(){
		this.createDatabase();
		this.installApp();
		this.installUser();
	//	this.installThemeUri();
		this.addProducts();
	}

	/*
	 * 创建数据库
	 */	
	private void createDatabase(){
	
		String sql  ="drop database if exists `eop`";
		this.jdbcTemplate.execute(sql);
		this.jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS `eop` DEFAULT CHARACTER SET UTF8");
		this.jdbcTemplate.execute("use eop");
		sqlFilExecutor.execute("file:com/enation/eop/eop_empty.sql");
		sqlFilExecutor.execute("file:com/enation/javashop/javashop.sql");
		sqlFilExecutor.execute("file:com/enation/cms/cms.sql");
	}
	
	private void addProducts(){ 
		//this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('bookstore', '书店','http://static.eop.com:8080/eop_static/user/1/1/attachment/product/1.jpg');");
		this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('moonbasa', '时尚内衣','http://static.enationsoft.com/user/1/1/attachment/product/neiyi.jpg');");
		this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('it360', '新潮电子','http://static.enationsoft.com/user/1/1/attachment/product/it.jpg');");
		//this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('vancl', '时尚服饰','http://static.enationsoft.com/user/1/1/attachment/product/fushi.jpg');");
		//this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('tongrentang', '药店解决方案','http://static.enationsoft.com/user/1/1/attachment/product/yaodian.jpg');");
		this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('jewel', '首饰珠宝','http://static.enationsoft.com/user/1/1/attachment/product/zhubao.jpg');");
		this.jdbcTemplate.execute("insert into eop_product (productid, product_name,preview) values('company', '公司展示','http://static.enationsoft.com/user/1/1/attachment/product/gongsi.png');");
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
		appCore.setDescript("这是核心应用"); 
		

		EopApp appShop = new EopApp();
		appShop.setApp_name("shop");
		appShop.setAppid("shop");
		appShop.setAuthor("enaiton");
		appShop.setAuthorizationcode("752513");
		appShop.setPath("/shop");
		appShop.setDeployment(ConnectType.local);
		appShop.setInstalluri("/app!install.do");
		appShop.setDescript("这是javashop应用"); 	
//

		EopApp appCms = new EopApp();
		appCms.setApp_name("cms");
		appCms.setAppid("cms");
		appCms.setAuthor("enaiton");
		appCms.setAuthorizationcode("752513");
		appCms.setPath("/cms");
		appCms.setDeployment(ConnectType.local);
		appCms.setInstalluri("/app!install.do");
		appCms.setDescript("这是cms应用"); 	
		
//		EopApp appTaobao = new EopApp();
//		appTaobao.setApp_name("taobao");
//		appTaobao.setAppid("taobao");
//		appTaobao.setAuthor("enaiton");
//		appTaobao.setAuthorizationcode("752513");
//		appTaobao.setPath("/taobao");
//		appTaobao.setDeployment(ConnectType.local);
//		appTaobao.setInstalluri("/app!install.do");
//		appTaobao.setDescript("这是Taobao应用"); 
		
		this.appManager.add(appEop);
		this.appManager.add(appCore);
		this.appManager.add(appShop);
		this.appManager.add(appCms);
		 
	}
	

	/*
	 * 初始化EOP 平台的用户
	 */
	public void installUser(){
		
//		String sql ="truncate table eop_user";
//		this.jdbcTemplate.execute(sql);
//		
//		sql ="truncate table eop_site";
//		this.jdbcTemplate.execute(sql);
//		
//		sql ="truncate table eop_siteapp";
//		this.jdbcTemplate.execute(sql);
//		
//		sql ="truncate table eop_sitedomain";
//		this.jdbcTemplate.execute(sql);
//		
//		sql ="truncate table eop_siteadmin";
//		this.jdbcTemplate.execute(sql);
//		
//		sql ="truncate table eop_useradmin";
//		this.jdbcTemplate.execute(sql);
//		
//		sql ="truncate table eop_userdetail";
//		this.jdbcTemplate.execute(sql);
//		
		UserDTO userDTO = new UserDTO();
		
		EopUser eopUser = new EopUser();
		eopUser.setAddress("北京市昌平区");
		eopUser.setUsername("易族智汇（北京）科技有限公司");
		eopUser.setLinkman("刘志毅");
		eopUser.setTel("01061750491");
		eopUser.setEmail("enation@126.com");
		eopUser.setUsertype(1);
		userDTO.setUser(eopUser);
		
		EopUserDetail eopUserDetail = new EopUserDetail();
		eopUserDetail.setBussinessscope("软件制作、维护服务");
		userDTO.setUserDetail(eopUserDetail);
		
		EopUserAdmin userAdmin = new EopUserAdmin();
		userAdmin.setUsername("admin");
		userAdmin.setPassword(StringUtil.md5("admin"));
		userDTO.setUserAdmin(userAdmin);
		
		///////////////
		SiteDTO siteDTO = new SiteDTO();
		EopSite site = new EopSite();
		site.setThemeid(1);
		site.setThemepath("default");
		site.setAdminthemeid(1);
		site.setSitename("EOP平台");
		siteDTO.setSite(site);
		
		EopSiteDomain domain = new EopSiteDomain();
		domain.setDomain("www.enationsoft.com");
		siteDTO.setDomain(domain);
		
		EopSiteAdmin siteAdmin = new EopSiteAdmin();
		siteDTO.setSiteAdmin(siteAdmin);
		
		///////////////////////////////////////////////////
		
		userDTO.setSiteDTO(siteDTO);
		
		IUserManager userManager = (IUserManager)context.getBean("userManager");
		Integer userid = userManager.createUser(userDTO);
//		InstallService installService=  new InstallService();
//		installService.setJdbcTemplate(this.jdbcTemplate);	//	lzy	add
//		installService.install(userid, userDTO.getSiteid());
		productService.install(userid, userDTO.getSiteid(), "base");
		productService.install(userid, userDTO.getSiteid(), "eopsaler");
		System.out.println(userid);
		Assert.assertEquals(2, 2);
		
	}
	
 
	
	
	
}
