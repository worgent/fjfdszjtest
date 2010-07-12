package com.apricot.app.report;

import jxl.*;
import jxl.write.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.util.DataUtil;
import com.apricot.app.report.*;
public class Ranking_order extends BO{
	
	
	public Object Ranking_order(DyncParameterMap map) throws NestedException{
		time time=new time();
		time.simpletime();
		System.out.println("进来了点菜排行~~~~开始查询数据~~~");
		System.out.println("Map的第一个值"+map.getString("order_time")+"Map的第二个值"+map.getString("order_time1"));
		StringBuffer sql1=new StringBuffer();
		//String sql1="SELECT b.food_name,a.isum FROM (SELECT SUM(t1.food_id) AS isum,t2.food_id,t3.order_time  FROM order_list t1 INNER JOIN food_info t2 ON t1.food_id=t2.food_id INNER JOIN order_info t3 ON t1.order_id=t3.order_id WHERE  DATE(t3.ORDER_TIME) between '2009-07-01' and '2009-07-30'GROUP BY t1.food_id)a INNER JOIN food_info b ON a.food_id=b.food_id";
		sql1.append("SELECT b.food_name,a.isum FROM (SELECT SUM(t1.food_id) AS isum,t2.food_id,t3.order_time FROM ");	
		sql1.append("order_list t1 INNER JOIN food_info t2 ON t1.food_id=t2.food_id INNER JOIN order_info t3 ON t1.order_id=t3.order_id WHERE ");
		if(map.getString("order_time")!=null&&map.getString("order_time1")!=null&&map.getString("order_time")!=""&&map.getString("order_time1")!="")
		{	
		sql1.append("DATE(t3.ORDER_TIME) between '"+map.getString("order_time")+"' and '"+map.getString("order_time1")+"'GROUP BY t1.food_id order by isum desc)a INNER JOIN food_info b ON a.food_id=b.food_id");		
		}
		else
		{
	    sql1.append("DATE(t3.ORDER_TIME)='"+time.simpletime()+"'GROUP BY t1.food_id order by isum desc)a INNER JOIN food_info b ON a.food_id=b.food_id");
		}
		setRuntimeStaticData("order_list");
		setRuntimeStaticData("food_info");
		setRuntimeStaticData("order_info");
		System.out.println("结束了"+sql1.toString());
		return showPages(sql1.toString(),0,-1);
	}	
	public Object dishes_back(DyncParameterMap map) throws NestedException{
		time time=new time();
		time.simpletime();
		StringBuffer sql1=new StringBuffer();
		//String sql1="SELECT b.food_name,a.isum,a.order_time FROM (SELECT SUM(t1.food_id) AS isum,t2.food_id,t3.order_time  FROM order_list t1 INNER JOIN food_info t2 ON t1.food_id=t2.food_id INNER JOIN order_info t3 ON t1.order_id=t3.order_id WHERE  DATE(t3.ORDER_TIME) between '2009-07-01' and '2009-07-30'GROUP BY t1.food_id)a INNER JOIN food_info b ON a.food_id=b.food_id";
		sql1.append("SELECT b.food_name,a.isum FROM (SELECT SUM(t1.food_count) AS isum,t2.food_id,t3.order_time  FROM ");
		sql1.append("order_list t1 INNER JOIN food_info t2 ON t1.food_id=t2.food_id and t1.food_action='2'INNER JOIN order_info t3 ON t1.order_id=t3.order_id WHERE");
		if(map.getString("order_time")!=null&&map.getString("order_time1")!=null&&map.getString("order_time")!=""&&map.getString("order_time1")!="")
		{
		sql1.append(" DATE(t3.ORDER_TIME) between '"+map.getString("order_time")+"' and '"+map.getString("order_time1")+"'GROUP BY t1.food_id order by isum desc)a INNER JOIN food_info b ON a.food_id=b.food_id");	
		}
		else
		{
		sql1.append(" DATE(t3.ORDER_TIME)='"+time.simpletime()+"'GROUP BY t1.food_id order by isum desc)a INNER JOIN food_info b ON a.food_id=b.food_id");	
		}
		setRuntimeStaticData("order_list");
		setRuntimeStaticData("food_info");
		setRuntimeStaticData("order_info");
		return showPages(sql1.toString(),0,-1);
	}
	
	
	public Object dishes_statements(DyncParameterMap map) throws NestedException{
		time time=new time();
		time.simpletime();
		StringBuffer sql1=new StringBuffer();
		//String sql1="SELECT b.food_name,a.isum,a.order_time FROM (SELECT SUM(t1.food_id) AS isum,t2.food_id,t3.order_time  FROM order_list t1 INNER JOIN food_info t2 ON t1.food_id=t2.food_id INNER JOIN order_info t3 ON t1.order_id=t3.order_id WHERE  DATE(t3.ORDER_TIME) between '2009-07-01' and '2009-07-30'GROUP BY t1.food_id)a INNER JOIN food_info b ON a.food_id=b.food_id";
//		sql1.append("SELECT b.food_name,a.isum,a.order_time,a.order_no,a.food_return_reseon,a.food_return_type FROM ");
//		sql1.append("(SELECT SUM(t1.food_count) AS isum,t2.food_id,t3.order_time,order_no,t1.food_return_reseon,t1.food_return_type  FROM order_list t1 INNER JOIN food_info t2 ON t1.food_id=t2.food_id AND t1.food_action='2'INNER JOIN order_info t3 ON t1.order_id=t3.order_id ");
//		if(map.getString("order_time")!=null&&map.getString("order_time1")!=null&&map.getString("order_time")!=""&&map.getString("order_time1")!="")
//		{
//		sql1.append("WHERE DATE(t3.ORDER_TIME) between '"+map.getString("order_time")+"' and '"+map.getString("order_time1")+"'GROUP BY t1.food_id order by isum desc)a INNER JOIN food_info b ON a.food_id=b.food_id");	
//		}
//		else
//		{
//		sql1.append("WHERE DATE(t3.ORDER_TIME) ='"+time.simpletime()+"'GROUP BY t1.food_id order by isum desc)a INNER JOIN food_info b ON a.food_id=b.food_id");
//		}		
		sql1.append("select * from order_info as oi left join order_list ol on (oi.order_id=ol.order_id) left join food_info fi on (ol.food_id=fi.food_id) where ol.food_action='2'");
		if(map.getString("order_time")!=null && !("".equals(map.getString("order_time"))))
		{
			sql1.append(" and DATE(oi.order_time) >=" + map.getSqlString("order_time"));
		}
		if(map.getString("order_time1")!=null && !("".equals(map.getString("order_time1"))))
		{
			sql1.append(" and DATE(oi.order_time) <=" + map.getSqlString("order_time1"));
		}
		setRuntimeStaticData("order_list");
//		setRuntimeStaticData("food_info");
//		setRuntimeStaticData("order_info");
		return showPages(sql1.toString(),0,-1);
	}
	
	
	public Object currday_turnover(DyncParameterMap map) throws NestedException{
		time time=new time();
		time.simpletime();
		StringBuffer sql1=new StringBuffer();
		//String sql1="SELECT b.food_name,a.isum,a.order_time FROM (SELECT SUM(t1.food_id) AS isum,t2.food_id,t3.order_time  FROM order_list t1 INNER JOIN food_info t2 ON t1.food_id=t2.food_id INNER JOIN order_info t3 ON t1.order_id=t3.order_id WHERE  DATE(t3.ORDER_TIME) between '2009-07-01' and '2009-07-30'GROUP BY t1.food_id)a INNER JOIN food_info b ON a.food_id=b.food_id";
		if((map.getString("order_time")==null&&map.getString("order_time1")==null)||(map.getString("order_time")==""&&map.getString("order_time1")==""))
		{
		sql1.append("select DATE(pay_time) as pay_time  ,sum(pay_total) as pay_total from order_payoff_info where DATE(pay_time) ='"+time.simpletime()+"' group by DATE(pay_time) ");			
		}	
		else
		{
		sql1.append("select DATE(pay_time) as pay_time,sum(pay_total) as pay_total from order_payoff_info where DATE(pay_time) between '"+map.getString("order_time")+"'  and '"+map.getString("order_time1")+"' group by DATE(pay_time)");
		}		
//		System.out.println("12"+sql1);
		setRuntimeStaticData("order_payoff_info");
		return showPages(sql1.toString(),0,-1);
	}
	//导出
	public Object currdayExport(DyncParameterMap map) throws NestedException{
		HashMap m = new HashMap();
		try
		{
			time time=new time();
			time.simpletime();
			 //open file.   
			WritableWorkbook book = Workbook.createWorkbook(new File("E:/营业额报表"+time.simpletime()+".xls"));   

			//create Sheet named "Sheet_1". 0 means this is 1st page.   
			WritableSheet sheet = book.createSheet("Sheet_1", 0);   
			
			//define cell column and row in Label Constructor, and cell content write "test".   
			//cell is 1st-Column,1st-Row. value is "test".   
			Label label = new Label(0, 0, "时间");
			Label label2 = new Label(1, 0, "金额");  
			//add defined cell above to sheet instance.   
			sheet.addCell(label);
			sheet.addCell(label2);
			
			
			StringBuffer sql1=new StringBuffer();
			if((map.getString("order_time")==null&&map.getString("order_time1")==null)||(map.getString("order_time")==""&&map.getString("order_time1")==""))
			{
				sql1.append("select DATE(pay_time) as pay_time  ,sum(pay_total) as pay_total from order_payoff_info where DATE(pay_time) ='"+time.simpletime()+"' group by DATE(pay_time) ");			
			}	
			else
			{
				sql1.append("select DATE(pay_time) as pay_time,sum(pay_total) as pay_total from order_payoff_info where DATE(pay_time) between '"+map.getString("order_time")+"'  and '"+map.getString("order_time1")+"' group by DATE(pay_time)");
			}
			List list = (List)this.getAll(sql1,null);
			double total = 0.00;
			int count = 1;
			for(int i = 0;i < list.size();i ++)
			{
				Object obj = (Object)list.get(i);
				Label labelTmp = new Label(0, (i+1), DataUtil.getString(obj, "pay_time"));
				jxl.write.Number number = new jxl.write.Number(1, (i+1), Double.parseDouble(DataUtil.getString(obj, "pay_total")));   
				//add defined cell above to sheet instance.   
				sheet.addCell(labelTmp);
				sheet.addCell(number);
				total = total + Double.parseDouble(DataUtil.getString(obj, "pay_total"));
				count ++;
			}
			label = new Label(0, count, "合计");
			sheet.addCell(label);
			jxl.write.Number number = new jxl.write.Number(1, count, total);
			sheet.addCell(number);
			//add defined all cell above to case. 
			book.write();   
			//close file case.   
			book.close();
			m.put("flag", "导出成功");
			return m;
		}catch(Exception ex)
		{
			m.put("flag", "导出失败");
			return m;
		}
	}
	public Object currday_turnovers(DyncParameterMap map) throws NestedException{
		time time=new time();
		time.simpletime();
		StringBuffer sql=new StringBuffer();
		if((map.getString("order_time")==null&&map.getString("order_time1")==null)||(map.getString("order_time")==""&&map.getString("order_time1")==""))
		{
		sql.append("select sum(pay_total)as sum from order_payoff_info where DATE(pay_time) ='"+time.simpletime()+"'");			
		}	
		else
		{
		sql.append("select sum(PAY_TOTAL) as sum from order_payoff_info where DATE(pay_time) between '"+map.getString("order_time")+"'  and '"+map.getString("order_time1")+"'");
		}		
		System.out.println(sql);
		setRuntimeStaticData("order_payoff_info");
		return showPages(sql.toString(),0,-1);
	}
	
	
	public Object cancel_ststements(DyncParameterMap map) throws NestedException{
		time time=new time();
		time.simpletime();
		System.out.println("进来预订取消报表cancel_ststements了~~~~开始查询数据~~~");
		StringBuffer sql1=new StringBuffer();
		//String sql1="SELECT b.food_name,a.isum,a.order_time FROM (SELECT SUM(t1.food_id) AS isum,t2.food_id,t3.order_time  FROM order_list t1 INNER JOIN food_info t2 ON t1.food_id=t2.food_id INNER JOIN order_info t3 ON t1.order_id=t3.order_id WHERE  DATE(t3.ORDER_TIME) between '2009-07-01' and '2009-07-30'GROUP BY t1.food_id)a INNER JOIN food_info b ON a.food_id=b.food_id";
		if(map.getString("order_time")!=null&&map.getString("order_time1")!=null&&map.getString("order_time")!=""&&map.getString("order_time1")!="")
		{
		sql1.append("select ORDER_NO,PREARRANGE_ORDER_TIME,PREARRANGE_NAME,PREARRANGE_PHONE,RECORD_STAFF_ID,order_reseon from order_info WHERE  DATE(can_order_time) between '"+map.getString("order_time")+"' and '"+map.getString("order_time1")+"'");	
		}
		else
		{
		sql1.append("select ORDER_NO,PREARRANGE_ORDER_TIME,PREARRANGE_NAME,PREARRANGE_PHONE,RECORD_STAFF_ID,order_reseon from order_info WHERE  DATE(can_order_time)='"+time.simpletime()+"'");	
		}
		
		setRuntimeStaticData("order_info");
		System.out.println("结束了"+sql1.toString());
		return showPages(sql1.toString(),0,-1);
	}
	
	public Object dayReport(DyncParameterMap map) throws NestedException{
		System.out.println("123");
		return null;
	}
	
}
