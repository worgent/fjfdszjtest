
<%--
  main/common.jsp
  功能:权限验证-公用模板
  作者:qsy
  生成日期:2005-04-28
  修改日期:2005-04-28
--%>
<%@include file="../config.jsp"%>
<%
  // 判断是否有登陆
  if (session.getAttribute("userInfo") == null) {
    /*
       // 纺织缓存保存.
     response.setHeader("Cache-Control","no-store"); //HTTP 1.1
     response.setHeader("Pragma","no-cache"); //HTTP 1.0
     response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
     */
%>
<!-- 定位到没有登陆的页面-->
<html>
<head>
<script language="JavaScript" type="text/JavaScript">
     parent.location="../m_system/nologin.faces";

      </script>
</head>
</html>
<%
  // 提交response,防止异常
  return;
  }
%>
