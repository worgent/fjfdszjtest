<%@page import="com.womobile.sysmanage.login.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=WoUser.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/sys/WoUser/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/sys/WoUser/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="userId" id="userId" value="%{model.userId}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=WoUser.ALIAS_GROUP_ID%></td>	
				<td><s:property value="%{model.groupId}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoUser.ALIAS_USERNAME%></td>	
				<td><s:property value="%{model.username}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoUser.ALIAS_PASSWD%></td>	
				<td><s:property value="%{model.passwd}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoUser.ALIAS_REPASSWD%></td>	
				<td><s:property value="%{model.repasswd}" /></td>
			</tr>
		</table>
	</s:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>