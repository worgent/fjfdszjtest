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
	<head>

 <script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确定保存。。。');return true;}});
	$("#username").formValidator({onfocus:"用户名至少6个字符,最多16个字符",oncorrect:"输入格式正确"}).inputValidator({min:6,max:16,onerror:"你输入的用户名非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"});
	$("#password").formValidator({onshow:"请输入密码",onfocus:"密码不能为空",oncorrect:"密码合法"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
	$("#repassword").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致哦",oncorrect:"密码一致"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"password",operateor:"=",onerror:"两次密码不一致,请确认"});
	$("#position").formValidator({onshow:"请输入地图位置标注",onfocus:"范围1到16个汉字",oncorrect:"格式正确"}).inputValidator({min:1,max:16,onerror:"输入地图位置标注非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"格式不正确"});
	$("#realName").formValidator({onshow:"请输入猎友实名",onfocus:"范围1到10个汉字",oncorrect:"格式正确"}).inputValidator({min:1,max:10,onerror:"输入猎友实名非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"格式不正确"});
	});
	</script>
</head>
	<body>

<center>
<form  method='POST' name='form1' action='/user.do?action=edit'  id="form1">

   <table >
    <tr>
   <td>&nbsp;</td>
      <td  align="center">
         恭喜你,已经注册成功。。。。。
      </td>
   <td><input type="hidden" name="search.userName" /></td>
   </tr>
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
       <a href="/user.do?action=enter">返回登录</a>
      
      </td>
   <td>&nbsp;</td>
   </tr>
 </table>

</form>
</center>
</body>
</html>