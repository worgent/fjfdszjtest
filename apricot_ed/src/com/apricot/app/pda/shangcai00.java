package com.apricot.app.pda;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apricot.app.pda.MYSQL;
import com.mchange.util.PasswordUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class shangcai00 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	response.setContentType("text/html;chareset=GB2312");
	//PrintWriter out = response.getWriter();//在输出时会乱码
	 
	
	
	
	List shangcailist=new ArrayList();
	request.setCharacterEncoding("GB2312");
	HttpSession session = request.getSession();
	String SF_ID=(String)session.getAttribute("SF_ID"); 
	String ORDER_ID=(String)session.getAttribute("ORDER_ID");
	//更新已上的菜
	String checkbox[]=request.getParameterValues("shangcaicheckbox");
	 try{
		 System.out.println("dsasdaaaaaaaaaa="+checkbox.length);
		 for(int i=0;i<checkbox.length;i++)
			{
			    String caishu=request.getParameter(checkbox[i]);
			    System.out.println("dsjakkkkkkkkkkkkkkkkkkk="+caishu);
			    
			    Connection  conn = null;
				conn=MYSQL.getDBC();
				
				System.out.println("错误");
				 String sql3="select * from order_list where ORDER_ID='"+ORDER_ID+"' and FOOD_ID='"+checkbox[i]+"' and SERVING_FLAG='0' and FOOD_ACTION='1'  limit "+caishu;
			     Statement stmt3=conn.createStatement();   
			     ResultSet rs3=stmt3.executeQuery(sql3);
			     System.out.println("错误1");
			     while(rs3.next())
			     {
					System.out.println("ssssssss="+rs3.getString("ORDER_LIST_ID"));
					System.out.println("dddddddd="+ORDER_ID);
					System.out.println("dddddddd="+checkbox[i]);
					String sql2="UPDATE order_list set SERVING_FLAG='1' where  (ORDER_ID='"+ORDER_ID+"' and FOOD_ID='"+checkbox[i]+"' and FOOD_ACTION='1' and ORDER_LIST_ID='"+rs3.getString("ORDER_LIST_ID")+"')";
					System.out.println("sql="+sql2);
					Statement stmt2=conn.createStatement();   
			        stmt2.executeUpdate(sql2);
					System.out.println("dd33333333333333333333="+checkbox[i]);
			     }
			}
		 
	 }
	 catch(Exception e){   
		 System.out.println("dsasdaaaaaaaaaa=0");  
	     }  
	
	//查询出没上的菜，反回页面
    try{
    	
		Connection  conn = null;
		conn=MYSQL.getDBC();    
        String sql="select * from order_list where ORDER_ID='"+ORDER_ID+"' and SERVING_FLAG='0' and FOOD_ACTION='1'";
        Statement stmt=conn.createStatement();   
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
        	Order_list order_list=new Order_list();
        	order_list.setFOOD_ACTION(rs.getString("FOOD_ACTION"));
        	order_list.setFOOD_PRICE(rs.getString("FOOD_PRICE"));
        	order_list.setFOOD_ID(rs.getString("FOOD_ID"));
        	conn=MYSQL.getDBC();    
            String sql1="select * from food_info where FOOD_ID='"+rs.getString("FOOD_ID")+"'";
            Statement stmt1=conn.createStatement();   
            ResultSet rs1=stmt1.executeQuery(sql1);
            while(rs1.next())
            {
            	Food_info food_info=new Food_info();
            	//System.out.println("菜的名字是："+rs1.getString("FOOD_NAME"));
            	food_info.setFOOD_NAME(rs1.getString("FOOD_NAME"));
            	order_list.setFood_info(food_info);
            }
            shangcailist.add(order_list);
        }   
        request.setAttribute("shangcai",shangcailist);
        request.getRequestDispatcher("/app/phone/shangcai.jsp").forward(request, response);
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
