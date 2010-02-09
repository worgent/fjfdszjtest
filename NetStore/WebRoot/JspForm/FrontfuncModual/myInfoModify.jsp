<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html lang="true">
<head>
	<html:base />
	<title>修改用户的联系信息</title>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
		<link href="<%=request.getContextPath()%>/css/header01.css"
		rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/index.css"
		type="text/css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/css/catalog.css"
		type="text/css" rel="stylesheet" />
</head>

<body>
	<center>
		<jsp:include page="top.jsp" />
		<table width="98%" align="center" cellspacing="1" cellpadding="2"
			bgcolor="#e1e1e1" border="0">
			<tr>
				<td bgcolor="#ffffff" align="left">
					<a
						href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition">我的账户</a>
					> 修改我的联系信息
				</td>
			</tr>
			<tr>
				<td bgcolor="#ffffff">
					<form
						action="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do"
						method="post">
						<table  width="100%">
							<tr>
								<td align="left">
									修改我的联系信息
								</td>
							</tr>
							<tr>
								<td>
									<table  width="100%">
										<tr>
											<td align="right" width="30%">
												我的姓名:
											</td>
											<td align="left">
												<input type="text" name="realName"
													value="${userInfoMap.realName}" />
											</td>
										</tr>
										<tr>
											<td align="right">
												性别:
											</td>
											<td align="left">
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
											<td align="left">
												<input type="text" name="email" value="${userInfoMap.email}" />
											</td>
										</tr>
										<tr>
											<td align="right">
												邮政编码:
											</td>
											<td align="left">
												<input type="text" name="postCode"
													value="${userInfoMap.postCode }" />
											</td>
										</tr>
										<tr>
											<td align="right">
												电话:
											</td>
											<td align="left">
												<input type="text" name="phone"
													value="${userInfoMap.phone }" />
											</td>
										</tr>
										<tr>
											<td align="right">
												手机:
											</td>
											<td align="left">
												<input type="text" name="cellPhone"
													value="${userInfoMap.cellPhone }" />
												<font color="red">（手机和电话至少有一项必填）</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												我的联系地址:
											</td>
											<td align="left">
												<input type="text" name="address" size="50"
													value="${userInfoMap.address}" />
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<center>
													<input type="button" value="返回" />
													&nbsp;&nbsp;&nbsp;
													<html:submit value="完成修改"></html:submit>
													<input type="hidden" name="orderId"
													value="${receiveInfo.orderId}" />
												<input type="hidden" name="status" value="updateMyInfo" />
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

