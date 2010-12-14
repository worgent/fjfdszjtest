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
package com.enation.db;

import org.junit.Before;
import org.junit.Test;

import com.enation.app.saler.service.IProductService;
import com.enation.app.saler.service.impl.ProfileCreator;
import com.enation.app.saler.service.impl.SqlExportService;
import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.test.SpringTestSupport;


public class ExportSqlTest extends SpringTestSupport {
	@Before
	public void mock() {
		EopSite site = new EopSite();
		site.setUserid(1);
		site.setId(1);
		site.setThemeid(1);
		EopContext context = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);
	}
	
	@Test
	public void testImport(){
		IProductService productService = this.getBean("productService");	
		productService.imported( "aaa","d:/test123.zip");
	}
	
	@Test
	public void testExport(){
		IProductService productService = this.getBean("productService");	
		productService.export("test123", true, true, true,true);
	}
	
	
	@Test
	public void testExportSql(){
		SqlExportService sqlExportService = this.getBean("sqlExportService");
		String sql  =sqlExportService.dumpSql();
		System.out.println(sql);
		
	}
	
	//@Test
	public void tesetCreateProfile(){
		ProfileCreator profileCreator = this.getBean("profileCreator");
		profileCreator.createProfile("d:/a.xml");
	}
}
