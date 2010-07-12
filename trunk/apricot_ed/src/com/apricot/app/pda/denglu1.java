package com.apricot.app.pda;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.apricot.app.pda.MYSQL;
import com.apricot.eating.util.DataUtil;
import com.mchange.util.PasswordUtils;

import java.sql.*;

public class denglu1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	response.setContentType("text/html;chareset=GB2312");
	//PrintWriter out = response.getWriter();//在输出时会乱码
	
	
	request.setCharacterEncoding("GB2312");
	String name=request.getParameter("name");
	String password=request.getParameter("password");
	System.out.print("Pageaaa="+name);
	System.out.print("Pageaaa="+password);
    
    try{
		Connection  conn = null;
			conn=MYSQL.getDBC();    
        String sql="select * from staff_info";
        Statement stmt=conn.createStatement();   
        ResultSet rs=stmt.executeQuery(sql);
        boolean contrl=true;
        while(rs.next()){
        	String a=PasswordUtils.deCodeValue(rs.getString("accounts_password"));
        	System.out.println("ni hap="+a+"dddd");
        	 if(name.equals(rs.getString("STAFF_CODE"))&&password.equals(a))
        	 {
        	    request.setAttribute("name",name);
        	    request.getRequestDispatcher("/app/phone/shouye.jsp").forward(request, response);	
        	    contrl=false;
        	    break;
        	 }
        }  
        //其他情况2010-07-12 fjfdszj
        if(contrl){
        	request.getRequestDispatcher("/app/phone/login.jsp").forward(request, response);
        }
     
        rs.close();   
        stmt.close();   
        conn.close();   
     }catch(Exception e){   
        e.printStackTrace();   
     }   
    
	
	
	
	
	
	
	
	/*

	request.setCharacterEncoding("GB2312");
	String name=request.getParameter("name");
	String password=request.getParameter("password");
	System.out.print("Pageaaa="+name);
	System.out.print("Pageaaa="+password);
    
    if(name.equals("admin")&&password.equals("123"))
    {
    request.setAttribute("name",name);
    request.getRequestDispatcher("/app/phone/shouye.jsp").forward(request, response);	
    }
    else
    {
    	request.getRequestDispatcher("/app/phone/login.jsp").forward(request, response);
    }
  
    */
    
    
    

    
    
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	this.doGet(request, response);
	}
	}
