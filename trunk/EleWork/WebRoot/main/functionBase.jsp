<!--
main/tradeBase.htm
功能:交易页面-公用模板
作者:qsy
生成日期:2005-04-28
修改日期:2005-04-28
-->
<%@page contentType="text/html; charset=GB2312" %>
<%String selfWeb = UtilWebTools.getRequestPath();%>
<%
  // 判断权限
  if (!frmFunctionBase.hasRight) {
    response.sendRedirect("../main/msg.faces?msg="
                          + "您没有使用<" + frmFunctionBase.functionInfo.getTheName() + "--"
                          + frmFunctionBase.modeName + ">的权限！");
    return;
  }

  // 让原来网页过期,防止缓存.
//  response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
//  response.setHeader("Pragma", "no-cache"); //HTTP 1.0
//  response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<html>
<head>
<title>
<%out.print(frmFunctionBase.caption);%>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript" src="../inc/public.js" type="text/JavaScript"></script><script language="javascript"  type="text/JavaScript">
var curPage="<%=selfWeb%>";
</script><link rel="stylesheet" href="../css/mm.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<f:view>
<form name="theForm" method="post" action="" >
  <table width="100%" border="0" cellspacing="10" cellpadding="0">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="1" cellpadding="0" class="btd3">
          <tr>
            <td>
            <%
              out.println(frmFunctionBase.genHtmlMain());
            %>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="hidden" name="hWhich" value="">
<script language="javascript"  type="text/JavaScript">
var parames="<%=frmFunctionBase.parames%>";
var findparames="<%=frmFunctionBase.findparam%>"
var detailParames="<%=frmFunctionBase.detailParams%>"
var mode="<%=frmFunctionBase.mode%>"
</script>
</form>
</f:view>
</body>
</html>
