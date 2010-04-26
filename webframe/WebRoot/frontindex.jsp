<%@page contentType="text/html; charset=UTF-8"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


频道:&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>selfconfig/reward.do?action=index">悬赏揭榜</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>selfconfig/mapcard.do?action=index">地图名片</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>selfconfig/bulletin.do?action=index&search.ptype=1">优惠劵信息</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>selfconfig/bulletin.do?action=index&search.ptype=2">狩猎贴信息</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>selfconfig/bulletin.do?action=index&search.ptype=3">领主招纳</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=basePath%>mapFortune.jsp">地图宝典</a>