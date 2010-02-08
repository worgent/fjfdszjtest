<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>菜单</title>
		<style type="text/css">
<!--
body {
	background-color: #FAFAFA;
}
-->
</style>
<script language="javascript" type="text/javascript">
function showMemu(i) {
  $("#t" + i).toggle();
}

function over(tid)
{
	tid.style.backgroundColor="#ebeadd";
}

function out(tid)
{
	tid.style.backgroundColor="#ffffff";
}
</script>
	</head>
	<body>
		<table width="160" border="0" cellspacing="2" cellpadding="0"
			class="table2">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="3" cellpadding="0"
						class="table3">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="table4">
									<tr>
										<td class="title" height="21" onClick="showMemu(0)">
											&nbsp;□ 首页
										</td>
									</tr>
								</table>
								
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									style="display: none" id="t0" class="table4">
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url value="/adminIndex.jsp"
												id="adminIndexUrl"></s:url>
											<a href="${adminIndexUrl}" target="webframe_mainFrame">浏览首页</a>
										</td>
									</tr>
									
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
										<td class="title" height="21" onClick="showMemu(1)">
											&nbsp;□ 用户设置
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									style="display: none" id="t1" class="table4">
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="archives/user.do" id="perchangeuser">
											<s:param name="action">perchangeuser</s:param>
											</s:url>
											<a href="${perchangeuser}" target="webframe_mainFrame">修改用户信息</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="archives/user.do" id="perchangepwd">
											<s:param name="action">perchangepwd</s:param>
											</s:url>
											<a href="${perchangepwd}" target="webframe_mainFrame">修改密码</a>
										</td>
									</tr>
								</table>
						   </td>
					  </tr>
					  <tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
										<td class="title" height="21" onClick="showMemu(3)">
											&nbsp;□ 自助管理
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									style="display: none" id="t3" class="table4">
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="net/order.do" id="order">
											</s:url>
											<a href="${order}" target="webframe_mainFrame">网上寄件</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="net/print.do" id="print">
											</s:url>
											<a href="${print}" target="webframe_mainFrame">网上打单</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="net/proLog.do" id="proLog">
											</s:url>
											<a href="${proLog}" target="webframe_mainFrame">处理过程</a>
										</td>
									</tr>
								</table>
						   </td>
					  </tr>
					  <tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="table4">
									<tr>
										<td class="title" height="21" onClick="showMemu(2)">
											&nbsp;□ 用户管理
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									style="display: none" id="t2" class="table4">
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="archives/user.do" id="user">
											</s:url>
											<a href="${user}" target="webframe_mainFrame">用户列表</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
					  <tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="table4">
									<tr>
										<td class="title" height="21" onClick="showMemu(4)">
											&nbsp;□ 基础档案
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									style="display: none" id="t4" class="table4">
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="archives/address.do" id="address">
											</s:url>
											<a href="${address}" target="webframe_mainFrame">取件地址</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="archives/clientMsg.do" id="clientMsg">
											</s:url>
											<a href="${clientMsg}" target="webframe_mainFrame">取件联系人</a>
										</td>
									</tr>
									<tr>
										<td class="item" height="21" align="center"
											onMouseOver="over(this);" onMouseOut="out(this)">
											<s:url action="archives/serTime.do" id="serTime">
											</s:url>
											<a href="${serTime}" target="webframe_mainFrame">服务时间</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>														
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
										<td class="title" height="21" onClick="showMemu(4)">
											&nbsp;□ 退出管理
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
