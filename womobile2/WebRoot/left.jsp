<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
		<script type="text/javascript" src="js/wtree.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
</head>
<body>
<table width="173" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
  <tr>
    <td style="width:4px; background-image:url(images/main_16.gif)">&nbsp;</td>
    <td width="169" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="20" background="images/main_11.gif" style="vertical-align:middle"><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript: d.openAll();" style="font-size: 12px;color: #FFFFFF;text-decoration: none;">全部展开</a>&nbsp;|&nbsp;<a href="javascript: d.closeAll();" style="font-size: 12px;color: #FFFFFF;text-decoration: none;">全部收缩</a></p></td>
      </tr>
      <tr>
        <td>
                   <div class="dtree" id="dtree2">
						<s:property value="menuTree" escape="false" />
					</div>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
