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
package com.enation.shop.cart;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.test.SpringTestSupport;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cart;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.model.support.CartItem;
import com.enation.javashop.core.service.ICartManager;
import com.enation.javashop.core.service.IPromotionManager;
import com.enation.shop.promotion.PromotionTest;
/**
 * 购物车测试
 * @author kingapex
 *2010-3-25上午10:06:03
 */
public class CartTest extends SpringTestSupport {
	private ICartManager cartManager ;
	private String sessionid = "AC95B4420D5CE0C61BC98F99E6700B74";
	
	@Before
	public void mock(){
		cartManager = this.getBean("cartManager");
		
        EopSite site = new EopSite();
        site.setUserid(2);
        site.setId(2);
		EopContext context  = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);		
	}
	
	/**
	 * 测试清空购物车
	 */
	@Test
	public void testClean(){
		cartManager.clean(sessionid);
		List list =cartManager.listGoods(sessionid);
		assertEquals(list.size(), 0);
	}
	
	/**
	 * 测试向购物车添加一项
	 */
	@Test
	public void testAdd(){
		cartManager.clean(sessionid);
		Cart cart = new Cart();
		cart.setProduct_id(3);
		cart.setSession_id(sessionid);
		cart.setNum(1);
		cartManager.add(cart);
	}
	
	
	/**
	 * 测试添加赠品
	 */
	@Test
	public void testAddGift(){
		cartManager.clean(sessionid);
		Cart cart = new Cart();
		cart.setProduct_id(1);
		cart.setSession_id(sessionid);
		cart.setNum(1);
		cart.setItemtype(2);
		cartManager.add(cart);		
	}
	
	
	/**
	 * 测试读取赠品列表
	 */
	@Test
	public void testListGift(){
		//testAddGift();
		List<CartItem> list  = cartManager.listGift(sessionid);
		
		for(CartItem item:list){
			System.out.println("name["+item.getName()+"]- price["+ item.getPrice()+"]");
		}
	}
	
	
	
	/**
	 * 测试读取购物车中项列表
	 */
	@Test
	public void testList(){
		this.testAdd();
		List list =cartManager.listGoods(sessionid);
		assertEquals(list.size(), 1);
		
	}
	
	
	/**
	 * 测试带有优惠规则的读取
	 */
	@Test
	public void testListWithPmt(){
		PromotionTest promotionTest = new PromotionTest();
		promotionTest.setup();
		promotionTest.mock();
		promotionTest.testAddDiscount();
		promotionTest.testAddDiscount2();
		
		cartManager.clean(sessionid);
		Cart cart = new Cart();
		cart.setProduct_id(2); //goodsid:1
		cart.setSession_id(sessionid); 
		cart.setNum(1);
		cartManager.add(cart);

		Cart cart1 = new Cart();
		cart1.setProduct_id(11); //goods_id:3
		cart1.setSession_id(sessionid);
		cart1.setNum(2);
		cartManager.add(cart1);
		
		List<CartItem> list =cartManager.listGoods(sessionid);
		for(CartItem item :list){
			System.out.println("商品"+item.getName() +"price:["+item.getPrice()+"]"+"-coupPrice["+item.getCoupPrice()+"]");
			System.out.println("----------------------");
			List<Promotion> pmtList  =  item.getPmtList();
			for(Promotion pmt:pmtList){
				System.out.println(pmt.getPmt_describe());
			}
			System.out.println("\n\n");
		}
		
	}
	
	
	
	/**
	 * 测试更新购买数量
	 */
	@Test
	public void testUpdateNum(){
		List list =cartManager.listGoods(sessionid);
		assertEquals(list.size(), 1);
		cartManager.updateNum(sessionid, ( (CartItem)list.get(0) ).getId(),2);
		 list =cartManager.listGoods(sessionid);
		 assertEquals(list.size(), 1);
		 assertEquals( ( (CartItem)list.get(0) ).getNum(),2);
			
	}
	
	/**
	 * 测试删除购物车中的一项
	 */
	@Test
	public void testDelete(){
		
		List list =cartManager.listGoods(sessionid);
		assertEquals(list.size(), 1);
		cartManager.delete(sessionid, ( (CartItem)list.get(0) ).getId());
		list =cartManager.listGoods(sessionid);
		assertEquals(list.size(), 0);
	}
	
	
	
	/**
	 * 测试计算购物总价
	 */
	@Test 
	public void testCountPrice(){
		cartManager.clean(sessionid);
		Cart cart = new Cart();
		cart.setProduct_id(2);
		cart.setSession_id(sessionid);
		cart.setNum(1);
		cartManager.add(cart);

		Cart cart1 = new Cart();
		cart1.setProduct_id(11);
		cart1.setSession_id(sessionid);
		cart1.setNum(2);
		cartManager.add(cart1);
		
		Double price  = cartManager.countGoodsTotal(sessionid);
		assertTrue(price.doubleValue()==440d);
	}
	

	
	/**
	 * 测试计算购物车中商品的重量
	 */
	@Test
	public void testCountWeight(){
		cartManager.clean(sessionid);
		Cart cart = new Cart();
		cart.setProduct_id(2);
		cart.setSession_id(sessionid);
		cart.setNum(1);
		cartManager.add(cart);

		Cart cart1 = new Cart();
		cart1.setProduct_id(11);
		cart1.setSession_id(sessionid);
		cart1.setNum(2);
		cartManager.add(cart1);
		Double weight = cartManager.countGoodsWeight(sessionid);
		assertTrue(weight.doubleValue()==3000);
	}
	
	@Test
	public void t(){
		System.out.println(StringUtil.md5("admin"));
	}
}
