<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	

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
           
            var caidan=document.getElementById("memu");
            var mens=xml.getElementsByTagName("mens")[0];
            // alert(mens.tagName);
            var menu=mens.getElementsByTagName("menu")
            for(var i=0; i<menu.length; i++)
            {
             
             
              if(menu[i].firstChild.nodeValue=="01")
              {
               var p=document.createElement("p");
                   p.innerHTML="大厅";
                   caidan.appendChild(p);
              }
              if(menu[i].firstChild.nodeValue=="02")
              {
               var p=document.createElement("p");
                   p.innerHTML="包厢";
                   caidan.appendChild(p);
              }
            }    
         }
     }
  } 	
  
 
</script>
  </head>
  
  <body onload="weizhi();">
    This is my JSP page. <br>
    <div id="memu">
    <p></p>
    <a href="../hotel">odododo</a>
    </div>
  </body>
</html>
