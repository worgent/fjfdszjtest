/**
 * 
 */
package com.apricot.app.eating.calc;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.apricot.app.eating.calc.vo.OrderGroup;
import com.apricot.app.eating.calc.vo.Rule;
import com.apricot.app.eating.calc.vo.RuleValue;
/**
 * @author Administrator
 */
public class Calculate {
	/**
	 * 
	 */
	public Calculate() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过规则和订单群，过滤不符合条件的算费规则。
	 * 
	 * @param rules
	 * @param og
	 * @return
	 */
	public List filterRules(List rules, OrderGroup og) {
		CalculateRule c = new CalculateRule();
		for (int i = rules.size() - 1; i >= 0; i--) {
			Rule r = (Rule) rules.get(i);
			if (!c.check(r, og)){
				rules.remove(i);
			}
			else if (!r.existScope() && !r.isVip())
				rules.remove(i);
			
			c.filter(r, og);
		}
		return rules;
	}

	/**
	 * 进行费用计算，最后得出费用等等信息
	 * 
	 * @param rules
	 * @param og
	 * @return
	 */
	public List calculate(List rules, OrderGroup og) {
		// System.out.println(2);
		CalculateRule c = new CalculateRule();

		// 清除掉不存在折扣范围的规则
		for (int i = rules.size() - 1; i >= 0; i--) {
			Rule r = (Rule) rules.get(i);
			if (!r.existScope() && !r.isVip())
				rules.remove(i);
		}
		// 固定金额赠送
		for (Iterator rs = rules.iterator(); rs.hasNext();) {
			Rule r = (Rule) rs.next();
			// Collection details = og.getOrderDetailsBy(r);
			// for (Iterator rsCon = r.conditions(); rsCon.hasNext();) {
			// RuleCondition con = (RuleCondition)rsCon.next();
			// System.out.println(con.getCondValue());
			// }
			
			for (Iterator vs = r.values(); vs.hasNext();) {
				RuleValue v = (RuleValue) vs.next();
				if (!"0".equals(v.getPriceUnit()))
					continue;
				
				c.dealPresent(v, r, og);
				// c.dealPresent(v, og);
			}
		}
		if (og.isUsePoint()) {// 只有当使用积分的时候才可以使用
			// 处理等值积分抵扣现金
			
			for (Iterator rs = rules.iterator(); rs.hasNext();) {
				Rule r = (Rule) rs.next();
				
				for (Iterator vs = r.values(); vs.hasNext();) {
					RuleValue v = (RuleValue) vs.next();
					
					if (!"4".equals(v.getPriceUnit()))
						continue;
					c.dealPointDeduction(v, og);
					// pointCountFlag = false;
				}
			}
		}
		// 处理折扣
		for (Iterator rs = rules.iterator(); rs.hasNext();) {
			Rule r = (Rule) rs.next();
			Collection details = og.getOrderDetailsBy(r);
			for (Iterator vs = r.values(); vs.hasNext();) {
				RuleValue v = (RuleValue) vs.next();
				if (!"1".equals(v.getPriceUnit()))
					continue;
				
				c.dealDiscount(v, details,og);
			}
			
		}
		//og.setPayTotal(og.getPayTotal() * og.getVipDiscount() / 100F);


		// 处理赠送固定积分
		for (Iterator rs = rules.iterator();  rs.hasNext();) {
			Rule r = (Rule) rs.next();
			for (Iterator vs = r.values(); vs.hasNext();) {
				RuleValue v = (RuleValue) vs.next();
				if (!"2".equals(v.getPriceUnit()))
					continue;
				//System.out.println(r.getName()+"="+v.getPriceUnit()+"="+v.getPriceValue());
				c.dealPresentPoint(v, og);
		
				//break;
			}
		}
		// 处理等值现金计算积分
		for (Iterator rs = rules.iterator();  rs.hasNext();) {
			Rule r = (Rule) rs.next();
			for (Iterator vs = r.values(); vs.hasNext();) {
				RuleValue v = (RuleValue) vs.next();
				if (!"3".equals(v.getPriceUnit()))
					continue;
				
				c.dealPointCount(v, og);
		
				//break;
			}
		}
		return null;
	}

}
