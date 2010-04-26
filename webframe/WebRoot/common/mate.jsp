<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%request.setCharacterEncoding("GBK");%> 
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ include file="/common/taglibs.jsp"%>
	<meta name="author" content="@qzgf Group Develop"/>
	<meta name="Copyright" content="冠发开发">
	<meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<meta http-equiv=Content-Language content=zh-cn>
	<!-- 引入jquery的样式,及ui库 -->
	<link type="text/css" href="<%=path%>/css/jquery-ui-1.7.custom.css" rel="stylesheet" />	
	<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.7.custom.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
	<!-- 引入验证js,及验证样式库 -->
	<link type="text/css" href="<%=path%>/css/validator.css" rel="stylesheet" />	
	<script type="text/javascript" src="<%=path%>/js/formValidator.js"></script>
	<script type="text/javascript" src="<%=path%>/js/formValidatorRegex.js"></script>
	<!-- 引入tablesorter -->
	<link type="text/css" href="<%=path%>/css/table/style.css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=path%>/js/jquery.tablesorter.js"></script>
	<!--公共js类库-->
	<script type="text/javascript" src="<%=path%>/js/jutil.js"></script>
	<!--站内消息类-->
	<script type="text/javascript" src="<%=path%>/js/jmessage.js"></script>
	<!--确认js-->
	<script type="text/javascript" src="<%=path%>/js/jquery.alerts.js"></script>
	<link  href="<%=path%>/js/jquery.alerts.css"  rel="stylesheet" type="text/css" media="screen" / >	

	<!-- 地图名片样式文件 -->

	<!--引入校内网的对话框
	<link type="text/css" href="<%=path%>/css/dialogpro.css" rel="stylesheet" />	
	<script type="text/javascript" src="<%=path%>/js/dialogpro.js"></script>	
	-->
	
	
   <!-- google地图引擎 -->
   <script src="http://ditu.google.cn/maps?file=api&amp;v=2&amp;key=ABQIAAAAEpL4bRjvk0L4bBpQx-NEvxQy551G5g2lme6bMIrVZjqptQxySRRW24MCOsw7CU1KA53s1kn5kB8U7A&hl=zh-CN" type="text/javascript"></script>