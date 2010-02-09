<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>¸¶¿î</title>
	
</head>

<body>
	<center>
		<jsp:include page="top.jsp" />
	    <form action="/pay.do">
	       <input type="submit" value="Ìá½»"/>
	    </form>
		<jsp:include flush="true" page="bottom.jsp" />
	</center>
</body>
</html:html>

