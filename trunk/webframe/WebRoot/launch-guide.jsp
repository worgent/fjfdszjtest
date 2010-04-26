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
		<link href="css/css1.css" rel="stylesheet" type="text/css" />
		<title>发起订阅</title>
	</head>
	<body>
	<center>
		<table width="780" border="0"  cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
		</table>
		<form action="orderGuide.do" method="post">
		<table width="780" border="1">
			<tr>
				<td>订阅方式：</td>
				<td align="left">
					<input type="radio" name="orderType" value="1" checked="checked">用户指定分类/商品/模糊字发起订阅<br/>
					<input type="radio" name="orderType" value="2">直接指定向导发起订阅
				</td>
			</tr>
			<tr >
				<td>根据向导名称输入值:</td>
				<td align="left"><input type="text" name="guidename" /></td>
			</tr>
			<tr >
				<td>指定商品分类/商品名称发起订阅:</td>
				<td align="left">
					<select id="classifyid" name="classify" style="width:150px">
						<option value="0">所有分类</option>
						<option value="1">分类一</option>
						<option value="2">分类二</option>
						<option value="3">分类三</option>
					</select>
					&nbsp;商品名称：<input type="text" name="productname" />
				</td>
			</tr>
			<tr>
				<td>地图框选</td>
				<td><div id="map">map area</div></td>
			</tr>
			<tr>
				<td>区域选择</td>
				<td><div id="area">区域选择</div></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="searchid" value="搜索"/>
				</td>
			</tr>
		</table>
		</form>
	</center>
	</body>
</html>