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

public class updatedingdan1 extends HttpServlet {

	public updatedingdan1() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        System.out.println("^^^^^^^^^^^^^^^^^^^^^进来了~~~~~~~~~~~~~~~~~~^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		response.setContentType("text/html");
		String SET_NO=request.getParameter("SET_NO");
		String Operation=request.getParameter("Operation");
		String prename=request.getParameter("prename");
		String telephone=request.getParameter("telephone");
		System.out.println("teliphone="+telephone);
		String prenumber=request.getParameter("prenumber");
		String ORDER_TYPE=request.getParameter("ORDER_TYPE");
		String OPERATE_ORDER_TIME=request.getParameter("OPERATE_ORDER_TIME");
		String balcony_name=request.getParameter("balcony_name");
		String MINCOST_TYPE=request.getParameter("MINCOST_TYPE");
		String MINCOST_MONEY=request.getParameter("MINCOST_MONEY");
		String MAN_COUNT=request.getParameter("MAN_COUNT");
		String ORDER_NO=request.getParameter("ORDER_NO");		
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		String day=request.getParameter("day");
		String shi=request.getParameter("shi");
		String fen=request.getParameter("fen");
		String PREARRANGE_ORDER_TIME=year+"-"+month+"-"+day+" "+shi+":"+fen+":00"; 
		if(year==""||month==""||day==""||shi==""||fen=="")
	    {PREARRANGE_ORDER_TIME="";System.out.println("时间为空");}
		
		if(SET_NO==""||Operation==""||prename==""||telephone==""||prenumber==""||ORDER_TYPE==""||OPERATE_ORDER_TIME==""||balcony_name==""||MINCOST_TYPE==""||MINCOST_MONEY==""||MAN_COUNT==""||ORDER_NO==""||PREARRANGE_ORDER_TIME=="")
		{
			request.setAttribute("i","i");
		  request.setAttribute("SET_NO", SET_NO);
		  request.setAttribute("Operation", Operation);		  
		  request.setAttribute("prename",prename);
		  request.setAttribute("telephone",telephone);
		  System.out.println("teliphone="+telephone);
		  request.setAttribute("prenumber",prenumber);
		  request.setAttribute("ORDER_TYPE",ORDER_TYPE);
		  request.setAttribute("OPERATE_ORDER_TIME",OPERATE_ORDER_TIME);
		  request.setAttribute("balcony_name",balcony_name);
		  request.setAttribute("MINCOST_TYPE",MINCOST_TYPE);
		  request.setAttribute("MINCOST_MONEY",MINCOST_MONEY);
		  request.setAttribute("MAN_COUNT",MAN_COUNT);
		  request.setAttribute("ORDER_NO",ORDER_NO);
		  request.setAttribute("PREARRANGE_ORDER_TIME", PREARRANGE_ORDER_TIME);
		  System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		  request.getRequestDispatcher("/app/phone/xiugaidingdan.jsp").forward(request, response);
		 
		}
		else{	
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try{
			 String VIPcard="";
			 String sql5="select vip_no from customer_info where cust_phone='"+telephone+"'";
			 	conn=MYSQL.getDBC();
			 	pstmt = conn.prepareStatement(sql5);
			 	rs=pstmt.executeQuery();
			 	if(rs.next())
			 	{
			 		VIPcard=rs.getString("vip_no");
			 	}
			 
			 
			 String sqlx="select * from dining_set_info t1 where t1.set_status='1' and t1.set_no not in (select set_no from order_info t2 where (t2.ORDER_NO<>'"+ORDER_NO+"' and t2.order_status='2'  and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('"+PREARRANGE_ORDER_TIME+"' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+PREARRANGE_ORDER_TIME+"' ,'%Y-%m-%d %T')) or DATE_ADD(t2.ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+PREARRANGE_ORDER_TIME+"' ,'%Y-%m-%d %T') )and t1.SET_NO='"+SET_NO+"'order by t1.set_max desc";
			 conn=MYSQL.getDBC();
			 pstmt = conn.prepareStatement(sqlx);
			 rs =pstmt.executeQuery();
			 if(rs.next())
			 {
				 String sql="update order_info set SET_NO='"+SET_NO+"',RECORD_STAFF_ID='"+Operation+"',MAN_COUNT='"+MAN_COUNT+"',ORDER_TIME=now(),PREARRANGE_NAME='"+prename+"',PREARRANGE_PHONE='"+telephone+"',PREARRANGE_MAN_COUNT='"+prenumber+"',VIP_CARD_NO='"+VIPcard+"',balcony_name='"+balcony_name+"',PREARRANGE_ORDER_TIME='"+PREARRANGE_ORDER_TIME+"',MINCOST_TYPE='"+MINCOST_TYPE+"',MINCOST_MONEY='"+MINCOST_MONEY+"'where (ORDER_NO='"+ORDER_NO+"')";
				 System.out.println(sql);
				 conn=MYSQL.getDBC();
				 pstmt = conn.prepareStatement(sql);
				 pstmt.executeUpdate(sql);
				 request.getRequestDispatcher("/servlet/chexunjieguo").forward(request, response);
			 }
			 else{
				 request.setAttribute("i","j");
				 request.setAttribute("SET_NO", SET_NO);
				  request.setAttribute("Operation", Operation);		  
				  request.setAttribute("prename", prename);
				  request.setAttribute("telephone",telephone);
				  System.out.println("teliphone="+telephone);
				  request.setAttribute("prenumber",prenumber);
				  request.setAttribute("ORDER_TYPE",ORDER_TYPE);
				  request.setAttribute("OPERATE_ORDER_TIME",OPERATE_ORDER_TIME);
				  request.setAttribute("balcony_name",balcony_name);
				  request.setAttribute("MINCOST_TYPE",MINCOST_TYPE);
				  request.setAttribute("MINCOST_MONEY",MINCOST_MONEY);
				  request.setAttribute("MAN_COUNT",MAN_COUNT);
				  request.setAttribute("ORDER_NO",ORDER_NO);
				  request.setAttribute("PREARRANGE_ORDER_TIME", PREARRANGE_ORDER_TIME);
				  System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				  request.getRequestDispatcher("/app/phone/xiugaidingdan.jsp").forward(request, response);
		 		}
		 }
		 catch (SQLException e) {
	
			 e.printStackTrace();
			 }
			  
		}	  
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
