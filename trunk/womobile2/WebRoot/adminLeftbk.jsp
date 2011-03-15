<%@page contentType="text/html; charset=UTF-8"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<SCRIPT LANGUAGE="JAVASCRIPT">
<!--

	OB_Top=0;						//position from top (px)
	OB_Left=5;						//position from left (px)
	OB_Margin=10;						//top and bottom margins between icons and borders
	OB_Width=110;						//bar width
	OB_SlideSpeed=4;					//speed during slide movement
	OB_ItemsSpacing=18;					//space between two items (icons and text)
	OB_BorderWidth=1;					//border width
	OB_IconsWidth=34;					//icons width
	OB_IconsHeight=34;					//icons height
	OB_ButtonFontColor="black";				//font color   black
	OB_ButtonHeight=22;					//button height
	OB_LabelFontColor="black";				//font color   
	OB_LabelMargin=10;					//margin between labels and icons
	OB_ArrowWidth=15;					//arrow width
	OB_ArrowHeight=15;					//arrow height
	OB_ArrowSlideSpeed=15;					//speed for items scrolling

if (document.all){
document.write('<script src="/js/outlook/folders.js"><\/script>')
}

function show(page){
parent.webframe_mainFrame.location="main.htm";
parent.webframe_mainFrame.content.location="page+'.htm'";
//parent.main.content.location=page;
}
//-->
</SCRIPT>
<style type="text/css">
<!--
.folder {
	BEHAVIOR: url(folder.htc)}
-->
</style>
</HEAD>
<BODY style="cursor:default;BACKGROUND-COLOR:buttonface" text=#000000 leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onResize="location.reload();">
<!-- 
<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" height="100%" id="table12">
							<tr>
								<td valign="top" width="221">
								<table border="0" cellpadding="0" style="border-collapse: collapse" width="22%" height="96%" id="table13">
									<tr>
										<td height="84">
										<img border="0" src="images/frontlogin01.jpg" width="221" height="84"></td>
									</tr>
									<tr>
										<td background="images/frontlogin02.jpg" width="221" height="30">
										<span style="font-size: 5pt"><br>
										</span><span style="font-size: 9pt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
										自助管理</span></td>
									</tr>
									<tr>
										<td background="images/frontlogin03.jpg">　
										<div id="Layer1" style="position:absolute; left:89px; top:100px; width:2px; height:50%; z-index:1;BACKGROUND-COLOR:buttonhighlight"></div>
									　</td>
									</tr>
</table>
 -->								
<SCRIPT LANGUAGE="JAVASCRIPT">
<!--
var message="";
function clickIE() {if (document.all) {(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")
//-->
</SCRIPT>
<script src="/js/outlook/outbar.js"></script>
</BODY>
</HTML>