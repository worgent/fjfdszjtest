<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>手机注册提示信息</title>
<style type="text/css"> 

a:link {color: #000000; text-decoration:none} 
a:visited {color:#000000; text-decoration: none} 
a:active {color:#000000; text-decoration: none} 
a:hover {color:#ff0000; text-decoration:underline} 

</style> 
<script type="text/javascript">
  function positionVal(){
  var positionId=document.getElementById("position").value;
  if(positionId==''){
   alert('请你填写初始位置,方便在地图上进行标注'); 
   return false;
  }else
  {
        if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/user.do?action=position';//ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=position';	//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();	
  
  }
  
  }

</script>

</head>
<body>
<div align="center">
<form  method='POST' name='form1' action='/user.do?action=position'  id="form1">
   <table border="1">
    <tr> 
    <td colspan="2">
         <s:property value="search.message" escape="false"/>
        <input type="hidden"  id="search.pusername" name="search.pusername" value="<s:property value="search.pusername"/>"  />  
    </td>
    </tr>
   
    <tr> 
      <td>
       请你填写初始位置
      </td>
      <td>
        <input type="text" id="position"  name="search.pposition"  size="30"/>
        <input type="button" id="saveId" name="save"  onclick="javascript:positionVal(this);"  value="发 送"  />
      </td>
    </tr>
    <tr> 
    <td>
     
    </td>
    <td>
         <font color="red"><a href="/user.do?action=mobileReg">重新注册</a>&nbsp;
         <a href="/user.do?action=enter">我要登录</a>
         </font>
     </td>
   </tr>
   
    <tr> 
      <td colspan="2">
        备注：初始位置标注之后，方便商家，用户找到你
      </td>
    </tr>
</table>
</form>


</div>

</body>
</html>