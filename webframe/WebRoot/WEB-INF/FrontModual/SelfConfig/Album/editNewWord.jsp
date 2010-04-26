<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
//定义全局变量
String path = request.getContextPath();
%>
<form action="#">
<s:hidden name="action"></s:hidden>
<s:hidden id="content" name="content"></s:hidden>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="table6">
  <tr>
    <td>
      评论
    </td>
  </tr>
  <tr>
    <td>
      <input type="button" name="Submit" value="保存" class="button1" onclick="saveEditDo('<%=path %>');"/>
      <input type="button" name="ClosePage" value="关闭" class="button1" onclick="closeEditPage();"/>
    </td>
  </tr>
  <tr>
    <td nowrap="nowrap">
      <s:textarea id="newWordContent" name="newWordContent" cols="45" rows="8" cssClass="textarea1"></s:textarea>
      <!--  <a href="javascript:;" onclick="loadSmilePage('newWordContent','<%=path %>');">插入表情</a> -->
    </td> 
  </tr>
  <tr>
    <td>
      <div id="smileDiv"></div>
    </td>
  </tr>
  <tr>
    <td>
      <input type="button" name="Submit" value="保存" class="button1" onclick="saveEditDo('<%=path %>');"/>
      <input type="button" name="ClosePage" value="关闭" class="button1" onclick="closeEditPage();"/>
    </td>
  </tr>
</table>
</form>