<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>�޸Ĺ�������ϵ��Ϣ</title>
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
						href="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do?status=queryOrderByCondition">�ҵ��˻�</a>
					> ������Ϣ > ����#${receiveInfo.orderId} > �޸Ķ�������ϵ��Ϣ
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
									�޸Ķ�������ϵ��Ϣ
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%">
										<tr>
											<td align="right">
												����������:
											</td>
											<td>
												<input type="text" name="realName"
													value="${userInfoMap.realName}" />
											</td>
										</tr>
										<tr>
											<td align="right">
												�Ա�:
											</td>
											<td>
												<input type="radio" name="sex" value="1"
													${userInfoMap.sex==1? 'checked':'' }/>
												��
												<input type="radio" name="sex" value="0"
													${userInfoMap.sex==0? 'checked':'' }/>
												Ů
											</td>
										</tr>
										<tr>
											<td align="right">
												�����ʼ�:
											</td>
											<td>
												<input type="text" name="email" value="${userInfoMap.email}" />
											</td>
										</tr>
										<tr>
											<td align="right">
												��������:
											</td>
											<td>
												<input type="text" name="postCode"
													value="${userInfoMap.postCode }" />
											</td>
										</tr>
										<tr>
											<td align="right">
												�绰:
											</td>
											<td>
												<input type="text" name="phone"
													value="${userInfoMap.phone }" />
											</td>
										</tr>
										<tr>
											<td align="right">
												�ֻ�:
											</td>
											<td>
												<input type="text" name="cellPhone"
													value="${userInfoMap.cellPhone }" />
												<font color="red">���ֻ��͵绰������һ����</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												�����ߵ�ַ:
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
													<input type="button" value="����" />
													&nbsp;&nbsp;&nbsp;
													<html:submit value="����޸�"></html:submit>
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
