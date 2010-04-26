<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
	<script type="text/javascript" src="js/comm.js"></script>
<script language="JavaScript" type="text/javascript">
<!--
function upIframeSize() {
  var objif = document.getElementById('upfileIframe');
  objif.height = 440;
}
//-->
</script>
<link href="css/css1.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<table width="50%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center">
					所属相册:<s:property id="a_name"  value="%{a_name}" />
				</td>
				<td align="right">
					<strong><s:a href="album.do">相看相册列表</s:a></strong>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<fieldset>
						<legend>
							上传照片
						</legend>
						<s:url action="album?action=uppage" id="uppage" namespace="/">
							<s:param name="a_Id" value="%{a_Id}"/>
						</s:url>
						<iframe id="upfileIframe"
							src="${uppage}" height="440" width="98%" scrolling="no" frameborder="0"></iframe>
					</fieldset>
				</td>
			</tr>
		</table>
	</body>
</html>
