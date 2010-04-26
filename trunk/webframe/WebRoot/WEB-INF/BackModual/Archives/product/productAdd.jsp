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
   <td><span style="font-size: 10.5pt; font-family: 宋体; color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 商品信息 -&gt;增加</span><span style="font-size: 10.5pt; font-family: 宋体;"></span><span style="font-size: 10.5pt; font-family: 宋体;"></span>   
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
   <td  align="right">商品分类:</td>
   <td>
     <select id="productsortId" name="search.pproductsort" >
        <option value="1"   >
		      新
		 </option>		
		<option value="2" >
		      特
		</option>
		<option value="3" >
		      优
		</option>
		</select>  
     
     
     
      <font color=red>* 必填写</font>
   </td>
   <td>&nbsp;</td>
   </tr>
   <tr>
   <td  align="right">商品名称:</td>
   <td>
       <input type="text" id="productname"  name="search.pproductname"   /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr>
   <td  align="right">品牌:</td>
   <td>
       <input type="text" id="productbrand"  name="search.pproductbrand"   /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   
    <tr>
   <td  align="right">介绍:</td>
   <td>
       <textarea id="introduct" name="search.pintroduct" rows="6" cols="35">  </textarea> 
   </td>
   <td>&nbsp;</td>
   </tr>
   

    <tr>
   <td  align="right">图片:</td>
   <td>
       <input type="text" id="picture"  name="search.ppicture"    /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
    <tr>
   <td  align="right">产地:</td>
   <td>
       <input type="text" id="origin"  name="search.porigin"  /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr>
   <td  align="right">供应商:</td>
   <td>
     <input type="text" id="supplier"  name="search.psupplier"  /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr>
   <td  align="right">浏览数:</td>
   <td>
       <input type="text" id="views"  name="search.pviews"   /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr>
   <td  align="right">向导锦囊:</td>
   <td>
       <input type="text" id="guide"  name="search.pguide"  /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr>
   <td  align="right">其它商家产品:</td>
   <td>
       <input type="text" id="otherproduct"  name="search.potherproduct"   /> 
   </td>
   <td>&nbsp;</td>
   </tr>
   
   
   <tr>
   <td>&nbsp;</td>
   <td  align="center">
     <input type="submit" id="saveId" name="save"    value="保存"  />
    

   &nbsp;<input type="reset" id="resaveId"  name="resave" value="重置"  /></td>
   <td>
     <input type="hidden" id="action"  name="action"   value="productAddSave"/>
   </td>
   </tr>
  
 </table>

</form>

</center>
</body>
</html>