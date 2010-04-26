<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>登录</title>
	</head>

	<body>
		<p>&nbsp;</p>
		<s:form action="/admin/adminLogout.do" target="_top">
	

			<table width="400" border="0" align="center" cellpadding="10"
				cellspacing="0" class="table1">
				<tr>
					<td>
						<table width="70%" border="1" align="center" cellpadding="5"
							cellspacing="0">
							<tr>
								<td colspan="2" align="center">
									<strong>登录</strong>
								</td>
							</tr>
							
							<tr>
								<td width="34%">
									<div align="right">
									用户名
									</div>
								</td>
								<td width="66%">
									<s:textfield name="userInfo.staffNo" id="staffNo" required="true" size="15" maxlength="20"></s:textfield>
								</td>
							</tr>
							<tr>
								<td>
									<div align="right">
										密码
									</div>
								</td>
								<td>
									<s:password name="userInfo.password" id="password" size="15" required="true" maxlength="20"></s:password>
								</td>
							</tr>
							
							<tr>
								<td colspan="2">
									<div align="center">
										<s:submit value="提交" ></s:submit>
										<s:reset value="重置"></s:reset>

									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
