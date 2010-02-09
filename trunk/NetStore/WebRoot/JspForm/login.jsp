
<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html><head><title>管理员登陆</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../css/css.css" rel="stylesheet" type="text/css">





 <script type="text/javascript">
 <logic:present name="notExist">
 	alert('${notExist}');
 </logic:present>
 <logic:present name="loginFail">
 	alert('${loginFail}');
 </logic:present>
 
 </script> 



</head>
<body leftmargin="0" topmargin="20" marginwidth="0" marginheight="0">
<TABLE WIDTH=420 BORDER=0 CELLPADDING=0 CELLSPACING=0  align=center>
<TR>
<TD><IMG SRC="<%=request.getContextPath()%>/images/login_admin1.jpg" WIDTH=420 HEIGHT=36></TD>
</TR>
<TR>
<TD><IMG SRC="<%=request.getContextPath()%>/images/login_admin2.jpg" WIDTH=420 HEIGHT=106></TD>
</TR>
<TR>
<TD background="<%=request.getContextPath()%>/images/login_admin3.jpg" WIDTH=420 HEIGHT=137>
<form name="admininfo" method="post" action="<%=request.getContextPath()%>/login.do?status=loginEnter" >
<table width="420" border="0" align="center" cellpadding="2" cellspacing="1" height="115">
<tr> 
<td width="75%"  align="center" valign="bottom"><font size="2" color="#297194">管理员帐号:</font>
<input   name="admin" type="text" id="admin" size="19" value="admin">
</td>
</tr>
<tr> 
<td width="75%"  align="center"><font size="2" color="#297194">管理员密码:</font>
<input  name="password" type="password" id="password" size="20" />
</td>

</tr>
<tr> 
<td  align="center" >
<input type="image" name="ImageButton1" onclick="submit" id="ImageButton1" tabindex="4" src="<%=request.getContextPath()%>/images/login.gif" alt="" border="0" > 
<input type="image" name="ImageButton2" onclick="reset"  id="ImageButton2" tabindex="5" src="<%=request.getContextPath()%>/images/reset.gif" alt="" border="0" >
</td>
</tr>
</table>
</form></TD>
</TR>
<TR>
<TD>
<IMG SRC="<%=request.getContextPath()%>/images/login_admin4.jpg" WIDTH=420 HEIGHT=51></TD>
</TR>
</TABLE>
</body>
</html>