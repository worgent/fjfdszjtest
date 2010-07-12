<%@ page language="java" import="java.sql.*,com.apricot.app.lin.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
MYSQLDB mysql = new MYSQLDB();
Connection conn = mysql.getDBC();
%>
