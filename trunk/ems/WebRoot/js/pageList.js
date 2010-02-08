//说明:默认List页面选项卡
//参数说明:path请求的路径要全,curpage当前选项卡编号,pages选项卡总数
function loadDefaultList(path,curpage,pages) {
  //设置选项卡为选中状态
  var i=0;	
  for(i=1;i<=pages;i++)
  {
      $('#tab'+i).removeAttr("class");
      //$('#tab'+i).removeclass("f_tabClass2");
      if(i==curpage) {
       		$('#tab'+i).addClass("f_tabClass1"); 
      }else{
            $('#tab'+i).addClass("f_tabClass2"); 
      }
  }

  //在未加载时动态显法加载
  $('#defaultlist').html("<center>页面载入中...</center>"); 
  //AJAX请求数据
  var url=path;
  $.get(url,{date:new Date()},showResult);
  function showResult(res){
	  $('#defaultlist').html(res); 
  }
}