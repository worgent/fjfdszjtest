<%@ page language="java" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>修改我的email</title>
		<link href="<%=request.getContextPath()%>/css/header01.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/index.css"
			type="text/css" rel="stylesheet" />
		<link href="<%=request.getContextPath()%>/css/catalog.css"
			type="text/css" rel="stylesheet" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		<script type="text/javascript">
	    function to_userAccount(){
	       window.location.href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition";
	    }
	</script>

	</head>

	<body>
		<center>
        <jsp:include page="top.jsp" />
			<table width="98%" align="center" cellspacing="1" cellpadding="2"
			bgcolor="#e1e1e1" border="0">
				<tr>
					<td bgcolor="#ffffff" align="left">
						修改我的email
					</td>
				</tr>
				<tr>
					<td valign="middle" align="center" bgcolor="#ffffff">
						<br />
						您的email地址更新成功
						<br />
						<br />
					</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#ffffff">
						<input type="button" value="完成" onclick="to_userAccount()" />
					</td>
				</tr>
			</table>
		<jsp:include flush="true" page="bottom.jsp" />
    </center>
	</body>
</html>
