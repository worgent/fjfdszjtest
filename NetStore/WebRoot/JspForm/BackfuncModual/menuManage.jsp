<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/extremecoponents.css" />
	<script language="JavaScript"
		src="<%=request.getContextPath()%>/jslib/extremecomponents.js"></script>
    <script type="text/javascript">
        function toAdd(){
            window.location.href="<%=request.getContextPath()%>/JspForm/BackfuncModual/menu.do?status=to_addPage";
        }
    </script>
	<title>菜单管理</title>
</head>

<body style="" background="<%=request.getContextPath()%>/images/bg.gif">
		<%
			String context = request.getContextPath();
			pageContext.setAttribute("ctx", context);
		%>
		<center>
			<ec:table items="menuList" var="item"
				action="${ctx}/JspForm/BackfuncModual/menu.do?status=queryAllMenus"
				imagePath="${ctx}/images/table/*.gif" width="90%" rowsDisplayed="5"
				border="1" filterable="false" state="default"
				title="<font color='red'>菜单管理</font>">
				<ec:exportXls fileName="president.xls" tooltip="Export Excel"/>
				<ec:exportPdf fileName="president.pdf" tooltip="Export PDF"/> 
				<ec:row>
					<ec:column property="操作" sortable="false" title="操作" >
						<a href="${ctx}/JspForm/BackfuncModual/menu.do?status=queryMenuById&id=${item.theCode }">修改</a>
				   |<a onclick='return confirm("确定要删除当前记录吗?")' href="${ctx}/JspForm/BackfuncModual/menu.do?status=deleteMenuById&id=${item.theCode }">删除</a>  
					</ec:column>
					<ec:column property="theCode" title="菜单编号"></ec:column>
					<ec:column property="theName" title="菜单名称"></ec:column>
					<ec:column property="theParentCode" title="父结点编号"></ec:column>
					<ec:column property="theUrl" title="地址"></ec:column>
					<ec:column property="theRemark" title="备注"></ec:column>
					<ec:column property="theOrderId" title="排序Id"></ec:column>
				</ec:row>
			</ec:table>
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
