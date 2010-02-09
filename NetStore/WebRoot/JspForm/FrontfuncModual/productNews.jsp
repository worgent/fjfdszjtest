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
alert('ss');
document.forms[0].action="indexFirst.do?status=productNewsShow&page="+document.getElementById("pageId").value;
alert('ccc');
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
 <table width="98%" align="center" cellspacing="1" cellpadding="2" bgcolor="#e1e1e1" border="0">
 <tr>
 <td bgcolor="#ffffdd" width="5%">
 
 </td>
  <td bgcolor="#ffffdd" >
  新闻标题
 </td>
  <td bgcolor="#ffffdd" >
  发布者
 </td>
  <td bgcolor="#ffffdd" >
  发布时间
 </td>
 </tr>
<logic:present name="ppage" scope="request">
<logic:present   name="ppage" property="resultList" scope="request">
<logic:iterate id="item" name="ppage" property="resultList" scope="request">
 <tr>
 <td bgcolor="#ffffff" >
<img src="<%=request.getContextPath() %>/images/newb.gif"></img> 
 </td>
  <td bgcolor="#ffffff" >
 <a href="indexFirst.do?status=newsDetailList&newsId=${item.newsId}">${item.newsTitle}</a>
 </td>
  <td bgcolor="#ffffff" >
 ${item.releaseMan}
 </td>
<td bgcolor="#ffffff" >
${item.releaseTime}
 </td>
 </tr>
 </logic:iterate>
 </logic:present>
 </logic:present>
 
 </table> 
</div>
<div>
   <table class="tbl" width="98%"  cellspacing="0" cellpadding="0"  >
	<tr>
	<td class="main" align="left">
								<%
									Page p = (Page) request.getAttribute("ppage");
									int currentPage = p.getCurrentPage();
								%>
								<a
									href="indexFirst.do?status=productNewsShow&targetPage=1"
									class="pagenav">首页</a>
								<%
								if (p.isHasPrevious()) {
								%>
								<a
									href="indexFirst.do?status=productNewsShow&targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>"
									class="pagenav">上一页</a>
								<%
								} else {
								%>
								上一页
								<%
									}
									if (p.isHasNext()) {
								%>
								<a
									href="indexFirst.do?status=productNewsShow&targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>"
									class="pagenav">下一页</a>
								<%
								} else {
								%>
								下一页
								<%
								}
								%>
								<a
									href="indexFirst.do?status=productNewsShow&targetPage=<%=p.getTotalPages()%>"
									class="pagenav">末页</a>&nbsp;&nbsp;第<%=currentPage%>页/ 共<%=p.getTotalPages()%>页 &nbsp;&nbsp;共<bean:write name="ppage" property="totalRecords"/>条记录
							</td>
    
    <td align="right" class="main">
    转<input type="text" id="pageId" name="page" class="inp_page" value="" size="2"  onkeypress="number()"/>页 
    <input type="button" value="Go" onclick="setAction('qr')" class="button"/>
    </td>
	</tr>
	</table>
     <input type="hidden" id="totalPages" name="totalPages" value="<bean:write name="ppage" property="totalPages"/>"/>
     
<jsp:include flush="true" page="bottom.jsp" />  
</div>
</center>
</form>

</body>
</html>