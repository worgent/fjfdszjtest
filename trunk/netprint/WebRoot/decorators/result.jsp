<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
    <head>
		<%@ include file="/common/meta.jsp"%>
        <title><decorator:title/></title>
        <decorator:head/>
    </head>
<body>
    <decorator:body/>
	<c:import url="/common/pagination.jsp?border=true&export=true"/>
</body>
</html>