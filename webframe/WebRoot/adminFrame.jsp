<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
	<head>
		<title>猎图网后台</title>
	</head>
	<s:url value="/adminLeft.jsp" id="leftUrl"></s:url>
	<s:url id="mainUrl" value="/adminIndex.jsp"></s:url>
	<frameset rows="*" cols="198,*" framespacing="0" frameborder="no"
		border="1">
		<frame src="${leftUrl}" name="webframe_leftFrame" frameborder="no"
			scrolling="yes" noresize="noresize" />
		<frame src="${mainUrl}" name="webframe_mainFrame" frameborder="no"
			scrolling="yes" noresize="noresize" />
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>

</html>
