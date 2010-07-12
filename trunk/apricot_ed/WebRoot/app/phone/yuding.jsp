<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>下单</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="../Calender/WdatePicker.js"></script>
	<style type="text/css">
     body{background:#ccc;}
    .anniu{color:white;background-color:black;width:80px;height:35px;float:left;margin-left:10px;}
    </style>
  </head>
    
  <body>
  <%System.out.print("错在这！！"); %>
  <%
String SET_NO=request.getParameter("weizhi");
String SF_ID=request.getParameter("id");
String balcony_name=request.getParameter("bname");
String MINCOST_MONEY=request.getParameter("mincost_money");
String MINCOST_TYPE=request.getParameter("mincost_type");
String telephone="";
String prename="";
String VIPcard="";
String Middleman="";
String guests="";
String i="";
if(SET_NO==null||SET_NO==""||SF_ID==null||SF_ID==""||balcony_name==null||balcony_name==""||MINCOST_MONEY==""||MINCOST_MONEY==null||MINCOST_TYPE==null||MINCOST_TYPE=="")
{
//telephone=(String)request.getAttribute("telephone");
//prename=(String)request.getAttribute("prename");
//Middleman=(String)request.getAttribute("Middleman");
guests=(String)request.getAttribute("guests");

SET_NO=(String)request.getAttribute("SET_NO");
SF_ID=(String)request.getAttribute("SF_ID");
balcony_name=(String)request.getAttribute("balcony_name");
MINCOST_TYPE=(String)request.getAttribute("MINCOST_TYPE");
MINCOST_MONEY=(String)request.getAttribute("hightmoney");
i=(String)request.getAttribute("i");
if(i=="i")
{out.println("请输入正确的完整信息！");}
}
if(i=="j")
{out.println("该时间已经被预定，请换个时间！");}
%>
<form action="/apricot_ed/xiadan1">

<label>宾客姓名: </label><br>
<input name="prename" type="text" size="15" value="<%=prename%>"/><br />

<label>联系电话:</label><br>
<input name="telephone" type="text" size="15" value="<%=telephone%>"/><br />

<label>推荐人:</label><br>
<input name="Middleman" type="text"size="15" value="<%=Middleman%>"/><br />

<label>来宾人数:</label><br>
<input name="guests" type="text" size="15" value="<%=guests%>"/><br />

<label>最低消费类型:</label><select name="MINCOST_TYPE">
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
											 <%} %>
											 </select><br/><br>
											 
<label>服务生工号:</label>
<select name="waiter" style='width:140px'><option value="2">Admin收银员</option>
					  <option value="3">收银员1</option>
					  <option value="4">收银员2</option>
					  </select><br /><br />											 
<label>最低消费金额:</label><br>
<input name="hightmoney" type="text" value="<%=MINCOST_MONEY%>" size="15" readonly="true"/><br /><br />

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
