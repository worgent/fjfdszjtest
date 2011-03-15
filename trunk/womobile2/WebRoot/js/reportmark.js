//加载用户列表
function loadReportMarkListPage(path,reportId,parentType){
  $('#reportMarkListDiv').html("<center>页面载入中...</center>"); 
  var urls = getActionMappingURL("/customReport",path);
  var pars = "action=list&reportId="+reportId+"&parentType="+parentType+"&date="+new Date();
  $.get(urls,pars,showResult);
  function showResult(res){
  	$('#reportMarkListDiv').html(res); 
  }
}

