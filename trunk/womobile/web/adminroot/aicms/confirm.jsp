<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息确认</title>
</head>
<body>
<%
String url=request.getParameter("url");
String msg=new String(request.getParameter("msg").getBytes("iso8859-1"),"utf-8");
%>
<script language="javascript">
if(confirm("<%=msg%>,你确定要继续吗?"))window.location.href="<%=url%>";
else window.history.back();
</script>
如果页面一直停留在这里，你可以手动<a href="javascript:window.history.back()">返回上一页</a>
</body>
</html>