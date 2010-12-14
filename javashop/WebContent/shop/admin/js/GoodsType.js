var Prop={
	inputHtml:undefined,
	init:function(){
		var self=this;
		this.initinputHtml();
		$("#propAddBtn").click(function(){self.addPropInput();});
		$("#props_table  .delete").click(function(){
			$(this).parents("tr").remove();
		});
	},
	
	/*
	 * 初始化属性输入模板html
	 */
	initinputHtml:function(){
		var self=this;
		$.ajax({
			 type: "POST",
			 url: "type/prop_input_item.jsp?ajax=yes",
			 dataType:'html',
			 success: function(html){ 
				self.inputHtml=html;
			 },
			 error:function(){
				 alert("抱歉，属性模板加载失败，请重试..");
			 }
			});
		
	}
	,
	/*
	 * 增加一个属性输入项
	 */
	addPropInput:function(){
		$(this.inputHtml).insertAfter($("#props_table>tbody>tr:last"))
		.find("td .delete").click(function(){
			$(this).parents("tr").remove();
		});
	}
};



var Param={
		inputHtml:undefined,
		init:function(){
			var self=this;
			this.initInputHtml();
			
			//添加一个组
			$("#paramAddBtn").click(function(){self.addGroupInput();});
			
			//删除组
			$("tr.group .delete").click(function(){
				$(this).parents("table").remove();
			});
			
			//删除参数项
			$("tr.param .delete").click(function(){
				$(this).parents("tr").remove();
			});
			
			$("tr.group>td a.addBtn").click(function(){
				self.addParamItemInput( $(this).parents("table") );			
			});
			

			
		},
		
		/*
		 * 初始化属性输入模板html
		 */
		initInputHtml:function(){
			var self=this;
			$.ajax({
				 type: "POST",
				 url: "type/param_input_item.jsp?ajax=yes",
				 dataType:'html',
				 success: function(html){ 
					self.inputHtml=html;
				 },
				 error:function(){
					 alert("抱歉，参数模板加载失败，请重试..");
				 }
				});
			
		}
		,
		/*
		 * 增加一个参数组输入项
		 */
		addGroupInput:function(){
			var self=this;
			var group=$(this.inputHtml).appendTo($("#param_div"));
			group.find("tbody>tr.group>td .delete").click(function(){
				$(this).parents("table").remove();
			});
			
			group.find("tbody>tr.group>td .addBtn").click(function(){
				self.addParamItemInput( $(this).parents("table") );
			});
		},
		
		/*
		 * 增加一个参数输入项
		 */
		addParamItemInput:function(table){
			$(this.inputHtml).find("tbody>tr:last")
			.insertAfter(table.find("tbody>tr:last"))
			.find("td .delete").click(function(){
				$(this).parents("tr").remove();
			});
		}
		
};
var GoodsType=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#cleanBtn").click(function(){self.doClean();	});
		$("#revertBtn").click(function(){self.doRevert();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的类型");
			return ;
		}
	 
		if(!confirm("确认要将这些类型放入回收站吗？")){	
			return ;
		}
		
		$.Loading.show("正在类型放入回收站...");
		
		this.deletePost("type!delete.do");
			
	},
	doClean:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的类型");
			return ;
		}
	 
		if(!confirm("确认要将这些类型彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		this.deletePost("type!clean.do");
	},
	
	doRevert:function(){
		if(!this.checkIdSeled()){
			alert("请选择要还原的类型");
			return ;
		}
	 
		this.deletePost("type!revert.do","选择的类型已被成功还原至类型列表中");		
	},
	intChkNameEvent:function(){
		$(".submitlist .submitBtn").click(function(){
			$.Loading.show("正在检测类型名是否重复...");
			var name = $("#name").val();
			$("form").ajaxSubmit({
				url:'type!checkname.do?ajax=yes',
				type:'POST',
				dataType:'json',
				success:function(result){
					if(result.result==1){
						if(confirm("类型"+name+"已经存在，您确定要保存吗？")){
							$.Loading.hide();
							$("form")[0].submit();
						}
						$.Loading.hide();
					} else{
						$.Loading.hide();
						$("form")[0].submit();
					} 
				},error:function(){
					alert("检测名称出错");
				}
			});
		});	
	}	
});
