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

import com.apricot.app.bean.dining_set_info;

public class selectweizhi extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public selectweizhi() {
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

		 
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ResultSet rsx = null;
		 dining_set_info dining_info=new dining_set_info();  
		 //List dininglist=new ArrayList();
		 String sqlx;
		 String sql = "select*from dining_set_info ";
		 //conn = MYSQLDB.getDBC();
		 conn=MYSQL.getDBC();
		 try {
		 pstmt = conn.prepareStatement(sql);
		 //pstmt.setString(1, "02"); //1 对应的就是第一个问号，“00”就是你要在问号里的数据
		 rs =pstmt.executeQuery();
		 while(rs.next())
		  {
	        
			dining_info.setSF_ID(rs.getString("SF_ID"));
			dining_info.setSET_NO(rs.getString("SET_NO"));
			/*
			 sqlx="select * from order_info where SET_NO='"+dining_info.getSET_NO()+"'";
			   conn=MYSQL.getDBC();
			   Date date = new Date();
			   pstmt = conn.prepareStatement(sqlx);
			   rsx =pstmt.executeQuery();
			 if(rsx.next())
			   {
				//System.out.println("~~~~~~~~~~应经占座~~~~~~~~~~~~~~~~~"+dining_info.getSET_NO());
			   
			   	}
			 */
			    dining_info.setDINING_FLOOR(rs.getString("DINING_FLOOR"));
				dining_info.setBALCONY_CODE(rs.getString("BALCONY_CODE"));
				dining_info.setBELONG_TO(rs.getString("BELONG_TO"));
				dining_info.setBALCONY_NAME(rs.getString("BALCONY_NAME"));
				dining_info.setSET_STATUS(rs.getString("SET_STATUS"));
				dining_info.setSET_MAX(rs.getString("SET_MAX"));
				dining_info.setPRE_ORDER_STYLE(rs.getString("PRE_ORDER_STYLE"));
			//dininglist.add(dining_info);
		  }
			 
		 }
		 catch (SQLException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }finally{
		 }
		 request.setAttribute("dininglist",dining_info);
	
   RequestDispatcher a=	getServletContext().getRequestDispatcher("/lin/dating.jsp");
	a.forward(request, response);
	  
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
