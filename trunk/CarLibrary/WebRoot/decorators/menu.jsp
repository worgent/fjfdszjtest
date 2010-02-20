
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%@ include file="/common/taglibs.jsp"%>

<html> 
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script type="text/javascript" src="/themes/default/js/xtree.js"></script>
		<link type="text/css" rel="stylesheet" href="/themes/default/style/xtree.css" />
	</head>
	<body style="MARGIN: 0px;">
		<decorator:body/>
	</body>
</html>