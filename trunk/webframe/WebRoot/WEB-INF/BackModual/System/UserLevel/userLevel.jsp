<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户等级设置</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<p>&nbsp;</p>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="4"><strong>用户等级设置</strong></td>
        </tr>
        <tr>
          <td  class="td1"><strong>用户等级列表</strong></td><td  class="td1"><strong>经验值</strong></td>
          <td  class="td1"><strong>贡献值</strong></td><td  class="td1"><strong>名望值</strong></td>
        </tr>
        <s:iterator id="userlevel" value="%{userLevelList}">

        <tr>
          <td width="25%"><s:property value="#userlevel.LEVELNAME"/></td>
          <td width="25%">
            &gt;=
            <s:property value="#userlevel.EXPERIENCEVALUE"/>
          </td>
           <td width="25%">
           	 &gt;=
            <s:property value="#userlevel.CONTRIBUTEVALUE"/>
          </td>
           <td width="25%">
            &gt;=<s:property value="#userlevel.FAMEVALUE"/>
            <s:url action="adminUserLevel?action=edit" id="adminUserLevelEditUrl" includeParams="none">
            <s:param name="search.id" value="#userlevel.ID"/>
            </s:url>
            <s:a href="%{adminUserLevelEditUrl}">修改</s:a>

            <s:url action="adminUserLevel?action=del" id="adminUserLevelDelUrl" includeParams="none">
            <s:param name="search.id" value="#userlevel.ID"/>
            </s:url>
            <s:a href="%{adminUserLevelDelUrl}">删除</s:a>
          </td>
        </tr>

        </s:iterator>
        <tr>
          <td colspan="4">
          <s:url action="adminUserLevel?action=add" id="adminUserLevelNewUrl" includeParams="none">
            <s:param name="levelType" value="%{levelType}"/>
            </s:url>
            <s:a href="%{adminUserLevelNewUrl}"><strong>增加新等级</strong></s:a>
          </td>
          </tr>
      </table></td>
    </tr>
  </table>
<p>&nbsp;</p>
</body>
</html>