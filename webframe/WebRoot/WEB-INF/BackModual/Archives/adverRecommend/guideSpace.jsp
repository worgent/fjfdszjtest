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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>向导空间</title>
</head>
<body>
  <center>
    <table border="0"  width="60%">
  <tr>
  <td>
    &nbsp;
  </td> 
  <td>
      <a href="/adverRec.do?action=business&search.guideUser=<s:property value="search.username"/>">商家推荐</a>
  </td> 
  <td>
   &nbsp;
  </td>

  </tr>
  <tr>
  <td>
    &nbsp;
  </td> 
  <td>
     备注:用户点击向导空间的商家商家推荐，进入商家空间
  </td> 
  <td>
   &nbsp;
  </td>

  </tr>
  
  </table>
 
</center>

 

</body>
</html>