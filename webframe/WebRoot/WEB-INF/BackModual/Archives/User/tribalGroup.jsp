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
   function moveOption(e1){
   for(var i=0;i<e1.options.length;i++){
   if(e1.options[i].selected){
   var e = e1.options[i];
   document.getElementById('chatid').value=e.value; 
  
}
}

}

  </script>
	
	
</head>
<body>
<center>
   <form   method='POST' name='form1' action='/user.do?action=loginTribal'  id="form1">
   <table  width="557"  border="1">
   <tr>
      <td   align="center">群<br></td>
   </tr>
    <tr>
      <td   align="center">点击群聊天<br></td>
   </tr>
   <tr>
      <td>   
         <select id="searchSel" name="searchSel" size="17"  onmouseOut="moveOption(document.getElementById('searchSel'))"  style="width:80%"  multiple="multiple">
    	    	<s:iterator id="sel"  value="%{searchGroupList.objectList}">
		         <option value="<s:property value="#sel.CHATID" />"  >
				  (<s:property value="#sel.GROUPNAME" />)<s:property value="#sel.MAINUSER" />
				 </option>
			   </s:iterator>			 
		 </select>
		      
		</td>
     
   
   </tr>

   <tr>
       <td  align="center">
                   <input type="submit" id="saveId"   name="save"    value="进 入"  />
             &nbsp;<input type="reset"  id="saveId"  name="rereset"  value="重 置"/>
             <input type="hidden"  id="chatid"  name="search.chatid" />
        
       </td>
   </tr>
   
   
 </table>
</form>
</center>
</body>
</html>