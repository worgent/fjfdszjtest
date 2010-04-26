<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
  response.setHeader("Cache-Control", "no-cache");
  response.setHeader("Pragma", "no-cache");
  
%>

<table width="100%" border="1" cellpadding="3" cellspacing="0">
    <tr>
    	<td>评论人</td><td>评论内容</td><td>评论时间</td>
    </tr>
	<s:iterator id="word" value="%{pageList.objectList}">
		<tr>
			<td><s:property value='#word.USERID' /></td>
			<td align="center" id='a'>
				<s:property value='#word.PW_CONTENT' />
			</td>
			<td>
				<s:property value='#word.COMMIT_DATE' />
			</td>
		</tr> 
	</s:iterator>
	
</table>
