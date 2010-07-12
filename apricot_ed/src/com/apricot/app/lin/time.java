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

public class time extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public time() {
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
	 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int y=0;
		String ORDER_STATUS=request.getParameter("ORDER_STATUS");
		String BELONG_TO=request.getParameter("BELONG_TO");
		String pretime=request.getParameter("pretime");
		String getPage=request.getParameter("getPage");
		request.setAttribute("ORDER_STATUS",ORDER_STATUS);
		request.setAttribute("BELONG_TO", BELONG_TO);
		request.setAttribute("pretime",pretime);
		
		if(getPage==null||getPage.equals(""))
	    request.setAttribute("getPage", "");
		else
		request.setAttribute("getPage", getPage);
		
		
		System.out.println(ORDER_STATUS);
		System.out.println(BELONG_TO);
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ResultSet rsy = null;
		 ResultSet rsx1 = null;
		 ResultSet rskong = null;
		 String sql;
		List dininglist=new ArrayList();			
	try{	
		if(BELONG_TO.equals("00"))
		{
			  sql = "select * from dining_set_info";	
		}
		else{ 
			 sql = "select * from dining_set_info where BELONG_TO='"+BELONG_TO+"'";
			}
		
		 //String sql = "select * from dining_set_info where BELONG_TO='"+BELONG_TO+"'";
		 System.out.println("查座位号"+sql);
		 conn=MYSQLDB.getDBC();
		 pstmt = conn.prepareStatement(sql);
		 rs =pstmt.executeQuery();
		 while(rs.next())
		 {
		
			//System.out.print("开始判断");	 
		if(ORDER_STATUS.equals("0")){	
			   
		String sqlx1="select * from order_info where SET_NO='"+rs.getString("SET_NO")+"'and ORDER_STATUS='0'and ORDER_TYPE<>'4'";			
		System.out.println(sqlx1);	  
		conn=MYSQLDB.getDBC();			  
			   pstmt = conn.prepareStatement(sqlx1);
			   rsx1 =pstmt.executeQuery();
				 rsx1 =pstmt.executeQuery();
				 if(rsx1.next())
				   {
					    dining_set_info dining_info=new dining_set_info();  
						dining_info.setSF_ID(rs.getString("SF_ID"));
						dining_info.setSET_NO(rs.getString("SET_NO"));
					 y=1;
					System.out.println("~~~~~~~~~~已经占座~~~~~~~~~~~~~~~~~"+dining_info.getSET_NO()); 
					Order_info order_info=new Order_info();
					order_info.setORDER_NO(rsx1.getString("ORDER_NO"));
				
					order_info.setORDER_ID(rsx1.getString("ORDER_ID"));
					System.out.println("id="+order_info.getORDER_ID());
					order_info.setMAN_COUNT(rsx1.getString("MAN_COUNT"));
					System.out.println("count="+order_info.getMAN_COUNT());
					order_info.setORDER_TIME(rsx1.getString("ORDER_TIME"));
					System.out.println("time="+order_info.getORDER_TIME());
					order_info.setORDER_TYPE(rsx1.getString("ORDER_TYPE"));
					order_info.setPREARRANGE_NAME(rsx1.getString("PREARRANGE_NAME"));
					order_info.setPREARRANGE_PHONE(rsx1.getString("PREARRANGE_PHONE"));			
					order_info.setPREARRANGE_MAN_COUNT(rsx1.getString("PREARRANGE_MAN_COUNT"));
					order_info.setVIP_CARD_NO(rsx1.getString("VIP_CARD_NO"));
					order_info.setPREARRANGE_ORDER_TIME(rsx1.getString("PREARRANGE_ORDER_TIME"));
					order_info.setOPERATE_ORDER_TIME(rsx1.getString("OPERATE_ORDER_TIME"));
					dining_info.setOrder_info(order_info);
				// dining_info.getOrder_info().setORDER_NO(rsx.getString("ORDER_NO"));
				  System.out.println("dining_info的大小"+dining_info.getOrder_info().getORDER_NO());					
				   	
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
				  
				    dininglist.add(dining_info);
				 }
		}
		 if(ORDER_STATUS.equals("1"))
			{
				y=0;
				String sqlkong="select * from dining_set_info t1 where t1.set_status='1' and t1.set_no not in (select set_no from order_info t2 where (t2.order_status='2'  and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('"+pretime+"' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+pretime+"' ,'%Y-%m-%d %T')) or t2.order_status='0')and t1.SET_NO='"+rs.getString("SET_NO")+"'order by t1.set_max desc";
				 conn=MYSQLDB.getDBC();
				 pstmt = conn.prepareStatement(sqlkong);
				rskong =pstmt.executeQuery();
				
				while(rskong.next())
				{
				//System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^为空");
					System.out.println(sqlkong);
				dining_set_info dining_info=new dining_set_info();  
				dining_info.setSF_ID(rskong.getString("SF_ID"));
				dining_info.setSET_NO(rskong.getString("SET_NO"));		
				dining_info.setDINING_FLOOR(rskong.getString("DINING_FLOOR"));
				dining_info.setBALCONY_CODE(rskong.getString("BALCONY_CODE"));
				dining_info.setBELONG_TO(rskong.getString("BELONG_TO"));
				dining_info.setBALCONY_NAME(rskong.getString("BALCONY_NAME"));
				dining_info.setSET_STATUS(rskong.getString("SET_STATUS"));
				dining_info.setSET_MAX(rskong.getString("SET_MAX"));
				dining_info.setPRE_ORDER_STYLE(rskong.getString("PRE_ORDER_STYLE"));
				dining_info.setPAY_MONEY(rskong.getString("PAY_MONEY"));
				dining_info.setPAY_STATUS(rskong.getString("PAY_STATUS"));
				dining_info.setMINCOST_MONEY(rskong.getString("MINCOST_MONEY"));
				dining_info.setMINCOST_TYPE(rskong.getString("MINCOST_TYPE"));
			    dining_info.setStatus(y);
			    dininglist.add(dining_info);
				}
			}
		//else if(ORDER_STATUS.equals("2"))
		 if(ORDER_STATUS.equals("2"))
		{
			
			 String sqly="select * from order_info t2 where (t2.order_status='2' and SET_NO='"+rs.getShort("SET_NO")+"' and t2.ORDER_TYPE in(1,2,3) and DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('"+pretime+"' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+pretime+"' ,'%Y-%m-%d %T'))";
		    //String sqly="select * from order_info where SET_NO='"+rs.getString("SET_NO")+"'and ORDER_STATUS='2'and ORDER_TYPE in(1,2,3)";
			//String sqly="select * from dining_set_info t1 where t1.set_status='1'  and t1.set_no in (select set_no from order_info t2 where (t2.order_status='2' and ORDER_TYPE in(1,2,3) and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('2009-07-20 14:28:24' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('2009-07-20 14:28:24' ,'%Y-%m-%d %T')) ) order by t1.set_max desc";
			System.out.println("预订的sql="+sqly);
			conn=MYSQLDB.getDBC();
				 pstmt = conn.prepareStatement(sqly);
				 rsy =pstmt.executeQuery();
				 while(rsy.next())
				   {	
					 y=2;
					    dining_set_info dining_info=new dining_set_info();  
						dining_info.setSF_ID(rs.getString("SF_ID"));
						dining_info.setSET_NO(rs.getString("SET_NO"));
			System.out.println("~~~~~~~~~~已经预定~~~~~~~~~~~~~~~~~"+dining_info.getSET_NO());					 
					    Order_info order_info=new Order_info();
					    order_info.setSET_NO(rsy.getString("SET_NO"));
					    System.out.println("set_no="+order_info.getSET_NO());
					    
					    order_info.setORDER_NO(rsy.getString("ORDER_NO"));
						//System.out.println("order_no="+order_info.getORDER_NO());
						
						order_info.setORDER_ID(rsy.getString("ORDER_ID"));
						System.out.println("order_id="+order_info.getORDER_ID());
						order_info.setMAN_COUNT(rsy.getString("MAN_COUNT"));
						System.out.println("man_count="+order_info.getMAN_COUNT());
						order_info.setORDER_TIME(rsy.getString("ORDER_TIME"));
						order_info.setORDER_TYPE(rsy.getString("ORDER_TYPE"));
						order_info.setPREARRANGE_NAME(rsy.getString("PREARRANGE_NAME"));
						order_info.setPREARRANGE_PHONE(rsy.getString("PREARRANGE_PHONE"));
						order_info.setPREARRANGE_MAN_COUNT(rsy.getString("PREARRANGE_MAN_COUNT"));
						order_info.setVIP_CARD_NO(rsy.getString("VIP_CARD_NO"));
						order_info.setPREARRANGE_ORDER_TIME(rsy.getString("PREARRANGE_ORDER_TIME"));
						order_info.setOPERATE_ORDER_TIME(rsy.getString("OPERATE_ORDER_TIME"));
						dining_info.setOrder_info(order_info);
						  
				   
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
				    dininglist.add(dining_info);
				   }
		 }
		
		
		  // System.out.println(dining_info.getDINING_FLOOR()+dining_info.getBALCONY_NAME()+dining_info.getBALCONY_CODE()+"位置状态"+  dining_info.getStatus()+"最低消费类型"+dining_info.getMINCOST_TYPE()+"最低消费金额="+dining_info.getMINCOST_MONEY());
		   
		
		}
		
		    rsy.close();
		    rsx1.close();
		    rskong.close();
		 	rs.close();   
		    pstmt.close();   
	        conn.close();   
}
	catch(Exception e)
	{
		e.getStackTrace();
	}
	finally{}
	 System.out.println("list的大小="+dininglist.size());
	 request.setAttribute("dininglist", dininglist);
	 request.getRequestDispatcher("/lin/dating.jsp").forward(request,response);
	 //RequestDispatcher a=getServletContext().getRequestDispatcher("/lin/dating.jsp");
	//	a.forward(request, response);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
