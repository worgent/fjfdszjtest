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

public class diancai00 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	response.setContentType("text/html;chareset=GB2312");
	//PrintWriter out = response.getWriter();//在输出时会乱码
	
	request.setCharacterEncoding("GB2312");
	HttpSession session = request.getSession();
	String SF_ID=(String)session.getAttribute("SF_ID");
	String ORDER_ID=(String)session.getAttribute("ORDER_ID");
	List caidan=new ArrayList();
    String food_type=null;
    food_type=request.getParameter("food_type");
    if(food_type==null)
    {food_type="00";}
    //System.out.println("food_type="+food_type);
    
  //把点的菜加入数据库
	String checkbox[]=request.getParameterValues("diancaicheckbox");
	try{
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;

		 System.out.println("点框的个数="+checkbox.length);
		 for(int i=0;i<checkbox.length;i++)
			{
			   String food_price=null;
			   String caishu=request.getParameter(checkbox[i]);
			   System.out.println("菜数="+caishu);
			   System.out.println("FOOD_ID="+checkbox[i]);
			    String sql3="select * from food_info where FOOD_ID='"+checkbox[i]+"'";
			   
			    conn=MYSQL.getDBC(); 
			    pstmt = conn.prepareStatement(sql3);
			    System.out.println("ddd="+sql3);
				 rs =pstmt.executeQuery();
				 while(rs.next())
				 {
					 System.out.println("food_price="+rs.getString("FOOD_PRICE"));
					 food_price=rs.getString("FOOD_PRICE");
				 }
			   
			    int a=Integer.parseInt(caishu);
			    for(int j=0;j<a;j++)
			    {
			    int sum=0;
			    String sql1="select MAX(ORDER_LIST_ID) as sum from order_list";
			    conn=MYSQL.getDBC(); 
			    pstmt = conn.prepareStatement(sql1);
				 rs =pstmt.executeQuery();
				 while(rs.next())
				 {
					 sum=Integer.parseInt(rs.getString("sum"));
				 }
				 sum++;
		        // String sql2="UPDATE order_list set SERVING_FLAG='1' where ORDER_ID='"+ORDER_ID+"' and FOOD_ID='"+checkbox[i]+"'";
		        String sql2="insert into order_list(ORDER_ID,FOOD_ID,ORDER_LIST_ID,MODIFY_STAFF_ID,FOOD_PRICE,OLD_FOOD_ID,FOOD_ACTION,SERVING_FLAG)values('"+ORDER_ID+"','"+checkbox[i]+"','"+sum+"','2','"+food_price+"','1','1','0')";
		        System.out.println(sql2);
		        
		        conn=MYSQL.getDBC(); 
		        pstmt = conn.prepareStatement(sql2);
				pstmt.executeUpdate(sql2);

			    }
			 System.out.println("dd33333333333333333333="+checkbox[i]);
			}
		 
	 }
	 catch(Exception e){   
		 System.out.println("dsasdaaaaaaaaaa=0");  
	     }
    
    
    //输出对应酒店的菜名，供用户点菜
    try{
    	
		Connection  conn = null;
		conn=MYSQL.getDBC();
		String attr_id = "0";
        String sql2 = "select * from sys_table_attribute where TABLE_COLUMN = 'SPICY_LEVEL'";
        Statement stmt=conn.createStatement();
        ResultSet rs2 = stmt.executeQuery(sql2);
        if(rs2.next())
        {
        	attr_id = rs2.getString("ATTR_ID");
        }
        String sql="select fi.*,sav.attr_desc from food_info as fi left join sys_attribute_value as sav on (fi.spicy_level=sav.attr_value) where fi.SF_ID='"+SF_ID+"' and fi.FOOD_TYPE='"+food_type+"' and sav.attr_id="+attr_id;
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
        	Food_info food_info=new Food_info();
        	System.out.println("菜名="+rs.getString("FOOD_NAME"));
        	food_info.setFOOD_ID(rs.getString("FOOD_ID"));
        	food_info.setFOOD_NAME(rs.getString("FOOD_NAME")+"("+rs.getString("attr_desc")+")");
        	food_info.setFOOD_PRICE(rs.getString("FOOD_PRICE"));
        	caidan.add(food_info);
        }  
        request.setAttribute("food_type",food_type);
        request.setAttribute("caidan",caidan);
     }catch(Exception e){   
        e.printStackTrace();   
     }   
     
     //输出已点菜单
     try
     {
    	 Connection  conn = null;
 		 conn=MYSQL.getDBC();  
 		 Statement stmt=conn.createStatement(); 
 		List dingdanxinxi=new ArrayList();
    	 String sql4="select * from order_list where ORDER_ID='"+ORDER_ID+"'";
			//order_list.setORDER_ID(rs2.getString("ORDER_ID"));
    	 stmt=conn.createStatement(); 
      	System.out.println("sql4"+sql4);
      	ResultSet rs4=stmt.executeQuery(sql4);
         System.out.println("错误1");
          while(rs4.next())
          {   
          	Order_list order_list=new Order_list();
          	System.out.println("food_id="+rs4.getString("FOOD_ID"));
          	System.out.println("错误2");
          	String sql5="select * from food_info where FOOD_ID='"+rs4.getString("FOOD_ID")+"'";
          	Statement stmt5=conn.createStatement(); 
          	ResultSet rs5=stmt5.executeQuery(sql5);
	           System.out.println("错误3");
	            while(rs5.next())
	            {   
	            	 Food_info food_info=new Food_info();
	            	 food_info.setFOOD_ID(rs5.getString("FOOD_Id"));
	            	 food_info.setORDER_LIST_ID(rs4.getString("order_list_id"));
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
			request.setAttribute("dingdanxinxi", dingdanxinxi);
			request.getRequestDispatcher("/app/phone/diancai.jsp").forward(request, response); 
	        stmt.close();   
	        conn.close();   
     }
     catch(Exception e)
     {
    	 e.printStackTrace();   
     }
    
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	this.doGet(request, response);
	}
	}
