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
package com.enation.order;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.enation.eop.context.EopContext;
import com.enation.eop.core.resource.model.EopSite;
import com.enation.framework.test.SpringTestSupport;
import com.enation.javashop.core.model.statistics.DayAmount;
import com.enation.javashop.core.model.statistics.MonthAmount;
import com.enation.javashop.core.service.IStatisticsManager;

/**
 * 统计功能测试类
 * 
 * @author lzf<br/>
 *         2010-3-9 下午05:54:51<br/>
 *         version 1.0<br/>
 */
public class OrderTest extends SpringTestSupport {

	private ApplicationContext context;
	private IStatisticsManager orderManager;

	@Before
	public void mock() {

		orderManager = getBean("orderManager");
		// mock site
		EopSite site = new EopSite();
		site.setUserid(2);
		site.setId(2);
		EopContext context = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);
	}

	/**
	 * 取得当前日期所在年的 月-销售额 统计
	 */
	@Test
	public void test_statisticsMonth_Amount() {
		List<MonthAmount> list = orderManager.statisticsMonth_Amount();
		for (MonthAmount map : list) {
			String key = map.getMonth();
			System.out.println(key + ":" + map.getAmount());

		}
	}

	/**
	 * 取得指定月份所在年的 月-销售额 统计
	 */
	@Test
	public void test_statisticsMonth_Amount_withInput() {
		List<MonthAmount> list = orderManager.statisticsMonth_Amount("2010-04");
		for (MonthAmount map : list) {
			String key = map.getMonth();
			System.out.println(key + ":" + map.getAmount());

		}
	}

	/**
	 * 取得当前日期所在月的 日-销售额 统计
	 */
	@Test
	public void test_statisticsDay_Amount() {
		List<DayAmount> list = orderManager.statisticsDay_Amount();
		for (DayAmount map : list) {
			String key = map.getDay();
			System.out.println(key + ":" + map.getAmount());

		}
	}

	/**
	 * 取得指定月份的 日-销售额 统计
	 */
	@Test
	public void test_statisticsDay_Amount_withInput() {
		List<DayAmount> list = orderManager.statisticsDay_Amount("2010-04");
		for (DayAmount map : list) {
			String key = map.getDay();
			System.out.println(key + ":" + map.getAmount());

		}
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public IStatisticsManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IStatisticsManager orderManager) {
		this.orderManager = orderManager;
	}

}
