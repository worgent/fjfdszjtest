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
<form  method='POST' name='form1' action='/archives/user.do?action=changepwd'  id="form1">
 <table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="78%" id="table14">
									<tr>
										<td background="<%=basePath%>/images/frontlogin05.jpg" width="740" height="45" STYLE="background-repeat: no-repeat;" >　</td>
									</tr>
									<tr>
										<td background="<%=basePath%>/images/frontlogin06.jpg"  STYLE="background-repeat:  repeat-y;" >
										<div align="center">
											<table  border="0" cellpadding="0" style="border-collapse: collapse" width="80%" height="100%" id="table15">
												<tr >
													<td width="98" height="37">
													<strong>
													<span style="font-size: 9pt">
													修改密码</span></strong></td>
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
													旧密码</span><span style="font-size: 9pt">：</span></td>
													<td>
													<span style="font-size: 9pt">
													<s:password id="ppasswd"  name="search.ppasswd"></s:password></span>  <div id="ppasswdTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98">
													<span style="font-size: 9pt">
													新密码：</span></td>
													<td>
													<span style="font-size: 9pt">
													  <s:password id="pnewpasswd"  name="search.pnewpasswd"></s:password></span><div id="pnewpasswdTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													密码确认</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:password  id="prepasswd"  name="search.prepasswd"  ></s:password></span><div id="prepasswdTip" style="display: inline "></div></td>
												</tr>
												<tr>
													<td width="98">　</td>
													<td>　</td>
												</tr>
												<tr>
													<td width="98">   <s:hidden name="search.pid" id="pid" value="%{search.ID}"></s:hidden>　</td>
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
										<td background="<%=basePath%>/images/frontlogin07.jpg" width="740" height="15" STYLE="background-repeat: no-repeat;" >　</td>
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
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
		$("#ppasswd").formValidator({onshow:"请输入旧密码",onfocus:"旧密码不能为空",oncorrect:"√"}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"旧密码有误"});
		$("#pnewpasswd").formValidator({onfocus:"密码长度为6~20个字符",oncorrect:"√"}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"新密码有误"});
		$("#prepasswd").formValidator({onfocus:"两次密码必须一致",oncorrect:"√"}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"pnewpasswd",operateor:"=",onerror:"两次密码不一致,请确认"});
	});
</script>
</body>
</html>