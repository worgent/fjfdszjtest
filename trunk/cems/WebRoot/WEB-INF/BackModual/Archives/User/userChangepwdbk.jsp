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
<form  method='POST' name='form1' action='/archives/user.do?action=changepwd'  id="form1">
   <table width="90%" height="190" border="1">
   <tr> 
   <td> 
       <strong>修改密码</strong>
   </td>
   </tr>
    <tr>
	<td>
		<s:actionerror theme="ems" />
		&nbsp;<s:actionmessage theme="ems" />
	</td>
  </tr>
   <tr>
      <td>旧密码：
      <s:password id="ppasswd"  name="search.ppasswd"></s:password>
      <div id="ppasswdTip" style="display: inline "></div></td>
   </tr>

   <tr>
   <td>新密码:
       <s:password id="pnewpasswd"  name="search.pnewpasswd"></s:password>
  <div id="pnewpasswdTip" style="display: inline "></div></td>
   </tr>
   
   <tr>
   <td>密码确认:
     <s:password  id="prepasswd"  name="search.prepasswd"  ></s:password>
   <div id="prepasswdTip" style="display: inline "></div></td>
   </tr>
   <tr>
   <s:hidden name="search.pid" id="pid" value="%{search.ID}"></s:hidden>
   <td align="center">
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
		$("#ppasswd").formValidator({onshow:"请输入旧密码",onfocus:"旧密码不能为空",oncorrect:"√"}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"旧密码有误"});
		$("#pnewpasswd").formValidator({onfocus:"密码长度为6~20个字符",oncorrect:"√"}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"新密码有误"});
		$("#prepasswd").formValidator({onfocus:"两次密码必须一致",oncorrect:"√"}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"pnewpasswd",operateor:"=",onerror:"两次密码不一致,请确认"});
	});
</script>
</body>
</html>