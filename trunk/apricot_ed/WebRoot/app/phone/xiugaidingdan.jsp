<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>修改订单</title>
    
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
    <%
    String prename=request.getParameter("prename");
    String telephone=request.getParameter("tel");
    String prenumber=request.getParameter("prenumber");
    String ORDER_TYPE=request.getParameter("ORDER_TYPE");
    String OPERATE_ORDER_TIME=request.getParameter("OPERATE_ORDER_TIME");
    String PREARRANGE_ORDER_TIME=request.getParameter("PREARRANGE_ORDER_TIME");
    String MAN_COUNT=request.getParameter("MAN_COUNT");
	String SET_NO=request.getParameter("weizhi");
	String SF_ID=request.getParameter("id");
	String balcony_name=request.getParameter("bname");
	String MINCOST_MONEY=request.getParameter("mincost_money");
	String MINCOST_TYPE=request.getParameter("mincost_type");
	String ORDER_NO=request.getParameter("ORDER_NO");
	String i="";
	
	
	if(prename==""||prename==null||telephone==""||telephone==null||prenumber==""||prenumber==null||OPERATE_ORDER_TIME==""||OPERATE_ORDER_TIME==null||MAN_COUNT==""||MAN_COUNT==null||SET_NO==""||SET_NO==null||SF_ID==""||SF_ID==null||balcony_name==""||balcony_name==null||MINCOST_MONEY==""||MINCOST_MONEY==null||MINCOST_TYPE==""||MINCOST_TYPE==null||ORDER_NO==""||ORDER_NO==null)
	{
	
	  
	   prename=(String)request.getAttribute("prename");
	   telephone=(String)request.getAttribute("telephone");
	   prenumber=(String)request.getAttribute("prenumber");
	   ORDER_TYPE=(String)request.getAttribute("ORDER_TYPE");
	   
	   OPERATE_ORDER_TIME=(String)request.getAttribute("OPERATE_ORDER_TIME");   
	   PREARRANGE_ORDER_TIME=(String)request.getAttribute("PREARRANGE_ORDER_TIME");
	   MAN_COUNT=(String)request.getAttribute("MAN_COUNT");
	   SET_NO=(String)request.getAttribute("SET_NO");
       balcony_name=(String)request.getAttribute("balcony_name");
      
       MINCOST_MONEY=(String)request.getAttribute("MINCOST_MONEY");
       MINCOST_TYPE=(String)request.getAttribute("MINCOST_TYPE");
       ORDER_NO=(String)request.getAttribute("ORDER_NO"); 
       i=(String)request.getAttribute("i");
       if(i=="i")
		{out.println("请输入正确的完整信息！");}

		if(i=="j")
		{out.println("该时间已经被预定，请换个时间！");}
             		  
	}	
%>

 <form action="/apricot_ed/updatedingdan1">
<label>宾客姓名:</label><br>
<input name="prename" type="text" value="<%=prename%>" size="15"/><br /><br />

<label>联系电话:</label><br>
<input name="telephone" type="text" value="<%=telephone%>" size="15"/><br /><br />

<label>预约人数:</label><br>
<input name="prenumber" type="text"  value="<%=prenumber%>" size="15"/><br /><br />

<label>操作时间:</label><br>
<input name="OPERATE_ORDER_TIME" type="text"  value="<%=OPERATE_ORDER_TIME%>" size="15" readonly="true"/><br /><br />

<label>预抵时间:</label><br>
<input name="year" type="text" size="4" value="<%=PREARRANGE_ORDER_TIME.substring(0,4)%>"/>年<input name="month" type="text" size="2" value="<%=PREARRANGE_ORDER_TIME.substring(5,7)%>"/>月<input name="day" type="text" size="2" value="<%=PREARRANGE_ORDER_TIME.substring(8,10)%>"/>日<input name="shi" type="text" size="2" value="<%=PREARRANGE_ORDER_TIME.substring(11,13)%>"/>时<input name="fen" type="text" size="2" value="<%=PREARRANGE_ORDER_TIME.substring(14,16) %>"/>分<br /><br />

<label>预定座位:</label><br>
<input type="text" name="balcony_name" size="15" value="<%=balcony_name%>" readonly="true"/><br><br>

<label>操作人员:</label>
<select name="Operation" style='width:150px'>
					  <option value="2">Admin收银员</option>
					  <option value="3">收银员1</option>
					  <option value="4">收银员2</option>
</select><br /><br />

<label>订单类型:</label>
<select name="ORDER_TYPE" style='width:160px'>
 <%
                                              if(ORDER_TYPE.equals("2")){ 
                                              %>
													<option value="2">电话预订</option>					 								
													<%} 
											  else if(ORDER_TYPE.equals("1")){%>
													<option value="1">网上预订</option>											      
											  <%} 
											  else{%>
											 		<option value="3">店面预订</option>
											  		<option value="1">网上预订</option>
											        <option value="2">电话预订</option>
											 <%} %>
										</select><br /><br />

<label>最低消费类型:</label><select name="MINCOST_TYPE" style='width:135px'>
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
											 	</select><br /><br />
<label>最低消费金额:</label><br>
<input name="MINCOST_MONEY" type="text" value="<%=MINCOST_MONEY%>" size="15"/><br><br>

<label>来宾人数:</label><br>
<%if(MAN_COUNT.equals("null")){MAN_COUNT="";}%>
<input name="MAN_COUNT" type="text"  value="<%=MAN_COUNT%>" size="15"/><br /><br />

<input type="hidden" name="ORDER_NO"  value="<%=ORDER_NO %>"/>
<input type="hidden" name="SET_NO" value="<%=SET_NO%>"/>
<input type="hidden" name="SF_ID"  value="<%=SF_ID%>"/>
<input type="hidden" name="balcony_name" value="<%=balcony_name%>"/>
<input class="anniu" name="" type="submit" value="保存"/>
</form>

<form action="/apricot_ed/servlet/chexunjieguo">
<input class="anniu" type="submit" value="取消"/>

</form>
</body>
</html>
