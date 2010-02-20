<!--
main/findBase.htm
功能:查找页面-公用模板
作者:qsy
生成日期:2005-04-28
修改日期:2005-04-28
-->
<html>
<head>
<!--打印Title-->
<%@page contentType="text/html; charset=GB2312" %>
<title>
<%out.println(frmFunctionBase.caption);%>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript" src="../inc/public.js" type="text/JavaScript"></script><script language="javascript"  type="text/JavaScript">

</script><link rel="stylesheet" href="../css/mm.css" type="text/css">
<script language="JavaScript" type="text/JavaScript">
// 当前页面
var curPage="<%=UtilWebTools.getRequestPath()%>";
// 页面参数
var parames="<%=frmFunctionBase.parames%>";
var findparames=""
 </script></head>
<base target="_self">
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="theForm" method="post">
  <table width="100%" border="0" cellspacing="10" cellpadding="0">
    <tr>
      <td>
                <!--产生查询结果Grid-->
                <%
                out.println(frmFunctionBase.genHtmlQueryComp());
                out.println(frmFunctionBase.genHtmlQueryResult());
                %>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
