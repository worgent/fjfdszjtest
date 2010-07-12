package com.apricot.app.pda;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class xxx extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	response.setContentType("text/html;chareset=GB2312");
	//PrintWriter out = response.getWriter();//在输出时会乱码
	request.setCharacterEncoding("GB2312");
	String checkbox[]=request.getParameterValues("diancaicheckbox");
	/*
	System.out.println("dsasdaaaaaaaaaa="+checkbox.length);
	System.out.println("错误3");
	if(checkbox.length>0){
		System.out.println("错误4");
	for(int i=0;i<checkbox.length;i++)
	{
		
		System.out.println("dd="+checkbox[i]);
	}
	}*/
	 try{
		 System.out.println("dsasdaaaaaaaaaa="+checkbox.length);
		 for(int i=0;i<checkbox.length;i++)
			{
				
				System.out.println("dd="+checkbox[i]);
			}
		 
	 }
	 catch(Exception e){   
		 System.out.println("dsasdaaaaaaaaaa=0");  
	     }   
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	this.doGet(request, response);
	}
	}
