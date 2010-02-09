<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<jsp:directive.page import="com.qzgf.NetStore.pub.*"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" type="text/css"/>
  
	<script language="JavaScript"
		src="<%=request.getContextPath()%>/jslib/extremecomponents.js"></script>
    <script type="text/javascript">
        function setAction(){
            var page=document.getElementById("page").value;
            window.location.href="<%=request.getContextPath()%>/JspForm/BackfuncModual/role.do?status=queryAllRoles&page="+page;
        }
function number()
{
var char = String.fromCharCode(event.keyCode)
var re = /[0-9]/g
event.returnValue = char.match(re) != null ? true : false
}
        function toAdd(){
            window.location.href="<%=request.getContextPath()%>/JspForm/BackfuncModual/role.do?status=to_addPage";
        }
    </script>
	<title>角色管理</title>
</head>

  
<body background="<%=request.getContextPath()%>/images/bg.gif">
		<%
			String context = request.getContextPath();
			pageContext.setAttribute("ctx", context);
		%>
		
 <form action="${ctx}/JspForm/BackfuncModual/role.do?status=queryAllRoles" method="post">
  <center>
    	<table class="tbl" width="80%"  cellspacing="0" cellpadding="0" >
    	   <tr><td colspan="5"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">角色管理</td></tr>
    		<tr>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			角色编号
    			</td>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			角色名称
    			</td>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			权限设置
    			</td>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			操作
    			</td>
    			
    		
    		</tr>
    		<logic:present name="ppage" scope="request">
				<logic:present   name="ppage" property="resultList" scope="request">
				 <logic:iterate id="item" name="ppage" property="resultList" scope="request">
    				<tr>
    					<td class="main">
    					${item.roleId}
    					</td>
    					<td class="main">
    					${item.roleName}
    					</td>
    					<td class="main">
 <a href="${ctx}/JspForm/BackfuncModual/roleValue.do?status=queryAllRoleValues&rid=${item.roleId }&rname=${item.roleName }">权限设置</a> 			
    					</td>
    					<td class="main">
    					<div class="txt_td_normal">
   <a href="${ctx}/JspForm/BackfuncModual/role.do?status=queryRoleById&id=${item.roleId }">修改</a>  
   |<a onclick='return confirm("确定要删除当前记录吗?")' href="${ctx}/JspForm/BackfuncModual/role.do?status=deleteRoleById&id=${item.roleId }">删除</a>  
    				    </div>
    					</td>
    				</tr>  
    	</logic:iterate>
    	</logic:present>
    	</logic:present>
    	</table>		
  <table class="tbl" width="80%"  cellspacing="0" cellpadding="0"  >
	<tr>
	<td class="main" align="left">
								<%
									Page p = (Page) request.getAttribute("ppage");
									int currentPage = p.getCurrentPage();
								%>
								<a
									href="${ctx}/JspForm/BackfuncModual/role.do?status=queryAllRoles&targetPage=1%>"
									class="pagenav">首页</a>
								<%
								if (p.isHasPrevious()) {
								%>
								<a
									href="${ctx}/JspForm/BackfuncModual/role.do?status=queryAllRoles&targetPage=<%=currentPage == 1 ? 1 : currentPage - 1%>"
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
									href="${ctx}/JspForm/BackfuncModual/role.do?status=queryAllRoles&targetPage=<%=currentPage == p.getTotalPages() ? p.getTotalPages() : currentPage + 1%>"
									class="pagenav">下一页</a>
								<%
								} else {
								%>
								下一页
								<%
								}
								%>
								<a
									href="${ctx}/JspForm/BackfuncModual/role.do?status=queryAllRoles&targetPage=<%=p.getTotalPages()%>"
									class="pagenav">末页</a>&nbsp;&nbsp;第<%=currentPage%>页/ 共<%=p.getTotalPages()%>页 &nbsp;&nbsp;共<bean:write name="ppage" property="totalRecords"/>条记录
							</td>
    
    <td align="right" class="main">
    转<input type="text" name="page" class="inp_page" value="" size="2"  onkeypress="number()"/>页 
    <input type="button" value="Go" onclick="setAction()" class="button"/>
    </td>
	</tr>
	</table>
	 <input type="hidden" id="totalPages" name="totalPages" value="<bean:write name="ppage" property="totalPages"/>"/>		
		<center>
			<input type="hidden" name="status" />
			<div><input type="button" value="添加" onclick="toAdd()"/></div>
		</center>
</body>
</html:html>
<logic:present name="xgResult">
   <script type="text/javascript">
       alert("${xgResult}");
   </script>
</logic:present>
