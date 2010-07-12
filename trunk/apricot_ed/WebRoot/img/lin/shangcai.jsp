<%@ page contentType="text/html; charset=GB2312" language="java" import="java.sql.*" import="java.util.*"import="com.apricot.app.bean.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shangcai.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
    <style type="text/css">
  body{background:#eeefff;}
 .ding{background:url(img/ming.gif); height:25px;padding-top:4px;text-align:center;}
 .shou{background:#eeefff;text-align:left;}
  </style>
  <body>
    <div class="shou">
	        <div class=ding>点菜系统</div>
	<form id="form" name="form" method="post" action="/apricot_ed/servlet/shangcai">
	<table>
    <tr><td>菜品名称</td><td>菜品单价</td><td>菜品数量</td><td>选中</td></tr>
    <%
    	Order_list order_list; 
		List list=new ArrayList();
		list=(List)request.getAttribute("shangcai");  
    	for(int i=0;i<list.size();i++)
		{
		order_list=(Order_list)list.get(i);	
		%><tr><td><%out.println(order_list.getFood_info().getFOOD_NAME());%></td>
		<td><%out.println(order_list.getFOOD_PRICE());%></td>
		<td><input name="<%=order_list.getFOOD_ID()%>" type="text" size="5" value="1"/></td>
		<td><input name="shangcaicheckbox" type="checkbox" value="<%=order_list.getFOOD_ID()%>" /></td></tr>
		<% 
		}
        %>
      </table>
      <br> <input   type="submit"   value="上菜结束"   onclick="window.close()">
     </form>
	</div>  
	    
	<a href ="" onClick="parent.history.back(); return false;" onMouseOver="self.status='后退' ;return true;">后退</a> 
	<!-- 将SRC后的图片改为你的向前的图片名-->                  
	<a href ="" onClick="parent.history.forward(); return false;" onMouseOver="self.status='前进'; return true;">前进</a>       
  </body>
</html>
