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

public class updatedingdan extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public updatedingdan() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//response.setContentType("text/html");
		String SET_NO=request.getParameter("SET_NO");
		String Operation=request.getParameter("Operation");
		String prename=request.getParameter("prename");
	 	//System.out.println("wo kao ni si ge"+prename.length());
		String telephone=request.getParameter("telephone");
		String prenumber=request.getParameter("prenumber");
		String VIPcard=request.getParameter("VIPcard");
		String ORDER_TYPE=request.getParameter("ORDER_TYPE");
		String Operationtime=request.getParameter("Operationtime");
		String Arrivedtime=request.getParameter("Arrivedtime");
		//String xiadantime=request.getParameter("xiadantime");
		String preweizhi=request.getParameter("preweizhi");
		String MINCOST_TYPE=request.getParameter("MINCOST_TYPE");
		String MINCOST_MONEY=request.getParameter("MINCOST_MONEY");
		String MAN_COUNT=request.getParameter("MAN_COUNT");
		String ORDER_NO=request.getParameter("ORDER_NO");
		
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 //String sql="update order_info set"
		 String sql="update order_info set SET_NO='"+SET_NO+"',RECORD_STAFF_ID='"+Operation+"',MAN_COUNT='"+MAN_COUNT+"',PREARRANGE_NAME='"+prename+"',PREARRANGE_PHONE='"+telephone+"',PREARRANGE_MAN_COUNT='"+prenumber+"',VIP_CARD_NO='"+VIPcard+"',balcony_name='"+preweizhi+"',PREARRANGE_ORDER_TIME='"+Arrivedtime+"',OPERATE_ORDER_TIME='"+Operationtime+"',MINCOST_TYPE='"+MINCOST_TYPE+"',MINCOST_MONEY='"+MINCOST_MONEY+"',UPDATE_ORDER_TIME=now()where (ORDER_NO='"+ORDER_NO+"')";
	  //String sql1="update order_info set SET_NO='"+SET_NO+"',RECORD_STAFF_ID='"+Operation+"',MAN_COUNT='"+MAN_COUNT+"',ORDER_TIME='"+xiadantime+"',PREARRANGE_NAME='"+prename+"',PREARRANGE_PHONE='"+telephone+"',PREARRANGE_MAN_COUNT='"+prenumber+"',VIP_CARD_NO='"+VIPcard+"',balcony_name='"+preweizhi+"',PREARRANGE_ORDER_TIME='"+Arrivedtime+"',OPERATE_ORDER_TIME='"+Operationtime+"',MINCOST_TYPE='"+MINCOST_TYPE+"',MINCOST_MONEY='"+MINCOST_MONEY+"'where (ORDER_NO='"+ORDER_NO+"')";
		 System.out.println(sql);
		 conn=MYSQLDB.getDBC();
		 try{
			 pstmt = conn.prepareStatement(sql);
			 pstmt.executeUpdate(sql);
		 }
		 catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }finally{
			 }
		
	}

	/**
	 * 
	 * 	 try{
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 String sqlx="select * from dining_set_info t1 where t1.set_status='1' and t1.set_no not in (select set_no from order_info t2 where (t2.ORDER_NO<>'"+ORDER_NO+"' and t2.order_status='2'  and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('"+Arrivedtime+"' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+Arrivedtime+"' ,'%Y-%m-%d %T')) or DATE_ADD(t2.ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+Arrivedtime+"' ,'%Y-%m-%d %T') )and t1.SET_NO='"+SET_NO+"'order by t1.set_max desc";
		 conn=MYSQLDB.getDBC();
		 pstmt = conn.prepareStatement(sqlx);
		 rs =pstmt.executeQuery();
		 if(rs.next())
		 {
			 String sql="update order_info set SET_NO='"+SET_NO+"',RECORD_STAFF_ID='"+Operation+"',MAN_COUNT='"+MAN_COUNT+"',PREARRANGE_NAME='"+prename+"',PREARRANGE_PHONE='"+telephone+"',PREARRANGE_MAN_COUNT='"+prenumber+"',VIP_CARD_NO='"+VIPcard+"',balcony_name='"+preweizhi+"',PREARRANGE_ORDER_TIME='"+Arrivedtime+"',OPERATE_ORDER_TIME='"+Operationtime+"',MINCOST_TYPE='"+MINCOST_TYPE+"',MINCOST_MONEY='"+MINCOST_MONEY+"',UPDATE_ORDER_TIME=now()where (ORDER_NO='"+ORDER_NO+"')";
			  //String sql1="update order_info set SET_NO='"+SET_NO+"',RECORD_STAFF_ID='"+Operation+"',MAN_COUNT='"+MAN_COUNT+"',ORDER_TIME='"+xiadantime+"',PREARRANGE_NAME='"+prename+"',PREARRANGE_PHONE='"+telephone+"',PREARRANGE_MAN_COUNT='"+prenumber+"',VIP_CARD_NO='"+VIPcard+"',balcony_name='"+preweizhi+"',PREARRANGE_ORDER_TIME='"+Arrivedtime+"',OPERATE_ORDER_TIME='"+Operationtime+"',MINCOST_TYPE='"+MINCOST_TYPE+"',MINCOST_MONEY='"+MINCOST_MONEY+"'where (ORDER_NO='"+ORDER_NO+"')";
				 System.out.println(sql);
				 conn=MYSQLDB.getDBC();
				
					 pstmt = conn.prepareStatement(sql);
					 pstmt.executeUpdate(sql);
		 }
		 else
		 {
			 request.getRequestDispatcher("/lin/error.jsp").forward(request,response);
		 }
		
		 }
		 catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }finally{
			 }
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
