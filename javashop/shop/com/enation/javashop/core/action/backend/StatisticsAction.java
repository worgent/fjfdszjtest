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
package com.enation.javashop.core.action.backend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.statistics.DayAmount;
import com.enation.javashop.core.model.statistics.MonthAmount;
import com.enation.javashop.core.service.IStatisticsManager;

/**
 * 统计
 * 
 * @author lzf <br/>
 *         2010-3-9 下午03:14:18 <br/>
 *         version 1.0
 */
public class StatisticsAction extends WWAction {
	
	private List<MonthAmount> month_AmountList;
	private List<DayAmount> day_AmountList;
	private IStatisticsManager statisticsManager;
	private String year;
	private String month;
	private int daycount;
	
	public String monthamount(){
		if(year == null || year.equals("")){
			Date date = new Date();
			SimpleDateFormat sdfyear = new SimpleDateFormat("yyyy");
			year = sdfyear.format(date);
			SimpleDateFormat sdfmonth = new SimpleDateFormat("MM");
			month = sdfmonth.format(date);
		}
		month_AmountList = this.statisticsManager.statisticsMonth_Amount(year + "-" + month);
		day_AmountList = this.statisticsManager.statisticsDay_Amount(year + "-" + month);
		daycount = day_AmountList.size();
		return "monthamount";
	}

	public List<MonthAmount> getMonth_AmountList() {
		return month_AmountList;
	}



	public void setMonth_AmountList(List<MonthAmount> monthAmountList) {
		month_AmountList = monthAmountList;
	}

	public List<DayAmount> getDay_AmountList() {
		return day_AmountList;
	}

	public void setDay_AmountList(List<DayAmount> dayAmountList) {
		day_AmountList = dayAmountList;
	}

	public IStatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	public void setStatisticsManager(IStatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDaycount() {
		return daycount;
	}

	public void setDaycount(int daycount) {
		this.daycount = daycount;
	}
	
	

}
