<%@page import="com.qzgf.application.workduty.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=Workduty.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/workduty/Workduty/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/workduty/Workduty/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="id" id="id" value="%{model.id}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=Workduty.ALIAS_TITLE%></td>	
				<td><s:property value="%{model.title}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Workduty.ALIAS_DETAIL%></td>	
				<td><s:property value="%{model.detail}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Workduty.ALIAS_ADJUNCT%></td>	
				<td><s:property value="%{model.adjunct}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Workduty.ALIAS_TARGET%></td>	
				<td><s:property value="%{model.target}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Workduty.ALIAS_FORMAT%></td>	
				<td><s:property value="%{model.format}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Workduty.ALIAS_PROID%></td>	
				<td><s:property value="%{model.proid}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Workduty.ALIAS_ATTITUDE%></td>	
				<td><s:property value="%{model.attitude}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Workduty.ALIAS_GRADE%></td>	
				<td><s:property value="%{model.grade}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=Workduty.ALIAS_ISEND%></td>	
				<td><s:property value="%{model.isend}" /></td>
			</tr>
		</table>
	</s:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>