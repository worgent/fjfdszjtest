<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <jsp:useBean id="time" scope="page" class="com.apricot.app.bean.nowtime"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>shouye</title>	
<link rel="stylesheet" type="text/css" href="sdmenu/sdmenu.css" />
<style type="text/css">
.i{font-size:18px;}
body{FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#D4EBF3,endColorStr=#E8F7FC);
background:url(picture/logon11.gif);
}
#obj{filter:revealTrans();}
</style>
<script type="text/javascript" src="sdmenu/sdmenu.js"></script>
	<script type="text/javascript">
	// <![CDATA[
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
	// ]]>
</script>
  </head>
  <body>	
<div id="left">
   <div id="my_menu" class="sdmenu">
      <div>
        <span>全部位置</span>
          <a href="../location?ORDER_STATUS=0&BELONG_TO=00" target="main">占座</a>
          <a href="../location?ORDER_STATUS=1&BELONG_TO=00" target="main">空闲</a>  
		  <a href="../location?ORDER_STATUS=2&BELONG_TO=00" target="main">预订</a> 
          <a href="../selectkongweizhi" target="main">全部位置</a>         
          </div>
      <div>
       <span>大厅</span>
		 <a href="../location?ORDER_STATUS=0&BELONG_TO=01" target="main">占座</a>
		 <a href="../location?ORDER_STATUS=1&BELONG_TO=01" target="main">空闲</a>
		 <a href="../location?ORDER_STATUS=2&BELONG_TO=01" target="main">预订</a>
      </div>
      <div>
        <span>包厢</span>
         <a href="../location?ORDER_STATUS=0&BELONG_TO=02" target="main">占座</a>
		 <a href="../location?ORDER_STATUS=1&BELONG_TO=02" target="main">空闲</a>
		 <a href="../location?ORDER_STATUS=2&BELONG_TO=02" target="main">预订</a>
      </div>
      <div>
      <span>大厅1</span>
         <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=0&BELONG_TO=01" target="main">占座扩展</a>
         <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=1&BELONG_TO=01" target="main">空闲座位</a>
		 <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=2&BELONG_TO=01" target="main">预订座位</a>
      </div>
      <div>
         <span>包厢1</span>
         <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=0&BELONG_TO=02" target="main">占座</a>
		 <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=1&BELONG_TO=02" target="main">空闲</a>
		 <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=2&BELONG_TO=02" target="main">预订</a>
      </div>
        <div>
         <span>全部座位</span>
         <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=0&BELONG_TO=00" target="main">占座</a>
		 <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=1&BELONG_TO=00" target="main">空闲</a>
		 <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=2&BELONG_TO=00" target="main">预订</a>
      </div>
   </div>
	 </div>
  </body>
</html>