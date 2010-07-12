<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Hello.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1[1].3.2.min.js"></script>
<script type="text/javascript">
   function hideDiv(){
     //alert("你好");
     $("#myDiv").hide(1000);
   }
   function showDiv(){
     //alert("你好");
     $("#myDiv").show(1000);
   }
</script>
<style type="text/css">
#myDiv{
  width:400px;
  height:400px;
  border:2px solid orange;
  display:none;
   z-index:2;
}

</style>
  </head>
  
  <body>
    <div id="myDiv">
      <form action="">
        <input name="username"><br>
        <input name="username"><br>
        <input name="username"><br>
        <input name="username"><br>
        <input type="button" value="点击隐藏" onclick="hideDiv()"/>
      </form>
    </div>
    <input type="button" value="点击显示" onclick="showDiv()"/>
  </body>
</html>
