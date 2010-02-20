<!--
main/msg.jsp
功能:消息页面
描述:可通过?msg=xxx形式传递要显示的消息
作者:qsy
生成日期:2005-04-28
修改日期:2005-04-28
-->
<%@page contentType="text/html; charset=gb2312" language="java"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/mm.css" type="text/css">
</HEAD>
<BODY oncontextmenu=self.event.returnValue=false>
<table width="100%" border="0" height="100%">
  <tr align="center">
    <td>
    <form method="post" action="" name="forms[0]">
      <table border="1" bordercolorlight="000000" bordercolordark="FFFFFF"
        cellspacing="0" class="btd">
        <tr>
          <td>
            <table border="0" class="btd3" cellspacing="0" cellpadding="2" width="350">
              <tr>
                <td width="342">                </td>
                <td width="18">
                  <table border="1" class="btd2"  bordercolorlight="666666" bordercolordark="FFFFFF" cellpadding="0"  cellspacing="0" width="18">
                    <tr>
                      <td width="16">
                        <b>
                          <a href="javascript:history.go(-1)" onMouseOver="window.status='';return true" onMouseOut="window.status='';return true" title="关闭">
                            <font color="000000">×</font>
                          </a>
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
                <%
                  String msg = request.getParameter("msg");
                    out.println("<P>"+msg+"</P>");
                %>
                </td>
              </tr>
              <tr>
                <td colspan="2" align="center" valign="top">
                  <input type="button" name="ok" value="　确 定　" onclick=javascript:history.go(-1)>
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
<html>
<script language="JavaScript"></script></html>
