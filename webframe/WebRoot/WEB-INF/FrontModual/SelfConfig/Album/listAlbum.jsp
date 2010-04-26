<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@page import="com.qzgf.utils.comm.*"%>
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
		<table width="780" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="2">
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
			<tr>
				<td class="bgColor3" >
				    我的相册(共有<FONT color="red"><s:property id="a" value="%{pageList.pages.totalNum}"/></FONT>个相册)
				    
				</td>
				<td class="bgColor3" align="right">
					<s:url action="album?action=toAddAlbum" id="toAddAlbum" namespace="/"></s:url>
					<strong><s:a href="${toAddAlbum}">新建相册</s:a></strong>
				</td>
			</tr>
			<tr>
			<td colspan="3">
			<table align="center" border="0" width="100%">
				<tr >
					<s:iterator id="album" value="%{pageList.objectList}" >
						<td align="center">
						    <s:url action="album?action=viewPhotos" id="viewPhotos" namespace="/">
						    	<s:param name="a_Id" value="#album.A_ID"></s:param>
						    	<s:param name="a_name" value="#album.A_NAME"></s:param>
						    </s:url>
						    <s:url action="album?action=toEditAlbum" id="toEditAlbum" namespace="/">
						    	<s:param name="a_Id" value="#album.A_ID"></s:param>
						    </s:url> 
						    <a href="${viewPhotos}">
						    <s:if test="#album.P_COVER==0">
						    <img src="<%=path %>/none.gif" alt='<s:property value="#album.A_DESC" />' width="140" height="105" border="0">
						 	</s:if>
						 	<s:if test="#album.P_COVER==1">
						    <img src="<%=WebFrameUtil.getUserWebPath("1")%><s:property value='#album.P_SMALL_PIC' />" alt='<s:property value="#album.A_DESC" />' width="140" height="105" border="0">
						 	</s:if>
						    </a><br>
							<a href="${viewPhotos}"><s:property value="#album.A_NAME" /></a><br> 
							<a href="${toEditAlbum}">修改</a>&nbsp;&nbsp;<a href="#">删除</a>
						</td>
					</s:iterator>
				</tr>
			</table>
			<table align="center" border="0" width="100%">
				<tr>
					<td	width="100%">
						页数:<qzgf:pages value="%{pageList.pages}" />
					</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
	</body>
</html>