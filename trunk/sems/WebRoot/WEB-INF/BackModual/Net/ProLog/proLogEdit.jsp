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
<body>
<center>
<form  method='POST' name='form1' action='/archives/user.do?action=update'  id="form1">
   <table width="90%" height="190" border="1">
   <tr>
   <td colspan="3"> 
       <strong>网上寄件</strong>
   </td>
   </tr>
    <tr>
	<td>
		<s:actionerror theme="ems" />
	</td>
	<td>
		<s:actionmessage theme="ems" />
	</td>
  </tr>
   <tr> 
   <td>联系人:</td>
   <td>
   <s:textfield id="pname"  name="search.pname" value="%{order.NAME}"></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="pnameTip" style="width:250px"></div></td>
   </tr>   
   
   <tr> 
   <td>固定电话:</td>
   <td>
   区号:
   <s:textfield id="pareacode"  name="search.pareacode" value="%{order.AREACODE}"></s:textfield>
   <font color="red">*</font>
   电话号码:
   <s:textfield id="ptel"  name="search.ptel" value="%{order.TEL}" ></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="ptelTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td>手机:</td>
   <td>
   <s:textfield id="pmobile"  name="search.pmobile" value="%{order.MOBILE}"></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="pmobileTip" style="width:250px"></div></td>
   </tr>

 
   <tr> 
   <td rowspan="2">取件地址:</td>
   <td>
                         省份:
                       <select id="pprovince" name="search.pprovince" onchange="changeCombo('pcity','pprovince','cityvalue')">
						</select>
                        地市：<select id="pcity" name="search.pcity"   onchange="changeCombo('pcounty','pcity','countyvalue')">
						</select>
					  县、区：<select id="pcounty" name="search.pcounty" >
						</select>
   </td>
   <td rowspan="2"><div id="paddressTip" style="width:250px"></div></td>
   </tr>
   <tr> 
   <td>
      详细地址:
    <s:textfield id="paddress"  name="search.paddress" value="%{order.ADDRESS}"></s:textfield>
   <font color="red">*</font>
   </td>
   </tr>    
 
   
   <tr> 
   <td>邮件种类:</td>
   <td>
   <select name="search.pmailtype" id="pmailtype">
 		<option value="-1">请选择</option>
 		<option value="0" <s:if test="%{order.MAILTYPE==0}">selected</s:if>>物品型</option>
 		<option value="1" <s:if test="%{order.MAILTYPE==1}">selected</s:if>>文件型</option>
   </select>
   <font color="red">*</font>
   </td>
   <td><div id="pmailtypeTip" style="width:250px"></div></td>
   </tr>  
 
   <tr> 
   <td>预约时间:</td>
   <td>
   <s:textfield id="pbookingtime"  name="search.pbookingtime" value="%{order.BOOKINGTIME}" readonly="true"></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="pbookingtimeTip" style="width:250px"></div></td>
   </tr>   

   <tr> 
   <td>寄件数量:</td>
   <td>
   <s:textfield id="porderingnum"  name="search.porderingnum" value="%{order.ORDERINGNUM}"></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="porderingnumTip" style="width:250px"></div></td>
   </tr>   
   
   <tr> 
   <td>物品重量:</td>
   <td>
   <s:textfield id="porderingweight"  name="search.porderingweight" value="%{order.ORDERINGWEIGHT}"></s:textfield>(千克)
   <font color="red">*</font>
   </td>
   <td><div id="porderingweightTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td>客户要求:</td>
   <td>
   <s:textfield  id="pclientremark"  name="search.pclientremark" value="%{order.CLIENTREMARK}"></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="pclientremarkTip" style="width:250px"></div></td>
   </tr>     

   <tr> 
   <td>收件人地址:</td>
   <td>
                         省份:
                       <select id="precprovince" name="search.precprovince" onchange="changeCombo('preccity','precprovince','cityvalue')">
						</select>
                        地市：<select id="preccity" name="search.preccity">
						</select>
   </td>
   <td><div id="precaddressTip" style="width:250px"></div></td>
   </tr>
   
      
   <tr>
   <td colspan="3" align="center">
      <s:hidden value="%{order.ID}" name="search.pid" id="pid"></s:hidden>
      <input type="button" id="btnupdate" name="btnupdate"  onclick="javascript:save('<s:property value="%{action}"/>');"  value="保 存"  />
      &nbsp; &nbsp; &nbsp;<input type="button" id="btnupdateauding" name="btnupdateauding"  onclick="javascript:save('send');"  value="保存并寄件"  />
      &nbsp; &nbsp; &nbsp;<input type="reset"  id="btnreset"  name="btnreset" value="重置"  /></td>
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
		$("#pname").formValidator({onfocus:"最多20个字符",oncorrect:"√"}).inputValidator({min:1,max:20,onerror:"姓名非法,请确认"});
		$("#pareacode").formValidator({tipid:"ptelTip",onfocus:"地区区号3位或4位数字",oncorrect:"√"}).regexValidator({regexp:"^\\d{3,4}$",onerror:"地区区号不正确"});
		$("#ptel").formValidator({onfocus:"电话号码或小灵通",oncorrect:"√"}).regexValidator({regexp:"tel",datatype:"enum",onerror:"电话号码格式不正确"});
		$("#pmobile").formValidator({onfocus:"手机号",oncorrect:"√"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机号码格式不正确"});
		$("#paddress").formValidator({onfocus:"取件地址",oncorrect:"√"}).inputValidator({min:1,onerror:"取件地址不能为空,请确认"});
		$("#pmailtype").formValidator({onfocus:"邮件种类",oncorrect:"√"}).inputValidator({min:1,onerror:"邮件种类不能为空,请确认"});
		$("#pbookingtime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入的预约时间",onfocus:"请输入的预约时间",oncorrect:"√"}).inputValidator({min:"2008-01-01 00:00:00",max:"2010-01-01 23:59:59",type:"datetime",onerror:"日期必须在\"1900-01-01\"和\"3000-01-01\"之间"});//.defaultPassed();
		$("#porderingnum").formValidator({onfocus:"寄件数量",oncorrect:"√"}).regexValidator({regexp:"num",datatype:"enum",onerror:"寄件数量格式不正确"});
		$("#porderingweight").formValidator({onfocus:"物品重量",oncorrect:"√"}).regexValidator({regexp:"decmal4",datatype:"enum",onerror:"物品重量格式不正确"});
		$("#pclientremark").formValidator({onfocus:"客户要求",oncorrect:"√"}).inputValidator({min:1,onerror:"客户要求不能为空,请确认"});
		$("#precprovince").formValidator({tipid:"precaddressTip",onfocus:"收件通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#preccity").formValidator({tipid:"precaddressTip",onfocus:"收件通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	});
	
	//保存数据
	function save(action){
	    if($.formValidator.pageIsValid("1")){
			var url ='<%=path%>/net/order.do?action='+action;
			document.forms[0].action=url;
			document.forms[0].submit();
		}else
		{
		  return false;
		}
		/*
		var url ='<%=path%>/archives/user.do?action='+action;
		document.forms[0].action=url;
		document.forms[0].submit();
		*/
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
       for(var i=0;i<bi.length;i++){     
                document.getElementById(chCombo).length=i+1; 
	   			document.getElementById(chCombo).options[i].value = bi[i].selectSingleNode("THE_CODE").text;//隐藏值
	   			document.getElementById(chCombo).options[i].text =  bi[i].selectSingleNode("THE_NAME").text;//显示值
       }
    }else{
       document.getElementById(chCombo).options.length = 1;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
    }
}
//初始化信息(取件地址)
changeCombo('precprovince','','provincevalue');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
//var tmp=<s:property value="%{order.RECPROVINCE}"/>;
var tmp="";
if(tmp=="")
{
 tmp=350000;
}
document.getElementById('precprovince').value=tmp;
changeCombo('preccity','precprovince','cityvalue');
//document.getElementById('preccity').value= <s:property value="%{order.RECCITY}"/>;
	</script>
</body>
</html>