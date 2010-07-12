/**
 * 
 */
package com.apricot.app.eating.business;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.apricot.app.report.time;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DataSet;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.Table;
import com.apricot.eating.util.DataUtil;

/**
 * @author Administrator
 */
public class VipBO extends BO {
	/**
	 * 
	 */
	public VipBO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 批量增加会员卡
	 * 
	 * @param map
	 * @return
	 * @throws NestedException
	 */
	public Object multiAddVipCard(DyncParameterMap map) throws NestedException {
		long startNo = map.getLong("start_no");
		long endNo = map.getLong("end_no");
		StringBuffer exist = new StringBuffer();
		exist.append("select count(*) from vip_card ");
		exist.append("where vip_card_no={0} and sf_id={1}");
		for (; startNo <= endNo; startNo++) {

			String[] args = new String[] {
					"'" + DataUtil.lpad(String.valueOf(startNo), '0', 3) + "'",
					map.getString("sf_id", "-1"), };

			if (getInt(new StringBuffer(MessageFormat.format(exist.toString(),
					args))) > 0) {
				return getMessage("on_vip_card_exist", args);
			}

			Table tab = getTable("vip_card");
			tab.setParameterMap(map);
			tab.setSqlType(Table.INSERT);
			map.set("vip_id", getMax("vip_card", "vip_id"));
			map.set("vip_card_no", DataUtil.lpad(String.valueOf(startNo), '0',
					3));

			execute(tab);
			String tabName = map.getString(Table.TABLE_KEY);

		}

		return null;
	}

	public Object addVipCard(DyncParameterMap map) throws NestedException {

		StringBuffer exist = new StringBuffer();
		exist.append("select count(*) from vip_card ");
		exist.append("where vip_card_no={0} and sf_id={1}");

			String[] args = new String[] {
					"'" + DataUtil.lpad(String.valueOf(map.getString("vip_card_no")), '0', 3) + "'",
					map.getString("sf_id", "-1"), };

			if (getInt(new StringBuffer(MessageFormat.format(exist.toString(),
					args))) > 0) {
				return getMessage("on_vip_card_exist", args);
			}

			Table tab = getTable("vip_card");
			tab.setParameterMap(map);
			tab.setSqlType(Table.INSERT);
			map.set("vip_id", getMax("vip_card", "vip_id"));
			map.set("vip_card_no", DataUtil.lpad(String.valueOf(map.getString("vip_card_no")), '0',
					3));

			execute(tab);
			String tabName = map.getString(Table.TABLE_KEY);


		return null;
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
		StringBuffer sql = new StringBuffer();
		sql.append("update vip_card set vip_status=1 where VIP_CARD_NO='"+map.getString("vip_no")+"'");
		update(sql);
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
	
	public Object selectMember(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select cust_name,cust_phone,vip_no as vip_card_no,cum_point from customer_info");
		if (!map.isNull("vip_card_no")) {
			sql.append(" where vip_no='"+map.getString("vip_card_no")+"'");
		}
		System.out.println(sql.toString());

		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
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
					map.getSqlString("vip_no", "-1"));
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
	
	//手机号查找会员vip卡号
	public Object getVipNO(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from customer_info where CUST_PHONE='"+map.getString("custPhone")+"'");
		return get(sql);
	}
//	会员vip卡号查找信息
	public Object getVipNOByCard(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from customer_info where VIP_NO='"+map.getString("custVipNo")+"'");
		System.out.println(sql);
		return get(sql);
	}
	
	public Object setRate(DyncParameterMap map) throws NestedException {

		Table tab = getTable("vip_rate");
		tab.setParameterMap(map);
		tab.setSqlType(Table.UPDATE);
		int i = execute(tab);
		if(i==0){
			tab.setSqlType(Table.INSERT);
			execute(tab);
		}
		return null;
	}
	public Object getRate(DyncParameterMap map) throws NestedException {

		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t3.attr_desc as vip_discount_text from vip_rate t1,sys_attribute t2,sys_attribute_value t3 ");
		sql.append(" where t2.attr_code=").append(map.getSqlString("discode"));
		sql.append(" and t2.attr_id = t3.attr_id ");
		sql.append(" and t3.attr_value=t1.vip_discount");
		System.out.println(sql);
		DataSet ds = showPages(sql.toString(), map.getStartRows(), map.getPageSize());
		
		return ds;
	}
	
	public Object getDiscount(DyncParameterMap map) throws NestedException {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from vip_rate where vip_level=").append(map.getSqlString("vip_level"));
		List list = new ArrayList();
		System.out.println(sql);
		for (Iterator objs = getAll(sql).iterator(); objs.hasNext();) {
			Object o = objs.next();
			list.add(DataUtil.getString(o, "vip_discount"));
			
		}
		System.out.println(list);
		return list;
	}
	
	//会员导出Excel
	public Object exportExcel(DyncParameterMap map) throws NestedException {
		HashMap m = new HashMap();
		try
		{
			time time=new time();
			String[] column = {"姓名","性别","身份证","联系电话","生日","会员卡号","卡级别","折扣率","积分"};
			String[] column2 = {"cust_name","cust_sex_text","cust_id_card","cust_phone","cust_birthday","vip_no","vip_level_text","vip_discount","cum_point"};
			WritableWorkbook book = Workbook.createWorkbook(new File("E:/会员列表"+time.simpletime()+".xls"));   
			WritableSheet sheet = book.createSheet("Sheet_1", 0);
			for(int i = 0;i < column.length;i ++)
			{
				Label label = new Label(i, 0, column[i]);
				sheet.addCell(label);
			}
			StringBuffer sql=new StringBuffer();
			sql.append("select * from customer_info");
			setRuntimeStaticData("customer_info");
			List list = (List)this.getAll(sql,null);
			for(int i = 0;i < list.size();i ++)
			{
				Object obj = (Object)list.get(i);
				for(int j = 0;j < column2.length;j ++)
				{
					Label label = new Label(j, (i+1), DataUtil.getString(obj, column2[j]));
					sheet.addCell(label);
				}
			}
			book.write();
			book.close();
			m.put("flag", "导出成功");
			return m;
		}catch(Exception ex)
		{
			m.put("flag", "导出失败");
			return m;
		}
	}
	
	public Object memberOrder(DyncParameterMap map) throws NestedException {
		StringBuffer sql = new StringBuffer();
		sql.append("select oi.*,opi.total,opi.pay_total,opi.youhui_total,opi.fact_pay_card, (fact_pay_total-return_total) as fact_pay_total,fact_pay_total-return_total as factmon ");
		sql.append("from order_info as oi left join order_payoff_info as opi on (oi.order_no=opi.order_no) ");
		sql.append("where oi.vip_card_no=");
		sql.append(map.getSqlString("vip_no", "-1"));
		setRuntimeStaticData("order_info");

		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}
}
