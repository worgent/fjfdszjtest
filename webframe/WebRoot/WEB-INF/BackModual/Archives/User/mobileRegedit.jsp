<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>手机注册</title>
 <script type="text/javascript">
    $(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确定发送。。。');return true;}});
	$("#username").formValidator({onfocus:"用户名至少6个字符,最多16个字符",oncorrect:"输入格式正确"}).inputValidator({min:6,max:16,onerror:"你输入的用户名非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"});
     });
	</script>
	
	<script type="text/javascript">
	     function isExist(obj){
	
				  var url ='<%=path%>/user.do?action=mobileValu&search.pusername='+document.getElementById("username").value;
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
		document.forms[0].attributes[83].value='/user.do?action=mobileRegInsert';//ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=mobileRegInsert';	//alert('firefox');firefox浏览器用action
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
<div align="center">
<form  method='POST' name='form1' action='/user.do?action=mobileRegInsert'  id="form1">
   <table border="1">
     <tr> 
   <td>&nbsp;</td>
   <td> 
       <strong>注册页面</strong>
   </td>
   <td>&nbsp;</td>
   </tr>
   
    <tr> 
   <td>用户名:</td>
   <td>
      <input type="text" id="username"  name="search.pusername"/>
   <font color="red">*</font>
   </td>
   <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>
   
    <tr> 
   <td>&nbsp;</td>
   <td>
   <input type="button" id="saveId" name="save"  onclick="javascript:isExist(this);"  value="注 册"  />
  
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   
</table>
</form>




</div>

</body>
</html>