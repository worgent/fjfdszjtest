<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
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
													<td height="16" colspan="2" >
													<s:actionerror theme="ems" />
													<s:actionmessage theme="ems" />
													</td>
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
		$("#ppasswd").formValidator({onshow:def_Showpasswd,onfocus:def_Showpasswd,oncorrect:" "}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:def_Errorpasswd},onerror:def_Errorpasswdex});
		$("#pnewpasswd").formValidator({onshow:def_Showpasswd,onfocus:def_Showpasswd,oncorrect:" "}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:def_Errorpasswd},onerror:def_Errorpasswdex});
		$("#prepasswd").formValidator({onshow:def_Showrepasswd,onfocus:def_Showrepasswd,oncorrect:" "}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:def_Errorrepasswd},onerror:def_Errorrepasswdex}).compareValidator({desid:"pnewpasswd",operateor:"=",onerror:def_Tworepasswd});
	});
</script>
</body>
</html>