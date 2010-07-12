<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="java.util.*"import="com.apricot.app.bean.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>客户下单</title>   
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="../Calender/WdatePicker.js"></script>
	<script type="text/javascript">
	function kong(){
	var name=document.getElementById("name").value;
	var telephone=document.getElementById("telephone").value;
	var VIP=document.getElementById("VIP").value;
	var waiter=document.getElementById("waiter").options[0].value;
	var Middleman=document.getElementById("Middleman").value;
	var starttime=document.getElementById("starttime").value;
	var guests=document.getElementById("guests").value;
	var MINCOST_TYPE=document.getElementById("MINCOST_TYPE").options[0].value;
	var hightmoney=document.getElementById("hightmoney").value;
	
	if(name==""||telephone==""||VIP==""||waiter==""||Middleman==""||starttime==""||guests==""||MINCOST_TYPE==""||hightmoney=="")
		{
	        alert("数据必须全部填写");
	        return false;
		}
    if(telephone.match(/[a-z]/ig))
    {
      alert("电话号码必须是数字")
      return false;
    }
     if(Middleman.match(/[0-9]/ig))
    {
      alert("中间人必须是英文或中文")
      return false;
    }
     if(guests.match(/[a-z]/ig))
    {
      alert("来宾人数为数字")
      return false;
    }
    if(guests>1000)
    { 
      alert("预约人数不能超过1000");
       return false
    }
    if(hightmoney.match(/[a-z]/ig))
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
  <jsp:useBean id="time" scope="page" class="com.apricot.app.bean.nowtime"/>
  <%
String SET_NO=request.getParameter("weizhi");
String SF_ID=request.getParameter("id");
String balcony_name=request.getParameter("bname");
//balcony_name=new String(balcony_name.getBytes("ISO-8859-1"),"UTF-8");
String MINCOST_MONEY=request.getParameter("mincost_money");
String MINCOST_TYPE=request.getParameter("mincost_type");
out.println(SET_NO);
out.println(SF_ID);
out.println(balcony_name);
out.println("最低消费金额"+MINCOST_MONEY);
out.println("最低消费类型"+MINCOST_TYPE);
%>
<form action="../xiadan">
<table>
  <tr>
     <td>客户姓名</td>
     <td>联系电话</td>
  </tr>
   <tr>
     <td><input name="name" type="text" id="name"/></td>
     <td><input name="telephone" type="text"  id="telephone"/></td>
  </tr>
  
  <tr>
     <td>VIP卡号</td>
     <td>服务生工号</td>
  </tr>
   <tr>
     <td><input name="VIP" type="text" id="VIP"/></td>
     <td><select name="waiter" id="waiter" style='width:155px'><option value="2">Admin收银员</option>
					  <option value="3">收银员1</option>
					  <option value="4">收银员2</option>
					  </select></td>
  </tr>
  
   <tr>
     <td>中间人</td>
     <td>下单时间</td>
  </tr>
   <tr>
     <td><input name="Middleman" id="Middleman" type="text" /></td>
     <td><input name="starttime" id="starttime" type="text" value="<%=time.time()%>" readonly="true"/></td>
  </tr>
   
   <tr>
     <td>来宾人数</td>
     <td>最低消费类型</td>
  </tr>
   <tr>
     <td><input name="guests" id="guests" type="text" /></td>
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
  </tr>
   <tr>
     <td><input name="hightmoney" type="text" id="hightmoney" value="<%=MINCOST_MONEY%>" readonly="true"/></td>
  </tr>
  
</table>
 <input type="hidden" name="SET_NO" value="<%=SET_NO%>"/>
 <input type="hidden" name="SF_ID"  value="<%=SF_ID%>"/>
 <input type="hidden" name="balcony_name" value="<%=balcony_name%>"/>
<input name="" type="submit" value="保存" onclick="return(kong());"/>
<button onclick="window.close()">退出</button>
</form>
  </body>
</html>
