<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta name="author" content="@Trust Group Develop"/>
	<meta name="Copyright" content="依赖团开发">
	<meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<meta http-equiv=Content-Language content=zh-cn>
	<link href="/images/login/css.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="/ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="/ext/ext-all.js"></script>
	<script type="text/javascript" src="/ext/examples/examples.js"></script>
	<script type="text/javascript" src="/js/prototype.js"></script>
</head>
	<body>
		<form name="admininfo" method="post" action="/system/login.shtml" class="formcheck"  onsubmit="return checkSubmit();">
		<table cellspacing=0 cellpadding=0 width=1004 border=0>
		  <tbody>
		  <tr>
		    <td colspan=6><img height=92 alt="" src="/images/login/crm_1.gif" 
		    width=345></td>
		    <td colspan=4><img height=92 alt="" src="/images/login/crm_2.gif" 
		    width=452></td>
		    <td><img height=92 alt="" src="/images/login/crm_3.gif" width=207></td></tr>
		  <tr>
		    <td colspan=6><img height=98 alt="" src="/images/login/crm_4.gif" 
		    width=345></td>
		    <td colspan=4><img height=98 alt="" src="/images/login/crm_5.gif" 
		    width=452></td>
		    <td><img height=98 alt="" src="/images/login/crm_6.gif" width=207></td></tr>
		  <tr>
		    <td rowspan=5><img height=370 alt="" src="/images/login/crm_7.gif" 
		    width=59></td>
		    <td colspan=5><img height=80 alt="" src="/images/login/crm_8.gif" 
		    width=286></td>
		    <td colspan=4><img height=80 alt="" src="/images/login/crm_9.gif" 
		    width=452></td>
		    <td><img height=80 alt="" src="/images/login/crm_10.gif" width=207></td></tr>
		  <tr>
		    <td><img height=110 alt="" src="/images/login/crm_11.gif" width=127></td>
		    <td background=/images/login/crm_12.gif colspan=6>
		      <table id=table1 cellspacing=0 cellpadding=0 width="98%" border=0>
		        <tbody>
		        <tr>
		          <td>
		            <table id=table2 cellspacing=1 cellpadding=0 width="100%" 
		              border=0><tbody>
		              <tr>
		                <td align=middle width=81><font color=#ffffff>username：</font></td>
		                <td><input class="wenbenkuang check" verify="string" required="true" shade="true" requiredColor="#ffffff" name="staffNo" type="text" id="staffNo" size="20" value="admini"></td></tr>
		              <tr>
		                <td align=middle width=81><font color=#ffffff>password：</font></td>
		                <td><input class="wenbenkuang check" verify="string" required="true" shade="true" requiredColor="#ffffff" name="password" type="password" id="password" size="20" value="123456"></td></tr>
		              </tbody></table></td></tr></tbody></table></td>
		    <td colspan=2 rowspan=2><img height=158 alt="" 
		      src="/images/login/crm_13.gif" width=295></td>
		    <td rowspan=2><img height=158 alt="" src="/images/login/crm_14.gif" 
		      width=207></td></tr>
		  <tr>
		    <td rowspan=3><img height=180 alt="" src="/images/login/crm_15.gif" 
		      width=127></td>
		    <td rowspan=3><img height=180 alt="" src="/images/login/crm_16.gif" 
		    width=24></td>
		    <td><input name="imageField" value="管理登陆"   type=image height=48 alt="" width=86 
		      src="/images/login/crm_17.gif" ></td>
		    <td><img height=48 alt="" src="/images/login/crm_18.gif" width=21></td>
		    <td colspan=2><a href="javascript:window.close()"><img
		      height=48 alt="" src="/images/login/crm_19.gif" width=84 border=0></a></td>
		    <td><img height=48 alt="" src="/images/login/crm_20.gif" width=101></td></tr>
		  <tr>
		    <td colspan=5 rowspan=2><img height=132 alt="" src="/images/login/crm_21.gif" width=292></td>
		    <td rowspan=2><img height=132 alt="" src="/images/login/crm_22.gif" 
		      width=170></td>
		    <td colspan=2><img height=75 alt="" src="/images/login/crm_23.gif" 
		    width=332></td></tr>
		  <tr>
		    <td colspan=2><img height=57 alt="" src="/images/login/crm_24.gif" 
		    width=332></td></tr>
		  <tr>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=59></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=127></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=24></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=86></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=21></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=28></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=56></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=101></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=170></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=125></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" 
		  width=207>
		  <div align="center"></div></td></tr></tbody></table>
		</form>
		<c:if test="${param.error != null}">
			<script language="javascript">
				 Ext.MessageBox.alert('提示', '工号或密码不正确，请重新输入!', clear_input);
				 function clear_input(){
				 	$('staffNo').value = '';
				 	$('password').value= '';
				 }
			</script>
		</c:if>
	</body>
</html>
