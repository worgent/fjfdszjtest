<%@ page language="java" pageEncoding="gbk"%>
<jsp:directive.page import="com.qzgf.NetStore.pub.*" />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
	<script type="text/javascript">
        function setAction(){
            var page=document.getElementById("page").value;
            window.location.href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manufacturer.do?status=queryAllManufacturers&page="+page;
        }
    </script>
	<title>生产厂商管理</title>
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
    <center>
    <table width="80%">
       <tr>
           <td colspan="8" align="right">
           <a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manufacturer.do?status=to_addPage">添加</a>
           </td>
       </tr>
    </table>

	<table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
	    <tr >
	        <td colspan="8"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">生产厂商管理</td></tr>
		<tr>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				厂商名称
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				厂商网站
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				联系人
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				电话
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				手机号码
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				联系地址
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				邮编
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				操作
			</td>
		</tr>
		<logic:present name="ppage" scope="request">
			<logic:present name="ppage" property="resultList" scope="request">
				<logic:iterate id="item" name="ppage" property="resultList"
					scope="request">
					<tr>
						<td class="main">
							${item.manufacturerName}&nbsp;
						</td>
						<td class="main">
							${item.producerHomePage }&nbsp;
						</td>
						<td class="main">
							${item.contactMan }&nbsp;
						</td>
						<td class="main">
							${item.phone }&nbsp;
						</td>
						<td class="main">
							${item.cellPhone}&nbsp;
						</td>
						<td class="main">
							${item.contactAddress }&nbsp;
						</td>
						<td class="main">
							${item.postCode }&nbsp;
						</td>
						<td class="main">
							<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manufacturer.do?status=queryManufacturerById&id=${item.manufacturerId }">修改</a>&nbsp;&nbsp;
							<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manufacturer.do?status=deleteManufacturerById&id=${item.manufacturerId }">删除</a>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</logic:present>
		<tr>
			<td class="main" align="left" colspan="6">
				<%
									Page p = (Page) request.getAttribute("ppage");
									int currentPage = p.getCurrentPage();
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manufacturer.do?status=queryAllManufacturers&targetPage=1"
					class="pagenav">首页</a>
				<%
								if (p.isHasPrevious()) {
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manufacturer.do?status=queryAllManufacturers&targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>"
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
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manufacturer.do?status=queryAllManufacturers&targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>"
					class="pagenav">下一页</a>
				<%
								} else {
								%>
				下一页
				<%
								}
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manufacturer.do?status=queryAllManufacturers&targetPage=<%=p.getTotalPages()%>"
					class="pagenav">末页</a>&nbsp;&nbsp;第<%=currentPage%>页/ 共<%=p.getTotalPages()%>页
				&nbsp;&nbsp;共
				<bean:write name="ppage" property="totalRecords" />
				条记录

			</td>
			<td class="main" align="right" colspan="2">
				转
				<input type="text" name="page" class="inp_page" value="" size="4"
					onkeyup="value=value.replace(/[^\d]/g,'')" />
				页
				<input type="button" value="Go" onclick="setAction()" class="button" />
				<input type="hidden" id="totalPages" name="totalPages"
					value="<bean:write name="ppage" property="totalPages"/>" />
			</td>
		</tr>
	</table>
    </center>
</body>
</html:html>
<logic:present name="xgResult">
	<script type="text/javascript">
       alert("${xgResult}");
   </script>
</logic:present>
