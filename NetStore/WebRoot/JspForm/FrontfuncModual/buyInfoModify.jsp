<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>修改购买者联系信息</title>
	<link href="<%=request.getContextPath()%>/css/header01.css"
		rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/index.css"
		type="text/css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/css/catalog.css"
		type="text/css" rel="stylesheet" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
</head>

<body>
	<center>
		<jsp:include page="top.jsp" />
		<table width="69%" align="center" cellspacing="1" cellpadding="2"
			bgcolor="#e1e1e1" border="0">
			<tr>
				<td bgcolor="#ffffff">
					<a
						href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition">我的账户</a>
					> 订单信息 > 订单#${receiveInfo.orderId} > 修改订购者联系信息
				</td>
			</tr>
			<tr>
				<td bgcolor="#ffffff">
					<form
						action="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do"
						method="post">
						<table  width="100%">
							<tr>
								<td>
									修改订购者联系信息
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%">
										<tr>
											<td align="right">
												订购者姓名:
											</td>
											<td>
												<input type="text" name="realName"
													value="${userInfoMap.realName}" />
											</td>
										</tr>
										<tr>
											<td align="right">
												性别:
											</td>
											<td>
												<input type="radio" name="sex" value="1"
													${userInfoMap.sex==1? 'checked':'' }/>
												男
												<input type="radio" name="sex" value="0"
													${userInfoMap.sex==0? 'checked':'' }/>
												女
											</td>
										</tr>
										<tr>
											<td align="right">
												电子邮件:
											</td>
											<td>
												<input type="text" name="email" value="${userInfoMap.email}" />
											</td>
										</tr>
										<tr>
											<td align="right">
												邮政编码:
											</td>
											<td>
												<input type="text" name="postCode"
													value="${userInfoMap.postCode }" />
											</td>
										</tr>
										<tr>
											<td align="right">
												电话:
											</td>
											<td>
												<input type="text" name="phone"
													value="${userInfoMap.phone }" />
											</td>
										</tr>
										<tr>
											<td align="right">
												手机:
											</td>
											<td>
												<input type="text" name="cellPhone"
													value="${userInfoMap.cellPhone }" />
												<font color="red">（手机和电话至少有一项必填）</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												订购者地址:
											</td>
											<td>
												<input type="text" name="address" size="50"
													value="${userInfoMap.address}" />
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
												<input type="hidden" name="orderId"
													value="${receiveInfo.orderId}" />
												<input type="hidden" name="status" value="updateBuyInfo" />
											</td>
											<td>
												<center>
													<input type="button" value="返回" />
													&nbsp;&nbsp;&nbsp;
													<html:submit value="完成修改"></html:submit>
												</center>
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

