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
<s:form action="adminUserLevel">
<s:hidden name="action"></s:hidden>
<s:hidden name="search.ID"></s:hidden>
  <table width="90%"  border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
    <tr>
      <td><table width="100%"  border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td colspan="2"><strong>用户等级设置</strong></td>
        </tr>
        <tr>
        <td colspan="2">
        <s:actionerror theme="webframe0"/>
        </td>
      </tr>
      <tr>
        <td colspan="2">
        <s:actionmessage theme="webframe0"/>
        </td>
      </tr>
        <tr>
          <td colspan="2" class="td1">
            <s:if test="%{action=='addnew'}"><strong>新增用户等级</strong></s:if>
            <s:if test="%{action=='editdo'}"><strong>修改用户等级</strong></s:if>
          </td>
        </tr>
        <tr>
          <td width="60%">等级名称</td>
          <td width="40%">
            <s:textfield id="levelName" name="search.LEVELNAME" size="20" maxlength="20" cssClass="input2"></s:textfield>
          	 <font color="red">*</font>
          </td>
        </tr>
        <tr>
          <td width="60%">经验值大于</td>
          <td width="40%">
            <s:textfield id="levelValue" name="search.EXPERIENCEVALUE" size="10" maxlength="10" cssClass="input2"></s:textfield>
          	 <font color="red">*</font>
          </td>
        </tr>
        <tr>
          <td width="60%">贡献值大于</td>
          <td width="40%">
            <s:textfield id="levelValue" name="search.CONTRIBUTEVALUE" size="10" maxlength="10" cssClass="input2"></s:textfield>
          	 <font color="red">*</font>
          </td>
        </tr>
         <tr>
          <td width="60%">名望值大于</td>
          <td width="40%">
            <s:textfield id="levelValue" name="search.FAMEVALUE" size="10" maxlength="10" cssClass="input2"></s:textfield>
          	 <font color="red">*</font>
          </td>
        </tr>
        <tr>
          <td colspan="2"><div align="center">
            <s:submit cssClass="button2" value="保存"></s:submit>
          </div></td>
          </tr>
      </table></td>
    </tr>
  </table>
</s:form>

<p>&nbsp;</p>
</body>
</html>