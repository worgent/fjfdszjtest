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
package com.enation.widget;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.test.SpringTestSupport;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.plugin.GoodsPluginBundle;
import com.enation.javashop.core.plugin.IGoodsBaseShowEvent;

/**
 * 网店挂件测试
 * @author kingapex
 * 2010-2-17上午10:30:16
 */
 
public class ShopWidgetTest extends SpringTestSupport{
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
	 

	@Test
	public void goodsDetailTest(){
		
		IWidget detailWidget  = this.getBean("goods_detail");
		
		System.out.println(detailWidget.process(null));
	}
	
	
	/**
	 * 测试基本信息显示插件解析
	 */
	@Test
	public void basePluginTest(){
		IGoodsBaseShowEvent baseShowEvent = this.getBean("shopexGoodsBase");
		String html = baseShowEvent.onBaseShow(null);
		System.out.println(html);
	}
	
	
 
	
	@Test
	public void memeberIndexTest(){
		IWidget detailWidget  = this.getBean("member_order");
		System.out.println(detailWidget.process(null));
	}
	
}
