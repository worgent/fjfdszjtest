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
package com.enation.shop.payment;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.eop.core.view.freemarker.FreeMarkerPaser;
import com.enation.framework.plugin.IPlugin;
import com.enation.framework.test.SpringTestSupport;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.service.IPaymentManager;
/**
 * 支付方式测试
 * @author kingapex
 *2010-4-4下午02:47:27
 */
public class PaymentTest extends SpringTestSupport {
	
	private IPaymentManager paymentManager;
	
	@Before
	public void mock(){
		this.paymentManager = this.getBean("paymentManager");
        EopSite site = new EopSite();
        site.setUserid(2);
        site.setId(2);
		EopContext context  = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);		
		FreeMarkerPaser.set(new FreeMarkerPaser());
	}
		
	
	@Test
	public void testList(){
		
		List<PayCfg> list  = this.paymentManager.list();
		assertEquals(list.size(), 2);
		for(PayCfg pay :list){
			System.out.println(pay.getName());
		}
	}
	
	/**
	 * 测试安装支付方式
	 */
	@Test
	public void testAdd(){
		Map<String,String> params = new HashMap<String, String>(); 
		params.put("chnid", "52560956"); 
		params.put("key", "020202029298282"); 
		this.paymentManager.add("财付通(中介担保)", "tenpay_med", "teset", params);
		
	}
	
	
	/**
	 * 测试修改一个支付方式
	 */
	@Test
	public void testEdit(){
		Map<String,String> params = new HashMap<String, String>(); 
		params.put("chnid", "52560956123"); 
		params.put("key", "020202029298282");
		this.paymentManager.edit(3,"财付通(中介担保)", "teset", params);
		params = this.paymentManager.getConfigParams(3);
		Assert.assertEquals("52560956123", params.get("chnid"));
	}
	
	/**
	 * 测试读取支付方式配置参数
	 * 
	 */
	@Test
	public void testGetParams(){
		Map<String,String> params = this.paymentManager.getConfigParams(3);
		Assert.assertEquals("52560956", params.get("chnid"));
	}
	
	
	/**
	 * 测试删除支付方式
	 */
	@Test
	public void testDelete(){
		paymentManager.delete(new Integer[]{1})	;
	}
	
	
	/**
	 * 测试读取支付插件列表
	 */
	@Test
	public void testListPlugins(){
		List<IPlugin> plugins  = this.paymentManager.listAvailablePlugins();
		for(IPlugin plugin:plugins){
			System.out.println(plugin.getName());
		}
	}
	
	
	/**
	 * 测试读取支付插件安装html
	 * 
	 */
	@Test
	public void testGetHtml(){
		String html = paymentManager.getPluginInstallHtml("tenpay_med", 3);
		System.out.println(html);
		 paymentManager.getPluginInstallHtml("tenpay_med", null);
			System.out.println(html);
		
	}
	
	
}
