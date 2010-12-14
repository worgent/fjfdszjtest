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

import java.util.List;
import java.util.Map;

import com.enation.framework.action.WWAction;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.service.IRankManager;

/**
 * 统计-销售量（额）排名
 * 
 * @author lzf<br/>
 *         2010-3-10 上午11:30:40<br/>
 *         version 1.0<br/>
 */
public class RankAction extends WWAction {
	
	private final static int PAGESIZE = 20;  //排名显示的数量
	private IRankManager rankManager;
	private String order;
	private String beginTime;
	private String endTime;
	private List list;
	private Map rankall;
	
	
	public String execute(){
		String condition = "";
		if(beginTime != null && !(beginTime.equals(""))){
			condition += " and orders.create_time > " + DateUtil.toDate(beginTime, "yyyy-MM-dd").getTime();
		}
		if(endTime != null && !(endTime.equals(""))){
			condition += " and orders.create_time <" + DateUtil.toDate(endTime, "yyyy-MM-dd").getTime();
		}
		list = this.rankManager.rank_goods(1, PAGESIZE, condition, order);
		return this.SUCCESS;
	}
	
	public String rankmember(){
		String condition = "";
		if(beginTime != null && !(beginTime.equals(""))){
			condition += " and orders.create_time > " + DateUtil.toDate(beginTime, "yyyy-MM-dd").getTime();
		}
		if(endTime != null && !(endTime.equals(""))){
			condition += " and orders.create_time <" + DateUtil.toDate(endTime, "yyyy-MM-dd").getTime();
		}
		list = this.rankManager.rank_member(1, PAGESIZE, condition, order);
		return "rankmember";
	}
	
	public String rankbuy(){
		list = this.rankManager.rank_buy(1, PAGESIZE, order);
		return "rankbuy";
	}
	
	public String rankall(){
		rankall = this.rankManager.rank_all();
		return "rankall";
	}

	public IRankManager getRankManager() {
		return rankManager;
	}

	public void setRankManager(IRankManager rankManager) {
		this.rankManager = rankManager;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Map getRankall() {
		return rankall;
	}

	public void setRankall(Map rankall) {
		this.rankall = rankall;
	}
	

}
