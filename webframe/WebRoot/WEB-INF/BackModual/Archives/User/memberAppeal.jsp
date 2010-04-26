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
<form  method='POST' name='form1' action='/user.do?action=insertAppealSave'  id="form1">
   <table width="782" height="99" border="1">
   <tr>
      <td>&nbsp;</td>
       <td  align="left">会员申诉</td>
      <td>&nbsp;</td>
   </tr>
    <tr>
   <td  >&nbsp;</td>
   <td align="left">
        <a href="<%=path %>/user.do?action=selectAllAppeal">查看申诉信息及回复 </a> 
       
       <a href="<%=path %>/user.do?action=enter">商家申诉管理</a>  
   </td>
   </tr>
   
   <tr>
   <td  >商家名称：</td>
   <td align="left">
       <input type="text" id="appealmerchant"  name="search.pappealmerchant"    size="22"/>
   </td>
   </tr>
   
   
   <tr>
   <td>标题：</td>
   <td align="left">
       <input type="text" id="appealtitle"  name="search.pappealtitle"    size="42"/>
   </td>
   </tr>
   
   
   <tr>
   <td  >申诉内容：</td>
   <td align="left">
   <textarea id="introduction" name="search.pintroduction" rows="8" cols="45"> </textarea>
   </td>
   <td>&nbsp;</td>
   </tr>
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
     <input type="submit" id="saveId" name="save"  onclick="javascript:isEmpty(this);"  value="提交申诉"  />
    
      &nbsp;<input type="reset"  id="saveId"  name="rereset" value="重置"/>
      </td>
   <td>
   &nbsp;
   </td>
   </tr>
   
    <tr>
   <td>&nbsp;</td>
   <td  align="center">
          <font color="#ff0000"><s:actionmessage theme="webframe0"/></font>
        <font color="#ff0000"><s:actionerror theme="webframe0"/></font>
      &nbsp;
      </td>
   <td>
   &nbsp;
   </td>
   </tr>
 </table>
</form>
</center>
</body>
</html>