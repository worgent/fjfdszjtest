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
   <td><span style="font-size: 10.5pt; font-family: 宋体; color: red;">商家基本信息&nbsp;</span><span style="font-size: 10.5pt; font-family: 宋体;"></span><span style="font-size: 10.5pt; font-family: 宋体;"></span>   
   </td>
   <td>&nbsp;</td>
   </tr>
   

   <tr>
   <td  align="right">商家名称:</td>
   <td>
      <input type="text" id="merchantname"  name="search.pmerchantname"   value="<s:property value="search.MERCHANTNAME" />"  /> 
      <font color=red>* 必填写</font>
   </td>
   <td>&nbsp;</td>
   </tr>
   <tr>
   <td  align="right">地址:</td>
   <td>
       <input type="text" id="address"  name="search.paddress"   value="<s:property value="search.ADDRESS" />" /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr>
   <td  align="right">法人代表:</td>
   <td>
       <input type="text" id="legalRepresentative"  name="search.plegalrepresentative"  value="<s:property value="search.LEGALREPRESENTATIVE" />"  /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   
    <tr>
   <td  align="right">联络方式:</td>
   <td>
       <input type="text" id="contact"  name="search.pcontact"  value="<s:property value="search.CONTACT" />" /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   

    <tr>
   <td  align="right">位置:</td>
   <td>
       <input type="text" id="location"  name="search.plocation"   value="<s:property value="search.LOCATION" />" /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
    <tr>
   <td  align="right">场景图照:</td>
   <td>
       <input type="text" id="picture"  name="search.ppicture"   value="<s:property value="search.PICTURE" />" /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr>
   <td  align="right">简介:</td>
   <td>
       <textarea id="introduction" name="search.pintroduction" rows="6" cols="35"> <s:property value="search.INTRODUCTION" /> </textarea>
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr>
   <td  align="right">实力:</td>
   <td>
       <input type="text" id="strength"  name="search.pstrength"  value="<s:property value="search.STRENGTH" />" /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr>
   <td  align="right">口碑:</td>
   <td>
       <input type="text" id="praise"  name="search.ppraise"  value="<s:property value="search.PRAISE" />" /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr>
   <td  align="right">人气:</td>
   <td>
       <input type="text" id="popularity"  name="search.ppopularity" value="<s:property value="search.POPULARITY" />"  /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
      <tr>
   <td  align="right">财力:</td>
   <td>
       <input type="text" id="financial"  name="search.pfinancial"   value="<s:property value="search.FINANCIAL" />" /> 
   </td>
   <td>&nbsp;</td>
   </tr>


   
     
  
  
  
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
     <input type="submit" id="saveId" name="save"     value="保存"  />
    

   &nbsp;<input type="reset" id="resaveId"  name="resave" value="重置"  /></td>
   <td>
     <input type="hidden" id="action"  name="action"   value="applMerchantSave"/>
   </td>
   </tr>
  
  
  	   <tr>
            <td colspan="3">
              <font color="#ff0000"><s:actionmessage theme="webframe0"/></font>
              
        	</td>
      		</tr>
      <tr>
		 <td colspan="3">
		    <font color="#ff0000"><s:actionerror theme="webframe0"/></font>  
		 </td>
	  </tr>
 </table>

</form>

</center>
</body>
</html>