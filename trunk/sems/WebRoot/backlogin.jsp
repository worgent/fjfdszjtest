<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>成渝</title>
		<link href="./css/css1.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<s:form action="backlogin" target="_top">
			<s:hidden id="action" name="action" ></s:hidden>
			<table width="400" border="0" align="center" cellpadding="10"
				cellspacing="0" class="table1">
				<tr>
					<td colspan="2">
						<strong>后台用户登录</strong>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:actionerror theme="ems" />
					</td>
				</tr>
				<tr>
					<td width="34%">
						<div align="right">
							<s:text name="用户名" />
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
							<s:text name="密码" />
							:
						</div>
					</td>
					<td>
						<s:password name="passwd" id="passwd" cssClass="input2" size="15"
							maxlength="20"></s:password>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							<s:text name="验证码" />
							:
						</div>
					</td>
					<td>
						<s:textfield id="authCode" name="authCode" size="5" maxlength="4"
							cssClass="input2"></s:textfield>
						<img alt="验证码" src="authimg" align="absmiddle" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<s:submit value="登录" cssClass="button1"></s:submit>
							<s:reset value="重置" cssClass="button1"></s:reset>
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
