<%@ page language="java"%>
<%@ page contentType="text/html;charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
	<head>
		<title></title>
		<link rel="StyleSheet" href="css/style.css" type="text/css" />
		<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
		<script type="text/javascript" src="include/dtree.js"></script>
	</head>
	<body bgcolor="#e0f4ff" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0">
		<table width="180" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="10">
				</td>
			</tr>
			<tr>
				<td align="center">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="26" width="135"
								background="<%=request.getContextPath()%>/images/bar123.gif"
								align="center">
								<a href="javascript: d.openAll();"><img
										src="<%=request.getContextPath()%>/images/nolines_plus.gif"
										align="absmiddle" border="0"><font color="#666666">展开</font>
								</a>&nbsp;|&nbsp;
								<a href="javascript: d.closeAll();"><img
										src="<%=request.getContextPath()%>/images/nolines_minus.gif"
										align="absmiddle" border="0"><font color="#666666">收缩</font>
								</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
		</table>
		<span id="loadtxt">
			<p align="center">
				<img src="../images/loading.gif" alt="数据载入中……" align="absmiddle"
					border="0" />
				&nbsp;正在载入菜单...
			</p> </span>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="5">
					&nbsp;
				</td>
				<td>

					<div class="dtree">

						<script type="text/javascript">
		d = new dTree('d');
		var sjmlbh;
		    <logic:present name="modulelist" scope="request">
			<logic:iterate id="module" name="modulelist" scope="request">
			d.add('<bean:write name="module" property="theCode"/>','<bean:write name="module" property="theParentCode"/>','<bean:write name="module" property="theName"/>','login.do?status=menuForward&ID=<bean:write name="module" property="theCode"/>','<bean:write name="module" property="theRemark"/>','mainFrame');
			</logic:iterate>
			</logic:present>
		
			<logic:present name="menu1list" scope="request">
			<logic:iterate id="menu1" name="menu1list" scope="request">
			sjmlbh='<bean:write name="menu1" property="theCode"/>';
			d.add('<bean:write name="menu1" property="theCode"/>','<bean:write name="menu1" property="theParentCode"/>','<bean:write name="menu1" property="theName"/>');
			</logic:iterate>
			</logic:present>
			d.add('<bean:write name="mlbh"/>',-1,'<bean:write name="mlmc"/>');
			loadtxt.innerHTML="";
			document.write(d);
	</script>
					</div>
				</td>
			</tr>
		</table>
		<br>
		<br>



	</body>
</html>