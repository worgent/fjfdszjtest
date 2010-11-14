<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do"> 
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
	<td>
		<a href="javascript:chk_all();">选择</a>
	</td>
	<#list table.columns as c>
	<#if !c.htmlHidden>
		<td>
			<strong>${c.columnAlias}</strong>
		</td>
	</#if>
	</#list>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}" >
		<tr>
		    <td>
		    <input type='checkbox' name='searchpid' id='searchpid' value='<s:property value="#g.id"/>'/>
		    <a href='<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?action=view&search.pid=<s:property value="#g.id"/>'  target='_self'> <s:property value="#g.id"/></A>
		    </td>
			<#list table.columns as c>
			<#if !c.htmlHidden>
			<td>
				<s:property value="#g.${c.sqlName}" />
			</td>
			</#if>
			</#list>	
		</tr>
	</s:iterator>
	<tr class="bgColor3">
		<td colspan="20" align="center">
		    <center>
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}"  javaScript="loadDefaultList"/>
			</center>
		</td>
	</tr>
</table>
