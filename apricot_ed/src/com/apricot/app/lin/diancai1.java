package com.apricot.app.lin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apricot.app.lin.MYSQL;
import com.mchange.util.PasswordUtils;
import com.apricot.app.bean.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class diancai1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	response.setContentType("text/html;chareset=GB2312");
	request.setCharacterEncoding("GB2312"); 
	String ORDER_ID=request.getParameter("order_id");
	String SF_ID=request.getParameter("set_id");
	HttpSession session=request.getSession();
	
	if((SF_ID==null||SF_ID=="")||(ORDER_ID==null||ORDER_ID=="")){
		 ORDER_ID=(String)session.getAttribute("ORDER_ID");
		 SF_ID=(String)session.getAttribute("SF_ID");
		 //System.out.print("dddddddddddddddddddddd为空dddddddddddddddddddddddddddddddddddd="+dd);
		}
	    else	
			{    
	    	System.out.print("ddggggggggggggggggggggg不为空gggggggggggggggggggggggggggggggggggggggggggg"+SF_ID);
				session.setAttribute("SF_ID",SF_ID);
				session.setAttribute("ORDER_ID",ORDER_ID);
				System.out.println("得到Session"+session.getAttribute("SF_ID"));
			} 
	
	
	
	
	List caidan=new ArrayList();
	
	
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
			    String sql3="select * from food_info where FOOD_ID='"+checkbox[i]+"'";
			    conn=MYSQL.getDBC(); 
			    pstmt = conn.prepareStatement(sql3);
				 rs =pstmt.executeQuery();
				 while(rs.next())
				 {
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
		        String sql2="insert into order_list(ORDER_ID,FOOD_ID,ORDER_LIST_ID,MODIFY_STAFF_ID,FOOD_PRICE,OLD_FOOD_ID,FOOD_ACTION,SERVING_FLAG)values('"+ORDER_ID+"','"+checkbox[i]+"','"+sum+"','2','"+food_price+"','1','1','0')";   
		        conn=MYSQL.getDBC(); 
		        pstmt = conn.prepareStatement(sql2);
				pstmt.executeUpdate(sql2);
			    }
			}	 
	 }
	 catch(Exception e){   
		 System.out.println("dsasdaaaaaaaaaa=0");  
	     }
	
	
	
	
	
	
    //输出对应酒店的菜名，供用户点菜
    try{
    	
		Connection  conn = null;
		conn=MYSQL.getDBC();    
        String sql="select * from food_info where SF_ID='"+SF_ID+"'";
        Statement stmt=conn.createStatement();   
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
        	Food_info food_info=new Food_info();
        	System.out.println("菜名="+rs.getString("FOOD_NAME"));
        	food_info.setFOOD_TYPE(rs.getString("FOOD_TYPE"));
        	food_info.setFOOD_ID(rs.getString("FOOD_ID"));
        	food_info.setFOOD_NAME(rs.getString("FOOD_NAME"));
        	food_info.setFOOD_PRICE(rs.getString("FOOD_PRICE"));
        	caidan.add(food_info);
        }  
        request.setAttribute("caidan",caidan);
        request.getRequestDispatcher("/lin/diancai1.jsp").forward(request, response);
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
