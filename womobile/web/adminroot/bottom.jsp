<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<jsp:useBean class="HibernateBeans.cms.cmsInfo" scope="page" id="cmsInfo"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>bottom</title>
<link href="styles/table.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #99CCCC;
}
-->
</style></head>

<body>
<table width="100%">
  <tr>
      <td align="left">&nbsp;</td>
    <td align="right"><a href="http://successage.googlepages.com" target="_blank">AICMS<%=cmsInfo.getVersion()%> based,Powered By Dreamidea Studio</a></td>
  </tr>
</table>
</body>
</html>
