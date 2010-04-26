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
	<head>
		<script type="text/javascript">
		$(document).ready(function(){ 
			$("#myTable").tablesorter({ 
				headers: { 
					1: {sorter: false }
				}   
			}); 
		}); 
		</script>

<script type="text/javascript">
	     function passwordCorrect(obj){
				  var url ='<%=path%>/user.do?action=passwordCorrect&search.pusername='+document.getElementById("username").value+'&search.ppassword='+document.getElementById("ppassword").value;
					try{
						var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
						oXMLDom.async = false ;
						oXMLDom.load(url); 
						var root;
						if (oXMLDom.parseError.errorCode != 0) {
							var myErr = oXMLDom.parseError;
							return ;
						} else {
							root = oXMLDom.documentElement;
						}
						if (null != root){
							var rowSet = root.selectNodes("//check");
							var mgr=rowSet.item(0).selectSingleNode("value").text;
							if(mgr!=0){
							alert(mgr);
							return false;
							}
							else
							{
							
					    var newPassword=document.getElementById("newPassword").value;
					   	var rePassword=document.getElementById("rePassword").value;
					
						if(newPassword==''){
						 alert('新密码不能为空!');
						 return false;
						}
						if(rePassword==''){
						 alert('密码确认不能为空!');
						 return false;
						}
						if(newPassword!=rePassword){ alert('新密码跟确认密码不一致!'); 	 return false; }
						
							
						
		if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/user.do?action=modify'; //ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=modify';	//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();	
		
		
		
							}
								
						}
					}catch(e){
						alert(e);
					}
			return true;				
	}
	</script>
	



 <script type="text/javascript">
    $(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('ddd');return true;}});
	$("#ppassword").formValidator({onshow:"请输入旧密码",onfocus:"旧密码不能为空",oncorrect:"密码合法"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"旧密码不能为空,请确认"});
	
	$("#newPassword").formValidator({onshow:"请输入密码",onfocus:"新密码不能为空",oncorrect:"密码合法"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"新密码不能为空,请确认"});
	$("#rePassword").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致哦",oncorrect:"密码一致"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"newPassword",operateor:"=",onerror:"两次密码不一致,请确认"});
	
	});
	</script>
</head>
<body>
<center>
<form  method='POST' name='form1' action='/user.do?action=modify'  id="form1">
   <table width="90%" height="190" border="1">
   
   <tr>
      <td>用户名：</td>
      <td>     
      <s:property value="search.USERNAME" />
      <input type="hidden" id="username"  name="search.pusername"  value="<s:property value="search.USERNAME"/>"/>
         </td>
      <td>&nbsp;</td>
   </tr>
   
   <tr>
      <td>旧密码：</td>
      <td><input type="password" id="ppassword"  name="search.ppassword"    /></td>
      <td><div id="oldPasswordTip" style="width:250px"></div></td>
   </tr>

   <tr>
   <td>新密码:</td>
   <td>
   <input type="password" id="newPassword"  name="search.pnewPassword" />
   </td>
   <td><div id="newPasswordTip" style="width:250px"></div></td>
   </tr>
   
   <tr>
   <td>密码确认:</td>
   <td>
     <input type="password" id="rePassword"  name="search.prePassword"   />
   </td>
   <td><div id="rePasswordTip" style="width:250px"></div></td>
   </tr>
    <tr>
   <td  colspan="3" align="left">安全设置，当您忘记密码时可以通过回答问题找回密码</td>
   </tr>
   
     <tr>
   <td>安全问题:</td>
   <td>
      <input type="text" id="safequestion"  name="search.psafequestion"   value="<s:property value="search.SAFEQUESTION"/>" />
   </td>
   <td><div id="rePasswordTip" style="width:250px"></div></td>
   </tr>
   
   <tr>
   <td>安全答案:</td>
   <td>
        <input type="text" id="safeanswer"  name="search.psafeanswer"    value="<s:property value="search.SAFEANSWER"/>"  />
   </td>
   <td><div id="safeAnswerTip" style="width:250px"></div></td>
   </tr> 
   
   
   
   
   <tr>
   <td>&nbsp;</td>
   <td>
   &nbsp;
   </td>
   <td>&nbsp;</td>
   </tr> 
   

 
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
     <input type="button" id="saveId" name="save"  onclick="javascript:passwordCorrect(this);"  value="保 存"  />
    
      &nbsp;<input type="reset"  id="saveId"  name="rereset" value="重置"  /></td>
   <td>&nbsp;</td>
   </tr>

   <tr>
   <td>&nbsp;</td>
   <td  align="center">
      <font color="#ff0000"><s:actionmessage theme="webframe0"/></font>
        <font color="#ff0000"><s:actionerror theme="webframe0"/></font> 
        &nbsp;
   </td>
   <td>&nbsp;</td>
   </tr>
   
 </table>
 

 
 
 
</form>

</center>


</body>
</html>