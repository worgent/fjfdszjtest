<%@ page contentType="text/html; charset=GB2312" language="java" import="java.sql.*" import="java.util.*"import="com.apricot.app.pda.*" errorPage="" %>
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
  body{background:#ccc;}
 .ding{background:url(phone_img/12.gif);color:white;  height:25px;padding-top:4px;text-align:center;}
 .shou{background:#ccc;text-align:left;}
  .anniu{color:white;background:url(phone_img/12.gif);width:80px;height:35px;}
   .di{text-align:center;font-size:20px;padding-left:170px;}
  </style>
  <body>
    <div class="shou">
	        <div class=ding>���ϵͳ</div>
	        
	<form id="form" name="form" method="post" action="/apricot_ed/servlet/tuicai00">
	
	
	<select name='yuanyin' style='width:70px'>
	<option value='0' selected>�ϲ���</option>
	<option value='1'>������</option>
	<option value='2'>����</option>
	</select>
	
	<table>
    <tr><td>��Ʒ����</td><td>��Ʒ����</td><td>��Ʒ����</td><td>ѡ��</td></tr>
    <%
    	Order_list order_list;  
		List list=new ArrayList();
		list=(List)request.getAttribute("tuicai");  
    	for(int i=0;i<list.size();i++)
		{
		order_list=(Order_list)list.get(i);
		%><tr><td><%out.println(order_list.getFood_info().getFOOD_NAME());%></td>
		<td><%out.println(order_list.getFOOD_PRICE());%></td>
		<td><input name="<%=order_list.getFOOD_ID()%>" type="text" size="5" value="1"/></td>
		<td><input name="tuicaicheckbox" type="checkbox" value="<%=order_list.getFOOD_ID()%>" /></td></tr>
		<% 
		}
        %>
      </table>
      <br><input class="anniu" type="submit" value="ȷ��">
     </form>
	</div> 
	<a class="di" href="/apricot_ed/servlet/chexunjieguo">�˲˽���</a>
	 <!--<a href ="" onClick="parent.history.back(); return false;" onMouseOver="self.status='����' ;return true;">����</a>  --> 
	<!-- ��SRC���ͼƬ��Ϊ�����ǰ��ͼƬ��-->                  
	 <!--<a href ="" onClick="parent.history.forward(); return false;" onMouseOver="self.status='ǰ��'; return true;">ǰ��</a>  --> 
  </body>
</html>