<?xml version="1.0" encoding="gb2312"?>
<%
	//防止IE缓存jsp文件
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@page language="java" errorPage="/error.jsp" pageEncoding="GB2312" contentType="text/xml;charset=gb2312" %>

<%@include file="/common/taglibs.jsp"%>
	<TreeNode>
		<ww:iterator value="menus" status="rowstatus">
			<TreeNode text="<ww:property value="menuName"/>" value="<ww:property value="menuId"/>"
				<ww:if test="\"1\".equals(menus[#rowstatus.index].haveSub)">
					src="/system/manage/menu.shtml?menuInfo.superId=<ww:property value="menuId"/>&amp;menuInfo.isMenu=1"
				</ww:if>
				action="/system/manage/menu.shtml?menuInfo.superId=<ww:property value="menuId"/>&amp;menuInfo.isMenu=0"
				target="FunListFrm"
			/>
		</ww:iterator>
	</TreeNode>

