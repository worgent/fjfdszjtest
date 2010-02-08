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
<form  method='POST' name='form1' action='/archives/clientMsg.do'  id="form1">
   <table width="90%" height="190" border="1">
   <tr>
   <td colspan="3"> 
       <strong>取件联系人</strong>
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
   <s:textfield id="pname"  name="search.pname" value="%{clientMsg.NAME}"></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="pnameTip" style="width:250px"></div></td>
   </tr>   
   
   <tr> 
   <td>固定电话:</td>
   <td>
   区号:
   <s:textfield id="pareacode"  name="search.pareacode" value="%{clientMsg.AREACODE}"></s:textfield>
   <font color="red">*</font>
   电话号码:
   <s:textfield id="ptel"  name="search.ptel" value="%{clientMsg.TEL}" ></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="ptelTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td>手机:</td>
   <td>
   <s:textfield id="pmobile"  name="search.pmobile" value="%{clientMsg.MOBILE}"></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="pmobileTip" style="width:250px"></div></td>
   </tr>
	
   <tr> 
   <td>单位名称:</td>
   <td>
   <s:textfield id="punit"  name="search.punit" value="%{clientMsg.UNIT}"></s:textfield>
   <font color="red">*</font>
   </td>
   <td><div id="punitTip" style="width:250px"></div></td>
   </tr>
      
   <tr>
   <td colspan="3" align="center">
      <s:hidden value="%{clientMsg.ID}" name="search.pid" id="pid"></s:hidden>
      <s:hidden value="%{action}" name="action" id="pid"></s:hidden>
      <s:submit value="保 存"></s:submit>
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
		/*
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
		*/
	});
	</script>
</body>
</html>