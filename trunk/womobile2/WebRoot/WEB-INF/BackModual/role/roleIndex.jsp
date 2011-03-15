<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>角色管理</title>
		<link href="css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/comm.js"></script>
		
		<script language="JavaScript" type="text/javascript">
		
<!--
//页面加载
$(document).ready(function(){ 
  
  $.get("role.do",{action:'list',date:new Date()},showResult);
  function showResult(res){
	  $('#roleListInfo').html(res); 
  }

});

//打开新增角色页面
function newRole() {
  $("#roleSet").show() ;
  $('#roleSet').html("<center>页面载入中...</center>");
  $.get("role.do",{action:'add',date:new Date()},showResult1);
}

function showResult1(res){
	$('#roleSet').html(res); 
}

//添加角色
function addRole() {
  //var checkBoxs = document.getElementsByName("permissions");
  //var pars="action=addsave&roleName="+encodeURIComponent($('#roleName').val());
  
  //for (var i = 0; i < checkBoxs.length; i++) {
    //if (checkBoxs[i].checked) {
     // pars=pars + "&permissions=" + checkBoxs[i].value;
    //}
  //}
  
  var selids=d.getCheckedNodes();
		var str="";
		for(var n=0; n<selids.length; n++){
			str+=selids[n]+";";
		}
		alert(str);
   //$.get("role.do",pars,addRoleOK);
}

function addRoleOK(res) {
    $('#messageDiv').hide();
    $('#messageDiv').html(res);
    var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		cloesRoleSetPage();
    	loadPage();
  	}
}

function cloesRoleSetPage() {
  $("#roleSet").html("");
  $('#roleSet').hide();
}

//更新成功后重新加载角色列表
function loadPage() {
  $('#roleListInfo').html("<center>页面载入中...</center>"); 
  
  $.get("role.do",{action:'list',date:new Date()},showResult);
  function showResult(res){
	  $('#roleListInfo').html(res); 
  }
}

//进入修改角色页面
function editRole(roleID) {
  $("#roleSet").show();
  $('#roleSet').html("<center>页面载入中...</center>");
  var pars = "action=edit&roleId=" + roleID;
  $.get("role.do",pars,function(data){
   	$('#roleSet').html(data); 
  });
}

//修改角色
function editDoRole() {
  alert("aa");
  var pars = "action=editsave&roleName="+encodeURIComponent($('#roleName').val())+"&roleId="+$('#roleId').val();
  alert(pars); 
  var checkBoxs = document.getElementsByName("permissions");
  for (var i = 0; i < checkBoxs.length; i++) {
    if (checkBoxs[i].checked) {
      pars=pars + "&permissions=" + checkBoxs[i].value;
    }
  }
  
  $.get("role.do",pars,function(res){
   $('#messageDiv').hide();
    $('#messageDiv').html(res);
    var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		$("#roleSet").html("");
  		$('#roleSet').hide();
    	loadPage();
  	}
  });
}

//删除角色
function delRole(id) {
  var pars = "action=del&roleId="+id;
  $.get("role.do",pars,deleteResult);
  
   	function deleteResult(res){
   		$('#messageDiv').hide();
    	$('#messageDiv').html(res);
    	var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  		var codeid = jsonMsgObj.getCodeid();
  		alert(jsonMsgObj.getMessage());
  		if (codeid == "0") {
  			cloesRoleSetPage();
    		loadPage();
  		}
  	}

}

//-->
</script>
	</head>
	<body>
		<p>
			&nbsp;
		</p>
		<table width="90%" border="0" align="center" cellpadding="10"
			cellspacing="0" class="table1">
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td>
								<strong>角色管理</strong>
							</td>
						</tr>
					</table>
					<div id="roleListInfo"></div>
					<div id="roleSet"></div>
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<div id="messageDiv"></div>
	</body>
</html>