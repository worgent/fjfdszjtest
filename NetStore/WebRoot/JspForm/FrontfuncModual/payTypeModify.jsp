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
		<table width="98%" align="center" cellspacing="1" cellpadding="2"
			bgcolor="#e1e1e1" border="0">
			<tr>
				<td bgcolor="#ffffff" align="left">
					<a
						href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition">我的账户</a>
					> 订单信息 > 订单#${orderId} > 修改付款方式
				</td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="left">
					<form
						action="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do"
						method="post">
						<table width="100%">
							<tr>
								<td>
									修改送货方式
								</td>
							</tr>
							<tr>
								<td align="left">
									<table width="100%" >
										<logic:present name="payTypeList">
											<logic:iterate id="item" name="payTypeList">
												<tr> 
													<td width="20%" align="left">
														<input type="radio" name="payTypeId"
															${item.ifChecked} value="${item.payTypeId}" />
														${item.payTypeName }
													</td>
													<td align="left">
														<logic:present name="item" property="companyName">
						                     公司名称:${item.companyName }<br/>
														</logic:present>
														<logic:present name="item" property="openAccountName">
						                     开户行名称:${item.openAccountName }<br/>
														</logic:present>
														<logic:present name="item" property="bankAccount">
						                     银行账号:${item.bankAccount }<br/>
														</logic:present>
														<logic:present name="item" property="remark">
						                  ${item.remark }<br/>
														</logic:present>
													</td>
												</tr>
											</logic:iterate>
										</logic:present>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<center>
										<input type="hidden" name="status" value="updatePayType" />
										<input type="hidden" name="orderId" value="${orderId}" />
										<input type="hidden" name="sendFee" />
										<input type="button" value="返回" />
										&nbsp;&nbsp;&nbsp;
										<html:submit value="完成修改"></html:submit>
									</center>
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

