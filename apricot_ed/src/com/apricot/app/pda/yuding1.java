package com.apricot.app.pda;

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

public class yuding1 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public yuding1() {
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
		request.setCharacterEncoding("UTF-8");
		nowtime time=new nowtime();
		String ORDER_NO="F"+time.timenumber();
	    System.out.println("预订进来了~~~~~~~~~~~~~~~~~~");
		String SET_NO=request.getParameter("SET_NO");
		String SF_ID=request.getParameter("SF_ID");
		String balcony_name=request.getParameter("balcony_name");
	    String prename=request.getParameter("prename");
	    String telephone=request.getParameter("telephone");
	    String prenumber=request.getParameter("prenumber");
	    String Operation=request.getParameter("Operation");
	    String type=request.getParameter("type");
	    String Operationtime=request.getParameter("Operationtime");
	    String Arrivedtime=request.getParameter("Arrivedtime");
	    
	    String year=request.getParameter("year");
	    String month=request.getParameter("month");
	    String day=request.getParameter("day");
	    String shi=request.getParameter("shi");
	    String fen=request.getParameter("fen");
	    String Arrivedtime1=year+"-"+month+"-"+day+" "+shi+":"+fen+":00";
	    if(year==""||month==""||day==""||shi==""||fen=="")
	    {Arrivedtime1="";System.out.println("时间为空");}
	    String preweizhi=request.getParameter("preweizhi");
	    String MINCOST_TYPE=request.getParameter("MINCOST_TYPE");
	    String hightmoney=request.getParameter("hightmoney");
	    String Releasetime=request.getParameter("Releasetime");
	    if(SET_NO==""||SF_ID==""|| balcony_name==""||prename==""||telephone==""||prenumber==""||Operation==""||type==""||Operationtime==""||Arrivedtime1==""||MINCOST_TYPE==""||hightmoney==""||Releasetime=="")
	    {
	    	request.setAttribute("Releasetime",Releasetime);
	    	request.setAttribute("prenumber",prenumber);
	    	request.setAttribute("prename",prename);
	    	request.setAttribute("telephone",telephone);
	    	request.setAttribute("i","i");
	    	request.setAttribute("SET_NO",SET_NO);
	    	request.setAttribute("SF_ID",SF_ID);
	    	request.setAttribute("balcony_name",balcony_name);
	    	request.setAttribute("MINCOST_TYPE",MINCOST_TYPE);
	    	request.setAttribute("hightmoney",hightmoney);
	    	request.getRequestDispatcher("/app/phone/yuyue.jsp").forward(request, response);
	    }
	    else{
	    //System.out.println("预订人名字="+prename+"电话="+telephone+"预订人人数"+prenumber+"VIP卡号"+VIPcard+"操作人员"+Operation+"订单类型"+type+"操作时间"+Operationtime+"抵达时间"+Arrivedtime+"预订位置"+preweizhi+"最低消费类型"+MINCOST_TYPE+"最低消费金额"+hightmoney+"到达预抵时间后自动释放"+Releasetime);
	    int sum=0;
	     Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ResultSet rsy = null;
		 try{ 
			 String sqly="select * from order_info t2 where (t2.order_status='2' and SET_NO='"+SET_NO+"' and t2.ORDER_TYPE in(1,2,3) and DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL -3 HOUR)<DATE_FORMAT('"+Arrivedtime1+"' ,'%Y-%m-%d %T') and  DATE_ADD(t2.PREARRANGE_ORDER_TIME,INTERVAL 3 HOUR) > DATE_FORMAT('"+Arrivedtime1+"' ,'%Y-%m-%d %T'))";
			 System.out.println(sqly);
			 conn=MYSQL.getDBC();		
			 pstmt = conn.prepareStatement(sqly);
			 rsy =pstmt.executeQuery();  
			 if(rsy.next())
			 {
				    request.setAttribute("Releasetime",Releasetime);
			    	request.setAttribute("prenumber",prenumber);
			    	request.setAttribute("prename",prename);
			    	request.setAttribute("telephone",telephone);
			    	request.setAttribute("i","j");
			    	request.setAttribute("SET_NO",SET_NO);
			    	request.setAttribute("SF_ID",SF_ID);
			    	request.setAttribute("balcony_name",balcony_name);
			    	request.setAttribute("MINCOST_TYPE",MINCOST_TYPE);
			    	request.setAttribute("hightmoney",hightmoney);
			    	request.getRequestDispatcher("/app/phone/yuyue.jsp").forward(request, response);
				    System.out.println("************************************");
			 } 
		 
			 else{ 
		         String VIPcard="";
				 String sqlx="select MAX(ORDER_ID) as sum from order_info";
				 conn=MYSQL.getDBC();
		
				 pstmt = conn.prepareStatement(sqlx);
			 	rs =pstmt.executeQuery();
			 	while(rs.next())
		 		{
			      sum=Integer.parseInt(rs.getString("sum"));
		 		}
			 	sum++;
			 	
			 	
			 	String sql5="select vip_no from customer_info where cust_phone='"+telephone+"'";
			 	conn=MYSQL.getDBC();
			 	pstmt = conn.prepareStatement(sql5);
			 	rs=pstmt.executeQuery();
			 	if(rs.next())
			 	{
			 		VIPcard=rs.getString("vip_no");
			 	}
			 	String sql="insert into order_info(SET_NO,ORDER_NO,ORDER_ID,RECORD_STAFF_ID,SERVICE_STAFF_ID,ORDER_STATUS,ORDER_TYPE,PREARRANGE_NAME,PREARRANGE_PHONE,PREARRANGE_MAN_COUNT,VIP_CARD_NO,SF_ID,SEND_TIME,balcony_name,PREARRANGE_ORDER_TIME,OPERATE_ORDER_TIME,MINCOST_TYPE,MINCOST_MONEY)values('"+SET_NO+"','"+ORDER_NO+"','"+sum+"','"+Operation+"','0','2','"+type+"','"+prename+"','"+telephone+"','"+prenumber+"','"+VIPcard+"','"+SF_ID+"',now(),'"+balcony_name+"','"+Arrivedtime1+"',now(),'"+MINCOST_TYPE+"','"+hightmoney+"')";
			 	conn=MYSQL.getDBC();
			 	pstmt = conn.prepareStatement(sql);
			 	pstmt.executeUpdate(sql);
			 	System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			 	 request.getRequestDispatcher("/servlet/chexunjieguo").forward(request, response);
			 	}
		 	}
			 catch (SQLException e) {		
				 e.printStackTrace();
				 }finally{
				 }
				
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

