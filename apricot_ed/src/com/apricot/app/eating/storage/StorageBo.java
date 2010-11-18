/**
 * 
 */
package com.apricot.app.eating.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DataSet;
import com.apricot.eating.engine.DyncParameterGetter;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class StorageBo extends BO {
	/**
	 * 
	 */
	public StorageBo() {
		// TODO Auto-generated constructor stub
	}

	public Object checkSave(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from check_info where ");
		sql.append("sf_id=").append(map.getString("sf_id", "-1"));
		sql.append(" and check_no=").append(map.getString("check_no"));//map.getString("check_no")
		if (getInt(sql) > 0) {
			return getMessage("storage.check.havechecked", new String[] { map.getString("check_no") });
		}
		Table tab = getTable("check_info");
		tab.setSqlType(Table.INSERT);
		tab.setParameterMap(map);
		map.set("check_id", getMax("check_info", "check_id"));
		map.set("check_time", DataUtil.getCurrDateTime());
		execute(tab);
		tab = getTable("check_detail");
		tab.setSqlType(Table.INSERT);
		tab.setParameterMap(map);
		map.setGetter("mat_num", new DyncParameterGetter() {
			public String getString(DyncParameterMap map, String name, int i) {
				// TODO Auto-generated method stub
				double d = map.getDouble("stog_count", i) + map.getDouble("adjust_total", i) - map.getDouble("sale_total", i);
				System.out.println(map.getDouble("mat_id", i) + ":" + map.getDouble("sale_total", i));
				try
				{
					StringBuffer sql = new StringBuffer();
					sql.append("update storage_info set stog_count="+d+" where mat_id="+map.getInt("mat_id", i));
					System.out.println(sql);
					update(sql);
				}catch(Exception ex)
				{}
				return String.valueOf(d);
			}
		});
		executeAll(tab);
		return null;
	}
	
	public Object realTimeStorage(DyncParameterMap map) throws NestedException {
		Date d = DataUtil.parseDate(map.getString("check_no"), "yyyyMMdd");
		String sD = DataUtil.format(d, "yyyy-MM-dd 00:00:00");
		String eD = DataUtil.format(d, "yyyy-MM-dd 23:59:59");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from check_info order by check_no desc");
		List ll = getAll(sql);
		String checkNo = "";
		if (ll.size() > 0) {//
			Object obj = ll.get(0);
			checkNo = DataUtil.getString(obj, "check_no");
		}
		sql = new StringBuffer();
		sql.append("select t1.*,t2.mat_name,t3.check_no ");
		sql.append("from check_detail t1,material_info t2,check_info t3 ");
		sql.append("where t1.mat_id=t2.mat_id and ");
		sql.append("t1.check_id=t3.check_id and t3.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		sql.append(" and t3.check_no=").append(checkNo);
		System.out.println("sql1:"+sql);
		List l = getAll(sql);

		if (l.size() > 0) {//
			return new DataSet("0", l);
		}
		
		//盘点后采购量		
		sql = new StringBuffer();
		sql.append("select t1.*,t2.mat_name,t3.dept_name,0 as buy_total, ");
		sql.append("0 as storage_total,0 as sale_total, ");
		sql.append("0 as return_total,0 as adjust_total ");
		sql.append("from storage_info t1,material_info t2,dept_info t3 ");
		sql.append("where t1.mat_id=t2.mat_id and t1.dept_id=t3.dept_id and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		System.out.println("sql2:"+sql);
		Map m = DataUtil.toMapSpecial(getAll(sql), "mat_id","dept_id");
		// Buy
		sql = new StringBuffer();
		sql.append("select t1.mat_id,sum(t1.buy_num) as buy_total,t3.dept_name");
		sql.append(" from buy_order_list t1,buy_order_info t2,dept_info t3 where ");
		sql.append("t1.buy_id=t2.buy_id and t2.record_dept_id=t3.dept_id and t2.buy_state in ('4','2') ");
		sql.append(" and t2.buy_date=").append(map.getSqlString("check_no"));
		sql.append(" and t2.sf_id=").append(map.getString("sf_id", "-1"));
		sql.append(" group by t1.mat_id,t2.record_dept_id");
		System.out.println("sql3:"+sql);

		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object t = objs.next();
			Object o = m.get(DataUtil.getObjectSpecial(t, "mat_id","dept_id"));
			DataUtil.copyProperties(o, t, new String[] { "buy_total" });
		}
		// In storage
		sql = new StringBuffer();
		sql.append("select t1.mat_id,sum(t1.buy_num) as storage_total,t3.dept_name");
		sql.append(" from buy_order_list t1,buy_order_info t2,dept_info t3 where ");
		sql.append("t1.buy_id=t2.buy_id and t2.record_dept_id=t3.dept_id and t2.buy_state='2' ");
		sql.append(" and t2.buy_date=").append(map.getSqlString("check_no"));
		sql.append(" and t2.sf_id=").append(map.getString("sf_id", "-1"));
		sql.append(" group by t1.mat_id,t2.record_dept_id");
		System.out.println("sql4:"+sql);
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object t = objs.next();
			Object o = m.get(DataUtil.getObjectSpecial(t, "mat_id","dept_id"));
			DataUtil.copyProperties(o, t, new String[] { "storage_total" });
		}
		// Sale
		sql = new StringBuffer();
		sql.append("select t1.mat_id,sum(t1.mat_total) as sale_total");
		sql.append(" from food_material_info t1,food_info t2,");
		sql.append("order_list t3,order_info t4 where ");
		sql.append("t1.food_id=t2.food_id and t2.food_id=t3.food_id ");
		sql.append("and t3.order_id=t4.order_id and ");
		sql.append("t4.order_status='2' and t3.food_action not in ('3','9')");
		sql.append(" and t4.order_time>=");
		sql.append(DataUtil.toSqlDateTime(sD));
		sql.append(" and t4.order_time<=").append(DataUtil.toSqlDateTime(eD));
		sql.append(" and t4.sf_id=").append(map.getString("sf_id", "-1"));
		sql.append(" group by t1.mat_id");
		System.out.println("sql5:"+sql);
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object t = objs.next();
			Object o = m.get(DataUtil.getObjectSpecial(t, "mat_id","dept_id"));
			DataUtil.copyProperties(o, t, new String[] { "sale_total" });
		}
		// Return
		sql = new StringBuffer();
		sql.append("select t1.mat_id,sum(t1.mat_total) as return_total");
		sql.append(" from food_material_info t1,food_info t2,");
		sql.append("order_list t3,order_info t4 where ");
		sql.append("t1.food_id=t2.food_id and t2.food_id=t3.food_id");
		sql.append(" and t3.order_id=t4.order_id and ");
		sql.append("t4.order_status='2' and t3.food_action='3' and t4.order_time>=");
		sql.append(DataUtil.toSqlDateTime(sD));
		sql.append(" and t4.order_time<=").append(DataUtil.toSqlDateTime(eD));
		sql.append(" and t4.sf_id=").append(map.getString("sf_id", "-1"));
		sql.append(" group by t1.mat_id");
		System.out.println("sql6:"+sql);
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object t = objs.next();
			Object o = m.get(DataUtil.getObjectSpecial(t, "mat_id","dept_id"));
			DataUtil.copyProperties(o, t, new String[] { "sale_total" });
		}
		return new DataSet("0", new ArrayList(m.values()));
	}
	
	public Object getNoCheckStorageNew(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.mat_name from check_detail t1,material_info t2 ");
		sql.append("where t1.mat_id=t2.mat_id and t1.check_id = (select max(check_id) from check_info");
		sql.append(" where sf_id=");
		sql.append(map.getString("sf_id", "-1")+")");
		List l = getAll(sql);
		if (l.size() > 0) {//
			return new DataSet("0", l);
		}
		
		sql = new StringBuffer();
		
		Map m = DataUtil.toMapSpecial(getAll(sql), "mat_id","dept_id");
		
		return new DataSet("0", new ArrayList(m.values()));
	}

	public Object getNoCheckStorage(DyncParameterMap map) throws NestedException {
		Date d = DataUtil.parseDate(map.getString("check_no"), "yyyyMMdd");
		String sD = DataUtil.format(d, "yyyy-MM-dd 00:00:00");
		String eD = DataUtil.format(d, "yyyy-MM-dd 23:59:59");
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.mat_name,t3.check_no ");
		sql.append("from check_detail t1,material_info t2,check_info t3 ");
		sql.append("where t1.mat_id=t2.mat_id and ");
		sql.append("t1.check_id=t3.check_id and t3.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		sql.append(" and t3.check_no=").append(map.getString("check_no","-1"));
		List l = getAll(sql);

		if (l.size() > 0) {//
			return new DataSet("0", l);
		}
		//
		sql = new StringBuffer();
		sql.append("select t1.*,t2.mat_name,t3.dept_name,0 as buy_total, ");
		sql.append("0 as storage_total,0 as sale_total, ");
		sql.append("0 as return_total,0 as adjust_total ");
		sql.append("from storage_info t1,material_info t2,dept_info t3 ");
		sql.append("where t1.mat_id=t2.mat_id and t1.dept_id=t3.dept_id and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		Map m = DataUtil.toMapSpecial(getAll(sql), "mat_id","dept_id");
		// Buy
		sql = new StringBuffer();
		sql.append("select t1.mat_id,sum(t1.buy_num) as buy_total,t3.dept_name");
		sql.append(" from buy_order_list t1,buy_order_info t2,dept_info t3 where ");
		sql.append("t1.buy_id=t2.buy_id and t2.record_dept_id=t3.dept_id and t2.buy_state in ('4','2') ");
		sql.append(" and t2.buy_date=").append(map.getSqlString("check_no"));
		sql.append(" and t2.sf_id=").append(map.getString("sf_id", "-1"));
		sql.append(" group by t1.mat_id,t2.record_dept_id");

		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object t = objs.next();
			Object o = m.get(DataUtil.getObjectSpecial(t, "mat_id","dept_id"));
			DataUtil.copyProperties(o, t, new String[] { "buy_total" });
		}
		// In storage
		sql = new StringBuffer();
		sql.append("select t1.mat_id,sum(t1.buy_num) as storage_total,t3.dept_name");
		sql.append(" from buy_order_list t1,buy_order_info t2,dept_info t3 where ");
		sql.append("t1.buy_id=t2.buy_id and t2.record_dept_id=t3.dept_id and t2.buy_state='2' ");
		sql.append(" and t2.buy_date=").append(map.getSqlString("check_no"));
		sql.append(" and t2.sf_id=").append(map.getString("sf_id", "-1"));
		sql.append(" group by t1.mat_id,t2.record_dept_id");
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object t = objs.next();
			Object o = m.get(DataUtil.getObjectSpecial(t, "mat_id","dept_id"));
			DataUtil.copyProperties(o, t, new String[] { "storage_total" });
		}
		// Sale
		sql = new StringBuffer();
		sql.append("select t1.mat_id,sum(t1.mat_total) as sale_total");
		sql.append(" from food_material_info t1,food_info t2,");
		sql.append("order_list t3,order_info t4 where ");
		sql.append("t1.food_id=t2.food_id and t2.food_id=t3.food_id ");
		sql.append("and t3.order_id=t4.order_id and ");
		sql.append("t4.order_status='2' and t3.food_action not in ('3','9')");
		sql.append(" and t4.order_time>=");
		sql.append(DataUtil.toSqlDateTime(sD));
		sql.append(" and t4.order_time<=").append(DataUtil.toSqlDateTime(eD));
		sql.append(" and t4.sf_id=").append(map.getString("sf_id", "-1"));
		sql.append(" group by t1.mat_id");
		System.out.println(sql);
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object t = objs.next();
			Object o = m.get(DataUtil.getObjectSpecial(t, "mat_id","dept_id"));
			DataUtil.copyProperties(o, t, new String[] { "sale_total" });
		}
		// Return
		sql = new StringBuffer();
		sql.append("select t1.mat_id,sum(t1.mat_total) as return_total");
		sql.append(" from food_material_info t1,food_info t2,");
		sql.append("order_list t3,order_info t4 where ");
		sql.append("t1.food_id=t2.food_id and t2.food_id=t3.food_id");
		sql.append(" and t3.order_id=t4.order_id and ");
		sql.append("t4.order_status='2' and t3.food_action='3' and t4.order_time>=");
		sql.append(DataUtil.toSqlDateTime(sD));
		sql.append(" and t4.order_time<=").append(DataUtil.toSqlDateTime(eD));
		sql.append(" and t4.sf_id=").append(map.getString("sf_id", "-1"));
		sql.append(" group by t1.mat_id");
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object t = objs.next();
			Object o = m.get(DataUtil.getObjectSpecial(t, "mat_id","dept_id"));
			DataUtil.copyProperties(o, t, new String[] { "sale_total" });
		}
		return new DataSet("0", new ArrayList(m.values()));
	}
}
