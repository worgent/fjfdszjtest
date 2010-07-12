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

public class yuding_datetime extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public yuding_datetime() {
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

		//response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 response.setContentType("text/XML;charset=GB2312");
		 System.out.println("jin lai le  $$$$$$$$$$$$$$$$$$$$$$$");
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 List list=new ArrayList();
		 String ATTR_ID=request.getParameter("datetime");
		 try{
		 String sql="select * from sys_attribute_value where ATTR_ID='"+ATTR_ID+"'";
		 conn=MYSQLDB.getDBC();
		 pstmt = conn.prepareStatement(sql);
		 rs =pstmt.executeQuery();
		 while(rs.next())
		 {
			 list.add(rs.getString("ATTR_VALUE"));
		 }
		 System.out.println("listµÄ´óÐ¡"+list.size());
		 out.print("<?xml version='1.0' encoding='GB2312'?>");
		 out.println("<times>");
		 for(int i=0;i<list.size();i++)
		 {
			 out.println("<time>"+list.get(i)+"</time>");
	         System.out.println("<time>"+list.get(i)+"</time>");
		 }
		 out.println("</times>");	
		 }
		 catch(Exception e)
		 {
			    System.out.println(e.toString());
				System.out.println(e.getMessage());
				e.printStackTrace();
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

		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
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
