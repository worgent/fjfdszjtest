//商品选择器
var GoodsSelector={
	conid:undefined,
	init:function(conid,onConfirm)	{
		this.conid = conid;
		var self  = this;
		$("#searchBtn").click(function(){
			self.search(onConfirm);
		});
		
		$("#toggleChk").click(function(){
			
			$("input[name=goodsid]").attr("checked",this.checked);			
		});

		
		$("#"+conid+" .submitBtn").click(function(){
			self.getGoodsList(onConfirm);
			Eop.Dialog.close(conid);
		});
		
		function loadUrl(url){
			$("#"+conid).load(url,function(){
				self.init(conid,onConfirm);
			});
		}
		EopPager.setLoadUrlFun(loadUrl);
		 
	},
	search:function(onConfirm){
		var self = this;
		var options = {
				url :basePath+"selector!goods.do?ajax=yes",
				type : "GET",
				dataType : 'html',
				success : function(result) {				
					$("#"+self.conid).empty().append(result);
					self.init(self.conid,onConfirm);
				},
				error : function(e) {
					alert("出错啦:(");
 				}
 		};
 
		$("#serchform").ajaxSubmit(options);		
	},
	getGoodsList:function(callback){
		var options = {
				url :basePath+"selector!listGoods.do?ajax=yes",
				type : "GET",
				dataType : 'json',
				success : function(result) {				
					if(callback){
						callback(result);
					}
				},
				error : function(e) {
					alert("出错啦:(");
 				}
 		};
 
		$("#serchform").ajaxSubmit(options);				
	},
	open:function(conid,onConfirm){
		var self= this;
		$("#"+conid).load(basePath+'selector!goods.do?ajax=yes',function(){
			self.init(conid,onConfirm);
		});
		Eop.Dialog.open(conid);		
	}
};

//货品选择器
var ProductSelector={
	conid:undefined,
	init:function(conid,onConfirm){
		this.conid = conid;
		var self  = this;
		$("#searchBtn").click(function(){
			self.search(onConfirm);
		});
		
		$("#toggleChk").click(function(){
			
			$("input[name=productid]").attr("checked",this.checked);			
		});

		$("#"+conid+" .submitBtn").click(function(){
			self.getGoodsList(onConfirm);
			Eop.Dialog.close(conid);
		});
		
		function loadUrl(url){
			$("#"+conid).load(url,function(){
				self.init(conid,onConfirm);
			});
		}
		EopPager.setLoadUrlFun(loadUrl);
	},
	search:function(onConfirm){
		var self = this;
		var options = {
				url :basePath+"selector!product.do?ajax=yes",
				type : "GET",
				dataType : 'html',
				success : function(result) {				
					$("#"+self.conid).empty().append(result);
					self.init(self.conid,onConfirm);
				},
				error : function(e) {
					alert("出错啦:(");
 				}
 		};
 
		$("#serchform").ajaxSubmit(options);		
	},
	getGoodsList:function(callback){
		var options = {
				url :basePath+"selector!listProduct.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {				
					if(callback){
						callback(result);
					}
				},
				error : function(e) {
					alert("出错啦:(");
 				}
 		};
 
		$("#serchform").ajaxSubmit(options);				
	},
	open:function(conid,onConfirm){
		var self= this;
		$("#"+conid).load(basePath+'selector!product.do?ajax=yes',function(){
			self.init(conid,onConfirm);
		});
		Eop.Dialog.open(conid);		
	}
};


//赠品选择器
var GiftSelector={
		conid:undefined,
		init:function(conid,onConfirm)	{
			this.conid = conid;
			var self  = this;
			$("#giftSerchform input[name=searchBtn]").click(function(){
				self.search();
			});
			
			$("#giftSerchform input[name=toggleChk]").click(function(){
				$("input[name=giftid]").attr("checked",this.checked);			
			});

			$("#"+conid+" .submitBtn").click(function(){
				self.getGiftList(onConfirm);
				Eop.Dialog.close(conid);
			});
			
			function loadUrl(url){
				$("#"+conid).load(url,function(){
					self.init(conid,onConfirm);
				});
			}
			EopPager.setLoadUrlFun(loadUrl);
			
		},
		search:function(onConfirm){
			var self = this;
			var options = {
					url :basePath+"selector!gift.do?ajax=yes",
					type : "GET",
					dataType : 'html',
					success : function(result) {				
						$("#"+self.conid).empty().append(result);
						self.init(self.conid,onConfirm);
					},
					error : function(e) {
						alert("出错啦:(");
	 				}
	 		};
	 
			$("#giftSerchform").ajaxSubmit(options);		
		},
		getGiftList:function(callback){
			var options = {
					url :basePath+"selector!listGift.do?ajax=yes",
					type : "GET",
					dataType : 'json',
					success : function(result) {				
						if(callback){
							callback(result);
						}
					},
					error : function(e) {
						alert("出错啦:(");
	 				}
	 		};
	 
			$("#giftSerchform").ajaxSubmit(options);				
		},
		open:function(conid,onConfirm){
			var self= this;
			$("#"+conid).load(basePath+'selector!gift.do?ajax=yes',function(){
				self.init(conid,onConfirm);
			});
			Eop.Dialog.open(conid);		
		}
	};
