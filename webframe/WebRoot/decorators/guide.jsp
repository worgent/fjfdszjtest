<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
    <head>
		<%@ include file="/common/mate.jsp"%>
		<script type="text/javascript" src="js/mapFortune.js"></script>
		<link href="css/mapFortune.css" rel="stylesheet" type="text/css" />
        <decorator:head/>
    </head>
<body onload="loadDefaultList('<%=path%>');">
    <decorator:body/> 
</body>
</html>