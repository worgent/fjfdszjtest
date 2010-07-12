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

import com.apricot.app.bean.dining_set_info;

public class yuding_select_kongweizhi extends HttpServlet {

	public yuding_select_kongweizhi() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 response.setContentType("text/XML;charset=GB2312");
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ResultSet rskong = null;
		 String sql1;
		 List list=new ArrayList();
		 List dininglist=new ArrayList();	
		 String pretime=request.getParameter("fulltime");
		 System.out.println("fulltime"+pretime);
		 try{
			 String sql="select * from dining_set_info";
			 conn=MYSQLDB.getDBC();
			 pstmt = conn.prepareStatement(sql);
			 rs =pstmt.executeQuery();
			 while(rs.next())
			 {
	   //String sqlkong="select * from dining_set_info t1 where t1.set_status='1' and t1.set_no not in (select set_no from order_info t2 where (t2.order_status='2'  and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('"+pretime+"' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+pretime+"' ,'%Y-%m-%d %T')) or t2.order_status='0')and t1.SET_NO='"+rs.getString("SET_NO")+"'order by t1.set_max desc";	
				 sql1="select * from dining_set_info t1 where t1.set_status='1' and t1.set_no not in (select set_no from order_info t2 where (t2.order_status='2'  and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('"+pretime+"' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+pretime+"' ,'%Y-%m-%d %T')) or DATE_ADD(t2.ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+pretime+"' ,'%Y-%m-%d %T') )and t1.SET_NO='"+rs.getString("SET_NO")+"'order by t1.set_max desc";
				 System.out.println(sql1);
				 conn=MYSQLDB.getDBC();
				 pstmt = conn.prepareStatement(sql1);
				 rskong =pstmt.executeQuery(); 
				 while(rskong.next())
				 {
					 dining_set_info dining_info=new dining_set_info();  
					 dining_info.setSF_ID(rskong.getString("SF_ID"));
					 dining_info.setSET_NO(rskong.getString("SET_NO"));		
					 dining_info.setDINING_FLOOR(rskong.getString("DINING_FLOOR"));
					 dining_info.setBALCONY_CODE(rskong.getString("BALCONY_CODE"));
					 dining_info.setBELONG_TO(rskong.getString("BELONG_TO"));
					 dining_info.setBALCONY_NAME(rskong.getString("BALCONY_NAME"));
					 dining_info.setSET_STATUS(rskong.getString("SET_STATUS"));
					 dining_info.setSET_MAX(rskong.getString("SET_MAX"));
					 dining_info.setPRE_ORDER_STYLE(rskong.getString("PRE_ORDER_STYLE"));
					 dining_info.setPAY_MONEY(rskong.getString("PAY_MONEY"));
					 dining_info.setPAY_STATUS(rskong.getString("PAY_STATUS"));
					 dining_info.setMINCOST_MONEY(rskong.getString("MINCOST_MONEY"));
					 dining_info.setMINCOST_TYPE(rskong.getString("MINCOST_TYPE"));
					 dininglist.add(dining_info);
			}
			 }
			 dining_set_info dining_in=new dining_set_info(); 
			 out.print("<?xml version='1.0' encoding='GB2312'?>");
			 out.println("<dinings>");
			 for(int i=0;i<dininglist.size();i++)
			 {
				dining_in=(dining_set_info)dininglist.get(i);
				out.println("<dining>");
				System.out.println("<dining>");
				out.println("<SET_NO>"+dining_in.getSET_NO()+"</SET_NO>");
				System.out.println("<SET_NO>"+dining_in.getSET_NO()+"</SET_NO>");
				String str=dining_in.getBALCONY_NAME();
				str=java.net.URLEncoder.encode(str, "utf-8");
				out.println("<BALCONY_NAME>"+str+"</BALCONY_NAME>");
				System.out.println("<BALCONY_NAME>"+dining_in.getBALCONY_NAME()+"</BALCONY_NAME>");
				out.println("<MINCOST_MONEY>"+dining_in.getMINCOST_TYPE()+"</MINCOST_MONEY>");
				System.out.println("<MINCOST_MONEY>"+dining_in.getMINCOST_TYPE()+"</MINCOST_MONEY>");
				out.println("<MINCOST_TYPE>"+dining_in.getMINCOST_TYPE()+"</MINCOST_TYPE>");
				System.out.println("<MINCOST_TYPE>"+dining_in.getMINCOST_TYPE()+"</MINCOST_TYPE>");
				out.println("</dining>");
				System.out.println("</dining>");
				//System.out.println("SET_NO"+dining_in.getSET_NO()+"职位名字"+dining_in.getBALCONY_NAME()+"消费金额"+dining_in.getMINCOST_MONEY()+"消费类型"+dining_in.getMINCOST_TYPE());
			 }
			 out.println("</dinings>");
			   
			    rskong.close();
			    rs.close();   
			    pstmt.close();   
		        conn.close(); 
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
