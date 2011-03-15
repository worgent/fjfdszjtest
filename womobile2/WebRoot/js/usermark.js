//加载用户列表
function loadUserMarkListPage(path,branchId){
  $('#userMarkListDiv').html("<center>页面载入中...</center>"); 
  var urls = getActionMappingURL("/user",path);
  var pars = "action=list&branchId="+branchId+"&date="+new Date();
  $.get(urls,pars,showResult);
  function showResult(res){
  	$('#userMarkListDiv').html(res); 
  }
}

//编辑保存考勤设置
function userCheckWork(path,id1,page1,branchId){
	var interval=$('#interval').val();
	if(interval==null||interval==''){
		alert('需输入时间间隔！');
		return false;
	}

	var startTime=$('#startTime').val();
	
	if(startTime==null||startTime==''){
		alert('需选择有效开始时间！');
		return false;
	}
	
	var endTime=$('#endTime').val();
	if(endTime==null||endTime==''){
		alert('需选择有效结束时间！');
		return false;
	}
	
        var start = startTime.split(" ");
        var end = endTime.split(" ");
     // 判断
        if( parseInt(start[0]) > parseInt( end[0]) ){
            alert("有效开始时间不能大于有效结束时间！") ;
            return false ;
        } else if( parseInt(start[1]) > parseInt( end[1]) ) {
             alert("有效开始时间不能大于有效结束时间！") ;
            return false ;
        } else if( parseInt(start[2]) > parseInt( end[2]) ) {
             alert("有效开始时间不能大于有效结束时间！") ;
            return false ;
        }
	
	
  var checkBoxs = document.getElementsByName("uploadIDs");
  var uploadId='';
  for (var i = 0; i < checkBoxs.length; i++) {
    if (checkBoxs[i].checked) {
      if(uploadId==""||uploadId==null){
      	uploadId=checkBoxs[i].value;
      }else{
      	uploadId=uploadId+','+checkBoxs[i].value;
      }
    }
  }
	var pars='action=saveCheckWork&search.id='+id1+'&date='+new Date()+"&search.interval="+interval
  +"&search.startTime="+$('#startTime').val()+"&search.endTime="+endTime+"&search.uploadIDs="+uploadId+"&search.branchId="+branchId;
  
  var url = getActionMappingURL("/user",path);
  	$.get(url,pars,
  	function(res){
    	$('#messageDiv').hide();
    	$('#messageDiv').html(res);
  		var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  		var codeid = jsonMsgObj.getCodeid();
  		alert(jsonMsgObj.getMessage());
  		if (codeid == "0") {
  			closeEditUser();
  			loadUserList(path,page1,branchId);
  		}
	});
}


//用户分页列表
function loadUserList(path,page1,branchId) {
  $('#userMarkListDiv').html("<center>页面载入中...</center>"); 

  var urls = getActionMappingURL("/user",path);
  var pars="action=list&branchId="+branchId+"&date="+new Date();
  $.get(urls,pars,showResult);
  function showResult(res){
	  $('#userMarkListDiv').html(res); 
  }
}

//加载用户新增页面
function loadUserAddPage(path,branchId) {
  $("#addEditUserDiv").show() ;
  $('#addEditUserDiv').html("<center>页面载入中...</center>"); 
  var pars = "action=add&branchId="+branchId+"&date="+new Date();
  var urls = getActionMappingURL("/user",path);
  $.get(urls,pars,showResult);
  function showResult(res){
	  $('#addEditUserDiv').html(res); 
  }
}

//编辑用户
function loadUserEditPage(id1,pageNum,path,branchId) {
  $("#addEditUserDiv").show() ;
  $('#addEditUserDiv').html("<center>页面载入中...</center>"); 
  
  var urls = getActionMappingURL("/user",path);
  var pars="action=edit&id="+id1+"&branchId="+branchId+"&page="+pageNum;
  $.get(urls,pars,showResult);
  function showResult(res){
	  $('#addEditUserDiv').html(res); 
  }
}

//编辑考勤设置
function loadCheckWorkEditPage(id1,pageNum,path,branchId) {
  $("#addEditUserDiv").show() ;
  $('#addEditUserDiv').html("<center>页面载入中...</center>"); 
  var urls = getActionMappingURL("/user",path);
  var pars="action=editCheckWork&id="+id1+"&branchId="+branchId+"&page="+pageNum;
  $.get(urls,pars,showResult);
  function showResult(res){
	  $('#addEditUserDiv').html(res); 
  }
}
 
 
//编辑保存用户
function userEdit(path,id1,page1,branchId1){
	//判断用户名是否为空
	var branchId=$('#branchId2').val();
	if(branchId==null||branchId==''){
		alert('没有组织机构！');
		return false;
	}
	
	var state=$('#state').val();
	if(state==null||state==''){
		alert('需先选择状态！');
		return false;
	}
	
	var userName=$('#userName').val();
	if(userName==null||userName==''){
		alert('用户名不能为空！');
		return false;
	}
	//判断密码是否是6到20位之间
	var passwd=$('#passwd').val();
	if(passwd!=null&&passwd.length>0){
		if(!(passwd.length>=6&&passwd.length<=20))
	    {
	    	alert('密码必须在6到20个字符之间！');
	    	return false;
	    }
	    var repasswd=$('#repasswd').val();
		if(!(repasswd.length>=6&&repasswd.length<=20))
	    {
	    	alert('确认密码必须在6到20个字符之间！');
	    	return false;
	    }
	    if(passwd!=repasswd){
	    	alert('密码不一致！');
	    }
    }
    
    //判断用户编号
    var userId=$('#userId').val();
    if(userId==null||userId==''){
    	alert('用户编号不能为空！');
		return false;
    } 
    //判断用户组
    var userGroup=$('#userGroup').val();
    if(userGroup==null||userGroup==''){
    	alert('必须选择用户组！');
		return false;
    } 
	var pars='action=editdo&search.id='+id1+'&date='+new Date()+"&search.userName="+encodeURIComponent(userName)
  +"&search.userId="+$('#userId').val()+"&search.passwd="+passwd+"&search.userGroup="+userGroup
  +"&search.realName="+encodeURIComponent($('#realName').val())+"&search.zhicheng="+$('#zhicheng').val()
  +"&search.duty="+$('#duty').val()+"&search.sex="+$('#sex').val()+"&search.mobilephone="+$('#mobilephone').val()
  +"&search.workphone="+$('#workphone').val()+"&search.email="+$('#email').val()+"&search.birthday="+$('#birthday').val()
  +"&search.address="+encodeURIComponent($('#address').val())+"&search.postId="+$('#postId').val()+"&search.accountBank="
  +encodeURIComponent($('#accountBank').val())+"&search.accountId="+$('#accountId').val()+"&search.remark="+encodeURIComponent($('#remark').val())+"&search.state="+state+"&search.branchId="+branchId;
	var url = getActionMappingURL("/user",path);
  	$.get(url,pars,
  	function(res){
    	$('#messageDiv').hide();
    	$('#messageDiv').html(res);
  		var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  		var codeid = jsonMsgObj.getCodeid();
  		alert(jsonMsgObj.getMessage());
  		if (codeid == "0") {
  			closeEditUser();
  			loadUserList(path,page1,branchId1);
  		}
	});
}

//前台直接点击"关闭"按钮
function closeEditUser() {
	$('#addEditUserDiv').html("");
	$('#messageDiv').html("");
	$('#addEditUserDiv').hide();
}

//添加用户
function userAdd(path) {
	//判断用户名是否为空
	var branchId=$('#branchId2').val();
	if(branchId==null||branchId==''){
		alert('需先选择组织机构！');
		return false;
	}
	
	var state=$('#state').val();
	if(state==null||state==''){
		alert('需先选择状态！');
		return false;
	}
	
	var userName=$('#userName').val();
	if(userName==null||userName==''){
		alert('用户名不能为空！');
		return false;
	}
	//判断密码是否是6到20位之间
	var passwd=$('#passwd').val();
	if(!(passwd.length>=6&&passwd.length<=20))
    {
    	alert('密码必须在6到20个字符之间！');
    	return false;
    }
    var repasswd=$('#repasswd').val();
	if(!(repasswd.length>=6&&repasswd.length<=20))
    {
    	alert('确认密码必须在6到20个字符之间！');
    	return false;
    }
    if(passwd!=repasswd){
    	alert('密码不一致！');
    }
    //判断用户编号
    var userId=$('#userId').val();
    if(userId==null||userId==''){
    	alert('用户编号不能为空！');
		return false;
    } 
    //判断用户组
    var userGroup=$('#userGroup').val();
    if(userGroup==null||userGroup==''){
    	alert('必须选择用户组！');
		return false;
    } 
    
  var pars='action=addsave&date='+new Date()+"&search.userName="+encodeURIComponent(userName)
  +"&search.userId="+$('#userId').val()+"&search.passwd="+passwd+"&search.userGroup="+userGroup
  +"&search.realName="+encodeURIComponent($('#realName').val())+"&search.zhicheng="+$('#zhicheng').val()
  +"&search.duty="+$('#duty').val()+"&search.sex="+$('#sex').val()+"&search.mobilephone="+$('#mobilephone').val()
  +"&search.workphone="+$('#workphone').val()+"&search.email="+$('#email').val()+"&search.birthday="+$('#birthday').val()
  +"&search.address="+encodeURIComponent($('#address').val())+"&search.postId="+$('#postId').val()+"&search.accountBank="
  +encodeURIComponent($('#accountBank').val())+"&search.accountId="+$('#accountId').val()+"&search.remark="+encodeURIComponent($('#remark').val())+"&search.state="+state+"&search.branchId="+branchId;
  var url = getActionMappingURL("/user",path);
  $.get(url,pars,function(res){
    $('#messageDiv').hide();
    $('#messageDiv').html(res);
  	var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		closeEditUser();
  		loadUserMarkListPage(path,branchId);
  	}
	});
}

//删除某一用户
function delUserId(id1,page1,path,branchId) {
  if (!confirm('确实删除该条记录吗?')){
  	return;
  }else{
  	var url = getActionMappingURL("/user",path);
  	var pars="action=del&branchId="+branchId+"&date="+new Date()+"&id="+id1;
  	$.get(url,pars,function(res){
    	$('#messageDiv').hide();
    	$('#messageDiv').html(res);
  		var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  		var codeid = jsonMsgObj.getCodeid();
  		alert(jsonMsgObj.getMessage());
  		if (codeid == "0") {
  			closeEditUser();
  			loadUserList(path,page1,branchId);
  		}
	});
  }
}


function getLength(val){
	var len = 0;
	for (var i = 0; i < val.length; i++) 
	{
		if (val.charCodeAt(i) >= 0x4e00 && val.charCodeAt(i) <= 0x9fa5){ 
			len += 2;
		}else {
			len++;
		}
	}
	return len;
}
