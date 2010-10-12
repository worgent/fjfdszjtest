<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:useBean class="HibernateBeans.cms.cmsInfo" scope="page" id="cmsInfo"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh" xml:lang="zh" xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<meta name="generator" content="Adobe GoLive" />
		<title>AICMS<%=cmsInfo.getVersion()%>-网站管理登录</title>
		<link href="style/login.css" rel="stylesheet" type="text/css" media="all" />
	</head>

	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
			<tr height="40%">
				<td width="19%" height="40%"></td>
				<td width="776" height="200"></td>
				<td width="19%" height="40%"></td>
			</tr>
			<tr height="166">
				<td width="19%" height="166"></td>
				<td width="776" height="166"><div style="position:relative;width:776px;height:166px;background-image:url(images/Loginbackground.jpg);">
				  <table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="4%" height="97">&nbsp;</td>
                      <td width="27%" align="center" valign="top">
                      <div style="color:#FFFFFF; font-family:'隶书','黑体'; font-size:30px;">AICMS</div>
                      <div style="color:#FFFFFF; font-size:20px; font-family:'Arial', 'Helvetica', 'sans-serif';">V<%=cmsInfo.getVersion()%></div></td>
                      <td width="69%">&nbsp;</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                  </table>
<div style="position:absolute;top:125px;left:301px;width:468px;height:33px;">
							<form action="login$.jsp" method="post" style="padding:0px; margin:0px;">
							<table width="488" border="0" cellpadding="0" cellspacing="5">
								<tr>
									<td width="72">用户名：</td>
								  <td width="130"><input name="username" type="text" id="username" size="17" /></td>
									<td width="55">密码：</td>
								  <td width="109"><input name="password" type="password" id="password" size="15" /></td>
								  <td width="72"><input type="image" src="images/Loginbutton.jpg" alt="" /></td>
								</tr>
							</table>
							</form>
					  </div>
					</div>
				</td>
				<td width="19%" height="166"></td>
			</tr>
			<tr>
				<td width="19%"></td>
				<td width="776"></td>
				<td width="19%"></td>
			</tr>
		</table>
		<p></p>
	</body>

</html>