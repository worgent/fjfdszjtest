<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" import="java.util.*"import="com.apricot.app.bean.dining_set_info" errorPage="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<script>

 function createXMLHttpRequest(){                           //创建HTTP对象
       if(window.ActiveXObject){
	    xmlHttp=new ActiveXObject("Microsoft.XMLHttp");
		
	   }
    else if(window.XMLHttpRequest){
	    xmlHttp=new XMLHttpRequest();
	   }
  }	

function totijiao(str)
{  
  var id=str; 
   createXMLHttpRequest();
    xmlHttp.onreadystatechange=getResult;
   xmlHttp.open("get","getmenu?orderid="+id,true);
   xmlHttp.send(null);

}
function ciping()
{
  var caidan=document.getElementById("caidan");
  while(caidan.firstChild!=null)caidan.removeChild(caidan.firstChild);
}
function getResult(){
 if(xmlHttp.readyState==4)
	 {  
       if(xmlHttp.status==200 || xmlHttp.status==202)
    
       {      //当有结果时 显示信息
           var xml=xmlHttp.responseXML;
           
           var caidan=document.getElementById("caidan");
          while(caidan.firstChild!=null)caidan.removeChild(caidan.firstChild);
          
                  var mens=xml.getElementsByTagName("mens")[0];
           var menu=mens.getElementsByTagName("menu");
           
           if(menu==null||menu.length==0)
           
           {
               var p=document.createElement("p");
              p.innerHTML="没有选定菜单";
            caidan.appendChild(p);
           }
           
           else
           {
              var p=document.createElement("p");
              p.innerHTML="已选菜单";      
            caidan.appendChild(p);
            var top=document.createElement("p");
              top.innerHTML=" "+"菜名"+" "+" "+" "+"价格"+" "+"数量";
               caidan.appendChild(top);
            for(var i=0; i<menu.length; i++)
            {
               
              var p=document.createElement("p");
              p.innerHTML=menu[i].firstChild.nodeValue;
             // alert(menu[i].firstChild.nodeValue)
              caidan.appendChild(p);
              //var food_number=document.createElement("p");
              //food_number=menu
            }
            }
         }
     }
  }
          
  
  
function tomessage(obj)
{
  
   var message=document.getElementById("getmessage");
   var louceng=obj.getElementsByTagName("input")[0];
   var zuoweihao=obj.getElementsByTagName("input")[1];
   var renshu=obj.getElementsByTagName("input")[3];
   var balcony_name=obj.getElementsByTagName("input")[2];
   var bianhao=obj.getElementsByTagName("input")[4];
   var phone=obj.getElementsByTagName("input")[5];
   var PREARRANGE_NAME=obj.getElementsByTagName("input")[6];
   var PREARRANGE_MAN_COUNT=obj.getElementsByTagName("input")[7];
   var dingdanTime=obj.getElementsByTagName("input")[8];
   var yudingshijian=obj.getElementsByTagName("input")[9];
    message["louceng"].value=louceng.value;
    message["zuoweihao"].value=zuoweihao.value;
    message["renshu"].value=renshu.value;
    message["balcony_name"].value=balcony_name.value; 
    message["bianhao"].value=bianhao.value;
    message["phone"].value=phone.value;
    message["PREARRANGE_NAME"].value=PREARRANGE_NAME.value;
    message["PREARRANGE_MAN_COUNT"].value=PREARRANGE_MAN_COUNT.value;
    message["dingdanTime"].value=dingdanTime.value;
    message["yudingshijian"].value=yudingshijian.value;   
}

function rightrootclick(ev,obj)
  {
     
     var e = ev|| window.event;
     var d=document.createElement("div"),css="width:300px; height:400px; position:absolute; z-index:200; background:#CCCCCC; border:1px solid #0aa";
      d.style.cssText = css;
   d.setAttribute('style',css);
   var st = document.documentElement.scrollTop || document.body.scrollTop, sl = document.documentElement.scrollLeft || document.body.scrollLeft;
   var left = e.clientX +sl +17 , top = e.clientY + st + 17;
    d.style.left=left+10+"px";
    d.style.top=top+10+"px";
   
    var p=document.createElement("p");
     if(p.addEventListener)
        p.addEventListener('click',clickme,false);
        else if(p.attachEvent)
        {
          p.attachEvent('onclick',clickme);
        }
        
     p.innerHTML="<a href=''>订单修改</a><br/><a href='lin/yuding.jsp?weizhi=12' target='main'>下单</a><br/><a href=''>点菜</a><br/><a href=''>催菜</>";
     d.appendChild(p);
     
     
     
        
     
     
     
     document.body.appendChild(d);
    
  
  }
  
  function clickme()
  {
    alert("取消右键的层");
  
  }
  
  function exit()
  {
   alert("取消右键的层");
  }
function wsug(e, str){ // http://www.hansir.cn
 var oThis = arguments.callee;
 if(!str) {
  oThis.sug.style.visibility = 'hidden';
  document.onmousemove = null;
  return;
 }  
 if(!oThis.sug){
  var div = document.createElement('div'), css = 'top:0; left:0; position:absolute; z-index:100; visibility:hidden';
   div.style.cssText = css;
   div.setAttribute('style',css);
  var sug = document.createElement('div'), css= 'font:normal 40px/40px "宋体"; white-space:nowrap; color:#666; padding:3px; position:absolute; left:0; top:0; z-index:10; background:#f9fdfd; border:1px solid #0aa';
   sug.style.cssText = css;
   sug.setAttribute('style',css);
  var dr = document.createElement('div'), css = 'position:absolute; top:3px; left:3px; background:#333; filter:alpha(opacity=50); opacity:0.5; z-index:9';
   dr.style.cssText = css;
   dr.setAttribute('style',css);
  var ifr = document.createElement('iframe'), css='position:absolute; left:0; top:0; z-index:8; filter:alpha(opacity=0); opacity:0';
   ifr.style.cssText = css;
   ifr.setAttribute('style',css);
  div.appendChild(ifr);
  div.appendChild(dr);
  div.appendChild(sug);
  div.sug = sug;
  document.body.appendChild(div);
  oThis.sug = div;
  oThis.dr = dr;
  oThis.ifr = ifr;
  div = dr = ifr = sug = null;
 }
 var e = e || window.event, obj = oThis.sug, dr = oThis.dr, ifr = oThis.ifr;
 obj.sug.innerHTML = str;
 
 var w = obj.sug.offsetWidth, h = obj.sug.offsetHeight, dw = document.documentElement.clientWidth||document.body.clientWidth; dh = document.documentElement.clientHeight || document.body.clientHeight;
 var st = document.documentElement.scrollTop || document.body.scrollTop, sl = document.documentElement.scrollLeft || document.body.scrollLeft;
 var left = e.clientX +sl +17 + w < dw + sl  &&  e.clientX + sl + 15 || e.clientX +sl-8 - w, top = e.clientY + st + 17;
 obj.style.left = left+ 10 + 'px';
 obj.style.top = top + 10 + 'px';
 dr.style.width = w + 'px';
 dr.style.height = h + 'px';
 ifr.style.width = w + 3 + 'px';
 ifr.style.height = h + 3 + 'px';
 obj.style.visibility = 'visible';
 document.onmousemove = function(e){
  var e = e || window.event, st = document.documentElement.scrollTop || document.body.scrollTop, sl = document.documentElement.scrollLeft || document.body.scrollLeft;
  var left = e.clientX +sl +17 + w < dw + sl  &&  e.clientX + sl + 15 || e.clientX +sl-8 - w, top = e.clientY + st +17 + h < dh + st  &&  e.clientY + st + 17 || e.clientY + st - 5 - h;
  obj.style.left = left + 'px';
  obj.style.top = top + 'px';
 }
}

 function hideDiv(x){
     //alert("你好");
     var i="ib"+x;
     $("#"+i).hide(1000);
     //$("#myDiv").hide(1000);
   }
   function showDiv(x){
     //alert("你好");
     var i="ib"+x;
        // alert(i);
    $("#"+i).show(1000);
 
   }
</script>
<script type="text/javascript" src="js/jquery-1[1].3.2.min.js"></script>
<style type="text/css">
  body {
    color:              #666;
    FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#D4EBF3,endColorStr=#E8F7FC);
    font-family: 		Arial, Helvetica, Geneva, sans-serif;
    font-weight:        normal;
    font-size:          12px;
}
.main{
            width:77%;
            float:left;
            border:1px solid #A5A5A5;
            } 
.right{float:left;width:35;  border:1px solid #A5A5A5; padding-left:30px;}          
.i{float:left;padding:2px; height:130px; margin-buttom:100px;}
.a{height:130px;  color: #666;float:left;}             
.ij1{float:left;background-color:#32B06D;width:80px; height:120px; color:white;font-size:13px;}
.ij2{float:left;background-color:#32B06D;width:80px; height:120px; color:white;font-size:13px;}
.ij3{float:left;background-color:#32B06D;width:80px; height:120px; color:white;font-size:13px;}
.ib{float:left;color:#000000; border:2px solid orange;display:none;z-index:2;}
li{padding:5px;}//功能的宽度
a{color:#000000;}
.niu{}
#niu{background-color:#D5ECF3;border:0px}
.topage{margin-top:400px;color:red;}
img.thumb {
    border-width:		5px;
    border-color:       #666;
    border-style:       solid;
}
img {
    border-width:   0;
    margin:         0;
}
img.thumb:hover { border-color: #600 }
</style>
</head>
	
<body oncontextmenu="return false">
<jsp:useBean id="dining" scope="page" class="com.apricot.app.bean.dining_set_info"/>
<div class="main" id="mainDiv">
<%
  dining_set_info dining_info;
  List list=new ArrayList();
  list=(List)request.getAttribute("dininglist");
       String ORDER_STATUS= (String)request.getAttribute("ORDER_STATUS");
		String BELONG_TO=(String)request.getAttribute("BELONG_TO");
		String pretime=(String)request.getAttribute("pretime");
  
  int pageCount=4;
  int Number=list.size();
  System.out.println("Number="+Number);
  int pageNum=(pageCount+Number-1)/pageCount;
  System.out.println("PageNum等于"+pageNum);
  String nowPage=(String)request.getAttribute("getPage");
  int thisPage;
  if(nowPage.equals("")||nowPage==null)
  thisPage=1;
  else
   thisPage=Integer.parseInt(nowPage);
     
  if(thisPage<1) thisPage=1;  
  if(thisPage>pageNum) thisPage=pageNum; 
     
  int begin=(thisPage-1)*pageCount;
for(int i=begin;i<begin+pageCount;i++)
{
  if(i<Number){
dining_info=(dining_set_info)list.get(i);
if(dining_info.getStatus()==0)
{ 
%>

<div class="i">
    <div class="a">
    <div class="ij1" onclick="showDiv('<%=i%>');tomessage(this.parentNode); ciping();this.style.cursor='hand';">
               <strong><%=dining_info.getDINING_FLOOR() %>楼&nbsp&nbsp<%=dining_info.getBALCONY_NAME()%><br/>
           容纳人数:&nbsp<%=dining_info.getSET_MAX()%><br/>
           最低消费:<br/>
    <%
      String type=dining_info.getMINCOST_TYPE();
      if(type.equals("1")){
        out.print("人均:");
        String money=dining_info.getMINCOST_MONEY();
        out.print(dining_info.getMINCOST_MONEY());
        }
     else if(type.equals("2")){
        out.print("总金额");
        String money=dining_info.getMINCOST_MONEY();
        out.print(dining_info.getMINCOST_MONEY());
     }
     else{
     out.print("无");
     }
     %>
     &nbsp
    <br/>
状态：空闲</strong>
	<!-- <img src="lin/img/zhuozi.jpg" class="obj1" width="120" height="120"  onclick="tomessage(this.parentNode); ciping();" onmouseout="wsug(event, 0)" onmouseover="wsug(event, '<%=dining_info.getDINING_FLOOR() %>楼<%=dining_info.getSET_NO()%>号<%=dining_info.getBALCONY_NAME()%>容纳人数:<%=dining_info.getSET_MAX()%>'); this.style.cursor='hand'" onmousedown="if(event.button == 0||event.button==2) rightrootclick(event,this);"/> -->
    <input type="hidden"       value="<%=dining_info.getDINING_FLOOR()%>"/>
    <input type="hidden"       value="<%=dining_info.getSET_NO()%>"/>
    <input type="hidden"       value="<%=dining_info.getBALCONY_NAME()%>"/>
    <input type="hidden"       value="<%=dining_info.getSET_MAX()%>"/>
    <input type="hidden"       value=""/>
    <input type="hidden"       value=""/>
    <input type="hidden"       value=""/>
    <input type="hidden"       value=""/>
    <input type="hidden"       value=""/>
    <input type="hidden"       value=""/>
    </div>
    <div class="ib" id="ib<%=i%>">
	<li><a href="lin/yuyue.jsp?weizhi=<%=dining_info.getSET_NO()%>&id=<%=dining_info.getSF_ID()%>&bname=<%=dining_info.getBALCONY_NAME()%>&MINCOST_MONEY=<%=dining_info.getMINCOST_MONEY() %>&MINCOST_TYPE=<%=dining_info.getMINCOST_TYPE()%>" target="blank">预约</a></li>
	<li><a href="lin/yuding.jsp?weizhi=<%=dining_info.getSET_NO()%>&id=<%=dining_info.getSF_ID()%>&bname=<%=dining_info.getBALCONY_NAME()%>&mincost_type=<%=dining_info.getMINCOST_TYPE()%>&mincost_money=<%=dining_info.getMINCOST_MONEY()%>" target="blank">下单</a></li>
    <li><a href="#" onclick="hideDiv('<%=i%>')">隐藏</a></li>
    </div>
    </div>
     
</div>
<%
}
if(dining_info.getStatus()==1)
{%> 
<div class="i">
    <div class="a">
	<div class="ij2" onclick="tomessage(this.parentNode); ciping(); totijiao('<%=dining_info.getOrder_info().getORDER_ID()%>');this.style.cursor='hand';">
	 <strong><%=dining_info.getDINING_FLOOR() %>楼&nbsp&nbsp<%=dining_info.getBALCONY_NAME()%><br/>
	 容纳人数:<%=dining_info.getSET_MAX()%><br/>
	  最低消费:<br/>
    <%
      String type=dining_info.getMINCOST_TYPE();
      if(type.equals("1")){
        out.print("人均:");
        String money=dining_info.getMINCOST_MONEY();
        out.print(dining_info.getMINCOST_MONEY());
        }
     else if(type.equals("2")){
        out.print("总金额");
        String money=dining_info.getMINCOST_MONEY();
        out.print(dining_info.getMINCOST_MONEY());
     }
     else{
     out.print("无");
     }
     %>
   
	
	<!-- <img src="lin/img/set/set_busy.gif" class="obj1" width="120" height="120" onclick="tomessage(this.parentNode); totijiao('<%=dining_info.getOrder_info().getORDER_ID()%>');" onmouseout="wsug(event, 0)" onmouseover="wsug(event, '<%=dining_info.getDINING_FLOOR() %>楼<%=dining_info.getSET_NO()%>号 <%=dining_info.getBALCONY_NAME()%>容纳人数:<%=dining_info.getSET_MAX()%>'); this.style.cursor='hand'" onmousedown="if(event.button == 0||event.button==2) rightrootclick(event,this);"/> -->
	 <input type="hidden"      value="<%=dining_info.getDINING_FLOOR() %>"/>
    <input type="hidden"       value="<%=dining_info.getSET_NO()%>"/>
    <input type="hidden"       value="<%=dining_info.getBALCONY_NAME()%>"/>
    <input type="hidden"       value="<%=dining_info.getSET_MAX()%>"/>
    <input type="hidden"       value="<%=dining_info.getOrder_info().getORDER_NO()%>"/>
    <input type="hidden"       value="<%=dining_info.getOrder_info().getPREARRANGE_PHONE()%>"/>
    <input type="hidden"       value="<%=dining_info.getOrder_info().getPREARRANGE_NAME()%>"/>
    <input type="hidden"       value="<%=dining_info.getOrder_info().getPREARRANGE_MAN_COUNT() %>"/>
    <input type="hidden"       value="<%=dining_info.getOrder_info().getORDER_TIME()%>"/>
    <input type="hidden"       value="<%=dining_info.getOrder_info().getOPERATE_ORDER_TIME()%>"/>
 	</div>
    <div class="ib" >
    <!-- <li><a href="lin/yuyue.jsp?weizhi=<%=dining_info.getSET_NO()%>&id=<%=dining_info.getSF_ID()%>&bname=<%=dining_info.getBALCONY_NAME()%>&MINCOST_MONEY=<%=dining_info.getMINCOST_MONEY() %>&MINCOST_TYPE=<%=dining_info.getMINCOST_TYPE()%>" target="blank">预约座位</a></li> -->
	<li><a href="/apricot_ed/servlet/diancai1?set_id=<%=dining_info.getSF_ID()%>&order_id=<%=dining_info.getOrder_info().getORDER_ID() %>" target="blank">点菜</a></li>
	<li><a href="/apricot_ed/servlet/shangcai?set_id=<%=dining_info.getSF_ID()%>&order_id=<%=dining_info.getOrder_info().getORDER_ID() %>"target="blank">上菜</a></li>
	<li><a href="/apricot_ed/servlet/tuicai?set_id=<%=dining_info.getSF_ID()%>&order_id=<%=dining_info.getOrder_info().getORDER_ID() %>"target="blank">退菜</a></li>
	<li><a href="Scheduled.jsp" target="main">催菜</a></li>
    </div>
    </div>
   
 
    
</div>
<%
}
if(dining_info.getStatus()==2)
{ 
%>

<div class="i">
    <div class="a">
    <div class="ij3" onclick="tomessage(this.parentNode); ciping(); totijiao('<%=dining_info.getOrder_info().getORDER_ID()%>');"  this.style.cursor='hand'";">
     <strong><%=dining_info.getDINING_FLOOR() %>楼&nbsp&nbsp<%=dining_info.getBALCONY_NAME()%><br/>
           容纳人数:<%=dining_info.getSET_MAX()%><br/>
	  最低消费:<br/>
    <%
      String type=dining_info.getMINCOST_TYPE();
      if(type.equals("1")){
        out.print("人均:");
        String money=dining_info.getMINCOST_MONEY();
        out.print(dining_info.getMINCOST_MONEY());
        }
     else if(type.equals("2")){
        out.print("总金额");
        String money=dining_info.getMINCOST_MONEY();
        out.print(dining_info.getMINCOST_MONEY());
     }
     else{
     out.print("无");
     }
     %>
     &nbsp
	<!--  <img src="lin/img/set/set_free.gif" class="obj1" width="120" height="120" onclick="tomessage(this.parentNode); totijiao('<%=dining_info.getOrder_info().getORDER_ID()%>');" onmouseout="wsug(event, 0)" onmouseover="wsug(event, '<%=dining_info.getDINING_FLOOR() %>楼<%=dining_info.getSET_NO()%>号<%=dining_info.getBALCONY_NAME()%>容纳人数:<%=dining_info.getSET_MAX()%>'); this.style.cursor='hand'" onmousedown="if(event.button == 0||event.button==2) rightrootclick(event,this);"/>-->
     <input type="hidden"     value="<%=dining_info.getDINING_FLOOR() %>"/>
    <input type="hidden"      value="<%=dining_info.getSET_NO()%>"/>
    <input type="hidden"      value="<%=dining_info.getBALCONY_NAME()%>"/>
    <input type="hidden"      value="<%=dining_info.getSET_MAX()%>"/>
    <input type="hidden"      value="<%=dining_info.getOrder_info().getORDER_NO()%>"/>
    <input type="hidden"      value="<%=dining_info.getOrder_info().getPREARRANGE_PHONE()%>"/>
    <input type="hidden"      value="<%=dining_info.getOrder_info().getPREARRANGE_NAME()%>"/>
    <input type="hidden"      value="<%=dining_info.getOrder_info().getPREARRANGE_MAN_COUNT() %>"/>
    <input type="hidden"      value="<%=dining_info.getOrder_info().getORDER_TIME()%>"/>
    <input type="hidden"      value="<%=dining_info.getOrder_info().getOPERATE_ORDER_TIME()%>"/>
    </div>
    <div class="ib">
    <li><form action="lin/xiugaidingdan.jsp">
	<input type="hidden"   name="xiadantime" value="<%=dining_info.getOrder_info().getORDER_TIME()%>"/>
    <input type="hidden"   name="ORDER_NO"   value="<%=dining_info.getOrder_info().getORDER_NO()%>"/>
    <input type="hidden"   name="MAN_COUNT"  value="<%=dining_info.getOrder_info().getMAN_COUNT()%>"/>
    <input type="hidden"   name="PREARRANGE_ORDER_TIME"  value="<%=dining_info.getOrder_info().getPREARRANGE_ORDER_TIME()%>"/>
    <input type="hidden"   name="OPERATE_ORDER_TIME"  value="<%=dining_info.getOrder_info().getOPERATE_ORDER_TIME()%>"/>
    <input type="hidden"   name="ORDER_TYPE"  value="<%=dining_info.getOrder_info().getORDER_TYPE()%>"/>
    <input type="hidden"   name="vipcard"     value="<%=dining_info.getOrder_info().getVIP_CARD_NO()%>"/>
    <input type="hidden"   name="prenumber"   value="<%=dining_info.getOrder_info().getPREARRANGE_MAN_COUNT()%>"/>
    <input type="hidden"   name="tel"         value="<%=dining_info.getOrder_info().getPREARRANGE_PHONE()%>"/>
    <input type="hidden"   name="weizhi"      value="<%=dining_info.getSET_NO()%>"/>
    <input type="hidden"  name="id"          value="<%=dining_info.getSF_ID()%>"/>
    <input type="hidden"   name="bname"       value="<%=dining_info.getBALCONY_NAME()%>"/>
    <input type="hidden"   name="mincost_type"    value="<%=dining_info.getMINCOST_TYPE()%>"/>
    <input type="hidden"   name="mincost_money"   value="<%=dining_info.getMINCOST_MONEY()%>"/>
    <input type="hidden"   name="prename"         value="<%=dining_info.getOrder_info().getPREARRANGE_NAME()%>"/>
	<input  id="niu" class="niu" type="submit" value="修改订单"/>
	</form></li>
	
	<li><form action="lin/kaidan.jsp">
	<input type="hidden"    name="ORDER_NO"     value="<%=dining_info.getOrder_info().getORDER_NO()%>"/>
    <input type="hidden"    name="MAN_COUNT"    value="<%=dining_info.getOrder_info().getMAN_COUNT()%>"/>
    <input type="hidden"    name="PREARRANGE_ORDER_TIME"  value="<%=dining_info.getOrder_info().getPREARRANGE_ORDER_TIME()%>"/>
    <input type="hidden"    name="OPERATE_ORDER_TIME"    value="<%=dining_info.getOrder_info().getOPERATE_ORDER_TIME()%>"/>
    <input type="hidden"    name="ORDER_TYPE"    value="<%=dining_info.getOrder_info().getORDER_TYPE()%>"/>
    <input type="hidden"    name="vipcard"      value="<%=dining_info.getOrder_info().getVIP_CARD_NO()%>"/>
    <input type="hidden"    name="prenumber"    value="<%=dining_info.getOrder_info().getPREARRANGE_MAN_COUNT()%>"/>
    <input type="hidden"    name="tel"    value="<%=dining_info.getOrder_info().getPREARRANGE_PHONE()%>"/>
    <input type="hidden"    name="weizhi"   value="<%=dining_info.getSET_NO()%>"/>
    <input type="hidden"    name="id"   value="<%=dining_info.getSF_ID()%>"/>
    <input type="hidden"    name="bname"   value="<%=dining_info.getBALCONY_NAME()%>"/>
    <input type="hidden"    name="mincost_type"   value="<%=dining_info.getMINCOST_TYPE()%>"/>
    <input type="hidden"    name="mincost_money"   value="<%=dining_info.getMINCOST_MONEY()%>"/>
    <input type="hidden"    name="prename"   value="<%=dining_info.getOrder_info().getPREARRANGE_NAME()%>"/>
    <input  class="niu" id="niu" type="submit" value="开单"/>
	</form></li>
	<li><a style="color:#000000" href="/apricot_ed/servlet/diancai?set_id=<%=dining_info.getSF_ID()%>&order_id=<%=dining_info.getOrder_info().getORDER_ID() %>" target="blank">点菜</a></li>
	<!-- 
	<li><a href="lin/kaidan.jsp?ORDER_NO=<%=dining_info.getOrder_info().getORDER_NO()%>&MAN_COUNT=<%=dining_info.getOrder_info().getMAN_COUNT()%>&PREARRANGE_ORDER_TIME=<%=dining_info.getOrder_info().getPREARRANGE_ORDER_TIME()%>&OPERATE_ORDER_TIME=<%=dining_info.getOrder_info().getOPERATE_ORDER_TIME()%>&ORDER_TYPE=<%=dining_info.getOrder_info().getORDER_TYPE()%>&vipcard=<%=dining_info.getOrder_info().getVIP_CARD_NO()%>&prenumber=<%=dining_info.getOrder_info().getPREARRANGE_MAN_COUNT()%>&tel=<%=dining_info.getOrder_info().getPREARRANGE_PHONE()%>&weizhi=<%=dining_info.getSET_NO()%>&id=<%=dining_info.getSF_ID()%>&bname=<%=dining_info.getBALCONY_NAME()%>&mincost_type=<%=dining_info.getMINCOST_TYPE()%>&mincost_money=<%=dining_info.getMINCOST_MONEY()%>&prename=<%=dining_info.getOrder_info().getPREARRANGE_NAME()%>" target="blank">客户下单</a></li> 
	<li><a href="lin/xiugaidingdan.jsp?xiadantime=<%=dining_info.getOrder_info().getORDER_TIME()%>&ORDER_NO=<%=dining_info.getOrder_info().getORDER_NO()%>&MAN_COUNT=<%=dining_info.getOrder_info().getMAN_COUNT()%>&PREARRANGE_ORDER_TIME=<%=dining_info.getOrder_info().getPREARRANGE_ORDER_TIME()%>&OPERATE_ORDER_TIME=<%=dining_info.getOrder_info().getOPERATE_ORDER_TIME()%>&ORDER_TYPE=<%=dining_info.getOrder_info().getORDER_TYPE()%>&vipcard=<%=dining_info.getOrder_info().getVIP_CARD_NO()%>&prenumber=<%=dining_info.getOrder_info().getPREARRANGE_MAN_COUNT()%>&tel=<%=dining_info.getOrder_info().getPREARRANGE_PHONE()%>&weizhi=<%=dining_info.getSET_NO()%>&id=<%=dining_info.getSF_ID()%>&bname=<%=dining_info.getBALCONY_NAME()%>&mincost_type=<%=dining_info.getMINCOST_TYPE()%>&mincost_money=<%=dining_info.getMINCOST_MONEY()%>&prename=<%=dining_info.getOrder_info().getPREARRANGE_NAME()%>" target="blank">订单修改</a></li>
	<li><a href="lin/yuyue.jsp?weizhi=<%=dining_info.getSET_NO()%>&id=<%=dining_info.getSF_ID()%>&bname=<%=dining_info.getBALCONY_NAME()%>&MINCOST_MONEY=<%=dining_info.getMINCOST_MONEY() %>&MINCOST_TYPE=<%=dining_info.getMINCOST_TYPE()%>" target="blank">预约座位</a></li>-->
    </div>
    </div>
   
</div>
<%
}

 %>
<%}}%>

<div class="topage" id="topage"> 
<%if(thisPage!=1){%>
<a href="time?getPage=<%=thisPage-1%>&ORDER_STATUS=<%=ORDER_STATUS%>&BELONG_TO=<%=BELONG_TO%>&pretime=<%=pretime%>"> 上一页</a>
<%}
for(int i=1 ;i<=pageNum; i++){
  %> 
      <a href="time?getPage=<%=i%>&ORDER_STATUS=<%=ORDER_STATUS%>&BELONG_TO=<%=BELONG_TO%>&pretime=<%=pretime%>"> <%=i%> </a>
  <%
} 
if(thisPage!=pageNum){%>
<a href="time?getPage=<%=thisPage+1%>&ORDER_STATUS=<%=ORDER_STATUS%>&BELONG_TO=<%=BELONG_TO%>&pretime=<%=pretime%>"> 下一页</a>
<%} %>
</div>

</div>



<div id="message" class="right">
<form action="" id="getmessage">
<table>
  <tr>
      <td>楼层</td>
      <td>座位号</td>
  </tr> 
  <tr>
		<td><input type="text" name="louceng" size="5" readonly="true"/></td>
		<td><input type="text" name="zuoweihao" size="5" readonly="true"/></td>
   </tr>
    <tr>
      <td>座位名称</td>
      <td>容纳人数</td>
  </tr> 
    <tr>
      <td><input type="text" name="balcony_name" size="5" readonly="true"/></td>
      <td><input type="text" name="renshu" size="5" readonly="true"/></td>
  </tr>
  <tr>
		<td colspan="4">订单号</td>
   </tr>
    <tr>
		<td colspan="4"><input type="text" name="bianhao" size="17" readonly="true"/></td>
   </tr>
    <tr>
        <td>预抵时间</td>
     </tr>
     <tr>
        <td colspan="4"><input type="text" name="yudingshijian" size="17" readonly="true"/></td>
     </tr>
    <tr>
		<td colspan="4">下单时间</td>
   </tr>
    <tr>
		<td colspan="4"><input type="text" name="dingdanTime" size="17" readonly="true"/></td>
   </tr>
   <tr>
		<td colspan="4">联系电话</td>
   </tr>
    <tr>
		<td colspan="4"><input type="text" name="phone" size="17" readonly="true"/></td>
   </tr>
    <tr>
      <td>预订人</td>
      <td>预定人数</td>
  </tr> 
  <tr>
		<td><input type="text" name="PREARRANGE_NAME" size="5" readonly="true"/></td>
		<td><input type="text" name="PREARRANGE_MAN_COUNT" size="5" readonly="true"/></td>
   </tr>
</table>
<hr/>
</form>
<div id="caidan"> 
<p>已选菜单</p>
</div>
</div>
</body>
</html>