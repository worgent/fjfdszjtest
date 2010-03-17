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
<form  method='POST' name='form1' action='/archives/serTime.do'  id="form1">
   <table width="90%" height="190" border="1">
   <tr>
   <td> 
       <strong>服务时间</strong>
   </td>
   </tr>
    <tr>
	<td>
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
	</td>
  </tr>

   <tr> 
   <td>地址:
                         省份:
                       <select id="pprovince" name="search.pprovince" onchange="changeCombo('pcity','pprovince','cityvalue')">
						</select>
                        地市：<select id="pcity" name="search.pcity"   onchange="changeCombo('pcounty','pcity','countyvalue')">
						</select>
					  县、区：<select id="pcounty" name="search.pcounty" >
						</select>
	<div id="paddressTip" style="display: inline "></div></td>
   </tr>  
 
   <tr> 
   <td>服务开始时间:
   <s:textfield id="pserverstime"  name="search.pserverstime" value="%{serTime.SERVERSTIME}" readonly="true"></s:textfield>
    <div id="pserverstimeTip" style="display: inline "></div></td>
   </tr> 
    
   <tr> 
   <td>服务终止时间:
   <s:textfield id="pserveretime"  name="search.pserveretime" value="%{serTime.SERVERETIME}" readonly="true"></s:textfield>
   <div id="pserveretimeTip" style="display: inline "></div></td>
   </tr>    

   <tr>
   <td align="center">
      <s:hidden value="%{serTime.ID}" name="search.pid" id="pid"></s:hidden>
      <s:hidden value="%{action}" name="action" id="action"></s:hidden>
      <s:submit value="保 存"></s:submit>
      &nbsp; &nbsp; &nbsp;<input type="reset"  id="btnreset"  name="btnreset" value="重置"  />
      <input type="button" onclick="javascript:history.go(-1)" value="返回"/>
      </td>
   </tr>
 </table>
</form>
</center>
<!-- 引入日期控件及验证 -->
<script defer="defer" src="<%=path%>/js/datepicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript" src="<%=path%>/js/DateTimeMask.js" type="text/javascript"></script>
 <script type="text/javascript">
  //s:select id="pclientbalance"  name="search.pclientbalance" list="clientbalanceList" headerKey="-1" headerValue="---请选择---" listKey="ID" listValue="NAME" key="ID">s:select
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确认保存');return true;}});
		$("#pserverstime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入的服务开始时间",onfocus:"请输入的预约时间",oncorrect:"√"}).inputValidator({min:"00:00:00",max:"23:59:59",type:"time",onerror:"日期必须在\"1900-01-01\"和\"3000-01-01\"之间"});//.defaultPassed();
		$("#pserveretime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入的服务结束时间",onfocus:"请输入的预约时间",oncorrect:"√"}).inputValidator({min:"00:00:00",max:"23:59:59",type:"time",onerror:"日期必须在\"1900-01-01\"和\"3000-01-01\"之间"});//.defaultPassed();
		$("#pprovince").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pcity").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pcounty").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
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