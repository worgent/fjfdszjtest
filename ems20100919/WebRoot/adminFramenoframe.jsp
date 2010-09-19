<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>福建省183速递官方网站</title>
<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<LINK href="SlideMenu/images/SlideMenu.css" type="text/css" rel="stylesheet"/>
<script language="JavaScript" src="SlideMenu/images/menu.js" type="text/javascript"></script>
</head>

<body background="images/bg.jpg">

<div align="center">
	<table border="4" cellpadding="0" class="frntable" style="border-collapse: collapse" width="969" id="table3" height="648" bordercolor="#FFFFFF" cellspacing="0">
		<tr class="frntr">
			<td valign="top" class="frntd">
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table4" class="frntable">
				<tr class="frntr">
					<td class="frntd">
					<img border="0" src="images/top1.jpg" width="518" height="76"><img border="0" src="images/top2.jpg" width="443" height="76"></td>
					<td>　</td>
				</tr>
			</table>
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table6" height="100%" class="frntable">
				<tr class="frntr">
					<td valign="top" bgcolor="#FFFFFF" class="frntd">
					<div align="center">
						<table border="0" cellpadding="0" style="border-collapse: collapse" width="961" height="31" id="table11" class="frntable" background="images/back01.jpg" >
							<tr class="frntr" background="images/back01.jpg" >
								<td  class="frntd"><font color="#FFFFFF">
								<span style="font-size: 9pt">用户名:<s:property value="%{search.CODE}"/>&nbsp;&nbsp; 
												客户名称:<s:property value="%{search.NAME}"/>&nbsp;&nbsp; 
												客户代码:<s:property value="%{search.CLIENTCODE}"/>&nbsp;&nbsp; 
												结算方式:<s:property value="%{search.CLIENTBALANCENAME}"/>&nbsp;&nbsp; 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								[退出登录]</span></font></td>
								<td class="frntd" style="font-size: 9pt;FONT-SIZE: 9pt; FONT-FAMILY: Tahoma,宋体;cursor:hand;"  onclick="parent.location='index.jsp';setborder();itemnow.innerText=sttgro.innerText;itemnow.title=sttgro.title" >
												<font color="#FFFFFF"> 
												[退出登录]
												</font>
								</td>
							</tr>
						</table>
						<table class="frntable">
						<tr class="frntr">
								<td valign="top" width="147" class="frntd">
								<table 	class="frntable" border="0" cellpadding="0" style="border-collapse: collapse"   id="table13">
											<tr class="frntr">
												<td height="74" class="frntd">
												<img border="0" src="images/frontlogin01a.jpg" width="130" height="74"></td>
											</tr>
											<tr class="frntr">
												<td id="sbCont" class="frntd"  STYLE="background-repeat:  repeat-y;width:130;" valign="top">
												
												<div class="SMMenu" state="0" style="width: 130px"> <div class="SMParent" onmouseover="Init(this);" style="width: 130px">自助管理</div><div class="SMChildrenBox"> <div class="subMenu" state="0"><span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000"> <br /><img class="SMChildImage"  src="SlideMenu/Icon/user1_mobilephone.png" /><br /><a ID="HyperLink1"  onclick="javascript:contentMsg('<%=path%>','/net/order')"  target="main">网上寄件</a> </span>  <br /><span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000"> <br /><img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br /><a ID="HyperLink1"  onclick="javascript:contentMsg('<%=path%>','/net/print.do')"  target="main">网上打单</a> </span>  <br /><span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000"> <br /><img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br /><a ID="HyperLink1"  href="net/printConfig.do"  target="main">打印配置</a> </span>  <br /></div></div></div><div class="SMMenu" state="0" style="width: 130px"> <div class="SMParent" onmouseover="Init(this);" style="width: 130px">用户设置</div><div class="SMChildrenBox"> <div class="subMenu" state="0"><span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000"> <br /><img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br /><a ID="HyperLink1"  onclick="javascript:contentMsg('<%=path%>','archives/user.do?action=perchangeuser')"     target="main">修改用户信息</a> </span>  <br /><span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000"> <br /><img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br /><a ID="HyperLink1"  href="archives/user.do?action=perchangepwd"  target="main">修改密码</a> </span>  <br /></div></div></div><div class="SMMenu" state="0" style="width: 130px"> <div class="SMParent" onmouseover="Init(this);" style="width: 130px">用户管理</div><div class="SMChildrenBox"> <div class="subMenu" state="0"><span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000"> <br /><img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br /><a ID="HyperLink1"  href="archives/user.do"  target="main">用户列表</a> </span>  <br /></div></div></div><div class="SMMenu" state="0" style="width: 130px"> <div class="SMParent" onmouseover="Init(this);" style="width: 130px">基础档案</div><div class="SMChildrenBox"> <div class="subMenu" state="0"><span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000"> <br /><img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br /><a ID="HyperLink1"  href="archives/address.do"  target="main">取件地址</a> </span>  <br /><span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000"> <br /><img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br /><a ID="HyperLink1"  href="archives/clientMsg.do"  target="main">取件联系人</a> </span>  <br /><span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000"> <br /><img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br /><a ID="HyperLink1"  href="archives/serTime.do"  target="main">服务时间</a> </span>  <br /></div></div></div>
												
												<!-- 
												 <s:property value="%{search.menu}" escape="false"/>
												  -->
												</td>
											</tr>
											<tr class="frntr">
												<td width="221" height="47" class="frntd">
												<img border="0" src="images/frontlogin04a.jpg" width="130" height="47"></td>
											</tr>
											<tr class="frntr">
												<td class="frntd">　</td>
											</tr>
											<tr class="frntr">
												<td height="17" class="frntd"></td>
											</tr>
								</table>
								</td>
								<td valign="top" class="frntd" width="100%"  colspan="3">
										<iframe name="content" id="content" src="adminIndexmain.jsp" height="500" width="100%" frameborder=auto></iframe>
								</td>
				</tr>
				</table>
				</div>
			
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table10" background="images/di.jpg" height="27" class="frntable">
				<tr class="frntr">
					<td valign="middle" class="frntd"><span style="font-size: 4pt"><br>
					</span><span style="font-size: 9pt">&nbsp;<font color="#FFFFFF">All 
					rights reserved. 2009&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					技术支持 by </font><font color="#02687F">泉州市冠发信息科技有限公司</font></span></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
</div>

<script language="javascript" type="text/javascript">	
//contentMsg('<%=path%>','/adminIndex');
contentMsg('<%=path%>','/net/order');
document.body.style.backgroundImage="url(images/bg.jpg)";
function contentMsg(path,path2){
            /*
            <div id="content"></div>
		  	$('#content').html("<center>页面载入中...</center>"); 
			var urls1 = getActionMappingURL(path2,path);
		  	$.get(urls1,{date:new Date()},showResult);
		  	function showResult(res){
			  $('#content').html(res); 
		  	}
		  	*/
		  	
		  	var urls1 = getActionMappingURL(path2,path);
		  	document.getElementById('content').src=urls1;
		  	
		}
</script>
</body>

</html>
