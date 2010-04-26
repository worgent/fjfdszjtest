<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>wap注册</title>
</head>
<body>
<div align="center">
  <form  method='POST' name='form1' action='/user.do?action=wapRegedit'  id="form1">
   <table border="1">
     <tr> 
   <td>&nbsp;</td>
   <td> 
       <strong>wap 注册页面</strong>
   </td>
   <td>&nbsp;</td>
   </tr>
   
    <tr> 
   <td>用户名:</td>
   <td>
      <input type="text" id="username"  name="search.pusername"/>
   <font color="red">*</font>
   </td>
   <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>
   
    <tr> 
   <td>密码:</td>
   <td>
      <input type="text" id="username"  name="search.pusername"/>
   <font color="red">*</font>
   </td>
   <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>
    <tr> 
   <td>确认密码:</td>
   <td>
      <input type="text" id="username"  name="search.pusername"/>
   <font color="red">*</font>
   </td>
   <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>
    <tr> 
   <td>手机:</td>
   <td>
      <input type="text" id="username"  name="search.pusername"/>
   <font color="red">*</font>
   </td>
   <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>
   
    <tr> 
   <td>地图标注:</td>
   <td>
     
     
        <div id="map_canvas"  style="width: 500px; height: 300px"></div>
        <input type="hidden"  id="search.plat" name="search.plat" value="38.93724"  /><!-- 纬度 -->
		<input type="hidden"  id="search.plng" name="search.plng" value="100.45733"  /><!-- 经度 -->
    
   </td>
   <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>
    <tr> 
   <td>&nbsp;</td>
   <td>
   <input type="button" id="saveId" name="save"  onclick="javascript:isExist(this);"  value="注 册"  />
  
   </td>
   <td>&nbsp;</td>
   </tr>
</table>
</form>

</div>
</body>
</html>