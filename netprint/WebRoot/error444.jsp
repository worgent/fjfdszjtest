<%@page language="java" isErrorPage="true" pageEncoding="GB2312" contentType="text/html;charset=gb2312" %>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="themes/default/style/newcss.css">
<title></title>
</head>
<body topmargin="0" leftmargin="0">
<table width="100%" height="100%" cellspacing='0' cellpadding='0' border='0'>
	<tr>
		<td valign="middle">
			<table width="400" height="157" cellspacing='0' cellpadding='0' border='0' align="center" style="background-image: url(images/bg.gif);background-position:0% 0%;background-repeat: no-repeat;">
				<tr height=30>
					<td colspan=4></td>
				</tr>
				<tr>
					<td width=30></td>
					<td ><img src="/ext/resources/images/default/window/icon-error.gif"></td>

					<td valign="top">
						<table cellspacing='0' cellpadding='0' border='0'>
							<tr height=10 colspan=2>
								<td></td>
							</tr>
							<tr>
								<td style="PADDING-left:12px;">
									<html:errors/>
									<br>
								</td>
							</tr>
							<tr height=7 colspan=2>
								<td></td>
							</tr>
							<tr>
								<td colspan=2 align=right> </td>
							</tr>
						</table>
						<span style="color:red">
						<ww:if test="null != actionMessages">
								<ww:iterator value="actionMessages">
									<ww:property />
								</ww:iterator>
								<br>
								<br>
						</ww:if>
						<ww:else>
							出错了
						</ww:else>
							<a onclick="history.back(-1);" href="javascript:void(0)">返回</a> 
						</span>
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function err_msg(){
		if(document.all.err_msg.style.display==""){
			document.all.err_msg.style.display="none";
		}else{
			document.all.err_msg.style.display="";
		}
	}
</script>
</body>
</html>
