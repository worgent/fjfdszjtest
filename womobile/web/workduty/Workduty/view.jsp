<%@page import="com.qzgf.application.workduty.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="${ctx}/workduty/Workduty/pic.do?deploymentId=${deploymentId}" style="position:absolute;left:0px;top:0px;">
<div style="position:absolute;border:1px solid red;left:${ac.x }px;top:${ac.y }px;width:${ac.width }px;height:${ac.height}px;"></div>
</body>
</html>