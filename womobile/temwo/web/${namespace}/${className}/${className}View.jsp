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
<center>
   <table width="90%" height="190" border="1">
   <tr>
   <td colspan="3"> 
       <strong>${table.tableAlias}</strong>
   </td>
   </tr>
    <tr>
	<td>
		<s:actionerror theme="ems" />
	</td>
	<td>
		<s:actionmessage theme="ems" />
	</td>
  </tr>
   <#list table.columns as c> 
   <tr> 
   <td>${c.columnAlias}:</td>
   <td>
   <s:property value="%{${classNameLower}.${c.sqlName}}"/>
   </td>
   <td><div id="p${c.sqlName}Tip" style="width:250px"></div></td>
   </tr>   
   </#list>
   <tr>
   <td align="center">
      <input type="button" onclick="javascript:history.go(-1)" value="返回"/>
   </td>  
   </tr>
 </table>
</center>
</body>
</html>