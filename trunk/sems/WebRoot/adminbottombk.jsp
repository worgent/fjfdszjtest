<%@page contentType="text/html; charset=UTF-8"%>
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
<div style="height=32;width:100%;border-right:1px solid buttonhighlight;border-bottom:1px solid buttonhighlight;
border-left:1px solid buttonshadow;border-top:1px solid buttonshadow">
  <table cellpadding=0 cellspacing=4 border=0 width=100% style="background:buttonface;
border-right:1px solid buttonshadow;border-left:1px solid buttonhighlight;
border-bottom:1px solid buttonshadow;border-top:1px solid buttonhighlight" height="32">
    <tr> 
      <td style="BACKGROUND-COLOR:buttonshadow" valign="bottom"> 
        <table border="0" cellspacing="2" cellpadding="0" width="100%" height="100%">
          <tr valign="middle"> 
            <td height="100%" valign="bottom"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr valign="baseline"> 
                  <td nowrap style="border:buttonshadow 1px solid;FONT-SIZE: 11px; FONT-FAMILY: Tahoma,宋体">&nbsp;iOutlook&nbsp;：</td>
                  <td style="FONT-SIZE: 11px; FONT-FAMILY: Tahoma,宋体">&nbsp;</td>
				  <td id="itemnow" nowrap style="border:buttonshadow 1px solid;FONT-SIZE: 11px; FONT-FAMILY: Tahoma,宋体">&nbsp;首页</td>
                </tr>
              </table>
            </td>
            <td height="100%" width="5%" align="center"><img src="spacer.gif" width="1" height="100%" style="BORDER: white 1px solid;"></td>
            <td width="95%" height="100%" valign="bottom">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr valign="baseline"> 
                  <td style="FONT-SIZE: 11px; FONT-FAMILY: Tahoma,宋体">用户名:<s:property value="%{search.CODE}"/>&nbsp;客户名称:<s:property value="%{search.NAME}"/>&nbsp;客户代码:<s:property value="%{search.CLIENTCODE}"/>&nbsp;结算方式:<s:property value="%{search.CLIENTBALANCENAME}"/></td>
                  <td style="FONT-SIZE: 11px; FONT-FAMILY: Tahoma,宋体">&nbsp;</td>
                  <td align="right" id="sttgro" style="border:buttonshadow 1px solid;FONT-SIZE: 11px; FONT-FAMILY: Tahoma,宋体" class="ico" onclick="parent.location='index.jsp';setborder();itemnow.innerText=sttgro.innerText;itemnow.title=sttgro.title" title="退出登录">&nbsp;退出登录&nbsp;</td>
                  </tr>
                </table>
			</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</div>
</body>
</html>