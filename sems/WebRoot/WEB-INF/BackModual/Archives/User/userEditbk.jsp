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
   <td> 
       <strong>用户审核</strong>
   </td>
   </tr>
    <tr>
	<td width="20%">
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
	</td>
  </tr>
   <tr>
      <td>用户名：
      		<s:property value="%{user.CODE}" />
      </td>
   </tr>

  <tr> 
   <td>姓名:
   <s:property value="%{user.NAME}"/>
   </td>
   </tr>
   
   <tr> 
   <td>固定电话:
   <s:property value="%{user.TEL}"/>
   </td>
   </tr>
   
   
   
   <tr> 
   <td>手机:
   <s:property value="%{user.MOBILE}"/>
   </td>
   </tr>

   <tr> 
   <td align="left">
		<font color="red">选填</font>
   </td>
   </tr>
   <tr> 
   <td>电子邮箱:
   &nbsp;<s:property value="%{user.EMAIL}"/>
   </td>
   </tr>
   
   
   <tr>
   <td  align="left"><font color="red">审核人员操作</font></td>
   </tr>
  
   <tr> 
   <td>单位名称:
   	<s:textfield name="search.punit" id="punit" value="%{user.UNIT}" ></s:textfield>
   <div id="punitTip" style="display: inline "></div></td>
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
   <td>
      详细地址:
    <s:textfield id="paddress"  name="search.paddress" value="%{user.ADDRESS}"></s:textfield>
   </td>
   </tr>
   <tr> 
   <td>客户类型:
   <select name="search.pclienttype" id="pclienttype">
 		<option value="">请选择客户类型</option>
   		<s:iterator id="list"  value="%{clienttypeList}">
			<option value="<s:property value="#list.ID" />"  <s:if test="%{#list.ID==user.CLIENTTYPE}">selected</s:if> >
					<s:property value="#list.NAME" />
			</option>
		</s:iterator>
   </select>
   <div id="pclienttypeTip" style="display: inline "></div></td>
   </tr>  
 
   <tr> 
   <td>客户代码:
   <s:textfield id="pclientcode"  name="search.pclientcode" value="%{user.CLIENTCODE}"></s:textfield>
   <div id="pclientcodeTip" style="display: inline "></div></td>
   </tr>   
 
   <tr> 
   <td>结算方式:
      <select name="search.pclientbalance" id="pclientbalance">
        <option value="">请选择结算方式</option>
   		<s:iterator id="list"  value="%{clientbalanceList}">
			<option value="<s:property value="#list.ID" />"  <s:if test="%{#list.ID==user.CLIENTBALANCE}">selected</s:if> >
					<s:property value="#list.NAME" />
			</option>
		</s:iterator>
	 </select>
   <div id="pclientbalanceTip" style="display: inline "></div></td>
   </tr>  
   
   <tr>
   <td align="center">
      <s:hidden value="%{user.ID}" name="search.pid" id="pid"></s:hidden>
      <input type="button" id="btnupdate" name="btnupdate"  onclick="javascript:save('update');"  value="保 存"  />
      <s:if test="%{user.BILL_TYPE==0}">
      &nbsp; &nbsp; &nbsp;<input type="button" id="btnupdateauding" name="btnupdateauding"  onclick="javascript:save('saveauding');"  value="保存并审核"  />
	  </s:if>
      &nbsp; &nbsp; &nbsp;<input type="reset"  id="btnreset"  name="btnreset" value="重置"  />
      <input type="button" onclick="javascript:history.go(-1)" value="返回"/>
      </td>
   </tr>
 </table>
</form>
</center>
 <script type="text/javascript">
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确认保存');return true;}});
		$("#pprovince").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pcity").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pcounty").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#paddress").formValidator({onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	    //客户类型, 客户代码-结算方式的关系
		$("#pclienttype").formValidator({onfocus:"客户类型",oncorrect:"√"}).inputValidator({min:1,onerror:"客户类型不能为空,请确认"}).functionValidator({
		    fun:function(val,elem){
		        if(val=="1001"){
		           $("#pclientcode").attr({"disabled":true});
		           $("#pclientbalance").attr({"disabled":true});
		           $("#pclientcode").val(" ");
		           $("#pclientbalance").val("1234");
			    }else{
			       $("#pclientcode").attr({"disabled":false});
		           $("#pclientbalance").attr({"disabled":false});
			    }
			    return true;
			}
		});
		
		//$("#pclientcode").formValidator({onfocus:"客户代码",oncorrect:"√"}).regexValidator({regexp:"^\\S+$",onerror:"客户代码不能为空,请确认"});
		//$("#pclientbalance").formValidator({onfocus:"结算方式",oncorrect:"√"}).regexValidator({regexp:"^\\S+$",onerror:"结算方式不能为空,请确认"});
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