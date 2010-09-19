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
<form  method='POST' name='form1' action='/archives/recMsg.do'  id="form1">
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
													收件人信息</span></strong></td>
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
														<s:textfield id="prec_name"  name="search.prec_name" value="%{recMsg.REC_NAME}"></s:textfield>
												</span> <div id="prec_nameTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													电话</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:textfield id="prec_tel"  name="search.prec_tel" value="%{recMsg.REC_TEL}" title="(例:059512345678)" ></s:textfield>
												</span> <div id="prec_telTip" style="display: inline "></div></td>
												</tr>
												<!--  
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													手机</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														 <s:textfield id="prec_mobile"  name="search.prec_mobile" value="%{recMsg.REC_MOBILE}"></s:textfield>
												</span><div id="prec_mobileTip" style="display: inline "></div></td>
												</tr>
												-->
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													单位名称</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														 <s:textfield id="prec_unit"  name="search.prec_unit" value="%{recMsg.REC_UNIT}"></s:textfield>
												</span><div id="prec_unitTip" style="display: inline "></div></td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													邮编</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														 <s:textfield id="prec_post"  name="search.prec_post" value="%{recMsg.REC_POST}"></s:textfield>
												</span><div id="prec_postTip" style="display: inline "></div></td>
												</tr>
												<TR align="left">
														<td width="98" align="left">
															<span style="FONT-SIZE: 9pt">
															详细地址:</span></td>
													    <td align="left"><span style="font-size: 9pt">
													    <s:textfield id="prec_address"  name="search.prec_address"  size="40" value="%{recMsg.REC_ADDRESS}" > </s:textfield>
														<font size="2"><FONT color=red>*</FONT> </font></span>
													    <div id="paddressTip" style="display: inline "></div>	
													</TD>
												</TR>
												<!--
												<TR align="left">
													    <TD rowSpan=2 width="95"><font size="2">地址:</font></TD>
													    <TD width="524"><font size="2">省份: </font>
														<select id="prec_province" name="search.prec_province" onchange="changeCombo('prec_city','prec_province','cityvalue')"></select>
														<font size="2"> 地市：</font>
														<select id="prec_city" name="search.prec_city" onchange="changeCombo('prec_county','prec_city','countyvalue')"></select>
														<font size="2"> 县、区：</font>
														<select id="prec_county" name="search.prec_county"></select>
														<font size="2"> </font> </TD>
												</TR>
												<TR align="left">
													    <TD width="524"><font size="2">详细地址: </font> 
													    <s:textfield id="prec_address"  name="search.prec_address"  size="40" value="%{recMsg.REC_ADDRESS}" > </s:textfield>
														<font size="2"><FONT color=red>*</FONT> </font>
													<div id="paddressTip" style="display: inline "></div>	
													</TD>
												</TR>
												  -->
												<tr>
													<td width="98">　</td>
													<td>　</td>
												</tr>
												<tr>
													<td width="98">
													      <s:hidden value="%{recMsg.ID}" name="search.pid" id="pid"></s:hidden>
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
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
		$("#prec_name").formValidator({onshow:def_Showname,onfocus:def_Showname,oncorrect:" "}).inputValidator({min:1,max:20,onerror:def_Errorname});
		$("#prec_tel").formValidator({onshow:def_Showtel,onfocus:def_Showtel,oncorrect:" "}).regexValidator({regexp:"^\\s+$|^$|^[0-9\\-]+$",onerror:def_Errortel});
		//$("#prec_mobile").formValidator({onfocus:"手机号",oncorrect:"√"}).regexValidator({regexp:"^$|^(13|15|18)[0-9]{9}$",onerror:"手机号码格式不正确"});
		$("#prec_address").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Erroraddress});
		$("#prec_post").formValidator({onshow:"请输入邮编",onfocus:"请输入邮编",oncorrect:" "}).regexValidator({regexp:"zipcode",datatype:"enum",onerror:"请输入邮编"});
		//$("#prec_province").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		//$("#prec_city").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空"});
		//$("#prec_county").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空"});
	});
	
	//2009-12-01
//省,地,市连动
//参数说明:chCombo变化控件的id
//		  srcCombo引起变化的控件id
//		  action事件类型
function changeCombo(chCombo,srcCombo,action)
{
	var cdsales=new ActiveXObject("Microsoft.XMLDOM"); //创建XmlDom对象
    cdsales.async=false; //使用异步加载
    var parmid='';
    if(srcCombo!='')
    {
      parmid=document.getElementById(srcCombo).value
    }
    cdsales.load("<%=path%>/archives/area.do?action="+action+"&search.pid="+parmid);
     var bi;
     if(cdsales.documentElement!=null)
         bi=cdsales.documentElement.selectNodes("NODE");
    if(bi!=null&&bi.length>0)
    {
       //默认行
       document.getElementById(chCombo).options.length = bi.length+1;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
       for(var i=1;i<bi.length+1;i++){     
	   			document.getElementById(chCombo).options[i].value = bi[i-1].selectSingleNode("THE_CODE").text;//隐藏值
	   			document.getElementById(chCombo).options[i].text =  bi[i-1].selectSingleNode("THE_NAME").text;//显示值
       }
    }else{
       document.getElementById(chCombo).options.length = 1;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
    }
}
/**
*对于连动bug处理,清空关连的数据,当省,市,县连动时使用
*/
function clearCombo(chCombo)
{
	   document.getElementById(chCombo).options.length = 0;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
}	    


/*
//初始化信息
changeCombo('prec_province','','provincevalue');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
document.getElementById('prec_province').value="350000";
<s:if test="%{action=='update'}">
document.getElementById('prec_province').value=<s:property value="%{recMsg.REC_PROVINCE}"/>;
</s:if>
changeCombo('prec_city','prec_province','cityvalue');
<s:if test="%{action=='update'}">
document.getElementById('prec_city').value=<s:property value="%{recMsg.REC_CITY}"/>;
</s:if>
changeCombo('prec_county','prec_city','countyvalue');
<s:if test="%{action=='update'}">
document.getElementById('prec_county').value=<s:property value="%{recMsg.REC_COUNTY}"/>;
</s:if>
*/
	</script>
</body>
</html>