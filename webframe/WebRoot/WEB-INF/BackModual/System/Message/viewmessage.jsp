<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<html>
	<head>
		<script type="text/javascript">

		</script>
	</head>
	<body>
	<table align="center">
	<tr>
	<td>发件人:<s:property value="message.SENDNAME"/></td>
	</tr>
	<tr>
	<td>收件人:<s:property value="message.RECNAME"/></td>
	</tr>
	<tr>
	<td>标题:<s:property value="message.TITLE"/></td>
	</tr>
	<tr>
	<td>内容:<s:property value="message.CONTENT"/></td>
	</tr>
	<tr>
	<td>发送时间:<s:property value="message.SENDTIME"/></td>
	</tr>
	</table>
	</body>
</html>