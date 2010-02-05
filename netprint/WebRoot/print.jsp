<%--
/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
--%>

<%@ page errorPage="error.jsp" %>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.applet.Applet" %>

<%
try{

URL url; 

url = new URL("http://localhost:8080/netprint/emsmail/emsmail.shtml?actionType=print&search.pid=2009091800000027");//从获得html参数中获得报表URL 
Object obj = JRLoader.loadObject(url);//发送对象请求，获得JasperPrint对象 
System.out.println(obj); 
JasperPrintManager.printReport((JasperPrint)obj, true);//调用方法打印所获得的JasperPrint对象 
/*
JasperPrintManager.printReport("G:\\Java\\netprint\\WebRoot\\WEB-INF\\pages\\application\\netprint\\emsmail\\emsmail.jasper",true);
*/
}catch(Exception e){
e.printStackTrace();
}
%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
  <style type="text/css">
    a {text-decoration: none}
  </style>
</head>
<body text="#000000" link="#000000" alink="#000000" vlink="#000000">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td width="50%">&nbsp;</td>
  <td align="left">
    <hr size="1" color="#000000">
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td><a href="viewer.jsp?reload=true"><img src="../images/reload.GIF" border="0"></a></td>
        <td>&nbsp;&nbsp;&nbsp;</td>
        <td width="100%">&nbsp;</td>
      </tr>
    </table>
    <hr size="1" color="#000000">
  </td>
  <td width="50%">&nbsp;</td>
</tr>
<tr>
  <td width="50%">&nbsp;</td>
  <td align="center">

  </td>
  <td width="50%">&nbsp;</td>
</tr>
</table>
</body>
</html>
