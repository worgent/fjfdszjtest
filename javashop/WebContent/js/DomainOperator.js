
var Core={};
Core.DomainOperator={
	dlg:undefined,
	init:function(){
		this.refreshList();
		var dlgEl =  $("#newDomainDlg");
		dlgEl.hide();
		var that = this;
		 $("#newDomain").click(function(){
			 var link= $(this);
			 that.dlg = dlgEl.dialog({ modal: true, title:'新增域名', resizable:false, bgiframe:true });
			 var siteid = link.attr("siteid");
			 dlgEl.load("userSite!adddomain.do",{id:siteid,ajax:'yes'},function(){
				 $("#add_btn").click(function(){
					 that.btn_click();
				 });
				 that.dlg.dialog("open");
			 });
			 
		 });		
	},
	
	
	del_click:function(domainid){
		var that = this;
		if(confirm('确认要删除吗？')){
			$.ajax({
				 type: "POST",
				 url: "userSite!deleteDomain.do",
				 data:   "ajax=yes&domainid="+domainid,
				 dataType:'json',
				 success: function(result){
					 if(result.code==0){
						 that.refreshList();
					 }else{
						 alert("删除失败，请重试");
					 }
			     },
			     error:function(){
					alert("删除异步请求错误");
				 }
				}); 		
		}
	},
	
	edit_click:function(domainid, statusid){
		var that = this;
		if(confirm('确认要更改此域名的状态吗？')){
			$.ajax({
				 type: "POST",
				 url: "userSite!editdomain.do",
				 data:   "ajax=yes&domainid="+domainid+"&statusid="+statusid,
				 dataType:'json',
				 success: function(result){
					 if(result.code==0){
						 that.refreshList();
					 }else{
						 alert("状态更改失败，请重试");
					 }
			     },
			     error:function(){
					alert("状态更改异步请求错误");
				 }
				}); 		
		}
	},

	refreshList:function(){
		var that= this;
		 var siteid = $("#newDomain").attr("siteid");
		$("#list_wrapper").load("core/user/userSite!domainlist.do",{id:siteid,ajax:'yes'},function(){
			$(".domainDel").click(function(){
				that.del_click($(this).parent("td").attr("domainid"));
			});
			$(".domainedit").click(function(){
				that.edit_click($(this).parent("td").attr("domainid"), $(this).parent("td").attr("statusid"));
			});
		});
	},

	btn_click:function(){
		 var that= this;
		 var options = {
				url : "userSite!addDomainSave.do" ,
				data: "ajax=yes",
				dataType : 'html',
				type : "POST",
				success : function(result) {
					that.dlg.dialog("close");
					 that.refreshList();
				},
				error : function(e) {
					alert("出现错误 " + e);
				}
			};

			$('#domainForm').ajaxSubmit(options);	
	}

};


$(function(){
	Core.DomainOperator.init();
});