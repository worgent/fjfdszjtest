<%@ page language="java"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>ÎÒµÄ¹ºÎï³µ</title>
</head>
<body>
<form action="../../indexFirst.do?status=allReviewAdd"  method="post">
<center>
<jsp:include flush="true" page="top.jsp" />
<table width="90%">
<tr>
<td>²úÆ·Ãû³Æ</td><td>ÊÐ³¡¼Û<br /></td><td>»áÔ±¼Û<br/></td><td>ÊýÁ¿<br /></td><td>²Ù×÷</td>








</tr>
</table>
<jsp:include flush="true" page="bottom.jsp" />
</center>
</form>
</body>
</html>