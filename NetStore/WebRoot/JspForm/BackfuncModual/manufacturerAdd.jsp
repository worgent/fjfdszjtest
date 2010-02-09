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
	<title>厂商信息添加</title>
        <link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/css.css" />
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
	<form
		action="<%=request.getContextPath()%>/JspForm/BackfuncModual/manufacturer.do"
		method="post">
		<center>
			<table class="tbl" width="80%"  cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="4"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">
						厂家信息添加
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						厂商名称
					</td>
					<td class="main" align="left">
						<input type="text" name="manufacturerName" />
					</td>
					<td class="main" align="right">
						厂家网站
					</td>
					<td class="main" align="left">
						<input type="text" name="producerHomePage" />
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						联系人
					</td>
					<td class="main" align="left">
						<input type="text" name="contactMan" />
					</td>
					<td class="main" align="right">
						电话
					</td>
					<td class="main" align="left">
						<input type="text" name="phone" />
					</td>
				</tr>

				<tr>
					<td class="main" align="right">
						手机号码
					</td>
					<td class="main" align="left">
						<input type="text" name="cellPhone" />
					</td>
					<td class="main" align="right">
						联系地址
					</td>
					<td class="main" align="left">
						<input type="text" name="contactAddress" />
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						邮编
					</td>
					<td class="main" align="left">
						<input type="text" name="postCode" />
					</td>
					<td class="main" align="right">
					    &nbsp;
					</td>
					<td class="main" align="left"> 
					    &nbsp;
					</td>
				</tr>
				<tr>
					<td class="main" align="right">
						厂家简介
					</td>
					<td  class="main"  colspan="3">
						<%
			// 获得上下文路径
			String path = request.getContextPath();
			FCKeditor oFCKeditor;
			// 定义一个属性来使Action通过request来获得FCKeditor编辑器中的值
			oFCKeditor = new FCKeditor(request, "manufacturerInfo");
			oFCKeditor.setBasePath(path + "/fckeditor/");
			// 设置FCKeditor编辑器打开时的默认值
			oFCKeditor.setValue("");
			out.println(oFCKeditor.create());
%>
					</td>
				</tr>
				<tr align="center">
					<td colspan="4" class="main" align="center">
						<input type="hidden" name="status" value="addManufacturer"/>
						<input type="submit" value="添加" class="button"/>
						<input type="button" value="返回" class="button" />
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
