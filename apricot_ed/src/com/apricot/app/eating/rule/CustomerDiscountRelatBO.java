/**
 * 
 */
package com.apricot.app.eating.rule;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import weblogic.i18n.tools.MessageFinder;

import com.apricot.eating.ApricotApp;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.Column;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;

/**
 * @author Administrator
 */
public class CustomerDiscountRelatBO extends BO {
	/**
	 * 
	 */
	public CustomerDiscountRelatBO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 设置关系
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object setCustDisRelat(DyncParameterMap map) throws NestedException {

		StringBuffer ext = new StringBuffer();
		ext.append("update customer_discount_relat set relat_type=");
		ext.append(map.getString("relat_type","-1"));
		ext.append(" where vip_level=").append(map.getString("vip_level", "-1"));
		ext.append(" and price_id=").append(map.getString("price_id","-1"));
		int i = update(ext);
		if(i<=0){
			ext = new StringBuffer();
			ext.append("insert into customer_discount_relat (vip_level,price_id,relat_type) values(");
			ext.append(map.getSqlString("vip_level","-1"));
			ext.append(",");
			ext.append(map.getSqlString("price_id","-1"));
			ext.append(",");
			ext.append(map.getSqlString("relat_type"));
			ext.append(")");
			System.out.println(ext);
			update(ext);
		}
		return null;
	}

	public Object showCustDisRelat(DyncParameterMap map) throws NestedException {

		StringBuffer exist = new StringBuffer();
		exist.append("select * from customer_discount_relat ");
		exist.append("where price_id=").append(map.getSqlString("price_id"));
		System.out.println(exist);
		setRuntimeStaticData("customer_discount_relat");
		return showPages(exist.toString(), 0, -1);
	}

	/**
	 * 修改卡级别
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object modifyVipCard(DyncParameterMap map) throws NestedException {

		Table tab = getTable("customer_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		// map.set("cust_id", getMax("customer_info", "cust_id"));
		// map.set("cust_state", "0A");
		System.out.println(tab.getSQL());
		execute(tab);
		tab = getTable("customer_info_log");
		map.set("oper_type", "01");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("log_id", getMax("customer_info_log", "log_id"));
		// System.out.println( map.getString("MODIFY_STAEE"));
		map.set("create_staee", map.getString("MODIFY_STAEE"));
		map.set("create_date", map.getString("MODIFY_TIME"));
		execute(tab);

		return null;
	}

	/**
	 * 会员卡挂失
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object lockVipCard(DyncParameterMap map) throws NestedException {

		Table tab = getTable("customer_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		// map.set("cust_id", getMax("customer_info", "cust_id"));
		map.set("cust_state", "0C");
		System.out.println(tab.getSQL());
		execute(tab);
		tab = getTable("customer_info_log");
		map.set("oper_type", "03");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("log_id", getMax("customer_info_log", "log_id"));
		// System.out.println( map.getString("MODIFY_STAEE"));
		map.set("create_staee", map.getString("MODIFY_STAEE"));
		map.set("create_date", DataUtil.getCurrDateTime());
		execute(tab);

		return null;
	}

	/**
	 * 会员卡解挂
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object unLockVipCard(DyncParameterMap map) throws NestedException {

		Table tab = getTable("customer_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		// map.set("cust_id", getMax("customer_info", "cust_id"));
		map.set("cust_state", "0A");
		System.out.println(tab.getSQL());
		execute(tab);
		tab = getTable("customer_info_log");
		map.set("oper_type", "04");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("log_id", getMax("customer_info_log", "log_id"));
		// System.out.println( map.getString("MODIFY_STAEE"));
		map.set("create_staee", map.getString("MODIFY_STAEE"));
		map.set("create_date", DataUtil.getCurrDateTime());
		execute(tab);

		return null;
	}

	/**
	 * 会员卡更换
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object chageVipCard(DyncParameterMap map) throws NestedException {

		Table tab = getTable("customer_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		// map.set("cust_id", getMax("customer_info", "cust_id"));
		map.set("cust_state", "0A");
		System.out.println(tab.getSQL());
		execute(tab);
		tab = getTable("customer_info_log");
		map.set("oper_type", "07");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("log_id", getMax("customer_info_log", "log_id"));
		// System.out.println( map.getString("MODIFY_STAEE"));
		map.set("create_staee", map.getString("MODIFY_STAEE"));
		map.set("create_date", DataUtil.getCurrDateTime());
		execute(tab);

		return null;
	}

	/**
	 * 增加会员
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object addMember(DyncParameterMap map) throws NestedException {

		Table tab = getTable("customer_info");
		System.out.println(map.getString("vip_no"));
		if (!DataUtil.isNull(map.getString("vip_no"))) {
			StringBuffer ext = new StringBuffer();
			ext.append("update vip_card set vip_status='1' ");
			ext.append(" where vip_card_no=").append(
					map.getString("vip_no", "-1"));
			update(ext);
			tab = getTable("customer_info");
		}
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("cust_id", getMax("customer_info", "cust_id"));
		map.set("cust_state", "0A");

		execute(tab);
		tab = getTable("customer_info_log");
		map.set("oper_type", "02");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("log_id", getMax("customer_info_log", "log_id"));

		execute(tab);

		return null;
	}

	/**
	 * 修改会员
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object modifyMember(DyncParameterMap map) throws NestedException {

		Table tab = getTable("customer_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		// map.set("cust_id", getMax("customer_info", "cust_id"));
		// map.set("cust_state", "0A");
		System.out.println(tab.getSQL());
		execute(tab);
		tab = getTable("customer_info_log");
		map.set("oper_type", "00");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("log_id", getMax("customer_info_log", "log_id"));
		// System.out.println( map.getString("MODIFY_STAEE"));
		map.set("create_staee", map.getString("MODIFY_STAEE"));
		map.set("create_date", map.getString("MODIFY_TIME"));
		execute(tab);

		return null;
	}

	/**
	 * 删除会员
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object deleteMember(DyncParameterMap map) throws NestedException {

		Table tab = getTable("customer_info");
		tab.setSqlType(Table.DELETE);
		tab.setParameterMap(map);
		execute(tab);
		tab = getTable("customer_info_log");
		map.set("oper_type", "05");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("log_id", getMax("customer_info_log", "log_id"));
		map.set("create_staee", map.getString("sf_id"));
		map.set("create_date", DataUtil.getCurrDateTime());
		execute(tab);

		return null;
	}
}
