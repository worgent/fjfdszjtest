	<%@page contentType="text/html; charset=gbk"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" " 
http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<title>联通沃移动助理平台</title>
	<script type="text/javascript" src="/js/defValidator.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE3 {color: #528311; font-size: 12px; }
.STYLE4 {
	color: #42870a;
	font-size: 12px;
}

-->
</style>	
</head>
	<body>

<form id="backlogin" name="backlogin" onsubmit="return true;" action="/backlogin.do"  method="post">	
<table  width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#E9F8D6">&nbsp;</td>
  </tr>	
  <tr>
    <td width="100%" height="706" background="images/login_01.gif">
    	<table width="634" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td colspan=2 height="67" background="images/login_03.gif">&nbsp;</td>
        </tr>
        <tr>
          <td width="221" height="155" background="images/login_05.gif">&nbsp;</td>
          <td width="413" height="155" background="images/login_06.gif">
             <table width="100%" border="0" height="155" cellspacing="0" cellpadding="0">
              <tr>
                <td width="40px" align="left" height="18px"><div align="center"><span class="STYLE3">用&nbsp;&nbsp;户</span></div></td>
                <td align="left" height="18px"><s:textfield name="search.pcode" id="pcode" required="true" size="15"  maxlength="40" value="" cssStyle="height:18px; width:110px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></s:textfield><div id="pcodeTip" style="display: inline "></div></td>
              </tr>
              <tr>
                <td align="left" height="18px"><div align="center"><span class="STYLE3">密&nbsp;&nbsp;码</span></div></td>
                <td align="left" height="18px"><s:password name="search.ppasswd" id="ppasswd" required="true" maxlength="20" value="" cssStyle="height:18px; width:110px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></s:password><div id="ppasswdTip" style="display: inline "></div></td>
                             
              </tr>
              <tr>
                  <td align="left" height="18px" style="vertical-align:middle"><div style="vertical-align:middle"><span class="STYLE3">验证码</span></div></td>
				  <td align="left" height="18px" style="vertical-align:middle">				
						<s:textfield id="pverifycode" name="search.pverifycode"  maxlength="4" cssStyle="height:18px; width:40px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></s:textfield>						
<IMG id="imgcode" title="看不清？！换一个" style="CURSOR: pointer;vertical-align:middle"  onclick="document.getElementById('imgcode').src='/authimg?action='+Math.random();"  alt="看不清？！换一个" src="/authimg"/>
						<div id="pverifycodeTip" style="display: inline "></div>						
					</td>	
              </tr>
              <tr>
                <td align="left" height="18">&nbsp;</td>
                <td align="left" height="18"><img src="images/dl.gif" width="81" height="22" border="0" style="cursor:hand" usemap="#Map"></td>               
              </tr>
            </table>         
          </td>
        </tr>
        <tr>
          <td width="221" height="144" background="images/login_07.gif">&nbsp;</td>
          <td width="413" height="144" background="images/login_08.gif" class="STYLE4" style="vertical-align:top"><div style="border:0px;vertical-align:middle;width:100px;height:30px;line-height:30px">版本 2010V1.0</div></td>
        </tr>              
    </table> 	
    </td>
  </tr>
         <tr>
           <td bgcolor="#a2d962">&nbsp;</td>
        </tr>  
</table>	
</form>	
<map name="Map"><area shape="rect" coords="3,3,36,19" onclick="javascript:save()"><area shape="rect" coords="40,3,78,18" onclick="javascript:reset()"></map>
<script type="text/javascript"> 
 //重置数据
	function reset(){
			document.forms[0].reset();
			return true;
	}
	function getLength(val){
				   var len = 0;
					for (var i = 0; i < val.length; i++) 
					{
						if (val.charCodeAt(i) >= 0x4e00 && val.charCodeAt(i) <= 0x9fa5){ 
							len += 2;
						}else {
							len++;
						}
					}
					return len;
	}
	
	//验证
    function checkmsg(){
    	result=false;
    	var pcode=document.getElementById("pcode").value;
    	var ppasswd=document.getElementById("ppasswd").value;
    	var pverifycode=document.getElementById("pverifycode").value;
    	if(!(getLength(pcode)>=3&&getLength(pcode)<=40))
    	{
    		alert('用户名必须在3到40个字符之间');
    		return result;
    	}
    	if(!(ppasswd.length>=6&&ppasswd.length<=20))
    	{
    		alert('密码必须在6到20个字符之间');
    		return result;
    	}
    	if(!(pverifycode.length==4))
    	{
    		alert('验证码必须4个字符');
    		return result;
    	}
		return true;
    }	
 		function save(){
			 if(checkmsg()==true){
															 $.ajax({
																    url:  '<%=path%>/backlogin!login.do?username='+encodeURIComponent(encodeURIComponent($("#pcode").val()))+'&passwd='+$("#ppasswd").val()+'&authCode='+$("#pverifycode").val(),
																	type: "POST",
												 					success: function(data) {
													                    //使用eval函数
													                    //jAlert(eval(data));
																		var value = $(data).find("value").text();
																		if(0==value){//验证码错误
																		   jAlert("验证码不正确,请重新输入!","提示");  
																		}else if(1==value){//成功
																		   window.location.replace("/main.do");
																		}else if(3==value){//用户名,密码异常
																		   jAlert("用户名或密码不正确,请核对后输入!","提示"); 
																		}else{//登录异常
																		   jAlert("数据不正确,请与管理员联系!","提示");  
																		}
												               		 },
												                	 error : function(){
																     	jAlert("连接数据库不正确,请与管理员联系!","提示");
																	 }					               
															});	
			}													
	    }	
	
 	//基本验证
	$(document).ready(function(){
			$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
			$("#pcode").formValidator({onshow:" ",onfocus:def_Showthecode,oncorrect:" "}).inputValidator({min:3,max:40,onerror:def_Errorthecode});
			$("#ppasswd").formValidator({onshow:" ",onfocus:"请输入密码",oncorrect:" "}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"请输入密码"},onerror:def_Errorpasswdex});
			$("#pverifycode").formValidator({onshow:" ",onfocus:def_Showverifycode,oncorrect:" "}).inputValidator({min:1,max:4,onerror:def_Errorverifycode});
		});
	</script>		
	</body>
</html>
