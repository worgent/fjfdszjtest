/**
 * 
 */
package com.apricot.app.eating.system;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
/**
 * @author Administrator
 */
public class SystemBO extends BO {
	/**
	 * 
	 */
	public SystemBO() {
		// TODO Auto-generated constructor stub
	}
	
	public Object getAllPrinters(DyncParameterMap map) throws NestedException{
	    StringBuffer sql=new StringBuffer();
	    sql.append("select t2.* from ");

	    sql.append("print_config t2 ");
	    System.out.println(sql.toString());
	    setRuntimeStaticData("print_config");
	    return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object rightResourceNotIn(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from resource_info t1 ");
		sql.append("where t1.res_id not in (select res_id from privilege_respirce_map where priv_id=");
		sql.append(map.getString("priv_id", "-1")).append(")");
		setRuntimeStaticData("resource_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object rightResourceAll(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.res_priv_id,t2.oper_type,t2.is_promission ");
		sql.append("from resource_info t1,privilege_respirce_map t2 ");
		sql.append("where t1.res_id=t2.res_id and  t2.priv_id=");
		sql.append(map.getString("priv_id", "-1"));
		setRuntimeStaticData("resource_info");

		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}
	
	public Object staffRoleNotIn(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from privilege_info t1 ");
		sql.append("where priv_type='01' and t1.priv_id not in (select priv_id from staff_role_map where staff_id=");
		sql.append(map.getString("staffid", "-1")).append(")");
		setRuntimeStaticData("privilege_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object staffRoleAll(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.staff_priv_id from privilege_info t1,staff_role_map t2 ");
		sql.append("where t1.priv_id=t2.priv_id and t1.priv_type='01' and t2.staff_id=");
		sql.append(map.getString("staff_id", "-1"));
		setRuntimeStaticData("privilege_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object resourceAll(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.res_lable as parent_res_name from ");
		sql.append("resource_info t1 left join resource_info t2 on t1.parent_res_id=t2.res_id where 1=1 ");
		sql.append(getWhereByNotNull(map, "resource_info", "t1"));
System.out.println(sql.toString());
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object getRolePrivilegeMap(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.priv_id,t1.child_priv_id,t2.PRIV_NAME,");
		sql.append("t2.priv_type,t2.priv_status,t2.priv_desc ");
		sql.append("from privilege_rela t1,privilege_info t2 where ");
		sql.append("t1.child_priv_id=t2.priv_id and t1.priv_id=");
		sql.append(map.getString("role_id", "-1"));
		sql.append(getWhereByNotNull(map, "privilege_info", "t2"));// Dyncmic
		// sql
		setRuntimeStaticData("PRIVILEGE_INFO");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object getSelectPrivilege(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from privilege_info where ");
		sql.append("priv_id not in (select child_priv_id from privilege_rela where priv_id=");
		sql.append(map.getString("role_id", "-1"));
		sql.append(")");
		//System.out.println(map.getString("role_id", "-1"));
		sql.append(getWhereByNotNull(map, "privilege_info", ""));// Dyncmic
		// sql
		setRuntimeStaticData("privilege_info");
		 System.out.println(sql.toString());
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object delRolePrivilegeMap(DyncParameterMap map) throws NestedException {
		Table tab = getTable("privilege_rela");
		tab.setSqlType(Table.DELETE);
		tab.setParameterMap(map);
		tab.setWhereColumn(new String[] { "priv_id", "child_priv_id" });
	    System.out.println(tab.getSQL());
		execute(tab);
		return null;
	}
}
