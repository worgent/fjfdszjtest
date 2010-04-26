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
		<title>向导锦囊搜索</title>
		<link href="css/css1.css" rel="stylesheet" type="text/css" />
	</head>
	<body >
		
		<s:form action="guideCoup">
		<s:hidden name="action" value="query"></s:hidden>
		<table width="80%" border="0" align="center" cellpadding="1"
			cellspacing="1">
			<tr>
				<td width="10%" height="19" align="right">排序方式:</td>
				<td width="10%" align="left">
					<s:select list="sortList" name="search.sortType" id="sortType" cssClass="select1" listKey="key" listValue="value"></s:select>
				</td>
				<td align="left">
					<s:submit value="查询" />
				</td>
			</tr>
		</table>
		</s:form>
		
		<table width="80%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
			<tr>
				<td class="bgColor3" align="left">
					向导列表
				</td>
			</tr>
		</table>
		<table width="80%" id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="80%">
			<thead>
			<tr>
				<th>主题</th><th>发布者</th><th>发布时间</th><th>浏览数</th>
			</tr>
			</thead>
			
			<s:iterator id="guideCoup" value="%{pageList.objectList}">
			<tr>
				<td><a href="#"><s:property value="#guideCoup.COUPTITLE" /> </a></td>
				<td><s:property value="#guideCoup.USERNAME" /></td>
				<td><s:property value="#guideCoup.COUPCREATEDATE" /></td>
				<td><s:property value="#guideCoup.COUPCOUNT" /></td>
			</s:iterator>
			
			<s:if test="%{pageList.pages!=null}" >
			<tr class="bgColor3">
				<td colspan="7">
				分页:                                                     
				<qzgf:pages value="%{pageList.pages}" />
				</td>
			</tr>
			</s:if>
		</table>
	</body>
</html>