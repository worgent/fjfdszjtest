<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户组管理</title>
<link href="css/admin.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/comm.js"></script>
<script language="JavaScript" type="text/javascript"><!--


//页面加载
$(document).ready(function(){ 
   $("#groupListInfo").show() ;
   $('#groupListInfo').html("<center>页面载入中...</center>"); 
  
  $.get("group.do",{action:'list',date:new Date()},showResult);
  function showResult(res){
	  $('#groupListInfo').html(res); 
  }
});


//打开新建组页面
function newGroup() {
  $("#groupSet").show() ;
  $('#groupSet').html("<center>页面载入中...</center>");
  $.get("group.do",{action:'add',date:new Date()},showResult1);
}

function showResult1(res){
	$('#groupSet').html(res); 
}

//进入用户组修改页面
function editGroup(groupId) {
  $("#groupSet").show();
  $('#groupSet').html("<center>页面载入中...</center>");
 
  var pars = "action=edit&groupId=" + groupId;
  $.get("group.do",pars,function(data){
   	$('#groupSet').html(data); 
  });
}




//修改用户组
function addGroup() {
  var checkBoxs = document.getElementsByName("roleIDs");
  var pars="action=addsave&groupName="+encodeURIComponent($('#groupName').val())+"&groupDesc="+encodeURIComponent($('#groupDesc').val());
  
  for (var i = 0; i < checkBoxs.length; i++) {
    if (checkBoxs[i].checked) {
      pars=pars + "&roleIDs=" + checkBoxs[i].value;
    }
  }
   $.get("group.do",pars,addGroupOK);
}

//添加用户组成功
function addGroupOK(res) {
    $('#messageDiv').hide();
    $('#messageDiv').html(res);
    var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		cloesGroupSetPage();
    	loadPage();
  	}
}

//关闭用户组修改页面
function cloesGroupSetPage() {
  $("#groupSet").html("");
  $('#groupSet').hide();
}

//更新成功后重新加载用户组列表
function loadPage() {
  $('#groupListInfo').html("<center>页面载入中...</center>"); 
  
  $.get("group.do",{action:'list',date:new Date()},showResult);
  function showResult(res){
	  $('#groupListInfo').html(res); 
  }
}

//更新用户组
function editDoGroup() {
  var pars = "action=editsave&groupName="+encodeURIComponent($('#groupName').val())+
  "&groupDesc="+encodeURIComponent($('#groupDesc').val())+"&groupId="+$('#groupId').val();
  var checkBoxs = document.getElementsByName("roleIDs");
  for (var i = 0; i < checkBoxs.length; i++) {
    if (checkBoxs[i].checked) {
      pars=pars + "&roleIDs=" + checkBoxs[i].value;
    }
  }
  
  $.get("group.do",pars,function(res){
   $('#messageDiv').hide();
    $('#messageDiv').html(res);
    var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		$("#groupSet").html("");
  		$('#groupSet').hide();
    	loadPage();
  	}
  });
}


//删除用户组
function delGroup(id) {
  var pars = "action=del&groupId="+id;
  $.get("group.do",pars,deleteResult);
  
   	function deleteResult(res){
   		$('#messageDiv').hide();
    	$('#messageDiv').html(res);
    	var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  		var codeid = jsonMsgObj.getCodeid();
  		alert(jsonMsgObj.getMessage());
  		if (codeid == "0") {
  			cloesGroupSetPage();
    		loadPage();
  		}
  	}
}



//
--></script>
</head>
<body onload="loadPage();">
<p>&nbsp;</p>
<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0" class="table1">
  <tr>
    <td>
      <table width="100%" border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td>
            <strong>用户组管理</strong>
          </td>
        </tr>
      </table>
      <div id="groupListInfo"></div>
      <table width="100%" border="0" cellpadding="5" cellspacing="0">
        <tr>
          <td>
            <div align="center">
              <input type="button" name="newGroup" value="新建用户组" class="button2" onclick="newGroup();"/>
            </div>
          </td>
        </tr>
      </table>
      <div id="groupSet"></div>
    </td>
  </tr>
</table>
<p>&nbsp;</p>
<div id="messageDiv"></div>
</body>
</html>