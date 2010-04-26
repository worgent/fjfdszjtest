<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
<title>广告推荐</title>
</head>
<body>
<center>

     <form  method='POST' name='form1'  action='/adverRec.do?action=insert'  id="form1">
      <table width="287" height="54">
      <tr>
      <td> 
       <a href="/adverRec.do?action=userList">广告推荐</a>
      </td>
        <td> 
       <a href="/selfAdver.do?action=userList">自助广告</a>
      </td>
      
      </tr>
      <tr><td colspan="2"> 选择进入向导选择空间</td></tr>
      </table>    
     </form>
     
</center> 

</body>
</html>