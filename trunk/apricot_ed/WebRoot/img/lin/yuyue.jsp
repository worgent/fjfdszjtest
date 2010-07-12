<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" import="java.util.*"import="com.apricot.app.bean.*" errorPage="" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>预订</title> 
   
	<script type="text/javascript" src="../Calender/WdatePicker.js"></script>
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
  
  var XMLHttp;
	 function createXMLHttpRequests(){                           //创建HTTP对象
       if(window.ActiveXObject){
	    xmlHttp=new ActiveXObject("Microsoft.XMLHttp");	
	   }
    else if(window.XMLHttpRequest){
	    xmlHttp=new XMLHttpRequest();
	   }
  }
  
  function start(str)
{  
  var id=str; 
   createXMLHttpRequest();
    xmlH.onreadystatechange=getResult;  
    xmlH.open("get","../yuding_datetime?datetime="+id,true);
    xmlH.send(null);

}
  function select()
{   
    var year=document.getElementById("Arrivedtime1");
    var shi=document.getElementById("Arrivedtime3");
    var fen=document.getElementById("Arrivedtime4");
    var time=year.options.value+" "+shi.options.value+":"+fen.options.value+":00";
        createXMLHttpRequests();
    xmlHttp.onreadystatechange=getResults;  
    xmlHttp.open("get","../yuding_select_kongweizhi?fulltime="+time,true);
    xmlHttp.send(null);
   //alert(time);
}

function select_info(str)
{
 var set_no=str;
  createXMLHttpRequests();
    xmlHttp.onreadystatechange=getResultss;   
    xmlHttp.open("get","../select_dining_info_set_no?set_no="+set_no,true);
    xmlHttp.send(null);
}

function getResultss(){
 if(xmlHttp.readyState==4)
	 {  //alert(xmlH.status)
       if(xmlHttp.status==200 || xmlHttp.status==202)
       {      
       var xmlttp=xmlHttp.responseXML;
       var dinings=xmlttp.getElementsByTagName("dinings")[0];
       var dining=dinings.getElementsByTagName("dining");
       var Releasetime=document.getElementById("Releasetime");
       
         var MINCOST_TYPE=document.getElementById("MINCOST_TYPE");
            MINCOST_TYPE.value=dining[1].firstChild.nodeValue;
         var hightmoney=document.getElementById("hightmoney");
       		hightmoney.value=dining[2].firstChild.nodeValue;
         var balcony_name=document.getElementById("balcony_name");
       		 balcony_name.value=dining[0].firstChild.nodeValue;      		    
        }
      }
    } 
function getResults(){
 if(xmlHttp.readyState==4)
	 {  //alert(xmlH.status)
       if(xmlHttp.status==200 || xmlHttp.status==202)
       {      
        var xmlttp=xmlHttp.responseXML;
        var preweizhi=document.getElementById("preweizhi");  
        var dinings=xmlttp.getElementsByTagName("dinings")[0];
        var dining=dinings.getElementsByTagName("dining");
            //alert("22"+dining.length+"2222");
            document.getElementById("preweizhi").options.length=0; 
        for(var i=0;i<dining.length;i++)
       		 {
       		  var BALCONY_NAME=dining[i].getElementsByTagName("BALCONY_NAME");
       		  var BALCONY_NAME1=BALCONY_NAME[0].firstChild.nodeValue;
       		 //var str=Unicode2Str(BALCONY_NAME1);
       		  //alert("aaaa");
       		  BALCONY_NAME1=decodeURI(BALCONY_NAME1);
       		        var SET_NO=dining[i].getElementsByTagName("SET_NO");
       		  var SET_NO1=SET_NO[0].firstChild.nodeValue;
       		  //alert("SET_NO1="+SET_NO1);
       		 
       		 document.getElementById("preweizhi").options.add(new Option(BALCONY_NAME1,SET_NO1)); 
       		 }
          
        }
      }
    } 

function getResult(){
 if(xmlH.readyState==4)
	 {  //alert(xmlH.status)
       if(xmlH.status==200 || xmlH.status==202)
    
       {      
             var xml=xmlH.responseXML;
             var Arrivedtime3=document.getElementById("Arrivedtime3");
             var times=xml.getElementsByTagName("times")[0];
             var time=times.getElementsByTagName("time");
            // alert("time.length()"+time.length());
             document.getElementById("Arrivedtime3").options.length=0;   
            for(var i=0;i<time.length;i++)
            {
              document.getElementById("Arrivedtime3").options.add(new Option(time[i].firstChild.nodeValue+"点",time[i].firstChild.nodeValue)); 
            }
        }
      }
    }    
	</script>
 
  <script type="text/javascript">
  function kong(){
  
	var prename=document.getElementById("prename").value;
	
	var telephone=document.getElementById("telephone").value;
	var prenumber=document.getElementById("prenumber").value;
	var VIPcard=document.getElementById("VIPcard").value;
    var Operation=document.getElementById("Operation").value;
    var type=document.getElementById("type").options[0].value;
    var Operationtime=document.getElementById("Operationtime").value;
	var Arrivedtime3=document.getElementById("Arrivedtime3").options[0].value;
	var preweizhi=document.getElementById("preweizhi").value;
	var MINCOST_TYPE=document.getElementById("MINCOST_TYPE").options[0].value;
	var hightmoney=document.getElementById("hightmoney").value;
	if(prename==""||telephone==""||prenumber==""||VIPcard==""||Operation==""||Arrivedtime3==""||preweizhi==""||MINCOST_TYPE==""||hightmoney=="")
	{
	   alert("数据必须全部填写");
	        return false;
	}
	if(telephone.match(/[a-z]/ig))
    {
      alert("电话号码必须是数字")
      return false;
    }
    if(telephone.length>12)
    {
      alert("请输入正确的电话号码");
      return false;
    }
    if(prenumber.match(/[a-z]/ig))
    {
      alert("预约人数必须是数字")
      return false;
    }
    if(Releasetime.match(/[a-z]/ig))
     {
      alert("时间必须是数字")
      return false;
     }
	
	}
  </script>
 
  </head>  
  <body>
<jsp:useBean id="time" scope="page" class="com.apricot.app.bean.nowtime"/>
  <%
  request.setCharacterEncoding("gb2312");
String SET_NO=request.getParameter("weizhi");
String SF_ID=request.getParameter("id");
String balcony_name=request.getParameter("bname");
String MINCOST_MONEY=request.getParameter("MINCOST_MONEY");
 String MINCOST_TYPE=request.getParameter("MINCOST_TYPE");
//"转好后的名字"=new String(要转的名字.getBytes("ISO-8859-1"),"GB2312");
out.println("金额"+MINCOST_MONEY);
out.println("类型"+MINCOST_TYPE);
out.println(time.time());
out.println(balcony_name);
%>
   <form action="../yuding">
  <table>
   <tr>
      <td>联系人</td>
      <td>联系电话</td>
   </tr>
   <tr>
      <td><input name="prename" id="prename" type="text" /></td>
      <td><input name="telephone" id="telephone" type="text" /></td>
   </tr>
  
   <tr>
      <td>预约人数</td>
      <td>VIP卡号</td>
   </tr>
   <tr>
      <td><input name="prenumber" id="prenumber" type="text" /></td>
      <td><input name="VIPcard" id="VIPcard" type="text" /></td>
   </tr>
  <tr><td>
       预抵时间</td> 
   </tr>
   <tr><td colspan="2">
      <select name="Arrivedtime1" id="Arrivedtime1" style='width:90px' >
          <option value="<%=time.printtoday(time.simpletime())%>"><%=time.printtoday(time.simpletime())%></option>
          <option value="<%=time.printNextTime(time.simpletime())%>"><%=time.printNextTime(time.simpletime())%></option>
          <option value="<%=time.printhoutian(time.printNextTime(time.simpletime()))%>"><%=time.printhoutian(time.printNextTime(time.simpletime()))%></option>
		 </select>
			<select name="Arrivedtime2" onchange="start(this.value)" style='width:60px'>
          <option value="47">上午</option>
          <option value="46">下午</option>
			</select>
			<select name="Arrivedtime3" id="Arrivedtime3">
			<option value="11">11点</option>
			<option value="12">12点</option>
			<option value="13">13点</option>
			<option value="14">14点</option>
			</select>
			<select name="Arrivedtime4" id="Arrivedtime4">
            <option value="00">00分</option>
            <option value="10">10分</option>
            <option value="20">20分</option>
            <option value="30">30分</option>
            <option value="40">40分</option>
            <option value="50">50分</option>
			</select><button onclick="select();">查询</button></td>
   </tr>
      <tr>
         <td>预定座位</td>
         <td>订单类型</td>
      </tr>
      <tr>
         <td><select name="preweizhi" id="preweizhi" onchange="select_info(this.value)" style='width:155px'>
                      <option value="<%=SET_NO%>"><%=balcony_name%></option>
		</select></td>
         <td><select name="type" id="type" style='width:155px'><option value="2">电话预订</option>
					  <option value="1">网上预订</option>
					  <option value="3">店面预订</option>
					  </select></td>
      </tr>
      <tr>
          <td>最低消费类型</td>
          <td>操作人员</td>
      </tr>
      <tr> 
          <td><select name="MINCOST_TYPE" id="MINCOST_TYPE"  style='width:155px'>
                            <%
                                              if(MINCOST_TYPE.equals("1")){ 
                                              %>
													<option value="1">人均</option>
													
												
													<%} 
											else{%>
											        <option value="2">总金额</option>
											       
													
											 <%} %>
						
		    </select></td>
          <td><select name="Operation" id="Operation" style='width:155px'>
					  <option value="2">Admin收银员</option>
					  <option value="3">收银员1</option>
					  <option value="4">收银员2</option>
					  </select></td>
      </tr>
    <tr>
      <td>最低消费金额</td>
      <td>操作时间</td>
    </tr>
     <tr>
      <td><input name="hightmoney" id="hightmoney" type="text" value="<%=MINCOST_MONEY%>" readonly="true"/></td>
      <td><input name="Operationtime" id="Operationtime" type="text" readonly="true" value="<%out.println(time.time());%>"/></td>
    </tr>
     <tr>
        <td colspan="2">达到预抵时间后<input name="Releasetime"  id="Releasetime" type="text"  value="10" size="4"/>分钟自动释放</td>
     </tr>
  </table>
  
<input type="hidden" name="SET_NO" id="SET_NO" value="<%=SET_NO%>"/>
<input type="hidden" name="SF_ID"  value="<%=SF_ID%>"/>
<input type="hidden" name="balcony_name" id="balcony_name" value="<%=balcony_name%>"/>
<input name="" type="submit" value="保存" onclick="window.close();return kong();"/>
<button onclick="window.close()">退出</button>
</form>
  </body>
</html>
