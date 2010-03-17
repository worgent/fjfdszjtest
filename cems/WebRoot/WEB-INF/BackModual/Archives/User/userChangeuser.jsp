<%@page contentType="text/html; charset=gbk"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<body>
<center>
<form  method='POST' name='form1' action='<%=basePath%>/archives/user.do?action=changeuser'  id="form1">
 <table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="78%" id="table14">
									<tr>
										<td background="<%=basePath%>/images/frontlogin05.jpg" width="100%" height="45" STYLE="background-repeat: no-repeat;" >　</td>
										<td>　</td>
									</tr>
									<tr>
										<td background="<%=basePath%>/images/frontlogin06.jpg"  STYLE="background-repeat:  repeat-y;" >
										<div align="center">
											<table  border="0" cellpadding="0" style="border-collapse: collapse" width="80%" height="100%" id="table15">
												<tr >
													<td width="98" height="37">
													<strong>
													<span style="font-size: 9pt">
													修改用户信息</span></strong></td>
													<td height="37">　</td>
												</tr>
												<tr align="left">
													<td width="98" height="16">
													<s:actionerror theme="ems" />
													&nbsp;<s:actionmessage theme="ems" />
													</td>
													<td height="16"></td>
												</tr>
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													用户名：</span></td>
													<td><span style="font-size: 9pt"><s:property value="%{search.CODE}" /></span>　</td>
												</tr>
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													客户代码：</span></td>
													<td><span style="font-size: 9pt"><s:property value="%{search.CLIENTCODE}" /></span>　</td>
												</tr>
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													姓名</span><span style="font-size: 9pt">：</span></td>
													<td>
													<span style="font-size: 9pt">
													<s:textfield id="pname"  name="search.pname" value="%{search.NAME}"></s:textfield></span><div id="pnameTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98">
													<span style="font-size: 9pt">
													固定电话：</span></td>
													<td>
													<span style="font-size: 9pt">
													 <s:textfield name="search.ptel" id="ptel" value="%{search.TEL}"></s:textfield></span><div id="ptelTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													手机</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:textfield id="pmobile"  name="search.pmobile" value="%{search.MOBILE}"></s:textfield></span><div id="pmobileTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98">　</td>
													<td>　</td>
												</tr>
												<tr align="left">
													<td width="98">
													<font color="red">
													<span style="FONT-SIZE: 9pt">
													选填</span></font></td>
													<td>　</td>
												</tr>
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													单位名称：</span></td>
													<td>
													<span style="font-size: 9pt">
													<s:textfield name="search.punit" id="punit" value="%{search.UNIT}" ></s:textfield></span><div id="punitTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													电子邮箱：</span></td>
													<td>
													<span style="font-size: 9pt">
													 <s:textfield name="search.pemail" id="pemail"  value="%{search.EMAIL}"></s:textfield></span>
													  <div id="pemailTip" style="display: inline "></div>
													  </td>
												</tr>
												<tr>
													<td width="98">　</td>
													<td>　</td>
												</tr>
												<tr>
													<td width="98"> <s:hidden name="search.pid" id="pid" value="%{search.ID}"></s:hidden>
													　</td>
													<td align="left">
													<input type="button" onclick="javascript:save()" value="保存" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													<input type="button" onclick="javascript:reset()" value="重置" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													</td>
												</tr>
											</table>
										</div>
										</td>
									</tr>
									<tr>
										<td background="<%=basePath%>/images/frontlogin07.jpg" width="100%" height="15" STYLE="background-repeat: no-repeat;" >　</td>
										<td></td>
									</tr>
								</table>
</form>
</center>
 <script type="text/javascript">
 
 	//保存数据
	function save(){
	    if($.formValidator.pageIsValid()){
			document.forms[0].submit();
		}else
		{
		  return false;
		}
	}
	
	 //重置数据
	function reset(){
			document.forms[0].reset();
			return true;
	}
 
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){
			   if($("#ptel").val()==""&&$("#pmobile").val()=="")
			   {
				   alert('电话和手机必填一项');
				   return false;	   
			   }else
			   {
				   return true;
			   }
		}});
		$("#pname").formValidator({onfocus:"最多20个字符",oncorrect:"√"}).inputValidator({min:1,max:20,onerror:"姓名非法,请确认"});
		$("#ptel").formValidator({onfocus:"电话号码或小灵通,例059512345678",oncorrect:"√"}).regexValidator({regexp:"^\\d{10,12}$",onerror:"电话号码格式不正确"});
		$("#pmobile").formValidator({onfocus:"手机号",oncorrect:"√"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机号码格式不正确"});
		$("#pprovince").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	
		$("#paddress").formValidator({onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pverifycode").formValidator({onfocus:"验证码",oncorrect:"√"}).inputValidator({min:1,max:4,onerror:"验证码为4位"});
		$("#pemail").formValidator({onfocus:"电子邮箱",oncorrect:"√"}).regexValidator({regexp:"emailex",datatype:"enum",onerror:"电子邮箱格式不正确"});
	});	
</script>
</body>
</html>