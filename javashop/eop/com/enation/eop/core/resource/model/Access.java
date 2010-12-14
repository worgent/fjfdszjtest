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
package com.enation.eop.core.resource.model;

import java.io.Serializable;

public class Access implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4339848792738875940L;
	private Integer id;
	private String ip;
	private String url; // 具体的url
	private String page; //访问的页面名
	private String area; //访问者地区
	private int access_time; //访问时间
	private int stay_time; //停留时间
	private int point ;//消耗积分
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getAccess_time() {
		return access_time;
	}
	public void setAccess_time(int accessTime) {
		 
		access_time = accessTime;
	}
	public int getStay_time() {
		return stay_time;
	}
	public void setStay_time(int stayTime) {
		stay_time = stayTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
}
