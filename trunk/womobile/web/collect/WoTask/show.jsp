<%@page import="com.womobile.collect.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=WoTask.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<s:form action="/collect/WoTask/list.do" method="get" theme="simple">
		<input type="button" value="返回列表" onclick="window.location='${ctx}/collect/WoTask/list.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<s:hidden name="taskId" id="taskId" value="%{model.taskId}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_TOPIC%></td>	
				<td><s:property value="%{model.topic}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_GUIDELINE%></td>	
				<td><s:property value="%{model.guideline}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_DESCRIPTION%></td>	
				<td><s:property value="%{model.description}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FEEDBACKTYPE_ID%></td>	
				<td><s:property value="%{model.feedbacktypeId}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_EXAMINECYCLE_ID%></td>	
				<td><s:property value="%{model.examinecycleId}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_STATUS_ID%></td>	
				<td><s:property value="%{model.statusId}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD_ID%></td>	
				<td><s:property value="%{model.fieldId}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD1%></td>	
				<td><s:property value="%{model.field1}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD2%></td>	
				<td><s:property value="%{model.field2}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD3%></td>	
				<td><s:property value="%{model.field3}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD4%></td>	
				<td><s:property value="%{model.field4}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD5%></td>	
				<td><s:property value="%{model.field5}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD6%></td>	
				<td><s:property value="%{model.field6}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD7%></td>	
				<td><s:property value="%{model.field7}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD8%></td>	
				<td><s:property value="%{model.field8}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD9%></td>	
				<td><s:property value="%{model.field9}" /></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=WoTask.ALIAS_FIELD10%></td>	
				<td><s:property value="%{model.field10}" /></td>
			</tr>
		</table>
	</s:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>