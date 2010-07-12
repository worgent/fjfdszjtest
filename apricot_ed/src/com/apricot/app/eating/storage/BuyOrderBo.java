/**
 * 
 */
package com.apricot.app.eating.storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class BuyOrderBo extends BO {
	/**
	 * 
	 */
	public BuyOrderBo() {
		// TODO Auto-generated constructor stub
	}
	
	public void buyOrderHistory(DyncParameterMap map) throws NestedException {
		map.set("history_id", String.valueOf(getMax("buy_order_history", "history_id")));
		map.set("buy_id", map.getInt("buy_id"));
		map.set("buy_state", map.getInt("buy_state"));
		map.set("record_staff_id", map.getInt("staff_id"));
		map.set("next_staff_id", map.getInt("record_staff_id"));
		map.set("create_time", DataUtil.getCurrDateTime());
		map.set("record_desc", map.getString("record_desc"));
		
		Table tab = getTable("buy_order_history");
		tab.setSqlType(Table.INSERT);
		tab.setParameterMap(map);

		execute(tab);
	}
	
	public void updateBuyOrder(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer("update buy_order_info set buy_state='"+map.getString("buy_state")+"'");
//		if(!DataUtil.isNull(map.getString("buy_date"))){
//			sql.append(",buy_date='"+map.getString("buy_date")+"'");
//		}
//		if(!DataUtil.isNull(map.getString("buy_staff_id"))){
//			sql.append(",buy_staff_id="+map.getString("buy_staff_id"));
//		}
//		if(!DataUtil.isNull(map.getString("record_staff_id"))){
//			sql.append(",RECORD_STAFF_ID="+map.getString("record_staff_id"));
//		}
//		if(!DataUtil.isNull(map.getString("total_money"))){
//			sql.append(",TOTAL_MONEY="+map.getDouble("total_money"));
//		}
		if(!DataUtil.isNull(map.getString("record_desc"))){
			sql.append(",RECORD_DESC='"+map.getString("record_desc")+"'");
		}
		sql.append(" where buy_id="+map.getString("buy_id"));
		update(sql);
		System.out.println(sql);
		buyOrderHistory(map);
	}

	public Object allNoCheck(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer("select t1.*,t2.staff_name,t3.dept_name ");
		sql.append("from buy_order_info t1,staff_info t2,dept_info t3 where ");
		sql.append("t1.record_staff_id=t2.staff_id and t1.record_dept_id=t3.dept_id and ");
		sql.append("t1.buy_state='1' and t1.buy_staff_id=");
		sql.append(map.getString("staff_id", "-1"));
		sql.append(" and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		return showPages(sql.toString(), 0, -1);
	}

	public Object allWaitCheck(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer("select t1.*,t2.staff_name,t3.dept_name ");
		sql.append("from buy_order_info t1,staff_info t2,dept_info t3 where ");
		sql.append("t1.buy_staff_id=t2.staff_id and t1.record_dept_id=t3.dept_id and ");
		sql.append("t1.buy_state!='1' and t1.record_staff_id=");
		sql.append(map.getString("staff_id", "-1"));
		sql.append(" and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));

		return showPages(sql.toString(), 0, -1);
	}

	public Object allWaitStorage(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.staff_name,t3.staff_name as r_name,t4.dept_name ");
		sql.append("from buy_order_info t1,staff_info t2,");
		sql.append("staff_info t3,dept_info t4 where ");
		sql.append("t1.buy_staff_id=t2.staff_id and t1.record_dept_id=t4.dept_id and ");
		sql.append("t1.record_staff_id=t3.staff_id and ");
		sql.append("t1.buy_state='4' ");
		sql.append(" and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
	
		return showPages(sql.toString(), 0, -1);
	}
	
	public Object realTimeMaterial(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.mat_name,t2.mat_measure_unit,t1.PRE_BUY_NUM*t1.PRE_BUY_PRICE as all_price,t1.BUY_NUM*t1.BUY_PRICE as buyed_price from ");
		sql.append("buy_order_list t1,material_info t2 ");
		sql.append("where t1.mat_id=t2.mat_id and ");
		sql.append("t1.buy_id=");
		sql.append(map.getString("buy_id", "-1"));
		setRuntimeStaticData("material_info");
		return showPages(sql.toString(), 0, -1);
	}

	public Object allMaterial(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.mat_name,t2.mat_measure_unit,t1.PRE_BUY_NUM*t1.PRE_BUY_PRICE as all_price,t1.BUY_NUM*t1.BUY_PRICE as buyed_price from ");
		sql.append("buy_order_list t1,material_info t2 ");
		sql.append("where t1.mat_id=t2.mat_id and ");
		sql.append("t1.buy_id=");
		sql.append(map.getString("buy_id", "-1"));
		setRuntimeStaticData("material_info");
		return showPages(sql.toString(), 0, -1);
	}
	
	public Object allMaterialExcute(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer("select t1.*,t2.staff_name,t3.dept_name ");
		sql.append("from buy_order_info t1,staff_info t2,dept_info t3 where ");
		sql.append("t1.record_staff_id=t2.staff_id and t1.record_dept_id=t3.dept_id and ");
		sql.append("t1.buy_state='6' and t1.buy_staff_id=");
		sql.append(map.getString("staff_id", "-1"));
		sql.append(" and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		return showPages(sql.toString(), 0, -1);
	}
	

	public void create(DyncParameterMap map) throws NestedException {
		map.set("buy_no", DataUtil.getNo());
		map.set("buy_id", getMax("buy_order_info", "buy_id"));
		map.set("record_time", DataUtil.getCurrDateTime());
		map.set("buy_staff_id", map.getString("staff_id"));
		if (map.isNull("total_money"))
			map.set("total_money", "0");
		execute("buy_order_info", map, Table.INSERT);
	}
	
	//提交采购单的实际采购量
	public void updateOrderList(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("update buy_order_list ");
		sql.append("set BUY_NUM="+map.getInt("buy_num"));
		sql.append(",BUY_PRICE="+map.getDouble("buy_price"));
		sql.append(" where DETAIL_ID='"+map.getString("detail_id")+"'");
		update(sql);
	}

	/**
	 * Put in storage
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object inStorage(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.record_dept_id as dept_id from buy_order_list t1,buy_order_info t2 ");
		sql.append("where t1.buy_id=t2.buy_id and t1.buy_id=").append(map.getString("buy_id", "-1"));
		System.out.println(sql);
		List a = getAll(sql);
		List ss = new ArrayList();
		if (a.size() == 0)
			return "\u5f53\u524d\u91c7\u8d2d\u5355\u6ca1\u6709\u660e\u7ec6\uff0c\u4e0d\u9700\u8981\u5165\u5e93\u3002";
		for (Iterator objs = a.iterator(); objs.hasNext();) {
			Object o = objs.next();
			sql = new StringBuffer("select stog_count,avg_price,dept_id,'M' as op_code from storage_info ");
			sql.append("where mat_id=").append(DataUtil.getString(o, "mat_id"));
			sql.append(" and dept_id=").append(DataUtil.getString(o,"dept_id"));
			sql.append(" and sf_id=").append(map.getString("sf_id", "-1"));
			System.out.println(sql);
			set(sql, o);
			if ("M".equals(DataUtil.getObject(o, "op_code"))) {
				float ct = DataUtil.getFloat(o, "buy_num", 0F) + DataUtil.getFloat(o, "stog_count", 0F);
				float total = (DataUtil.getFloat(o, "buy_num", 0F) * DataUtil.getFloat(o, "buy_price", 0F))
						+ (DataUtil.getFloat(o, "stog_count", 0F) * DataUtil.getFloat(o, "avg_price", 0F));
				float avgPrice = total / ct;
				sql = new StringBuffer();
				sql.append("update storage_info set ");
				sql.append("stog_count=").append(ct).append(",");
				sql.append("last_price=").append(DataUtil.getString(o, "buy_price")).append(",");
				sql.append("avg_price=").append(avgPrice).append(" where ");
				sql.append(" mat_id=").append(DataUtil.getString(o, "mat_id"));
				sql.append(" and dept_id=").append(DataUtil.getString(o, "dept_id"));
				sql.append(" and sf_id=").append(map.getString("sf_id", "-1"));
				System.out.println(sql);
				ss.add(sql.toString());
				continue;
			}
			sql = new StringBuffer();
			sql.append("insert into storage_info");
			sql.append("(mat_id,sf_id,stog_count,last_price,avg_price,dept_id)");
			sql.append(" values(");
			sql.append(DataUtil.getString(o, "mat_id")).append(",");
			sql.append(map.getString("sf_id")).append(",");
			sql.append(DataUtil.getString(o, "buy_num")).append(",");
			sql.append(DataUtil.getString(o, "buy_price")).append(",");
			sql.append(DataUtil.getString(o, "buy_price")).append(",");
			sql.append(DataUtil.getString(o, "dept_id")).append(")");
			System.out.println(sql);
			ss.add(sql.toString());

		}
		// Record log
		sql = new StringBuffer();
		sql.append("insert into storage_log");
		sql.append("(buy_id,store_staff_id,store_date,store_memo,log_id)");
		sql.append(" values(");
		sql.append(map.getString("buy_id")).append(",");
		sql.append(map.getString("staff_id")).append(",");
		sql.append("'").append(DataUtil.getCurrDateTime()).append("',");
		sql.append("'").append(map.getString("store_memo")).append("',");
		sql.append(getMax("storage_log", "log_id")).append(")");
		System.out.println(sql);
		ss.add(sql.toString());
		// Change order state
		sql = new StringBuffer();
		sql.append("update buy_order_info set buy_state='2' where buy_id=");
		sql.append(map.getString("buy_id", "-1"));
		ss.add(sql.toString());
		System.out.println(sql);
		// Batch commit;
		updateBatch((String[]) ss.toArray(new String[ss.size()]));
		return null;
	}
}
