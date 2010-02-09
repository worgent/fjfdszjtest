<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>错误信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <center>
    <table width="40%">
        <tr>
            <td>
                修改配送信息
            </td>
        </tr>
        <tr>
            <td  valign="middle" align="center">
            <br/>
                信息修改成功
            <br/>
            <br/>
            </td>
        </tr>
        <tr>
            <td align="center">
               <input type="button" value="完成"/>
            </td>
        </tr>
    </table>
    </center>
  </body>
</html>
