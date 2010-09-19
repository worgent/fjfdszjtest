<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", 0); 
%>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr class="td1">
    <td width="80%">
      <div align="center">角色名称</div>
    </td>
    <td width="10%">
      <div align="center">修改</div>
    </td>
    <td width="10%">
      <div align="center">删除</div>
    </td>
  </tr>
  <s:iterator value="%{roleList}" id="role">
    <tr>
      <td>
        <strong><s:property value="#role.ROLENAME"/></strong>
      </td>
      <td>
        <div align="center">
        	<s:url action="role?action=edit" id="editRoleURL">
        		<s:param name="roleId" value="#role.ROLEID"></s:param>
        	</s:url>
        	<s:a href="%{editRoleURL}" >修改</s:a>
        
        </div>
      </td>
      <td>
        <div align="center"><a href="javascript:;" onclick="delRole('<s:property value="#role.ROLEID"/>');">删除</a></div>
      </td>
    </tr>
  </s:iterator>
</table>