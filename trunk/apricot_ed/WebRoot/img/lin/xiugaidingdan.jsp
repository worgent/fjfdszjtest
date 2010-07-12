<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" import="java.util.*"import="com.apricot.app.bean.*" errorPage="" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>修改订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script type="text/javascript" src="../Calender/WdatePicker.js"></script>
<script type="text/javascript">

function kong(){
	var Operation=document.getElementById("Operation").value;
	var prename=document.getElementById("prename").value;
	var telephone=document.getElementById("telephone").value;
	var prenumber=document.getElementById("prenumber").value;
	var VIPcard=document.getElementById("VIPcard").value; 
	var ORDER_TYPE=document.getElementById("ORDER_TYPE").options[0].value;
	var Operationtime=document.getElementById("Operationtime").value;
	var Arrivedtime=document.getElementById("Arrivedtime").value;
	var preweizhi=document.getElementById("preweizhi").options[0].value;
	var MINCOST_TYPE=document.getElementById("MINCOST_TYPE").options[0].value;
	var MINCOST_MONEY=document.getElementById("MINCOST_MONEY").value;
	var MAN_COUNT=document.getElementById("MAN_COUNT").value;
	if(Operation==""||prename==""||telephone==""||prenumber==""||VIPcard==""||ORDER_TYPE==""||Operationtime==""||Arrivedtime==""||preweizhi==""||MINCOST_TYPE==""||MINCOST_MONEY==""||MAN_COUNT=="")
		{
	        alert("数据必须全部填写");
	        return false;
		}
    if(telephone.match(/[a-z]/ig))
    {
      alert("电话号码必须是数字")
      return false;
    }
    if(prenumber.match(/[a-z]/ig))
    {
      alert("预约人数为数字")
      return false;
    }
     if(MAN_COUNT.match(/[a-z]/ig))
    {
      alert("来宾人数为数字")
      return false;
    }
    if(MAN_COUNT>100)
    { 
      alert("预约人数不能超过100");
       return false
    }
    if(MINCOST_MONEY.match(/[a-z]/ig))
    {
      alert("金额为数字")
      return false;
    }
    else
       return true;
	}



</script>
  </head>
  
  <body>
    <%
          request.setCharacterEncoding("GB2312");
    String prename=request.getParameter("prename");
  //  prename.trim();
   // byte[] b=new byte[100];
    //b=prename.getBytes("GB2312");
   // prename=new String(b,"GB2312");
   // out.println(prename.length());
    
    String tel=request.getParameter("tel");
    String prenumber=request.getParameter("prenumber");
    //prename=new String(prename.getBytes("ISO-8859-1"),"UTF-8");
    String vipcard=request.getParameter("vipcard");
    String ORDER_TYPE=request.getParameter("ORDER_TYPE");
    String OPERATE_ORDER_TIME=request.getParameter("OPERATE_ORDER_TIME");
    String PREARRANGE_ORDER_TIME=request.getParameter("PREARRANGE_ORDER_TIME");
    String MAN_COUNT=request.getParameter("MAN_COUNT");
	String SET_NO=request.getParameter("weizhi");
	String SF_ID=request.getParameter("id");
	String balcony_name=request.getParameter("bname");
	//balcony_name=new String(balcony_name.getBytes("ISO-8859-1"),"UTF-8");
	String MINCOST_MONEY=request.getParameter("mincost_money");
	String MINCOST_TYPE=request.getParameter("mincost_type");
	String ORDER_NO=request.getParameter("ORDER_NO");
	String xiadantime=request.getParameter("xiadantime");
    out.println("预订人名字="+prename);
    out.println("电话号码="+tel);

%>
 <form action="../updatedingdan">
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
 		   <td><input name="prename" id="prename" type="text" value="<%=prename%>"/></td>
 		</tr>
         <tr>
 		   <td>联系电话</td>
 		   <td>预约人数</td>
 		</tr>
 		<tr>
 		   <td><input name="telephone" id="telephone" type="text" value="<%=tel%>"/></td>
 		   <td><input name="prenumber" id="prenumber" type="text"  value="<%=prenumber%>"/></td>
 		</tr>
 		
 		<tr>
 		   <td>VIP卡号</td>
 		   <td>订单类型</td>
 		</tr>
 		
 		<tr>
 		   <td><input name="VIPcard" id="VIPcard" type="text" value="<%=vipcard%>"/></td>
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
 		</tr>
 		
 		<tr>
 		   <td>操作时间</td>
 		   <td>预抵时间</td>
 		</tr>
 		<tr>
 		   <td><input name="Operationtime" id="Operationtime" type="text"  value="<%=OPERATE_ORDER_TIME%>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/></td>
 		   <td><input name="Arrivedtime" id="Arrivedtime" type="text" value="<%=PREARRANGE_ORDER_TIME%>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})"/></td>
 		</tr>
 		
 		<tr>
 		   <td>来宾人数</td>
 		   <td>预定座位</td>
 		</tr>
 		<tr>
 		   <td><input name="MAN_COUNT" id="MAN_COUNT" type="text"  value="<%=MAN_COUNT%>"/></td>
 		   <td><select name="preweizhi" id="preweizhi"  style='width:155px'>
                      <option value="<%out.println(balcony_name);%>"><%out.println(balcony_name);%></option>
			</select></td>
 		</tr>
 		
 		
 		<tr>
 		   <td>最低消费类型</td>
 		   <td>最低消费金额</td>
 		</tr>
 		<tr>
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
 		   <td><input name="MINCOST_MONEY" id="MINCOST_MONEY" type="text" value="<%=MINCOST_MONEY%>" onclick="kong()" readonly="true"/></td>
 		</tr>
 		
 </table>
<input type="hidden" name="ORDER_NO"  value="<%=ORDER_NO %>"/>
<input type="hidden" name="SET_NO" value="<%=SET_NO%>"/>
<input type="hidden" name="SF_ID"  value="<%=SF_ID%>"/>
<input type="hidden" name="balcony_name" value="<%=balcony_name%>"/>
<input name="" type="submit" value="保存" onclick="return(kong());window.close();"/>
<button onclick="window.close()">退出</button>
</form>
  </body>
</html>
