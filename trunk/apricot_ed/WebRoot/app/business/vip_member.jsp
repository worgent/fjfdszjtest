var vipCardFields=[
      {label:"VIP标识",name:"vip_id",allowBlank:false,hide:"all"},
      {label:"VIP卡号",name:"vip_card_no",allowBlank:false},
      {label:"VIP级别",name:"vip_level",allowBlank:false,xtype:"combo",code:"VIP_LEVEL"},
      {label:"VIP最大折扣率",name:"vip_discount",allowBlank:false}
];

var staffFields=[
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"}
];

var listField=[
      {label:"会员编号",name:"cust_id",allowBlank:false},
      {label:"会员姓名",name:"cust_name",allowBlank:false},
      {label:"性别",name:"cust_sex",allowBlank:false,xtype:"combo",code:"SEX",width:50},
      {label:"身份证",name:"cust_id_card"},
      {label:"联系电话",name:"cust_phone",allowBlank:false},
      {label:"生日",name:"cust_birthday",xtype:"date"},
      {label:"会员卡号",name:"vip_no",allowBlank:false},
      {label:"VIP级别",name:"vip_level",allowBlank:false,xtype:"combo",code:"vip_level2"},
      {label:"折扣率",name:"vip_discount",allowBlank:false},
      {label:"积分",name:"cum_point",allowBlank:false},
      {label:"状态",name:"cust_state",allowBlank:false,code:"CUST_STATUS"},
      {label:"备注",name:"cust_memo",hide:"form"}
   ];
var orderFields=[
      {label:"订单号",name:"order_no",hide:"form",width:120},
      {label:"订单号",name:"order_id",hide:"all"},
      {label:"餐位序号",name:"set_no",hide:"all"},
      {label:"座位名称",name:"balcony_name",width:80},
      {label:"订单类型",name:"order_type",hide:"form",xtype:"combo",code:"ORDER_TYPE",width:60},
      {label:"来宾人数",name:"man_count",width:60},
      {label:"下单时间",name:"order_time",xtype:"datetime",width:110},
      {label:"取消时间",name:"can_order_time",xtype:"datetime",width:110},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS",width:60},
      {label:"优惠前金额(￥)",name:"total"},
      {label:"优惠后金额(￥)",name:"pay_total"},
      {label:"优惠券金额(￥)",name:"youhui_total"},
      {label:"现金收费(￥)",name:"fact_pay_total"},
      {label:"刷卡收费(￥)",name:"fact_pay_card"},
      {label:"获得积分",name:"gain_point"},
      {label:"消费积分",name:"use_point"},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form",width:60}
   ];
   
var addField=[
      
      {label:"会员编号",name:"cust_id",allowBlank:false,hide:"all"},
      {label:"会员姓名",name:"cust_name",allowBlank:false},
      {label:"性别",name:"cust_sex",allowBlank:false,xtype:"combo",code:"SEX",value:"1"},
      {label:"身份证",name:"cust_id_card"},
      {label:"联系电话",name:"cust_phone",allowBlank:false},
      {label:"生日",name:"cust_birthday",readOnly:true,xtype:"date"},
      {label:"会员卡号",name:"vip_no",readOnly:true,xtype:"trigger",table:"vip_card",getData:function() {return {vip_status:"0",cAndAndAnd:true};},columns:vipCardFields,map:{vip_card_no:"vip_no"}},
      {label:"VIP卡级别",name:"vip_level",hide:"all"},
      {label:"VIP卡级别",name:"vip_level_text",allowBlank:false,readOnly:true,
      listeners:{
      	"select":function(){
      		var d = getFormValues("app_busi_vipmember_win_form",["vip_level"]);
      		alert(d.vip_level);
      		doSyncRequest("/vip/vip_card_getdiscount.do",d,
      		function(dt){
      			
      			setFormValues("app_busi_vipmember_win_form",{"vip_discount":dt[0]})
      		}
      		);
      	}
      }
      },
      {label:"折扣率",name:"vip_discount",readOnly:true},
//      {label:"状态",name:"cust_state",allowBlank:false,value:"0A",xtype:"combo",code:"MEMBER_STATUS",hide:"form"},
      {label:"备注",name:"cust_memo"},
      {label:"创建时间",name:"create_date",allowBlank:false,xtype:"datetime",readOnly:true,value:new Date()},
      {label:"操作员",name:"create_staee",hide:"form"},
     　{label:"操作员",name:"staff_name",readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields,map:{staff_id:"create_staee"}}

   ];

var updateMemberField=[
      
      {label:"会员编号",name:"cust_id",allowBlank:false,hide:"all"},
      {label:"会员姓名",name:"cust_name",allowBlank:false},
      {label:"性别",name:"cust_sex",allowBlank:false,xtype:"combo",code:"SEX",value:"1"},
      {label:"身份证",name:"cust_id_card",allowBlank:false},
      {label:"联系电话",name:"cust_phone",allowBlank:false},
      {label:"生日",name:"cust_birthday",allowBlank:false,readOnly:true,xtype:"datetime"},
//      {label:"会员卡号",name:"vip_no",readOnly:true},
//      {label:"卡级别",name:"vip_level",readOnly:true,xtype:"combo",code:"VIP_LEVEL"},
//      {label:"折扣率",name:"vip_discount",readOnly:true},
      {label:"状态",name:"cust_state",allowBlank:false,value:"1",xtype:"combo",code:"MEMBER_STATUS",hide:"all"},
      {label:"备注",name:"cust_memo"},
      {label:"修改时间",name:"MODIFY_TIME",allowBlank:false,xtype:"datetime",readOnly:true,value:new Date()},
      {label:"操作员",name:"MODIFY_STAEE",hide:"form"},
     　{label:"操作员",name:"staff_name",readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields,map:{staff_id:"MODIFY_STAEE"}}

   ];



var updateCardField=[
      
      {label:"会员编号",name:"cust_id",readOnly:true,allowBlank:false,hide:"all"},
      {label:"会员姓名",name:"cust_name",readOnly:true,allowBlank:false,hide:"all"},
      {label:"性别",name:"cust_sex",readOnly:true,hide:"all",allowBlank:false,xtype:"combo",code:"SEX",value:"1"},
      {label:"身份证",name:"cust_id_card",readOnly:true,allowBlank:false,hide:"all"},
      {label:"联系电话",name:"cust_phone",readOnly:true,allowBlank:false,hide:"all"},
      {label:"生日",name:"cust_birthday",allowBlank:false,readOnly:true,xtype:"datetime",hide:"all"},
      {label:"会员卡号",name:"vip_no",readOnly:true},
        {label:"VIP级别",name:"vip_level",allowBlank:false,xtype:"combo",code:"VIP_LEVEL",
      listeners:{
      	"select":function(){
      		var d = getFormValues("app_busi_vipmember_win_form",["vip_level"]);
      		doSyncRequest("/vip/vip_card_getdiscount.do",d,
      		function(dt){
      			setFormValues("app_busi_vipmember_win_form",{"vip_discount":dt[0]})
      		}
      		);
      	}
      }
      },
      {label:"折扣率",name:"vip_discount",readOnly:true},
      {label:"状态",name:"cust_state",allowBlank:false,value:"1",xtype:"combo",code:"MEMBER_STATUS",hide:"all"},
      {label:"备注",name:"cust_memo"},
      {label:"修改时间",name:"MODIFY_TIME",allowBlank:false,xtype:"datetime",readOnly:true,value:new Date()},
      {label:"操作员",name:"MODIFY_STAEE",hide:"form"},
     　{label:"操作员",name:"staff_name",readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields,map:{staff_id:"MODIFY_STAEE"}}

   ];
   
var changeCardField=[
      
      {label:"会员编号",name:"cust_id",readOnly:true,allowBlank:false,hide:"all"},
      {label:"会员姓名",name:"cust_name",readOnly:true,allowBlank:false,hide:"all"},
      {label:"性别",name:"cust_sex",readOnly:true,hide:"all",allowBlank:false,xtype:"combo",code:"SEX",value:"1"},
      {label:"身份证",name:"cust_id_card",readOnly:true,allowBlank:false,hide:"all"},
      {label:"联系电话",name:"cust_phone",readOnly:true,allowBlank:false,hide:"all"},
      {label:"生日",name:"cust_birthday",allowBlank:false,readOnly:true,xtype:"datetime",hide:"all"},
      {label:"会员卡号",name:"vip_no",readOnly:true,xtype:"trigger",table:"vip_card",getData:function() {return {vip_status:"0",cAndAndAnd:true};},columns:vipCardFields,map:{vip_card_no:"vip_no"}},
      {label:"VIP卡级别",name:"vip_level_text",allowBlank:false,readOnly:true,
      listeners:{
      	"select":function(){
      		var d = getFormValues("app_busi_vipmember_win_form",["vip_level"]);
      		doSyncRequest("/vip/vip_card_getdiscount.do",d,
      		function(dt){
      			
      			setFormValues("app_busi_vipmember_win_form",{"vip_discount":dt[0]})
      		}
      		);
      	}
      }
      },
      {label:"折扣率",name:"vip_discount",readOnly:true},
      {label:"状态",name:"cust_state",allowBlank:false,value:"1",xtype:"combo",code:"MEMBER_STATUS",hide:"all"},
      {label:"备注",name:"cust_memo"},
      {label:"修改时间",name:"MODIFY_TIME",allowBlank:false,xtype:"datetime",readOnly:true,value:new Date()},
      {label:"操作员",name:"MODIFY_STAEE",hide:"form"},
     　{label:"操作员",name:"staff_name",readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields,map:{staff_id:"MODIFY_STAEE"}}

   ];


 var addFunc=function(){
     createFormWindow({
        id        : "app_busi_vipmember",
        label     : "创建新的会员",
        items     : addField,
        width	  : 350,
        height	  : 450,
        cols      : 1,
        action    : "/vip/vip_member_insert.do",
        close     : cLoadEvent("app_busi_vipmember")
     
     }).show();
 }
 
 var modFunc=function(){
      var d=getData("app_busi_vipmember");
      createFormWindow({
        id        : "app_busi_vipmember",
        label     : "修改的会员信息",
        items     : updateMemberField,
        cols      : 1,
        action    : "/vip/vip_member_modify.do",
        data      : d,
        close     : cLoadEvent("app_busi_vipmember")
     
     }).show();
 }
 
 var delFunc=function(){
     var d=getData("app_busi_vipmember");

     doPost("/vip/vip_member_delete.do",d,cLoadEvent("app_busi_vipmember"));
 }
 
  var cardaddFunc=function(){
  var d=getData("app_busi_vipmember");
  if(d.vip_no!=""){
  	$alt("该用户已有VIP卡");
  	return;
  }
  var d=getData("app_busi_vipmember");
      createFormWindow({
        id        : "app_busi_vipmember",
        label     : "会员卡发放",
        items     : changeCardField,
        cols      : 1,
        action    : "customer_info.modify.go",
        data      : d,
        close     : cLoadEvent("app_busi_vipmember")
     
     }).show();
 }
 
var cardUpdateFunc=function(){
  var d=getData("app_busi_vipmember");
  if(d.vip_no==""){
  	$alt("该用户还未有VIP卡,不能修改");
  	return;
  }
      createFormWindow({
        id        : "app_busi_vipmember",
        label     : "会员卡级别修改",
        items     : updateCardField,
        cols      : 1,
        action    : "/vip/vip_card_modify.do",
        data      : d,
        close     : cLoadEvent("app_busi_vipmember")
     
     }).show();
 }
 
  var cardLockFunc=function(){
  var d=getData("app_busi_vipmember");

     doPost("/vip/vip_card_lock.do",d,cLoadEvent("app_busi_vipmember"));
 }
  
   var cardUnLockFunc=function(){
 var d=getData("app_busi_vipmember");

     doPost("/vip/vip_card_unLock.do",d,cLoadEvent("app_busi_vipmember"));
 } 
  var cardChangeFunc=function(){
  var d=getData("app_busi_vipmember");
      createFormWindow({
        id        : "app_busi_vipmember",
        label     : "会员卡更换",
        items     : changeCardField,
        cols      : 1,
        action    : "/vip/vip_card_change.do",
        data      : d,
        close     : cLoadEvent("app_busi_vipmember")
     
     }).show();
 } 
 
 var grid=createPageGrid({
     id            : "app_busi_vipmember",
     label         : "会员列表",
     region        : "north",
     height        : 300,
     items         : listField,
     filter        : true,
     page          : true,
     urls          : "customer_info.pages.go",
     createFunction: addFunc,
     modifyFunction: modFunc,
     deleteFunction: delFunc,
     toolBarFields :[
      
      {text:"会员卡发放",iconCls:"gridmultiadd",handler:cardaddFunc},
      "-",
//      {text:"修改卡级别信息",iconCls:"gridmultiadd",handler:cardUpdateFunc},
//      "-",
      {text:"会员卡挂失",iconCls:"gridmultiadd",handler:cardLockFunc},
      "-",
      {text:"会员卡解挂",iconCls:"gridmultiadd",handler:cardUnLockFunc},
      "-",
      {text:"会员卡更换",iconCls:"gridmultiadd",handler:cardChangeFunc},
      "-",
      {text:"导出Excel",handler:ExportExcel},
      "-"
    ],
    rowclick     : function(){
                       var d=getData("app_busi_vipmember",["vip_no"]);
                       load("app_busi_vipmember_order",d);
                    }
 
 });
 
function ExportExcel()
{
	doGet("/vip/vip_member_exportExcel.do",null,function(d){
	    alert(d["flag"]);
	})
}

 var orderGrid=createPageGrid({
     id            : "app_busi_vipmember_order",
     label         : "会员订单列表",
     region        : "center",
     items         : orderFields,
     filter        : false,
     page          : true,
     urls          : "/vip/vip_member_order.do"
 })

 return [grid,orderGrid];
   