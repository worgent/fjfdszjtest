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
package com.enation.shop.promotion;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.database.Page;
import com.enation.framework.test.SpringTestSupport;
import com.enation.javashop.core.model.PromotionActivity;
import com.enation.javashop.core.service.IPromotionActivityManager;

/**
 * 促销活动管理类测试
 * @author kingapex
 *2010-4-15下午12:16:10
 */
public class PromotionActivityTest extends SpringTestSupport {
	private IPromotionActivityManager promotionActivityManager;
	protected IDBRouter shopSaasDBRouter;
	@Before
	public void mock(){
		promotionActivityManager = this.getBean("promotionActivityManager");
		shopSaasDBRouter = this.getBean("shopSaasDBRouter");
        EopSite site = new EopSite();
        site.setUserid(2);
        site.setId(2);
		EopContext context  = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);		

	}
	
	/**
	 * 清除数据并建立表结构
	 */
	private void clean(){

		this.jdbcTemplate.execute("drop table if exists js_promotion_activity_2");
		shopSaasDBRouter.createTable( "promotion_activity");

	}
	
	
	/**
	 * 测试添加
	 */
	@Test
	public void testAdd(){
		this.clean();
		PromotionActivity  activity = new PromotionActivity();
		activity.setName("test");
		activity.setEnable(1);
		activity.setBegin_time( new Date().getTime());
		activity.setEnd_time(new Date().getTime()) ;
		activity.setBrief("test");
		promotionActivityManager.add(activity);
		int count  = this.jdbcTemplate.queryForInt("select count(0) from js_promotion_activity_2 ");
		/**
		 * 验证数据库记录为1
		 */
		Assert.assertEquals(count, 1);
		
		try{
			PromotionActivity  activity1 = new PromotionActivity();
			promotionActivityManager.add(activity1);
			Assert.fail("参数错误，不应正常执行");
		}catch(RuntimeException e){
			 
		}

		
		try{
			PromotionActivity  activity3 = new PromotionActivity();
			activity3.setName("abc");
			promotionActivityManager.add(activity3);
			Assert.fail("参数错误，不应正常执行");
		}catch(RuntimeException e){
			 
		}		
		
		try{
			PromotionActivity  activity2=null;
			promotionActivityManager.add(activity2);
			Assert.fail("参数错误，不应正常执行");
		}catch(RuntimeException e){
			 
		}
		
	}
	
	
	/**
	 * 测试读取
	 */
	@Test
	public void testGet(){
		this.testAdd(); //模拟数据
		PromotionActivity activity =  promotionActivityManager.get(1);
		Assert.assertEquals(activity.getName(), "test");
		
		try{
			activity =  promotionActivityManager.get(132323);
			Assert.fail("未正常抛出未找到记录异常");
		}catch(ObjectNotFoundException e){
			
		}
	}
	
	/**
	 * 测试修改
	 */
	@Test
	public void testEdit(){
		PromotionActivity  activity = this.promotionActivityManager.get(1);
		activity.setId(1);
		activity.setName("test1");
		activity.setBrief("test1");
		promotionActivityManager.edit(activity);
		PromotionActivity activityDb =   this.promotionActivityManager.get(1);
		Assert.assertEquals(activityDb.getName(), "test1");
		
		PromotionActivity  activity1 = new PromotionActivity();
	 
		activity1.setName("test2");
		activity1.setBrief("test2");
		try{
			promotionActivityManager.edit(activity1);
			Assert.fail("参数错误，不应正常执行");
		}catch (RuntimeException e) {
			
		}
		
		
	}
	
	/**
	 * 测试读取列表
	 */
	@Test
	public void testList(){
		this.testAdd();
		Page page  = this.promotionActivityManager.list(1, 20);
		Assert.assertEquals(page.getTotalCount(), 1);
		Assert.assertEquals( ((List)page.getResult()).size()  , 1);
	}
	
	
	/**
	 * 测试删除
	 */
	@Test
	public void testDelete(){
		this.testAdd();
		PromotionTest promotionTest =new PromotionTest();
		promotionTest.setup();
		promotionTest.mock();
		promotionTest.testAddDiscount();
		
		promotionActivityManager.delete( null );	
		promotionActivityManager.delete(new Integer[]{});	
		promotionActivityManager.delete(new Integer[]{1});	
		int count  = this.jdbcTemplate.queryForInt("select count(0) from js_promotion_activity_2 ");
		Assert.assertEquals(count, 0);

		//验证规则 已清除		
		 count =  this.jdbcTemplate.queryForInt("select count(0) from js_promotion_2");
		Assert.assertEquals(count, 0);
		
		//验证关联会员 已清除
		count =  this.jdbcTemplate.queryForInt("select count(0) from js_pmt_member_lv_2");
		Assert.assertEquals(count,0);
		
		//验证关联 商品 已清除
		count =  this.jdbcTemplate.queryForInt("select count(0) from js_pmt_goods_2");
		Assert.assertEquals(count,0);			
	}
	
}
