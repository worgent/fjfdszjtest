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
package com.enation.shop.order;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.DeliveryItem;
import com.enation.javashop.core.model.PaymentLog;
import com.enation.javashop.core.service.IOrderReportManager;

/**
 * 订单单据测试
 * @author kingapex
 *2010-4-11下午03:26:51
 */
public class OrderReportManagerTest extends OrderFlowTest {
	
	private IOrderReportManager orderReportManager;
	
 
	
	/**
	 * 测试读取收款单
	 */
	@Test
	public void testListPayment(){
		orderReportManager = this.getBean("orderReportManager");
		
		this.testPay();//进行支付
		 
		Page page = orderReportManager.listPayment(1, 20, null); //读取列表-默认排序
		long total  = page.getTotalCount();
		
		//验证正确读取两条数据
		Assert.assertEquals(total, 2);
		
	}
	
	/**
	 * 测试读取退款单
	 */
	@Test
	public void testListRefund(){
		
		orderReportManager = this.getBean("orderReportManager");
		this.testRefund(); //模拟退款数据 
		
		Page page = orderReportManager.listRefund(1, 20, null); //读取列表-默认排序
		long total  = page.getTotalCount();
		
		//验证正确读取两条数据
		Assert.assertEquals(total, 2);
	}
	
	
	/**
	 * 测试读取发货单
	 * 
	 */
	@Test
	public void testListShipping(){
		
		//模拟发货数据
		this.shippingTest2();
		
		orderReportManager = this.getBean("orderReportManager");
		Page page = orderReportManager.listShipping(1, 20, null);
		long total  = page.getTotalCount();
		
		//验证正确读取1条数据
		Assert.assertEquals(total, 1);
		
		
	}

	
	/**
	 * 测试读取退货单
	 * 
	 */
	@Test
	public void testListReturned(){
		
		//模拟退货数据
		testReturned1();
		
		orderReportManager = this.getBean("orderReportManager");
		Page page = orderReportManager.listReturned(1, 20, null);
		long total  = page.getTotalCount();
		
		//验证正确读取1条数据
		Assert.assertEquals(total, 1);
		
	}
	
	/**
	 * 测试读取收款单/退款单详细
	 */
	@Test
	public void testGetPayment(){
		
		orderReportManager = this.getBean("orderReportManager");
		this.testPay();//模拟支付数据
		
		PaymentLog payment  =orderReportManager.getPayment(1);
		//验证此收款单的金额为100
		Assert.assertEquals(true, payment.getMoney().equals(new Double(100)));
		
		try{
			payment  =orderReportManager.getPayment(1030303);
			Assert.fail("不能正常执行，单据不存在");
		}catch(ObjectNotFoundException e){
			
		}
	}

	
	/**
	 * 测试读取发货单/退货单
	 */
	@Test
	public void testGetDelivery(){
		
		orderReportManager = this.getBean("orderReportManager");
		this.shippingTest2(); //模拟发货数据
		
		Delivery delivery = this.orderReportManager.getDelivery(1);
	 
		Assert.assertEquals(true, delivery.getMoney().equals(new Double(10)));
		
		try{
			delivery  =orderReportManager.getDelivery(1030303);
			Assert.fail("不能正常执行，单据不存在");
		}catch(ObjectNotFoundException e){
			
		}
	}
	
	/**
	 *测试读取发货单/退货单 明细列表
	 */
	@Test
	public void testListDelivery(){
		
		orderReportManager = this.getBean("orderReportManager");
		this.shippingTest2(); //模拟发货数据
		List<DeliveryItem>  itemList  = orderReportManager.listDeliveryItem(1);
		
		//验证正常读取了两条数据
		Assert.assertEquals(2,itemList.size());;
	}
	
}
