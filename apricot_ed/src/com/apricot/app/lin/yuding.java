package com.apricot.app.lin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apricot.app.bean.nowtime;

public class yuding extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public yuding() {
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
		request.setCharacterEncoding("gb2312");
		nowtime time=new nowtime();
		
		String ORDER_NO="F"+time.timenumber();
		String prename=request.getParameter("prename");
		String telephone=request.getParameter("telephone");
		  String prenumber=request.getParameter("prenumber");
		  String VIPcard=request.getParameter("VIPcard");
		  String Operation=request.getParameter("Operation");  
		  String type=request.getParameter("type");
		  String Operationtime=request.getParameter("Operationtime"); 
		  String year=request.getParameter("Arrivedtime1");
		  String shi=request.getParameter("Arrivedtime3");
		  String fen=request.getParameter("Arrivedtime4");
		  String Arrivedtimes=year+" "+shi+":"+fen+":00";
		  String SET_NO=request.getParameter("preweizhi");  
		  String MINCOST_TYPE=request.getParameter("MINCOST_TYPE");  
		  String hightmoney=request.getParameter("hightmoney");  
		  String Releasetime=request.getParameter("Releasetime");
		  String balcony_name=request.getParameter("balcony_name");
		  String SF_ID=request.getParameter("SF_ID");
		  
	    System.out.println("预订进来了~~~~~~~~~~~~~~~~~~");
		//String SET_NO=request.getParameter("SET_NO");
		System.out.println("包厢名字="+balcony_name);    
	   // String Arrivedtime=request.getParameter("Arrivedtime");
	    
	    System.out.println("预订人名字="+prename+"电话="+telephone+"预订人人数"+prenumber+"VIP卡号"+VIPcard+"操作人员"+Operation+"订单类型"+type+"操作时间"+Operationtime+"抵达时间"+Arrivedtimes+"SET_NO"+SET_NO+"位置的名字"+balcony_name+"最低消费类型"+MINCOST_TYPE+"最低消费金额"+hightmoney+"到达预抵时间后自动释放"+Releasetime);
	       int sum=0;
	     Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ResultSet rsy = null;
		 try{ 
	String sqly="select * from order_info t2 where (t2.order_status='2' and SET_NO='"+SET_NO+"' and t2.ORDER_TYPE in(1,2,3) and DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('"+Arrivedtimes+"' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+Arrivedtimes+"' ,'%Y-%m-%d %T'))";
	 conn=MYSQLDB.getDBC();		
	 pstmt = conn.prepareStatement(sqly);
	 rsy =pstmt.executeQuery();  
		if(rsy.next())
		{
			 RequestDispatcher a=	getServletContext().getRequestDispatcher("/lin/error.jsp");
				a.forward(request, response);
		} 
		 
	else{ 
		 
		 String sqlx="select MAX(ORDER_ID) as sum from order_info";
		 conn=MYSQLDB.getDBC();
		
			 pstmt = conn.prepareStatement(sqlx);
			 rs =pstmt.executeQuery();
			 while(rs.next())
		 		{
			      sum=Integer.parseInt(rs.getString("sum"));
		 		}
			 sum++;

	     // String sql =  "insert into order_info(SET_NO,ORDER_NO,ORDER_ID,RECORD_STAFF_ID,SERVICE_STAFF_ID,MAN_COUNT,ORDER_TIME,can_order_time,ORDER_TYPE,PREARRANGE_NAME,PREARRANGE_PHONE,PREARRANGE_MAN_COUNT,VIP_CARD_NO,SF_ID,SEND_TIME,balcony_name,PREARRANGE_ORDER_TIME,MINCOST_TYPE,MINCOST_MONEY,overdue_time)values('"+preweizhi+"','F200907151515743','"+sum+"','"+Operation+"','0','1',now(),now(),'"+type+"','"+prename+"','"+telephone+"','"+prenumber+"','"+VIPcard+"','"+SF_ID+"',now(),'"+preweizhi+"','"+Arrivedtime+"','"+MINCOST_TYPE+"','"+hightmoney+"','10')";
			 String sql=   "insert into order_info(SET_NO,ORDER_NO,ORDER_ID,RECORD_STAFF_ID,SERVICE_STAFF_ID,ORDER_STATUS,ORDER_TYPE,PREARRANGE_NAME,PREARRANGE_PHONE,PREARRANGE_MAN_COUNT,VIP_CARD_NO,SF_ID,SEND_TIME,balcony_name,PREARRANGE_ORDER_TIME,OPERATE_ORDER_TIME,MINCOST_TYPE,MINCOST_MONEY)values('"+SET_NO+"','"+ORDER_NO+"','"+sum+"','"+Operation+"','0','2','"+type+"','"+prename+"','"+telephone+"','"+prenumber+"','"+VIPcard+"','"+SF_ID+"',now(),'"+balcony_name+"','"+Arrivedtimes+"',now(),'"+MINCOST_TYPE+"','"+hightmoney+"')";
		  // String sql1 = "insert into order_info(SET_NO,ORDER_NO,ORDER_ID,RECORD_STAFF_ID,SERVICE_STAFF_ID,MAN_COUNT,ORDER_TIME,can_order_time,ORDER_TYPE,PREARRANGE_NAME,PREARRANGE_PHONE,VIP_CARD_NO,SF_ID,balcony_name,MINCOST_TYPE,MINCOST_MONEY)values('"+SET_NO+"','F200907141634125','"+sum+"','"+waiter+"','0','"+guests+"',now(),now(),'0','"+name+"','"+telephone+"','"+vip+"','"+SF_ID+"','"+balcony_name+"','"+MINCOST_TYPE+"','"+hightmoney+"')";
	      conn=MYSQLDB.getDBC();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.executeUpdate(sql);
			 System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			 //RequestDispatcher a=	getServletContext().getRequestDispatcher("/lin/succes.jsp");
				//a.forward(request, response);
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
