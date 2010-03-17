<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>EMS菜单���˵�</title>
		<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
		<script type="text/javascript" src="js/wtree.js"></script>
	</head>
	<body>
		<table height="100%">
			<tr valign="top" height="100%">
				<td nowrap="nowrap">
					<div class="dtree" id="dtree2">
						<s:property value="menuTree" escape="false" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部收缩</a></p>
				</td>
			</tr>
			<tr>
				
				<td><p>&nbsp;</p>
				<s:url action="adminLogout" id="adminLogoutUrl"></s:url>
				<a href="${adminLogoutUrl}" target="_top">退出管理</a></td>
			</tr>
		</table>
		
	</body>
</html>
