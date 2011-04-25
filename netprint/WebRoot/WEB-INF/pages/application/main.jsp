<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<%@ include file="/common/mate.jsp"%>
		<link rel="stylesheet" type="text/css" href="/themes/default/main/menuPaenl.css" />
		<link rel="stylesheet" type="text/css" href="/themes/default/main/style.css" />
		<style type="text/css">
			a{ text-decoration:none; color:#000033 }
			.link{
			font-weight:bold;
			padding:6px 0 0 -2px;
			line-height:25px;
			}
			.myMsg{ background:#FEEDFE; margin:4px 0; color:#FF00FF; font:12px "宋体" }
			.yourMsg{ background:#E1E1E1; margin:4px 0; color:blue; font:12px "宋体" }
			.user{ background:url("/images/user.gif") no-repeat 1px 2px;}
			.im{ background:url("/images/im.jpg") no-repeat 1px 2px; }
			.key{ background:url("/images/key.gif") no-repeat 1px 2px;  }
			.key,.user{
				background-color:#FFFFFF;
				padding-left:20px;
				font-weight:bold;
				color:#000033;
			}
			#logo{
				background-image: url(images/header_left.jpg);
				margin: 0px;
				height: 65px;
				width: 157px;
				border-top-width: 0px;
				border-right-width: 0px;
				border-bottom-width: 0px;
				border-left-width: 0px;
			}
		</style>
		<script>
			var currentUser = '<ww:property value="#session['UserInfo'].staffName"/>';
			var left_margin = '<ww:property value="#session['left_margin']"/>';
			var top_margin = '<ww:property value="#session['top_margin']"/>';
			var left_marginex = '<ww:property value="#session['left_marginex']"/>';
			var top_marginex = '<ww:property value="#session['top_marginex']"/>';			
			var send_code = '<ww:property value="#session['send_code']"/>';
			var sendname = '<ww:property value="#session['sendname']"/>';
		</script>
	</head>
	<body>
		<div id="loading-mask" ></div>
		<div style="width:100%">
		<div id="header" style="background:#deecfd;">
			<img src="/images/header/headerBg.jpg"><img src="/images/header/banner.jpg" style="background:url(/images/header/banner.jpg) repeat-x 0 0;position:absolute;left:442px;" >
		</div>
		<!-- 脚本 -->
		<script type="text/javascript" src="/js/main/onlineChat.js"></script>
		<script type="text/javascript" src="/js/main/manage.js"></script>
		<script type="text/javascript" src="/js/main/menu.js"></script>
		<script type="text/javascript" src="/js/main/toolsBar.js"></script>
		<script type="text/javascript" src="/js/main/mainPanel.js"></script>
		<!-- 脚本-->
		<select id="skins" onchange="changeSkin(this.value)" style="display:none">
			<option value="ext-all">默认风格</option>
			<option value="xtheme-gray">银白风格</option>
			<option value="xtheme-purple">紫色风格</option>
			<option value="xtheme-olive">绿色风格</option>
			<option value="xtheme-darkgray">灰色风格</option>
			<option value="xtheme-black">黑色风格</option>
			<option value="xtheme-slate">深蓝风格</option>
		</select>
		<div id="msgPanel" style="position:absolute;left:0;top:0; "/>
	</body>
</html>
