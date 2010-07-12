/**
 * 
 */
package com.apricot.app.eating.business;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.apricot.app.eating.calc.Calculate;
import com.apricot.app.eating.calc.vo.Order;
import com.apricot.app.eating.calc.vo.OrderDetail;
import com.apricot.app.eating.calc.vo.OrderGroup;
import com.apricot.app.eating.calc.vo.Rule;
import com.apricot.app.eating.calc.vo.RuleCondition;
import com.apricot.app.eating.calc.vo.RuleScope;
import com.apricot.app.eating.calc.vo.RuleValue;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;
import com.apricot.webservice.bean.Orders;
import com.apricot.webservice.bean.Response;
import com.apricot.webservice.bean.Row;
import com.apricot.webservice.impl.ClientImpl;
/**
 * @author Administrator
 */
public class CalculateBo extends BO {
	/**
	 * 
	 */
	public CalculateBo() {
		// TODO Auto-generated constructor stub
	}

	public Object bookBill(DyncParameterMap map) throws NestedException {
		double FACT_PAY_TOTAL = Double.parseDouble(map.getString("fact_pay_total"));
		double RETURN_TOTAL = Double.parseDouble(map.getString("return_total"));
		double mon = FACT_PAY_TOTAL - RETURN_TOTAL;
		StringBuffer ext = new StringBuffer();
		ext.append("select staff_name from staff_info where staff_code='");
		ext.append(map.getString("name"));
		ext.append("'");
		Object o = get(ext);
		Table tab = getTable("order_payoff_info");
		tab.setSqlType(Table.INSERT);
		tab.setParameterMap(map);
		map.set("cashier", map.getString("staff_id"));
		map.set("book_time", DataUtil.getCurrDateTime());
		map.set("pay_id", getMax("order_payoff_info", "pay_id"));
		map.set("pay_time", DataUtil.getCurrDateTime());
		StringBuffer sql = new StringBuffer();
		sql.append("update order_info set order_status='4',pay_status='0' ");
		sql.append("where order_status='0' and order_no=");
		sql.append(map.getSqlString("order_no", "-1"));
		StringBuffer upSql = new StringBuffer();
		upSql.append("update staff_business_info set BOOK_CASH=BOOK_CASH+'");
		upSql.append(mon);
		upSql.append("'");
		upSql.append(" where staff_id='");
		upSql.append(map.getString("staff_id"));
		upSql.append("' and r_date=CURDATE()");
		if (update(sql) > 0) {
			execute(tab);
			update(upSql);
			// 更新记账信息
			tab = getTable("order_book_info");
			tab.setSqlType(Table.INSERT);
			tab.setParameterMap(map);
			map.set("book_date", map.getString("book_date"));
			map.set("book_remarks", map.getString("book_remarks"));
			map.set("book_name", DataUtil.getString(o, "staff_name"));
			execute(tab);
		} else
			return getMessage("bookbill_001", null);
		return getMessage("bookbill_000", null);
	}

	public Object payOver(DyncParameterMap map) throws NestedException {
		Table tab = getTable("order_payoff_info");
		tab.setSqlType(Table.INSERT);
		tab.setParameterMap(map);
		map.set("pay_id", getMax("order_payoff_info", "pay_id"));
		map.set("pay_time", DataUtil.getCurrDateTime());
		if (!DataUtil.isNull(map.getString("cp_num"))) {
			StringBuffer sql1 = new StringBuffer();
			sql1.append("select count(*) from coupon_info where cp_status='02'and cp_num in(");
			sql1.append(map.getString("cp_num"));
			sql1.append(")");
			if (getInt(sql1) > 0) {
				StringBuffer sql2 = new StringBuffer();
				sql2
						.append("select count(*) from coupon_info where cp_status='02' and now() between start_time and end_time and cp_num in(");
				sql2.append(map.getString("cp_num"));
				sql2.append(")");
				if (getInt(sql2) > 0) {
					StringBuffer sql3 = new StringBuffer();
					sql3.append("update coupon_info set cp_status='01' where cp_num in(");
					sql3.append(map.getString("cp_num"));
					sql3.append(")");
					update(sql3);
				} else {
					return getMessage("payoff_005", null);
				}
			} else {
				return getMessage("payoff_004", null);
			}
		}
		double FACT_PAY_TOTAL = Double.parseDouble(map.getString("fact_pay_total"));
		double RETURN_TOTAL = Double.parseDouble(map.getString("return_total"));
		double mon = FACT_PAY_TOTAL - RETURN_TOTAL;
		StringBuffer sql = new StringBuffer();
		sql.append("update order_info set order_status='1',OPERATE_ORDER_TIME=now() ");
		sql.append(",gain_point=" + map.getString("dis_point"));
		if (!DataUtil.isNull(map.getString("fact_pay_point"))) {
			sql.append(",use_point=" + map.getString("fact_pay_point"));
		} else {
			sql.append(",use_point=" + 0);
		}
		sql.append(" where order_status in ('0','4') and order_no=");
		sql.append(map.getSqlString("order_no", "-1"));
		// WS
		ClientImpl cli = new ClientImpl(map.getString("service_ip"));
		List listSend = new ArrayList();
		Orders order = new Orders();
		// order.setArrivingTime(DataUtil.getString(obj,
		// "prearrange_order_time"));
		// order.setCancelTime(DataUtil.getString(obj, "can_order_time"));
		order.setHandleTime(DataUtil.getCurrDateTime());
		order.setOrderId(map.getString("order_id"));
		order.setOrderNo(map.getString("order_no", "-1"));
		// order.setOrderTime(DataUtil.getCurrDateTime());
		order.setRid(map.getString("sf_id"));
		order.setSeatNo(map.getString("set_no"));
		order.setState("1");
		order.setType(map.getString("order_type"));
		order.setSqlType(1);
		if ("1".equals(map.getString("order_type"))) {
			order.setTotal(map.getString("pay_total"));
		} else {
			order.setTotal("0");
		}
		order.setManCount(map.getString("man_count"));
		Row row = new Row(order, new String[] {
				"arrivingTime",
				"cancelTime",
				"handleTime",
				"orderId",
				"orderNo",
				"orderTime",
				"rid",
				"seatNo",
				"state",
				"type",
				"sqlType",
				"total",
				"manCount" }, new String[] { "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11", "f12", "f13" });
		listSend.add(row);
		StringBuffer sqlTemp = new StringBuffer();
		sqlTemp.append("update customer_info set cum_point=" + map.getString("fact_point") + " where vip_no='"
				+ map.getString("vip_card_no") + "'");
		update(sqlTemp);
		StringBuffer upSql = new StringBuffer();
		upSql.append("update staff_business_info set r_cash=r_cash+'");
		upSql.append(mon);
		upSql.append("'");
		upSql.append(" where staff_id='");
		upSql.append(map.getString("staff_id"));
		upSql.append("' and r_date=CURDATE()");
		if (update(sql) > 0) {
			update(upSql);
			execute(tab);
			Response rep = cli.syncOrders(map.getString("sf_id"), "", listSend);
			if (!"1".equals(rep.getErrorCode())) {
				map.set("id", getMax("order_info_temp", "id"));
				map.set("rid", map.getString("sf_id"));
				map.set("sql_type", 2);
				Table tabTemp = getTable("order_info_temp");
				tabTemp.setSqlType(Table.INSERT);
				tabTemp.setParameterMap(map);
				execute(tabTemp);
			}
		} else {
			return getMessage("payoff_001", null);
		}
		return null;
	}

	public Object getOrderDetails(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.food_price,t1.food_count,");
		sql.append("t2.food_name from order_list t1,food_info t2");
		sql.append(",order_info t3 where ");
		sql.append("t1.order_id=t3.order_id and t1.food_id=t2.food_id");
		sql.append(" and t1.food_action in ('1','2') and t1.serving_flag=");
		sql.append(DataUtil.getSqlString(map.getString("serving_flag", "-1")));
		sql.append(" and t3.order_no='");
		sql.append(map.getString("order_no", ",", 0) + "'");
		System.out.println(sql);
		return showPages(sql.toString(), 0, -1);
	}

	public Object calculate(DyncParameterMap map) throws NestedException {
		double cash = 0.00;
		double disct = 0.00;
		int smg = 88888888;
		if (map.getString("cp_num") != "") {
			StringBuffer ext = new StringBuffer();
			ext.append("select count(*) from coupon_info where cp_num in (");
			ext.append(map.getString("cp_num"));
			ext.append(")");
			if (getInt(ext) == 0)
				return new Integer(smg);
			StringBuffer sqlv = new StringBuffer();
			sqlv.append("select cp_cash,cp_disct ,cp_status from coupon_info where cp_num in (");
			sqlv.append(map.getString("cp_num"));
			sqlv.append(")");
			// Object o=get(sqlv);
			// System.out.println("cp_cash"+DataUtil.getString(o, "cp_cash"));
			// System.out.println("cp_disct"+DataUtil.getString(o, "cp_disct"));
			List list = getAll(sqlv);
			for (int i = 0; i < list.size(); i++) {
				if ("01".equals(DataUtil.getString(list.get(i), "cp_status")))
					return new Integer(smg);
				cash += Double.parseDouble(DataUtil.getString(list.get(i), "cp_cash"));
				disct += Double.parseDouble(0 + "." + DataUtil.getString(list.get(i), "cp_disct"));
			}
		}
		HashMap m = new HashMap();
		m.put("order_no", map.getString("order_no"));
		Calculate c = new Calculate();
		OrderGroup og = getOrderGroup(map.getString("order_no"), map.getString("vip_card_no"));
		List a = getRules(map.getStrings("price_id", ','));
		System.out.println(map.getStrings("price_id",','));
		//如果选中了VIP折扣
		if(map.getStrings("price_id",',').indexOf("vip_price")>=0){
			addVIP(og, a);
		}
	
		og.setConsumePoint(Float.parseFloat(map.getString("fact_pay_point", "0")));
		og.setUsePoint(map.getString("point_check"));
		c.calculate(a, og);
		// c.calculate(c.filterRules(getRules(map.getStrings("price_id", ',')),
		// og), og);
		// Double mo=og.getPayTotal()-cash;
		double mo = 0.00;
		DecimalFormat dec = new DecimalFormat("#.##");
		if (cash == 0.0 && disct != 0.0) {
			mo = og.getPayTotal() * disct;
		} else if (cash != 0.0 && disct == 0.0) {
			mo = og.getPayTotal() - cash;
		} else {
			mo = (og.getPayTotal() - cash) * disct;
		}
		m.put("vip_card_no", (map.isNull("vip_card_no")) ? og.getVipCardNo() : map.getString("vip_card_no"));
		m.put("total", DataUtil.format(og.getTotal(), null));
		m.put("cum_point", DataUtil.format(getVipPoint((String) m.get("vip_card_no")), "#0"));
		if (map.getString("cp_num") != "") {
			m.put("pay_total", dec.format(mo - map.getDouble("youhui_total", 0)));
		} else {
			m.put("pay_total", DataUtil.format((og.getPayTotal() - Float.parseFloat(map.getString("youhui_total", "0"))), null));
		}
		m.put("fact_pay_card", map.getString("fact_pay_card"));
		m.put("youhui_total", map.getString("youhui_total"));
		m.put("point_money", DataUtil.format(og.getPointMoney(), "#0.00"));
		m.put("dis_point", DataUtil.format(og.getDisPoint() - Float.parseFloat(map.getString("youhui_total", "0")), "#0"));
		m.put("fact_pay_total", map.getString("fact_pay_total"));
		m.put("fact_pay_point", (og.getPointMoney() <= 0) ? "0" : "" + og.getConsumePoint());
		m.put("youhui_total", map.getString("youhui_total"));
		m.put("mincost_money", map.getString("mincost_money"));
		m.put("mincost_type", map.getString("mincost_type"));
		m.put("man_count", map.getString("man_count"));
		// System.out.print( map.getString("fact_pay_total")+"-");
		// System.out.print(DataUtil.format(og.getPayTotal(), null)+"+");
		// System.out.println(map.getString("youhui_total"));
		return m;
	}

	public Object getRules(DyncParameterMap map) throws NestedException {
		// System.out.println("haha:"+map.getString("order_no"));
		System.out.println("haha:" + map.getString("order_no", ",", 0));
		OrderGroup og = getOrderGroup(map.getString("order_no", ",", 0), map.getString("vip_card_no"));
		List a = new Calculate().filterRules(getRules(""), getOrderGroup(map.getString("order_no"), map.getString("vip_card_no")));
		// List a = getRules("");
		System.out.println("ppp" + a.size());
		// System.out.println(a);
		addVIP(og, a);
		String type = map.getString("price_type");
		for (int i = a.size() - 1; i >= 0; i--) {
			Rule r = (Rule) a.get(i);
			if (DataUtil.isNull(type)) {
				continue;
			}
			if (r.getType().equals(type)) {
				continue;
			}
			a.remove(i);
		}
		System.out.println(a);
		return a;
	}

	/**
	 * 获取订单信息
	 * 
	 * @param orderNo
	 * @return
	 * @throws NestedException
	 */
	private OrderGroup getOrderGroup(String orderNo, String vipNo) throws NestedException {
		OrderGroup group = new OrderGroup(orderNo);
		StringBuffer sql = new StringBuffer();
		sql.append("select order_id,vip_card_no,cum_point from order_info where order_status in('0','4')");
		sql.append(" and order_no=").append(DataUtil.getSqlString(orderNo));
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			group.add(new Order(objs.next()));
		}
		Map m = DataUtil.toMap(group.getOrders(), "id");
		// 读取订单明细
		sql = new StringBuffer();
		sql.append("select t1.*,t2.food_name,t2.food_type from");
		sql.append(" order_list t1,food_info t2 where ");
		sql.append("t1.food_action in ('1','2') and t1.serving_flag='1' and ");
		sql.append("t1.food_id=t2.food_id and t1.order_id in (");
		sql.append("select order_id from order_info where order_status in ('0','4')");
		sql.append(" and order_no=").append(DataUtil.getSqlString(orderNo));
		sql.append(")");
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object o = objs.next();
			Order od = (Order) m.get(DataUtil.getString(o, "order_id"));
			OrderDetail oo = new OrderDetail(o);
			od.add(oo);
			group.addTotal(oo.getTotal());
		}
		group.setPayTotal(group.getTotal());
//		group.setPointTotal(450);
		m.clear();
		return group;
	}

	private float getVipPoint(String vipNo) throws NestedException {
		// 读取卡号积分
		StringBuffer sql = new StringBuffer();
//		sql.append("select VIP_POINT from vip_card where vip_status in('1')");
//		sql.append(" and vip_card_no=").append(DataUtil.getSqlString(vipNo));
		sql.append("select cum_point from customer_info where vip_no=").append(DataUtil.getSqlString(vipNo));
		System.out.println(sql.toString());
		Object o = get(sql);
		return DataUtil.getFloat(o, "cum_point", 0F);
	}

	/**
	 * 获取规则列表
	 * 
	 * @return
	 * @throws NestedException
	 */
	private List getRules(String id) throws NestedException {
		List a = new ArrayList();
		StringBuffer sql = new StringBuffer();
		String currDate = DataUtil.getSqlString(DataUtil.getCurrDateTime("yyyy-MM-dd"));
		sql.append("select * from price_plan_define t1 where ");
		sql.append(" t1.eff_date<=").append(currDate);
		sql.append(" and t1.exp_date>").append(currDate);
		sql.append(" and t1.price_state='2' ");
		if (!DataUtil.isNull(id)) {
			id=id.replaceAll("vip_price", "-99999999");
			sql.append(" and t1.price_id in (");
			sql.append(id).append(")");
		} 

		
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			a.add(new Rule(objs.next()));
		}
		// System.out.println(a.size());
		Map m = DataUtil.toMap(a, "id");
		// 定义子查询
		StringBuffer subsql = new StringBuffer();
		subsql.append("(select price_id from price_plan_define where eff_date<=");
		subsql.append(currDate);
		subsql.append(" and exp_date>");
		subsql.append(currDate);
		subsql.append(" and price_state='2' ");
		if (!DataUtil.isNull(id)) {
			subsql.append(" and price_id in (");
			subsql.append(id).append(")");
		}
		subsql.append(")");
		// 读取范围
		Calendar ca = Calendar.getInstance();
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		String nowTime = hour + ":" + minute;
		sql = new StringBuffer();
		sql.append("select t1.*,t2.attr_desc as scope_value_text ");
		sql.append("from price_plan_scope t1,");
		sql.append("sys_attribute_value t2,sys_attribute t3 ");
		sql.append("where t1.scope_value=t2.attr_value and ");
		// sql.append("'"+nowTime+"'>t1.start_time and '"+nowTime+"'<t1.end_time and");
		sql.append("t3.attr_code='FOOD_TYPE' and t1.scope_type='1' and ");
		sql.append("t2.attr_id=t3.attr_id and t1.price_id in ");
		sql.append(subsql.toString());
		sql.append(" union select t1.*,t2.food_name as scope_value_text ");
		sql.append("from price_plan_scope t1,food_info t2 ");
		sql.append("where  t1.scope_type='2' and ");
		sql.append("t1.scope_value=t2.food_id and t1.price_id in ");
		sql.append(subsql.toString());
		setRuntimeStaticData("price_plan_scope");
			for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object o = objs.next();
			Rule r = (Rule) m.get(DataUtil.getObject(o, "price_id"));
			if (r != null) {
				r.add(new RuleScope(o));
			}
		}
		// 读取条件
		sql = new StringBuffer();
		sql.append("select * from price_plan_condition where ");
		sql.append("price_id in ").append(subsql.toString());
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object o = objs.next();
			Rule r = (Rule) m.get(DataUtil.getObject(o, "price_id"));
			if (r != null) {
				r.add(new RuleCondition(o));
			}
		}
		// 读取取值
		sql = new StringBuffer();
		sql.append("select * from price_plan_value where ");
		sql.append("price_id in ").append(subsql.toString());
		sql.append(" order by price_unit asc");
		setRuntimeStaticData("price_plan_value");
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object o = objs.next();
			Rule r = (Rule) m.get(DataUtil.getObject(o, "price_id"));
			if (r != null) {
				r.add(new RuleValue(o));
			} else {
			}
		}
		m.clear();
		return a;
	}

	/**
	 * 是否有vip卡
	 * 
	 * @param orderNo
	 * @return
	 * @throws NestedException
	 */
	private void isVIP(OrderGroup og) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from vip_card where vip_card_no='");
		sql.append(og.getVipCardNo()).append("'");
		int vip_discount = 100;
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object o = objs.next();
			// String vip_discount_str=DataUtil.getString(o, "vip_discount");
			String vipLevel = DataUtil.getString(o, "vip_level");
			// if(vip_discount_str != null && !vip_discount_str.equals("")){
			// vip_discount = Integer.parseInt(vip_discount_str);
			// }
		}
		// og.setVipDiscount(vip_discount);
	}

	private boolean isVIP(String id) {
		String[] temp = id.split(",");
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].equals("null")) {
				return true;
			}
		}
		return false;
	}

	private void addVIP(OrderGroup og, List rules) throws NestedException {
		StringBuffer sql = new StringBuffer();
		// System.out.println(og.getVipCardNo());
		sql.append("select * from vip_card where vip_card_no='");
		sql.append(og.getVipCardNo()).append("'");
		int vip_discount = 100;
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object o = objs.next();
			String vip_discount_str = DataUtil.getString(o, "vip_discount");
			System.out.println(vip_discount_str);
			if (vip_discount_str != null && !vip_discount_str.equals("")) {
				vip_discount = Integer.parseInt(vip_discount_str);
			}
			System.out.println(vip_discount);
			Rule rule = new Rule("vip_price");
			rule.setName("vip优惠");
			RuleValue rv = new RuleValue(null);
			rv.setPriceUnit("1");
			rv.setPriceValue(vip_discount);
			rule.add(rv);
			rule.setType("2");
			rule.setVip(true);
			// rule.add(rs);
			// ["2","12","所有","70.0%折扣"]
			rules.add(rule);
		}
		// /增加依赖互斥的判断
		sql = new StringBuffer();
		// System.out.println(og.getVipCardNo());
		sql.append("select t1.* from customer_discount_relat t1, vip_card t2 where t1.vip_level=t2.vip_level and t2.vip_card_no='");
		sql.append(og.getVipCardNo()).append("'");
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object o = objs.next();
			int inx = rules.indexOf(new Rule(o));
			if (inx < 0)
				continue;// 不存在这样的规则
			Rule r = (Rule) rules.get(inx);
			if ("0".equals(DataUtil.getString(o, "relat_type"))) {// 互斥
				if ("1".equals(r.getType()))
					continue;// 和基础优惠不互斥，即使配置和互斥关系
				rules.remove(inx);
			}
			/*
			if ("1".equals(DataUtil.getString(o, "relat_type"))) {// 依赖
				if (!DataUtil.isNull(DataUtil.getString(o, "price_id"))) {
					List aa = getRules(DataUtil.getString(o, "price_id"));
					if (aa.size() > 0 && rules.indexOf(aa.get(0)) < 0) {
						rules.add(aa.get(0));
					}
				}
			}*/
		}
	}

	private boolean isCan(OrderGroup og, String str) throws NestedException {
		String[] temp = str.split(",");
		StringBuffer sql = null;
		String vipLevel = "";
		int n = 0;
		for (int i = 0; i < temp.length; i++) {
			sql = new StringBuffer();
			sql.append("select price_type from price_plan_define where price_id='").append(temp[i]).append("'");
			for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
				Object o = objs.next();
				String relaType = DataUtil.getString(o, "price_type");
				if ("2".equals(relaType)) {
					n++;
				}
			}
		}
		if (n > 1) {
			return false;
		}
		if (str.contains("null")) {
			sql = new StringBuffer();
			sql.append("select * from vip_card where vip_card_no='");
			sql.append(og.getVipCardNo()).append("'");
			for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
				Object o = objs.next();
				vipLevel = DataUtil.getString(o, "vip_level");
			}
			for (int i = 0; i < temp.length; i++) {
				String pid = temp[i];
				System.out.println(pid.equals("null"));
				if (pid.equals("null")) {
					continue;
				}
				sql = new StringBuffer();
				sql.append("select relat_type from customer_discount_relat where vip_level='").append(vipLevel).append("'");
				sql.append(" and price_id='").append(pid).append("'");
				System.out.println(sql);
				Iterator objs = getAll(sql).iterator();
				if (!objs.hasNext()) {
					return false;
				}
				for (; objs.hasNext();) {
					Object o = objs.next();
					String relaType = DataUtil.getString(o, "relat_type");
					System.out.println(relaType);
					if (!"1".equals(relaType)) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
