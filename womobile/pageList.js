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


//说明:默认List页面选项卡
//参数说明:path请求的路径要全,curpage当前选项卡编号,pages选项卡总数
function loadDefaultListex(path,parm,curpage,pages) {
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
  var url=path+encodeURIComponent(encodeURIComponent(parm));
  $.get(url,{date:new Date()},showResult);
  function showResult(res){
	  $('#defaultlist').html(res); 
  }
}



function loadList(id,path) {
  //在未加载时动态显法加载
  $('#'+id).html("<center>页面载入中...</center>"); 
  //AJAX请求数据
  var url=path;
  $.get(url,{date:new Date()},showResult);
  function showResult(res){
	  $('#'+id).html(res); 
  }
}



//说明:默认List页面选项卡
//参数说明:path请求的路径要全,curpage当前选项卡编号,pages选项卡总数
function loadSearchList(path,parm,curpage,pages) {
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
  $('#defaultlist').html("<center>loadSearchList页面载入中...</center>"); 
  //AJAX请求数据
  /*
  var url=path+encodeURIComponent(encodeURIComponent(parm));
  $.get(url,{date:new Date()},showResult);
  function showResult(res){
	  $('#defaultlist').html(res); 
  }
  */
  //
  $.ajax({	
						mode : "abort",
						type : "post", 
						url : path, 
						data : $('#form1').serialize(),
						cache:false, 
	                    async:false, 
						success : function(data){
						     $('#defaultlist').html(data); 
						},
						complete : function(){
						}, 
						beforeSend : function(xhr){
						}, 
						error : function(){
						     alert("服务器忙");
						}
	});	
}

/*
参考格式
  $('#form1').submit(function(){
                jQuery.ajax({
                    url: "cashpay.do",   // 提交的页面
                    data: $('#form1').serialize(), // 从表单中获取数据
                    type: "POST",                   // 设置请求类型为"POST"，默认为"GET"
                    cache:false, 
                    async:false, 
                    beforeSend: function()          // 设置表单提交前方法
                    {    
                         var b=true;
                         var phoneNumber = $("#phoneNumber").val(); 
                         var repPhoneNumber=$("#repPhoneNumber").val();
                         var payAmt=$("#payAmt").val();
                         var repPayAmt=$("#repPayAmt").val();
                         if (phoneNumber.length!=8&&phoneNumber.length!=11){
                            alert("号码必须为8或11位数字！");
                            return false;
                         }
                           
               
                        
                     // new screenClass().lock();
                    },
                    error: function(request) {      // 设置表单提交出错
                      //  new screenClass().unlock();
                        alert("表单提交出错，请稍候再试");
                    },
                    success: function(data) {
                      //  new screenClass().unlock(); // 设置表单提交完成使用方法
                      
                         var url = "../biz/cashpay!GetBalance.do";
                          $.post(url, null , callbackGetBalance);
                        function callbackGetBalance(data1){

	                      // $("#form1").each( function() { this.reset();}); 
	                       $('#phoneNumber').val("");
	                       $('#payAmt').val("");
	                       $('#repPhoneNumber').val("");
	                          $('#Balance').val(data1);
	                     }
                         $.post("/biz/cashpay.do",{action:'search',totalPage:$("#totalPage").val(),curPage:'1'},showResult1);
	                     function showResult1(data2){
	                          $('#cashpaylist').html(data2); 
	                     }

                    }
                });

*/