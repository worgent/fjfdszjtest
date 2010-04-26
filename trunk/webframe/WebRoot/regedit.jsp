<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<body>
<center>
<form  method='POST' name='form1' action='/archives/user.do?action=insert'  id="form1">
   <table width="905" height="301" border="1">
     <tr> 
   <td>&nbsp;</td>
   <td> 
       <strong>注册页面</strong>
   </td>
   <td>&nbsp;</td>
   </tr>
     <tr> 
   <td>&nbsp;</td>
   <td>
   &nbsp;
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
   <td>昵称:</td>
   <td>
   <input type="text" id="nickname"  name="search.pnickname"/>
   <font color="red">*</font>
   </td>
   <td><div id="usernameTip" style="width:250px"></div></td>
   </tr>
   
    <tr> 
   <td>密码:</td>
   <td>
   <input type="password" id="password"  name="search.ppassword"   />
   <font color="red">*</font>
   </td>
   <td><div id="passwordTip" style="width:250px"></div></td>
   </tr>
   
    <tr> 
   <td>确认密码:</td>
   <td>
   <input type="password" id="repassword"  name="search.prepassword"   />
   <font color="red">*</font>
   </td>
   <td><div id="repasswordTip" style="width:250px"></div></td>
   </tr>
    <tr>
      <td>性别：</td>
      <td>
     <select name="search.psex" id="sex">
          <option value="1"   <s:if test="#user.SEX==1">selected</s:if> >男</option>
          <option value="2"   <s:if test="#user.SEX==2">selected</s:if> >女</option>
          <option value="3"   <s:if test="#user.SEX==3">selected</s:if> >保密</option>
   </select>
      <font color="red">*</font>
      </td>
      <td>&nbsp;</td>
   </tr>
    <tr>
      <td>验证码：</td>
      <td>
       <input type="text"  id="checkcode" name="search.checkcode"  size="6" maxlength="4"/> 
       <img border=0 src="../../../../image.jsp" id="imgcode" style="cursor:pointer;" border="0" onClick="document.getElementById('imgcode').src='../../../../image.jsp'" title="看不清？！换一个" alt="看不清？！换一个"/>
                 <input type="button" value="看不清，换一张" onClick="document.getElementById('imgcode').src='../../../../image.jsp'"> <span id="msg_6"> </span>
      <font color="red">*</font>
      </td>
      <td>&nbsp;</td>
   </tr>
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
     <input type="button" id="saveId" name="save"  onclick="javascript:isExist(this);"  value="保 存"  />
      &nbsp;<input type="reset"  id="setId"  name="rereset" value="重 置" />
      </td>
   <td>&nbsp;</td>
   </tr>
 </table>

</form>
</center>
<script type='text/javascript' src='/js/DateTimeCalendar.js'></script>
<script type="text/javascript">

$(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确定保存。。。');return true;}});
	$("#username").formValidator({onfocus:"用户名至少6个字符,最多16个字符",oncorrect:"输入格式正确"}).inputValidator({min:6,max:16,onerror:"你输入的用户名非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"});
	$("#password").formValidator({onshow:"请输入密码",onfocus:"密码不能为空",oncorrect:"密码合法"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
	$("#repassword").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致哦",oncorrect:"密码一致"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"password",operateor:"=",onerror:"两次密码不一致,请确认"});
});



 function isExist(obj){
				  var url ='<%=path%>/archives/user.do?action=isExist&search.pusername='+document.getElementById("username").value+'&search.checkcode='+document.getElementById("checkcode").value;
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
		document.forms[0].attributes[83].value='/archives/user.do?action=insert';//ie7支持用attributes[83]
		}else{
		document.forms[0].action='/archives/user.do?action=insert';	//alert('firefox');firefox浏览器用action
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
</body>
</html>