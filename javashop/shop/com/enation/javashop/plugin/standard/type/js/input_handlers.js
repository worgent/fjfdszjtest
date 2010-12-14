

//加载属性定义的输入html
function loadPropsInput(type_id){
	$.ajax({
	type: "get",
	url:basePath+"type!disPropsInput.do?ajax=yes",
	data:"type_id=" + type_id +"&m=" + new Date().getTime(),
	dataType:"html",
	success:function(result){
	   document.getElementById("tab_props").innerHTML = result;
	},
	 error :function(res){alert("异步读取失败:" + res);}
	});
}



//加载参数定义的输入html
function loadParamsInput(type_id){
	$.ajax({
	type: "get",
	url:basePath+"type!disParamsInput.do?ajax=yes",
	data:"type_id=" + type_id +"&m=" + new Date().getTime(),
	dataType:"html",
	success:function(result){
	 
		try{
			document.getElementById("tab_params").innerHTML = result;
	    }catch(e){}
	//    alert( document.getElementById("custom_params").innerHTML);
	  
	},
	 error :function(res){alert("异步读取失败:" + res);}
	});
}


//加载参数定义的输入html
function loadBrandsInput(type_id,brand_id){
	$.ajax({
	type: "get",
	url:basePath+"type!listBrand.do?ajax=yes",
	data:"type_id=" + type_id +"&m=" + new Date().getTime(),
	dataType:"html",
	success:function(result){
		 
		   $("#brand_id").empty().append(result);
		   if(brand_id){
		   	$("#brand_id").val(brand_id);
		   }
	  
	},
	 error :function(res){alert("异步读取失败:" + res);}
	});
}
   
function type_change_event(type_id,join_brand,brand_id){
	if(parseInt(join_brand)==1){  
		$("#brand_tr").show();
		loadBrandsInput(type_id,brand_id);
	}else{
		$("#brand_id").empty();
		$("#brand_tr").hide();
	}
		
		if(!brand_id){ //如果传递了brand_id 说明在编辑
			loadPropsInput(type_id);
			loadParamsInput(type_id);
		}	
}

function getJoinBrand(typeid){
    var join_brand = 0;
	$("#type_id option").each(function(){
		if($(this).val() == typeid && $(this).attr("join_brand")== 1 ){
			join_brand=1;
		} 
	});
	return join_brand;
}
 
//切换类型
function changeType(cat_id){
	if(confirm("是否根据所选分类的默认类型重新设定商品类型？\n如果重设，可能丢失当前所输入的类型属性、关联品牌、参数表等类型相关数据。")){
		var type_id = types["" + cat_id] ;
		$("#type_id").val(type_id);
		type_change_event(type_id,getJoinBrand(type_id));
	}
}
