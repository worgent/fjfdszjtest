<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display-el"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/screen.css">
	<title>新闻类别</title>
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
	<center>
		<form
			action="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do"
			method="post">
			<table width="80%">
				<tr>
					<td>
						新闻类别
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="groupName" />
					</td>
				</tr>
				<tr>
					<td>
						描述
					</td>
				</tr>
				<tr>
					<td>
						<textarea name="description" cols="30" rows="4"></textarea>
					</td>
				</tr>
				<tr>
					<td style="align: right">
						<input type="hidden" name="status" value="addGroup" />
						<input type="submit" value="提交" />
						<input type="reset" value="重置" />
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html:html>
