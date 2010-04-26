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
	</head>
	<body>
		<table width="780" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
		</table>
		<table id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="750">
			<thead>
				<tr>
					<th>
						分类名称
					</th>
					<th>
						日志数
					</th>
					<th align="center">
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="twitterType" value="%{pageList}">
					<tr>
						<td width="30%" class="bgColor3">
							<span class="font1"><a href="#"><s:property value="#twitterType.TWITTERTYPENAME" /></a></span>
						</td>
						<td>
							<span class="font1">&nbsp;<s:property value="#twitterType.TWITTERTYPENUM" /></span>
						</td>
						<td align="center">
							<s:if test="#twitterType.TWITTERTYPENAME!='默认分类'">
							<s:url action="twitterType?action=del" id="deleteType" namespace="/">
								<s:param name="twitterTypeId" value="#twitterType.TWITTERTYPEID"/>
								<s:param name="method" value="POST"/>
							</s:url>
							 <a href="${deleteType}">删除</a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="3" valign="middle">
					
					    <form action="<%=path%>/twitterType.do" method="post">
					    <s:hidden name="action" value="add"></s:hidden>
						添加新的分类:<input type="text" name="twitterTypeName" />
						<input type="submit" value="确定" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>