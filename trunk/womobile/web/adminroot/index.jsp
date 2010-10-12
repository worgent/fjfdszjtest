<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="checklogin.jsp"%>
<jsp:useBean class="HibernateBeans.cms.cmsInfo" scope="page" id="cmsInfo"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AICMS<%=cmsInfo.getVersion()%>-管理中心</title>
</head>
<frameset rows="11%,*,3%" frameborder="no"  border="0" framespacing="0">
<frame src="top.jsp" name="top" frameborder="no"  />
<frameset cols="15%,*" frameborder="no"  border="0" framespacing="0">
   <frame name="left" src="left.jsp" frameborder="no" />
   <frame name="main" frameborder="no" scrolling="auto" src="main.jsp" />
</frameset>
<frame src="bottom.jsp" name="top" frameborder="no"  />
</frameset>
<noframes></noframes>
<body>
</body>
</html>
