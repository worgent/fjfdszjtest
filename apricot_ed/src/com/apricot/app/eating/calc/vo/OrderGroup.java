/**
 * 
 */
package com.apricot.app.eating.calc.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.apricot.eating.util.DataUtil;

/**
 * @author Administrator
 */
public class OrderGroup {
	// 消费积分
	private float consumePoint;
	private List group;
	private String no;
	private float payTotal = 0F;
	private float pointTotal;
	private float total = 0F;
	private float pointMoney=0F;
	private float factPayCard=0F;
	private String vipCardNo;
	private float vipDiscount=100F;
	private StringBuffer belongTos=new StringBuffer(",");
	private boolean usePoint;
//	private Map relatType = new HashMap();//后来改的

	/**
	 * @return Returns the usePoint.
	 */
	public boolean isUsePoint() {
		return usePoint;
	}

	/**
	 * @param usePoint The usePoint to set.
	 */
	public void setUsePoint(String usePoint) {
		
		this.usePoint = "on".equals(usePoint);
	}

	public void addTotal(float t) {
		this.total = this.total + t;
	}
	
	public String getBelongTos(){
		return belongTos.toString();
	}


	public float getOrderPayTotal() {
		float f = 0;
		for (Iterator objs = this.group.iterator(); objs.hasNext();) {
			Order o = (Order) objs.next();
			f += o.getPayTotal();

		}

		return f;
	}

	/**
	 * 
	 */
	public OrderGroup(String no) {
		// TODO Auto-generated constructor stub
		this.group = new ArrayList();
		this.no = no;
	}

	public int size() {
		return this.group.size();
	}

	public void add(Order o) {
		this.group.add(o);
		if (DataUtil.isNull(vipCardNo)) {
			vipCardNo = o.getVipCardNo();
		}
		this.total = this.total + o.getTotal();
		if (DataUtil.isNull(this.vipCardNo)) {
			this.vipCardNo = o.getVipCardNo();
		}
		this.pointTotal = o.getPoint();
		System.out.println("og:"+o.getPoint());
		this.belongTos.append(o.getSetBelong()).append(",");
	}

	private StringBuffer footType;
	private StringBuffer foodIds;
	private void forEachDetail(){
		if(footType!=null) return;
		footType=new StringBuffer();
		foodIds=new StringBuffer(",");
		for(Iterator ds=getOrderDetails().iterator();ds.hasNext();){
			OrderDetail od=(OrderDetail)ds.next();
			if(footType.indexOf(od.getFoodType())<0){
				footType.append(",").append(od.getFoodType());
			}
			foodIds.append(od.getFoodId()).append(",");
			
		}
		
	}
	public boolean contailFoodType(String foodType) {
		if(footType==null) forEachDetail();
		return footType.indexOf(foodType)>=0;
	}

	public boolean containFood(int foodId) {
		if(foodIds==null) forEachDetail();
		return foodIds.indexOf(","+foodId+",")>=0;
	}

	public float getConsumePoint() {
		return consumePoint;
	}

	/**
	 * 获取订单明细,通过菜品ID
	 * 
	 * @param args
	 * @return
	 */
	public List getDetailsByID(String id) {
		ArrayList arr = new ArrayList();
		for (Iterator objs = this.group.iterator(); objs.hasNext();) {
			Order o = (Order) objs.next();
			arr.addAll(o.getDetailsByID(id));
		}
		return arr;
	}

	/**
	 * 获取订单明细,通过菜品类型
	 * 
	 * @param args
	 * @return
	 */
	public List getDetailsByType(String type) {
		ArrayList arr = new ArrayList();
		for (Iterator objs = this.group.iterator(); objs.hasNext();) {
			Order o = (Order) objs.next();
			arr.addAll(o.getDetailsByType(type));
		}
		return arr;
	}

	private float disPoint=0;

	public String getNo() {
		return no;
	}

	public Collection getOrderDetailsBy(Rule r) {
		HashMap m = new HashMap();
		String foodType = r.getScopeFoodType();
		String foodId = r.getScopeFoodID();
		String belongType = r.getBelongType();
		boolean isFlag = false;
		if(foodId != null && !("".equals(foodId)) && Long.parseLong(foodId.replaceAll("[,]", ""))>=0)
		{
			isFlag = true;
		}

		// 如果没有设定范围,则表示所有都适用
		if (!r.scopes().hasNext()) {
			for(Iterator orders=this.group.iterator();orders.hasNext();){
				Order o=(Order) orders.next();
				for(Iterator details=o.getDetails().iterator();details.hasNext();){
					OrderDetail od = (OrderDetail) details.next();
					m.put(od.getDetailId(), od);
				}
			}
			
			return m.values();
		}
		for (Iterator objs = this.group.iterator(); objs.hasNext();) {
			Order o = (Order) objs.next();
			for (Iterator details = o.getDetails().iterator(); details
					.hasNext();) {
				OrderDetail od = (OrderDetail) details.next();
				if (foodType.equals("03")
						&& belongType.indexOf(o.getSetID()) < 0) {
					m.put(od.getDetailId(), od);
					continue;
				}
				if(isFlag)
				{
					if (foodId.indexOf(String.valueOf(od.getFoodId())) >= 0) {
						m.put(od.getDetailId(), od);
						continue;
					}
				}else 
				{
					if(foodType.indexOf(od.getFoodType()) >= 0) {
						m.put(od.getDetailId(), od);
						continue;
					}
				}
			}
		}
	    
		return m.values();
	}

	/**
	 * 获取订单群下面所有订单明细数据
	 * 
	 * @return
	 */
	public List getOrderDetails() {
		ArrayList arr = new ArrayList();
		for (Iterator objs = this.group.iterator(); objs.hasNext();) {
			Order o = (Order) objs.next();
			arr.addAll(o.getDetails());
		}
		return arr;
	}

	public List getOrders() {
		return group;
	}

	public float getPayTotal() {
		return payTotal;
	}

	public float getPointTotal() {
		return pointTotal;
	}

	/**
	 * 获取当前订单号下面所有订单的应收总金额
	 * 
	 * @return
	 */
	public float getTotal() {
		return total;
	}

	public String getVipCardNo() {
		return vipCardNo;
	}

	public Iterator iterator() {
		return this.group.iterator();
	}

	public void setConsumePoint(float consumePoint) {
		this.consumePoint = consumePoint;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setPayTotal(float payTotal) {
		this.payTotal = payTotal;
	}

	public void setPointTotal(float pointTotal) {
		this.pointTotal = pointTotal;
	}

	public void setVipCardNo(String vipCardNo) {
		this.vipCardNo = vipCardNo;
	}

	public float getDisPoint() {
		return disPoint;
	}

	public void setDisPoint(float disPoint) {
		this.disPoint = disPoint;
	}

	public float getVipDiscount() {
		return vipDiscount;
	}

	public void setVipDiscount(float vipDiscount) {
		this.vipDiscount = vipDiscount;
	}

	public float getPointMoney() {
		return pointMoney;
	}

	public void setPointMoney(float pointMoney) {
		this.pointMoney = pointMoney;
	}

	public float getFactPayCard() {
		return factPayCard;
	}

	public void setFactPayCard(float factPayCard) {
		this.factPayCard = factPayCard;
	}
}
