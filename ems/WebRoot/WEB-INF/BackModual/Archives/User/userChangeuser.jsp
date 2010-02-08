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
<form  method='POST' name='form1' action='/archives/user.do?action=changeuser'  id="form1">
   <table width="90%" height="190" border="1">
   <tr> 
   <td colspan="3"> 
       <strong>修改用户信息</strong>
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
      		<s:property value="%{search.CODE}" />
      </td>
      <td>&nbsp;</td>
   </tr>
   <tr>
      <td>客户代码：</td>
      <td>     
      		<s:property value="%{search.CLIENTCODE}" />
      </td>
      <td>&nbsp;</td>
   </tr> 


   
   <tr> 
   <td>固定电话:</td>
   <td>
   区号:
   <s:textfield id="pareacode"  name="search.pareacode"  value="%{search.AREACODE}" ></s:textfield>
   电话号码:
   <s:textfield name="search.ptel" id="ptel" value="%{search.TEL}"></s:textfield>
   </td>
   <td><div id="ptelTip" style="width:250px"></div></td>
   </tr>
   
   
   
   <tr> 
   <td>手机:</td>
   <td>
   	<s:textfield id="pmobile"  name="search.pmobile" value="%{search.MOBILE}"></s:textfield>
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
   <td>单位名称:</td>
   <td>
   	<s:textfield name="search.punit" id="punit" value="%{search.UNIT}" ></s:textfield>
   </td>
   <td><div id="punitTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td>电子邮箱:</td>
   <td>
   <s:textfield name="search.pemail" id="pemail"  value="%{search.EMAIL}"></s:textfield>
   </td>
   <td><div id="pemailTip" style="width:250px"></div></td>
   </tr>

   <tr>
   <s:hidden name="search.pid" id="pid" value="%{search.ID}"></s:hidden>
   <td colspan="3" align="center">
     <s:submit id="saveId" value="保 存"></s:submit> &nbsp;<s:reset id="setId" value="重 置"></s:reset>
   </td>
   </tr>
 </table>
</form>
</center>
 <script type="text/javascript">
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确认修改');return true;}});
		$("#pname").formValidator({onfocus:"最多20个字符",oncorrect:"√"}).inputValidator({min:1,max:20,onerror:"姓名非法,请确认"});
		$("#pareacode").formValidator({tipid:"ptelTip",onfocus:"地区区号3位或4位数字",oncorrect:"√"}).regexValidator({regexp:"^\\d{3,4}$",onerror:"地区区号不正确"});
		$("#ptel").formValidator({onfocus:"电话号码或小灵通",oncorrect:"√"}).regexValidator({regexp:"tel",datatype:"enum",onerror:"电话号码格式不正确"});
		$("#pmobile").formValidator({onfocus:"手机号",oncorrect:"√"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机号码格式不正确"});
		$("#pprovince").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	
		$("#paddress").formValidator({onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pverifycode").formValidator({onfocus:"验证码",oncorrect:"√"}).inputValidator({min:1,max:4,onerror:"验证码为4位"});
		$("#pemail").formValidator({onfocus:"电子邮箱",oncorrect:"√"}).regexValidator({regexp:"email",datatype:"enum",onerror:"电子邮箱格式不正确"});
	});	
</script>
</body>
</html>