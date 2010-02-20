<!--
main/left.jsp
功能:左边菜单栏
作者:qsy
生成日期:2005-04-28
修改日期:2005-04-28
-->
<%@include file="common.jsp"%>
<%@page contentType="text/html; charset=GB2312" import="YzSystem.JMain.*"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<%
FrmLeft frmLeft= (FrmLeft)UtilWebTools.getValueBinding("frmLeft");
%>
<head>
<title>left</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
<link rel="stylesheet" href="../css/mm.css" type="text/css"/>
<STYLE type="text/css"">
  BODY {
  scrollbar3dLightColor: #ff0000;
  }
</STYLE>
</head>
<body bgcolor="#78A327" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow-x:hidden; scrollbar-face-color:#78A327; scrollbar-arrow-color:#F0F9DF; scrollbar-track-color:#C8E097; scrollbar-3dlight-color:#E2F3C0; scrollbar-shadow-color:#59821C; scrollbar-highlight-color:#F0F9DF; scrollbar-darkshadow-color:#4B7013">
<%
  String firstPage = frmLeft.getFirstMenuPage();
%>
<script language="JavaScript" type="text/JavaScript">
parent.frames[3].location.href="../<%=firstPage%>";
</script><f:view>
  <h:form>
    <h:inputHidden value="#{frmLeft.rootMenu}">    </h:inputHidden>
    <table width="150" border="0" height="100%" cellspacing="0" cellpadding="5">
      <tr>
        <td valign="top" background="../images/leftbg.gif">
          <table width="100%" border="0" height="100%" cellspacing="0" cellpadding="0">
            <tr>
              <td valign="top">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="25">
                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="9" align="left">
                            <img src="../images/re01.gif" width="9" height="24">
                          </td>
                          <td background="../images/rebg01.gif" valign="bottom">
                            <b>
                              <font color="#FFFFFF">
                                <img src="../images/wuye.gif" width="18" height="18" align="absmiddle" hspace="3">
                                &nbsp;
</font>
                            </b>
                            <font color="#2C430B">
                              <h:outputLabel value="#{frmLeft.rootMenu}">                              </h:outputLabel>
                            </font>
                          </td>
                          <td width="10">
                            <img src="../images/re02.gif" width="10" height="24">
                          </td>
                        </tr>
                        <tr>
                          <td background="../images/rebg04.gif">&nbsp;</td>
                          <td bgcolor="#E2F3C0" height="12">
                            <img src="../images/lefthr.gif" width="113" height="5"/>
                          </td>
                          <td background="../images/rebg02.gif">&nbsp;</td>
                        </tr>
                      <%//　动态生成所有菜单栏
                      out.println(frmLeft.getMenuDetail());
                      %>
                      </table>
                    </td>
                  </tr>
                  <tr>
                    <td height="25">
                      <a href="../m_system/login.faces?logout=true" target="_top">
                        <img src="../images/exit_top.gif" width="18" height="18" hspace="2" align="absmiddle" border="0">
                        <span class="at">退出...</span>
                      </a>
                    </td>
                  </tr>
                </table>
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
