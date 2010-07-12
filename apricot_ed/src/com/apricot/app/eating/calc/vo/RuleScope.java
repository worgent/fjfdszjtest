/**
 * 
 */
package com.apricot.app.eating.calc.vo;

import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class RuleScope {
	/**
	 * 
	 */
	public RuleScope(Object o) {
		// TODO Auto-generated constructor stub
		this.scopeType = DataUtil.getString(o, "scope_type");
		this.scopeValue = DataUtil.getString(o, "scope_value");
		this.scopeValueText = DataUtil.getString(o, "scope_value_text");
		this.startTime = DataUtil.getString(o, "start_time");
		this.endTime = DataUtil.getString(o, "end_time");
	}
	private String scopeType;
	private String scopeValue;
	private String scopeValueText;
	private String startTime;
	private String endTime;
	private boolean delete = false;

	public String toString() {
		// TODO Auto-generated method stub
		return this.scopeValueText;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getScopeType() {
		return scopeType;
	}

	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	public String getScopeValue() {
		return scopeValue;
	}

	public void setScopeValue(String scopeValue) {
		this.scopeValue = scopeValue;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getScopeValueText() {
		return scopeValueText;
	}

	public void setScopeValueText(String scopeValueText) {
		this.scopeValueText = scopeValueText;
	}
}
