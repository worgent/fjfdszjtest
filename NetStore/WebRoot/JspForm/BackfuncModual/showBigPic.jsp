<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>ÏÔÊ¾´óÍ¼Æ¬</title>
</head>
<body>
  <center>
  <table><tr><td>
   <%
  ServletContext   servletContext   =   pageContext.getServletContext();   
  String   realPath   =   servletContext.getRealPath("/");
  String name2=realPath+"/upload/bigPic"; 
        %>
  <input type="image" name="imagePic" src="<%=name2 %>\<%=request.getAttribute("picPath")%>"  height="200" width="200" />
  </td></tr></table>
  </center>
</body>
</html>