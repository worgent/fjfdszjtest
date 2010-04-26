<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>前后台切换</title>
</head>
<body>
<div  align="center">
   <table  border="1" width="50%">
   <tr>
   <td>
      <a href="/adverRec.do?action=adverRec">前台进入</a>
   </td>
   <td>
      <a href="/login.do?action=backLogin">后台进去</a>
   </td>
   </tr>
   </table>
</div>
</body>
</html>