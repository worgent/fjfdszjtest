<%@ page language="java" import="com.fredck.FCKeditor.*,java.util.Map"
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
	<title>��ҳ���Ź���</title>
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
					<td colspan="2" bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">
						������վ����
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						��������
					</td>
					<td class="main" align="left">
						<input type="text" name="newsTitle" size="100%" value="${newsMap.newsTitle}" />
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						������
					</td>
					<td class="main" align="left">
						${newsMap.releaseMan }
					</td>
				</tr>
				<tr>
					<td valign="top" class="main" align="right">
						��������
					</td>
					<td>
						<%
			// ���������·��
			String path = request.getContextPath();
			FCKeditor oFCKeditor;
			// ����һ��������ʹActionͨ��request�����FCKeditor�༭���е�ֵ
			oFCKeditor = new FCKeditor(request, "content");
			oFCKeditor.setBasePath(path + "/fckeditor/");
			Map map=(Map)request.getAttribute("newsMap");
			// ����FCKeditor�༭����ʱ��Ĭ��ֵ
			oFCKeditor.setValue((String)map.get("newsContent"));
			out.println(oFCKeditor.create());
%>          
					</td>
				</tr>
				<tr>
					<td class="main">
						&nbsp;
					</td>
					<td class="main">
						<input type="hidden" name="id" value="${newsMap.newsId }" />
						<input type="hidden" name="status" value="updateNews" />
						<input type="submit" value="�����޸�" class="button"/>
		                <input type="reset" value="����" class="button"/>
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html:html>