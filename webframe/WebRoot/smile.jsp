<%@page contentType="text/html; charset=UTF-8"%>
<%
  response.setHeader("Cache-Control", "no-cache");
  response.setHeader("Pragma", "no-cache");
  String inputName = request.getParameter("inputName");
  String path = request.getContextPath();
  if (inputName == null) {
    inputName = "smileInput";
  }
 
%>
<table width="100%" border="0" cellpadding="3" cellspacing="0">
  <tr>
    <td colspan="7">
      <strong>表情图标</strong>
    </td>
    <td>
      <div align="right"><a href="javascript:;" onclick="closeSmilePage();">X</a></div>
    </td>
  </tr>
  <tr>
  <%
    int counter = 0;
    for (int i = 0; i < 85; i++) {
      if (counter == 0) {
        counter = 6;
        out.println("<tr>");
      }
  %>
    <td>
      <div align="center">
        <img id="smile<%=i%>" src="<%=path %>/img/smile/<%=i%>.gif" alt="smile" onmousemove="this.style.cursor='hand';this.style.cursor='pointer'" onclick="insertSmile('<%=inputName%>','{<%=i%>}');"/>
      </div>
    </td>
  <%
    if (counter == 1) {
      counter = 0;
      out.println("</tr>");
    }
    else {
      counter = counter - 1;
    }
    }
  %>
  <tr>
    <td colspan="7">
      <strong></strong>
    </td>
    <td>
      <div align="right"><a href="javascript:;" onclick="closeSmilePage();">X</a></div>
    </td>
  </tr>
</table>
