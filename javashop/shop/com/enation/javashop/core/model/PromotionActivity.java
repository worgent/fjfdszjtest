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
 * 促销活动
 * @author kingapex
 *2010-4-15上午11:57:56
 */
public class PromotionActivity {
	private Integer id;	      
	private String  name;	   	
	private int enable;		
	private Long  begin_time;	
	private Long  end_time;	
	private String brief;		
	private int disabled ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Long getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Long beginTime) {
		begin_time = beginTime;
	}
	public Long getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Long endTime) {
		end_time = endTime;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public int getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
	} 	
	
	
}
