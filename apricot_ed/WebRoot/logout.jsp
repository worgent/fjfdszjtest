<%@ page language="java"  pageEncoding="GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
  <%
						session.removeAttribute(com.apricot.app.eating.LoginData.LOGIN_SESSION_KEY);
						session.invalidate();
						%>
  
   <script>
	document.location.href = "<%=request.getContextPath()%>/default.jsp";
	</script>
  </body>
</html>
