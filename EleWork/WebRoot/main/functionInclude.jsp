<%@include file="common.jsp"%>
<%@ page contentType="text/html; charset=GB2312" %>
<html>
<head>
<title>
  <%
  boolean hasright=false;
  if (request.getParameter("tseqn")!=null);
  {

  }
  if (!hasright){
    response.sendRedirect("您没有本功能权限");
  }
  %>

</title>
</head>
<body bgcolor="#ffffff">
<h1>
</h1>
</body>
</html>
