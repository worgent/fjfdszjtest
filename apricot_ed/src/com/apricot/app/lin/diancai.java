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

public class diancai extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	response.setContentType("text/html;chareset=GB2312");
	//PrintWriter out = response.getWriter();//在输出时会乱码
	 System.out.println("进来了diancai");
	request.setCharacterEncoding("GB2312");
	HttpSession session=request.getSession();
	   
	String ORDER_ID=request.getParameter("order_id");
	String SF_ID=request.getParameter("set_id");
	System.out.print("jin="+SF_ID);

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
    String food_type=null;
    food_type=request.getParameter("food_type");
    if(food_type==null)
    {food_type="00";}
    System.out.println("food_type="+food_type);
    
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
			   System.out.println("错误1");
			    String sql3="select * from food_info where FOOD_ID='"+checkbox[i]+"'";
			   
			    conn=MYSQL.getDBC(); 
			    System.out.println("错误2");
			    pstmt = conn.prepareStatement(sql3);
			    System.out.println("ddd="+sql3);
			    System.out.println("错误3");
				 rs =pstmt.executeQuery();
		        System.out.println("错误的倒萨");
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
			 	/*
			 	Connection  conn = null;
				conn=MYSQL.getDBC();    
		        String sql1="select * from order_list where FOOD_ID='"+checkbox[i]+"'";
		        Statement stmt1=conn.createStatement();   
		        ResultSet rs1=stmt1.executeQuery(sql1);
		        while(rs1.next())
		        {
		        	 System.out.println("ddssxadsadsa3="+rs1.getString("ORDER_ID"));
		        }
		        
		        
		        */
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
        String sql="select * from food_info where SF_ID='"+SF_ID+"' and FOOD_TYPE='"+food_type+"'";
        Statement stmt=conn.createStatement();   
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
        	Food_info food_info=new Food_info();
        	System.out.println("菜名="+rs.getString("FOOD_NAME"));
        	food_info.setFOOD_ID(rs.getString("FOOD_ID"));
        	food_info.setFOOD_NAME(rs.getString("FOOD_NAME"));
        	food_info.setFOOD_PRICE(rs.getString("FOOD_PRICE"));
        	caidan.add(food_info);
        }  
        request.setAttribute("food_type",food_type);
        request.setAttribute("caidan",caidan);
        request.getRequestDispatcher("/lin/diancai.jsp").forward(request, response);
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
