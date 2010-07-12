<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <jsp:useBean id="time" scope="page" class="com.apricot.app.bean.nowtime"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>shouye</title>
	
<link rel="stylesheet" type="text/css" href="sdmenu/sdmenu.css" />
<style type="text/css">
.i{font-size:18px;}
body{FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#D4EBF3,endColorStr=#E8F7FC);
background:url(picture/logon11.gif);
}
#obj{filter:revealTrans();}
</style>
<script type="text/javascript" src="sdmenu/sdmenu.js"></script>
	<script type="text/javascript">
	// <![CDATA[
	var myMenu;	
	window.onload = function() {
	    weizhi();
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	
	};
	// ]]>
	
	
		var XMLH;
	 function createXMLHttpRequest(){                           //创建HTTP对象
       if(window.ActiveXObject){
	    xmlH=new ActiveXObject("Microsoft.XMLHttp");
		
	   }
    else if(window.XMLHttpRequest){
	    xmlH=new XMLHttpRequest();
	   }
  }
  
  function weizhi()
{  
   createXMLHttpRequest();
    xmlH.onreadystatechange=getResult;
    
   xmlH.open("get","../hotel",true);
   xmlH.send(null);

}
 function getResult(){
 if(xmlH.readyState==4)
	 {  //alert(xmlH.status)
       if(xmlH.status==200 || xmlH.status==202)
    
       {      
            var xml=xmlH.responseXML;
            var caidan=document.getElementById("my_menu");
            var mens=xml.getElementsByTagName("mens")[0];
            // alert(mens.tagName);
            var menu=mens.getElementsByTagName("menu");
            for(var i=0; i<menu.length; i++)
            {
                   
              if(menu[i].firstChild.nodeValue=="01")
              {
                  var div=document.createElement("div");
                 var span=document.createElement("span");
                     span.innerHTML="大厅"; 
                 var a1=document.createElement("a");
                     a1.href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=0&BELONG_TO=01" 
                     a1.target="main";
                     a1.innerHTML="占座"; 
                     
                 var a2=document.createElement("a");
                     a2.href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=1&BELONG_TO=01" 
                     a2.target="main";
                     a2.innerHTML="空闲"; 
                     
                 var a3=document.createElement("a");
                     a3.href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=2&BELONG_TO=01" 
                     a3.target="main";
                     a3.innerHTML="预订";   
                                          
                    div.appendChild(span);
                     div.appendChild(a1);
                     div.appendChild(a2);
                     div.appendChild(a3);  
                   caidan.appendChild(div);
                   
                   
              }
              if(menu[i].firstChild.nodeValue=="02")
              {
                var div=document.createElement("div");
                var span=document.createElement("span");
                    span.innerHTML="包厢"; 
              var a1=document.createElement("a");
                     a1.href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=0&BELONG_TO=02" 
                     a1.target="main";
                     a1.innerHTML="占座"; 
                     
                 var a2=document.createElement("a");
                     a2.href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=1&BELONG_TO=02" 
                     a2.target="main";
                     a2.innerHTML="空闲"; 
                     
                 var a3=document.createElement("a");
                     a3.href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=2&BELONG_TO=02" 
                     a3.target="main";
                     a3.innerHTML="预订";  
                    
                      div.appendChild(span);
                     div.appendChild(a1);
                     div.appendChild(a2);
                     div.appendChild(a3); 
                   caidan.appendChild(div);
              }
            }
            //alert(caidan.lastChild.innerHTML);    
         }
     }
  } 			
</script>
  </head>
  <body>
   
<div id="left" >
   <div id="my_menu" class="sdmenu">
        <div>
         <span>全部座位</span>
         <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=0&BELONG_TO=00" target="main">占座</a>
		 <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=1&BELONG_TO=00" target="main">空闲</a>
		 <a href="../time?pretime=<%out.print(time.time());%>&ORDER_STATUS=2&BELONG_TO=00" target="main">预订</a>
      </div>
   </div>
	 </div>
  </body>
</html>