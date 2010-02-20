<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%@ include file="/common/taglibs.jsp"%>

<html> 
	<head>
		<title>
			<ww:i18n name="'messages'">
				<ww:text name="'main.title'"/>
			</ww:i18n>
		</title>
		<decorator:head/>
	</head>
	<body>
		<decorator:body/>
	</body>
</html>
