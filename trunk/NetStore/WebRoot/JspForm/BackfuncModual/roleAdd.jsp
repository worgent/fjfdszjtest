<%@ page language="java" import="com.fredck.FCKeditor.*"
	pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
	<title>角色信息添加</title>
    	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
</head>


<body background="<%=request.getContextPath()%>/images/bg.gif">
	<form
		action="<%=request.getContextPath()%>/JspForm/BackfuncModual/role.do?page=1"
		method="post">
		<center>
			<table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
			    <tr>
					<td colspan="2"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">
						角色信息增加
					</td>
				</tr>
				<tr>
					<td class="main" align="right" >
						角色名称
					</td>
					<td class="main" align="left" >
						<input type="text" name="roleName" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2" class="main"  align="center">
						<input type="hidden" name="status" value="addRole"/>
						<input type="submit" value="保存" class="button" />
						<input type="button" value="返回" class="button" onclick="javascript:self.history.back(); " />
					</td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html:html>
<logic:present name="xgResult">
   <script type="text/javascript">
       alert("${xgResult}");
   </script>
</logic:present>
