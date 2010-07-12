/**
 * 
 */
package com.apricot.app.eating.calc.vo;

import com.apricot.eating.util.DataUtil;

/**
 * @author Administrator
 */
public class OrderDetail {
	private boolean calculate = false;
	private long foodId;
	private float foodNum;
	private float foodPrice;
	private String foodType;
	private float disPoint = 0F;
	private float total;
	private String detailId;

	private float payTotal;
	private float point;

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	/**
	 * 
	 */
	public OrderDetail(Object o) {
		// TODO Auto-generated constructor stub
		this.foodId = DataUtil.getLong(o, "food_id", 0L);
		this.foodNum = DataUtil.getFloat(o, "food_count", 0F);
		this.foodPrice = DataUtil.getFloat(o, "food_price", 0F);
		this.foodType = DataUtil.getString(o, "food_type");
		this.detailId = DataUtil.getString(o, "order_list_id");
		this.total = this.foodNum * this.foodPrice;
		this.payTotal = this.total;
		this.point = 0;
	}

	public float getPayTotal() {
		return payTotal;
	}

	public long getFoodId() {
		return foodId;
	}

	public float getFoodNum() {
		return foodNum;
	}

	public float getFoodPrice() {
		return foodPrice;
	}

	public String getFoodType() {
		return foodType;
	}

	public float getTotal() {
		return this.total;
	}

	public float getDisPoint() {
		return disPoint;
	}

	public boolean isCalculate() {
		return calculate;
	}

	public void setCalculate(boolean calculate) {
		this.calculate = calculate;
	}

	public void setPayTotal(float factPayTotal) {
		this.payTotal = factPayTotal;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public void setFoodNum(float foodNum) {
		this.foodNum = foodNum;
	}

	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public void setDisPoint(float p) {
		this.disPoint = this.disPoint + p;
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}
}
