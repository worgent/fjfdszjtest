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
<form  method='POST' name='form1' action='/net/printConfig.do?action=update'  id="form1">
 <table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="78%" id="table14">
									<tr>
										<td background="<%=basePath%>/images/frontlogin05.jpg" width="740" height="45" STYLE="background-repeat: no-repeat;" >　</td>
									</tr>
									<tr>
										<td background="<%=basePath%>/images/frontlogin06.jpg" width="740" height="45" STYLE="background-repeat:  repeat-y;" >
										<div align="center">
											<table  border="0" cellpadding="0" style="border-collapse: collapse" width="80%" height="100%" id="table15">
												<tr>
													<td width="98" height="37" colspan="2">
													<strong>
													<span style="font-size: 9pt">
													打印配置信息</span></strong></td>
												</tr>
												<tr align="left">
													<td width="98" height="16">
													</td>
													<td height="16">	
													<span style="font-size: 9pt">												<s:actionerror theme="ems" />
													<s:actionmessage theme="ems" />
													</span>
													</td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													用户名 </span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:property value="%{search.CODE}" />
												</span> </td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													上边距</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:textfield id="ptop_margin"  name="search.ptop_margin" value="%{search.TOP_MARGIN}"></s:textfield>(mm)
												</span> <div id="ptop_marginTip" style="width:250px"></div></td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													左边距</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:textfield name="search.pleft_margin" id="pleft_margin" value="%{search.LEFT_MARGIN}"></s:textfield>(mm)
												</span>  <div id="pleft_marginTip" style="width:250px"></div></td>
												</tr>
												<tr>
													<td width="98">　</td>
													<td>　</td>
												</tr>
												<tr>
													<td width="98">
													      <s:hidden name="search.pid" id="pid" value="%{search.ID}"></s:hidden>
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
		$("#pleft_margin").formValidator({onfocus:"左边距",oncorrect:"√"}).regexValidator({regexp:"^[1-9]\\d*|0$",onerror:"左边距格式不正确"});
		$("#ptop_margin").formValidator({onfocus:"上边距",oncorrect:"√"}).regexValidator({regexp:"^[1-9]\\d*|0$",onerror:"上边距格式不正确"});
	});
</script>
</body>
</html>