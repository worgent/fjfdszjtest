<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<script type="text/javascript" src="js/wtree.js"></script>
<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/comm.js"></script>

<script language="JavaScript" type="text/javascript">
<!--
	//添加角色
	function editDoRole() {
		  var pars ="action=editsave&roleName="+encodeURIComponent($('#roleName').val())+"&roleId="+$('#roleId').val();
		  var selids=d.getCheckedNodes();//树的所有选中的节点
		  var str="";
		  for(var n=0; n<selids.length; n++){
		  	pars=pars+"&permissions="+selids[n];
		  }
		 $.get("role.do",pars,function(res){
	   		$('#messageDiv').hide();
	    	$('#messageDiv').html(res);
	    	var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
	  		var codeid = jsonMsgObj.getCodeid();
	  		alert(jsonMsgObj.getMessage());
  		 });
	}
	
	$(document).ready(function(){ 
  		for(var n=0; n<funcs.funcs.length;n++){
			d.co(funcs.funcs[n].menudm).checked=true;
		}
	});
	
	function cloesPage() {
  		document.roleForm.action.value = "index";
  		document.roleForm.submit();
	}
-->
</script>
<s:form action="role" name="roleForm">
	<s:hidden name="action"></s:hidden>
	<s:hidden id="roleId" name="roleId"></s:hidden>
	<table width="100%" border="0" cellpadding="5" cellspacing="0"
		class="table6">
		<tr>
			<td colspan="2">
				<b>修改角色</b>
			</td>
		</tr>
		<tr>
			<td width="15%">
				角色名称(中文)
			</td>
			<td width="85%">
				<s:textfield id="roleName" name="roleName" cssClass="input2"
					size="40" onkeypress="return false;"></s:textfield>
			</td>
		</tr>

		<tr>
			<td valign="top">
				权限
			</td>
			<td nowrap="nowrap">
				<div class="dtree" id="dtree3">
					<s:property value="menuTree" escape="false" />
				</div>
			</td>
		</tr>
		<tr>
			<td valign="top">
				&nbsp;
			</td>
			<td>
				<input type="button" name="saveRole" value="保存" class="button1"
					onclick="editDoRole();" />
				<input type="button" name="clostButton" value="返回列表" class="button1"
					onclick="cloesPage();" />
			</td>
		</tr>
	</table>
	<div id="messageDiv"></div>
</s:form>

