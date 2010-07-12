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

public class hotel extends HttpServlet {


	public hotel() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         System.out.println("fuck");
		response.setContentType("text/XML;charset=GB2312");
		PrintWriter out = response.getWriter();
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql="select distinct BELONG_TO   from dining_set_info";
		 List list=new ArrayList();
		try{
		 conn=MYSQLDB.getDBC();
		 pstmt = conn.prepareStatement(sql);
		 rs =pstmt.executeQuery();
		 while(rs.next())
		 {
			 list.add(rs.getString("BELONG_TO"));
		 }
		 out.print("<?xml version='1.0' encoding='GB2312'?>");
			//System.out.print("<?xml version='1.0' encoding='GB2312'?>");
		 out.println("<mens>");
		 for(int i=0;i<list.size();i++)
		 {
			out.println("<menu>"+list.get(i)+"</menu>");
		 }
		   out.println("</mens>");
		 //System.out.println("list µÄ´óÐ¡"+list.size());
		
		/* out.println("<root>");
		  out.println("<ff>");
		  out.println("</ff>");
		 out.println("</root>");
		 */
		}
		catch(Exception e){
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally{
			
		}
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

                       doGet(request,response);
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
