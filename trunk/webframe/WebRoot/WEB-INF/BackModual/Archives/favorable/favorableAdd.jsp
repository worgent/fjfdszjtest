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
   <td><span style="font-size: 10.5pt; font-family: 宋体; color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 优惠券 -&gt;增加</span><span style="font-size: 10.5pt; font-family: 宋体;"></span><span style="font-size: 10.5pt; font-family: 宋体;"></span>   
   </td>
   <td>&nbsp;</td>
   </tr>
   
    <tr>
   <td  align="right"><br></td>
   <td>&nbsp; 
    
    		
				
				    
				
				    
				
				
   </td>
   
   <td>&nbsp;</td>
   </tr>

   
   <tr>
   <td  align="right">编号:</td>
   <td  align="left">
      <input type="text" id="favourableno"  name="search.pfavourableno"   /> 
      <font color=red>* 必填写&nbsp; 自编号码</font>
   </td>
   <td>&nbsp;</td>
   </tr>
   <tr>
   <td  align="right">名称:</td>
   <td align="left">
       <input type="text" id="favourablename"  name="search.pfavourablename"   />
       <font color=red>*</font>
   </td>
   <td>&nbsp;</td>
   </tr>

    <tr>
   <td  align="right">使用情况:</td>
   <td align="left"><input type="text" id="useinstance" name="search.puseinstance"></td>
   <td>&nbsp;</td>
   </tr>
    
    <tr>
   <td  align="right">介绍:</td>
   <td align="left">
        <textarea id="introduce" name="search.pintroduce" rows="6" cols="35"></textarea> 
   </td>
   <td>&nbsp;</td>
   </tr>
				
    <tr>
   <td  align="right">数量:</td>
   <td align="left">
       <input type="text" id="count"  name="search.pcount"  /> 
   </td>
   <td>&nbsp;</td>
   </tr>
				 
   <tr>
   <td  align="right">领取数量:</td>
   <td align="left">
     <input type="text" id="countreceve"  name="search.pcountreceve"  /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr>
   <td  align="right">使用部落:</td>
   <td align="left">
       <input type="text" id="usertribal"  name="search.pusertribal"   /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
         <input type="submit" id="saveId" name="save"    value="保存"  />
   &nbsp;<input type="reset" id="resaveId"  name="resave" value="重置"  /></td>
   <td>
     <input type="hidden" id="action"  name="action"   value="favourableAddSave"/>
   </td>
   </tr>
  
 </table>

</form>

</center>
</body>
</html>