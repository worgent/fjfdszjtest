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

public class chuicai extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	//response.setContentType("text/html;chareset=gb2312");
	//PrintWriter out = response.getWriter();//在输出时会乱码
	//request.setCharacterEncoding("gb2312");
	
	String nihao=request.getParameter("nihao");
	//nihao=new String(nihao.getBytes("UTF-8"),"ISO-8859-1");
	//nihao=new String(nihao.getBytes("ISO-8859-1"),"GBK");
	System.out.println("dsadsa="+nihao);
    request.setAttribute("xxx",nihao);
	request.getRequestDispatcher("/app/phone/xxx.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	this.doGet(request, response);
	}
	}
