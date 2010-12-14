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
package com.enation.javashop.core.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.eop.EopSetting;
import com.enation.eop.context.EopContext;
import com.enation.eop.impl.support.BaseSupport;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.statistics.DayAmount;
import com.enation.javashop.core.model.statistics.MonthAmount;
import com.enation.javashop.core.service.IStatisticsManager;

/**
 * 订单管理的实现类
 * 
 * @author lzf <br/>
 *         2010-3-9 下午01:56:28 <br/>
 *         version 1.0
 */
public class StatisticsManager extends BaseSupport<Order> implements IStatisticsManager {

	
	public List<MonthAmount> statisticsMonth_Amount() {
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy");
		String year = sdfInput.format(new Date());
		String sql = "";
		if(EopSetting.DBTYPE.equals("1")){//是mysql
			sql = "select sum(a.order_amount) as amount, Date_format(FROM_UNIXTIME(a.create_time / 1000),'%Y-%m') as mo from " + this.getTableName("order") + " a where Date_format(FROM_UNIXTIME(a.create_time / 1000),'%Y') = ?  group by mo";
		}else{
			sql = "select sum(a.order_amount) as amount, SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,7) as mo from " + this.getTableName("order") + " a where SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,4) = ?  group by SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,7)";
		}
		List<Map> list = this.daoSupport.queryForList(sql, year);
		List<MonthAmount> target = new ArrayList<MonthAmount>();
		List<String> monthList = getMonthList();
		for(String month:monthList){
			MonthAmount ma = new MonthAmount();
			ma.setMonth(month);
			ma.setAmount(new Double(0));
			for(Map mapdata:list){
				if(mapdata.get("mo").equals(month)){
					ma.setAmount(Double.valueOf(mapdata.get("amount").toString()));
				}
			}
			target.add(ma);
		}
		return target;
	}
	
	
	public List<MonthAmount> statisticsMonth_Amount(String monthinput) {
		String year = monthinput.substring(0,4);
		String sql = "";
		if(EopSetting.DBTYPE.equals("1")){//是mysql
			sql = "select sum(a.order_amount) as amount, Date_format(FROM_UNIXTIME(a.create_time / 1000),'%Y-%m') as mo from " + this.getTableName("order") + " a where Date_format(FROM_UNIXTIME(a.create_time / 1000),'%Y') = ?  group by mo";
		}else{//是oracle
			sql = "select sum(a.order_amount) as amount, SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,7) as mo from " + this.getTableName("order") + " a where SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,4) = ?  group by SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,7)";
		}
		List<Map> list = this.daoSupport.queryForList(sql, year);
		List<MonthAmount> target = new ArrayList<MonthAmount>();
		List<String> monthList = getMonthList(monthinput);
		for(String month:monthList){
			MonthAmount ma = new MonthAmount();
			ma.setMonth(month);
			ma.setAmount(new Double(0));
			for(Map mapdata:list){
				if(mapdata.get("mo").equals(month)){
					ma.setAmount(Double.valueOf(mapdata.get("amount").toString()));
				}
			}
			target.add(ma);
		}
		return target;
	}
	
	
	public List<DayAmount> statisticsDay_Amount() {
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM");
		String year = sdfInput.format(new Date());
		String sql = "";
		if(EopSetting.DBTYPE.equals("1")){//是mysql
			sql = "select sum(a.order_amount) as amount, Date_format(FROM_UNIXTIME(a.create_time / 1000),'%Y-%m-%d') as mo from " + this.getTableName("order") + " a where a.status = 3 and Date_format(FROM_UNIXTIME(a.create_time / 1000),'%Y-%m') = ?  group by mo";
		}else{
			sql = "select sum(a.order_amount) as amount, SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,10) as mo from " + this.getTableName("order") + " a where a.status = 3 and SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,7) = ?  group by SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,10)";
		}
		List<Map> list = this.daoSupport.queryForList(sql, year);
		List<DayAmount> target = new ArrayList<DayAmount>();
		List<String> dayList = getDayList();
		for(String day:dayList){
			DayAmount da = new DayAmount();
			da.setDay(day);
			da.setAmount(new Double(0));
			for(Map mapdata:list){
				if(mapdata.get("mo").equals(day)){
					da.setAmount(Double.valueOf(mapdata.get("amount").toString()));
				}
			}
			target.add(da);
		}
		return target;
	}

	
	public List<DayAmount> statisticsDay_Amount(String monthinput) {
		String sql = "";
		if(EopSetting.DBTYPE.equals("1")){//是mysql
			sql = "select sum(a.order_amount) as amount, Date_format(FROM_UNIXTIME(a.create_time / 1000),'%Y-%m-%d') as mo from " + this.getTableName("order") + " a where a.status = 3 and Date_format(FROM_UNIXTIME(a.create_time / 1000),'%Y-%m') = ?   group by mo";
		}else{
			sql = "select sum(a.order_amount) as amount, SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,10) as mo from " + this.getTableName("order") + " a where a.status = 3 and SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,7) = ?   group by SUBSTR(FROM_UNIXTIME(a.create_time / 1000),1,10)";
		}
		List<Map> list = this.daoSupport.queryForList(sql, monthinput);
		List<DayAmount> target = new ArrayList<DayAmount>();
		List<String> dayList = getDayList(monthinput);
		for(String day:dayList){
			DayAmount da = new DayAmount();
			da.setDay(day);
			da.setAmount(new Double(0));
			for(Map mapdata:list){
				if(mapdata.get("mo").equals(day)){
					da.setAmount(Double.valueOf(mapdata.get("amount").toString()));
				}
			}
			target.add(da);
		}
		return target;
	}

	
	private static List<String> getMonthList(){
		List<String> monthList = new ArrayList<String>();
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy");
		String year = sdfInput.format(new Date());
		DecimalFormat df = new DecimalFormat("00");
		for(int i=1;i<=12;i++){
			monthList.add(year + "-" + df.format(i));
		}
		return monthList;
	}
	
	private static List<String> getMonthList(String monthinput){
		List<String> monthList = new ArrayList<String>();
		String year = monthinput.substring(0,4);
		DecimalFormat df = new DecimalFormat("00");
		for(int i=1;i<=12;i++){
			monthList.add(year + "-" + df.format(i));
		}
		return monthList;
	}
	
	private static List<String> getDayList(){
		//TODO 取得当前月的天 列表
		List<String> dayList = new ArrayList<String>();
		Date date = new Date();
		Calendar cal =Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM");
		String str_month = sdfInput.format(date);
		DecimalFormat df = new DecimalFormat("00");
		int count = days(year, month);
		for(int i=1;i<=count;i++){
			dayList.add(str_month + "-" + df.format(i));
		}
		return dayList;
	}
	
	private static List<String> getDayList(String monthinput){
		//TODO 取得指定月的天 列表
		List<String> dayList = new ArrayList<String>();
		
		Date date =DateUtil.toDate(monthinput+"-01", "yyyy-MM-dd");// new Date(monthinput + "-01");
		 
		Calendar cal =Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String str_month = monthinput;
		DecimalFormat df = new DecimalFormat("00");
		int count = days(year, month);
		for(int i=1;i<=count;i++){
			dayList.add(str_month + "-" + df.format(i));
		}
		return dayList;
	}
	
	public static int days(int year,int month){
		int days = 0;
		if(month!=2){
			switch(month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:days = 31 ;break;
			case 4:
			case 6:
			case 9:
			case 11:days = 30;

			}
		}else{
			if(year%4==0 && year%100!=0 || year%400==0)
				days = 29;
			else  
				days = 28;
		}
		return days;
	}
	
	public static void main(String[] args){
		List<String> list = getDayList("2010-02");
		for(String month:list){
			System.out.println(month);
		}
	}
}
