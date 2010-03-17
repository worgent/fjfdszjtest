<%@page contentType="text/html; charset=gbk"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
<!--
.ico {
	BEHAVIOR: url(menubar.htc)
}
-->
</style>
</head>
<body >

<script  LANGUAGE="JAVASCRIPT">
//document.body.style.backgroundImage="url(images/bg.jpg)";
document.body.style.background="white";


function setborder() {
		parent.main.outborder.style.borderRight="1 solid buttonhighlight";
		parent.main.outborder.style.borderBottom="1 solid buttonhighlight";
		parent.main.outborder.style.borderLeft="1 solid buttonshadow";
		parent.main.outborder.style.borderTop="1 solid buttonshadow";
		parent.main.innerborder.style.borderRight="1 solid buttonshadow";
}

function disableselect(e){
return false
}
var message="";
function clickIE() {if (document.all) {(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")
function reEnable(){
return true
}
//if IE4+
document.onselectstart=new Function ("return false")

</script>
<div  align="center">
<table border="4" cellpadding="0" style="border-collapse: collapse" width="969" id="table3"  bordercolor="#FFFFFF" cellspacing="0">
	<tr>
	<td valign="top">
	<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table4">
				<tr>
					<td>
					<img border="0" src="images/top1.jpg" width="518" height="76"><img border="0" src="images/top2.jpg" width="443" height="76"></td>
					<td>　</td>
					<td>　</td>
				</tr>
	</table>
					<table border="0" cellpadding="0" style="border-collapse: collapse" width="961" height="25" id="table11" background="images/back01.jpg">
					<tr>
												 <td width="8%" id="itemnow" nowrap>
												 <font color="#FFFFFF"><span style="font-size: 9pt">
												 &nbsp;首页&nbsp;&nbsp; </span>
											    </font>
											    </td>
											    <td height="100%" width="5%" align="center">
											    <img src="spacer.gif" width="1" height="100%" style="BORDER: white 1px solid;">
											    </td>
											    <td style="font-size: 9pt" align="left"><font color="#FFFFFF"> 
											    <span style="font-size: 9pt">
												用户名:<s:property value="%{search.CODE}"/>&nbsp;&nbsp; 
												客户名称:<s:property value="%{search.NAME}"/>&nbsp;&nbsp; 
												客户代码:<s:property value="%{search.CLIENTCODE}"/>&nbsp;&nbsp; 
												结算方式:<s:property value="%{search.CLIENTBALANCENAME}"/>&nbsp;&nbsp; 
												</span>
												</font>
												</td>
												<td style="font-size: 9pt;FONT-SIZE: 9pt; FONT-FAMILY: Tahoma,宋体;cursor:hand;"  onclick="parent.location='index.jsp';setborder();itemnow.innerText=sttgro.innerText;itemnow.title=sttgro.title" >
												<font color="#FFFFFF"> 
												[退出登录]
												</font>
												</td>
					</tr>
					</table>

</td>
</tr>
</table>
</div>
</body>
</html>