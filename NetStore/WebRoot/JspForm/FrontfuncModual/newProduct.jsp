<%@ page language="java" pageEncoding="gbk"%>
<jsp:directive.page import="com.qzgf.NetStore.pub.*" />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title></title>
	<link href="<%=request.getContextPath()%>/css/product/list.css"
		type="text/css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/global/topsell.css"
		rel="stylesheet" type="text/css">
</head>

<body>
	<table border="1" width="80%">
		<tr>
			<td width="100%">
				<logic:present name="newPpage" scope="request">
					<logic:present name="newPpage" property="resultList"
						scope="request">
						<logic:iterate id="item" name="newPpage" property="resultList"
							indexId="rowNum" scope="request">
							<bean:define id="result2" value="<%=String.valueOf(((rowNum.intValue()) % 3))%>" type="java.lang.String" />
				                <div style="width: 180; display: inline">
								    <a href="#"> <img src="<%=request.getContextPath()%>/${item.SmallPath}" border="0" width="100" /></a>
								    <br><a href="#"><font color="#FF6600">${item.ProductName}</font>
								    <br>市场价:￥${item.MarketPrice }
								    <br>会员价:￥${item.MemberPrice }
								    <br><input type="button" value="购买" />
								    </a>
							    </div>
							<logic:equal value="2" name="result2">
								<br />
							</logic:equal>
						</logic:iterate>
					</logic:present>
				</logic:present>
			</td>
		</tr>
		<logic:present name="newPpage">
		<tr>
			<td class="main" align="left" colspan="6">
				<%
									Page p = (Page) request.getAttribute("newPpage");
									int currentPage = p.getCurrentPage();
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/productDisplay.do?status=queryProductByNew&targetPage=1"
					class="pagenav">首页</a>
				<%
								if (p.isHasPrevious()) {
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/productDisplay.do?status=queryProductByNew&targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>"
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
					href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/productDisplay.do?status=queryProductByNew&targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>"
					class="pagenav">下一页</a>
				<%
								} else {
								%>
				下一页
				<%
								}
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/FrontfuncModual/productDisplay.do?status=queryProductByNew&targetPage=<%=p.getTotalPages()%>"
					class="pagenav">末页</a>&nbsp;&nbsp;第<%=currentPage%>页/ 共<%=p.getTotalPages()%>页
				&nbsp;&nbsp;共
				<bean:write name="newPpage" property="totalRecords" />
				条记录&nbsp;&nbsp;&nbsp;&nbsp;
				转
				<input type="text" name="page" class="inp_page" value="" size="4"
					onkeyup="value=value.replace(/[^\d]/g,'')" />
				页
				<input type="button" value="Go" onclick="setAction()" class="button" />
				<input type="hidden" id="totalPages" name="totalPages"
					value="<bean:write name="newPpage" property="totalPages"/>" />
			</td>
		</tr>
		</logic:present>
	</table>

</body>
</html:html>

