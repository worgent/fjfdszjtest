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
<title>用户列表</title>
<script type="text/javascript" src="<%=basePath%>js/comm.js"></script>
<script type="text/javascript" src="<%=basePath%>js/usermark.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js" defer="defer"></script>
<script type="text/javascript" src="js/wtree.js"></script>
<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 	window.onload=loadUserMarkListPage("<%=path%>","<s:property value='branchId'/>");
 </script>
</head>

<body>
<div id="userMarkListDiv"></div><!-- 用户列表 -->
<div id="addEditUserDiv"></div><!-- 用户添加修改 -->
<div id="messageDiv"></div><!-- 信息 -->
</body>
</html>