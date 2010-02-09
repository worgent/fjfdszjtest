<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html lang="true">
<head>
	<html:base />
	<title>修改配送信息</title>
	<link href="<%=request.getContextPath()%>/css/header01.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/index.css" type="text/css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/catalog.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
</head>

<body>
    <center>
    <jsp:include page="top.jsp"/>
    <table width="98%" align="center" cellspacing="1" cellpadding="2" bgcolor="#e1e1e1" border="0">
    <tr>
    <td bgcolor="#ffffff" align="left">
		<a
			href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition">我的账户</a>
		> 订单信息 > 订单#${receiveInfo.orderId} > 修改配送信息
	</td>
	</tr>
	<tr>
	<td bgcolor="#ffffff">
	<form action="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do" method="post">
		<table width="100%" >
			<tr> 
				<td bgcolor="#ffffff"> 
					修改配送信息
				</td> 
			</tr>
			<tr>
				<td bgcolor="#ffffff">
					<table width="100%">
						<tr>
							<td align="right" width="50%">
								收货人姓名:
							</td>
							<td align="left">
								<input type="text" name="receiveUser"
									value="${receiveInfo.receiveName}" />
							</td>
						</tr>
						<tr>
							<td align="right">
								邮政编码:
							</td>
							<td align="left">
								<input type="text" name="receivePostCode"
									value="${receiveInfo.receivePostCode }" />
							</td>
						</tr>
						<tr>
							<td align="right">
								电话:
							</td>
							<td align="left">
								<input type="text" name="receivePhone"
									value="${receiveInfo.receivePhone }" />
							</td>
						</tr>
						<tr>
							<td align="right">
								手机:
							</td>
							<td align="left">
								<input type="text" name="receiveCellPhone"
									value="${receiveInfo.receiveCellPhone }" />
								<font color="red">（手机和电话至少有一项必填）</font>
							</td>
						</tr>
						<tr>
							<td align="right">
								收货人地址:
							</td>
							<td align="left">
								<input type="text" name="receiveAddress" size="50"
									value="${receiveInfo.receiveAddress }" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<center>
									<input type="button" value="返回" />
									&nbsp;&nbsp;&nbsp;
									<html:submit value="完成修改"></html:submit>
								</center>
								<input type="hidden" name="orderId"
									value="${receiveInfo.orderId}" />
								<input type="hidden" name="status" value="updateReceiveInfo" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	</td>
	</tr>
	</table>
	<jsp:include flush="true" page="bottom.jsp" />
	</center>
</body>
</html:html>

