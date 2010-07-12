package com.apricot.app.pda;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.*;

public class xiadan1 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public xiadan1() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		nowtime time=new nowtime();
		String ORDER_NO="F"+time.timenumber();
		System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& jian lai xin dan la");
		String SET_NO=request.getParameter("SET_NO");
		String SF_ID=request.getParameter("SF_ID");
		String prename=request.getParameter("prename");
		String balcony_name=request.getParameter("balcony_name");
	    String telephone=request.getParameter("telephone");
		String waiter=request.getParameter("waiter");
		String Middleman=request.getParameter("Middleman");
		String starttime=request.getParameter("starttime");
		String guests=request.getParameter("guests");
		String MINCOST_TYPE=request.getParameter("MINCOST_TYPE");
		String hightmoney=request.getParameter("hightmoney");
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$"+name+telephone+vip+waiter+Middleman+starttime+"来宾人数"+guests+MINCOST_TYPE+hightmoney);
//		if(SET_NO==""||SF_ID==""|| balcony_name==""||prename==""||telephone==""||waiter==""||Middleman==""||starttime==""||guests==""||MINCOST_TYPE==""||hightmoney=="")
		if(SET_NO==""||SF_ID==""|| balcony_name==""||starttime==""||guests==""||hightmoney=="")
	    {
	    	request.setAttribute("prename",prename);
	    	request.setAttribute("telephone",telephone);
	    	request.setAttribute("Middleman",Middleman);
	    	request.setAttribute("guests",guests);
	    	request.setAttribute("i","i");
	    	request.setAttribute("SET_NO",SET_NO);
	    	request.setAttribute("SF_ID",SF_ID);
	    	request.setAttribute("balcony_name",balcony_name);
	    	request.setAttribute("MINCOST_TYPE",MINCOST_TYPE);
	    	request.setAttribute("hightmoney",hightmoney);
	    	request.getRequestDispatcher("/app/phone/yuding.jsp").forward(request, response);
	    }
		else
		{
			 try{
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 int sum=0; 
		 //String sqlstart="select * from order_info where SET_NO='"+SET_NO+"'"; 
		 //查询数据库有多少条记录
		 String sqlx="select MAX(ORDER_ID) as sum from order_info";
		 conn=MYSQL.getDBC();
		
			 pstmt = conn.prepareStatement(sqlx);
			 rs =pstmt.executeQuery();
			 while(rs.next())
		 		{
			      sum=Integer.parseInt(rs.getString("sum"));
		 		}
			 sum++;
		
			 String kong="select * from order_info where SET_NO='"+SET_NO+"'and ORDER_STATUS='0'and ORDER_TYPE<>'4'";
			 conn=MYSQL.getDBC();
			 pstmt = conn.prepareStatement(kong);
			 rs =pstmt.executeQuery();
			 if(rs.next())
			 {
				System.out.print("^^^^^^^^^^^^^^^^^^^^fdsfdsfsdfdsfdsfdsf^^^^^^^^^^^^^^^^^^^^^^^^");
		    	request.setAttribute("prename",prename);
		    	request.setAttribute("telephone",telephone);
		    	request.setAttribute("Middleman",Middleman);
		    	request.setAttribute("guests",guests);
		    	request.setAttribute("i","j");
		    	request.setAttribute("SET_NO",SET_NO);
		    	request.setAttribute("SF_ID",SF_ID);
		    	request.setAttribute("balcony_name",balcony_name);
		    	request.setAttribute("MINCOST_TYPE",MINCOST_TYPE);
		    	request.setAttribute("hightmoney",hightmoney);
		    	RequestDispatcher rd=getServletContext().getRequestDispatcher("/app/phone/yuding.jsp");
		    	rd.forward(request, response);
		    	//request.getRequestDispatcher("/app/phone/yuding.jsp").forward(request, response);
			 }
			 else{ //用户开单数据插入数据库
				 
				 String VIPcard="";
				 String sql5="select vip_no from customer_info where cust_phone='"+telephone+"'";
				 	conn=MYSQL.getDBC();
				 	pstmt = conn.prepareStatement(sql5);
				 	rs=pstmt.executeQuery();
				 	if(rs.next())
				 	{
				 		VIPcard=rs.getString("vip_no");
				 	}
				 String sql = "insert into order_info(SET_NO,ORDER_NO,ORDER_ID,RECORD_STAFF_ID,SERVICE_STAFF_ID,MAN_COUNT,ORDER_TIME,can_order_time,ORDER_TYPE,PREARRANGE_NAME,PREARRANGE_PHONE,VIP_CARD_NO,SF_ID,balcony_name,MINCOST_TYPE,MINCOST_MONEY,introducer)values('"+SET_NO+"','"+ORDER_NO+"','"+sum+"','"+waiter+"','0','"+guests+"',now(),now(),'0','"+prename+"','"+telephone+"','"+VIPcard+"','"+SF_ID+"','"+balcony_name+"','"+MINCOST_TYPE+"','"+hightmoney+"','"+Middleman+"')";
				 conn=MYSQL.getDBC();
		 
				 pstmt = conn.prepareStatement(sql);
				 pstmt.executeUpdate(sql);
				 System.out.println("这里");
				 request.getRequestDispatcher("/servlet/chexunjieguo").forward(request, response);
			 	}
			 }
		 catch (SQLException e) {
			 System.out.println(e.toString());
			 System.out.println(e.getMessage());
			 e.printStackTrace();
			 }
			
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
