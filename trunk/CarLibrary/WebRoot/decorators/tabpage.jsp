<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns:stedysoft="http://www.vcom.com">
    <head>
		<%@ include file="/common/meta.jsp"%>
		<?IMPORT namespace="stedysoft" implementation="/themes/default/tab/2k3Tab.htc"?>
		<?IMPORT namespace="stedysoft" implementation="/themes/default/tab/2k3TabPage.htc"?>
		<link rel="stylesheet" type="text/css" href="/themes/default/tab/2k3Tab.css">
        <title><decorator:title/></title>
        <decorator:head/>
		<script language="javascript">
			function elementOnContextMenu() {
				with (window.event) {
					cancelBubble = true ;
					returnValue = false ;
				}
				return false ;
			}
		</script>
    </head>
<body onbeforeunload="closeIt()" oncontextmenu="elementOnContextMenu()">
    <decorator:body/> 
</body>
</html>