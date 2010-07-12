/**
 * 
 */
package com.apricot.app.eating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class LoginData {
	public static final String LOGIN_SESSION_KEY = "com.apricot.app.eating.LoginData";

	/**
	 * 
	 */
	public LoginData() {
		// TODO Auto-generated constructor stub
		this.login=false;
		this.menus=new ArrayList();
	}
	private String shopId;
	private String staffId;
	private String shopName;
	private String staffName;
	private String logingTime;
	private String serviceIp;
	private String smsStatus;
	
	private List menus;
	
	public void addMenus(List a){
		if(a!=null) this.menus.addAll(a);
	}
	
	private boolean login;

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
		this.logingTime=DataUtil.getCurrDateTime();
	}

	public String getShopId() {
		return shopId;
	}
	
	public String toString(){
	    return ""+login;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public HashMap toMap() {
		HashMap map = new HashMap();
		
		map.put("service_ip", new String[] { serviceIp });
		map.put("sms_status", new String[] { smsStatus });
		map.put("sf_id", new String[] { shopId });
		map.put("sf_name", new String[] { shopName });
		map.put("staff_id", new String[] { staffId });
		map.put("staff_name", new String[] { staffName });
		
		return map;
	}

	public List getMenus() {
		return menus;
	}

	public String getLogingTime() {
		return logingTime;
	}

	public String getServiceIp() {
		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
}
