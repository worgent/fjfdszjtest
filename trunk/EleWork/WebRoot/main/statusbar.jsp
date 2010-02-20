<link rel="stylesheet" href="../css/mm.css" type="text/css">
<%@include file="common.jsp"%>
<%@page contentType="text/html; charset=GB2312"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>状态栏</title>
<script src="../inc/mytable.js"></script>
<script src="../inc/marquee.js"></script>
<script src="../inc/ccorrect_btn.js"></script>
<SCRIPT LANGUAGE="JavaScript">
function killErrors()
{
  return true;
}
window.onerror = killErrors;

var ctroltime;

function MyLoad()
{
  ctroltime=setTimeout("sms_mon()",3000);
}

var xmlHttpObj=getXMLHttpObj();

function sms_mon()
{
  var theURL="sms_mon.do?CHECK_SMS=1";//调用servlet
  xmlHttpObj.open("GET",theURL,true);
  var responseText="0";
  xmlHttpObj.onreadystatechange=function()
  {
    if(xmlHttpObj.readyState==4)
    {
      responseText=xmlHttpObj.responseText;
      if(responseText!="0")
      {
         document.getElementById("new_sms").innerHTML="<a href='#' onclick='javascript:show_sms();' title='点击查看短信'><img src='<%=SystemURL%>images/sms1.gif'border=0 height=10> 短信</a><object id='sms_sound' classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='<%=SystemURL%>inc/swflash.cab' width='0' height='0'><param name='movie' value='<%=SystemURL%>wav/1.swf'><param name=quality value=high><embed id='sms_sound' src='<%=SystemURL%>wav/1.swf' width='0' height='0' quality='autohigh' wmode='opaque' type='application/x-shockwave-flash' plugspace='http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash'></embed></object>";
         show_sms();
      }
      else
      {
      	 document.getElementById("new_sms").innerHTML="";
      }
    }
  }
  xmlHttpObj.send(null)
  ctroltime=setTimeout("sms_mon()",30000);//30s查寻一次.
}

function show_sms()
{
   clearTimeout(ctroltime);
   ctroltime=window.setTimeout('sms_mon()',40000);

   mytop=screen.availHeight-200;
   myleft=0;
   window.open("sms_show.faces","","height=160,width=370,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,top="+mytop+",left="+myleft+",resizable=yes");
}

//打开新窗体
menu_flag=0;
var STATUS_BAR_MENU;

function show_menu()
{
   mytop=screen.availHeight-480;
   myleft=screen.availWidth-260;
   if(menu_flag==0)
       STATUS_BAR_MENU=window.open("","STATUS_BAR_MENUadmin","height=400,width=200,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,top="+mytop+",left="+myleft+",resizable=no");

   STATUS_BAR_MENU.focus();
}

function MyUnload()
{
   if(menu_flag==1)
   {
     STATUS_BAR_MENU.focus();
     STATUS_BAR_MENU.MAIN_CLOSE=1;
     STATUS_BAR_MENU.close();
   }
}
</script>
</head>

<body bgcolor="#78A327"  class="statusbar" topmargin="0" leftmargin="0" marginwidth="0" marginheight="0" onload="MyLoad();" onunload="MyUnload();">


<table border="0" width="100%" cellspacing="1" cellpadding="0" class="small">
  <tr>
    <td align="center" width="90">

    </td>
    <td align="center" width="80">&nbsp;
       <span id="new_sms"></span>
    </td>
    <td align="center" style="FONT-WEIGHT: bold;">
      <script language="JavaScript">
        new marquee('status_text');
        status_text.setDelay(10*1000);
        status_text.init(new Array("Elework 2008","冠发科技","邮政大厦 多经楼"));
      </script>
    </td>

    <td align="center" width="75">&nbsp;
       <a href="javascript:show_menu();">新窗口</a>
    </td>
  </tr>
</table>
<script>
window.setTimeout('this.location.reload();',3600000);
</script>
</body>
</html>
