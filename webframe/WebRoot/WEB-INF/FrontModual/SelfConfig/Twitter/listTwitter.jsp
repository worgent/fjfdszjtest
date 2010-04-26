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
	</head>
	<body>
		<table width="80%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
			<tr>
				<td class="bgColor3" align="right">
					<s:url action="twitter?action=toAdd" id="toAddId">
					</s:url>
					<strong><a href="${toAddId}" >写新日志</a></strong>
				</td>
			</tr>
		</table>
		<table id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="80%">

			<tbody>
				<tr>
					<td colspan="2" align="center" width="100%">
						<font color="red"><s:actionmessage theme="webframe0" />
						</font>
						<font color="red"><s:actionerror theme="webframe0" />
						</font>
					</td>
				</tr>
				<s:iterator id="twitter" value="%{pageList.objectList}">
					<tr>
						<td width="30%" class="bgColor3">
							<s:url action="twitter?action=view" id="view">
								<s:param name="twitterId" value="#twitter.TWITTERID" />
							</s:url>
							<s:url action="twitter?action=view" id="views">
								<s:param name="twitterId" value="#twitter.TWITTERID" />
							</s:url>
							<span class="font1"> <a href="${view}"><s:property
										value="#twitter.TWITTERTITLE" />
							</a> </span>
							<br>
							<span class="font1"><s:property
									value="#twitter.TWITTERSENDTIME" />(分类:<a href="#"><s:property
									value="#twitter.TWITTERTYPENAME" /></a>)</span>
						</td>
						<td width="70%" class="bgColor3" align="right">
							<s:url action="twitter?action=toEdit" id="toEditId">
								<s:param name="twitterId" value="#twitter.TWITTERID" />
							</s:url>
							<s:url action="twitter?action=del" id="delId">
								<s:param name="twitterId" value="#twitter.TWITTERID" />
								<s:param name="twitterTypeId" value="#twitter.TWITTERTYPEID" />
							</s:url>
							<a href="${toEditId}">编辑</a>&nbsp;&nbsp;
							<a href="${delId }">删除</a>
							
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="2" class="bgColor4">
							<span class="font1"><s:property
									value="#twitter.TWITTERCONTENT" />
							</span>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="4">
					分页:
					<qzgf:pages value="%{pageList.pages}" />
				</td>
			</tr>
		</table>
		<!--  
		<table>
			<tr>
				<td><qzgf:userinfoinpost idValue="2009100900000141" styleClass="pic1"/></td>
			</tr>
		</table>
		-->
	</body>
</html>