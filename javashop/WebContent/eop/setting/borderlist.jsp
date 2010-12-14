<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<input type="text" name="border" id="wdborder"  eop_type="widget_params"/>
<div class="wdg_tpl">
	<ul>
	<li  id="none" >无边框</li>
	<c:forEach items="${borderList}" var="border">
		
			<li  id="${border.borderid }" >${border.bordername }</li>
		
	</c:forEach>	
	
	</ul>
</div>中文