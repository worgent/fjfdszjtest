/**
 * 
 */
package com.apricot.app.eating;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.StaticDataSet;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public class StaticDataBO extends BO {
	/**
	 * 
	 */
	public StaticDataBO() {
		// TODO Auto-generated constructor stub
	}

	public Object get(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.attr_value,t1.attr_desc from sys_attribute_value t1,");
		sql.append("sys_attribute t2 where t1.attr_id=t2.attr_id and t2.attr_code='");
		sql.append(map.getString(StaticDataSet.ATTRIBUTE_CODE_KEY)).append("' ");
		sql.append("order by t1.attr_sort asc");
		System.out.println(sql.toString());
		return new StaticDataSet(getAll(sql));
	}

	public Object getGlobalStaticData(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.table_name,t1.table_column,t2.attr_value,t2.attr_desc ");
		sql.append("from sys_attribute_value t2,sys_table_attribute t1 ");
		sql.append("where t2.attr_id=t1.attr_id");
		PreparedStatement stmt = null;
		ResultSet res = null;
		HashMap m = new HashMap();
		try {
			stmt = prepareStatement(sql);
			res = stmt.executeQuery();
			while (res.next()) {
				String tabName = res.getString("table_name").toLowerCase();
				HashMap o = (HashMap) m.get(tabName);
				if (o == null) {
					o = new HashMap();
					m.put(tabName, o);
				}
				String colName = res.getString("table_column").toLowerCase();
				Object c = o.get(colName);
				if (c == null) {
					c = super.createObject();
					o.put(colName, c);
				}
				// System.out.println(colName+"="+res.getString("attr_value")+"="+res.getString("attr_desc"));
				DataUtil.setObject(c, res.getString("attr_value"), res.getString("attr_desc"));
				c = null;
				o = null;
				tabName = null;
				colName = null;
			}
		} catch (Exception e) {
		} finally {
			closeCursor(stmt, res);
		}
		return m;
	}
}
