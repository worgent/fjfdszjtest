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
 * 发货中心
 * 
 * @author lzf<br/>
 *         2010-4-30上午10:02:28<br/>
 *         version 1.0
 */
public class DlyCenter implements java.io.Serializable {
	private Integer dly_center_id;
	private String name;
	private String address;
	private String province;
	private String city;
	private String region;
	private Integer province_id;
	private Integer city_id;
	private Integer region_id;
	private String zip;
	private String phone;
	private String uname;
	private String cellphone;
	private String sex; //enum('male','famale') default NULL,
	private String memo;
	private String disabled;//enum('true','false') not null default 'false',
	public Integer getDly_center_id() {
		return dly_center_id;
	}
	public void setDly_center_id(Integer dlyCenterId) {
		dly_center_id = dlyCenterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public Integer getProvince_id() {
		return province_id;
	}
	public void setProvince_id(Integer provinceId) {
		province_id = provinceId;
	}
	public Integer getCity_id() {
		return city_id;
	}
	public void setCity_id(Integer cityId) {
		city_id = cityId;
	}
	public Integer getRegion_id() {
		return region_id;
	}
	public void setRegion_id(Integer regionId) {
		region_id = regionId;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
}

