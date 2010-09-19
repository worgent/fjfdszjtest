<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>错误信息</title>
	</head>

	<body>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<table width="600" border="0" align="center" cellpadding="10"
			cellspacing="0">
			<tr>
				<td width="400">
					<div id="error" class="errormsg">
						<s:property value="%{interceptError}" escape="false"/>
					</div>
				</td>
			</tr>
			<tr>
			    <td>
			    &nbsp;
			    </td>
			    <td>
			    <div id="loginform" style="display:none">
					<s:form action="login">
						<s:hidden name="action" value="login"></s:hidden>
						<s:hidden name="tourl" value="%{tourl}"></s:hidden>
						<table width="100%" border="0" align="center" cellpadding="5"
							cellspacing="0">
							<tr>
								<td colspan="2">
									<strong><s:text name="login.title" />:<s:text
											name="login.input" />
									</strong>
								</td>
							</tr>
							<tr>
								<td width="34%">
									<div align="right">
										<s:text name="login.username" />
										:
									</div>
								</td>
								<td width="66%">
									<s:textfield name="username" id="username" cssClass="input2"
										size="15" maxlength="20"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>
									<div align="right">
										<s:text name="login.passwd" />
										:
									</div>
								</td>
								<td>
									<s:password name="passwd" id="passwd" cssClass="input2"
										size="15" maxlength="20"></s:password>
								</td>
							</tr>
							
							<tr>
								<td colspan="2">
									<div align="center">
										<s:submit value="登录"
											cssClass="button1"></s:submit>
										<s:reset value="重置"
											cssClass="button1"></s:reset>
									</div>
								</td>
							</tr>
						</table>

					</s:form>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<a href="javascript:history.go(-1);">返回</a>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
