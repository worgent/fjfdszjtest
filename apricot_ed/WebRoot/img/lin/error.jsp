<%@ page contentType="text/html; charset=GBK" language="java" import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>My JSP 'error.jsp' starting page</title>
  </head>
  
  <body>
  位置已经被占用了请刷新页面查看最新信息<br>
  </body>
</html>
