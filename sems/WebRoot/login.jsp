<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>福建邮政速递网上营业厅</title>
	<script type="text/javascript" src="/js/defValidator.js"></script>
</head>
	<body>

<div align="center">
	　</div>
<div align="center">
	　</div>
<div align="center">
	　</div>
<div align="center">
	　</div>
<div align="center">
	　</div>
<div align="center">
	　</div>
<div align="center">
	　</div>
<div align="center">
	　</div>
<div align="center">
<form id="frontlogin" name="frontlogin" onsubmit="return true;" action="/frontlogin.do"  method="post">
	<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="351" id="table1" background="images/denglu01.jpg">
		<tr>
			<td>
			<div align="center">
				<table border="0" cellpadding="0" style="border-collapse: collapse" width="541" height="241" id="table2" background="images/denglu02.jpg">
					<tr>
						<td height="73" width="541" colspan="3">　</td>
					</tr>
					<tr align="left">
						<td width="238" rowspan="4">　</td>
						<td width="58" height="35"><font style="font-size: 9pt">用户名：</font></td>
						<td width="245" height="35">
						<span style="font-size: 9pt">
						<s:textfield name="search.pcode" id="pcode" required="true" size="15"  maxlength="40"></s:textfield>
						</span>
						<div id="pcodeTip" style="display: inline "></div>
						</td>
					</tr>
					<tr align="left">
						<td width="58" height="35"><font style="font-size: 9pt">密码：</font></td>
						<td width="245" height="35">
						<span style="font-size: 9pt"><s:password name="search.ppasswd" id="ppasswd" size="15" required="true" maxlength="20"></s:password>
						</span>
						<div id="ppasswdTip" style="display: inline "></div>
						</td>
					</tr>
					<tr align="left">
						<td width="58" height="36"><font style="font-size: 9pt">
						验证码：</font></td>
						<td width="245" height="36">
						<span style="font-size: 9pt">
						<s:textfield id="pverifycode" name="search.pverifycode"  size="6" maxlength="4"></s:textfield>
						</span><font style="font-size: 9pt"> 
						<IMG id="imgcode" title="看不清？！换一个" style="CURSOR: pointer"  onclick="document.getElementById('imgcode').src='/authimg?action='+Math.random();"  alt="看不清？！换一个" src="/authimg" border="0"/> 
						</font>
						<div id="pverifycodeTip" style="display: inline "></div> 
						</td>
					</tr>
					<tr>
						<td width="303" colspan="2" height="62">
						<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="100%" id="table3">
							<tr>
							<td width="100%">
							<input type="button" onclick="javascript:save()" value="登录" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
							<input type="button" onclick="javascript:reset()" value="重置" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
								<font style="font-size: 9pt">&nbsp;
								<A href='javascript:fun_forget("clientcancel","")'><span style="text-decoration: none">忘记密码</span></A>
								<A href="/regedit.jsp"> <span style="text-decoration: none; font-weight: 700">注册</span></A></font>
							</td>
								<td>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr>
	</table>
</form>	
</div>
 	<script type="text/javascript">
 	document.body.style.backgroundImage="url(images/bg.jpg)";
 		//发送密码到手机,邮箱==手机.密码设置
		function fun_forget(action,pid){
			//jConfirm('您确定执行该操作!', '确认框', function(r) {	
			jPrompt('电子邮箱:', ' ', '提示框', function(r) {
	    		if (r){
	    		  jAlert('电子邮箱:'+r+'已经提交到服务器,您的手机将会得到重置后的密码!','提示');
	    		}
			});
		}
 
 //重置数据
	function reset(){
			document.forms[0].reset();
			return true;
	}
	
 //保存数据
	function save(){
	    if($.formValidator.pageIsValid()){
	    	var url ='/frontlogin.do';
			document.forms[0].action=url;
			document.forms[0].submit();
		}else
		{
		  return false;
		}
	}	
 	
 	//基本验证
	$(document).ready(function(){
	//设置背景
	//$("body").css("background-img","url('images/bg.jpg')");
			$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
			$("#pcode").formValidator({onshow:" ",onfocus:def_Showthecode,oncorrect:" "}).inputValidator({min:3,max:40,onerror:def_Errorthecode});
			$("#ppasswd").formValidator({onshow:" ",onfocus:"请输入密码",oncorrect:" "}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"请输入密码"},onerror:def_Errorpasswdex});
			$("#pverifycode").formValidator({onshow:" ",onfocus:def_Showverifycode,oncorrect:" "}).inputValidator({min:1,max:4,onerror:def_Errorverifycode});
		});
	</script>		
	</body>
</html>
