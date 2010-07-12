var setRateField=[
      {label:"VIP级别",name:"vip_level",xtype:"combo",code:"VIP_LEVEL"},
      {label:"VIP最大折扣率",name:"vip_discount"}
   ];
   

   
var listField=[
      {label:"VIP标识",name:"vip_id",allowBlank:false,hide:"all"},
      {label:"VIP卡号",name:"vip_card_no",allowBlank:false},

        {label:"VIP级别",name:"vip_level",allowBlank:false,xtype:"combo",code:"VIP_LEVEL",
      listeners:{
      	"select":function(){
      		var d = getFormValues("app_busi_vipcard_win_form",["vip_level"]);
      		doSyncRequest("/vip/vip_card_getdiscount.do",d,
      		function(dt){
      			
      			setFormValues("app_busi_vipcard_win_form",{"vip_discount":dt[0]})
      		}
      		);
      	}
      }
      },
      {label:"VIP最大折扣率",name:"vip_discount",allowBlank:false},

      {label:"VIP状态",name:"vip_status",allowBlank:false,code:"VIP_STATUS"}
//      {label:"VIP积分",name:"vip_point",hide:"form"}
   ];
   multiaddField=[
      {label:"VIP标识",name:"vip_id",allowBlank:false,hide:"all"},
      {label:"VIP开始编号",name:"start_no",allowBlank:false},
      {label:"VIP结束编号",name:"end_no",allowBlank:false},

       {label:"VIP级别",name:"vip_level",allowBlank:false,xtype:"combo",code:"VIP_LEVEL",
      listeners:{
      	"select":function(){
      		var d = getFormValues("app_busi_vipcard_win_form",["vip_level"]);
      		doSyncRequest("/vip/vip_card_getdiscount.do",d,
      		function(dt){
      			
      			setFormValues("app_busi_vipcard_win_form",{"vip_discount":dt[0]})
      		}
      		);
      	}
      }
      },
      {label:"VIP最大折扣率",readOnly:true,name:"vip_discount",allowBlank:false},

      {label:"VIP状态",name:"vip_status",allowBlank:false,xtype:"combo",value:"0",code:"VIP_STATUS"}
//      {label:"VIP积分",name:"vip_point",hide:"form"}
   ];
   var addField=[
      {label:"VIP标识",name:"vip_id",allowBlank:false,hide:"all"},
      {label:"VIP卡号",name:"vip_card_no",allowBlank:false},

      {label:"VIP级别",name:"vip_level",allowBlank:false,xtype:"combo",code:"VIP_LEVEL",
      listeners:{
      	"select":function(){
      		var d = getFormValues("app_busi_vipcard_win_form",["vip_level"]);
      		doSyncRequest("/vip/vip_card_getdiscount.do",d,
      		function(dt){
      			
      			setFormValues("app_busi_vipcard_win_form",{"vip_discount":dt[0]})
      		}
      		);
      	}
      }
      },
      {label:"VIP最大折扣率",readOnly:true,name:"vip_discount",allowBlank:false},
      {label:"VIP状态",name:"vip_status",allowBlank:false,xtype:"combo",value:"0",code:"VIP_STATUS"}
//      {label:"VIP积分",name:"vip_point",hide:"form"}
   ];
   var updateField=[
      {label:"VIP标识",name:"vip_id",allowBlank:false,hide:"all"},
      {label:"VIP卡号",name:"vip_card_no",allowBlank:false},

        {label:"VIP级别",name:"vip_level",allowBlank:false,xtype:"combo",code:"VIP_LEVEL",
      listeners:{
      	"select":function(){
      		var d = getFormValues("app_busi_vipcard_win_form",["vip_level"]);
      		doSyncRequest("/vip/vip_card_getdiscount.do",d,
      		function(dt){
      			
      			setFormValues("app_busi_vipcard_win_form",{"vip_discount":dt[0]})
      		}
      		);
      	}
      }
      },
      {label:"VIP最大折扣率",name:"vip_discount",readOnly:true,allowBlank:false},

      {label:"VIP状态",name:"vip_status",allowBlank:false,xtype:"combo",code:"VIP_STATUS"}
//      {label:"VIP积分",name:"vip_point",hide:"form"}
   ];

   
   
  	var setRate=function(){
     	createFormWindow({
        id        : "app_busi_vipcard",
        label     : "设置折扣率",
        items     : setRateField,
        cols      : 1,
        action    : "/vip/vip_card_setRate.do",
        close     : cLoadEvent("app_busi_vipcard")
     
     }).show();
 }
 var multiaddFunc=function(){
     createFormWindow({
        id        : "app_busi_vipcard",
        label     : "批量增加VIP卡",
        items     : multiaddField,
        cols      : 1,
        action    : "/vip/vip_card_insert_m.do",
        close     : cLoadEvent("app_busi_vipcard")
     
     }).show();
 }
   
 var addFunc=function(){
     createFormWindow({
        id        : "app_busi_vipcard",
        label     : "创建新的VIP卡",
        items     : addField,
        cols      : 1,
        action    : "/vip/vip_card_insert.do",
        close     : cLoadEvent("app_busi_vipcard")
     
     }).show();
 }
 
 var modFunc=function(){
      var d=getData("app_busi_vipcard");
      createFormWindow({
        id        : "app_busi_vipcard",
        label     : "修改的VIP卡",
        items     : updateField,
        cols      : 1,
        action    : "vip_card.modify.go",
        data      : d,
        close     : cLoadEvent("app_busi_vipcard")
     
     }).show();
 }
 
 var delFunc=function(){
     var d=getData("app_busi_vipcard",["vip_id","vip_status"]);
 //    d["vip_status"];
 //    alert(urlEncode(d));
//	alert(d["vip_status"]);
	if(d["vip_status"]=="0"||d["vip_status"]=="2"){
     	doPost("vip_card.delete.go",d,cLoadEvent("app_busi_vipcard"));
     }else{
     	$alt("只能删除未发放和已损坏的VIP卡");
     }
 }
 

   
 var grid=createPageGrid({
     id            : "app_busi_vipcard",
     label         : "VIP卡列表",
     region        : "center",
     items         : listField,
     filter        : false,
     page          : true,
     urls          : "vip_card.pages.go",
     createFunction: addFunc,
     modifyFunction: modFunc,
     deleteFunction: delFunc,
     toolBarFields :[
      {text:"批量增加",iconCls:"gridmultiadd",handler:multiaddFunc},
      {text:"设置卡率",iconCls:"gridmultiadd",handler:setRate}
    ]
 
 });
 
 return [grid];
   