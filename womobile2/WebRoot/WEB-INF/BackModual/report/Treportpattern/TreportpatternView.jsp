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
       <strong>报表管理</strong>
   </td>
   </tr>
    <tr>
	<td colspan="3">
		<s:actionerror theme="ems" /><s:actionmessage theme="ems" />
	</td>
  </tr>
   <tr> 
   <td>模板:
   <s:property value="%{treportpattern.PatternId}"/>
   </td>
    <td>报表名称:
   <s:property value="%{treportpattern.ReportName}"/>
   </td>
      <td>是否启动:
   <s:property value="%{treportpattern.State}"/>
   </td>
   </tr>   
    
   <tr> 

   <td>备注:
   <s:property value="%{treportpattern.Remark}"/>
   </td>
   <td>创建人:
   <s:property value="%{treportpattern.Maker}"/>
   </td>
   <td>创建时间:
   <s:property value="%{treportpattern.MakeDateTime}"/>
   </td>
   </tr>  
   <tr>
   <td align="center" colspan="3">
      <input type="button" onclick="javascript:history.go(-1)" value="返回" class="btn" />
   </td>  
   </tr>
 </table>
</body>
</html>