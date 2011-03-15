<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据字典管理</title>
</head>
<!-- 左边数据字典父级 -->
<s:url action="dictionary?action=getDictionary" id="adminLeftUrl"></s:url>
<!-- 右边各数据字典子级 -->
<s:url id="adminIndexUrl" value="dictionary.do?action=list"></s:url>

<frameset rows="*" cols="250,*" framespacing="0" frameborder="no" border="0">
  <frame src="${adminLeftUrl}" name="user_admin_leftFrame" frameborder="1" scrolling="yes" noresize="noresize">
  <frame src="${adminIndexUrl}" name="user_admin_mainFrame" frameborder="0" scrolling="yes" noresize="noresize">
</frameset>
<noframes><body>
</body></noframes>
</html>