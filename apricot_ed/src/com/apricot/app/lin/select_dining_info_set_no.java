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

public class select_dining_info_set_no extends HttpServlet {

	public select_dining_info_set_no() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 response.setContentType("text/XML;charset=GB2312");
		 System.out.println("jin lai le  $$$$$$$$$$$$$$$$$$$$$$$");
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 List list=new ArrayList();
		 String SET_NO=request.getParameter("set_no");
		 try{
			 String sql="select * from dining_set_info where SET_NO='"+SET_NO+"'";
			 System.out.println(sql);
			 conn=MYSQLDB.getDBC();
			 pstmt = conn.prepareStatement(sql);
			 rs =pstmt.executeQuery();
			 while(rs.next())
			 {
				 list.add(rs.getString("BALCONY_NAME"));
				 list.add(rs.getString("MINCOST_TYPE"));
				 list.add(rs.getString("MINCOST_MONEY"));
			 }
			 System.out.println("listµÄ´óÐ¡"+list.size());
			 out.print("<?xml version='1.0' encoding='GB2312'?>");
			 out.println("<dinings>");
			 for(int i=0;i<list.size();i++)
			 {
				 out.println("<dining>"+list.get(i)+"</dining>");
		  System.out.println("<dining>"+list.get(i)+"</dining>");
			 }
			 out.println("</dinings>");	
		 }
			 catch(Exception e){
				    System.out.println(e.toString());
					System.out.println(e.getMessage());
					e.printStackTrace();
			 }
	}

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
