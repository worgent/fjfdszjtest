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
   <td>审批会员-&gt;基本信息   
   </td>
   <td>&nbsp;</td>
   </tr>
   
    <tr>
    <td  align="right">会员名称:</td>
    <td>
     <s:property value="search.MEMBERNAME" />
     
     <input type="hidden" id="picture"  name="search.MEMBERID"   value="<s:property value="search.MEMBERID" />" /> 

    </td>
   
   <td>&nbsp;</td>
   </tr>
  
    <tr>
   <td  align="right">会员类型:</td>
   <td>
            <s:if test="search.MEMBERTYPE==1">商家会员</s:if>   
            <s:if test="search.MEMBERTYPE==2">普通会员</s:if>   
            <s:if test="search.MEMBERTYPE==3">向导会员</s:if>   
            <s:if test="search.MEMBERTYPE==4">领主会员</s:if>
   </td>
   
   
   
   <td>&nbsp;</td>
   </tr>
   
  

    <tr>
   <td  align="right">商家分类:</td>
   <td>
<s:if test="search.MERCHANTCATEGORIES==0">&nbsp;</s:if>  
<s:if test="search.MERCHANTCATEGORIES==1">门店经营</s:if> 
<s:if test="search.MERCHANTCATEGORIES==2">家政服务</s:if> 
   </td>
   
   
   
   <td>&nbsp;</td>
   </tr>
   
   <tr>
   <td  align="right">商家名称:</td>
   <td>
     <s:property value="search.MERCHANTNAME" />
   </td>
   <td>&nbsp;</td>
   </tr>
   <tr>
   <td  align="right">地址:</td>
   <td>
    <s:property value="search.ADDRESS" />
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr>
   <td  align="right">法人代表:</td>
   <td>
      <s:property value="search.LEGALREPRESENTATIVE" />
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   
    <tr>
   <td  align="right">联络方式:</td>
   <td>
       <s:property value="search.CONTACT" /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   

    <tr>
   <td  align="right">位置:</td>
   <td>
      <s:property value="search.LOCATION" />
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
    <tr>
   <td  align="right">场景图照:</td>
   <td>
       <s:property value="search.PICTURE" />
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr>
   <td  align="right">简介:</td>
   <td>
     <s:property value="search.INTRODUCTION" />
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
   <td  align="right">是否审核:</td>
   <td>
   
         <select name="search.ISAUDIT" id="ISAUDIT">
          <option value="1"   <s:if test="search.ISAUDIT==1">selected</s:if>   >是</option>
          <option value="0"   <s:if test="search.ISAUDIT==0">selected</s:if>   >否</option>
         </select>
         
   </td>
   <td>&nbsp;</td>
   </tr>



  
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
     <input type="submit" id="saveId" name="save"    value="保存审批"  />
    

   &nbsp;<input type="reset" id="resaveId"  name="resave" value="重置"  /></td>
   <td>
     <input type="hidden" id="action"  name="action"   value="applicationAudit"/>
   </td>
   </tr>
  
 </table>

</form>

</center>
</body>
</html>