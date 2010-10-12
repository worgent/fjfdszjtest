<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:useBean class="HibernateBeans.cms.cmsInfo" scope="page" id="cmsInfo"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.mapName {
	text-align: left;
	font-family: Arial;
	font-size: 15pt;
	vertical-align: middle;
	color: White;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<title>AICMS<%=cmsInfo.getVersion()%>管理</title>
<link href="styles/table.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="100%" height="50" border="0" cellpadding="0" cellspacing="0" bgcolor="#99CCCC">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="98%" align="right">
        <a href="#" target="main">更改资料</a>&nbsp;|&nbsp;<a href="logout$.jsp" target="_top">退出系统</a>
    </td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><span class="mapName">AICMS<%=cmsInfo.getVersion()%>管理系统</span></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right">Welcome <%=(String)session.getAttribute("m_account")%><span id="nt">2008-8-8</span></td>
    <td>&nbsp;</td>
  </tr>
</table>
<SCRIPT language="JavaScript">
function startclock()
{
var thetime=new Date();

var nhours=thetime.getHours();
var nmins=thetime.getMinutes();
var nsecn=thetime.getSeconds();
var nday=thetime.getDay();
var nmonth=thetime.getMonth();
var ntoday=thetime.getDate();
var nyear=thetime.getYear();
var AorP=" ";

if (nhours>=12)
    AorP="P.M.";
else
    AorP="A.M.";

if (nhours>=13)
    nhours-=12;

if (nhours==0)
   nhours=12;

if (nsecn<10)
 nsecn="0"+nsecn;

if (nmins<10)
 nmins="0"+nmins;

if (nday==0)
  nday="Sunday";
if (nday==1)
  nday="Monday";
if (nday==2)
  nday="Tuesday";
if (nday==3)
  nday="Wednesday";
if (nday==4)
  nday="Thursday";
if (nday==5)
  nday="Friday";
if (nday==6)
  nday="Saturday";

nmonth+=1;

if (nyear<=99)
  nyear= "19"+nyear;

if ((nyear>99) && (nyear<2000))
 nyear+=1900;

document.getElementById("nt").innerHTML=nhours+": "+nmins+": "+nsecn+" "+AorP+" "+nday+", "+nmonth+"/"+ntoday+"/"+nyear;
setTimeout('startclock()',1000);
} 
startclock();
</SCRIPT>
</body>
</html>
