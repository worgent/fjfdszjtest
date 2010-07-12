<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="java.util.*"import="com.apricot.app.pda.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>预定</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="../Calender/WdatePicker.js"></script>
  </head>  
   <style type="text/css">
  body{background:#ccc;}
 .ding{background:url(phone_img/12.gif);color:white; height:25px;padding-top:4px;text-align:center;}
 .shou{background:#ccc;text-align:left;}
   .anniu{color:white;background-color:black;width:80px;height:35px;float:left;margin-left:10px;}
  .di{text-align:center;font-size:20px;padding-left:200px;}
  </style>
  <body>
  <jsp:useBean id="time" scope="page" class="com.apricot.app.pda.nowtime"/>
  <%
  String balcony_name=request.getParameter("bname");
String SET_NO=request.getParameter("weizhi");
String SF_ID=request.getParameter("id");
String MINCOST_MONEY=request.getParameter("mincost_money");
String MINCOST_TYPE=request.getParameter("mincost_type");
System.out.println("hhh="+balcony_name);
String telephone="";
String prename="";
String VIPcard="";
String prenumber="";
String Releasetime="";
String i="";
if(SET_NO==null||SET_NO.equals(null))
{
//String zhuangtai=(String)request.getAttribute("zhuangtai");
SET_NO=(String)request.getAttribute("SET_NO");
SF_ID=(String)request.getAttribute("SF_ID");
balcony_name=(String)request.getAttribute("balcony_name");
MINCOST_TYPE=(String)request.getAttribute("MINCOST_TYPE");
MINCOST_MONEY=(String)request.getAttribute("hightmoney");
telephone=(String)request.getAttribute("telephone");
prename=(String)request.getAttribute("prename");
prenumber=(String)request.getAttribute("prenumber");
Releasetime=(String)request.getAttribute("Releasetime");
i=(String)request.getAttribute("i");
}
//out.println(SET_NO);
if(i=="j")
{out.println("该时间已经的位置已经被预定！");}
if(i=="i")
{out.println("请输入正确的完整信息！");}
%>
<form action="/apricot_ed/yuding1">

<label>宾客姓名: </label><br>
<input name="prename" type="text" size="15" value="<%=prename%>"/><br />

<label>联系电话:</label><br>
<input name="telephone" type="text" size="15" value="<%=telephone %>"/><br />

<label>预约人数:</label><br>
<input name="prenumber" type="text" size="15" value="<%=prenumber%>"/><br />

<label>操作时间: &nbsp;&nbsp;</label><br>
<input name="Operationtime" type="text" value="<%=time.time()%>" size="15" readonly="true"/><br /><br />
<%//out.println(time.time().substring(8,10));%>
<label>预抵时间: &nbsp;&nbsp;</label><br>
<input name="year" type="text" size="4" value="<%=time.time().substring(0,4)%>" readonly="true"/>年<input name="month" type="text" size="2" value="<%=time.time().substring(5,7)%>" readonly="true"/>月<input name="day" type="text" size="2" value="<%=time.time().substring(8,10)%>"/>日<input name="shi" type="text" size="2"/>时<input name="fen" type="text" size="2"/>分<br /><br />

<label>预定座位:</label><br>
<input name="preweizhi" type="text" value="<%=balcony_name%>" size="15" readonly="true"><br>


<label>最低消费类型:</label>
<select name="MINCOST_TYPE" style='width:125px' readonly="true">
													<%
                                              if(MINCOST_TYPE.equals("1")){ 
                                              %>
													<option value="1">人均</option>
													<%} 
											else if(MINCOST_TYPE.equals("2")){%>
											        <option value="2">总金额</option>
											 <%} 
											 else{%>
											  		<option value=""></option>
											        <option value="2">总金额</option>
											 		<option value="1">人均</option>
											 		
											 <%} %></select><br /><br>
<label>最低消费金额:</label><br>
<input name="hightmoney" type="text" value="<%=MINCOST_MONEY%>" size="15" readonly="true"/><br /><br />

<label>到达预抵时间后自动释放:</label><br>
<input name="Releasetime" type="text"  value="10" size="15" value="<%=Releasetime%>"/><br />
<label>操作人员:</label>
<select name="Operation" style='width:166px'>
					  <option value="2">Admin收银员</option>
					  <option value="3">收银员1</option>
					  <option value="4">收银员2</option>
</select><br /><br />


<label>订单类型: </label>
<select name="type" style='width:166px'>
					  <option value="2">电话预订</option>
</select><br /><br />



<input type="hidden" name="SET_NO" value="<%=SET_NO%>"/>
 <input type="hidden" name="SF_ID"  value="<%=SF_ID%>"/>
  <input type="hidden" name="balcony_name" value="<%=balcony_name%>"/>
 <input class="anniu" type="submit" value="保存"/>

</form>

<form action="/apricot_ed/servlet/chexunjieguo">
<input class="anniu" type="submit" value="取消"/>
</form>
  </body>
</html>
