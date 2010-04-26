<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户选择向导并进入向导空间</title>
</head>
<body>
   <center>
	<table id="myTable" width="90%" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" >
			<thead>
				<tr>
					<th>
					向 导 
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="user" value="%{pageUserList.objectList}">
					<tr>
						<td  class="bgColor4">
					       <a href="/adverRec.do?action=guideSpace&search.username=<s:property value="#user.USERNAME"/>">
					        <s:property value="#user.USERNAME"/> 
						</a>
						</td>
					</tr>
				</s:iterator>
				    <tr class="bgColor3">
				        <td colspan="29">
					     分页:
					    <qzgf:pages value="%{pageUserList.pages}" />
			        	</td>
			         </tr>
				   <tr>
				
				
				   <tr>
						<td  class="bgColor4">
					        备注:选择某个向导进入向导空间
						</td>
					</tr>
			</tbody>
		</table>
		<center>
</body>
</html>