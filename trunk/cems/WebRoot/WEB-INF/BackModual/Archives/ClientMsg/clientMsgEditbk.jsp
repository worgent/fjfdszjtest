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
<form  method='POST' name='form1' action='/archives/clientMsg.do'  id="form1">
   <table width="90%" height="190" border="1">
   <tr>
   <td> 
       <strong>取件联系人</strong>
   </td>
   </tr>
    <tr>
	<td>
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
	</td>
  </tr>
   <tr> 
   <td>联系人:
   <s:textfield id="pname"  name="search.pname" value="%{clientMsg.NAME}"></s:textfield>
   <div id="pnameTip" style="display: inline "></div></td>
   </tr>   
   
   <tr> 
   <td>固定电话:
   <s:textfield id="ptel"  name="search.ptel" value="%{clientMsg.TEL}" ></s:textfield>
   <div id="ptelTip" style="display: inline "></div></td>
   </tr>
   
   <tr> 
   <td>手机:
   <s:textfield id="pmobile"  name="search.pmobile" value="%{clientMsg.MOBILE}"></s:textfield>
   <div id="pmobileTip" style="display: inline "></div></td>
   </tr>
	
   <tr> 
   <td>单位名称:
   <s:textfield id="punit"  name="search.punit" value="%{clientMsg.UNIT}"></s:textfield>
	<div id="punitTip" style="display: inline "></div></td>
   </tr>
      
   <tr>
   <td   align="center">
      <s:hidden value="%{clientMsg.ID}" name="search.pid" id="pid"></s:hidden>
      <s:hidden value="%{action}" name="action" id="action"></s:hidden>
      <s:submit value="保 存"></s:submit>
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
		$("#pname").formValidator({onfocus:"最多20个字符",oncorrect:"√"}).inputValidator({min:1,max:20,onerror:"姓名非法,请确认"});
		$("#ptel").formValidator({onfocus:"电话号码或小灵通,例059512345678",oncorrect:"√"}).regexValidator({regexp:"^\\d{10,12}$",onerror:"电话号码格式不正确"});
		$("#pmobile").formValidator({onfocus:"手机号",oncorrect:"√"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机号码格式不正确"});
	});
	</script>
</body>
</html>