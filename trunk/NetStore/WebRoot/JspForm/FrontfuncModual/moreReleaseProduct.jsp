<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:directive.page import="com.qzgf.NetStore.pub.*"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>更多推荐</title>
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
document.forms[0].action="indexFirst.do?status=moreReleaseProduct&page="+document.getElementById("pageId").value+"&totalPages="+document.getElementById("totalPages").value; 
document.forms[0].submit();
}
</script>

</head>
<body>
<form action="" method="post">
<%
  //ServletContext   servletContext   =   pageContext.getServletContext();   
  //String   realPath   =   servletContext.getRealPath("/");
  String name2=request.getContextPath()+"/upload/smallPic"; 
        %>
<center>
<jsp:include flush="true" page="top.jsp" />

<table  width="98%" align="center" cellspacing="1" cellpadding="2" bgcolor="#e1e1e1" border="0" >
<tr><td colspan="3" style="color:#ff7000">推荐区域</td></tr>
<tr><td>商品预览</td><td>商品名称</td><td>商品价格</td></tr>

<logic:present name="ppage" scope="request">
<logic:present   name="ppage" property="resultList" scope="request">
<logic:iterate id="item" name="ppage" property="resultList" scope="request">
  <tr>
  <td align="center"  bgcolor="#ffffff">
  
  <table  onmouseover="this.style.backgroundColor='#FF4FAE'" onmouseout="this.style.backgroundColor=''" width="100"  cellspacing="1" cellpadding="2" bgcolor="#e1e1e1" border="0">
  <tr><td  bgcolor="#ffffff" height="100" align="center">
   <a href="indexFirst.do?status=onlyGoodsShow&productId=${item.productId}">
   <img src="<%=name2 %>\<bean:write name="item" property="smallPath"/>" width="100" height="100" border="0"/></a>
  </td></tr>
  </table>

  </td>
  <td align="left"  bgcolor="#ffffff">
  <a href="indexFirst.do?status=onlyGoodsShow&productId=${item.productId}">
  <font color="#ff6600">${item.productName}</font>
  </a>
  <br/>
   <br/>
  ${item.productIntro}
  <br/>
   <br/>
  上架时间:${item.displayDate}
  
  </td>
  <td  bgcolor="#ffffff">
  
 <table>
 <tr><td>市场价:</td><td>${item.marketPrice}</td></tr>
 <tr><td>会员价:</td><td>${item.memberPrice}</td></tr>
 <tr><td><a href="indexFirst.do?status=onlyGoodsShow&productId=${item.productId}"><img src="images/goumai.gif" border="0"></img></a></td>
 <td><img src="images/shoucang1.gif" border="0"></img></td>
 </tr>
 </table>
  
  </td>
  </tr>
</logic:iterate>
</logic:present>
</logic:present>
</table>

<table class="tbl" width="98%"  cellspacing="0" cellpadding="0"  >
	<tr>
	<td class="main" align="left">
								<%
									Page p = (Page) request.getAttribute("ppage");
									int currentPage = p.getCurrentPage();
								%>
								<a
									href="indexFirst.do?status=moreReleaseProduct&targetPage=1"
									class="pagenav">首页</a>
								<%
								if (p.isHasPrevious()) {
								%>
								<a
									href="indexFirst.do?status=moreReleaseProduct&targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>"
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
									href="indexFirst.do?status=moreReleaseProduct&targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>"
									class="pagenav">下一页</a>
								<%
								} else {
								%>
								下一页
								<%
								}
								%>
								<a
									href="indexFirst.do?status=moreReleaseProduct&targetPage=<%=p.getTotalPages()%>"
									class="pagenav">末页</a>&nbsp;&nbsp;第<%=currentPage%>页/ 共<%=p.getTotalPages()%>页 &nbsp;&nbsp;共<bean:write name="ppage" property="totalRecords"/>条记录
							</td>
    <td align="right" class="main">
    转<input type="text"  id="pageId" name="page" class="inp_page"  size="2"  onkeypress="number()"/>页 
    <input type="button" value="Go" onclick="setAction('qrys')"  onkeypress="number()" class="button"/>
    <input type="hidden" id="totalPages" name="totalPages" value="<bean:write name="ppage" property="totalPages"/>"/>
    </td>
	</tr>
	</table>




<jsp:include flush="true" page="bottom.jsp" />
</center>

</form>
</body>
</html>