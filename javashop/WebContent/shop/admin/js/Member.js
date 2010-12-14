var Member=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#delMBtn").click(function(){self.doMDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的会员等级");
			return ;
		}
	 
		if(!confirm("确认要将这些会员等级彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		$.Loading.show("正在删除会员等级...");
		
		this.deletePost("member!deletelv.do");
			
	} ,
    doMDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的会员");
			return ;
		}
	 
		if(!confirm("确认要将这些会员彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		$.Loading.show("正在删除会员...");
		
		this.deletePost("member!delete.do");
			
	} 
	
});

$(function(){
	Member.init();
});