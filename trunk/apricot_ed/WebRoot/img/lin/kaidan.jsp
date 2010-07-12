<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" import="java.util.*"import="com.apricot.app.bean.*" errorPage="" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'kaidan.jsp' starting page</title> 
	<script type="text/javascript" src="../Calender/WdatePicker.js"></script>
	<script type="text/javascript">
	function kong(){
	var prename=document.getElementById("prename").value;
	var prenumber=document.getElementById("prenumber").value;
	var VIPcard=document.getElementById("VIPcard").value;
	var ORDER_TYPE=document.getElementById("ORDER_TYPE").options[0].value;
	var xiadantime=document.getElementById("xiadantime").value
	if(prename==""||prenumber==""||VIPcard==""||ORDER_TYPE==""||xiadantime=="")
	{
	   alert("数据必须全部填写");
	        return false;
	}
	}
	</script>
  </head>
  
  <body>
    <%
     request.setCharacterEncoding("GB2312");
    String prename=request.getParameter("prename");
    String tel=request.getParameter("tel");
    String prenumber=request.getParameter("prenumber");
    String vipcard=request.getParameter("vipcard");
    String ORDER_TYPE=request.getParameter("ORDER_TYPE");
    String OPERATE_ORDER_TIME=request.getParameter("OPERATE_ORDER_TIME");
    String PREARRANGE_ORDER_TIME=request.getParameter("PREARRANGE_ORDER_TIME");
    String MAN_COUNT=request.getParameter("MAN_COUNT");
    out.println("ORDER_TYPE"+ORDER_TYPE);
String SET_NO=request.getParameter("weizhi");
String SF_ID=request.getParameter("id");
String balcony_name=request.getParameter("bname");
String MINCOST_MONEY=request.getParameter("mincost_money");
String MINCOST_TYPE=request.getParameter("mincost_type");
String ORDER_NO=request.getParameter("ORDER_NO");
out.println("ORDER_NO"+ORDER_NO);


%>
  
<form action="../pre_xiadan_select">

<table>
   <tr>
      <td>操作人员</td>
      <td>预约人名字</td>
   </tr>
     <tr>
      <td><select name="Operation" id="Operation" style='width:155px'>
					  <option value="2">Admin收银员</option>
					  <option value="3">收银员1</option>
					  <option value="4">收银员2</option>
					  </select></td>
      <td><input name="prename" id="prename" type="text" value="<%=prename%>" readonly="true"/></td>
   </tr>
   
   <tr>
      <td>预约人数</td>
      <td>VIP卡号</td>
   </tr>
     <tr>
      <td><input name="prenumber" id="prenumber" type="text" value="<%=prenumber%>" readonly="true"/></td>
      <td><input name="VIPcard" id="VIPcard" type="text" value="<%=vipcard%>" readonly="true"/></td>
   </tr>
   
   <tr>
      <td>订单类型</td>
      <td>操作时间</td>
   </tr>
     <tr>
      <td><select name="ORDER_TYPE" id="ORDER_TYPE" style='width:155px'>
 			<%
                                              if(ORDER_TYPE.equals("2")){ 
                                              %>
													<option value="2">电话预订</option>
					 								<option value="1">网上预订</option>
													<option value="3">店面预订</option>
													
													<%} 
											else if(ORDER_TYPE.equals("1")){%>
													<option value="1">网上预订</option>
											        <option value="2">电话预订</option>
													<option value="3">店面预订</option>
													
											 <%} 
											 else{%>
											 		<option value="3">店面预订</option>
											  		<option value="1">网上预订</option>
											        <option value="2">电话预订</option>
											 <%} %>
		</select></td>
      <td><input name="Operationtime"  id="Operationtime" type="text" value="<%=OPERATE_ORDER_TIME%>" /></td>
   </tr>
   
    <tr>
      <td>预抵时间</td>
      <td>下单时间</td>
   </tr>
     <tr>
      <td><input name="Arrivedtime" id="Arrivedtime" type="text" value="<%=PREARRANGE_ORDER_TIME%>"/></td>
      <td><input name="xiadantime" id="xiadantime" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/></td>
   </tr>
   
    <tr>
      <td>预定座位</td>
      <td>最低消费类型</td>
   </tr>
     <tr>
      <td><select name="preweizhi" id="preweizhi" style='width:155px'>
                      <option value="<%=SET_NO%>"><%=balcony_name%></option>
		 </select></td>
      <td><select name="MINCOST_TYPE" id="MINCOST_TYPE" style='width:155px'>
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
											 <%} %>
											</select></td>
   </tr>
   
   <tr>
      <td>最低消费金额</td>
      <td>来宾人数</td>
   </tr>
     <tr>
      <td><input name="MINCOST_MONEY" id="MINCOST_MONEY" type="text" value="<%=MINCOST_MONEY%>" readonly="true"/></td>
      <td><input name="MAN_COUNT" id="MAN_COUNT" type="text"  value="<%=MAN_COUNT%>" readonly="true"/></td>
   </tr>
</table>

 <input type="hidden" name="SET_NO" value="<%=SET_NO%>"/>
 <input type="hidden" name="SF_ID"  value="<%=SF_ID%>"/>
 <input type="hidden" name="balcony_name" value="<%=balcony_name%>"/>
 <input type="hidden" name="ORDER_NO" value="<%=ORDER_NO%>"/>
<input name="" type="submit" value="保存" onclick="return(kong());"/>
<button onclick="window.close()">退出</button>


</form>
  </body>
</html>
