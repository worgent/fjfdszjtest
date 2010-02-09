<%@page language="java" contentType="text/html; charset=gb2312"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld" %>
<% 
//	com.doone.Def def = new com.doone.Def();
//	session.removeValue(def.LOGIN_SESSION_NAME);
	session.invalidate();
%>
<script language="javascript">
	parent.window.location = "/login.jsp";
</script>
