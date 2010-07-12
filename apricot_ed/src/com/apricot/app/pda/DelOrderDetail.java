package com.apricot.app.pda;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DelOrderDetail extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String denglu_name=null;
		String denglu_num=null;
		denglu_name=request.getParameter("denglu_name");
		denglu_num=request.getParameter("denglu_num");
		String orderId = request.getParameter("OrderId");
		String orderListId = request.getParameter("OrderListId");
		System.out.println("orderId="+orderId);
		if(denglu_name==null&&denglu_num==null)
		{
			String aa=(String)session.getAttribute("denglu_name"); denglu_name=aa;System.out.println("ppppppppppppp="+denglu_name);
			String bb=(String)session.getAttribute("denglu_num"); denglu_num=bb;System.out.println("ppppppppppppp="+denglu_num);
		}
		else
		{
			session.setAttribute("denglu_name",denglu_name);
			session.setAttribute("denglu_num",denglu_num);
		}
		String sql = "delete from order_list where order_list_id="+orderListId;
		try
		{
			Connection  conn = null;
	 		conn=MYSQL.getDBC();  
	 		Statement stmt=conn.createStatement();
	 		stmt.executeUpdate(sql);
	 		stmt.close();
	 		conn.close();
		}catch(Exception e)
		{
			e.printStackTrace(); 
		}
		
		request.getRequestDispatcher("diancai00").forward(request, response); 
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		this.doGet(request, response);
	}

}
