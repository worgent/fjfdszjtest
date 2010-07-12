/**
 * 
 */
package com.apricot.app.eating.calc.vo;

import com.apricot.eating.MessageResource;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class RuleValue {
	/**
	 * 
	 */
	public RuleValue(Object o) {
		// TODO Auto-generated constructor stub
		this.priceUnit = DataUtil.getString(o, "price_unit");
		this.priceUnitText = DataUtil.getString(o, "price_unit_text");
		this.priceValue = DataUtil.getFloat(o, "price_value",0F);
	}
	private String priceUnit;
	private String priceUnitText;
	private float priceValue;
	private boolean delete = false;

	public boolean isDelete() {
		return delete;
	}

	public String toString() {
		StringBuffer s = new StringBuffer();
		try {
			s.append(MessageResource.getInstance().getMessage("price_unit_" + priceUnit, new String[] { String.valueOf(priceValue) }));
		} catch (Exception e) {
		}
		return s.toString();
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public float getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(float priceValue) {
		this.priceValue = priceValue;
	}

	public String getPriceUnitText() {
		return priceUnitText;
	}

	public void setPriceUnitText(String priceUnitText) {
		this.priceUnitText = priceUnitText;
	}
}
