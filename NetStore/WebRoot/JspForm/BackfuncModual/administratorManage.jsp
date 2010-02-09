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
	<title>管理员维护</title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
		<script type="text/javascript">
        function setAction(){
            var page=document.getElementById("page").value;
            window.location.href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do?status=queryManagers&page="+page;
        }
    </script>
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
<center>
    <table width="80%">
       <tr>
           <td colspan="8" align="right">
           <a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do?status=to_addPage">添加</a>
           </td>
       </tr>
    </table>
<table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
	    <tr >
	        <td colspan="7"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">管理员列表</td></tr>
		<tr>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				管理员ID
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				真实姓名
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				性别
			</td>
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				Email
			</td>
			
			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
				权限
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
							${item.adminId}
						</td>
						<td class="main">
							${item.realName }
						</td>
						<td class="main">
							${item.sex }
						</td>
						<td class="main">
							${item.email }
						</td>
						<td class="main">
							${item.roleName }
						</td>
						<td class="main">
							<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do?status=queryAdminById&id=${item.adminId }">修改</a> &nbsp;&nbsp;
							<a href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do?status=deleteManagerById&id=${item.adminId }">删除</a>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</logic:present>
		<tr>
			<td class="main" align="left" colspan="5">
				<%
									Page p = (Page) request.getAttribute("ppage");
									int currentPage = p.getCurrentPage();
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do?status=queryManagers&targetPage=1"
					class="pagenav">首页</a>
				<%
								if (p.isHasPrevious()) {
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do?status=queryManagers&targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>"
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
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do?status=queryManagers&targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>"
					class="pagenav">下一页</a>
				<%
								} else {
								%>
				下一页
				<%
								}
								%>
				<a
					href="<%=request.getContextPath()%>/JspForm/BackfuncModual/manager.do?status=queryManagers&targetPage=<%=p.getTotalPages()%>"
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
