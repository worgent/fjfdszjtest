<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
 .ding{background:url(phone_img/12.gif);color:white; height:25px;padding-top:4px;text-align:center;}
 .shou{background:#ccc;text-align:left;}
 .logo{margin-top:30%}
.anniu{color:white;background:url(phone_img/12.gif);width:80px;height:35px;}
 </style>
  <body>
  <%
  String name=(String)request.getAttribute("name");
 
   %>
   <div class="shou">
        <div class=ding>点菜系统</div>
    	欢迎你<%out.print(name);%>
    	<br>
    	<form id="form" name="form" method="post" action="/apricot_ed/servlet/chexunjieguo">
    	查询餐位名称：
    	<input name="denglu_name"  id="txt" type="text" size="10" onkeyup="search(this,'suggest')">
    	<input class="anniu" type="submit" value="确定"><br>
    	<div id="suggest" style="display:none"></div>
    	</form>
    	
    	<form id="form1" name="form1" method="post" action="/apricot_ed/servlet/chexunjieguo">
    	<br>
    	查询餐位编码：
    	<input type="text" name="denglu_num" id="tet1" size="10" onkeyup="search(this,'suggest1')">
    	<input class="anniu" type="submit" value="确定"><br>
    	<div id="suggest1" style="display:none"></div>
    	</form>
    </div>
  </body>
</html>
