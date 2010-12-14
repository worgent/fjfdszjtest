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
package com.enation.page;

import org.junit.Before;
import org.junit.Test;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.facade.IPageParamJsonGetter;
import com.enation.eop.core.facade.IPagePaser;
import com.enation.eop.core.facade.IPageUpdater;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.impl.facade.PageEditModeWrapper;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.test.SpringTestSupport;

/**
 * 前台页面相关测试类
 * @author kingapex
 * 2010-2-4下午04:01:59
 */
public class PageTest extends SpringTestSupport {
	
	@Before
	public void mock(){
		 UserServiceFactory.isTest=1;
		 
        EopSite site = new EopSite();
        site.setUserid(2);
        site.setId(2);
        site.setThemeid(1);
		EopContext context  = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);		
	}
	
	
	/**
	 * 挂件解析测试
	 */
	@Test
	public void pageParserTest(){
		  String url ="/index.html";
		  IPagePaser pagePaser = this.getBean("facadePagePaser");
		  String content  = pagePaser.pase(url);
		  System.out.println(content);
		  
	}
	
	/**
	 * 编辑模式解析测试
	 */
	@Test
	public void editModeWrapperTest(){
		 String url ="/index.html?mode=yes";
		  IPagePaser pagePaser = this.getBean("facadePagePaser");
		  pagePaser = new PageEditModeWrapper(pagePaser);
		  String content  = pagePaser.pase(url);
		  System.out.println(content);
	}
	
	/**
	 * 页面更新测试
	 */
	@Test
	public void updateTest(){
		String json="["
			+"{'id':'1','type':'goods_list','tag_id':'1','border':'border1'},"
			+"{'id':'2','type':'goods_detail','border':'none'}"
			+"]";
		
		 String url ="/goods-1.html"; //or /goods-1.html?mode=yes
		 IPageUpdater pageUpdater = this.getBean("facadePageUpdater");
		 pageUpdater.update(url, "t$est",json);
		 
		 this.pageParserTest();
	}
	
	
	/**
	 * 页面挂件参数json格式获取测试
	 */
	@Test
	public void paramJsonTest(){
		String url ="/goods-1.html";
		 IPageParamJsonGetter pageParamJsonGetter = getBean("pageParamJsonGetter");
		 String json = pageParamJsonGetter.getJson(url);
		 System.out.println(json);
	}
}
