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
		<link href="css/css1.css" rel="stylesheet" type="text/css" />
		<title>发起订阅</title>
	</head>
	<body>
		<table width="780" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
		</table>
		<form action="<%=path%>/album.do" method="post">
		<s:hidden name="action"></s:hidden>
		<s:hidden name="a_Id"></s:hidden>
		<table width="700" border="0" align="center" cellpadding="10"
				cellspacing="0" class="table1">
				<tr>
					<td>
						<table width="70%" border="0" align="center" cellpadding="5"
							cellspacing="0">
							<tr>
								<td colspan="2">
									<strong>创建新相册</strong>
								</td>
							</tr>
							<tr>
        						<td colspan="2">
        							<s:actionerror theme="webframe0"/>
        						</td>
      						</tr>
      						<tr>
						        <td colspan="2">
						        <s:actionmessage theme="webframe0"/>
						        </td>
						    </tr>
							<tr>
								<td width="34%" >
									<div align="right">
										相册名称:
									</div>
								</td>
								<td width="66%">
									<s:textfield name="a_name"  id="a_name" cssClass="input2" size="20" maxlength="30"></s:textfield>
								</td>
							</tr>
							<tr>
								<td width="34%">
									<div align="right">
										相册描述:
									</div>
								</td>
								<td width="66%">
									<s:textarea cols="50" rows="5" name="a_desc" id="a_desc" cssClass="input2" ></s:textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="center">
										<s:submit value="提交" ></s:submit>
										<s:reset value="重置"></s:reset>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
	    </table>
	    </form>
	</body>
</html>