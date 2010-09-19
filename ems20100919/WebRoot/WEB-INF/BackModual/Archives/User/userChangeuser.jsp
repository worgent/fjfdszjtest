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
													<td  height="16" colspan="2">
													<s:actionerror theme="ems" />
													<s:actionmessage theme="ems" />
													</td>
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
												
												<TR align="left">
											    <TD width="95" align="left"><font size="2">省&nbsp;&nbsp;市&nbsp;&nbsp;区：</font></TD>
											    <TD width="524"><font size="2">福建省 </font>
											    <s:hidden value="0" name="search.pprovince" id="pprovince"></s:hidden>
											    <!-- 
												<select id="pprovince" name="search.pprovince" onchange="changeCombo('pcity','pprovince','cityvalue');clearCombo('pcounty')"></select>
												 -->
												<font size="2"> 地市:</font>
												<select id="pcity" name="search.pcity" onchange="changeCombo('pcounty','pcity','countyvalue')"></select>
												<font size="2"> 县、区:</font>
												<select id="pcounty" name="search.pcounty"></select>
												<font size="2"> </font> </TD>
											    </TR>
											    <TR align="left">
											    <TD width="95" align="left"><font size="2">详细地址：</font></TD>
											    <TD width="524" align="left"><font size="2"></font> 
											    <s:textfield id="paddress"  name="search.paddress" value="%{search.ADDRESS}" size="36"> </s:textfield>
												<font size="2"><FONT color=red>*</FONT> </font>
											    <div id="paddressTip" style="display: inline "></div>	
											    </TD>
												
												</TR>											
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													电子邮箱：</span></td>
													<td>
													<span style="font-size: 9pt">
													 <s:textfield name="search.pemail" id="pemail"  value="%{search.EMAIL}"></s:textfield></span><font size="2"><FONT color=red>*</FONT> </font>
													  <div id="pemailTip" style="display: inline "></div>
													  </td>
												</tr>
												
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													单位名称：</span></td>
													<td>
													<span style="font-size: 9pt">
													<s:textfield name="search.punit" id="punit" value="%{search.UNIT}" ></s:textfield></span><div id="punitTip" style="display: inline "></div></td>
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
		$("#pcity").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Errorcity});
		$("#pcounty").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Errorcounty});
		$("#paddress").formValidator({onshow:def_Showaddress,onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Erroraddress});
		$("#pverifycode").formValidator({onshow:def_Showverifycode,onfocus:def_Showverifycode,oncorrect:" "}).inputValidator({min:1,max:4,onerror:def_Errorverifycode});
		$("#pemail").formValidator({onshow:def_Showemail,onfocus:def_Showemail,oncorrect:" "}).regexValidator({regexp:"email",datatype:"enum",onerror:def_Erroremail});
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

/*
*对于连动bug处理,清空关连的数据,当省,市,县连动时使用
*/
function clearCombo(chCombo)
{
	   document.getElementById(chCombo).options.length = 0;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
}	    

//初始化信息
//changeCombo('pprovince','','provincevalue');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
document.getElementById('pprovince').value="350000";
changeCombo('pcity','pprovince','cityvalue');
document.getElementById('pcity').value=<s:property value="%{search.CITY}"/>;
changeCombo('pcounty','pcity','countyvalue');
document.getElementById('pcounty').value=<s:property value="%{search.COUNTY}"/>;
//不能修改省份
document.getElementById('pprovince').disable=true;
</script>
</body>
</html>