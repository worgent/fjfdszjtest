
package com.apricot.app.lin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apricot.app.bean.Food_info;
import com.apricot.app.bean.Order_info;
import com.apricot.app.bean.Order_list;
import com.apricot.app.bean.dining_set_info;


public class selectkongweizhi extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public selectkongweizhi() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         int y;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ResultSet rsx = null;
		 ResultSet rsy = null;
		 ResultSet rsxx = null;
		 ResultSet rsa = null;
			List dininglist=new ArrayList();
			List dingdanxinxi=new ArrayList();
		 String sqlx,sqly,sql4,sql5;
		 
		// ResultSet rs1=null,rs2=null,rs3=null,rs4=null,rs5=null;
		 
		 String sql = "select*from dining_set_info ";
		 //conn = MYSQLDB.getDBC();
		 conn=MYSQLDB.getDBC();
		 try {
		 pstmt = conn.prepareStatement(sql);
		 rs =pstmt.executeQuery();
		 while(rs.next())
		  {
	        dining_set_info dining_info=new dining_set_info();  
			dining_info.setSF_ID(rs.getString("SF_ID"));
			dining_info.setSET_NO(rs.getString("SET_NO"));			 
			 Connection connx1 = null;
			 PreparedStatement pstmtx1 = null;
			 ResultSet rsx1 = null;
			 y=0;
			String sqlx1="select * from order_info where SET_NO='"+dining_info.getSET_NO()+"'and ORDER_STATUS='0'and ORDER_TYPE<>'4'";
			//System.out.print("sqlx1="+sqlx1);
			   conn=MYSQLDB.getDBC();
			   Date date = new Date();
			   try{
			   pstmtx1 = conn.prepareStatement(sqlx1);
			   rsx1 =pstmtx1.executeQuery();
			 if(rsx1.next())
			   {
			//	System.out.println("~~~~~~~~~~已经占座~~~~~~~~~~~~~~~~~"+dining_info.getSET_NO()); 
				Order_info order_info=new Order_info();
				order_info.setORDER_NO(rsx1.getString("ORDER_NO"));
				order_info.setORDER_ID(rsx1.getString("ORDER_ID"));
				order_info.setORDER_TIME(rsx1.getString("ORDER_TIME"));			
				order_info.setPREARRANGE_NAME(rsx1.getString("PREARRANGE_NAME"));
				order_info.setPREARRANGE_PHONE(rsx1.getString("PREARRANGE_PHONE"));			
				order_info.setPREARRANGE_MAN_COUNT(rsx1.getString("PREARRANGE_MAN_COUNT"));
				dining_info.setOrder_info(order_info);
				// dining_info.getOrder_info().setORDER_NO(rsx.getString("ORDER_NO"));
			    y=1;
			   	}
		 
			   }
			   catch (SQLException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
					 }finally{
					 }
			 
			 sqly="select * from order_info where SET_NO='"+dining_info.getSET_NO()+"'and ORDER_STATUS='2'and ORDER_TYPE in(1,2,3)";
			// System.out.println("sqly="+sqly);
			 conn=MYSQLDB.getDBC();
			 pstmt = conn.prepareStatement(sqly);
			 rsy =pstmt.executeQuery();
			 if(rsy.next())
			   {
				
				//System.out.println("~~~~~~~~~~已经预定~~~~~~~~~~~~~~~~~"+dining_info.getSET_NO());
				 
				    Order_info order_info=new Order_info();
				    order_info.setSET_NO(rsy.getString("SET_NO"));
					order_info.setORDER_NO(rsy.getString("ORDER_NO"));
					order_info.setORDER_ID(rsy.getString("ORDER_ID"));
					order_info.setMAN_COUNT(rsy.getString("MAN_COUNT"));
					order_info.setORDER_TIME(rsy.getString("ORDER_TIME"));
					order_info.setORDER_TYPE(rsy.getString("ORDER_TYPE"));
					order_info.setPREARRANGE_NAME(rsy.getString("PREARRANGE_NAME"));
					order_info.setPREARRANGE_PHONE(rsy.getString("PREARRANGE_PHONE"));
					order_info.setPREARRANGE_MAN_COUNT(rsy.getString("PREARRANGE_MAN_COUNT"));
					order_info.setVIP_CARD_NO(rsy.getString("VIP_CARD_NO"));
					order_info.setPREARRANGE_ORDER_TIME(rsy.getString("PREARRANGE_ORDER_TIME"));
					order_info.setOPERATE_ORDER_TIME(rsy.getString("OPERATE_ORDER_TIME"));
					
					
					 
					 String sqlorder_list="select * from order_list where ORDER_ID='"+rsy.getString("ORDER_ID")+"'";
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
								//System.out.println(food_info.getFOOD_NAME());
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
						//	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+order_list.getFOOD_ID());
				order_info.getOrder_List_list().add(order_list);
							
		//System.out.println("大小循环里%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+order_info.getOrder_List_list().size());
					     }
					
	System.out.println("大小%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+order_info.getOrder_List_list().size());
				
					
					
					dining_info.setOrder_info(order_info);
					
			    y=2;
			   	}
			    dining_info.setDINING_FLOOR(rs.getString("DINING_FLOOR"));
				dining_info.setBALCONY_CODE(rs.getString("BALCONY_CODE"));
				dining_info.setBELONG_TO(rs.getString("BELONG_TO"));
				dining_info.setBALCONY_NAME(rs.getString("BALCONY_NAME"));
				dining_info.setSET_STATUS(rs.getString("SET_STATUS"));
				dining_info.setSET_MAX(rs.getString("SET_MAX"));
				dining_info.setPRE_ORDER_STYLE(rs.getString("PRE_ORDER_STYLE"));
				dining_info.setPAY_MONEY(rs.getString("PAY_MONEY"));
				dining_info.setPAY_STATUS(rs.getString("PAY_STATUS"));
				dining_info.setMINCOST_MONEY(rs.getString("MINCOST_MONEY"));
				dining_info.setMINCOST_TYPE(rs.getString("MINCOST_TYPE"));
			    dining_info.setStatus(y);
			  // System.out.println(dining_info.getDINING_FLOOR()+dining_info.getBALCONY_NAME()+dining_info.getBALCONY_CODE()+"位置状态"+  dining_info.getStatus()+"最低消费类型"+dining_info.getMINCOST_TYPE()+"最低消费金额="+dining_info.getMINCOST_MONEY());
			   
			dininglist.add(dining_info);
			
			
			/*Order_list order_list;
			for(int i=0;i<dininglist.size();i++)
			{
				order_list=(Order_list)dining_info.getOrder_info().getOrder_List_list().get(i);
				System.out.println("FOOD_ID======================"+order_list.getFOOD_ID());
			}
			*/
		  }
		    rs.close();   
		    pstmt.close();   
	        conn.close();    
		 }
		 catch (SQLException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }finally{
		 }

		 request.setAttribute("dininglist", dininglist);
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LIST的大小= "+dininglist.size());
	
   RequestDispatcher a=	getServletContext().getRequestDispatcher("/lin/dating.jsp");
	a.forward(request, response);
	  
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	
		this.doGet(request, response);
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

