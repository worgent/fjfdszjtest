<%@include file="common.jsp"%>
<%@page contentType="text/html; charset=GB2312"%>
<style type="text/css">.cacher {behavior:url(#default#userdata);}</style>
<HTML>
<HEAD>
<script language="javascript">
var flag = false;
function shift_status()
{
	if(flag)
	{
		if(screen.width>1024)
			parent.framsetmain.cols = "150,9,*";
		else if(screen.width>800)	
			parent.framsetmain.cols = "150,9,*";
		else
			parent.framsetmain.cols = "150,9,*";
		document.all.menuSwitchimg.src='<%=SystemURL%>images/switchleft.gif';
		document.all.menuSwitchimg.title='Òþ²Ø';
	}
	else
	{
		parent.framsetmain.cols = "0,9,*";
		document.all.menuSwitchimg.src='<%=SystemURL%>images/switchright.gif';
		document.all.menuSwitchimg.title='ÏÔÊ¾';
	}

	flag = !flag;
}
</script>
</HEAD>
<BODY onclick="shift_status()" leftmargin=0 topmargin=0 ">
<span id="span1"></span>
<table border=0 height="100%" cellspacing="0" cellpadding="0">
	<tr height="100%">
	 <td valign="middle" bgcolor="#F5F5F5" id=tdmenuSwitch style="cursor:hand"><img id=menuSwitchimg src="<%=SystemURL%>images/switchleft.gif"  width=9></td>
	</tr>
</table>
</BODY>
</HTML>