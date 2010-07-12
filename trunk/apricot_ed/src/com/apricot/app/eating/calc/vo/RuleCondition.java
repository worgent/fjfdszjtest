/**
 * 
 */
package com.apricot.app.eating.calc.vo;

import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class RuleCondition {
	/**
	 * 
	 */
	public RuleCondition(Object o) {
		// TODO Auto-generated constructor stub
		this.condMust = DataUtil.getString(o, "cond_must");
		this.condType = DataUtil.getString(o, "cond_type");
		this.condTypeText = DataUtil.getString(o, "cond_type_text");
		this.condOp = DataUtil.getString(o, "cond_op");
		this.condOpText = DataUtil.getString(o, "cond_op_text");
		this.condValue = DataUtil.getString(o, "cond_value");
	}
	private boolean delete = false;
	private String condType;
	private String condTypeText;
	private String condOp;
	private String condOpText;
	private String condValue;
	private String condMust;

	public String getCondMust() {
		return condMust;
	}

	public void setCondMust(String condMust) {
		this.condMust = condMust;
	}

	public String getCondOp() {
		return condOp;
	}

	public void setCondOp(String condOp) {
		this.condOp = condOp;
	}

	public String getCondType() {
		return condType;
	}

	public void setCondType(String condType) {
		this.condType = condType;
	}

	public String getCondValue() {
		return condValue;
	}

	public void setCondValue(String condValue) {
		this.condValue = condValue;
	}

	public String getCondTypeText() {
		return condTypeText;
	}

	public void setCondTypeText(String condTypeText) {
		this.condTypeText = condTypeText;
	}

	public String getCondOpText() {
		return condOpText;
	}

	public void setCondOpText(String condOpText) {
		this.condOpText = condOpText;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}
}
