<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:form action="group">
<s:hidden name="action"></s:hidden>
<s:hidden name="groupId" id="groupId"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="table6">
    <tr>
      <td colspan="2"></td>
    </tr>
    <tr>
      <td width="18%">用户组名</td>
      <td width="82%">
        <s:textfield id="groupName" name="groupName" cssClass="input2" size="40" onkeypress="return false;"></s:textfield>
      </td>
    </tr>
    <tr>
      <td valign="top">用户组描述</td>
      <td>
        <s:textarea id="groupDesc" name="groupDesc" cols="40" rows="5" cssClass="textarea1"></s:textarea>
      </td>
    </tr>
    <tr>
      <td valign="top">用户角色</td>
      <td>
        <table width="100%" border="0" cellpadding="3" cellspacing="0">
          <s:checkboxlist list="roleValues" listKey="key" listValue="value" name="roleIDs" theme="ems"></s:checkboxlist>
        </table>
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>
        <s:if test="%{action=='addsave'}">
        <input type="button" name="saveGroup" value="保存" class="button1" onclick="addGroup();"/>
        </s:if>
        <s:if test="%{action=='editsave'}">
        <input type="button" name="saveGroup" value="保存" class="button1" onclick="editDoGroup();"/>
        </s:if>
        <input type="button" name="closeButton" value="关闭" class="button1" onclick="cloesUgSetPage();"/>
      </td>
    </tr>
</table>
</s:form>