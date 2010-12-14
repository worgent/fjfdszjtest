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

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.facade.widget.IWidgetParamParser;
import com.enation.eop.core.facade.widget.IWidgetParamUpdater;
import com.enation.eop.core.facade.widget.WidgetXmlUtil;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.test.SpringTestSupport;

public class XmlWidgetParamTest extends SpringTestSupport{
	
	
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
	 * xml 式的挂件解析器测试
	 * 测试读取当前会话、当前url中的url
	 */
	@Test
	public void parseTest(){
		
		IWidgetParamParser pp = this.getBean("widgetParamParser");
		Map<String,Map<String,Map<String,String>>> widgets = pp.parse();
	    Map<String,Map<String,String>> page = widgets.get("/index.html");
	    Map<String,String> param = page.get("1");
	    String type = param.get("type");
		Assert.assertEquals(type, "shop_menu");
		
	}
	
	/**
	 * 挂件参数保存测试
	 */
	@Test
	public void saveTest(){
		IWidgetParamUpdater paramUpdater = this.getBean("widgetParamUpdater");
		
		String json="["
			+"{'id':'1','type':'goods_list','tag_id':'4','border':'border1'},"
			+"{'id':'2','type':'goods_detail','border':'border2'}"
			+"]";
		List<Map<String,String>> mapList= WidgetXmlUtil.jsonToMapList(json);			
		paramUpdater.update("/index.html", mapList);
		
		//断言
		IWidgetParamParser pp = this.getBean("widgetParamParser");
		Map<String,Map<String,Map<String,String>>> widgets = pp.parse();
	    Map<String,Map<String,String>> page = widgets.get("/index.html");
	    Map<String,String> param = page.get("1");
	    String type = param.get("type");
		Assert.assertEquals(type, "goods_list");		
	}
}
