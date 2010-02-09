<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:directive.page import="com.qzgf.NetStore.pub.*"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>商品新闻</title>
<link href="<%=request.getContextPath()%>/css/header01.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/index.css" type="text/css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/catalog.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript">
function number()
{
var char = String.fromCharCode(event.keyCode)
var re = /[0-9]/g
event.returnValue = char.match(re) != null ? true : false
}

function setAction(action){

document.forms[0].action="indexFirst.do?status=productNewsShow&page="+document.getElementById("pageId").value;

document.forms[0].submit();
}
</script>
</head>

<body class="ProducTypeHome8">
<form action="" method="post">
<center>
<div>
<jsp:include flush="true" page="top.jsp" /></div>  
<div>
 <table width="98%"  cellspacing="1" cellpadding="2" bgcolor="#e1e1e1" border="0">

 <logic:present name="newsDetailList">
 <logic:iterate id="item" name="newsDetailList" scope="request">                   
 <tr>
 <td bgcolor="#ffffdd" colspan="4" align="center">
 ${item.newsTitle}
 </td>
 </tr>
 <tr>
 <td bgcolor="#ffffff" colspan="4" height="800"  valign="top" align="left">
 ${item.newsContent}
 </td>
 </tr>
 <tr>
 <td bgcolor="#ffffff" colspan="4" align="right">
 作者:${item.newsAuthor}
 |
 发布人:${item.releaseMan}
 |
 发布时间:${item.releaseTime}
 </td>
 </tr>
 </logic:iterate>
 </logic:present>
 
 </table> 
</div>
<div align="center">
<jsp:include flush="true" page="bottom.jsp" />
</div>
</center>
</form>

</body>
</html>