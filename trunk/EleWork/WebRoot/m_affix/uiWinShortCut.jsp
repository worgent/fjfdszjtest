<!--
main/main.jsp
功能:主页面
作者:qsy
生成日期:2005-04-28
修改日期:2005-04-28
-->

<script>
//-------- 打开windows程序 -------
function dowinOpen(myArray)
{
	exec(myArray[3]);
}

function exec (command) {
window.oldOnError = window.onerror;
window._command = command;
window.onerror = function (err) {
if (err.indexOf('utomation') != -1) {
alert('命令' + window._command + ' 已经被用户禁止！'); 
return true;
}
else return false;
};
var wsh = new ActiveXObject('WScript.Shell');
if (wsh)
wsh.Run(command);
window.onerror = window.oldOnError;
}
</script>
<%@include file="../main/common.jsp"%>
<%@page contentType="text/html; charset=GB2312" import="YzSystem.J_Affix.*,YzSystem.JMain.*" errorPage="../main/error.faces"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%FrmFunctionBase frmFunctionBase = (FrmWinShortCut) UtilWebTools.getValueBinding("frmWinShortCut");%>
<%@include file="../main/functionBase.jsp"%>