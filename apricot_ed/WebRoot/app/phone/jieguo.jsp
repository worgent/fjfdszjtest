<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" import="java.util.*"import="com.apricot.app.pda.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shouye.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
		function checkdata() {
			//alert(searchForm.query.value)
			if(!check_empty(searchForm.query.value)){
				alert("不能为空");	
				return false;
			}
			return true;
		}
	</script>
	        <style type="text/css">    
           .suggest_link{     
              background-color:#FFFFCC; //背景色  
              padding:2px 6px 2px 6px;   
           }   
           .suggest_link_over{   
              cursor:default;   
              color:white;   
              background-color:#3366CC;//选中的背景色   
              padding:2px 6px 2px 6px;   
           }   
           #suggest{   
              position:absolute;                 
              text-align:left;  
              width:82px;  
              margin-left:120px; 
              border:1px solid #000000;   
           } 
           
             .suggest1_link{     
              background-color:#FFFFCC; //背景色  
              padding:2px 6px 2px 6px;   
           }   
           .suggest1_link_over{   
              cursor:default;   
              color:white;   
              background-color:#3366CC;//选中的背景色   
              padding:2px 6px 2px 6px;   
           }   
           #suggest1{   
              position:absolute;                 
              text-align:left;  
              width:82px;  
              margin-left:120px; 
              border:1px solid #000000;   
           }     
        </style>   
        <script type="text/javascript">   
              
function getXmlHttpRequest(){   
    if(window.XMLHttpRequest){   
        return new XMLHttpRequest();   
    }else if(window.ActiveXObject){   
        return new ActiveXObject("Microsoft.XMLHttp");   
    }else{   
        alert("The browser does not support XmlHttpRequest object");   
        return;   
    }   
}   
 
var xmlHttp=getXmlHttpRequest();   
 
 
 var str;
 var id;
 var obj1;
function search(obj,idname){  
    obj1=obj;//用来存选中的值
     str=obj.value; //text输入值
     id=idname;  //显示div的ID
    if(str==""){   
        document.getElementById(id).style.display='none';   
        return;   
        
    }
    else
    {   
        //display the suggest   
        // alert(str+"222");
        document.getElementById(id).style.display='';   
    }     
    //release the pressure for server because of input in field at a high speed   
    if(xmlHttp.readyState==4||xmlHttp.readyState==0){   
        var url="app/phone/g_suggest.jsp?idinfo="+id;  
        string="para="+str; 
        xmlHttp.open("POST",url,true);   
        xmlHttp.onreadystatechange=callback; 
        xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  
        xmlHttp.send(string);   
    }   
}   
 
function callback(){   
    if(xmlHttp.readyState==4){   
        if(xmlHttp.status==200){       
            var str=xmlHttp.responseText;  
           
            var str1=str.split("\n"); 
            
            var result="";   
            for(i=0;i<str1.length;i++){   
               result+='<div onmouseover="javascript:suggestOver(this);"';   
               result+='onmouseout="javascript:suggestOut(this);"';   
               result+='onclick="setSearch(this.innerHTML);"';   
               result+='class="suggest_link">'+str1[i]+'</div>';   
            }   
            document.getElementById(id).innerHTML=result;   
        }   
        else{   
            alert("problem retrieving data:"+xmlHttp.statusText);   
        }   
    }   
}   

//mouse over function   
function suggestOver(div_value){   
    div_value.className=id+"_link_over";   
}   
//mouse out function   
function suggestOut(div_value){   
    div_value.className=id+"_link";   
}   
//click function   
function setSearch(value){   
   //value值为选中的值
    obj1.value=value;   
    document.getElementById(id).innerHTML='';   
    document.getElementById(id).style.display='none';   
}   
  
OnDocumentClick=function(event){   
    if(!event){   
        event=window.event;//ie   
    }   
    var e=event.target;//for firefox   
    if(!e){   
        e=event.srcElement;//for ie   
    }   
    while(e){   
        //only if event source is on search_suggest or the search box   
        //the popup div will not be hidden   
        if(e==document.getElementById(id)||e==document.getElementById(str)){   
            return;   
        }   
        e=e.parentNode;   
    }   
    document.getElementById(id).style.display="none";   
}   
 
//for ie   
try{   
    document.onclick=OnDocumentClick;   
}catch(e){   
}   
//for firfox(core Gecko)   
try{   
    document.addEventListener('click',OnDocumentClick,true);   
}catch(e){   
}   
              
        </script> 
    
       
        
  <style type="text/css">
  body{background:#ccc;}
 .ding{background:url(phone_img/12.gif); color:white; height:25px;padding-top:4px;text-align:center;}
 .shou{background:#ccc;text-align:left;}
 .logo{margin-top:30%}
 .jieguo{text-align:center;font-color:#222222;}
 .dingdanxinxi{text-align:left;margin-left:60px;width:350px;}
 .ul{text-align:left;}
 .dingdanmingxi{text-align:left;padding:10px;}
 table{text-align:center;margin-top:-70px;}
 li{padding:5px;}
 .anniu{color:white;background:url(phone_img/12.gif);width:80px;height:35px;}
 
a:link    { color: #000000 }
a:visited { color: #000000 }
a:active  { color: red }

 </style>
  <body>
    <jsp:useBean id="dining" scope="page" class="com.apricot.app.pda.dining_set_info"/>
  <%
  request.setCharacterEncoding("UTF-8");
  String zhuangtai=(String)request.getAttribute("zhuangtai");
  dining_set_info dining_info=(dining_set_info)request.getAttribute("dining_info");
   %>
   <div class="shou">
        <div class=ding>点菜系统</div>
    	<br>
    	<form id="form" name="form" method="post" action="/apricot_ed/servlet/chexunjieguo">
    	餐位名称：
    	<input name="denglu_name"  font-size="20" id="txt" type="text" size="10" onkeyup="search(this,'suggest')">
    	<input class="anniu" type="submit" value="确定"><br>
    	<div id="suggest" style="display:none"></div>
    	</form>
    	<form id="form1" name="form1" method="post" action="/apricot_ed/servlet/chexunjieguo">
    	<br>
    	餐位编码：
    	<input type="text" name="denglu_num" id="tet1" size="10" onkeyup="search(this,'suggest1')">
    	<input class="anniu" type="submit" value="确定"><br>
    	<div id="suggest1" style="display:none"></div>
    	</form>
    </div>
    <div class="jieguo">
    <%if(zhuangtai.equals("0")){%>
       <img class="thumb" src=phone_img/picFree.png width=200 height=200><br>
       <table>
       <tr>
       <td>
       <form action="app/phone/yuding.jsp">
       	<input type="hidden" name="weizhi" value="<%=dining_info.getSET_NO()%>"/>   
       	<input type="hidden" name="id" value="<%=dining_info.getSF_ID()%>"/>   
       	<input type="hidden" name="bname" value="<%=dining_info.getBALCONY_NAME()%>"/>
       	<input type="hidden" name="mincost_type" value="<%=dining_info.getMINCOST_TYPE()%>"/>
       	<input type="hidden" name="mincost_money" value="<%=dining_info.getMINCOST_MONEY()%>"/>   	
       	<input type="submit" value="客户下单"/>   
     	</form> 
   	   </td>    
   	   <td>    
   	    <form action="app/phone/yuyue.jsp">
       	<input type="hidden" name="weizhi" value="<%=dining_info.getSET_NO()%>"/>   
       	<input type="hidden" name="id" value="<%=dining_info.getSF_ID()%>"/>   
       	<input type="hidden" name="bname" value="<%=dining_info.getBALCONY_NAME()%>"/>
       	<input type="hidden" name="mincost_type" value="<%=dining_info.getMINCOST_TYPE()%>"/>
       	<input type="hidden" name="mincost_money" value="<%=dining_info.getMINCOST_MONEY()%>"/>  
       	<input type="submit" value="客户预定"/>   
     	</form>
     	</td>
     	</tr>
     	</table>
   	   <%
   
    }
    else if(zhuangtai.equals("1")){
    %>
    	<img class="thumb" src=phone_img/picUse.png width=200 height=200><br>
  
        <a href="/apricot_ed/servlet/diancai00"><%out.print("点菜"); %></a>&nbsp&nbsp&nbsp
        <a href="/apricot_ed/servlet/shangcai00"><%out.print("上菜"); %></a>&nbsp&nbsp&nbsp
        <a href="/apricot_ed/servlet/chuicai?nihao=非师范的"><%out.print("催菜"); %></a>&nbsp&nbsp&nbsp
        <a href="/apricot_ed/servlet/tuicai00"><%out.print("退菜");%></a><br><%              
    
        //订单信息
        %><div class="dingdanxinxi">
        <div class="ding">订单信息</div>
        <ul>
		<li>楼号:<%out.println(dining_info.getDINING_FLOOR()+"楼");%></li>
		<li>座位号：<%out.println(dining_info.getSET_NO()+"号");%></li>
		<li>座位名字：<%out.println(dining_info.getBALCONY_NAME());%></li>
		<li>联系电话：<%out.println(dining_info.getOrder_info().getPREARRANGE_PHONE());%></li>
	    <li>订单时间：<%out.println(dining_info.getOrder_info().getORDER_TIME());%></li>
	    <li>容纳人数：<%out.println(dining_info.getOrder_info().getPREARRANGE_MAN_COUNT());%></li>
		</ul>
		</div>
		

       <% //菜信息
        Order_list order_list;  
		List list1=new ArrayList();
		list1=(List)request.getAttribute("dingdanxinxi"); 
		if(list1.size()!=0){  %>
        <div class="dingdanmingxi">
        <div class="ding">订单明细</div> 
        <table><tr><td>菜品名称</td>&nbsp<td>数量</td>&nbsp<td>菜品标志</td>&nbsp<td>是否上菜</td>&nbsp<td>当前价格</td></tr><%
		for(int i=0;i<list1.size();i++)
		{
		order_list=(Order_list)list1.get(i);
		%><tr><td><%out.println(order_list.getFood_info().getFOOD_NAME());%></td>
		<td><%out.println(order_list.getFOOD_COUNT());%></td>
		<td><%if(order_list.getFOOD_ACTION().equals("1")){out.println("点菜");}else if(order_list.getFOOD_ACTION().equals("2")){out.println("退菜");}else{out.println("取消");}%></td>
		<td><%if(order_list.getSERVING_FLAG().equals("0")){out.print("未上菜");}else{out.print("已上菜");}%></td>
		<td><%out.println(order_list.getFood_info().getFOOD_PRICE());%></td></tr><%
		}
        %>
        </table>
        </div>
        <%
        }
    }
    else if(zhuangtai.equals("2")){
    %>
      <img class="thumb" src=phone_img/2.png width=200 height=200><br>
      <table>
      <tr>
      <td>
      <!-- <a href="/apricot_ed/servlet/diancai"><%out.print("点菜");%></a>&nbsp&nbsp&nbsp -->
      <form action="/apricot_ed/servlet/diancai00">
      <input type="submit" value="点菜"/>
      </form>
      </td>
      <td>
      <form action="app/phone/kaidan.jsp">
        <input type="hidden" name="balcony_name" value="<%=dining_info.getBALCONY_NAME()%>"/>
       	<input type="hidden" name="ORDER_NO" value="<%=dining_info.getOrder_info().getORDER_NO()%>"/>   
       	<input type="hidden" name="MAN_COUNT" value="<%=dining_info.getOrder_info().getMAN_COUNT()%>"/>   
       	<input type="hidden" name="PREARRANGE_ORDER_TIME" value="<%=dining_info.getOrder_info().getPREARRANGE_ORDER_TIME()%>"/>
       	<input type="hidden" name="OPERATE_ORDER_TIME" value="<%=dining_info.getOrder_info().getOPERATE_ORDER_TIME()%>"/> 
       	<input type="hidden" name="ORDER_TYPE" value="<%=dining_info.getOrder_info().getORDER_TYPE()%>"/>
       	<input type="hidden" name="vipcard" value="<%=dining_info.getOrder_info().getVIP_CARD_NO()%>"/>
       	<input type="hidden" name="prenumber" value="<%=dining_info.getOrder_info().getPREARRANGE_MAN_COUNT()%>"/>
       	<input type="hidden" name="prename"/ value="<%=dining_info.getOrder_info().getPREARRANGE_NAME()%>"/>
       	<input type="hidden" name="tel" value="<%=dining_info.getOrder_info().getPREARRANGE_PHONE()%>"/>
       	<input type="hidden" name="weizhi" value="<%=dining_info.getSET_NO()%>"/>
       	<input type="hidden" name="id" value="<%=dining_info.getSF_ID()%>"/>
       	<input type="hidden" name="mincost_type" value="<%=dining_info.getMINCOST_TYPE()%>"/>
       	<input type="hidden" name="mincost_money" value="<%=dining_info.getMINCOST_MONEY()%>"/> 
       	<input type="submit" value="客户开单"/>  
     	</form> 
     	</td>
     	
     	<td>
     	<form action="app/phone/xiugaidingdan.jsp">
     	<input type="hidden" name="xiadantime" value="<%=dining_info.getOrder_info().getORDER_TIME()%>"/>
     	<input type="hidden" name="ORDER_NO" value="<%=dining_info.getOrder_info().getORDER_NO()%>"/>   
       	<input type="hidden" name="MAN_COUNT" value="<%=dining_info.getOrder_info().getMAN_COUNT()%>"/>   
       	<input type="hidden" name="PREARRANGE_ORDER_TIME" value="<%=dining_info.getOrder_info().getPREARRANGE_ORDER_TIME()%>"/>
       	<input type="hidden" name="OPERATE_ORDER_TIME" value="<%=dining_info.getOrder_info().getOPERATE_ORDER_TIME()%>"/> 
       	<input type="hidden" name="ORDER_TYPE" value="<%=dining_info.getOrder_info().getORDER_TYPE()%>"/>
       	<input type="hidden" name="vipcard" value="<%=dining_info.getOrder_info().getVIP_CARD_NO()%>"/>
       	<input type="hidden" name="prenumber" value="<%=dining_info.getOrder_info().getPREARRANGE_MAN_COUNT()%>"/>
       	<input type="hidden" name="prename"/ value="<%=dining_info.getOrder_info().getPREARRANGE_NAME()%>"/>
       	<input type="hidden" name="tel" value="<%=dining_info.getOrder_info().getPREARRANGE_PHONE()%>"/>
       	<input type="hidden" name="weizhi" value="<%=dining_info.getSET_NO()%>"/>
       	<input type="hidden" name="id" value="<%=dining_info.getSF_ID()%>"/>
       	<input type="hidden" name="mincost_type" value="<%=dining_info.getMINCOST_TYPE()%>"/>
       	<input type="hidden" name="mincost_money" value="<%=dining_info.getMINCOST_MONEY()%>"/> 
       	<input type="hidden" name="bname" value="<%=dining_info.getBALCONY_NAME()%>"/>
       	<input type="submit" value="订单修改"/>
     	</form>
     	</td>
     	</tr>
     	</table>
     	
<%
  
 
       	
     	
        //订单信息
        %><div class="dingdanxinxi">
        <div class="ding">订单信息</div>
        <ul>
		<li>座位号：<%out.println(dining_info.getDINING_FLOOR()+"楼");%></li>
		<li>座位名称：<%out.println(dining_info.getBALCONY_NAME());%></li>
		<li>订单号：<%=dining_info.getOrder_info().getORDER_NO()%></li>
		<li>联系电话：<%out.println(dining_info.getOrder_info().getPREARRANGE_PHONE());%></li>
	    <li>订单时间：<%out.println(dining_info.getOrder_info().getPREARRANGE_ORDER_TIME());%></li>
	    <li>预定人：  <%out.println(dining_info.getOrder_info().getPREARRANGE_NAME());%></li>
	    <li>预定人数:<%out.println(dining_info.getOrder_info().getPREARRANGE_MAN_COUNT());%></li>
		</ul>
		</div>
		
		 <% //菜信息
        Order_list order_list;  
		List list1=new ArrayList();
		list1=(List)request.getAttribute("dingdanxinxi"); 
		if(list1.size()!=0){  %>
        <div class="dingdanmingxi">
        <div class="ding">订单明细</div> 
        <table><tr><td>菜品名称</td>&nbsp<td>数量</td>&nbsp<td>菜品标志</td>&nbsp<td>是否上菜</td>&nbsp<td>当前价格</td></tr><%
		for(int i=0;i<list1.size();i++)
		{
		order_list=(Order_list)list1.get(i);
		%><tr><td><%out.println(order_list.getFood_info().getFOOD_NAME());%></td>
		<td><%out.println(order_list.getFOOD_COUNT());%></td>
		<td><%if(order_list.getFOOD_ACTION().equals("1")){out.println("点菜");}else if(order_list.getFOOD_ACTION().equals("2")){out.println("退菜");}else{out.println("取消");}%></td>
		<td><%if(order_list.getSERVING_FLAG().equals("0")){out.print("未上菜");}else{out.print("已上菜");}%></td>
		<td><%out.println(order_list.getFood_info().getFOOD_PRICE());%></td></tr><%
		}
        %>
        </table>
        </div>
        <%
        }
		
    }
    else if(zhuangtai.equals("3"))
    {out.print("没有这个信息，请重新输入！");}
    %>
    </div>  
    
    
    <!-- <a href ="" onClick="parent.history.back(); return false;" onMouseOver="self.status='后退' ;return true;">后退</a>  --> 
	<!-- 将SRC后的图片改为你的向前的图片名-->                  
	
  </body>
</html>
