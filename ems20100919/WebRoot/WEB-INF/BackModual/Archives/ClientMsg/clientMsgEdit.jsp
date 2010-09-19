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
<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
<body>
<center>
<form  method='POST' name='form1' action='/archives/clientMsg.do'  id="form1">
  <table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="78%" id="table14">
									<tr>
										<td background="<%=basePath%>/images/frontlogin05.jpg" width="740" height="45" STYLE="background-repeat: no-repeat;">　</td>
									</tr>
									<tr>
										<td background="<%=basePath%>/images/frontlogin06.jpg" STYLE="background-repeat:  repeat-y;">
										<div align="center">
											<table  border="0" cellpadding="0" style="border-collapse: collapse" width="80%" height="100%" id="table15">
												<tr >
													<td width="98" height="37">
													<strong>
													<span style="font-size: 9pt">
													取件联系人</span></strong></td>
													<td height="37">　</td>
												</tr>
												<tr align="left">
													<td height="16" colspan="2" >
													<s:actionerror theme="ems" />
													<s:actionmessage theme="ems" />
													</td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													联系人</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:textfield id="pname"  name="search.pname" value="%{clientMsg.NAME}"></s:textfield>
												</span> <div id="pnameTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													固定电话</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:textfield id="ptel"  name="search.ptel" value="%{clientMsg.TEL}" title="(例:059512345678)" ></s:textfield>
												</span> <div id="ptelTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													手机</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														 <s:textfield id="pmobile"  name="search.pmobile" value="%{clientMsg.MOBILE}"></s:textfield>
												</span><div id="pmobileTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													单位名称</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														 <s:textfield id="punit"  name="search.punit" value="%{clientMsg.UNIT}"></s:textfield>
												</span><div id="punitTip" style="display: inline "></div></td>
												</tr>
												<tr>
													<td width="98">　</td>
													<td>　</td>
												</tr>
												<tr>
													<td width="98">
													      <s:hidden value="%{clientMsg.ID}" name="search.pid" id="pid"></s:hidden>
													      <s:hidden value="%{action}" name="action" id="action"></s:hidden>
													　</td>
													<td align="left">
													<input type="button" onclick="javascript:save()" value="保存" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													<input type="button" onclick="javascript:reset()" value="重置" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
												    <input type="button" onclick="javascript:history.go(-1)" value="返回" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													</td>
												</tr>
											</table>
										</div>
										</td>
									</tr>
									<tr>
										<td background="<%=basePath%>/images/frontlogin07.jpg" width="740" height="15" STYLE="background-repeat: no-repeat;">　</td>
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
			   if($("#ptel").val().Trim()==""&&$("#pmobile").val().Trim()=="")
			   {
				   alert('电话和手机必填一项');
				   return false;	   
			   }else
			   {
				   return true;
			   }
		}});
		$("#pname").formValidator({onshow:def_Showname,onfocus:def_Showname,oncorrect:" "}).inputValidator({min:1,max:20,onerror:def_Errorname});
		$("#ptel").formValidator({onshow:def_Showtel,onfocus:def_Showtel,oncorrect:" "}).regexValidator({regexp:"^\\s+$|^$|^[0-9\\-]+$",onerror:def_Errortel});
		$("#pmobile").formValidator({onshow:def_Showmobile,onfocus:def_Showmobile,oncorrect:" "}).regexValidator({regexp:"^\\s+$|^$|^(13|15|18)[0-9]{9}$|^\\d{10,12}$",onerror:def_Errormobile});
	});
	</script>
</body>
</html>