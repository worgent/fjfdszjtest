var Cat={
	init:function(){
		 var self = this;
		$("#sortBtn").click(function(){
			self.saveSort();
		});
	},
	saveSort:function(){
		$.Loading.show('正在保存排序，请稍侯...');
		var options = {
				url :"cat!saveSort.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {				
				 	if(result.result==1){
				 		$.Loading.hide();
				 		alert("更新成功");
				 		location.reload();
				 	}else{
				 		alert(result.message);
				 	}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出错啦:(");
 				}
 		};
 
		$("form").ajaxSubmit(options);		
	}
	
};