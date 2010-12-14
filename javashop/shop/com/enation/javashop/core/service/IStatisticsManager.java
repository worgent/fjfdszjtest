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
package com.enation.javashop.core.service;

import java.util.List;

import com.enation.javashop.core.model.statistics.DayAmount;
import com.enation.javashop.core.model.statistics.MonthAmount;

/**
 * 订单管理接口
 * 
 * @author lzf 2010-3-9 上午11:14:20 version 1.0
 */
public interface IStatisticsManager {

	/**
	 * 统计当年的 月-销售额
	 * 
	 * @return
	 */
	public List<MonthAmount> statisticsMonth_Amount();

	/**
	 * 统计指定月份所在的年的 月-销售额
	 * 
	 * @param monthinput
	 * @return
	 */
	public List<MonthAmount> statisticsMonth_Amount(String monthinput);

	/**
	 * 统计当前月的天-销售额
	 * 
	 * @return
	 */
	public List<DayAmount> statisticsDay_Amount();

	/**
	 * 统计指定月份的天-销售额
	 * 
	 * @param monthinput
	 * @return
	 */
	public List<DayAmount> statisticsDay_Amount(String monthinput);

}
