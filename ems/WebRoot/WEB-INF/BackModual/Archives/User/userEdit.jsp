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
       <strong>用户审核</strong>
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
      <td>用户名：</td>
      <td>     
      		<s:property value="%{user.CODE}" />
      </td>
      <td>&nbsp;</td>
   </tr>

  <tr> 
   <td>姓名:</td>
   <td>
   <s:property value="%{user.NAME}"/>
   <font color="red">*</font>
   </td>
   <td><div id="pnameTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td>固定电话:</td>
   <td>
   区号:
   <s:property value="%{user.AREACODE}"  />
   电话号码:
   <s:property value="%{user.TEL}"/>
   </td>
   <td><div id="ptelTip" style="width:250px"></div></td>
   </tr>
   
   
   
   <tr> 
   <td>手机:</td>
   <td>
   <s:property value="%{user.MOBILE}"/>
   <font color="red">*</font>
   </td>
   <td><div id="pmobileTip" style="width:250px"></div></td>
   </tr>

   <tr> 
   <td>
		选填
   </td>
   </tr>
   <tr> 
   <td>电子邮箱:</td>
   <td>
   <s:property value="%{user.EMAIL}"/>
   </td>
   <td><div id="pemailTip" style="width:250px"></div></td>
   </tr>
   
   
   <tr>
   <td>审核人员操作</td>
   </tr>
  
   <tr> 
   <td>单位名称:</td>
   <td>
   	<s:textfield name="search.punit" id="punit" value="%{user.UNIT}" ></s:textfield>
   </td>
   <td><div id="punitTip" style="width:250px"></div></td>
   </tr>
 
   <tr> 
   <td rowspan="2">地址:</td>
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
    <s:textfield id="paddress"  name="search.paddress" value="%{user.ADDRESS}"></s:textfield>
   <font color="red">*</font>
   </td>
   </tr>    
 
   
   <tr> 
   <td>客户类型:</td>
   <td>
   <select name="search.pclienttype" id="pclienttype">
 		<option value="">请选择客户类型</option>
   		<s:iterator id="list"  value="%{clienttypeList}">
			<option value="<s:property value="#list.ID" />"  <s:if test="%{#list.ID==user.CLIENTTYPE}">selected</s:if> >
					<s:property value="#list.NAME" />
			</option>
		</s:iterator>
   </select>
   <font color="red">*</font>
   </td>
   <td><div id="pclienttypeTip" style="width:250px"></div></td>
   </tr>  
 
   <tr> 
   <td>客户代码:</td>
   <td>
   <s:textfield id="pclientcode"  name="search.pclientcode" value="%{user.CLIENTCODE}"></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="pclientcodeTip" style="width:250px"></div></td>
   </tr>   
 
   <tr> 
   <td>结算方式:</td>
   <td>
      <select name="search.pclientbalance" id="pclientbalance">
        <option value="">请选择结算方式</option>
   		<s:iterator id="list"  value="%{clientbalanceList}">
			<option value="<s:property value="#list.ID" />"  <s:if test="%{#list.ID==user.CLIENTBALANCE}">selected</s:if> >
					<s:property value="#list.NAME" />
			</option>
		</s:iterator>
	 </select>
   <font color="red">*</font>
   </td>
   <td><div id="pclientbalanceTip" style="width:250px"></div></td>
   </tr>  
   
   <tr>
   <td colspan="3" align="center">
      <s:hidden value="%{user.ID}" name="search.pid" id="pid"></s:hidden>
      <input type="button" id="btnupdate" name="btnupdate"  onclick="javascript:save('update');"  value="保 存"  />
      &nbsp; &nbsp; &nbsp;<input type="button" id="btnupdateauding" name="btnupdateauding"  onclick="javascript:save('updateauding');"  value="保存并审核"  />
      &nbsp; &nbsp; &nbsp;<input type="reset"  id="btnreset"  name="btnreset" value="重置"  /></td>
   </tr>
 </table>
</form>
</center>
 <script type="text/javascript">
  //s:select id="pclientbalance"  name="search.pclientbalance" list="clientbalanceList" headerKey="-1" headerValue="---请选择---" listKey="ID" listValue="NAME" key="ID">s:select
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确认保存');return true;}});
		$("#pprovince").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pcity").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pcounty").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#paddress").formValidator({onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	
		$("#pclienttype").formValidator({onfocus:"客户类型",oncorrect:"√"}).inputValidator({min:1,onerror:"客户类型不能为空,请确认"});
		$("#pclientcode").formValidator({onfocus:"客户代码",oncorrect:"√"}).inputValidator({min:1,onerror:"客户代码不能为空,请确认"});
		$("#pclientbalance").formValidator({onfocus:"结算方式",oncorrect:"√"}).inputValidator({min:1,onerror:"结算方式不能为空,请确认"});
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
    cdsales.load("/archives/area.do?action="+action+"&search.pid="+parmid);
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
//初始化信息
changeCombo('pprovince','','provincevalue');
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