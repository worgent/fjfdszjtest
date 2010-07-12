<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 <style type="text/css">
 body{background:#ccc;}
 .ding{background:url(phone_img/12.gif); height:25px;padding-top:4px;color:white;}
 .shou{background:#ccc;text-align:center;}
 .logo{margin-top:25%}
 .anniu{color:white;background:url(phone_img/12.gif);width:80px;height:35px;}
 </style>
  <body>
    <div class="shou">
        <div class="ding">民以食为天</div>
    	<div class="logo">
    	<img src=phone_img/lo1.png>
    	</div>
    	<br>
    	<form id="form" name="form" method="post" action="/apricot_ed/servlet/denglu1">
    	用户名<input type="text" name="name" size="18">
		<br><br>
		密&nbsp&nbsp&nbsp码<input name="password" type="password" size="18">
		<br><br>
		<input class="anniu" type="submit" value="确定">&nbsp&nbsp<input class="anniu" type="reset" value="重置">
		</form>
    </div>
  </body>
</html>
