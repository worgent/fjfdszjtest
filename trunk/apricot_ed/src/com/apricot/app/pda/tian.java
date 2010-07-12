package com.apricot.app.pda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class tian {
		
		class chajieguo extends Thread{
		public void run(){
			
			String sql1;
			try{
					Connection  conn = null;
					conn=MYSQL.getDBC(); 
					for(int i=0;i<3;i++)
					{
						nowtime time=new nowtime();
						System.out.println("12345="+time.printNextTime(i));
						String time1=time.printNextTime(i)+"12:00:00";
						
						sql1="select * from dining_set_info t1 where t1.set_status='1' and t1.set_no not in (select set_no from order_info t2 where (t2.order_status='2'  and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('"+time1+"' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+time1+"' ,'%Y-%m-%d %T')) or t2.order_status='0')order by t1.set_max desc";
						Statement stmt=conn.createStatement();   
					    ResultSet rs=stmt.executeQuery(sql1);
					    while(rs.next())
					    {	
					    	System.out.println("you="+rs.getString("SET_NO"));
					    }
					}
				}
				catch(Exception e){   
		        e.printStackTrace();   
		     }   

		
		}
	}

	
	


	public String ai(){
   chajieguo a=new chajieguo();
   a.start();
   return a.toString();
		
	}

}
//当前所有位置为空闲的
//select * from dining_set_info t1 where t1.set_status='1' and t1.set_no not in (select set_no from order_info t2 where (t2.order_status='2'  and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT(now() ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT(now() ,'%Y-%m-%d %T')) or t2.order_status='0') order by t1.set_max desc
