<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html>
	<head>
		<base href="<%=basePath%>">
		<title>修改E-mail</title>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
		<link href="<%=request.getContextPath()%>/css/header01.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/index.css"
			type="text/css" rel="stylesheet" />
		<link href="<%=request.getContextPath()%>/css/catalog.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript">
   	    <logic:present name="xgResult">
   	        alert("${xgResult}");
   	    </logic:present>
    </script>
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
						> 修改E-mail
					</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff">
						<form
							action="<%=request.getContextPath()%>/JspForm/FrontfuncModual/account.do"
							method="post">
							<table width="100%">
								<tr>
									<td>
										修改E-mail
									</td>
								</tr>
								<tr>
									<td valign="middle" align="center">
										<span style="text-align: left">由于email具有找回密码的功能，为了防止在您离开电脑时，email被他人恶意修改，此处需要您输入登录密码。</span>
										<br />
										<table>
											<tr>
												<td align="right">
													您的登录密码:
												</td>
												<td>
													<input type="password" name="userPwd" />
												</td>
											</tr>
											<tr>
												<td align="right">
													E-mail地址:
												</td>
												<td>
													<input type="text" name="email"
														value="${userMailMap.email }" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td align="center">
										<input type="hidden" name="status" value="updateUserEmail" />
										<input type="button" value="返回" />
										<input type="submit" value="完成修改" />
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
</html>

