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

public class pre_xiadan_select extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public pre_xiadan_select() {
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

		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
	System.out.println("进来了~~~~~~~~~~~~~~~预订下单查询");
	String SET_NO=request.getParameter("SET_NO");
	String xiadantime=request.getParameter("xiadantime");
	String Operation=request.getParameter("Operation");
	String ORDER_NO=request.getParameter("ORDER_NO");
	System.out.println(ORDER_NO);
	System.out.println(xiadantime);
	System.out.println(Operation);
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 String ORDER_TIME=null;
	 try{ 
	 String sql_ORDER_TIME="select ORDER_TIME from order_info where (ORDER_NO='"+ORDER_NO+"')";
	 System.out.println(sql_ORDER_TIME);
	 conn=MYSQLDB.getDBC();
	 pstmt = conn.prepareStatement(sql_ORDER_TIME);
	 rs=pstmt.executeQuery();
	 while(rs.next())
	 {
		 ORDER_TIME=rs.getString("ORDER_TIME");
	 }
	 
	 if(ORDER_TIME==null||ORDER_TIME=="")
	 {
			String sql="update order_info set RECORD_STAFF_ID='"+Operation+"',ORDER_TIME='"+xiadantime+"',ORDER_STATUS='0'where (ORDER_NO='"+ORDER_NO+"')";
		 	conn=MYSQLDB.getDBC();
		    pstmt = conn.prepareStatement(sql);
		    pstmt.executeUpdate(sql);
	
	 }	 
	 else{
		 request.getRequestDispatcher("/lin/error.jsp").forward(request,response);
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
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
