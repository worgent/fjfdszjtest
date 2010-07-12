<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>预定下的开单</title> 
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
    String tel=request.getParameter("tel");
    String prenumber=request.getParameter("prenumber");
    String ORDER_TYPE=request.getParameter("ORDER_TYPE");
    String OPERATE_ORDER_TIME=request.getParameter("OPERATE_ORDER_TIME");
    String PREARRANGE_ORDER_TIME=request.getParameter("PREARRANGE_ORDER_TIME");
    String MAN_COUNT=request.getParameter("MAN_COUNT");
    //out.println("ORDER_TYPE"+ORDER_TYPE);
	String SET_NO=request.getParameter("weizhi");
	String SF_ID=request.getParameter("id");
	String balcony_name=request.getParameter("balcony_name");
	String MINCOST_MONEY=request.getParameter("mincost_money");
	String MINCOST_TYPE=request.getParameter("mincost_type");
	String ORDER_NO=request.getParameter("ORDER_NO");
//out.println("bianhao"+balcony_name);
if(OPERATE_ORDER_TIME==""||OPERATE_ORDER_TIME==null||OPERATE_ORDER_TIME.equals(null))
{
            prename=(String)request.getAttribute("prename");
			tel=(String)request.getAttribute("telephone");
            prenumber=(String)request.getAttribute("prenumber");
            ORDER_TYPE=(String)request.getAttribute("ORDER_TYPE");
            OPERATE_ORDER_TIME=(String)request.getAttribute("Operationtime");
            MAN_COUNT=(String)request.getAttribute("MAN_COUNT");   
            SET_NO=(String)request.getAttribute("SET_NO");
            SF_ID=(String)request.getAttribute("SF_ID");
            balcony_name=(String)request.getAttribute("preweizhi");
            MINCOST_MONEY=(String)request.getAttribute("MINCOST_MONEY");
            MINCOST_TYPE=(String)request.getAttribute("MINCOST_TYPE");
            ORDER_NO=(String)request.getAttribute("ORDER_NO");
            PREARRANGE_ORDER_TIME=(String)request.getAttribute("Arrivedtime");
         
	     // xiadantime=(String)request.getAttribute("xiadantime");
	     // Operation=(String)request.getAttribute("Operation");
	    
}
%>
  
<form action="/apricot_ed/pre_xiadan_select1">
    
<label>宾客姓名:</label><br>
<input name="prename" type="text" value="<%=prename%>" size="15" readonly="true"/><br /><br />

<label>联系电话:</label><br>
<input name="telephone" type="text"  value="<%=tel%>" size="15" readonly="true"/><br /><br />

<label>预约人数:</label><br>
<input name="prenumber" type="text" value="<%=prenumber%>" size="15" readonly="true"/><br /><br />

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
											 <%} %></select><br /><br />


<label>操作时间:</label><br>
<input name="Operationtime" type="text" value="<%=OPERATE_ORDER_TIME%>" size="15" readonly="true"/><br /><br />

<label>预抵时间:</label><br>
<input name="Arrivedtime" type="text" value="<%=PREARRANGE_ORDER_TIME%>" size="15" readonly="true" /><br /><br />


<label>预定座位:</label>
<select name="preweizhi" style='width:150px'>
                      <option value="<%=SET_NO %>"><%=balcony_name%></option>
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
<input name="MINCOST_MONEY" type="text" value="<%=MINCOST_MONEY%>" size="15" readonly="true"/><br /><br />

<label>来宾人数:</label><br>
<%if(MAN_COUNT.equals("null")){MAN_COUNT="";}%>
<input name="MAN_COUNT" type="text"  value="<%=MAN_COUNT%>" size="15"/><br /><br />
<label>操作人员:</label>
<select name="Operation">
					  <option value="2">Admin收银员</option>
					  <option value="3">收银员1</option>
					  <option value="4">收银员2</option>
					  </select><br /><br />
<input type="hidden" name="SET_NO" value="<%=SET_NO%>"/>
 <input type="hidden" name="SF_ID"  value="<%=SF_ID%>"/>
  <input type="hidden" name="balcony_name" value="<%=balcony_name%>"/>
 <input type="hidden" name="ORDER_NO" value="<%=ORDER_NO%>"/>

<input class="anniu" name="" type="submit" value="确定"/>
</form>

<form action="/apricot_ed/servlet/chexunjieguo">
<input class="anniu" type="submit" value="取消"/>
</form>
  </body>
</html>
