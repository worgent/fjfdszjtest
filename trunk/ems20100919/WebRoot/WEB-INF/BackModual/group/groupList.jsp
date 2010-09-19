<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
  <tr class="td1">
    <td width="25%" >
      <div align="left">用户组名</div>
    </td>
    <td width="45%" align="left">
      <div align="left">描述</div>
    </td>
    <td width="15%">
      <div align="center">修改</div>
    </td>
    <td width="15%" >
      <div align="center">删除</div>
    </td>
  </tr>
  <s:iterator id="group1" value="%{groupList}">
  <tr>
    <td><strong><s:property value="#group1.GROUPNAME"/></strong></td>
    <td><s:property value="#group1.GROUPDESC"/></td>
    <td>
      <div align="center"><a href="javascript:;" onclick="editGroup('<s:property value="#group1.GROUPID"/>');">修改</a></div>
    </td>
    <td>
      <div align="center"><a href="javascript:;" onclick="delGroup('<s:property value="#group1.GROUPID"/>');">删除</a></div>
    </td>
  </tr>
  </s:iterator>
</table>