package com.apricot.app.pda;

public class dining_set_info {
	public String getSF_ID() {
		return SF_ID;
	}
	public void setSF_ID(String sf_id) {
		SF_ID = sf_id;
	}
	public String getSET_NO() {
		return SET_NO;
	}
	public void setSET_NO(String set_no) {
		SET_NO = set_no;
	}
	public String getDINING_FLOOR() {
		return DINING_FLOOR;
	}
	public void setDINING_FLOOR(String dining_floor) {
		DINING_FLOOR = dining_floor;
	}
	public String getBALCONY_CODE() {
		return BALCONY_CODE;
	}
	public void setBALCONY_CODE(String balcony_code) {
		BALCONY_CODE = balcony_code;
	}
	public String getBELONG_TO() {
		return BELONG_TO;
	}
	public void setBELONG_TO(String belong_to) {
		BELONG_TO = belong_to;
	}
	public String getBALCONY_NAME() {
		return BALCONY_NAME;
	}
	public void setBALCONY_NAME(String balcony_name) {
		BALCONY_NAME = balcony_name;
	}
	public String getSET_STATUS() {
		return SET_STATUS;
	}
	public void setSET_STATUS(String set_status) {
		SET_STATUS = set_status;
	}
	public String getSET_MAX() {
		return SET_MAX;
	}
	public void setSET_MAX(String set_max) {
		SET_MAX = set_max;
	}
	public String getPRE_ORDER_STYLE() {
		return PRE_ORDER_STYLE;
	}
	public void setPRE_ORDER_STYLE(String pre_order_style) {
		PRE_ORDER_STYLE = pre_order_style;
	}
	private String SF_ID;
	private String SET_NO;
	private String DINING_FLOOR;
	private String BALCONY_CODE;
	private String BELONG_TO;
	private String BALCONY_NAME;
	private String SET_STATUS;
	private String SET_MAX;
	private String PRE_ORDER_STYLE;
	private String MINCOST_MONEY;
	private String MINCOST_TYPE;
	private int status;
	private Order_info order_info;
	
	public Order_info getOrder_info() {
		return order_info;
	}
	public void setOrder_info(Order_info order_info) {
		this.order_info = order_info;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMINCOST_MONEY() {
		return MINCOST_MONEY;
	}
	public void setMINCOST_MONEY(String mincost_money) {
		MINCOST_MONEY = mincost_money;
	}
	public String getMINCOST_TYPE() {
		return MINCOST_TYPE;
	}
	public void setMINCOST_TYPE(String mincost_type) {
		MINCOST_TYPE = mincost_type;
	}
	

	

}
