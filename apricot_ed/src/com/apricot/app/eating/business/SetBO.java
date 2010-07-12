/**
 * 
 */
package com.apricot.app.eating.business;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import weblogic.i18n.tools.MessageFinder;

import com.apricot.eating.ApricotApp;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.util.DataUtil;
import com.apricot.eating.util.Printer;

/**
 * @author Administrator
 */
public class SetBO extends BO {
	/**
	 * 
	 */
	public SetBO() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	/**
	 * ¿Í»§µã²Ë
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 * @throws UnsupportedEncodingException 
	 */
	public Object customerOrderFoods(DyncParameterMap map)
			throws NestedException, UnsupportedEncodingException {
		List order = (List) map.get("order_id");
	
		String sql = ApricotApp.arrayJoin(new String[] {
				"insert into order_list(",
				"order_id,food_id,food_count,order_list_id,",
				"modify_staff_id,food_price,food_action,serving_flag,food_memo)",
				"values({0},{1},{2},{3},{4},{5},'1','0',{6})" }, " ");
	
		for (Iterator ids = order.iterator(); ids.hasNext();) {
			List orderList = (List) map.get("order_list");
			DefaultTableModel table = new DefaultTableModel(orderList.size(),3);
			
			String orderId = (String) ids.next();
			int i = 0;
			for (Iterator lists = orderList.iterator(); lists.hasNext();) {
				Object o = lists.next();
//				System.out.println("food_count"+DataUtil.getString(o, "food_id"));
				String s=DataUtil.getString(o, "food_memo");
				if(DataUtil
					.isNull(s)){
				    s="";
				}else{
				    s=java.net.URLDecoder.decode(s, "UTF-8");
				}
				String[] args = new String[] { orderId,
						DataUtil.getString(o, "food_id"),
						DataUtil.getString(o, "food_num")==null?"1":DataUtil.getString(o, "food_num"),
						String.valueOf(getMax("order_list", "order_list_id")),
						map.getString("staff_id"),
						DataUtil.getString(o, "food_price"),"'"+s +"'"};
				update(new StringBuffer(MessageFormat.format(sql, args)));
				
				args = null;
				String text = DataUtil.getString(o, "food_name");
				
				table.setValueAt(java.net.URLDecoder.decode(text, "UTF-8"), i, 0);
				table.setValueAt(DataUtil.getString(o, "food_count"), i, 1);
				table.setValueAt(DataUtil.getString(o, "food_price"), i, 2);
				i ++;
			}

			
		}

        order.clear();
		return null;
	}

	public Object getOrderBusy(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.* from order_info t1,");
		sql.append("dining_set_info t2 where ");
		sql.append("t1.set_no=t2.set_no and t1.set_no=");
		sql.append(map.getString("set_no", "-1"));
		sql.append(" and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		sql.append(" and t1.order_status='0'");
		setRuntimeStaticData("order_info");
		setRuntimeStaticData("dining_set_info");
		return get(sql);
	}

	public Object getSetImages(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,'set.free' as set_st from dining_set_info t1 where t1.set_status='1' and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		sql.append(" and t1.belong_to=");
		sql.append(map.getSqlString("belong_to", "01"));
		setRuntimeStaticData("DINING_SET_INFO");
		List l = getAll(sql);
		List al = new ArrayList();
		HashMap m = new HashMap();
		m.put("images", al);
		Map temp = DataUtil.toMap(l, "set_no");
		
//		free
		/**
		sql = new StringBuffer("select distinct 'set.free' as ");
		sql.append("set_st ,set_no,order_no,order_id from order_info");
		sql.append(" where order_status='2' ");
//		sql.append("or (order_status='2' and date_add(PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<now() and DATE_ADD(PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) >now()) ");
		sql.append(" and sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		sql.append(" group by set_no");
		l = getAll(sql);
		for (Iterator objs = l.iterator(); objs.hasNext();) {
			Object o = objs.next();
			Object old = temp.get(DataUtil.getObject(o, "set_no"));
			if (old == null)
				continue;
			DataUtil.setObject(old, "order_no", DataUtil.getString(o, "order_no"));
			DataUtil.setObject(old, "order_id", DataUtil.getString(o, "order_id"));
			
			DataUtil.copyProperties(old, o, new String[] { "set_st" });
		}
		*/
//		 per
		sql = new StringBuffer("select distinct 'set.pre' as ");
		sql.append("set_st ,set_no,order_no,order_id from order_info");
		sql.append(" where order_status='2' and date_add(PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<now() and DATE_ADD(PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) >now() ");
		sql.append(" and sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		sql.append(" group by set_no");
		l = getAll(sql);
		for (Iterator objs = l.iterator(); objs.hasNext();) {
			Object o = objs.next();
			Object old = temp.get(DataUtil.getObject(o, "set_no"));
			if (old == null)
				continue;
			DataUtil.setObject(old, "order_no", DataUtil.getString(o, "order_no"));
			DataUtil.setObject(old, "order_id", DataUtil.getString(o, "order_id"));
			DataUtil.copyProperties(old, o, new String[] { "set_st" });
		}

		// Busy
		sql = new StringBuffer("select distinct 'set.busy' as ");
		sql.append("set_st ,set_no,order_no,order_id from order_info");
		sql.append(" where order_status='0' ");
//		sql.append("or (order_status='2' and date_add(PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<now() and DATE_ADD(PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) >now()) ");
		sql.append(" and sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		sql.append(" group by set_no");
		l = getAll(sql);
		for (Iterator objs = l.iterator(); objs.hasNext();) {
			Object o = objs.next();
			Object old = temp.get(DataUtil.getObject(o, "set_no"));
			if (old == null)
				continue;
			DataUtil.setObject(old, "order_no", DataUtil.getString(o, "order_no"));
			DataUtil.setObject(old, "order_id", DataUtil.getString(o, "order_id"));
			DataUtil.copyProperties(old, o, new String[] { "set_st" });
		}
		
		// All
		for (Iterator objs = temp.values().iterator(); objs.hasNext();) {
			Object c = createObject();
			Object o = objs.next();
			StringBuffer name = new StringBuffer();
			name.append(getMessage("floor_00"+DataUtil.getString(o, "dining_floor"),null));
			name.append("¡ª");
			name.append(DataUtil.getString(o, "balcony_name"));
//			name.append(DataUtil.getString(o, "balcony_code"));
			name.append("<br>");
			name.append(getMessage("mincost_00"+DataUtil.getString(o, "mincost_type"), null));
			name.append(DataUtil.getString(o, "mincost_money"));
			name.append("<br>");
//			name.append(getMessage(DataUtil.getString(o, "set_st"), null));// Free
			DataUtil.setObject(c, "name", name.toString());
			DataUtil.setObject(c, "staff_id", map.getString("staff_id"));
			DataUtil.setObject(c, "staff_name", map.getString("staff_name"));
			DataUtil.setObject(c, "mincost_type", DataUtil.getString(o, "mincost_type"));
			DataUtil.setObject(c, "mincost_type_text", getMessage("mincost_00"+DataUtil.getString(o, "mincost_type"), null));
			
			DataUtil.setObject(c, "mincost_money", DataUtil.getString(o, "mincost_money"));
			DataUtil.setObject(c, "set_no", DataUtil.getString(o, "set_no"));
			DataUtil.setObject(c, "balcony_name", DataUtil.getString(o, "balcony_name"));
			DataUtil.setObject(c, "set_st", DataUtil.getString(o, "set_st"));
			DataUtil.setObject(c, "belong_to", DataUtil.getString(o, "belong_to"));
			DataUtil.setObject(c, "order_no", DataUtil.getString(o, "order_no"));
			DataUtil.setObject(c, "order_id", DataUtil.getString(o, "order_id"));
			DataUtil.setObject(c, "dining_floor_text", DataUtil.getString(o,
					"dining_floor_text"));
			if ("set.free".equals(DataUtil.getString(o, "set_st"))) {
				DataUtil.setObject(c, "url", "img/set/set_free.gif");
			} else if("set.pre".equals(DataUtil.getString(o, "set_st")))
			{
				DataUtil.setObject(c, "url", "img/set/set_pre.gif");
			}
			else{
				DataUtil.setObject(c, "url", "img/set/set_busy.gif");
			}
			al.add(c);
		}
		DataUtil.sortAsc(al, "set_no");
		temp.clear();
		l.clear();
		return m;
	}

	/**
	 * Get all free set list.
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object getFreeSet(DyncParameterMap map) throws NestedException {
		String currDt1 = DataUtil.getCurrDateTime(3); // later 3 hour
		String currDt2 = DataUtil.getCurrDateTime();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from dining_set_info where ");
		sql.append("set_no not in (select set_no from ");
		sql.append("order_info where (order_time<");
		sql.append(DataUtil.toSqlDateTime(currDt1));
		sql.append(" and order_time>=");
		sql.append(DataUtil.toSqlDateTime(currDt2));
		sql.append(" and order_status='2') or order_status='0')");
		if (!map.isNull("set_no")) {
			sql.append(" and set_no in (");
			sql.append(map.getString("set_no"));
			sql.append(")");
		}
		return showPages(sql.toString(), 0, -1);
	}
}
