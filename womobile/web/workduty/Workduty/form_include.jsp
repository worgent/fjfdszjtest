<%@page import="com.qzgf.application.workduty.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	
	<s:textfield label="%{@com.qzgf.application.workduty.model.Workduty@ALIAS_TITLE}" key="title" value="%{model.title}" cssClass="required " required="true" />
	
	
	<s:textfield label="%{@com.qzgf.application.workduty.model.Workduty@ALIAS_DETAIL}" key="detail" value="%{model.detail}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@com.qzgf.application.workduty.model.Workduty@ALIAS_ADJUNCT}" key="adjunct" value="%{model.adjunct}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@com.qzgf.application.workduty.model.Workduty@ALIAS_TARGET}" key="target" value="%{model.target}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@com.qzgf.application.workduty.model.Workduty@ALIAS_FORMAT}" key="format" value="%{model.format}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@com.qzgf.application.workduty.model.Workduty@ALIAS_PROID}" key="proid" value="%{model.proid}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@com.qzgf.application.workduty.model.Workduty@ALIAS_ATTITUDE}" key="attitude" value="%{model.attitude}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@com.qzgf.application.workduty.model.Workduty@ALIAS_GRADE}" key="grade" value="%{model.grade}" cssClass="" required="false" />
	
	
	<s:textfield label="%{@com.qzgf.application.workduty.model.Workduty@ALIAS_ISEND}" key="isend" value="%{model.isend}" cssClass="" required="false" />
	
