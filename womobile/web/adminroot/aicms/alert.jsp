<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息提示</title>
</head>
<body>
<%
String url=request.getParameter("url");
String msg=new String(request.getParameter("msg").getBytes("iso8859-1"),"gbk");
%>
<script language="javascript">
alert("<%=msg%>");
window.location.href="<%=url%>";
</script>
如果页面一直停留在这里，你可以手动<a href="<%=url%>" target="_self">转向下一页</a>
</body>
</html>