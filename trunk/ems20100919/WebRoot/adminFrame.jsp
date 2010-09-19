<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<html>
	<head>
		<title>福建邮政速递网上营业厅</title>
	</head>
	<frameset cols="*,969,*" framespacing="0" frameborder="0" name="hmain">
		<frame  src="/black.jsp" name="menu"  scrolling="no"  noresize/>
		<frameset  ROWS="107,*,30" framespacing="0" frameborder="no" style="border:0px">
		<frame scrolling="no"  noresize src="/frontlogin.do?action=adminbottom" marginwidth="0" marginheight="0" name="bar" frameborder="no" style="border:0px"/>		
			<frameset cols="140,*" framespacing="0" name="framsetmain" id="framsetmain" frameborder="no" style="border:0px">
				<frame  src="/adminLeftbk2.jsp" name="menu" scrolling="no" marginwidth="5" marginheight="0" margin-left="0" noresize />
				<frame  src="/net/order.do" name="main"  scrolling="auto"  marginwidth="0" marginheight="0" noresize/>
			</frameset>
	    <frame  src="/copyRight.jsp"   id="bottomFrame" scrolling="no" noresize/>	
		</frameset>
		<frame  src="/black.jsp" name="menu"  scrolling="no"  noresize/>
	</frameset>
	<noframes>
	<body>
	</body>
	</noframes>
</html>








