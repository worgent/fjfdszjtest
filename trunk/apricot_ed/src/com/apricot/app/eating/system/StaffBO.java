/**
 * 
 */
package com.apricot.app.eating.system;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.apricot.app.eating.LoginData;
import com.apricot.app.eating.Resource;
import com.apricot.app.eating.calc.vo.Order;
import com.apricot.app.eating.calc.vo.OrderDetail;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.ResponseData;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;
import com.mchange.util.PasswordUtils;
import com.rsa.certj.pkcs7.Data;

/**
 * @author Administrator
 */
public class StaffBO extends BO {
	/**
	 * 
	 */
	public StaffBO() {
		// TODO Auto-generated constructor stub
	}

	public Object getMenus(DyncParameterMap map) throws NestedException {
		return map.getMenus();
	}

	public HashMap map(DyncParameterMap map) {
		HashMap m = new HashMap();
		map.remove("staff_id");
		m.put("ID", map.getString("staff_id"));
		return m;

	}

	public Object keepaccounts(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		try {
			if (DataUtil.isNull("accounts_password")
					&& DataUtil.isNull("accounts_password1")) {
				return ResponseData.createFailure("not_exist_staff1", null);
			}
			if (!map.getString("accounts_password1").equals(
					map.getString("accounts_password"))) {
				return ResponseData.createFailure("not_exist_staff2", null);
			}

			map.set("accounts_password", PasswordUtils.enCodeValue(map
					.getString("accounts_password")));
		} catch (Exception e) {
		}
		StringBuffer ext = new StringBuffer();
		ext.append("select count(*) from staff_log_info where re_staff_id='");
		ext.append(map.getString("staffid", "-"));
		ext.append("'");
		sql.append("update staff_info set  accounts_password=");
		sql.append(map.getSqlString("accounts_password"));
		sql.append(" where staff_id=");
		sql.append(map.getString("staffid", "-"));

		Table tab = getTable("staff_log_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		map.set("re_staff_id", map.getString("staffid", "-"));
		map.set("p_time", map.getString("p_time"));
		map.set("staff_id", map.getString("staff_id"));
		if (getInt(ext) <= 0) {
			map.set("op_type", "密码初始化");
		} else if (map.getString("staffid", "-").equals(
				map.getString("staff_id"))) {
			map.set("op_type", "密码修改");
		}

		else {
			map.set("op_type", "密码重置");
		}
		execute(tab);
		update(sql);

		return null;
	}

	public Object addkeepaccounts(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("update staff_info set accounts_per='1'");
		sql.append(" where staff_id=");
		sql.append(map.getString("staff_id", "-"));
		update(sql);
		return null;
	}

	public Object staffdelete(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("update staff_info set accounts_per='0'");
		sql.append(" where staff_id=");
		sql.append(map.getString("staff_id", "-"));
		update(sql);
		return null;
	}

	public Object kai_accout(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("update kai_accounts set kai_account='0'");
		update(sql);
		return ResponseData.createSuccess("bookbill_002", null);
	}

	public Object close_accout(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("update kai_accounts set kai_account='1'");
		update(sql);
		return ResponseData.createSuccess("bookbill_002", null);
	}

	public Object passwordReset(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		try {
			if (map.isNull("staff_password")) {
				map.set("staff_password", "111111");
			}
			// StringBuffer sqlStr=new StringBuffer();
			// sqlStr.append("select staff_password from staff_info where
			// staff_id=");
			// sqlStr.append(map.getString("staff_id"));
			// Object o=get(sqlStr);
			// if(o==null)
			// return ResponseData.createFailure("not_exist_staff", null);
			// String password = PasswordUtils.deCodeValue(DataUtil.getString(o,
			// "staff_password"));
			// if (!password.equals(map.getString("staff_password1")))
			// return ResponseData.createFailure("no_match_password1", null);
			map.set("staff_password", PasswordUtils.enCodeValue(map
					.getString("staff_password")));
		} catch (Exception e) {
		}
		sql.append("update staff_info set staff_password=");
		sql.append(map.getSqlString("staff_password"));
		sql.append(" where staff_id=");
		sql.append(map.getString("staff_id", "-"));
		update(sql);
		return null;
	}

	public Object add(DyncParameterMap map) throws NestedException {
		Table table = getTable("staff_info");
		table.setSqlType(Table.INSERT);
		table.setParameterMap(map);
		map.set("staff_id", getMax("staff_info", "staff_id"));
		try {
			map.set("staff_password", PasswordUtils.enCodeValue(map
					.getString("staff_password")));
			map.set("accounts_per", "0");
		} catch (Exception e) {
		}
		execute(table);
		return null;
	}

	public Object getParentList(DyncParameterMap map) throws NestedException {
		if (map.getString("dept_id").equals("1")) {
			return null;
		}
		StringBuffer sql = new StringBuffer();

		sql
				.append("select t1.dept_id,t1.dept_name,t1.parent_id,t2.dept_name as parent_name from dept_info t1,dept_info t2 ");
		sql.append("where t1.is_valid ='1' and t1.sf_id=");
		sql.append(map.getSqlString("sf_id", "-1"));
		sql.append(" and t1.parent_id=t2.dept_id ");
		sql.append(" and t1.dept_id <>").append(map.getSqlString("dept_id"));
		setRuntimeStaticData("dept_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object getParentList2(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();

		sql
				.append("select t1.*,t2.dept_name as parent_name from dept_info t1,dept_info t2 ");
		sql.append("where t1.is_valid ='1' and t1.sf_id=");
		sql.append(map.getSqlString("sf_id", "-1"));
		sql.append(" and (t1.parent_id=t2.dept_id )");
		sql.append(" group by t1.dept_id");
		setRuntimeStaticData("dept_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object deptAdd(DyncParameterMap map) throws NestedException {
		Table tab = getTable("dept_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.INSERT);
		long primaryKey = getMax("dept_info", "dept_id");
		if (DataUtil.isNull(map.getString("parent_id"))) {
			map.set("path_code", primaryKey);
		} else {
			StringBuffer sql = new StringBuffer();
			sql.append("select path_code from dept_info ");
			sql.append("where dept_id=");
			sql.append(map.getSqlString("parent_id"));
			for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
				Object o = objs.next();
				String path_code = DataUtil.getString(o, "path_code");
				map.set("path_code", path_code + "." + primaryKey);
			}
		}
		// if(primaryKey == 1){
		// map.set("path_code", primaryKey);
		// }else{

		// }
		map.set("dept_id", primaryKey);

		execute(tab);
		return null;
	}

	public Object deptModify(DyncParameterMap map) throws NestedException {
		
		Table tab = getTable("dept_info");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		String newPath = "";
		String oldPath =map.getString("path_code");
		StringBuffer sql = null;
		String dept_id = map.getString("dept_id");
		String parent_id = map.getString("parent_id");
//		System.out.println(dept_id+":"+parent_id);
		if(!dept_id.equals(parent_id)){
			sql = new StringBuffer();
			sql.append("select path_code from dept_info ");
			sql.append("where dept_id=");
			sql.append(map.getSqlString("parent_id"));
			for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
				Object o = objs.next();
				String path_code = DataUtil.getString(o, "path_code");
				newPath =path_code + "." + map.getString("dept_id");
				map.set("path_code", newPath);

			}
			sql = new StringBuffer();
			sql.append("update dept_info set path_code=replace(path_code,'").append(oldPath).append("','").append(newPath).append("')");
			sql.append(" where path_code like '");
			sql.append(oldPath);
			sql.append("%'");
			update(sql);
		}

		execute(tab);
		
		return null;
	}

	public Object getPageLIist(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select t1.*,t2.sf_name,t3.dept_name from staff_info t1,storefront_info t2,dept_info t3 ");
		sql.append("where t1.sf_id=t2.sf_id and t3.dept_id=t1.dept_id");
		sql.append(" and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		setRuntimeStaticData("staff_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object getPageLIist1(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select t1.*,t2.sf_name from staff_info t1,storefront_info t2 ");
		sql.append("where t1.sf_id=t2.sf_id and t1.accounts_per='1'");
		sql.append(" and t1.sf_id=");
		sql.append(map.getString("sf_id", "-1"));
		setRuntimeStaticData("staff_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}

	public Object checkaccout(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		HashMap m = new HashMap();
		sql.append("select kai_account ");
		sql.append("from kai_accounts  ");
		// System.out.println(map.getSqlString("loginname"));
		Object o = get(sql);
		try {
			String kai_accounts = DataUtil.getString(o, "kai_account");
			m.put("kai_accounts", kai_accounts);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	public Object checklogin(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.staff_id ,t1.staff_name,");
		sql
				.append("t1.staff_password,t1.accounts_password,t2.sf_name,t2.sf_id ");
		sql.append("from staff_info t1,storefront_info t2 ");
		sql
				.append("where t1.sf_id=t2.sf_id and t1.accounts_per='1' and t1.staff_code=");
		sql.append(map.getSqlString("name"));
		// System.out.println(map.getSqlString("loginname"));
		Object o = get(sql);

		if (o == null)
			return ResponseData.createFailure("not_exist_staff", null);
		try {
			String password = PasswordUtils.deCodeValue(DataUtil.getString(o,
					"accounts_password"));
			if (!password.equals(map.getString("pws")))
				return ResponseData.createFailure("no_match_password", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object login(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.staff_id ,t1.staff_name,");
		sql.append("t1.staff_password,t2.sf_name,t2.sf_id,t2.service_ip,t2.sms_status ");
		sql.append("from staff_info t1,storefront_info t2 ");
		sql.append("where t1.sf_id=t2.sf_id and t1.staff_code=");
		sql.append(map.getSqlString("loginname"));
		// System.out.println(map.getSqlString("loginname"));
		Object o = get(sql);
		LoginData login = null;
		if (o == null)
			return ResponseData.createFailure("not_exist_staff", null);
		try {
			String password = PasswordUtils.deCodeValue(DataUtil.getString(o,
					"staff_password"));
			if (!password.equals(map.getString("password")))
				return ResponseData.createFailure("no_match_password", null);
			login = new LoginData();
			login.setShopId(DataUtil.getString(o, "sf_id"));
			login.setShopName(DataUtil.getString(o, "sf_name"));
			login.setStaffId(DataUtil.getString(o, "staff_id"));
			login.setStaffName(DataUtil.getString(o, "staff_name"));
			login.setServiceIp(DataUtil.getString(o, "service_ip"));
			login.setSmsStatus(DataUtil.getString(o, "sms_status"));

			login.setLogin(true);
		} catch (Exception e) {
		}
		sql = new StringBuffer(
				"select * from staff_resource_view where staff_id=");
		sql.append(login.getStaffId());
		sql
				.append(" and res_type='02' and res_status='1' and is_promission='1'");
		List a = new ArrayList();
		List b = getAll(sql);
		Map m = DataUtil.toMap(b, "res_id");
		for (Iterator objs = b.iterator(); objs.hasNext();) {
			Resource r = new Resource(objs.next());
			// 获取父亲节点
			Object p = m.get(r.getParentId());
			if (p == null) {// 根节点
				a.add(r);
				m.put(r.getId(), r);// 将本身覆盖
				continue;
			}
			if (!(p instanceof Resource)) {
				p = new Resource(p);
				m.put(r.getParentId(), p);
				if (!a.contains(p))
					a.add(p);
			}
			((Resource) p).add(r);
		}
		login.addMenus(a);
		a.clear();
		m.clear();
		b.clear();
		return ResponseData.createSuccess("sucess_login", login);
	}

	public Object modify(DyncParameterMap map) throws NestedException {
		Table table = getTable("staff_info");
		table.setSqlType(Table.UPDATE);
		table.setParameterMap(map);
		// map.set("staff_id", getMax("staff_info", "staff_id"));
		map.remove("staff_password");
		execute(table);
		return null;
	}
	public Object busInfo(DyncParameterMap map) throws NestedException {
		StringBuffer sql=new StringBuffer();
		StringBuffer sql1=new StringBuffer();
		sql1.append("select staff_type,staff_id,STAFF_TYPE from staff_info where staff_code='");
		sql1.append(map.getString("loginname"));
		sql1.append("'");
		Object o1=get(sql1);
		sql.append("select count(*), r_statu from staff_business_info where  r_date=CURDATE() and r_statu='1'");
		sql.append(" and staff_id='");
		sql.append(DataUtil.getString(o1, "staff_id"));
		sql.append("'  group by staff_id");
		Object o=get(sql);
		HashMap m=new HashMap();
		if(getInt(sql)>0){
			if("1".equals(DataUtil.getString(o, "r_statu"))&&"01".equals(DataUtil.getString(o1, "staff_type"))){
				m.put("flag", "YES");
			}else{
			m.put("flag", "NO");
			}
		}else{
			m.put("flag", "NO");
		}
		if(!"01".equals(DataUtil.getString(o1, "staff_type"))){
		    m.put("flag", "NO");
		}
		return m;
	}
	public Object login1(DyncParameterMap map) throws NestedException {
		String orgStaff_id = map.getString("staff_id");
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.staff_id ,t1.staff_name,");
		sql.append("t1.staff_password,t2.sf_name,t2.sf_id,t2.service_ip,t2.sms_status ");
		sql.append("from staff_info t1,storefront_info t2 ");
		sql.append("where t1.sf_id=t2.sf_id and t1.staff_code=");
		sql.append(map.getSqlString("loginname"));
		// System.out.println(map.getSqlString("loginname"));
		Object o = get(sql);
		LoginData login = null;
		if (o == null)
			return ResponseData.createFailure("not_exist_staff", null);
		if(DataUtil.isNull(map.getString("money")))
			return ResponseData.createFailure("not_exist_money", null);
		try {
			String password = PasswordUtils.deCodeValue(DataUtil.getString(o,
					"staff_password"));
			if (!password.equals(map.getString("password")))
				return ResponseData.createFailure("no_match_password", null);
			
			StringBuffer sql1=new StringBuffer();
			Date dt=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=sdf.format(dt);
			sql1.append("update staff_business_info set take_cash=");
			sql1.append(map.getInt("balance",0));
			sql1.append(", r_cash=");
			sql1.append(map.getInt("factCash",0));
			sql1.append(", priv_staff_id='");
			sql1.append(DataUtil.getString(o, "staff_id"));
			sql1.append("', diff_memo='");
			sql1.append(map.getString("diff_memo"));
			sql1.append("', r_statu='0',d_time='");
			sql1.append(time);
			sql1.append("'");
			sql1.append(" where staff_id='");
			sql1.append(orgStaff_id);
			sql1.append("' and r_date=CURDATE() and r_statu='1'");
			System.out.println(sql1);
			update(sql1);
			
			login = new LoginData();
			login.setShopId(DataUtil.getString(o, "sf_id"));
			login.setShopName(DataUtil.getString(o, "sf_name"));
			login.setStaffId(DataUtil.getString(o, "staff_id"));
			login.setStaffName(DataUtil.getString(o, "staff_name"));
			login.setServiceIp(DataUtil.getString(o, "service_ip"));
			login.setSmsStatus(DataUtil.getString(o, "sms_status"));

			login.setLogin(true);
		} catch (Exception e) {
		}
		sql = new StringBuffer(
				"select * from staff_resource_view where staff_id=");
		sql.append(login.getStaffId());
		sql
				.append(" and res_type='02' and res_status='1' and is_promission='1'");
		List a = new ArrayList();
		List b = getAll(sql);
		Map m = DataUtil.toMap(b, "res_id");
		for (Iterator objs = b.iterator(); objs.hasNext();) {
			Resource r = new Resource(objs.next());
			// 获取父亲节点
			Object p = m.get(r.getParentId());
			if (p == null) {// 根节点
				a.add(r);
				m.put(r.getId(), r);// 将本身覆盖
				continue;
			}
			if (!(p instanceof Resource)) {
				p = new Resource(p);
				m.put(r.getParentId(), p);
				if (!a.contains(p))
					a.add(p);
			}
			((Resource) p).add(r);
		}
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(dt);
		Table table = getTable("staff_business_info");
		table.setSqlType(Table.INSERT);
		table.setParameterMap(map);
		map.set("staff_id", DataUtil.getString(o, "staff_id"));
		if(DataUtil.isNull(map.getString("balance"))){
			map.set("priv_cash", "0.00");
		}else{
			map.set("priv_cash", map.getString("balance"));
		}
		map.set("r_date", time);
		map.set("staff_cash", map.getString("money"));
		map.set("r_statu", "1");
		map.set("r_cash", "0.00");
		map.set("book_cash", "0.00");
		map.set("fact_cash", "0.00");
		map.set("u_time", dt);
		map.set("d_time", dt);
		map.set("take_cash", "0.00");
		execute(table);
		login.addMenus(a);
		a.clear();
		m.clear();
		b.clear();
		return ResponseData.createSuccess("sucess_login", login);
	}
	
	public Object login2(DyncParameterMap map) throws NestedException {
		String orgStaff_id = map.getString("staff_id");
		StringBuffer sql = new StringBuffer();
		try {
			StringBuffer sql1=new StringBuffer();
			Date dt=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=sdf.format(dt);
			sql1.append("update staff_business_info set take_cash=");
			sql1.append(map.getInt("balance",0));
			sql1.append(", r_cash=");
			sql1.append(map.getInt("factCash",0));
			sql1.append(", diff_memo='");
			sql1.append(map.getString("diff_memo"));
			sql1.append("', r_statu='0',d_time='");
			sql1.append(time);
			sql1.append("'");
			sql1.append(" where staff_id='");
			sql1.append(orgStaff_id);
			sql1.append("' and r_date=CURDATE() and r_statu='1'");
			System.out.println(sql1);
			update(sql1);
		} catch (Exception e) {
		}
		return null;
	}
}
