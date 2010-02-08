<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>

		<title>登录</title>
	</head>
	<body>
		<p>&nbsp;</p>
		<s:form action="frontlogin" target="_top" namespace="/">
			<table width="700" border="0" align="center" cellpadding="10"
				cellspacing="0" class="table1">
				<tr>
					<td>
						<table width="70%" border="1" align="center" cellpadding="5" cellspacing="0">
							<tr>
								<td colspan="3" align="center">
									<strong>登录</strong>
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
								<td width="20%">
									<div align="right">
									用户名
									</div>
								</td>
								<td width="40%">
									<s:textfield name="search.pcode" id="pcode" required="true" size="15"  maxlength="40" value="admini"></s:textfield>
								</td>
								<td width="40%"><div id="pcodeTip"></div></td>
							</tr>
							<tr>
								<td>
									<div align="right">
										密码
									</div>
								</td>
								<td>
									<s:password name="search.ppasswd" id="ppasswd" size="15" required="true" maxlength="20" value="admini"></s:password>
								</td>
								<td><div id="ppasswdTip"></div></td>
							</tr>
							<tr>
						      <td>验证码：</td>
						      <td>
						       <input type="text"  id="pverifycode" name="search.pverifycode"  size="6" maxlength="4"/> 
							   <img border=0 src="/authimg" id="imgcode" style="cursor:pointer;" border="0" onClick="document.getElementById('imgcode').src='/authimg?action='+Math.random();" title="看不清？！换一个" alt="看不清？！换一个"/>
						       <font color="red">*</font>
						      </td>
							  <td><div id="pverifycodeTip"></div></td>
   							</tr>
							<tr>
								<td colspan="3">
									<div align="center">
										<s:submit value="登录" ></s:submit>
										<a href="/regedit.jsp">忘记密码</a>
										<a href="/regedit.jsp">注册</a>
										<s:reset value="重置"></s:reset>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>	
 	<script type="text/javascript">
	    $(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('开始登录。。。');return true;}});
	$("#pcode").formValidator({onfocus:"请输入用户名",oncorrect:"√"}).inputValidator({min:6,max:40,onerror:"用户名有误,请确认"});
	$("#ppasswd").formValidator({onfocus:"请输入密码",oncorrect:"√"}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码有误,请确认"});
	$("#pverifycode").formValidator({onfocus:"验证码",oncorrect:"√"}).inputValidator({min:1,max:4,onerror:"验证码有误,请确认!"});
		});
		<s:if test="${param.error != null}">
				 alert('提示', '工号或密码不正确，请重新输入!', clear_input);
				 function clear_input(){
				 	$('#search.pcode').value = '';
				 	$('#search.ppasswd').value= '';
				 }
		</s:if>
	</script>		
	</body>
</html>
