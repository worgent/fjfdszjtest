<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
<body>
<center>
<form  method='POST' name='form1' action='/archives/clientMsg.do'  id="form1">
   <table class="table6" width="100%" border="1" cellpadding="3" cellspacing="0">
   <tr>
   <td colspan="3"> 
       <strong>取件联系人</strong>
   </td>
   </tr>
    <tr>
	<td>
		<s:actionerror theme="ems" />
	</td>
	<td>
		<s:actionmessage theme="ems" />
	</td>
  </tr>
   <tr> 
   <td>联系人:</td>
   <td>
   <s:textfield id="pnameName"  name="search.pname" value="%{clientMsg.NAME}"></s:textfield>
   <font color="red">*</font>
   <div id="pnameNameTip"  style="display: inline "></div>
   </td>
   </tr> 
     
   <tr> 
   <td>手机:</td>
   <td>
   <s:textfield id="pmobileName"  name="search.pmobile" value="%{clientMsg.MOBILE}"></s:textfield>
   <font color="red">*</font>
   <div id="pmobileNameTip" style="display: inline "></div>
   </td>
   </tr>  
   
   <tr> 
   <td>固定电话:</td>
   <td>
   <s:textfield id="ptelName"  name="search.ptel" value="%{clientMsg.TEL}" title="(例:059512345678)" ></s:textfield>
   <font color="red">*</font>
   <div id="ptelNameTip" style="display: inline "></div>
   </td>
   </tr>

	
   <tr> 
   <td>单位名称:</td>
   <td>
   <s:textfield id="punitName"  name="search.punit" value="%{clientMsg.UNIT}"></s:textfield>
   <font color="red">*</font>
   <div id="punitNameTip" style="display: inline "></div>
   </td>
   </tr>
      
   <tr>
   <td colspan="2" align="center">
      <s:hidden value="%{clientMsg.ID}" name="search.pid" id="pid"></s:hidden>
      <s:hidden value="%{action}" name="action" id="action"></s:hidden>
      <input type="button" onclick="saveClient()" value="保存" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'"  />
							<input type="reset" id="btnreset" name="btnreset" value="重置" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
							<input type="button" onclick="javascript:closeClientPage();" value="关闭" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
	</td>
   </tr>
 </table>
</form>
</center>
 <script type="text/javascript">
  //s:select id="pclientbalance"  name="search.pclientbalance" list="clientbalanceList" headerKey="-1" headerValue="---请选择---" listKey="ID" listValue="NAME" key="ID">s:select
    //验证 $("main").ready(function(){
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
		$("#pnameName").formValidator({onshow:def_Showname,onfocus:def_Showname,oncorrect:""}).inputValidator({min:1,max:20,onerror:def_Errorname});
		$("#ptelName").formValidator({onshow:def_Showtel,onfocus:def_Showname,oncorrect:""}).regexValidator({regexp:"^$|^[0-9\\-]+$",onerror:def_Errortel});
		$("#pmobileName").formValidator({onshow:def_Showmobile,onfocus:def_Showmobile,oncorrect:""}).regexValidator({regexp:"^(13|15|18)[0-9]{9}$|^\\d{10,12}$",onerror:def_Errormobile});
	});

 	function closeClientPage() {
		  $("#clientDiv").html("") ;
		  $("#clientDiv").hide() ;
		}
 
 	function saveClient(){
 		
  			var pars="action="+$("#action").val()+"&search.pname="+encodeURIComponent($("#pnameName").val())+"&search.pareacode="+$("#pareacodeName").val()
  				+"&search.ptel="+$("#ptelName").val()+"&search.pmobile="+$("#pmobileName").val()+"&search.punit="+encodeURIComponent($("#punitName").val())+"&search.pid="+$("#pid").val();
  			//alert(pars);
  			//return false;
		  	$.get("/archives/clientMsg.do",pars,function(res){
   				$('#messageDiv').hide();
			    $('#messageDiv').html(res);
			    var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
			  	var codeid = jsonMsgObj.getCodeid();
			  	//alert(jsonMsgObj.getMessage());
			  	if (codeid == "0") {
	  				$.get("/archives/clientMsg.do",{action:'frame',date:new Date()},showResult1);
	  				function showResult1(data){
		  				$('#clientList').html(data); 
	  				}
			  	}
  			});
		}

	</script>
</body>
</html>