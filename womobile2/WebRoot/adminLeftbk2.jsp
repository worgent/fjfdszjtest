<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<HTML>
<style type="text/css">
body{margin-top:0px;} 
html { overflow-x:hidden; overflow-y:hidden; }
</style>
<BODY>
<table border="0" cellpadding="0" style="border-collapse: collapse" height="100%"  id="table1ex" >
									<tr>
										<td  width="140" height="74">
										<img border="0" src="images/menu/frontlogin01a.jpg" width="130" height="74"></td>
									</tr>
									<tr>
										 <td  width="140" id="sbCont" height="100%"  background="images/menu/frontlogin03a.jpg" STYLE="background-repeat:  repeat-y;" valign="top">
									 	 <iframe src="/menu.do" name="menu"  width="130" height="100%" marginheight="0" marginwidth="3" scrolling="no" frameborder="no"  ></iframe>
										</td>
									</tr>
									<tr>
										<td width="140" height="47">
										<img border="0" src="images/menu/frontlogin04a.jpg" width="130" height="47"></td>
									</tr>
</table>	
<!--  -->
<SCRIPT LANGUAGE="JAVASCRIPT">
<!--
//document.all("table1ex").height=parent.document.body.clientHeight-260;
//alert(eval(parent.document.body.clientHeight-260));
//document.getElementById("table1ex").height=parent.document.body.clientHeight-260;
document.getElementById("table1ex").setAttribute("height",parent.document.body.clientHeight-260);

var message="";
function clickIE() {if (document.all) {(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}
document.oncontextmenu=new Function("return false");

//-->
</SCRIPT>
</BODY>
</HTML>