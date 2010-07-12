package com.apricot.app.pda;
import java.util.List;
public class Order_list {

	private Food_info food_info;
	
	private String ORDER_ID;
	private String FOOD_ID;
	private String FOOD_COUNT;
	private String FOOD_MEMO;
	private String FOOD_RETURN_RESEON;
	
	private String FOOD_RETURN_TYPE;
	private String FOOD_DISCOUNT;
	private String FOOD_DISCOUNT_RESEON;
	private String ORDER_LIST_ID;
	private String MODIFY_STAFF_ID;
	
	private String FOOD_PRICE;
	private String OLD_FOOD_ID;
	private String FOOD_ACTION;
	private String SERVING_FLAG;
	private String modify_staff_name;
	public String getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(String order_id) {
		ORDER_ID = order_id;
	}
	public String getFOOD_ID() {
		return FOOD_ID;
	}
	public void setFOOD_ID(String food_id) {
		FOOD_ID = food_id;
	}
	public String getFOOD_COUNT() {
		return FOOD_COUNT;
	}
	public void setFOOD_COUNT(String food_count) {
		FOOD_COUNT = food_count;
	}
	public String getFOOD_MEMO() {
		return FOOD_MEMO;
	}
	public void setFOOD_MEMO(String food_memo) {
		FOOD_MEMO = food_memo;
	}
	public String getFOOD_RETURN_RESEON() {
		return FOOD_RETURN_RESEON;
	}
	public void setFOOD_RETURN_RESEON(String food_return_reseon) {
		FOOD_RETURN_RESEON = food_return_reseon;
	}
	public String getFOOD_RETURN_TYPE() {
		return FOOD_RETURN_TYPE;
	}
	public void setFOOD_RETURN_TYPE(String food_return_type) {
		FOOD_RETURN_TYPE = food_return_type;
	}
	public String getFOOD_DISCOUNT() {
		return FOOD_DISCOUNT;
	}
	public void setFOOD_DISCOUNT(String food_discount) {
		FOOD_DISCOUNT = food_discount;
	}
	public String getFOOD_DISCOUNT_RESEON() {
		return FOOD_DISCOUNT_RESEON;
	}
	public void setFOOD_DISCOUNT_RESEON(String food_discount_reseon) {
		FOOD_DISCOUNT_RESEON = food_discount_reseon;
	}
	public String getORDER_LIST_ID() {
		return ORDER_LIST_ID;
	}
	public void setORDER_LIST_ID(String order_list_id) {
		ORDER_LIST_ID = order_list_id;
	}
	public String getMODIFY_STAFF_ID() {
		return MODIFY_STAFF_ID;
	}
	public void setMODIFY_STAFF_ID(String modify_staff_id) {
		MODIFY_STAFF_ID = modify_staff_id;
	}
	public String getFOOD_PRICE() {
		return FOOD_PRICE;
	}
	public void setFOOD_PRICE(String food_price) {
		FOOD_PRICE = food_price;
	}
	public String getOLD_FOOD_ID() {
		return OLD_FOOD_ID;
	}
	public void setOLD_FOOD_ID(String old_food_id) {
		OLD_FOOD_ID = old_food_id;
	}
	public String getFOOD_ACTION() {
		return FOOD_ACTION;
	}
	public void setFOOD_ACTION(String food_action) {
		FOOD_ACTION = food_action;
	}
	public String getSERVING_FLAG() {
		return SERVING_FLAG;
	}
	public void setSERVING_FLAG(String serving_flag) {
		SERVING_FLAG = serving_flag;
	}
	public String getModify_staff_name() {
		return modify_staff_name;
	}
	public void setModify_staff_name(String modify_staff_name) {
		this.modify_staff_name = modify_staff_name;
	}
	public Food_info getFood_info() {
		return food_info;
	}
	public void setFood_info(Food_info food_info) {
		this.food_info = food_info;
	}
	
}
