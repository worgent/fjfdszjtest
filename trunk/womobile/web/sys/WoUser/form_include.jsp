<%@page import="com.womobile.sysmanage.login.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="userId" name="userId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.womobile.sysmanage.login.model.WoUser@ALIAS_GROUP_ID}" key="groupId" value="%{model.groupId}" cssClass="required validate-integer max-value-2147483647" required="true" />
	
	
	<s:textfield label="%{@com.womobile.sysmanage.login.model.WoUser@ALIAS_USERNAME}" key="username" value="%{model.username}" cssClass="required " required="true" />
	
	
	<s:textfield label="%{@com.womobile.sysmanage.login.model.WoUser@ALIAS_PASSWD}" key="passwd" value="%{model.passwd}" cssClass="required " required="true" />
	
	
	<s:textfield label="%{@com.womobile.sysmanage.login.model.WoUser@ALIAS_REPASSWD}" key="repasswd" value="%{model.repasswd}" cssClass="required " required="true" />
	
