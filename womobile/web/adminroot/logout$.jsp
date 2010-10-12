<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
session.removeAttribute("m_id");
session.removeAttribute("m_account");

response.sendRedirect("index.jsp");
%>
