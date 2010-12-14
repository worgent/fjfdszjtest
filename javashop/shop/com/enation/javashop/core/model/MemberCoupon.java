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
package com.enation.javashop.core.model;

/**
 * 会员优惠券
 * 
 * @author lzf<br/>
 *         2010-3-23 上午11:12:04<br/>
 *         version 1.0<br/>
 */
public class MemberCoupon implements java.io.Serializable {
	private String memc_code;
	private int cpns_id;
	private int member_id;
	private String memc_gen_orderid;
	private String memc_source;//enum('a','b','c') not null default 'a'
	private String memc_enabled;//enum('true','false') not null default 'true'
	private int memc_used_times;
	private Long memc_gen_time;
	public String getMemc_code() {
		return memc_code;
	}
	public void setMemc_code(String memcCode) {
		memc_code = memcCode;
	}
	public int getCpns_id() {
		return cpns_id;
	}
	public void setCpns_id(int cpnsId) {
		cpns_id = cpnsId;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int memberId) {
		member_id = memberId;
	}
	public String getMemc_gen_orderid() {
		return memc_gen_orderid;
	}
	public void setMemc_gen_orderid(String memcGenOrderid) {
		memc_gen_orderid = memcGenOrderid;
	}
	public String getMemc_source() {
		return memc_source;
	}
	public void setMemc_source(String memcSource) {
		memc_source = memcSource;
	}
	public String getMemc_enabled() {
		return memc_enabled;
	}
	public void setMemc_enabled(String memcEnabled) {
		memc_enabled = memcEnabled;
	}
	public int getMemc_used_times() {
		return memc_used_times;
	}
	public void setMemc_used_times(int memcUsedTimes) {
		memc_used_times = memcUsedTimes;
	}
	public Long getMemc_gen_time() {
		return memc_gen_time;
	}
	public void setMemc_gen_time(Long memcGenTime) {
		memc_gen_time = memcGenTime;
	}
	
}
