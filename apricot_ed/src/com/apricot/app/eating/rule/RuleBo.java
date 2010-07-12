/**
 * 
 */
package com.apricot.app.eating.rule;

import java.util.*;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;
import com.apricot.webservice.bean.Plan;
import com.apricot.webservice.bean.Row;
import com.apricot.webservice.impl.ClientImpl;
/**
 * @author Administrator
 */
public class RuleBo extends BO {
	/**
	 * 
	 */
	public RuleBo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create a rule
	 * 
	 * @param map
	 * @throws NestedException
	 */
	public void create(DyncParameterMap map) throws NestedException {
		Table tab = getTable("price_plan_define");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("state_date", new Date());// Current datetime
		map.set("price_id", getMax("price_plan_define", "price_id"));
		execute(tab);
	}

	/**
	 * Modify a rule
	 * 
	 * @param map
	 * @throws NestedException
	 */
	public void modify(DyncParameterMap map) throws NestedException {
		Table tab = getTable("price_plan_define");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		map.set("state_date", new Date());// Current datetime
		execute(tab);
		
		ClientImpl cli = new ClientImpl(map.getString("service_ip"));
		List list = new ArrayList();
		Plan plan = new Plan();
		map.getString("sf_id");
		plan.setPlanName(map.getString("price_name"));
		plan.setBeginTime(map.getString("eff_date"));
		plan.setEndTime(map.getString("exp_date"));
		plan.setStatus(map.getString("price_state"));
		Row row = new Row(plan,new String[]{"planName","beginTime","endTime","status"},
				new String[]{"f1","f2","f3","f4"});
		list.add(row);
		cli.syncDiscounts(map.getString("shopId"), "modify", list);
	}

	public void valueSave(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from price_plan_value where price_id=");
		sql.append(map.getString("price_id", "-1"));
		update(sql);
		Table tab = getTable("price_plan_value");
		tab.setSqlType(Table.INSERT);
		tab.setParameterMap(map);
		executeAll(tab);
	}

	public Object allScope(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.attr_desc as scope_value_text ");
		sql.append("from price_plan_scope t1,");
		sql.append("sys_attribute_value t2,sys_attribute t3 ");
		sql.append("where t1.scope_value=t2.attr_value and ");
		sql.append("t3.attr_code='FOOD_TYPE' and t1.scope_type='1' and ");
		sql.append("t2.attr_id=t3.attr_id and t1.price_id=");
		sql.append(map.getString("price_id", "-1"));
		sql.append(" union select t1.*,t2.food_name as scope_value_text ");
		sql.append("from price_plan_scope t1,food_info t2 ");
		sql.append("where  t1.scope_type='2' and ");
		sql.append("t1.scope_value=t2.food_id and t1.price_id=");
		sql.append(map.getString("price_id", "-1"));
		////
		sql.append(" union select t1.*,t2.balcony_name as scope_value_text ");
		sql.append("from price_plan_scope t1,dining_set_info t2 ");
		sql.append("where t1.scope_value=t2.balcony_code and ");
		sql.append("t1.scope_type='3' and ");
		sql.append(" t1.price_id=");
		sql.append(map.getString("price_id", "-1"));
		setRuntimeStaticData("price_plan_scope");
		
		return showPages(sql.toString(), 0, -1);
	}

	public Object allCond(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from price_plan_condition ");
		sql.append("where  price_id=");
		sql.append(map.getString("price_id", "-1"));
		setRuntimeStaticData("price_plan_condition");
		return showPages(sql.toString(), 0, -1);
	}

	public Object allValue(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from price_plan_value ");
		sql.append("where  price_id=");
		sql.append(map.getString("price_id", "-1"));
		setRuntimeStaticData("price_plan_value");
		return showPages(sql.toString(), 0, -1);
	}
	public Object allValue1(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from food_info ");
		if(!DataUtil.isNull(map.getString("scope_value"))){
		sql.append("where  food_type=");
		sql.append(map.getString("scope_value"));
		}
		setRuntimeStaticData("food_info");
		return showPages(sql.toString(), 0, -1);
	}
	public Object allValue2(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from food_info ");
		
		setRuntimeStaticData("food_info");
		return showPages(sql.toString(), 0, -1);
	}
	public Object mutInsert(DyncParameterMap map) throws NestedException {
		StringBuffer sql=new StringBuffer();
		sql.append("select scope_id from price_plan_scope");
		Table table = getTable("price_plan_scope");
		table.setSqlType(Table.INSERT);
		table.setParameterMap(map);
		//map.set("scope_id", getMax("price_plan_scope", "scope_id"));
		String s=map.getString("scope_value");
		String[] s1=s.split(",");
		for(int j=0;j<s1.length;j++){
			map.set("scope_id", getMax("price_plan_scope", "scope_id"));
			map.set("scope_value", s1[j]);
			execute(table);
		}
		
		return null;
	}
	public Object pMutInsert(DyncParameterMap map) throws NestedException {
		StringBuffer sql=new StringBuffer();
		sql.append("select scope_id from price_plan_scope");
		Table table = getTable("price_plan_scope");
		table.setSqlType(Table.INSERT);
		table.setParameterMap(map);
		//map.set("scope_id", getMax("price_plan_scope", "scope_id"));
		String s=map.getString("scope_value");
		String[] s1=s.split(",");
		for(int j=0;j<s1.length;j++){
			map.set("scope_id", getMax("price_plan_scope", "scope_id"));
			map.set("scope_value", s1[j]);
			execute(table);
		}
		
		return null;
	}
}
