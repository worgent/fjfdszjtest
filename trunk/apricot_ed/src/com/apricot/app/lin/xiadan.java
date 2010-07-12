package com.apricot.app.lin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apricot.app.bean.nowtime;

public class xiadan extends HttpServlet {

	public xiadan() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		nowtime time=new nowtime();
		String ORDER_NO="F"+time.timenumber();
		System.out.println("编号！！！！！！！！！！！！！！！！！！="+ORDER_NO);
		response.setContentType("text/html");
		System.out.print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& jian lai xin dan la");
		String SET_NO=request.getParameter("SET_NO");
		String SF_ID=request.getParameter("SF_ID");
		String name=request.getParameter("name");
		String balcony_name=request.getParameter("balcony_name");
	    String telephone=request.getParameter("telephone");
	    String vip=request.getParameter("VIP");
		String waiter=request.getParameter("waiter");
		String Middleman=request.getParameter("Middleman");
		String starttime=request.getParameter("starttime");
		System.out.println("时间="+starttime);
		String guests=request.getParameter("guests");
		String MINCOST_TYPE=request.getParameter("MINCOST_TYPE");
		String hightmoney=request.getParameter("hightmoney");
	System.out.println("$$$$$$$$"+name+telephone+vip+waiter+Middleman+starttime+"来宾人数"+guests+MINCOST_TYPE+hightmoney);
	
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ResultSet rskong = null;
		 
		 try{
		 int sum=0; 
		 String sumx;
		 //下单之前查询位置是不是为空
		 String sqlkong="select * from order_info where SET_NO='"+SET_NO+"'and ORDER_STATUS='0'and ORDER_TYPE<>'4'";
		 System.out.println(sqlkong);
		 conn=MYSQLDB.getDBC();
		 pstmt = conn.prepareStatement(sqlkong);
		 rskong =pstmt.executeQuery();
		 if(rskong.next())
		 {
			 request.getRequestDispatcher("/lin/error.jsp").forward(request,response);
		 }
 else{	 
		 String sqlx="select MAX(ORDER_ID) as sum from order_info";
		 conn=MYSQLDB.getDBC();
		
			 pstmt = conn.prepareStatement(sqlx);
			 rs =pstmt.executeQuery();
			 while(rs.next())
		 		{
				  sumx=rs.getString("sum");
				  if(sumx==""||sumx==null)
			      {
			    	  sum=-1;
			      }
				  else{
			      sum=Integer.parseInt(rs.getString("sum"));
				  }
		 		}
			 sum++;
		 String sql = "insert into order_info(SET_NO,ORDER_NO,ORDER_ID,RECORD_STAFF_ID,SERVICE_STAFF_ID,MAN_COUNT,ORDER_TIME,ORDER_TYPE,PREARRANGE_NAME,PREARRANGE_PHONE,VIP_CARD_NO,SF_ID,balcony_name,MINCOST_TYPE,MINCOST_MONEY,introducer)values('"+SET_NO+"','"+ORDER_NO+"','"+sum+"','"+waiter+"','0','"+guests+"','"+starttime+"','0','"+name+"','"+telephone+"','"+vip+"','"+SF_ID+"','"+balcony_name+"','"+MINCOST_TYPE+"','"+hightmoney+"','"+Middleman+"')";
		 conn=MYSQLDB.getDBC();
		 pstmt = conn.prepareStatement(sql);
		 pstmt.executeUpdate(sql);
		    rs.close();   
		    pstmt.close();   
	        conn.close(); 
		 }
	 }
		 catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }finally{
				  
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
