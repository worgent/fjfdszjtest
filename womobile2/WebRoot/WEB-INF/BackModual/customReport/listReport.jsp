<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<HTML>

	<HEAD>
		<TITLE>机构树</TITLE>
		<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
		<script type="text/javascript" src="js/wtree.js"></script>
		<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
	</HEAD>
	<BODY bgcolor="white" leftmargin="0" topmargin="0" marginheight="0"
		marginwidth="0"
		onResize="if (navigator.family == 'nn4') window.location.reload()">

		<TABLE cellpadding="0" cellspacing="0" border="0">
			<TR>
				<td>
					<div class="dtree" id="dtree2">
						<s:property value="menuTree" escape="false" />
					</div>
				</td>
			</TR>
		</TABLE>
	</BODY>

</HTML>