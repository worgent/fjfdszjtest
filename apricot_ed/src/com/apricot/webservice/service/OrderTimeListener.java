package com.apricot.webservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import com.apricot.eating.engine.Table;
import com.apricot.eating.engine.TimerTaskBO;
import com.apricot.eating.util.DataUtil;
import com.apricot.webservice.bean.*;
import com.apricot.webservice.impl.ClientImpl;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OrderTimeListener implements ServletContextListener {
	
	private Timer timer = null;

	  public void contextInitialized(ServletContextEvent event) {
	    timer = new Timer(true);
	    //设置任务计划，启动和间隔时间
//	    timer.schedule(new MyTask(), 0,150000);
//	    timer.schedule(new OrderTask(), 0, 50000);
//	    timer.schedule(new SeatSetFree(), 0,100000);
	    
	 //   String urlConfig = event.getServletContext().getInitParameter("urlConfig");
	 //   timer.schedule(new PriceCalc(urlConfig), 0,360000);
	  }

	  public void contextDestroyed(ServletContextEvent event) {
	    timer.cancel();
	  }
}
class MyTask extends TimerTaskBO
{
	public void run() {
		List listSend = new ArrayList();
		String shopId = "0";
		String serviceIp="127.0.0.1";//59.54.54.145
		StringBuffer sql = new StringBuffer();
		sql.append("select * from storefront_info");
		try
		{
			List listTmp = (List)this.getAll(sql,null);
			if(listTmp.size() > 0)
			{
				Object object = (Object)listTmp.get(0);
				serviceIp = DataUtil.getString(object, "service_ip");
			}
			ClientImpl cli = new ClientImpl(serviceIp);
			sql = new StringBuffer();
			sql.append("select * from dining_set_info");
			setRuntimeStaticData("dining_set_info");
			List list = (List)this.getAll(sql,null);
			for(int i = 0;i < list.size();i ++)
			{
				Object obj = (Object)list.get(i);
				Seats seat = new Seats();
				shopId = DataUtil.getString(obj, "sf_id");
				seat.setRid(Integer.parseInt(DataUtil.getString(obj, "sf_id")));
				seat.setSetName(DataUtil.getString(obj, "balcony_name"));
				seat.setMinCastType(getMessage("mincost_00"+DataUtil.getString(obj, "mincost_type"), null));
				seat.setMinCastCash(DataUtil.getString(obj, "mincost_money"));
				seat.setSetLayer(getMessage("floor_00"+DataUtil.getString(obj, "dining_floor"),null));
				seat.setSetNo(Integer.parseInt(DataUtil.getString(obj, "set_no")));
				seat.setState(DataUtil.getString(obj, "set_status"));
				seat.setIsPreOrder(DataUtil.getString(obj, "pre_order_style"));
				seat.setLocation(getMessage("belong_0"+DataUtil.getString(obj, "belong_to"),null));
				seat.setLodge(DataUtil.getString(obj, "set_max"));
				Row row = new Row(seat,new String[]{"rid","setName","minCastType","minCastCash","setLayer","setNo","state","isPreOrder","location","lodge"},
						new String[]{"f1","f2","f3","f4","f5","f6","f7","f8","f9","f10"});
				listSend.add(row);
			}
			Response rep = cli.syncSets(shopId, "", listSend);
			System.out.println("Sets--"+rep.getErrorCode()+":"+rep.getErrorMessage());
			
			sql = new StringBuffer();
			listSend = new ArrayList();
			sql.append("select * from food_info");
			list = (List)this.getAll(sql,null);
			for(int i = 0;i < list.size();i ++)
			{
				Object obj = (Object)list.get(i);
				Food food = new Food();
				food.setIsOrder(DataUtil.getString(obj, "intelnetorder_food"));
				food.setName(DataUtil.getString(obj, "food_name"));
				food.setPrice(DataUtil.getString(obj, "food_price"));
				food.setUnit(DataUtil.getString(obj, "food_price_unit"));
				food.setRid(shopId);
				food.setState(DataUtil.getString(obj, "food_status"));
				food.setType(DataUtil.getString(obj, "food_type"));
				Row row = new Row(food,new String[]{"isOrder","name","price","rid","state","type","unit"},
						new String[]{"f1","f2","f3","f4","f5","f6","f7"});
				listSend.add(row);
			}
			rep = cli.syncFoods(shopId, "", listSend);
			System.out.println("Foods--"+rep.getErrorCode()+":"+rep.getErrorMessage());
			
			sql = new StringBuffer();
			listSend = new ArrayList();
			sql.append("select * from price_plan_define");
			list = (List)this.getAll(sql,null);
			for(int i = 0;i < list.size();i ++)
			{
				Object obj = (Object)list.get(i);
				Plan plan = new Plan();
				plan.setBeginTime(DataUtil.getString(obj, "eff_date"));
				plan.setEndTime(DataUtil.getString(obj, "exp_date"));
				plan.setPlanName(DataUtil.getString(obj, "price_name"));
				plan.setRid(shopId);
				plan.setStatus(DataUtil.getString(obj, "price_state"));
				Row row = new Row(plan,new String[]{"beginTime","endTime","planName","rid","status"},
						new String[]{"f1","f2","f3","f4","f5"});
				listSend.add(row);
			}
			rep = cli.syncDiscounts(shopId, "", listSend);
			System.out.println("Plans--"+rep.getErrorCode()+":"+rep.getErrorMessage());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

//定时释放座席
class SeatSetFree extends TimerTaskBO
{
	public void run()
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from storefront_info");
		String serviceIp="127.0.0.1";//www.580ya.com
		String sf_id = "1";
		try
		{
//			List listTmp = (List)this.getAll(sql,null);
//			if(listTmp.size() > 0)
//			{
//				Object o = (Object)listTmp.get(0);
//				serviceIp=DataUtil.getString(o, "service_ip");
//				sf_id = DataUtil.getString(o, "sf_id");
//			}
			sql = new StringBuffer();
			sql.append("select order_id,order_no,(now()-prearrange_order_time)/60 as a1,overdue_time from order_info where prearrange_order_time is not null and order_status=2;");
			List list = (List)this.getAll(sql,null);
			for(int i = 0;i < list.size();i ++)
			{
				Object obj = (Object)list.get(i);
				if(Double.parseDouble(DataUtil.getString(obj, "a1"))>Double.parseDouble(DataUtil.getString(obj, "overdue_time")))
				{
					sql = new StringBuffer();
					sql = sql.append("update order_info set order_status=3,prearrange_reseon='在预定时内未抵达' where order_id="+DataUtil.getString(obj, "order_id"));
					update(sql);
					ClientImpl cli = new ClientImpl(serviceIp);
					List listSend = new ArrayList();
					Orders order = new Orders();
//					order.setArrivingTime(DataUtil.getString(obj, "prearrange_order_time"));
					order.setOrderNo(DataUtil.getString(obj, "order_no"));
					order.setCancelTime(DataUtil.getCurrDateTime());
					order.setHandleTime(DataUtil.getCurrDateTime());
					order.setOrderTime(DataUtil.getCurrDateTime());
					order.setState(3+"");
					order.setSqlType(2);

					Row row = new Row(order,new String[]{"arrivingTime","cancelTime","handleTime","orderId","orderNo","orderTime","rid","seatNo","state","type","sqlType","total","manCount"},
							new String[]{"f1","f2","f3","f4","f5","f6","f7","f8","f9","f10","f11","f12","f13"});
					listSend.add(row);

					Response rep = cli.syncOrders(sf_id, "", listSend);
					if(!"1".equals(rep.getErrorCode()))
					{
						sql = new StringBuffer();
						sql.append("insert into order_info_temp (id,order_no,rid,sql_type) values ("+getMax("order_info_temp", "id")+",'"+DataUtil.getString(obj, "order_no")+"',"+sf_id+",2)");
						update(sql);
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
class OrderTask extends TimerTaskBO
{
	public void run() {
		List listSend = new ArrayList();
		String shopId = "0";
		StringBuffer sql = new StringBuffer();
		String delIds = "";
		String serviceIp = "127.0.0.1";//59.54.54.145
		sql.append("select * from storefront_info");
		try
		{
			List listTmp = (List)this.getAll(sql,null);
			if(listTmp.size() > 0)
			{
				Object object = (Object)listTmp.get(0);
				serviceIp = DataUtil.getString(object, "service_ip");
			}
			ClientImpl cli = new ClientImpl(serviceIp);
			sql = new StringBuffer();
			sql.append("select * from order_info_temp;");
			List list = (List)this.getAll(sql,null);
			if(list.size() > 0)
			{
				for(int i = 0;i < list.size();i ++)
				{
					Object obj = (Object)list.get(i);
					Orders order = new Orders();
					delIds = delIds + "'" + DataUtil.getString(obj, "id") + "',";
					shopId = DataUtil.getString(obj, "rid");
					order.setArrivingTime(DataUtil.getString(obj, "arrivingTime"));
					order.setCancelTime(DataUtil.getString(obj, "cancelTime"));
					order.setHandleTime(DataUtil.getString(obj, "handleTime"));
					order.setOrderId(DataUtil.getString(obj, "order_id"));
					order.setOrderNo(DataUtil.getString(obj, "order_no"));
					order.setOrderTime(DataUtil.getString(obj, "order_time"));
					order.setRid(DataUtil.getString(obj, "rid"));
					order.setSeatNo(DataUtil.getString(obj, "set_no"));
					order.setState(DataUtil.getString(obj, "order_status"));
					order.setType(DataUtil.getString(obj, "order_type"));
					order.setSqlType(Integer.parseInt(DataUtil.getString(obj, "sql_type")));
					order.setTotal(DataUtil.getString(obj, "total"));
					order.setManCount(DataUtil.getString(obj, "man_count"));
	
					Row row = new Row(order,new String[]{"arrivingTime","cancelTime","handleTime","orderId","orderNo","orderTime","rid","seatNo","state","type","sqlType","total","manCount"},
							new String[]{"f1","f2","f3","f4","f5","f6","f7","f8","f9","f10","f11","f12","f13"});
					listSend.add(row);
				}
				Response rep = cli.syncOrdersTemp(shopId, "", listSend);
				System.out.println("Orders--"+rep.getErrorCode()+":"+rep.getErrorMessage());
				if("1".equals(rep.getErrorCode()))
				{
					sql = new StringBuffer();
					delIds = delIds.substring(0,delIds.length()-1);
					sql.append("delete from order_info_temp where id in ("+delIds+");");
					update(sql);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
