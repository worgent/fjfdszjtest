<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'netPay.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<center>
			<form name="SendToMer" method="post"
				action="/pay.do">

				<input type="text" name="MerId" value="808080092399170" />
				<br>
				<input type="text" name="OrdId" value="2008991700000001" />
				<br>

				<input type="text" name="TransAmt" value="000000001234" /><br>
				<input type="text" name="CuryId" value="156" /><br>
				<input type="text" name="TransDate" value="20081023" /><br>
				<input type="text" name="TransType" value="0001" /><br>
				<input type="text" name="Version" value="20040916" /><br>
				<input type="text" name="BgRetUrl"
					value="<%=request.getContextPath()%>/welcome.jsp" /><br>
				<input type="text" name="PageRetUrl"
					value="<%=request.getContextPath()%>/welcome.jsp" /><br>
			   <input type="text" name="ChkValue"
					value="A6D10BC8FF7BFB3C5A86678A5F3963D543868BC53CF520FDC614A7D0D9F7BE43BC25B0BC8C1844E78AA98F9193C4B53C28A7E9B07AD2572C241D47AA6591E1DE3DF22B9A83565E42DF21970020415E2DE0462B543AFB30793EEDF2490EAE47E5EB61BB0429C0F65F81E53B0F25C9EC96CC84BD2FD25FBDA0640694027E5D5AF8" />
				
				<br>
				<input type="text" name="GateId" value="0001"><br>
				<input type="text" name="Priv1" value="Memo"><br>
				<input type="submit" value="Ìá½»"/>
					<input type="reset" value="ÖØÖÃ"/>
			</form>
		</center>
	</body>
</html>
