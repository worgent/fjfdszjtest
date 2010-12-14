var Eop=Eop||{};
Eop.Grid={
	defauts:{
		idChkName:"id", //id复选框name
		toggleChkId:"toggleChk"
	} 
	,
	opation:function(key,value){
		if(typeof(key)=='object'){
			this.defauts=$.extend({},this.defauts,key);
		}else if( typeof(key)=="string" ){
			this.defauts[key]=value;
		}
	}
	,
	deletePost:function(url,msg){
		var self =this;
		url=url.indexOf('?')>=0?url+"&":url+"?";
		url+="ajax=yes";
	//	url=basePath+url;
		var options = {
				url : url,
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();
					if(result.result==0){
						self.deleteRows();
						if(msg)
							alert(msg);
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

			$('form').ajaxSubmit(options);		
	},
	deleteRows:function(){
		$("input[name="+this.defauts.idChkName+"]").each(function(){
			var checkbox= $(this);
			if( checkbox.attr("checked") ){
				 checkbox.parents("tr").remove();
			}
		});		
	}
	,
	/**
	 * 检测id是否有被选中的，如果一个也没有返回假
	 */
	checkIdSeled:function(){
		var r=false;
		$("input[name="+this.defauts.idChkName+"]").each(function(){
			if( $(this).attr("checked") ){
				r=true;
				return ;
			}
		});
		
		return r;
	},
	/**
	 * 切换全选
	 */
	toggleSelected:function(checked){
		$("input[name="+this.defauts.idChkName+"]").attr("checked",checked);
	}
		
};

var EopPager={
		url:undefined,
		init:function(url){
		 	this.url= url;
			var self =this;
				$(".grid .page a").bind("click",function(){
					self.load(url,$(this).attr("pageno"));
				});
				$(".grid .page a.selected").unbind("click");
		},load:function(url,pageNo){
			
			var self =this;
			if(url.indexOf("ajax=yes")>0){
			 
				url=url.replace('&ajax=yes','');
				url=url.replace('?ajax=yes&','?');
				url=url.replace('?ajax=yes','');
				
				 
				url= url.replace(basePath,'');
				url=url.indexOf("?")>0?url+"&":url+"?";
				loadUrl(url+"page="+pageNo,self.onLoad);
			 
			}else{
				url=url.indexOf("?")>0?url+"&":url+"?";
					parent.Eop.AdminUI.loadUrlInFrm(url+"page="+pageNo);
				 
			}			
		},
		setLoadUrlFun:function(fun){
			$(".grid .page a").unbind("click");
			var self=this;
			$(".grid .page a").bind("click",function(){
				url= self.url;
				url=url.indexOf("?")>0?url+"&":url+"?";
				url=url+"page="+$(this).attr("pageno");
				fun(url);
			});
			$(".grid .page a.selected").unbind("click");
		}
	};