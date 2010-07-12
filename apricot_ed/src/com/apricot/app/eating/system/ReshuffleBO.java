package com.apricot.app.eating.system;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.apricot.app.report.time;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.util.DataUtil;
import com.rsa.certj.pkcs7.Data;

public class ReshuffleBO extends BO{

	public Object totalMoney(DyncParameterMap map) throws NestedException{
		StringBuffer sql=new StringBuffer();
		sql.append("select staff_cash,r_cash,priv_cash from staff_business_info where  r_date=CURDATE()");
		sql.append(" and staff_id='");
		sql.append(map.getString("staff_id"));
		sql.append("'");
		Object o=get(sql);
		HashMap m=new HashMap();
		Double m1=Double.parseDouble(DataUtil.getString(o, "staff_cash"));
		Double m2=Double.parseDouble(DataUtil.getString(o, "r_cash"));
		Double m3=Double.parseDouble(DataUtil.getString(o, "priv_cash"));
		Double money=m1+m2+m3;
		m.put("totalmoney", money);
		return m;
	}
	public Object over(DyncParameterMap map) throws NestedException{
		StringBuffer sql=new StringBuffer();
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(dt);
		sql.append("update staff_business_info set take_cash='");
		sql.append(map.getString("take_cash"));
		sql.append("', diff_memo='");
		sql.append(map.getString("diff_memo"));
		sql.append("', r_statu='0',d_time='");
		sql.append(time);
		sql.append("'");
		sql.append(" where staff_id='");
		sql.append(map.getString("staff_id"));
		sql.append("' and r_date=CURDATE() and r_statu='1'");
		update(sql);
		return null;
	}
	public Object beforeover(DyncParameterMap map) throws NestedException{
		
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*), r_statu from staff_business_info where  r_date=CURDATE() and staff_id='");
		sql.append(map.getString("staff_id"));
		sql.append("'");
		Object o=get(sql);
		HashMap m=new HashMap();
		if(getInt(sql)>0){
			if("1".equals(DataUtil.getString(o, "r_statu"))){
				m.put("flag", "YES");
			}else{
			m.put("flag", "NO");
			}
		}else{
			m.put("flag", "NO");
		}
		return m;
	}
	
	public Object detail(DyncParameterMap map) throws NestedException{
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(dt);
		//String t=sdf.format(map.getString("cur_date"));
		//System.out.println(t);
		StringBuffer sql=new StringBuffer();
		sql.append("select *,(fact_pay_total-return_total) as fact_pay_total,fact_pay_total-return_total as factmon from order_info as o ,order_payoff_info as op where o.order_status in('1','4') and op.staff_id='");
		sql.append(map.getString("staff_id"));
		sql.append("'");
		sql.append("  and op.order_no=o.order_no ");
		if(!DataUtil.isNull(map.getString("cur_date"))){
			sql.append(" and o.operate_order_time like '");
			sql.append(map.getString("cur_date"));
			sql.append("%' and op.pay_time like '");
			sql.append(map.getString("cur_date"));
			sql.append("%'");
		}else{
		sql.append(" and o.operate_order_time like '");
		sql.append(time);
		sql.append("%' and op.pay_time like '");
		sql.append(time);
		sql.append("%'");
		}
		System.out.println(sql);
		setRuntimeStaticData("order_info");
		return showPages(sql.toString(),0,-1);
	}
	public Object detailPage(DyncParameterMap map) throws NestedException{
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(dt);
		//String t=sdf.format(map.getString("cur_date"));
		//System.out.println(t);
		StringBuffer sql=new StringBuffer();
		sql.append("select o.*,op.pay_total,op.total,(op.fact_pay_total-op.return_total) as fact_pay_total,op.fact_pay_card,op.youhui_total,fact_pay_total-return_total as factmon,s.staff_name from order_info as o ,order_payoff_info as op,staff_info as s where op.staff_id=s.staff_id and o.order_status in('1','4') ");
		
		sql.append("  and op.order_no=o.order_no ");
		if(!DataUtil.isNull(map.getString("cur_date"))){
			sql.append(" and o.operate_order_time like '");
			sql.append(map.getString("cur_date"));
			sql.append("%' and op.pay_time like '");
			sql.append(map.getString("cur_date"));
			sql.append("%'");
		}else{
			sql.append(" and o.operate_order_time like '");
			sql.append(time);
			sql.append("%' and op.pay_time like '");
			sql.append(time);
			sql.append("%'");
		}
		
		if(!DataUtil.isNull(map.getString("cur_name"))){
			sql.append(" and op.staff_id='");
			sql.append(map.getString("cur_name"));
			sql.append("'");
		}
		
		setRuntimeStaticData("order_info");
		System.out.println(sql);
		return showPages(sql.toString(),0,-1);
	}
	public Object page(DyncParameterMap map) throws NestedException{
		StringBuffer sql=new StringBuffer();
		sql.append("select si.*,sum(oi.fact_pay_total-oi.return_total) as fact_pay_total ,sum(oi.youhui_total) as youhui_total,sum(oi.fact_pay_card) as fact_pay_card from staff_business_info as si,order_payoff_info as oi where si.u_time<oi.pay_time and r_statu=1 and r_date=CURDATE() and si.staff_id=oi.staff_id and si.staff_id='");
		sql.append(map.getString("staff_id"));
		sql.append("'  group by CURDATE()");
		Object obj = get(sql);
		if(obj == null)
		{
			sql=new StringBuffer();
			sql.append("select * from staff_business_info where r_statu=1 and r_date=CURDATE() and staff_id='");
			sql.append(map.getString("staff_id"));
			sql.append("'");
		}
		setRuntimeStaticData("staff_business_info");
		
		return showPages(sql.toString(),0,-1);
	}
	
	public Object reshuffleReport(DyncParameterMap map) throws NestedException{
		time time=new time();
		time.simpletime();
		StringBuffer sql=new StringBuffer();
		sql.append("select sbi.*,si.staff_name as staff_name,sii.staff_name as priv_staff_name from staff_business_info as sbi left join staff_info as si on (sbi.staff_id=si.staff_id) "
				+ "left join staff_info as sii on(sbi.PRIV_STAFF_ID=sii.staff_id)");
		if(map.getString("reshuffle_time")!=null&&map.getString("reshuffle_time1")!=null&&map.getString("reshuffle_time")!=""&&map.getString("reshuffle_time1")!="")
		{
			sql.append(" where DATE(sbi.d_time) between '"+map.getString("reshuffle_time")+"' and '"+map.getString("reshuffle_time1")+"'");	
		}
		setRuntimeStaticData("staff_business_info");
		return showPages(sql.toString(), map.getStartRows(), map.getPageSize());
	}
}
