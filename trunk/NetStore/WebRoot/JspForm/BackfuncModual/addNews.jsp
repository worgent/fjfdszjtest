<%@ page language="java" import="com.fredck.FCKeditor.*"
	pageEncoding="gbk"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
	<title>首页新闻添加</title>
    <link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
	<center>
		<form id="newsForm"
			action="<%=request.getContextPath()%>/JspForm/BackfuncModual/news.do"
			method="post">
			<table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">
						首页新闻添加
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						新闻主题:
					</td>
					<td class="main" align="left">
						<input type="text" name="newsTitle" size="100%" />
					</td>
				</tr>
				<tr>
					<td valign="top" class="main" align="right">
						新闻内容:
					</td>
					<td>
						<%
			// 获得上下文路径
			String path = request.getContextPath();
			FCKeditor oFCKeditor;
			// 定义一个属性来使Action通过request来获得FCKeditor编辑器中的值
			oFCKeditor = new FCKeditor(request, "content");
			oFCKeditor.setBasePath(path + "/fckeditor/");
			// 设置FCKeditor编辑器打开时的默认值
			oFCKeditor.setValue("");
			out.println(oFCKeditor.create());
%>          
					</td>
				</tr>
				<tr>
					<td class="main" align="center" colspan="2">
						<input type="hidden" name="status" value="addNews" />
						<input type="submit" value="提交发表" class="button"/>
		                <input type="reset" value="重新填写" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html:html>
