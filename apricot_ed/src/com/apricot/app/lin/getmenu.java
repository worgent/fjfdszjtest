package com.apricot.app.lin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apricot.app.bean.Food_info;
import com.apricot.app.bean.Order_list;

public class getmenu extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getmenu() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
   try{
		response.setContentType("text/XML;charset=GB2312");
		PrintWriter out = response.getWriter();
		// System.out.println("dddddddddddddddddddddddddddddddddddddddd");
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rsa = null;
		 ResultSet rsxx = null;
		 List list_food=new ArrayList();
		String id=request.getParameter("orderid");
		 String sqlorder_list="select * from order_list where ORDER_ID='"+id+"'";
		// System.out.println("sqlorder_list="+sqlorder_list);
		 conn=MYSQLDB.getDBC();
		 pstmt = conn.prepareStatement(sqlorder_list);
		 rsa =pstmt.executeQuery();
		while(rsa.next()){
			   Order_list order_list=new Order_list();
			  // System.out.println(" jian   lai   le  "+sqlorder_list);
			 		String sql_food_info="select * from food_info where FOOD_ID='"+rsa.getString("FOOD_ID")+"'";
				//System.out.println("sql_food_info="+sql_food_info);
				conn=MYSQLDB.getDBC();
				pstmt = conn.prepareStatement(sql_food_info);
				rsxx =pstmt.executeQuery();	
				while(rsxx.next()){
					Food_info food_info=new Food_info();
					food_info.setFOOD_ID(rsxx.getString("FOOD_ID"));
					food_info.setFOOD_NAME(rsxx.getString("FOOD_NAME"));
					//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+food_info.getFOOD_NAME());
					food_info.setFOOD_PRICE(rsxx.getString("FOOD_PRICE"));
					food_info.setFOOD_PRICE_UNIT(rsxx.getString("FOOD_PRICE_UNIT"));
					food_info.setFOOD_STATUS(rsxx.getString("FOOD_STATUS"));							
					order_list.setFood_info(food_info);
				}
				order_list.setORDER_ID(rsa.getString("ORDER_ID"));
				order_list.setFOOD_ID(rsa.getString("FOOD_ID"));
				order_list.setFOOD_COUNT(rsa.getString("FOOD_COUNT"));
				order_list.setFOOD_DISCOUNT(rsa.getString("FOOD_DISCOUNT"));
				order_list.setORDER_LIST_ID(rsa.getString("ORDER_LIST_ID"));
				order_list.setFOOD_PRICE(rsa.getString("FOOD_PRICE"));
				order_list.setOLD_FOOD_ID(rsa.getString("OLD_FOOD_ID"));
				order_list.setFOOD_ACTION(rsa.getString("FOOD_ACTION"));
				order_list.setSERVING_FLAG(rsa.getString("SERVING_FLAG"));
				//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+order_list.getFOOD_ID());
	//order_info.getOrder_List_list().add(order_list);
				list_food.add(order_list);
				
//System.out.println("大小循环里%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+order_info.getOrder_List_list().size());
		     }
		Order_list order_list1;
		out.print("<?xml version='1.0' encoding='GB2312'?>");
		//System.out.print("<?xml version='1.0' encoding='GB2312'?>");
		out.println("<mens>");
		
		if(list_food==null||list_food.size()==0)
			;
		else
		for(int i=0; i<list_food.size(); i++)
		{
			
			order_list1=(Order_list)list_food.get(i);
			       out.println("<menu>"+order_list1.getFood_info().getFOOD_NAME()+" "+order_list1.getFood_info().getFOOD_PRICE()+" "+order_list1.getFOOD_COUNT()+"</menu>");
			System.out.println("<menu>"+order_list1.getFood_info().getFOOD_NAME()+" "+order_list1.getFood_info().getFOOD_PRICE()+" "+order_list1.getFOOD_COUNT()+"</menu>");
			
		}
	
		out.println("</mens>");
		out.close();
		
		
		
   }
    catch(Exception e)
    {
    	
    	System.out.println(e.toString());
    	System.out.println(e.getMessage());
    }
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
