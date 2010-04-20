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
<form  method='POST' name='form1' action='/net/printConfig.do?action=update'  id="form1">
   <table width="90%" height="190" border="1">
   <tr> 
   <td> 
       <strong>修改打印配置信息</strong>
   </td>
   </tr>
    <tr>
	<td>
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
	</td>

  </tr>
   <tr>
      <td>用户名 
      		<s:property value="%{search.CODE}" />
     </td>
   </tr>
   <tr> 
   <td>左边距:
   <s:textfield name="search.pleft_margin" id="pleft_margin" value="%{search.LEFT_MARGIN}"></s:textfield>
   <div id="pleft_marginTip" style="width:250px"></div></td>
   </tr>
   <tr> 
   <td>上边距:
   	<s:textfield id="ptop_margin"  name="search.ptop_margin" value="%{search.TOP_MARGIN}"></s:textfield>
   <div id="ptop_marginTip" style="width:250px"></div></td>
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
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
		$("#pleft_margin").formValidator({onfocus:"左边距",oncorrect:"√"}).regexValidator({regexp:"^[1-9]\\d*|0$",onerror:"左边距格式不正确"});
		$("#ptop_margin").formValidator({onfocus:"上边距",oncorrect:"√"}).regexValidator({regexp:"^[1-9]\\d*|0$",onerror:"上边距格式不正确"});
	});
</script>
</body>
</html>