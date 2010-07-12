package com.apricot.app.pda;



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

public class pre_xiadan_select1 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public pre_xiadan_select1() {
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

		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
	System.out.println("进来了~~~~~~~~~~~~~~~预订下单查询");
	
	String Operation=request.getParameter("Operation");
	String prename=request.getParameter("prename");
	String telephone=request.getParameter("telephone");
	String prenumber=request.getParameter("prenumber");
	String ORDER_TYPE=request.getParameter("ORDER_TYPE");
	String Operationtime=request.getParameter("Operationtime");
	String Arrivedtime=request.getParameter("Arrivedtime");
	String preweizhi=request.getParameter("preweizhi");
	String MINCOST_TYPE=request.getParameter("MINCOST_TYPE");
	String MINCOST_MONEY=request.getParameter("MINCOST_MONEY");
	String SET_NO=request.getParameter("SET_NO");
	String SF_ID=request.getParameter("SF_ID");
	String ORDER_NO=request.getParameter("ORDER_NO");
	String MAN_COUNT=request.getParameter("MAN_COUNT");
	String balcony_name=request.getParameter("balcony_name");

	System.out.println("preweizhi"+preweizhi+"MINCOST_TYPE"+MINCOST_TYPE+"MINCOST_MONEY"+MINCOST_MONEY+"SET_NO"+SET_NO+"SF_ID"+SF_ID+"ORDER_NO"+ORDER_NO+"MAN_COUNT"+MAN_COUNT+"balcony_name"+balcony_name);
	//System.out.println("Operation"+Operation+"prename"+prename+"telephone"+telephone+"prenumber"+"VIPcard"+VIPcard+"ORDER_TYPE"+ORDER_TYPE+"Operationtime"+Operationtime+"Arrivedtime"+Arrivedtime);
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 if(MAN_COUNT==""||prename==""||MINCOST_MONEY==""||SET_NO==""||Operation==""||ORDER_NO==""||telephone==""||prenumber==""||ORDER_TYPE==""||Operationtime==""||Arrivedtime=="")
	 {  
		    request.setAttribute("Operationtime", Operationtime);
			request.setAttribute("prenumber",prenumber);
			request.setAttribute("telephone",telephone);
			request.setAttribute("prename",prename);
			request.setAttribute("ORDER_TYPE", ORDER_TYPE);
			request.setAttribute("Operation",Operation);
			request.setAttribute("Arrivedtime", Arrivedtime);
			 request.setAttribute("preweizhi", preweizhi);
			
		    request.setAttribute("MINCOST_TYPE", MINCOST_TYPE);
		    request.setAttribute("MINCOST_MONEY",MINCOST_MONEY);
		    
		    
		    request.setAttribute("SET_NO",SET_NO);
		    request.setAttribute("SF_ID", SF_ID);
			request.setAttribute("ORDER_NO",ORDER_NO);
	    
		    request.setAttribute("balcony_name", balcony_name);
		    request.setAttribute("MAN_COUNT", MAN_COUNT);	 
		    
	    	
	    	
	    
	    	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	    	request.getRequestDispatcher("/app/phone/kaidan.jsp").forward(request, response);
	 }
	else{
	 String sql="update order_info set ORDER_TIME=now(),RECORD_STAFF_ID='"+Operation+"',ORDER_STATUS='0'where (ORDER_NO='"+ORDER_NO+"')";
	 conn=MYSQL.getDBC();
	 try{
		 pstmt = conn.prepareStatement(sql);
		 pstmt.executeUpdate(sql);
	 }
	 catch (SQLException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }finally{
		 }
		  request.getRequestDispatcher("/servlet/chexunjieguo").forward(request, response);
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
