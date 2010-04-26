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
			$("#myTable").tablesorter({ 
				headers: { 
					1: {sorter: false }
				}   
			}); 
		}); 
		</script>

     <script type="text/javascript">
     
       function searchMember(str){

        var userName=document.getElementById("username").value;
        var nickName=document.getElementById("nickname").value;
        if((userName=='')&&(nickName==''))    {alert("帐号跟昵称至少输入一个查询条件进行查找"); return false;
        } 
        else
        {
     
		if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/user.do?action=searchUser'; //ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=searchUser';	//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();	
		}
		
		}
		
	 </script>



</head>
<body>
<center>
<form  method='POST' name='form1' action='/user.do?action=searchUser'  id="form1">
   <table width="337" height="113">
   <tr>
      <td  colspan="2" align="center">查找用户</td>
   </tr>
   
   <tr>
      <td>帐号：</td>
      <td><input type="text" id="username"  name="search.PUSERNAME" /></td>
   </tr>

   <tr>
   <td>昵称:</td>
   <td>
       <input type="text" id="nickname"  name="search.PNICKNAME" />
   </td>
   
   </tr>
   
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
           
             <input type="button" id="saveId" name="save"  onclick="javascript:searchMember(this);"  value="查 找"  />
    
      &nbsp;<input type="reset"  id="saveId"  name="rereset" value="重置"/>
      <input type="hidden" id="mainUser" name="mainUser"   value="<s:property value="search.MAINUSER" />" />
      <input type="hidden" id="chatid" name="search.chatid"   value="<%=request.getParameter("search.chatid") %>" />
      
   </td>
   </tr>
   
   
 </table>
</form>
</center>
</body>
</html>