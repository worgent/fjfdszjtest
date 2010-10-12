<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:useBean class="HibernateBeans.cms.cmsInfo" scope="page" id="cmsInfo"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>mian space</title>
<link href="styles/table.css" rel="stylesheet" type="text/css" />
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
 
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="10%" height="32">&nbsp;</td>
    <td width="66%">&nbsp;</td>
    <td width="24%" align="right"><iframe src="http://weather.265.com/weather.htm" width="170" height="50" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"></iframe></td>
  </tr>
  <tr>
    <td height="36">&nbsp;</td>
    <td align="left"><%=session.getAttribute("m_account")%>,
<SCRIPT language="JavaScript">
var thetime=new Date();
var str="你好!";
var nhours=thetime.getHours();
//var nmins=thetime.getMinutes();
//var nsecn=thetime.getSeconds();
//var nday=thetime.getDay();
//var nmonth=thetime.getMonth();
//var ntoday=thetime.getDate();
//var nyear=thetime.getYear();
/*
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
*/

if(nhours>=0&&nhours<9)str="早！";
if(nhours>=9&&nhours<=10)str="早上好！";
if(nhours>10&&nhours<=12)str="上午好！";
if(nhours>12&&nhours<=14)str="中午好！";
if(nhours>14&&nhours<19)str="下午好！";
if(nhours>=19&&nhours<=23)str="晚上好！";
document.write(str);
</SCRIPT></td>
    <td rowspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td height="38">&nbsp;</td>
    <td align="center">欢迎回来~！</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center">&nbsp;</td>
  </tr>
</table>
</body>
</html>
