<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table width="100%" border="0" cellpadding="4" cellspacing="0">
	<tr class="trClass">
		<td width="25%">
			<strong>向导名称2</strong>
		</td>
		<td width="25%">
			<strong>向导图标</strong>
		</td>
		<td width="20%">
			<strong>收入值</strong>
		</td>
		<td width="35%">
			<strong>操作</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
			<td>
				<s:property value="#g.USERNAME" />
			</td>
			<td>
				到时放向导图标
			</td>
			<td>
				<s:property value="#g.FINANCIAL" />
			</td>
			<td>
				<a href="javascript:;"
					onclick="hireGuide('<s:property value="#g.MEMBERID"/>');">雇请</a>
			</td>
		</tr>
	</s:iterator>
	<tr class="bgColor3">
		<td colspan="4">
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}" javaScript="listFameMore"/>
		</td>
	</tr>
</table>

