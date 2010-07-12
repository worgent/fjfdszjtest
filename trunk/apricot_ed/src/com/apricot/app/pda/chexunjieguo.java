package com.apricot.app.pda;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apricot.app.pda.*;

import com.mchange.util.PasswordUtils;

public class chexunjieguo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	//response.setContentType("text/html;chareset=GB2312");
	//PrintWriter out = response.getWriter();//在输出时会乱码
	
	
	dining_set_info dining_info=new dining_set_info(); 
	request.setCharacterEncoding("UTF-8");
	HttpSession session = request.getSession();
	List dininglist=new ArrayList();
	List dingdanxinxi=new ArrayList();
	String denglu_name=null;
	String denglu_num=null;
	String sqlx,sqly,sqlz,sql,sql4,sql5;
	int y=0;
	System.out.println("进补来");
	denglu_name=request.getParameter("denglu_name");
	denglu_num=request.getParameter("denglu_num");
	System.out.println("ppppppppppppp="+denglu_name);
			System.out.println("ppppppppppppp="+denglu_num);
	if(denglu_name==null&&denglu_num==null)
	{
		String aa=(String)session.getAttribute("denglu_name"); denglu_name=aa;System.out.println("ppppppppppppp="+denglu_name);
		String bb=(String)session.getAttribute("denglu_num"); denglu_num=bb;System.out.println("ppppppppppppp="+denglu_num);
	}
	else
	{
		session.setAttribute("denglu_name",denglu_name);
		session.setAttribute("denglu_num",denglu_num);
	}
	 Statement stmt1=null,stmt2=null,stmt3=null,stmt4=null,stmt5=null;
	 ResultSet rs1=null,rs2=null,rs3=null,rs4=null,rs5=null;
	
	 System.out.println("进补来1");
    try{
		Connection  conn = null;
		conn=MYSQL.getDBC(); 
		if(denglu_num==null)
        {sql="select * from dining_set_info where BALCONY_NAME like '%"+denglu_name+"%'";}
		else
		{sql="select * from dining_set_info where BALCONY_CODE like '%"+denglu_num+"%'";}
        Statement stmt=conn.createStatement();   
        ResultSet rs=stmt.executeQuery(sql);

        //y=0位置空闲;y=1占座;y=2预定;y=3没有这个餐位;
        while(rs.next()){
        	 
        	
			session.setAttribute("SF_ID",rs.getString("SF_ID"));

			dining_info.setSF_ID(rs.getString("SF_ID"));
			dining_info.setSET_NO(rs.getString("SET_NO"));
        	
        	 y=0;
        	 //占用状态
        	 sqlx="select * from order_info where SET_NO='"+dining_info.getSET_NO()+"'and ORDER_STATUS='0'and ORDER_TYPE<>'4'";
        	 stmt1=conn.createStatement();   
             rs1=stmt1.executeQuery(sqlx);
             if(rs1.next())
             {
            	y=1; 
            	//查询出订单的信息
            	Order_info order_info=new Order_info();
            	
            	order_info.setORDER_NO(rs1.getString("ORDER_NO"));
				order_info.setORDER_ID(rs1.getString("ORDER_ID"));
				order_info.setMAN_COUNT(rs1.getString("MAN_COUNT"));
				order_info.setORDER_TIME(rs1.getString("ORDER_TIME"));
				order_info.setORDER_TYPE(rs1.getString("ORDER_TYPE"));
				order_info.setPREARRANGE_NAME(rs1.getString("PREARRANGE_NAME"));
				order_info.setPREARRANGE_PHONE(rs1.getString("PREARRANGE_PHONE"));
				order_info.setPREARRANGE_MAN_COUNT(rs1.getString("PREARRANGE_MAN_COUNT"));
				order_info.setVIP_CARD_NO(rs1.getString("VIP_CARD_NO"));
				order_info.setPREARRANGE_ORDER_TIME(rs1.getString("PREARRANGE_ORDER_TIME"));
				order_info.setOPERATE_ORDER_TIME(rs1.getString("OPERATE_ORDER_TIME"));
            	
            	
            	
            	
				
				session.setAttribute("ORDER_ID",rs1.getString("ORDER_ID"));
				dining_info.setOrder_info(order_info);
				
				
				
				
				//查询出点的菜的信息
				
				sql4="select * from order_list where ORDER_ID='"+rs1.getString("ORDER_ID")+"'";
				//order_list.setORDER_ID(rs1.getString("ORDER_ID"));
	        	stmt4=conn.createStatement();   
	            rs4=stmt4.executeQuery(sql4);
	            while(rs4.next())
	            {
	            	Order_list order_list=new Order_list();
	            	System.out.println("food_id="+rs4.getString("FOOD_ID"));
	            	sql5="select * from food_info where FOOD_ID='"+rs4.getString("FOOD_ID")+"'";
		        	stmt5=conn.createStatement(); 
		            rs5=stmt5.executeQuery(sql5);
		            while(rs5.next())
		            {    
		            	 Food_info food_info=new Food_info();
		            	 food_info.setFOOD_NAME(rs5.getString("FOOD_NAME"));
		            	 food_info.setFOOD_PRICE(rs5.getString("FOOD_PRICE"));
		            	 order_list.setFood_info(food_info);
		            }
	            	order_list.setFOOD_COUNT(rs4.getString("FOOD_COUNT"));
	            	order_list.setSERVING_FLAG(rs4.getString("SERVING_FLAG"));
	            	order_list.setFOOD_ACTION(rs4.getString("FOOD_ACTION"));
	            	dingdanxinxi.add(order_list);
	            }
				System.out.print("点座菜的个数="+dingdanxinxi.size());	
             }
             
             
             
             //预定状态
             sqly="select * from order_info t2 where (t2.order_status='2' and SET_NO='"+rs.getShort("SET_NO")+"' and t2.ORDER_TYPE in(1,2,3) and DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT(now() ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT(now() ,'%Y-%m-%d %T'))";
             stmt2=conn.createStatement();   
              rs2=stmt2.executeQuery(sqly);
        	 if(rs2.next())
        	 {
        		 y=2;
        		//查询出订单的信息
        		 Order_info order_info=new Order_info();
 				
 				order_info.setORDER_NO(rs2.getString("ORDER_NO"));
				order_info.setORDER_ID(rs2.getString("ORDER_ID"));
				order_info.setMAN_COUNT(rs2.getString("MAN_COUNT"));
				order_info.setORDER_TYPE(rs2.getString("ORDER_TYPE"));
				order_info.setPREARRANGE_NAME(rs2.getString("PREARRANGE_NAME"));
				order_info.setPREARRANGE_PHONE(rs2.getString("PREARRANGE_PHONE"));
				order_info.setPREARRANGE_MAN_COUNT(rs2.getString("PREARRANGE_MAN_COUNT"));
				order_info.setVIP_CARD_NO(rs2.getString("VIP_CARD_NO"));
				order_info.setPREARRANGE_ORDER_TIME(rs2.getString("PREARRANGE_ORDER_TIME"));
				order_info.setOPERATE_ORDER_TIME(rs2.getString("OPERATE_ORDER_TIME"));
 				 dining_info.setOrder_info(order_info);
                 System.out.print("sssssssss="+dining_info.getOrder_info().getORDER_NO());
                 
                 
                 session.setAttribute("ORDER_ID",rs2.getString("ORDER_ID"));
 				dining_info.setOrder_info(order_info);
 				
 				
                //查询出点的菜的信息
 				
 				sql4="select * from order_list where ORDER_ID='"+rs2.getString("ORDER_ID")+"'";
 				System.out.println("错误="+rs2.getString("ORDER_ID"));
 				//order_list.setORDER_ID(rs2.getString("ORDER_ID"));
 	        	stmt4=conn.createStatement(); 
 	        	System.out.println("sql4"+sql4);
 	            rs4=stmt4.executeQuery(sql4);
 	           System.out.println("错误1");
 	            while(rs4.next())
 	            {   
 	            	Order_list order_list=new Order_list();
 	            	System.out.println("food_id="+rs4.getString("FOOD_ID"));
 	            	System.out.println("错误2");
 	            	sql5="select * from food_info where FOOD_ID='"+rs4.getString("FOOD_ID")+"'";
 		        	stmt5=conn.createStatement(); 
 		            rs5=stmt5.executeQuery(sql5);
 		           System.out.println("错误3");
 		            while(rs5.next())
 		            {   
 		            	 Food_info food_info=new Food_info();
 		            	 food_info.setFOOD_NAME(rs5.getString("FOOD_NAME"));
 		            	 food_info.setFOOD_PRICE(rs5.getString("FOOD_PRICE"));
 		            	 order_list.setFood_info(food_info);
 		            }
 	            	order_list.setFOOD_COUNT(rs4.getString("FOOD_COUNT"));
 	            	order_list.setSERVING_FLAG(rs4.getString("SERVING_FLAG"));
 	            	order_list.setFOOD_ACTION(rs4.getString("FOOD_ACTION"));
 	            	dingdanxinxi.add(order_list);
 	            	System.out.println("错误4");
 	            }
 				System.out.print("预定菜的个数="+dingdanxinxi.size());
        	 }
        	    dining_info.setMINCOST_TYPE(rs.getString("MINCOST_TYPE"));
        	    dining_info.setMINCOST_MONEY(rs.getString("MINCOST_MONEY"));
        	    dining_info.setDINING_FLOOR(rs.getString("DINING_FLOOR"));
				dining_info.setBALCONY_CODE(rs.getString("BALCONY_CODE"));
				dining_info.setBELONG_TO(rs.getString("BELONG_TO"));
				dining_info.setBALCONY_NAME(rs.getString("BALCONY_NAME"));
				dining_info.setSET_STATUS(rs.getString("SET_STATUS"));
				dining_info.setSET_MAX(rs.getString("SET_MAX"));
				dining_info.setPRE_ORDER_STYLE(rs.getString("PRE_ORDER_STYLE"));
			    dining_info.setStatus(y);
		
        }
        if(rs.last()==false)
        {y=3;}
        String z=y+"";
        System.out.println("woowowowoowow="+z);
        request.setAttribute("zhuangtai",z);
        request.setAttribute("dining_info", dining_info);
        request.setAttribute("dingdanxinxi", dingdanxinxi);

        request.getRequestDispatcher("/app/phone/jieguo.jsp").forward(request, response);
        rs.close();
        stmt.close();
        conn.close();   
     }catch(Exception e){   
        e.printStackTrace();   
     }   
           
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	this.doGet(request, response);
	}
	}
