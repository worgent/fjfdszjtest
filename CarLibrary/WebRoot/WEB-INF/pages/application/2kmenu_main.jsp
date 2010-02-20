<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
 
<html>
	<head>
		<%@ include file="/common/mate.jsp"%>
		<link rel="stylesheet" type="text/css" href="/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="/ext/examples/tabs/TabCloseMenu.js"></script>
		<link rel="stylesheet" type="text/css" href="/ext/examples/tabs/tabs-example.css" />
		<link rel="stylesheet" type="text/css" href="/ext/examples/tabs/examples.css" />
		<link rel="stylesheet" type="text/css" href="/themes/default/menu/2k3Menu.css">
		<style type="text/css">
			.office{
				background-color: transparent;
				position: absolute;
				top: 0px;
				left: 0px;
				white-space: nowrap;
				filter: progid:DXImageTransform.Microsoft.Gradient(GradientType=1, StartColorStr='#9EBEF5', EndColorStr='#C3DAF9')
			}
		</style>
	</head>
	<body>	
		<div class="downMenu" id="MenuDiv" url="/system/loadMenu.shtml" root="Root"  menutStyle="h"></div>
		 <div id="tabs" style="margin:0px 0;"></div>
	</body>
</html>
