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
<%String ref = request.getHeader("REFERER");%>
</head>
	<body>

<center>
<form  method='POST' name='form1' action=''  id="form1">

   <table >
    <tr>
   <td>&nbsp;</td>
      <td  align="center">此用户已经存在,不能重复 <br></td>
   <td>&nbsp;</td>
   </tr>
    
  
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
<input type="button" name="returngo" value="返 回"   onclick="javascript:window.location='<%=ref%>'" >
 &nbsp;<input type="reset"  id="setId"  name="rereset" value="重置"  />
  </td>
   <td>&nbsp;</td>
   </tr>
 </table>

</form>
</center>
</body>
</html>