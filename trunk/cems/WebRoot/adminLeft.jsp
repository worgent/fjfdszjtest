<%@page contentType="text/html; charset=gbk"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<script language="JavaScript" src="js/outlook.js" type="text/javascript"></script>

</HEAD>
<BODY marginwidth="0" marginheight="0" bgcolor="#D0D0D0" style="background: #FFFFFF right top repeat-y url(SlideMenu/images/frontlogin03a.jpg);">		
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

document.oncontextmenu=new Function("return false");

var OUTLOOKBAR_DEFINITION = {
	format:{
		target:'main',
		blankImage:'images/bex.gif',
		rollback:true,
		animationSteps:3,
		animationDelay:20,
		templates:{
			panel:{
				common:'<table width="130" height="37" border="0" cellspacing="0" cellpadding="0" background="SlideMenu/images/frontlogin02a.jpg" STYLE="background-repeat:  repeat-y;"><tr><td align="center"><div style="font: bold 11pt trebuchet ms, arial;font-size: 9pt">{text}</div></td></tr></table>',
				normal:{state:'n'}
			},
			item:{
				common:'<table width="130" border="0" bgcolor="{backgroundColor}" cellspacing="0" cellpadding="5" background="SlideMenu/images/frontlogin03a.jpg" STYLE="background-repeat:  repeat-y;" ><tr align="center"><td><img src="SlideMenu/Icon/{icon}" width="32" height="32" STYLE="background-repeat:  repeat-y;"/></td></tr><tr align="center"><td><span style="font: 9pt verdana;font-size: 9pt">{text}</span></td></tr></table>',
				normal:{borderColor:'#D0D0D0', backgroundColor:'white', state:'n'}
			},
			upArrow:{
				common:'<img src="images/btn_up_{state}.gif" width="24" height="24" />',
				normal:{state:'n'}
			},
			downArrow:{
				common:'<img src="images/btn_down_{state}.gif" width="24" height="24" />',
				normal:{state:'n'}
			}
		}
	},
	panels:[
		 <s:property value="%{search.menu}" escape="false"/>
	]
};	
new COOLjsOutlookBar(OUTLOOKBAR_DEFINITION);

document.body.style.backgroundImage="url(SlideMenu/images/frontlogin03a.jpg)";

//-->
</script>
					
</BODY>
</HTML>