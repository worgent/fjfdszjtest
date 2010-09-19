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
   <td> 
       <strong>修改用户信息</strong>
   </td>
   </tr>
    <tr>
	<td>
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
	</td>
  </tr>
   <tr>
      <td>用户名：
      		<s:property value="%{search.CODE}" />
     </td>
   </tr>
   <tr>
      <td>客户代码：
      		<s:property value="%{search.CLIENTCODE}" />
      </td>
   </tr> 
   <tr> 
   <td>姓名:
   	<s:textfield id="pname"  name="search.pname" value="%{search.NAME}"></s:textfield>
   <div id="pnameTip" style="display: inline "></div></td>
   </tr>
   <tr> 
   <td>固定电话:
   <s:textfield name="search.ptel" id="ptel" value="%{search.TEL}"></s:textfield>
   <div id="ptelTip" style="display: inline "></div></td>
   </tr>
   
   
   
   <tr> 
   <td>手机:
   	<s:textfield id="pmobile"  name="search.pmobile" value="%{search.MOBILE}"></s:textfield>
   <div id="pmobileTip" style="display: inline "></div></td>
   </tr>
   
   <tr> 
   <td align="left">
		<font color="red">选填</font>
   </td>
   </tr>
   <tr> 
   <td>单位名称:
   	<s:textfield name="search.punit" id="punit" value="%{search.UNIT}" ></s:textfield>
   <div id="punitTip" style="display: inline "></div></td>
   </tr>
   
   <tr> 
   <td>电子邮箱:
   <s:textfield name="search.pemail" id="pemail"  value="%{search.EMAIL}"></s:textfield>
   <div id="pemailTip" style="display: inline "></div></td>
   </tr>

   <tr>
   <s:hidden name="search.pid" id="pid" value="%{search.ID}"></s:hidden>
   <td  align="center">
     <s:submit id="saveId" value="保 存"></s:submit> &nbsp;<s:reset id="setId" value="重 置"></s:reset>
   </td>
   </tr>
 </table>
</form>
</center>
 <script type="text/javascript">
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){
			   if($("#ptel").val()==""&&$("#pmobile").val()=="")
			   {
				   alert('电话和手机必填一项');
				   return false;	   
			   }else
			   {
				   alert('确认保存!');
				   return true;
			   }
		}});
		$("#pname").formValidator({onfocus:"最多20个字符",oncorrect:"√"}).inputValidator({min:1,max:20,onerror:"姓名非法,请确认"});
		$("#ptel").formValidator({onfocus:"电话号码或小灵通,例059512345678",oncorrect:"√"}).regexValidator({regexp:"^\\d{10,12}$",onerror:"电话号码格式不正确"});
		$("#pmobile").formValidator({onfocus:"手机号",oncorrect:"√"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机号码格式不正确"});
		$("#pprovince").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	
		$("#paddress").formValidator({onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pverifycode").formValidator({onfocus:"验证码",oncorrect:"√"}).inputValidator({min:1,max:4,onerror:"验证码为4位"});
		$("#pemail").formValidator({onfocus:"电子邮箱",oncorrect:"√"}).regexValidator({regexp:"emailex",datatype:"enum",onerror:"电子邮箱格式不正确"});
	});	
</script>
</body>
</html>