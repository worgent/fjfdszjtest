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
<form  method='POST' name='form1' action='/archives/user.do?action=update'  id="form1">
  <table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="78%" id="table14">
									<tr>
										<td background="<%=basePath%>/images/frontlogin05.jpg" width="740" height="45" STYLE="background-repeat: no-repeat;" >　</td>
									</tr>
									<tr>
										<td background="<%=basePath%>/images/frontlogin06.jpg" STYLE="background-repeat:  repeat-y;">
										<div align="center">
											<table  border="0" cellpadding="0" style="border-collapse: collapse" width="80%" height="100%" id="table15">
												<tr >
													<td width="98" height="37">
													<strong>
													<span style="font-size: 9pt">
													用户审核</span></strong></td>
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
													用户名：</span></td>
													<td><span style="font-size: 9pt"><s:property value="%{user.CODE}" /></span>　</td>
												</tr>
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													姓名：</span></td>
													<td><span style="font-size: 9pt"><s:property value="%{user.NAME}"/></span></td>
												</tr>
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													固定电话：</span></td>
													<td><span style="font-size: 9pt"><s:property value="%{user.TEL}"/></span></td>
												</tr>
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													手机</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
													<s:property value="%{user.MOBILE}" /></span></td>
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
													电子邮箱：</span></td>
													<td>
													<span style="font-size: 9pt">
													<s:property value="%{user.EMAIL}"/></span>
													</td>
												</tr>
												
												<tr align="left">
													<td width="98">
													<font color="red">
													<span style="FONT-SIZE: 9pt">
													审核人员操作</span></font></td>
													<td>　</td>
												</tr>
												
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													单位名称：</span></td>
													<td>
													<span style="font-size: 9pt">
														<s:textfield name="search.punit" id="punit" value="%{user.UNIT}" ></s:textfield></span>
														<div id="punitTip" style="display: inline "></div>
													</td>
												</tr>
												
											  <TR align="left" style="font-size: 9pt">
											    <TD rowSpan=2 width="95"><font size="2">地址:</font></TD>
											    <TD width="700">省份:福建省
											    <s:hidden value="0" name="search.pprovince" id="pprovince"></s:hidden>
											    <!--
												<select id="pprovince" name="search.pprovince" onchange="changeCombo('pcity','pprovince','cityvalue')"></select>
												-->
												 地市:
												<select id="pcity" name="search.pcity" onchange="changeCombo('pcounty','pcity','countyvalue')"></select>
												县、区:
												<select id="pcounty" name="search.pcounty"></select>
												</TD>
											    </TR>
											  <TR align="left">
											    <TD width="700"><font size="2">详细地址: </font> 
											    <s:textfield id="paddress"  name="search.paddress" value="%{user.ADDRESS}"  size="50"> </s:textfield>
												<font size="2"><FONT color=red>*</FONT> </font>
											<div id="paddressTip" style="display: inline "></div>	
											</TD>
												
												</TR>
																							
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													客户类型：</span></td>
													<td>
													<span style="font-size: 9pt">
													<select name="search.pclienttype" id="pclienttype">
												 		<option value="">请选择客户类型</option>
												   		<s:iterator id="list"  value="%{clienttypeList}">
															<option value="<s:property value="#list.ID" />"  <s:if test="%{#list.ID==user.CLIENTTYPE}">selected</s:if> >
																	<s:property value="#list.NAME" />
															</option>
														</s:iterator>
												   </select>
					  								 </span>
					  								 <div id="pclienttypeTip" style="display: inline "></div>
													</td>
												</tr>
												
												
												
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													客户代码：</span></td>
													<td>
													<span style="font-size: 9pt">
													 <s:textfield id="pclientcode"  name="search.pclientcode" value="%{user.CLIENTCODE}"></s:textfield></span>
													 <div id="pclientcodeTip" style="display: inline "></div>
													</td>
												</tr>
												
												<tr align="left">
													<td width="98">
													<span style="FONT-SIZE: 9pt">
													结算方式：</span></td>
													<td>
													<span style="font-size: 9pt">
													<select name="search.pclientbalance" id="pclientbalance">
												        <option value=" ">请选择结算方式</option>
												   		<s:iterator id="list"  value="%{clientbalanceList}">
															<option value="<s:property value="#list.ID" />"  <s:if test="%{#list.ID==user.CLIENTBALANCE}">selected</s:if> >
																	<s:property value="#list.NAME" />
															</option>
														</s:iterator>
													 </select>
													 </span>
													 <div id="pclientbalanceTip" style="display: inline "></div>
													</td>
												</tr>
												
												<tr>
													<td width="98">　</td>
													<td>　</td>
												</tr>
												<tr>
													<td width="98"> <s:hidden value="%{user.ID}" name="search.pid" id="pid"></s:hidden>　</td>
													<td align="left">
													<input type="button" onclick="javascript:save('update')" value="保存" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													<s:if test="%{user.BILL_TYPE==0}">
													<input type="button" onclick="javascript:save('saveauding')" value="保存审核" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													</s:if>
													<input type="button" onclick="javascript:reset()" value="重置" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
												    <input type="button" onclick="javascript:history.go(-1)" value="返回" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
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
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
		$("#pprovince").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Erroraddress});
		$("#pcity").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Erroraddress});
		$("#pcounty").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Erroraddress});
		$("#paddress").formValidator({onshow:def_Showaddress,onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Erroraddress});
	    //客户类型, 客户代码-结算方式的关系
		$("#pclienttype").formValidator({onshow:"客户类型",onfocus:"客户类型",oncorrect:" "}).inputValidator({min:1,onerror:"请输入客户类型"}).functionValidator({
		    fun:function(val,elem){
		        if(val=="1001"){
		           $("#pclientcode").attr({"disabled":true});
		           $("#pclientbalance").attr({"disabled":true});
		           $("#pclientcode").val(" ");
		           $("#pclientbalance").val(" ");
			    }else{
			       $("#pclientcode").attr({"disabled":false});
		           $("#pclientbalance").attr({"disabled":false});
		           $("#pclientbalance").formValidator({onshow:"结算方式",onfocus:"结算方式",oncorrect:" "}).inputValidator({min:1,onerror:"结算方式不能为空"});
			    }
			    return true;
			}
		});
		
		//$("#pclientcode").formValidator({onfocus:"客户代码",oncorrect:"√"}).regexValidator({regexp:"^\\S+$",onerror:"客户代码不能为空,请确认"});
		
	});
	//保存数据
	function save(action){
	    if($.formValidator.pageIsValid("1")){
			var url ='<%=path%>/archives/user.do?action='+action;
			document.forms[0].action=url;
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
//初始化信息
//changeCombo('pprovince','','provincevalue');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
var tmp=<s:property value="%{user.PROVINCE}"/>;
if(tmp=="")
{
 tmp=350000;
}
document.getElementById('pprovince').value=tmp;
changeCombo('pcity','pprovince','cityvalue');
document.getElementById('pcity').value= <s:property value="%{user.CITY}"/>;
changeCombo('pcounty','pcity','countyvalue');
document.getElementById('pcounty').value=<s:property value="%{user.COUNTY}"/>;
	</script>
</body>
</html>