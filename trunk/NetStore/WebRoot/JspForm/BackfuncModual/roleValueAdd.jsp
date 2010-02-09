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
	<title>角色权限值添加</title>
    
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
	<form
		action="<%=request.getContextPath()%>/JspForm/BackfuncModual/roleValue.do"
		method="post">
		<center>
			<table border=1 width="60%">
				<tr>
					<td colspan="2" align="center">
						角色权限增加
					</td>
				</tr>
				<tr>
					<td>
						角色编号
					</td>
					<td>
						<input type="text" name="roleId" />
					</td>
				</tr>
				<tr>
					<td>
						菜单编号
					</td>
					<td>
						<input type="text" name="menuCode"/>
					</td>
				</tr>
				<tr>
					<td>
						是否显示
					</td>
					<td>
						<input type="text" name="funisShow" />
					</td>
				</tr>
				<tr>
					<td>
						权限值
					</td>
					<td>
						<input type="text" name="powerValue"/>
					</td>
				</tr>
								<tr>
					<td>
						备注
					</td>
					<td>
						<input type="text" name="remark"/>
					</td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<input type="hidden" name="status" value="addRoleValue"/>
						<input type="submit" value="保存" />
						<input type="button" value="返回" onclick="javascript:self.history.back(); "/>
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
