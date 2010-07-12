/**
 * 
 */
package com.apricot.app.eating.calc.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class Order {
	private List details;
	private String vipCardNo;
	private float total=0F;
	private String id;
	private String setID;
	private String setBelong;
	private float point;

	public void setDetails(List details) {
		this.details = details;
	}

	public void setPoint(float point) {
		this.point = point;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * @return Returns the setBelong.
	 */
	public String getSetBelong() {
		return setBelong;
	}

	/**
	 * @param setBelong The setBelong to set.
	 */
	public void setSetBelong(String setBelong) {
		this.setBelong = setBelong;
	}

	public String getSetID() {
		return setID;
	}

	public void setSetID(String setID) {
		this.setID = setID;
	}
    
	public float getPayTotal(){
		float f=0;
		for(Iterator objs=details.iterator();objs.hasNext();){
			OrderDetail od=(OrderDetail) objs.next();
			f+=od.getPayTotal();
		}
		return f;
	}
	
	public float getTotal(){
		float f=0;
		for(Iterator objs=details.iterator();objs.hasNext();){
			OrderDetail od=(OrderDetail) objs.next();
			f+=od.getTotal();
		}
		return f;
	}
	
	public float getPoint(){
//		float f=0;
//		for(Iterator objs=details.iterator();objs.hasNext();){
//			OrderDetail od=(OrderDetail) objs.next();
//			f+=od.getPoint();
//		}
//		return f;
		return point;
	}
	
	/**
	 * 
	 */
	public Order(Object o) {
		// TODO Auto-generated constructor stub
		this.details = new ArrayList();
		this.vipCardNo=DataUtil.getString(o, "vip_card_no");
		this.id=DataUtil.getString(o, "order_id");
		this.setID=DataUtil.getString(o, "set_no");
		this.setBelong=DataUtil.getString(o, "belong_to");
		if(DataUtil.getString(o, "cum_point")!=null&&!("".equals(DataUtil.getString(o, "cum_point"))))
		{
			this.point = Float.parseFloat(DataUtil.getString(o, "cum_point"));
		}
		else
		{
			this.point = 0F;
		}
	}

	public void add(OrderDetail od) {
		this.details.add(od);
		this.total=this.total+od.getTotal();
	}

	public String getVipCardNo() {
		return vipCardNo;
	}

	public void setVipCardNo(String vipCardNo) {
		this.vipCardNo = vipCardNo;
	}

	/**
	 * 获取订单明细,通过菜品类型
	 * 
	 * @param args
	 * @return
	 */
	public List getDetailsByType(String type) {
		ArrayList arr = new ArrayList();
		if (DataUtil.isNull(type))
			return arr;
		for (Iterator objs = this.details.iterator(); objs.hasNext();) {
			OrderDetail o = (OrderDetail) objs.next();
			if (type.indexOf(o.getFoodType()) >= 0) {
				arr.add(o);
			}
		}
		return arr;
	}

	/**
	 * 获取订单明细,通过菜品ID
	 * 
	 * @param args
	 * @return
	 */
	public List getDetailsByID(String id) {
		ArrayList arr = new ArrayList();
		if (DataUtil.isNull(id))
			return arr;
		for (Iterator objs = this.details.iterator(); objs.hasNext();) {
			OrderDetail o = (OrderDetail) objs.next();
			if (id.indexOf(String.valueOf(o.getFoodId())) >= 0) {
				arr.add(o);
			}
		}
		return arr;
	}

	public List getDetails() {
		return details;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
