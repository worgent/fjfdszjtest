/**
 * 
 */
package com.apricot.app.eating.calc;

import java.util.Collection;
import java.util.Iterator;

import com.apricot.app.eating.calc.vo.OrderDetail;
import com.apricot.app.eating.calc.vo.OrderGroup;
import com.apricot.app.eating.calc.vo.Rule;
import com.apricot.app.eating.calc.vo.RuleCondition;
import com.apricot.app.eating.calc.vo.RuleScope;
import com.apricot.app.eating.calc.vo.RuleValue;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
class CalculateRule {
	/**
	 * 
	 */
	CalculateRule() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 处理打折部分
	 * 
	 * @param v
	 * @param details
	 */
	void dealDiscount(RuleValue v, Collection details,OrderGroup og) {
		// System.out.println(1);
		float otherTotal=og.getPayTotal();
		float payTotal=0;
		for (Iterator dts = details.iterator(); dts.hasNext();) {
			OrderDetail od = (OrderDetail) dts.next();
			otherTotal-=od.getPayTotal();
			od.setPayTotal((od.getPayTotal()*v.getPriceValue())/100F);
			payTotal+=od.getPayTotal();
		}
		
		og.setPayTotal(otherTotal+payTotal);
		
	}

	/**
	 * 处理固定金额赠送
	 * 
	 * @param v
	 * @param details
	 */
	// void dealPresent(RuleValue v,OrderGroup o){
	// float p=o.getPayTotal();
	// System.out.println(p+"-"+v.getPriceValue());
	// p=p-v.getPriceValue();
	//        
	// o.setPayTotal(p);
	// }
	void dealPresent(RuleValue v, Rule r, OrderGroup o) {
		float p = o.getPayTotal();
		float factValue = 0;
		
		Collection details = o.getOrderDetailsBy(r);
		for (Iterator dts = details.iterator(); dts.hasNext();) {
			OrderDetail od = (OrderDetail) dts.next();
			factValue = factValue + od.getPayTotal();
		}
		float otherValue=o.getTotal()-factValue; //其他菜品金额
		
		if((p-factValue+v.getPriceValue())>otherValue){
			p = p - v.getPriceValue();
		}
		System.out.println("固定赠送"+factValue+"="+v.getPriceValue());

		o.setPayTotal(p);
	}

	/**
	 * 处理等值积分抵扣现金
	 * 
	 * @param v
	 * @param o
	 */
	void dealPointDeduction(RuleValue v, OrderGroup o) {
		float p = o.getPayTotal();
		p = p - (o.getConsumePoint() / v.getPriceValue());
		if (p < 0) {
			o.setPointMoney(o.getPayTotal());
			o.setConsumePoint(o.getPayTotal() * v.getPriceValue());
			o.setPayTotal(0);
		} else {
			o.setPointMoney(o.getConsumePoint() / v.getPriceValue());
			o.setPayTotal(p);
		}
		// o.setPayTotal(p);
	}

	/**
	 * 处理固定积分计算
	 * 
	 * @param v
	 * @param o
	 */
	void dealPresentPoint(RuleValue v, OrderGroup o) {
		//System.out.println("返积分"+o.getDisPoint()+"="+v.getPriceValue());
		o.setDisPoint(o.getDisPoint() +v.getPriceValue());
	}

	/**
	 * 处理动态积分计算
	 * 
	 * @param v
	 * @param o
	 */
	void dealPointCount(RuleValue v, OrderGroup o) {
		float p = o.getPayTotal();

		o.setDisPoint(o.getDisPoint() +(p / v.getPriceValue()));
	}

	boolean check(Rule r, OrderGroup og) {
		
		for (Iterator objs = r.conditions(); objs.hasNext();) {
			RuleCondition c = (RuleCondition) objs.next();
			float tt = 0;
			if ("setbelong".equalsIgnoreCase(c.getCondType())) {
				if (og.getBelongTos().indexOf("," + c.getCondValue() + ",") >= 0 && "1".equals(c.getCondMust())) // 只要满足一个
					return true;
				if (og.getBelongTos().indexOf("," + c.getCondValue() + ",") < 0 && "2".equals(c.getCondMust())) // 所有必须满足
					return false;
				continue;
			}
			// 判断总金额
			if ("total".equalsIgnoreCase(c.getCondType())) {
				tt = og.getTotal();
				System.out.println("total"+og.getTotal());
				if(tt >= Float.parseFloat(c.getCondValue()))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			// 判断积分
			if ("point".equalsIgnoreCase(c.getCondType())) {
				tt = og.getPointTotal();
				System.out.println("tt:"+tt);
				if(tt >= Float.parseFloat(c.getCondValue()))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			// 判断总菜金
			if ("foodtype".equalsIgnoreCase(c.getCondType())) {
				for (Iterator ds = og.getOrderDetails().iterator(); ds.hasNext();) {
					OrderDetail od = (OrderDetail) ds.next();
					
					if ("00,01,02,06".indexOf(od.getFoodType()) >= 0) {
						tt += od.getFoodNum() * od.getFoodPrice();
					}
				}
			}
			
			
			if (match(c, tt) && "1".equals(c.getCondMust())) // 只要满足一个
				return true;
			if (!match(c, tt) && "2".equals(c.getCondMust())) // 所有必须满足
				return false;
		}
		return true;
	}

	void filter(Rule r, OrderGroup og) {
		String t = DataUtil.getCurrDateTime("HH:mm");
		for (Iterator objs = r.scopes(); objs.hasNext();) {
			RuleScope s = (RuleScope) objs.next();
			// 判断时间范围
			if (s.getStartTime().compareTo(t) > 0) {
				s.setDelete(true);
				continue;
			}
			if (s.getEndTime().compareTo(t) < 0) {
				s.setDelete(true);
				continue;
			}
			// 判断类型
			if ("1".equals(s.getScopeType()) && !og.contailFoodType(s.getScopeValue())) {
				s.setDelete(true);
			}
			if ("2".equals(s.getScopeType()) && !og.containFood(DataUtil.parseInt(s.getScopeValue()))) {
				s.setDelete(true);
			} 
		}
		r.delete();
	}

	/**
	 * 规则条件比较
	 * 
	 * @param c
	 * @param v
	 * @return
	 */
	private boolean match(RuleCondition c, float v) {
		float v1 = DataUtil.parseFloat(c.getCondValue());
		if (">".equalsIgnoreCase(c.getCondOp())) {
			return v > v1;
		}
		if (">=".equalsIgnoreCase(c.getCondOp())) {
			return v >= v1;
		}
		if ("<".equalsIgnoreCase(c.getCondOp())) {
			return v < v1;
		}
		if ("<=".equalsIgnoreCase(c.getCondOp())) {
			return v <= v1;
		}
		if ("=".equalsIgnoreCase(c.getCondOp())) {
			return v == v1;
		}
		return false;
	}
}
