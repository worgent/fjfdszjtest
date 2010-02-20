
<!--
  m_sys/nologin.jsp
  功能:未登陆处理
  作者:qsy
  生成日期:2005-04-28
  修改日期:2005-04-28
-->
<%@page contentType="text/html; charset=GB2312" import="java.io.*"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/mm.css" type="text/css">
<script language="JavaScript" type="text/JavaScript">
　　　　　// 重新登陆
	function relogin() {
          window.location="../m_system/login.faces";

	}
</script>
</head>
<BODY oncontextmenu=self.event.returnValue=false>
<table width="100%" border="0" height="100%">
  <tr align="center">
    <td>
    <form method="post" action="" name="theform">
      <table border="1" bordercolorlight="000000" bordercolordark="FFFFFF" cellspacing="0" class="btd">
        <tr>
          <td>
            <table width="100%" border="0" class="btd3" cellspacing="0" cellpadding="2">
              <tr align="right">
                <td width="90%" align="left">未登陆</td>
                <td width="10%">
                  <table border="1" class="btd2" bordercolorlight="666666" bordercolordark="FFFFFF" cellpadding="0" cellspacing="0" width="18">
                    <tr>
                      <td width="16">
                        <b>
                          <font color="000000">×</font>
                        </b>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
            <table border="0" width="350" cellpadding="4">
              <tr>
                <td width="59" align="center" valign="top">&nbsp;</td>
                <td width="269">
                  <font color="red">连接超时，或者您已经签退，请重新登陆系统!</font>
                </td>
              </tr>
              <tr>
                <td colspan="2" align="center" valign="top">
                  <input type="button" name="ok" value="　重新登陆　" onclick="javascript:relogin();">
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </form>
    </td>
  </tr>
</table>
</body>
</html>

