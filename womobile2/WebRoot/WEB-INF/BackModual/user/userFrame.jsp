<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
	<HEAD>
		<TITLE>人员树</TITLE>
		<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
		<script type="text/javascript" src="js/wtree.js"></script>
		<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
	</HEAD>
<BODY bgcolor="white" leftmargin="0" topmargin="0" marginheight="0"
		marginwidth="0"
		onResize="if (navigator.family == 'nn4') window.location.reload()">
		<TABLE cellpadding="0" cellspacing="0" border="0">
			<TR>
				<td>
					<div class="dtree" id="dtree2">
						<s:property value="menuTree" escape="false" />
					</div>
				</td>
			</TR>
		</TABLE>
	<input type="hidden" name="frmid" id="frmid" value=""/>
	<input type="hidden" name="frmname"  id="frmname" value="已经选择">
	

<script type="text/javascript">
	//显示数据
	$(document).ready(function(){
  		for(var n=0; n<funcs.funcs.length;n++){
			d1.co(funcs.funcs[n].menudm).checked=true;
		}
	});
	function procheck(){
		var pars ="";
		var selids=d1.getCheckedNodes();//树的所有选中的节点
		for(var n=0; n<selids.length; n++){
			  	if(selids[n].indexOf("_ex")>0)
			  	    pars=pars+selids[n]+",";
		}
		$("#frmid").val(pars);
	}
</script>
 
</BODY>
