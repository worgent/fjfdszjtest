/**
 * 
 */
package com.apricot.webservice.bean;

import hb.net.sms.client.Client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.apricot.eating.NestedException;
import com.apricot.eating.engine.BO;
import com.apricot.eating.engine.TimerTaskBO;
import com.apricot.eating.util.DataUtil;


/**
 * @author Administrator
 * 
 */
public class ClientBo extends TimerTaskBO {
	/**
	 * 
	 * 
	 * @param shopId
	 * @param username
	 * @param phone
	 * @param setno
	 * @param arrivetime
	 * @return
	 * @throws NestedException 
	 */
	public Response preOrder(Request req){
		System.out.println("req_XML:"+req.toXML());
		String shopId = req.getShopId();
		Response response =new Response();
		Iterator it = req.getRows();
		String username = "";
		String orderPersons = "";
		String phone = "";
		String arrTime = "";
		String setNo = "''";
		String netOrderId = "";
		while(it.hasNext())
		{
			Row row = (Row)it.next();
			HashMap m = row.getValues();
			orderPersons = (String)m.get("ordergoNumber");
			username = (String)m.get("username");
			phone = (String)m.get("phone");
			arrTime = (String)m.get("arrivetime");
			setNo = (String)m.get("setno");
			netOrderId = (String)m.get("net_order_id");
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from order_info where (order_status='0' ");
		sql.append("or (order_status='2' and date_add(PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<now() and DATE_ADD(PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) >now())) ");
		sql.append("and set_no="+setNo);
		try
		{
			List list = getAll(sql);
			if(list.size() > 0)
			{
				response.setErrorCode("77");
				response.setErrorMessage("该座位已被预订");
			}
			else
			{
				sql = new StringBuffer();
				sql.append("select * from dining_set_info where set_no='"+setNo+"'");
				Object obj = get(sql);
				if(obj == null)
				{
					response.setErrorCode("4");
					response.setErrorMessage("该座位不存在");
				}
				else
				{
					sql = new StringBuffer();
					sql.append("select * from customer_info where CUST_PHONE='"+phone+"'");
					Object oo = get(sql);
					sql = new StringBuffer();
					sql.append("insert into order_info (order_id,sf_id,set_no,order_status,order_type,order_no," +
							"prearrange_name,prearrange_phone,prearrange_order_time,PREARRANGE_MAN_COUNT,operate_order_time," +
							"balcony_name,MINCOST_TYPE,MINCOST_MONEY,vip_card_no,cum_point)" +
							" values ("+getMax("order_info", "order_id")+","+shopId+",'"+setNo+"','2','1','"+netOrderId+"','"+username+"','"+phone+"'" +
									",DATE_FORMAT('"+arrTime+"','%Y-%m-%d %T'),"+orderPersons+",now(),'"+DataUtil.getString(obj, "balcony_name")+"','"+DataUtil.getString(obj, "mincost_type")+"','"+DataUtil.getString(obj, "mincost_money")+"'" +
											",'"+(DataUtil.getString(oo, "vip_no")==null?"":DataUtil.getString(oo, "vip_no"))+"',"+(DataUtil.getString(oo, "cum_point")==null?0:DataUtil.getString(oo, "cum_point"))+");");
					System.out.println(sql);
					update(sql);
					response.setErrorCode("1");
					response.setErrorMessage("预订成功");
					sql = new StringBuffer();
					sql.append("select * from storefront_info");
					Object o = (Object)get(sql);
					if(o!=null && Integer.parseInt(DataUtil.getString(o, "sms_status"))==1)
					{
						Client cl = new Client("regCode","passWord");
						int flag = cl.reg("regCode", "passWord", "enterpriseNAME", "csName", "address", "linkTel", "linkMan", "eMail", "fax", "postCode", "mobileTel");
						if(flag == 0 || flag == 3)
						{
							String flagTemp = cl.sendSMS("Regcode", "Pwd", "Phone", "CONTENT", "Extnum", "Level", "Schtime", "0", "", "0");
						}
					}
				}
			}
			return response;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			response.setErrorCode("99");
			response.setErrorMessage("系统错误");
			return response;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
