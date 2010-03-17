<%@page contentType="text/html; charset=gbk"%>
<HTML>
<HEAD>
<script language="javascript">
var flag = false;
function shift_status()
{
	if(flag)
	{
		if(screen.width>1024)
			parent.framsetmain.cols = "138,9,*";
		else if(screen.width>800)	
			parent.framsetmain.cols = "138,9,*";
		else
			parent.framsetmain.cols = "138,9,*";
		document.all.menuSwitchimg.src='images/switchleft.gif';
		document.all.menuSwitchimg.title='Òþ²Ø';
	}
	else
	{
		parent.framsetmain.cols = "0,9,*";
		document.all.menuSwitchimg.src='images/switchright.gif';
		document.all.menuSwitchimg.title='ÏÔÊ¾';
	}
	flag = !flag;
}
</script>
</HEAD>
<BODY>
<table border="0" height="100%" cellspacing="0" cellpadding="0" onclick="shift_status()">
	<tr height="100%" >
	 <td valign="middle" bgcolor="#F5F5F5" id=tdmenuSwitch style="cursor:hand" ><img id=menuSwitchimg src="images/switchleft.gif" height=969 width=9></td>
	</tr>
</table>
</BODY>
</HTML>