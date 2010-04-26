<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>向导名人榜</title>
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<link href="css/mapFortune.css" rel="stylesheet" type="text/css" />
		<link href="css/css1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/mapFortune.js"></script>

	</head>
	<body onload="loadDefaultList('<%=path%>');">
		<div id="f_bg">
			<div id="f_tabs">
				<ul>
					<li id="tab1" class="f_tabClass1">
						<a href="javascript:;" onclick="loadDefaultList('<%=path%>');">白领向导</a>
					</li>
					<li id="tab2" class="f_tabClass2">
						<a href="javascript:;" onclick="loadFameList('<%=path%>');">名望向导</a>
					</li>
					<li id="tab3" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDallyList('<%=path%>');">赋闲向导</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="f_main">
			<div id="defaultlist"></div>
		</div>
	</body>
</html>