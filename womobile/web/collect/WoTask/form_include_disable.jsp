<%@page import="com.womobile.collect.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="taskId" name="taskId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
 <tr>
	<td align="right" width="15%"><b><s:property value="%{@com.womobile.collect.model.WoTask@ALIAS_TOPIC}"/>:</b></td><td><s:property value="%{model.topic}"/></td>
</tr>
<tr>
	<td align="right"><b><s:property value="%{@com.womobile.collect.model.WoTask@ALIAS_GUIDELINE}"/>:</b></td><td><s:property value="%{model.guideline}"/></td>
</tr>
<tr>
	<td align="right"><b><s:property value="%{@com.womobile.collect.model.WoTask@ALIAS_DESCRIPTION}"/>:</b></td><td><s:property value="%{model.description}"/></td>
</tr>
<tr>	
	<td align="right"><b><s:property value="%{@com.womobile.collect.model.WoTask@ALIAS_FEEDBACKTYPE_ID}"/>:</b></td><td><s:property value="%{model.feedbacktypeId}"/></td>
</tr>
<tr>
	<td align="right"><b><s:property value="%{@com.womobile.collect.model.WoTask@ALIAS_EXAMINECYCLE_ID}"/>:</b></td><td><s:property value="%{model.examinecycleId}"/></td>
</tr>
<tr>
	<td align="right"><b><s:property value="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD_ID}"/>:</b></td><td><s:property value="%{model.fieldId}"/></td>
</tr>	
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD1.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD1}" key="field1" value="%{model.field1}" cssClass="" required="false" />
	</s:if>
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD2.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD2}" key="field2" value="%{model.field2}" cssClass="" required="false" />
	</s:if>
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD3.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD3}" key="field3" value="%{model.field3}" cssClass="" required="false" />
	</s:if>
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD4.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD4}" key="field4" value="%{model.field4}" cssClass="" required="false" />
	</s:if>
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD5.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD5}" key="field5" value="%{model.field5}" cssClass="" required="false" />
	</s:if>
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD6.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD6}" key="field6" value="%{model.field6}" cssClass="" required="false" />
	</s:if>
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD7.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD7}" key="field7" value="%{model.field7}" cssClass="" required="false" />
	</s:if>
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD8.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD8}" key="field8" value="%{model.field8}" cssClass="" required="false" />
	</s:if>
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD9.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD9}" key="field9" value="%{model.field9}" cssClass="" required="false" />
	</s:if>
	
	<s:if test="%{!@com.womobile.collect.model.WoTask@ALIAS_FIELD10.startsWith('备用字段_')}">
	<s:textfield label="%{@com.womobile.collect.model.WoTask@ALIAS_FIELD10}" key="field10" value="%{model.field10}" cssClass="" required="false" />
	</s:if>