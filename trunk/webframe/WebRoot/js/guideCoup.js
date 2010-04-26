//向导锦囊列表
function loadCoupList(path) {
  $('#coupListDiv').html("<center>页面载入中...</center>"); 

  var urls = getActionMappingURL("/coup",path);
  
  $.get(urls,{action:'list',date:new Date()},showResult);
  function showResult(res){
	  $('#coupListDiv').html(res); 
  }
} 

//打开单个向导锦囊
function loadCoupAddPage(path) {
  $("#addEditCoupDiv").show() ;
  $('#addEditCoupDiv').html("<center>页面载入中...</center>"); 
  var urls = getActionMappingURL("/coup",path);
  $.get(urls,{action:'add',date:new Date()},showResult);
  function showResult(res){
	  $('#addEditCoupDiv').html(res); 
  }
}

//添加向导锦囊
function guideCoupAdd(path) {
  var url = getActionMappingURL("/coup",path);
   
  $.get(url,{action:'addsave',date:new Date(),coupTitle:encodeURIComponent($('#coupTitle').val()),coupContent:encodeURIComponent($('#coupContent').val())},
  function(res){
    $('#messageDiv').hide();
    $('#messageDiv').html(res);
  	var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  	var codeid = jsonMsgObj.getCodeid();
  	alert(jsonMsgObj.getMessage());
  	if (codeid == "0") {
  		closeAddEditGuideCoup();
  		loadCoupList(path);
  	}
});
  
	function closeAddEditGuideCoup() {
	  $('#addEditCoupDiv').html("");
	  $('#messageDiv').html("");
	  $("#addEditCoupDiv").hide();
	}
}
	
//前台直接点击"关闭"按钮
function closeEditGuideCoup() {
	$('#addEditCoupDiv').html("");
	$('#messageDiv').html("");
	$("#addEditCoupDiv").hide();
}

//编辑向导锦囊
function loadCoupEditPage(id1,pageNum,path) {
  $("#addEditCoupDiv").show() ;
  $('#addEditCoupDiv').html("<center>页面载入中...</center>"); 
  
  var urls = getActionMappingURL("/coup",path);
  $.get(urls,{action:'edit',id:id1,date:new Date(),page:pageNum},showResult);
  function showResult(res){
	  $('#addEditCoupDiv').html(res); 
  }
}

//编辑保存向导锦囊
function guideCoupEdit(path,id1,page1){
	var url = getActionMappingURL("/coup",path);
   
  	$.get(url,{action:'editdo',date:new Date(),id:id1,coupTitle:encodeURIComponent($('#coupTitle').val()),coupContent:encodeURIComponent($('#coupContent').val())},
  	function(res){
    	$('#messageDiv').hide();
    	$('#messageDiv').html(res);
  		var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  		var codeid = jsonMsgObj.getCodeid();
  		alert(jsonMsgObj.getMessage());
  		if (codeid == "0") {
  			closeEditGuideCoup();
  			loadCoupList(path,page1);
  		}
	});
}

//向导锦囊分页列表
function loadCoupList(path,page1) {
  $('#coupListDiv').html("<center>页面载入中...</center>"); 

  var urls = getActionMappingURL("/coup",path);
  
  $.get(urls,{action:'list',page:page1,date:new Date()},showResult);
  function showResult(res){
	  $('#coupListDiv').html(res); 
  }
} 

//删除某一向导锦囊
function delCoupById(id1,page1,path) {
  if (!confirm('确实删除该条记录吗?')){
  	return;
  }else{
  	var url = getActionMappingURL("/coup",path);
   
  	$.get(url,{action:'del',date:new Date(),id:id1},
  	function(res){
    	$('#messageDiv').hide();
    	$('#messageDiv').html(res);
  		var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  		var codeid = jsonMsgObj.getCodeid();
  		alert(jsonMsgObj.getMessage());
  		if (codeid == "0") {
  			closeEditGuideCoup();
  			loadCoupList(path,page1);
  		}
	});
  }
}

