<%@ page contentType="text/html; charset=GBK"%>
<%
    com.apricot.app.eating.LoginData ld = (com.apricot.app.eating.LoginData) session
		    .getAttribute(com.apricot.app.eating.LoginData.LOGIN_SESSION_KEY);
%>

<table width="100%" height="33" class="x-panel-header" cellspacing="0"
	cellpadding="0">
	<tr>
		<td width="100%">&nbsp;&nbsp;当前登录员工: <%=ld.getStaffName()%>&nbsp;&nbsp;&nbsp;店面：<%=ld.getShopName()%>
		</td>
		<td width="30">
		<table border="0">
			<tr>
				<td nowrap><a id="main_banner_safe_logout" href="javascript:" style="font-size:9pt;">安全退出</a></td>
				<td nowrap><a id="main_banner_safe_changepassword" href="javascript:" style="font-size:9pt;">修改密码</a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<script language="javascript">

document.getElementById("main_banner_safe_logout").onclick=function(){
	window.location.href=currentPageBaseHREF+'/logout.jsp';
};

document.getElementById("main_banner_safe_changepassword").onclick=function(){
	alert('增加修改密码事件banner.jsp');
}

</script>

