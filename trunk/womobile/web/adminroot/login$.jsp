<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
String userName=request.getParameter("username");
String passWord=request.getParameter("password");

session.setAttribute("m_id",1);
session.setAttribute("m_account","管理员");
session.setMaxInactiveInterval(1800);
response.sendRedirect("index.jsp");
%>
