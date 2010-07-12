package com.apricot.app.eating.print;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.util.DataUtil;
import com.apricot.eating.util.Printer;

public class PrintBo extends BO{

	public Object printOrder(DyncParameterMap map) throws NestedException {
		HashMap m = new HashMap();
		m.put("flag", 0);
		int count = map.getInt("print_count");
		
		StringBuffer sql=new StringBuffer();
		sql.append("select t1.*,t2.food_name,t3.* from order_list t1,food_info t2,order_info t3 where t1.food_id=t2.food_id and  t3.order_id=t1.order_id");
//		sql.append(" and t2.food_type in ('00','01','02','03','09','10','06')");
		if(!DataUtil.isNull(map.getString("order_no"))){
			sql.append(" and t3.order_no='");
			sql.append(map.getString("order_no"));
			sql.append("'");
		}
		sql.append(" and t1.is_print='");
		sql.append("02");
		sql.append("'");
		sql.append("  order by  FIELD(t3.order_status, 0,2,1,3,4),FIELD(t1.food_action, 2,9,1)");
//		System.out.println(count+":"+sql);
		setRuntimeStaticData("order_list");
		setRuntimeStaticData("food_info");
		setRuntimeStaticData("order_info");
		List list = getAll(sql);
		DefaultTableModel table = new DefaultTableModel(list.size()+7,3);
		String title = "";
		int countNum = 3;
//		double total = 0.00;
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " " + "hh:mm:ss"); 
		String datetime = tempDate.format(new java.util.Date());
		int countTotal = 0;
		String vip_card_no = "";
		table.setValueAt(datetime, 0, 0);
		table.setValueAt("名称",1,0);
		table.setValueAt("数量",1,1);
		table.setValueAt("单价",1,2);
		for(int i = 0;i < list.size();i ++)
		{
			Object o = (Object)list.get(i);
			title = "【"+(String)DataUtil.getObject(o, "vip_card_no")+"】  "+(String)DataUtil.getObject(o, "balcony_name");
			vip_card_no = (String)DataUtil.getObject(o, "vip_card_no");
			table.setValueAt(DataUtil.getObject(o, "food_name"), i+2, 0);
			table.setValueAt(DataUtil.getObject(o, "food_count"), i+2, 1);
			table.setValueAt(DataUtil.getObject(o, "food_price"), i+2, 2);
//			total = total + (Double.parseDouble((String)DataUtil.getObject(o, "food_count"))*Double.parseDouble((String)DataUtil.getObject(o, "food_price")));
			countTotal = countTotal + DataUtil.getInt(o, "food_count",0);
			countNum ++;
		}
		table.setValueAt("----------------------------------", countNum, 0);
		countNum ++;
		table.setValueAt("合计", countNum, 0);
		table.setValueAt(countTotal, countNum, 1);
		table.setValueAt(map.getString("total"), countNum, 2);
		countNum ++;
		table.setValueAt("实际金额", countNum, 0);
		table.setValueAt("", countNum, 1);
		table.setValueAt(map.getString("pay_total"), countNum, 2);
		countNum ++;
		table.setValueAt("返还积分", countNum, 0);
		table.setValueAt("", countNum, 1);
		table.setValueAt(map.getString("dis_point"), countNum, 2);
		sql = new StringBuffer();
		sql.append("select * from customer_info where vip_no = '"+vip_card_no+"'");
		Object obj = get(sql);
		title = map.getString("sf_name")+" "+DataUtil.getObject(obj, "cust_name")+title;
		for(int j = 0;j < count;j ++)
		{
			Printer print = new Printer(table, title,"Grandi Pos58 III Printer");
//			Printer print = new Printer(table, title,"XP-80III+");
			m.put("flag", print.print());
		}
		return m;
	}
	public String foodPage(DyncParameterMap map) throws NestedException {
		Date dt=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		String time=sdf.format(dt);
		StringBuffer sql=new StringBuffer();
		sql.append("select t1.*,t2.food_name,t3.* from order_list t1,food_info t2,order_info t3 where t1.food_id=t2.food_id and  t3.order_id=t1.order_id");
		sql.append(" and t2.food_type in ('00','01','02','03','09','10','06')");
		if(!DataUtil.isNull(map.getString("order_no"))){
			sql.append(" and t3.order_no='");
			sql.append(map.getString("order_no"));
			sql.append("'");
		}
		sql.append(" and t1.is_print='");
		sql.append("02");
		sql.append("'");
		sql.append("  order by  FIELD(t3.order_status, 0,2,1,3,4),FIELD(t1.food_action, 2,9,1)");
		System.out.println(sql);
		setRuntimeStaticData("order_list");
		setRuntimeStaticData("food_info");
		setRuntimeStaticData("order_info");
		List list = getAll(sql);
		DefaultTableModel table = new DefaultTableModel(list.size(),2);
		String title = "";
		for(int i = 0;i < list.size();i ++)
		{
			Object o = (Object)list.get(i);
			title = (String)DataUtil.getObject(o, "balcony_name");
			table.setValueAt(DataUtil.getObject(o, "food_name"), i, 0);
			table.setValueAt(DataUtil.getObject(o, "food_count"), i, 1);
		}
		Printer print = new Printer(table, title,"Grandi Pos58 III Printer");
		print.print();
		return "打印成功";
	}
	public Object drinkPage(DyncParameterMap map) throws NestedException {
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(dt);
		StringBuffer sql=new StringBuffer();
		sql.append("select t1.*,t2.food_name,t3.* from order_list t1,food_info t2,order_info t3 where t1.food_id=t2.food_id and  t3.order_id=t1.order_id");
		sql.append(" and t2.food_type in ('04','99','05','07','08')");
//		sql.append(" and t3.order_time like '");
//		sql.append(time);
//		sql.append("%'");
		if(!DataUtil.isNull(map.getString("order_no"))){
			sql.append(" and t3.order_no='");
			sql.append(map.getString("order_no"));
			sql.append("'");
		}
//		if(!DataUtil.isNull(map.getString("balcony_name"))){
//			sql.append(" and t3.balcony_name='");
//			sql.append(map.getString("balcony_name"));
//			sql.append("'");
//		}
//		if(!DataUtil.isNull(map.getString("order_type"))){
//			sql.append(" and t3.order_type='");
//			sql.append(map.getString("order_type"));
//			sql.append("'");
//		}
//		if(!DataUtil.isNull(map.getString("order_status"))){
//			sql.append(" and t3.order_status='");
//			sql.append(map.getString("order_status"));
//			sql.append("'");
//		}
//		if(!DataUtil.isNull(map.getString("hurry_times"))){
//			if("01".equals(map.getString("hurry_times"))){
//			sql.append(" and t3.hurry_times !='0'");
//			}
//		}if(!DataUtil.isNull(map.getString("is_print"))){
//			sql.append(" and t1.is_print='");
//			sql.append(map.getString("is_print"));
//			sql.append("'");
//		}else{
			sql.append(" and t1.is_print='");
			sql.append("02");
			sql.append("'");
//		}
		sql.append(" order by  FIELD(t3.order_status, 0,2,1,3,4),FIELD(t1.food_action, 2,9,1)");
		setRuntimeStaticData("order_list");
		setRuntimeStaticData("food_info");
		setRuntimeStaticData("order_info");
//		System.out.println(sql.toString());
		return "打印成功";
	}
}
