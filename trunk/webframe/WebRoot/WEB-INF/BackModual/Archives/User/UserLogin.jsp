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
    $(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('开始登录。。。');return true;}});
	$("#username").formValidator({onfocus:"用户名至少6个字符,最多16个字符",oncorrect:"输入格式正确"}).inputValidator({min:6,max:16,onerror:"你输入的用户名非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"});
	$("#password").formValidator({onshow:"请输入密码",onfocus:"密码不能为空",oncorrect:"密码合法"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
	
 });
	</script>
	
	<script type="text/javascript">
	     function loginCorrect(obj){
	              
				  var url ='<%=path%>/user.do?action=loginCorrect&search.pusername='+document.getElementById("username").value+'&search.ppassword='+document.getElementById("password").value;
					
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
						
		if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/login.do?action=userLogin'; //ie7支持用attributes[83]
		}else{
		document.forms[0].action='/login.do?action=userLogin';	//alert('firefox');firefox浏览器用action
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
</head>
<body>
<center>
 <form  method='POST' name='form1' action='/login.do?action=userLogin'  id="form1">
   <table>
   <tr>
      <td>用户名：</td>
      <td  align="left"><input type="text" id="username"  name="search.pusername"  width="22" /></td>
      <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>

   <tr>
   <td>密码:</td>
   <td align="left">
   <input type="password" id="password"  name="search.ppassword"    size="22"/>
   </td>
   <td><div id="passwordTip" style="width:250px"></div></td>
   </tr>
   
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
     <input type="button" id="saveId" name="save"  onclick="javascript:loginCorrect(this);"  value="登 录"  />
    
      &nbsp;<input type="reset"  id="saveId"  name="rereset" value="重置"/>
      </td>
   <td>
   <a href="/user.do?action=regedit">我要注册</a>
   </td>
   </tr>
   
   
 </table>
</form>
</center>
</body>
</html>