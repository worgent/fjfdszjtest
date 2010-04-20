<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html> 
	<head>
		<title>自助平台</title>
	</head>
	<frameset  ROWS="38,*" framespacing="0" frameborder="no" style="border:0px">
	<frame scrolling="no" noresize src="/frontlogin.do?action=adminbottom" marginwidth="0" marginheight="0" name="bar">		
	<frameset cols="96,*" framespacing="0" frameborder="0" style="border:0px">
		<frame scrolling="no" src="/adminLeft.jsp" name="menu" frameborder="no" noresize="noresize" />
		<frame scrolling="no" src="/adminIndex.jsp" name="main" frameborder="no"  noresize="noresize" />
	</frameset>
	<noframes>
	<body>
	</body>
	</noframes>
</html>








