var CheckOut={
		init:function(){
			var self = this;
			$("#receiver input[name=addressId]").click(function(){
				if($(this).attr("id")=="otherAddress"){
					$("#checkout-recaddr").show();
				}else{
					self.loadDlyType($(this).attr("regionid"));
					$("#checkout-recaddr").hide();
				}
			
			});
			
			$("#checkoutform").submit(function(){
				return self.checkform();
			});
			
			$("#receiver input[name=addressId]:first").click();
			if($("#receiver input[name=addressId]").size()==0){
				$("#otherAddress").click();
			}
	 	},
	 
	 	//显示配送方式
	 	loadDlyType:function(regionid){
	 		
	 		if(regionid){
		 		var self= this;
		 		$("#shipping").html("loding...").load("widget?type=checkOutWidget&action=showDlyType&ajax=yes&regionid="+regionid,function(){
					$("#shipping input[name=typeId]").click(function(){
						$("#shipping [name=isProtect]").removeAttr("checked");
						var isProtect = $(this).parents("tr").find("input[name=isProtect]").attr("checked")?1:0;
						self.loadOrderTotal($(this).val(),isProtect);
					});
					$("#shipping [name=isProtect]").click(function(){
						var current = this;
						$("#shipping [name=isProtect]").each(function(){
							if(current != this)
								$(this).removeAttr("checked");
						});
						var shippingType = $(this).parents("tr").find("input[name=typeId]");
						shippingType.attr("checked",true);
						self.loadOrderTotal(shippingType.val(),1);
					});
				});
	 		}
	 	},
	 	//加载订单总金额
	 	loadOrderTotal:function(typeId,isProtected){
	 		var regionid = $("#receiver input[checked!='']").attr("regionid");
	 		if( $("#otherAddress").attr("checked") ){
	 			regionid =$("#region_id").val();
	 		}
	 		$("#amountInfo").load
	 		(
	 		"widget?type=checkOutWidget&action=showOrderTotal&ajax=yes&typeId="+typeId+"&regionId="+regionid+"&isProtected="+isProtected
	 		);
	 	},
	 	checkform:function(){
	 		if( $("input[name=typeId][checked=='']").size()==0 ){
	 			alert('请选择配送方式');
	 			return false;
	 		} 
	 		if( $("input[name=paymentId][checked=='']").size()==0 ){
	 			alert('请选择支付方式');
	 			return false;
	 		} 	 		
	 		return true;
	 	}
};