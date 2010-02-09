<%@ page language="java" import="com.fredck.FCKeditor.*,java.util.Map"
	pageEncoding="gbk"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
	<title>首页新闻管理</title>

</head>

<body >
	<center>
	    <logic:present name="newsMap">
		<table width="80%">
		     <tr>
		         <td align="center"> 
		            ${newsMap.newsTitle } 
		         </td>
		     </tr>
		     <tr>
		         <td >
		            &nbsp;&nbsp;${newsMap.newsContent }
		         </td>
		     </tr>
		     <tr>
		         <td>
		         <hr>
		         </td>
		     </tr>
		     <tr>
		         <td align="left">
		             发布人:${newsMap.releaseMan }<br>
		             发布时间:${newsMap.releaseTime}
		         </td>
		     </tr>
		      
		</table>
		</logic:present>
	</center>
</body>
</html:html>
