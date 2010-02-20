<!--
  m_system/login.jsp
  功能:登陆页面
  作者:qsy
  生成日期:2005-04-28
  修改日期:2005-04-28
-->
<%@include file="../config.jsp"%>
<%@page contentType="text/html; charset=GB2312" import="YzSystem.JMain.*" errorPage="../main/error.faces"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%
  // 设置系统常量
  session.setAttribute("SystemURL", SystemURL);
  session.setAttribute("RowCount", RowCount);
  session.setAttribute("DetailEditRow", DetailEditRow);
  if (UtilCommon.NVL(request.getParameter("logout")).equals("true")) {
    UtilWebTools.ClearSessionENV();
  }
%>
<html>
<head>
<title><%=SystemTitle%></title>
<link href="../Style" rel="stylesheet" type="text/css">
<style type="text/css">
  <!--
    td {  font-size: 12px; line-height: 14px}
    .tl {  font-size: 14px; line-height: 14px}
    .ts {  font-size: 12px; line-height: 14px}
    .INPUT {WIDTH: 115px;height: 20px}
    .TT {  font-size: 12px; color: #FFFFFF}
    .ts2 {  font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 10px; color: #FFFFFF}
  -->
</style>
<script language="JavaScript" type="text/JavaScript">
        // 登陆验证
	function dologin() {
		if (document.getElementById("loginForm:userID").value=="") {
			alert("账号不能为空！");
			return false;
		}
		if (document.getElementById("loginForm:pwd").value=="") {
			alert("密码不能为空！");
			return false;
		}
                return true;
	}
</script></head>
<body bgcolor="#699326" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<f:view locale="loginForm">
  <h:form id="loginForm">
    <f:attribute name="name" value="loginForm"/>
<table width="100%" border="0" height="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center">
      <table width="587" border="0" cellspacing="0" cellpadding="0" height="296">
        <tr>
          <td height="55" background="<%=SystemURL%>images/home_top.gif" align="right" valign="bottom">
            <table width="116" border="0" cellspacing="0" cellpadding="5">
              <tr>
                    <td>                      &nbsp;
                      <font color="E08204">                        &copy;
                        :中 国 邮 政
</font>
                    </td>

              </tr>
            </table>

          </td>
        </tr>
        <tr>
          <td valign="top" height="202">
            <table width="100%" border="0" height="202" cellspacing="0" cellpadding="0">
              <tr>
                <td valign="top" width="311"><img src="<%=SystemURL%>images/home_left.jpg" width="311" height="202"></td>
                <td background="<%=SystemURL%>images/home_cen.jpg" valign="top">
                  <table width="100%" border="0" height="202" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="60">
                        <table width="100%" border="0" cellpadding="3" cellspacing="0" height="60">
                          <tr>
                            <td height="30" valign="bottom">&nbsp;&nbsp;<font color="#E08204"></font>

                            </td>
                          </tr>
                          <tr>
                                <td height="20" valign="bottom">                                  &nbsp;&nbsp;
                                  <h:outputLabel value="#{frmLogin.hint}">                                  </h:outputLabel>
                                  <font color="#E08204">                                    &nbsp;&nbsp;
                                    请输入帐号与密码...
</font>
                                </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <table width="100%" border="0" height="142" cellspacing="0" cellpadding="17">
                          <tr>
                            <td>
                              <table width="100%" border="0" cellspacing="0" cellpadding="3">
                                  <tr>
                                    <td align="right" width="28%"><font color="E08204">帐号:</font></td>
                                      <td width="72%">
                                        <h:inputText id="userID" value="#{frmLogin.userID}" size="17" style="INPUT">
                                          <f:attribute name="name" value="userID"/>
                                        </h:inputText>
                                      </td>
                                  </tr>
                                  <tr>
                                    <td align="right" width="28%"><font color="E08204">密码:</font></td>
                                      <td width="72%">
                                        <h:inputSecret id="pwd" value="#{frmLogin.pwd}" size="17" style="INPUT"/>
                                      </td>
                                  </tr>
                                  <tr>
                                    <td width="28%">&nbsp;</td>
                                      <td width="72%">
                                        <h:commandButton value="确定" action="#{frmLogin.doLogin}" onclick="javascript:return dologin();"/>
                                        <input type="reset" name="Submit" value="重 置" class="ts">
                                      </td>
                                  </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </td>

                    <td width="45" valign="top">
                      <img src="<%=SystemURL%>images/home_right.jpg" width="45" height="202"/></td>

              </tr>
            </table>
          </td>
        </tr>
        <tr>

              <td valign="top" height="39">
                <img src="<%=SystemURL%>images/home_bottom.jpg" width="587" height="39">
              </td>
        </tr>
      </table>
      </td>
  </tr>
</table>
  </h:form>
</f:view>
</body>
</html>
