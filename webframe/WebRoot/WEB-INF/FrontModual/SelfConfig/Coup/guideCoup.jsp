<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>向导锦囊</title>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
<script type="text/javascript" src="js/guideCoup.js"></script>
<script type="text/javascript">
	function loadDefaultList(path){
		loadCoupList(path);
	}
</script>
</head>

<body >

<div id="coupListDiv"></div><!-- 锦囊列表 -->
<div id="addEditCoupDiv"></div><!-- 锦囊添加修改 -->
<div id="messageDiv"></div><!-- 信息 -->
</body>
</html>