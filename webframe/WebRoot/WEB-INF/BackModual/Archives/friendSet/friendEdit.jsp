<%@ page language="java"  import="java.util.Date" errorPage="/error.jsp" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>

<jsp:directive.page import="java.util.List" />
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@include file="/common/taglibs.jsp"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
  <head>
  
 
	
	
  </head>
<body>
<center>
<form  method='POST' name='form1' action='/user.do'  id="form1">
   <table border="1"  width="90%">

    <tr>
   <td>&nbsp;</td>
   <td><span style="font-size: 10.5pt; font-family: 宋体; color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 好友信息  </span><span style="font-size: 10.5pt; font-family: 宋体;"></span><span style="font-size: 10.5pt; font-family: 宋体;"></span>   
   </td>
   <td>&nbsp;</td>
   </tr>
   
    <tr>
   <td  align="right"><br></td>
   <td>
   
    &nbsp;
   </td>
   
   <td>&nbsp;</td>
   </tr>

   
   <tr>
   <td  align="right">用户帐号:</td>
   <td>
    <s:property value="search.FRIENDUSER" />
    
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   
   
    <tr>
   <td  align="right">邀请内容:</td>
   <td>
       <textarea id="sendcontext" name="search.psendcontext" rows="6" cols="35"><s:property value="search.SENDCONTEXT" /></textarea> 
   </td>
   <td>&nbsp;</td>
   </tr>
   

   
   
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
     <input type="submit" id="saveId" name="save"    value="接 受"  />
     &nbsp;<input type="reset" id="resaveId"  name="resave" value="重置"  /></td>
   <td>
    <input type="hidden" id="MAINUSER"  name="search.MAINUSER"   value="<s:property value="search.MAINUSER" />"  />
    <input type="hidden" id="FRIENDUSER"  name="search.FRIENDUSER"   value="<s:property value="search.FRIENDUSER" />"  />
  
     <input type="hidden" id="action"  name="action"   value="updateAcceptSave"/>
     
     <input type="hidden" id="PRODUCTID"  name="search.PRODUCTID"   value="<s:property value="search.PRODUCTID" />"/>
     
   </td>
   </tr>
  
 </table>

</form>

</center>
</body>
</html>