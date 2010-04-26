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
	     function isEmpty(obj){
	      var  appealmerchant=document.getElementById("appealmerchant").value; 
	       if (appealmerchant=='')
	       {
	       alert("要申诉的商家名称不能为空!");
	       return false;
	       }
	
	      var  introduction=document.getElementById("introduction").value; 
	       if (introduction=='')
	       {
	       alert("申诉内容不能为空,请填写!");
	       return false;
	       }
	 
						
		if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/user.do?action=insertAppealSave'; //ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=insertAppealSave';	//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();		
		
		return true;	
	</script>
	
	
</head>
<body>
<center>
<form  method='POST' name='form1' action='/user.do?action=editOnlyAppealSave'  id="form1">
   <table width="782" height="99" border="0">
  
    <tr>
   <td   width="20%">&nbsp;</td>
   <td align="left">
          查看申诉信息及回复
   </td>
   </tr>
   
   
   
   	<s:iterator id="selA" value="%{searchAppealList.objectList}">
   
   <tr>
   <td  bgcolor="#6699FF">标题：</td>
   <td align="left"  bgcolor="#6699FF">
         <s:property value="#selA.APPEALTITLE" />
         &nbsp;
   </td>
   </tr>
   
   <tr>
   <td   bgcolor="#66FFFF">原内容：</td>
   <td align="left"  bgcolor="#66FFFF">
     <s:property value="#selA.APPEALTITLE" />&nbsp;
   </td>
   </tr>
   
   <tr>
   <td  bgcolor="#66FFCC">回复内容：</td>
   <td align="left" bgcolor="#66FFCC">
   <s:property value="#selA.APPEALCONTENT" />&nbsp;
  
   </td>
   </tr>
   
    <tr>
   <td   bgcolor="#CCCCCC">回复商家及时间：</td>
   <td align="left" bgcolor="#CCCCCC">
  
        <font color="#ff0000"><s:property value="#selA.APPEALMERCHANT"/> </font>
        (<s:property value="#selA.REPLYTIME"/>)
   &nbsp;
   </td>
   </tr>
   </s:iterator>
   
   
   
   
  
   
   
 </table>
</form>
</center>
</body>
</html>