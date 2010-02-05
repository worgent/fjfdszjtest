<%@page language="java" errorPage="/error.jsp" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312"%>
<%-- 加入标签 --%>
<%@include file="/common/taglibs.jsp"%>
<html>
<head>
	<script type="text/javascript" src="/themes/default/js/xtree.js"></script>
	<link type="text/css" rel="stylesheet"
		href="/themes/default/css/xtree.css" />
</head>

<body>
<div style="width:100%;height:100%;overflow:auto">
	<script type="text/javascript">
		var atree = new WebFXLoadTree("系统管理","/system/manage/menu.shtml?menuInfo.superId=Root&menuInfo.isMenu=1");
		document.write(atree);
	</script>
</div>
</body>
</html>