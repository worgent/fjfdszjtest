<%@page contentType="text/html; charset=UTF-8"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


频道:&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>hireGuide.do">雇请向导</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>guideCoup.do">向导锦囊</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>guideToff.do">向导名人榜</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>guideDynamic.do">周边向导动态</a>
