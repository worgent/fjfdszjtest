<%@ page language="java" pageEncoding="gbk"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>��ʾСͼƬ</title>
<script language=javascript>
function window.onunload()
{
  dialogArguments.dWin=null; 
}


  function returnResult(){
  alert(document.getElementById("sPic").value);
  
  window.opener.document.forms.title.value='XXX'; 
  //document.forms[0].action="JspForm/BackfuncModual/ProductAdd.jsp?sPic="+document.getElementById("sPic").value;
  window.opener.document.forms[0].name.
  //document.forms[0].submit();
  window.close();
}
</script>

</head>
<body>
  <center>
  <form  action=""  method="post" >
  <table><tr><td>
  <%
  ServletContext   servletContext   =   pageContext.getServletContext();   
  String   realPath   =   servletContext.getRealPath("/");
  String name2=realPath+"/upload/smallPic"; 
        %>
  <input type="image" name="imagePic" src="<%=name2 %>\<%=request.getAttribute("picPath")%>" width="150" height="150"  />
  </td></tr></table>
  </form>
  </center>
</body>
</html>