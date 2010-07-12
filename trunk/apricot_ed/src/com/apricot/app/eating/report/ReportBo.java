/**
 * 
 */
package com.apricot.app.eating.report;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.util.DataUtil;

/**
 * @author Administrator
 *
 */
public class ReportBo extends BO{
	/**
	 * 
	 */
	public ReportBo() {
		// TODO Auto-generated constructor stub
	}
	
	public Object currDayOrder(DyncParameterMap map) throws NestedException{
		StringBuffer sql=new StringBuffer();
		sql.append("select * from ");
		sql.append("order_info where date(order_time)=date(");
		sql.append("now()");
		sql.append(")");
		setRuntimeStaticData("order_info");
		
		return showPages(sql.toString(),0,-1);
	}
	public Object accountreprot(DyncParameterMap map) throws NestedException{
		StringBuffer sql=new StringBuffer();
		sql.append("select *,TO_DAYS(NOW()) - TO_DAYS(op.pay_time) as days from ");
		sql.append("order_info as oi , order_payoff_info as op, order_book_info as ob  where oi.order_status='4' and oi.order_no=op.order_no and oi.order_no=ob.order_no");
		if(!DataUtil.isNull(map.getString("order_type"))){
			sql.append(" and oi.order_type='");
			sql.append(map.getString("order_type"));
			sql.append("'");
		}if(!DataUtil.isNull(map.getString("book_date"))&&!DataUtil.isNull(map.getString("book_date1"))){
			sql.append(" and ob.book_date>='");
			sql.append(map.getString("book_date"));
			sql.append("'");
			sql.append(" and ob.book_date<='");
			sql.append(map.getString("book_date1"));
			sql.append("'");
		}if(!DataUtil.isNull(map.getString("order_time"))&&!DataUtil.isNull(map.getString("order_time1"))){
			sql.append(" and oi.order_time>='");
			sql.append(map.getString("order_time"));
			sql.append("'");
			sql.append(" and oi.order_time<='");
			sql.append(map.getString("order_time1"));
			sql.append("'");
		}if(!DataUtil.isNull(map.getString("days"))){
			sql.append(" and TO_DAYS(NOW()) - TO_DAYS(op.pay_time)='");
			sql.append(map.getString("days"));
			sql.append("'");
		}
		setRuntimeStaticData("order_info");
		setRuntimeStaticData("order_payoff_info");
		setRuntimeStaticData("order_book_info");
		return showPages(sql.toString(),0,-1);
	}
	public Object getOrder(DyncParameterMap map) throws NestedException{
		StringBuffer sql=new StringBuffer();
		sql.append("select * from order_info where ");
		sql.append(" order_time>=");
		sql.append(DataUtil.toSqlDateTime(map.getString("starttime")));
		sql.append(" and order_time<=");
		sql.append(DataUtil.toSqlDateTime(DataUtil.getCurrDateTime("yyyy-MM-dd 23:59:59")));
		return showPages(sql.toString(), 0, -1);
	}
	
	
	public Object realTimeStorage(DyncParameterMap map) throws NestedException{
		StringBuffer sql=new StringBuffer();
		sql.append("select order_status,count(*) as ct,now() as starttime from ");
		sql.append("order_info where order_time>=");
		sql.append(DataUtil.toSqlDateTime(DataUtil.getCurrDateTime()));
		sql.append(" and order_time<=");
		sql.append(DataUtil.toSqlDateTime(DataUtil.getCurrDateTime("yyyy-MM-dd 23:59:59")));
		sql.append(" group by order_status,starttime");
		
		
		return showPages(sql.toString(), 0, -1);
	}
	
	public Object lossReport(DyncParameterMap map) throws NestedException{
		StringBuffer sql=new StringBuffer();
		sql.append("select * from order_info as oi,order_list as ol left join food_info as fi on (ol.food_id=fi.food_id) left join staff_info as si on (ol.modify_staff_id=staff_id) where oi.order_id=ol.order_id and ol.food_action='0'");
		if(!DataUtil.isNull(map.getString("order_time"))&&!DataUtil.isNull(map.getString("order_time1"))){
			sql.append(" and oi.order_time>='");
			sql.append(map.getString("order_time"));
			sql.append("'");
			sql.append(" and oi.order_time<='");
			sql.append(map.getString("order_time1"));
			sql.append("'");
		}
		setRuntimeStaticData("order_info");
		System.out.println(sql);
		return showPages(sql.toString(), 0, -1);
	}
}
