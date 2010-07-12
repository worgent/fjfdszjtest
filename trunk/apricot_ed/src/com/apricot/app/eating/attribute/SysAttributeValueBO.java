/**
 * 
 */
package com.apricot.app.eating.attribute;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
/**
 * 系统静态属性管理。
 * 
 * @author Administrator
 */
public class SysAttributeValueBO extends BO {
	/**
	 * 
	 */
	public SysAttributeValueBO() {
		// TODO Auto-generated constructor stub
	}

	public Object getPageList(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer("select * from sys_attribute_value where attr_id=");
		sql.append(map.getString("attr_id", "-1"));
		return super.showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object getTableAttributeMap(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.attr_code,t1.attr_name,t2.* from ");
		sql.append("sys_attribute t1,sys_table_attribute t2 where ");
		sql.append("t1.attr_id=t2.attr_id");
		sql.append(getWhereByNotNull(map, "sys_table_attribute", "t2"));
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object delete(DyncParameterMap map) throws NestedException {
		StringBuffer sql0 = new StringBuffer("delete from sys_attribute_value where attr_id=");
		sql0.append(map.getString("attr_id", "-1"));
		StringBuffer sql1 = new StringBuffer("delete from sys_table_attribute where attr_id=");
		sql1.append(map.getString("attr_id", "-1"));
		StringBuffer sql2 = new StringBuffer("delete from sys_attribute where attr_id=");
		sql2.append(map.getString("attr_id", "-1"));
		updateBatch(new StringBuffer[] { sql0, sql1, sql2 });
		return null;
	}
}
