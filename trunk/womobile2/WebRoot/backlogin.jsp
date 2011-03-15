<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>后台管理</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

.STYLE3 {
	color: #528311;
	font-size: 12px;
}

.STYLE4 {
	color: #42870a;
	font-size: 12px;
}
-->
</style>
<script type="text/javascript">
function submit(){
document.form1.submit();
}

</script>
	</head>

	<body>
		<s:form action="backlogin" name="form1" target="_top">
			<s:hidden id="action" name="action"></s:hidden>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td bgcolor="#E9F8D6">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width="100%" height="706" background="images/login_01.gif">
						<table width="634" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td colspan=2 height="67" background="images/login_03.gif">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td width="221" height="155" background="images/login_05.gif">
									&nbsp;
								</td>
								<td width="413" height="155" background="images/login_06.gif">
									<table width="100%" border="0" height="155" cellspacing="0"
										cellpadding="0">
										<tr>
											<td width="40px" align="left" height="18px">
												<div align="center">
													<span class="STYLE3">用&nbsp;&nbsp;户</span>
												</div>
											</td>
											<td align="left" height="18px">
												<s:textfield name="username" id="pcode" required="true"
													size="15" maxlength="40" value=""
													cssStyle="height:18px; width:110px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></s:textfield>
												<div id="pcodeTip" style="display: inline"></div>
											</td>
										</tr>
										<tr>
											<td align="left" height="18px">
												<div align="center">
													<span class="STYLE3">密&nbsp;&nbsp;码</span>
												</div>
											</td>
											<td align="left" height="18px">
												<s:password name="passwd" id="ppasswd"
													required="true" maxlength="20" value=""
													cssStyle="height:18px; width:110px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></s:password>
												<div id="ppasswdTip" style="display: inline"></div>
											</td>

										</tr>
										<tr>
											<td align="left" height="18px" style="vertical-align: middle">
												<div style="vertical-align: middle">
													<span class="STYLE3">验证码</span>
												</div>
											</td>
											<td align="left" height="18px" style="vertical-align: middle">
												<s:textfield id="pverifycode" name="authCode"
													maxlength="4"
													cssStyle="height:18px; width:40px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></s:textfield>
												<img alt="验证码" src="authimg" align="absmiddle" />
												<div id="pverifycodeTip" style="display: inline"></div>
											</td>
										</tr>
										<tr>
											<td align="left" height="18">
												&nbsp;
											</td>
											<td align="left" height="18">
												<img src="images/dl.gif" width="81" height="22" border="0"
													style="cursor: hand" onclick="submit()" usemap="#Map" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width="221" height="144" background="images/login_07.gif">
									&nbsp;
								</td>
								<td width="413" height="144" background="images/login_08.gif"
									class="STYLE4" style="vertical-align: top">
									<div
										style="border: 0px; vertical-align: middle; width: 100px; height: 30px; line-height: 30px">
										版本 2010V1.0
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td bgcolor="#a2d962">
						&nbsp;
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
