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
package com.enation.shop.delivery;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.test.SpringTestSupport;
import com.enation.javashop.core.model.DlyCenter;
import com.enation.javashop.core.service.IDlyCenterManager;

/**
 * 发货中心测试类
 * @author lzf<br/>
 * 2010-4-30上午10:27:51<br/>
 * version 1.0
 */
public class DlyCenterTest extends SpringTestSupport {
	
	private IDlyCenterManager dlyCenterManager;
	private IDBRouter shopSaasDBRouter;
	
	@Before
	public void mock(){
		this.dlyCenterManager = this.getBean("dlyCenterManager");
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

		this.jdbcTemplate.execute("drop table if exists js_dly_center_2");
		shopSaasDBRouter.createTable(  "dly_center");

	}
	
	/**
	 * 测试添加
	 */
	@Test
	public void testAdd(){
		
		this.clean();
		DlyCenter dlyCenter = new DlyCenter();
		dlyCenter.setName("易族智汇");
		dlyCenter.setUname("刘先生");
		dlyCenter.setAddress("名佳花园3区51号楼1单元401室");
		dlyCenter.setProvince("北京");
		dlyCenter.setProvince_id(1);
		dlyCenter.setCity("北京市");
		dlyCenter.setCity_id(2);
		dlyCenter.setRegion("昌平区");
		dlyCenter.setRegion_id(15);
		dlyCenter.setZip("102209");
		dlyCenter.setCellphone("010-61750491");
		dlyCenter.setPhone("13331002660");
		dlyCenter.setSex("male");
		dlyCenter.setDisabled("false");
		this.dlyCenterManager.add(dlyCenter);
		
		//断言
		DlyCenter dlyCenterDb = dlyCenterManager.get(1);
		Assert.assertEquals(dlyCenterDb.getRegion(), "昌平区");
		Assert.assertEquals(dlyCenterDb.getAddress(), "名佳花园3区51号楼1单元401室");
		Assert.assertEquals(dlyCenterDb.getDisabled(),"false");
	}
	
	/**
	 * 测试修改
	 */
	@Test
	public void testEdit(){
		this.testAdd(); //模拟数据
		DlyCenter dlyCenter = new DlyCenter();
		dlyCenter.setDly_center_id(1);
		dlyCenter.setAddress("名佳花园4区7号楼1单元401室");
		dlyCenterManager.edit(dlyCenter);
		//断言
		DlyCenter dlyCenterDb = dlyCenterManager.get(1);
		Assert.assertEquals(dlyCenterDb.getRegion(), "昌平区");
		Assert.assertEquals(dlyCenterDb.getAddress(), "名佳花园4区7号楼1单元401室");
		Assert.assertEquals(dlyCenterDb.getDisabled(),"false");
	}
	
	/**
	 * 测试删除
	 */
	@Test
	public void testDelete(){
		
		this.testAdd();
		dlyCenterManager.delete(null);
		
		//断言
		DlyCenter dlyCenterDb = dlyCenterManager.get(1);
		Assert.assertEquals(dlyCenterDb.getRegion(), "昌平区");
		Assert.assertEquals(dlyCenterDb.getAddress(), "名佳花园3区51号楼1单元401室");
		Assert.assertEquals(dlyCenterDb.getDisabled(),"false");
		
		dlyCenterManager.delete(new Integer[]{1});
		int count  = this.jdbcTemplate.queryForInt("select count(0) from js_dly_center_2 where disabled = 'false'");
		Assert.assertEquals(count,0);
		
	}
	
	@Test
	public void listTest(){
		this.testAdd();
		List<DlyCenter> list = dlyCenterManager.list();
		Assert.assertEquals(list.get(0).getRegion(), "昌平区");
		Assert.assertEquals(list.get(0).getAddress(), "名佳花园3区51号楼1单元401室");
		Assert.assertEquals(list.get(0).getDisabled(),"false");
	}

	public IDlyCenterManager getDlyCenterManager() {
		return dlyCenterManager;
	}

	public void setDlyCenterManager(IDlyCenterManager dlyCenterManager) {
		this.dlyCenterManager = dlyCenterManager;
	}
	public IDBRouter getShopSaasDBRouter() {
		return shopSaasDBRouter;
	}
	public void setShopSaasDBRouter(IDBRouter shopSaasDBRouter) {
		this.shopSaasDBRouter = shopSaasDBRouter;
	}
	
	
	

}
