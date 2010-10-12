<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="<%=basePath%>">
</head>
<body>
  <fieldset>
    <legend>登陆</legend>
    <form action="login.do" method="post">
         用户名：<input type="text" name="userName" value=""/><br/>
   		   <input type="submit" value="登陆"/>
    </form>
  </fieldset>

</body>
</html>