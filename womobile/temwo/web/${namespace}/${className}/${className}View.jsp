<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do"> 
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<body>
   <table width="90%"  border="0">
   <tr>
   <td colspan="3" align="center"> 
       <strong>${table.tableAlias}</strong>
   </td>
   </tr>
    <tr>
	<td colspan="3">
		<s:actionerror theme="ems" /><s:actionmessage theme="ems" />
	</td>
  </tr>
   <#list table.columns as c> 
   <tr> 
   <td>${c.columnAlias}:
   <s:property value="%{${classNameLower}.${c.sqlName}}"/>
   </td>
   </tr>   
   </#list>
   <tr>
   <td align="center" colspan="3">
      <input type="button" onclick="javascript:history.go(-1)" value="返回"/>
   </td>  
   </tr>
 </table>
</body>
</html>