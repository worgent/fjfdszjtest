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
<form  method='POST' name='form1' action='/archives/serTime.do'  id="form1">
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
													服务时间</span></strong></td>
													<td height="37">　</td>
												</tr>
												<tr align="left">
													<td height="16" colspan="2" >
													<s:actionerror theme="ems" />
													<s:actionmessage theme="ems" />
													</td>
												</tr>
												<tr align="left">
													    <TD width="95"><font size="2">地址:</font></TD>
													    <TD width="524"><font size="2">省份: </font>
														<select id="pprovince" name="search.pprovince" onchange="changeCombo('pcity','pprovince','cityvalue');clearCombo('pcounty')"></select>
														<font size="2"> 地市：</font>
														<select id="pcity" name="search.pcity" onchange="changeCombo('pcounty','pcity','countyvalue')"></select>
														<font size="2"> 县、区：</font>
														<select id="pcounty" name="search.pcounty"></select>
														<font size="2"> </font>
														<div id="paddressTip" style="display: inline "></div>	 
														</TD>
												</tr>
												
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													服务开始时间</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:textfield id="pserverstime"  name="search.pserverstime" value="%{serTime.SERVERSTIME}" readonly="true"></s:textfield>
												</span>  <div id="pserverstimeTip" style="display: inline "></div></td>
												</tr>
												
												<tr align="left">
													<td width="98" align="left">
													<span style="FONT-SIZE: 9pt">
													服务终止时间</span><span style="font-size: 9pt">：</span></td>
													<td align="left">
													<span style="font-size: 9pt">
														<s:textfield id="pserveretime"  name="search.pserveretime" value="%{serTime.SERVERETIME}" readonly="true"></s:textfield>
												</span><div id="pserveretimeTip" style="display: inline "></div></td>
												</tr>
												<tr>
													<td width="98">　</td>
													<td>　</td>
												</tr>
												<tr>
													<td width="98">
													 <s:hidden value="%{serTime.ID}" name="search.pid" id="pid"></s:hidden>
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
<!-- 引入日期控件及验证 -->
<script defer="defer" src="<%=path%>/js/datepicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript" src="<%=path%>/js/DateTimeMask.js" type="text/javascript"></script>
 <script type="text/javascript">
  //s:select id="pclientbalance"  name="search.pclientbalance" list="clientbalanceList" headerKey="-1" headerValue="---请选择---" listKey="ID" listValue="NAME" key="ID">s:select
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
		$("#pserverstime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入的服务开始时间",onfocus:"请输入的预约时间",oncorrect:"√"}).inputValidator({min:"00:00:00",max:"23:59:59",type:"time",onerror:"日期必须在\"1900-01-01\"和\"3000-01-01\"之间"});//.defaultPassed();
		$("#pserveretime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入的服务结束时间",onfocus:"请输入的预约时间",oncorrect:"√"}).inputValidator({min:"00:00:00",max:"23:59:59",type:"time",onerror:"日期必须在\"1900-01-01\"和\"3000-01-01\"之间"});//.defaultPassed();
		$("#pprovince").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:def_Erroraddress});
		$("#pcity").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:def_Erroraddress});
		$("#pcounty").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:def_Erroraddress});
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


//初始化信息
changeCombo('pprovince','','provincevalue');
//document.getElementById('pprovince').options[12].selected;

//默认福建省
document.getElementById('pprovince').value="350000";
<s:if test="%{action=='update'}">
document.getElementById('pprovince').value=<s:property value="%{serTime.PROVINCE}"/>;
</s:if>
changeCombo('pcity','pprovince','cityvalue');
<s:if test="%{action=='update'}">
document.getElementById('pcity').value=<s:property value="%{serTime.CITY}"/>;
</s:if>
changeCombo('pcounty','pcity','countyvalue');
<s:if test="%{action=='update'}">
document.getElementById('pcounty').value=<s:property value="%{serTime.COUNTY}"/>;
</s:if>
	</script>
</body>
</html>