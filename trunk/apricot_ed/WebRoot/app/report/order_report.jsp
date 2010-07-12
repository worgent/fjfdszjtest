var setFields=[
  {label:"座位号",name:"set_no",hide:"all",readOnly:true},
  {label:"楼层",name:"dining_floor",readOnly:true},
  {label:"所属位置",name:"belong_to_text",readOnly:true},
  {label:"座位名称",name:"balcony_name",readOnly:true},
  {label:"座位状态",name:"man_num",readOnly:true},
  {label:"最低消费类型",name:"mincost_type",readOnly:true,code:"MINCOST_TYPE"},
  {label:"最低消费金额",name:"mincost_money",readOnly:true}
];
var staffFields=[
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"}
];

var foodFields=[
  {label:"菜品ID",name:"food_id",hide:"all"},
  {label:"菜品名称",name:"food_name"},
  {label:"菜品单价",name:"food_price"},
  {label:"菜品类型",  name:"food_type",xtype:"combo",code:"FOOD_TYPE"}

];
//订单列表域
var orderFields=[
      {label:"订单号",name:"order_no",hide:"form",width:150},
      {label:"订单号",name:"order_id",hide:"all"},
      {label:"餐位序号",name:"set_no",hide:"all"},
      {label:"座位名称",name:"balcony_name"},
      {label:"订单类型",name:"order_type",hide:"form",xtype:"combo",code:"ORDER_TYPE"},
      {label:"来宾人数",name:"man_count"},
      {label:"最低消费类型",name:"mincost_type",hide:"form",xtype:"combo",code:"MINCOST_TYPE"},
      {label:"最低消费金额",name:"mincost_money"},
      {label:"操作时间",name:"operate_order_time",xtype:"datetime",width:160},
      {label:"预抵时间",name:"prearrange_order_time",xtype:"datetime",width:160},
      {label:"下单时间",name:"order_time",xtype:"datetime",width:160},
      {label:"取消时间",name:"can_order_time",xtype:"datetime",width:160},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS"},
      {label:"预定人名字",name:"prearrange_name"},
      {label:"预定人电话号码",name:"prearrange_phone"},
      {label:"预定人数",name:"prearrange_man_count"},
      {label:"预定VIP卡号",name:"vip_card_no"},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form"},
      {label:"预定记录人",name:"record_staff_id"},
      {label:"订餐人地址",name:"prearrange_addr"} 
   ];
//订单编辑域
var orderFields1=[
      {label:"订单号",name:"order_no"},
      {label:"餐位序号",name:"set_no"},
      {label:"服务员",name:"service_staff_id",hide:"all"},
      {label:"服务员",name:"staff_name"},
      {label:"来宾人数",name:"man_count"},
      {label:"订单时间",name:"order_time",xtype:"datetime"},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS"},
      {label:"折扣",name:"order_discount",hide:"grid"},
      {label:"折扣原因",name:"order_discount_reseon",hide:"grid"},
      {label:"是否已经拿发票",name:"has_invoice",xtype:"combo",code:"STATUS",allowBlank:false},
      {label:"订单类型",name:"order_type",xtype:"combo",code:"ORDER_TYPE"},
      {label:"预定人名字",name:"prearrange_name"},
      {label:"预定人电话号码",name:"prearrange_phone"},
      {label:"预定人数",name:"prearrange_man_count"},
      {label:"预定VIP卡号",name:"vip_card_no"},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form"},
      {label:"订餐人地址",name:"prearrange_addr"}
   ];
   
var outOrderFields=[
      {label:"服务员",name:"service_staff_id",hide:"all"},
      {label:"送餐员",name:"staff_name",allowBlank:false,readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields,map:{staff_id:"service_staff_id"}},
      {label:"几份饭",name:"man_count",allowBlank:false},
      {label:"送餐时间",name:"order_time",xtype:"datetime",allowBlank:false},
      {label:"订单状态",name:"order_status",hide:"form"},
      {label:"是否要发票",name:"has_invoice",xtype:"combo",code:"IS_OR_NOT",value:'0',allowBlank:false},
      {label:"订餐人名字",name:"prearrange_name",allowBlank:false},
      {label:"订餐人电话",name:"prearrange_phone",allowBlank:false},
      {label:"VIP卡号",name:"vip_card_no"},
      {label:"订单类型",name:"order_type",hide:"form"},
      {label:"订单状态",name:"order_status",hide:"form"},
      {label:"送餐地址",name:"prearrange_addr",allowBlank:false}
   ];   
//订单明细列表域   
var orderDetailFields=[
      {label:"订单标识",name:"order_id",hide:"all"},
      {label:"菜品ID",name:"food_id",hide:"all"},
      {label:"菜品名称",name:"food_name",sortable:true},
      {label:"当前价格",name:"food_price",width:60,sortable:true},
      {label:"数量",name:"food_count",width:60},
      {label:"员工姓名",name:"modify_staff_name",sortable:true},
      {label:"菜品动作",name:"food_action",code:"FOOD_ACTION",sortable:true},
      {label:"上菜标识",name:"serving_flag",code:"SERVING_FLAG",sortable:true},
      {label:"特殊要求",name:"food_memo"},
      {label:"退菜原因",name:"food_return_reseon"},
      {label:"退菜原因类型",name:"food_return_type"},
      {label:"折扣",name:"food_discount"},
      {label:"折扣原因",name:"food_discount_reseon"},
      {label:"订单明细ID",name:"order_list_id",hide:"all"},
      //{label:"最受一次修改人",name:"modify_staff_id"},
      
      {label:"旧菜品ID",name:"old_food_id",hide:"all"}
   ];
   
//客户预定订单编辑域
var preOrderFields=[
      {label:"订单号",name:"order_no",hide:"form",width:150},
      {label:"订单号",name:"order_id",hide:"all"},
     　{label:"预定人姓名",name:"prearrange_name"},
      {label:"联系电话",name:"prearrange_phone"},
     　{label:"预定人数",name:"prearrange_man_count"},
     　{label:"VIP卡号",name:"vip_card_no"},
     　{label:"订餐人地址",name:"prearrange_addr",hide:"all"},
      {label:"服务生工号",name:"staff_id",hide:"all"},
     　{label:"操作员",name:"staff_name",allowBlank:false,readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields},
      {label:"订单类型",name:"order_type",xtype:"combo",code:"PREARRANGE_ORDER_TYPE"}, 
      {label:"操作时间",name:"operate_order_time",width:160,readOnly:true,value:new Date().dateFormat("Y-m-d H:i:s")},
      {label:"预抵时间",name:"prearrange_order_time",readOnly:true,xtype:"datetime",width:160 },  
      {label:"预定座位",name:"set_no",hide:"form"},
      {label:"预定座位",name:"balcony_name",xtype:"trigger",tableURL:"/shop/free_set2.do",getData:function() {return {P_time:getFormValues("app_business_order_pre_order_win_form",["prearrange_order_time"]).prearrange_order_time};}
      ,columns:setFields},
      {label:"最低消费类型",name:"mincost_type",width:80,xtype:"combo",code:"MINCOST_TYPE",readOnly:true},
    	{label:"最低消费金额",name:"mincost_money",width:80,readOnly:true},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS"},
//      {label:"来宾人数",name:"man_count"},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form"},
      {label:"到达预抵时间后自动释放(分)",name:"overdue_time",width:100,xtype:"number",emptyText:10}
      
   ];
//加菜编辑辑域   
var addFoodFields=[
      {label:"订单标识",name:"order_id",hide:"all"},
      {label:"菜品ID",name:"food_id",hide:"all"},
      {label:"菜品名称",name:"food_name",readOnly:true,xtype:"trigger",table:"food_info",columns:foodFields},
      {label:"数量",name:"food_count"},
      {label:"订单明细ID",name:"order_list_id",hide:"all"},
      {label:"当前价格",name:"food_price",width:60},
      {label:"菜品明细标识",name:"order_list_id",hide:"all"},
      {label:"特殊要求",name:"food_memo"},
      {label:"菜品明细序号",name:"food_seq"}
   ];
   
/**
 * 订单事件
 */   
function createOrder(urls,data,itms){
  var w=createFormWindow({cols:1,id:"app_business_order_pre_order",
     label:"客户预定",action:urls,items:(itms)?itms:preOrderFields,
     width:500,height:500
     });
  w.show();
  getCmp("app_business_order_pre_order_win_form").getForm().setValues(data);
  w.on("beforedestroy",function(){reload("app_business_preorder_write_3");});
}

function createOrderList(urls,flds,data,tit){
  var w=createFormWindow({cols:1,id:"app_business_order_pre_order",label:tit,action:urls,items:flds});
  w.show();
  getCmp("app_business_order_pre_order_win_form").getForm().setValues(data);
  w.on("beforedestroy",function(){reload("app_business_preorder_write_2222");});
}

//加菜操作
var addFoodEvent=function(){
   createOrderList("/order/order_addfood.do",addFoodFields,getData("app_business_preorder_write_3",["order_id"]),"客户加菜");
}

//取消操作
var cancelFoodEvent=function(){
   doPost("/order/order_cancelfood.do",getData("app_business_preorder_write_2222",["order_list_id"]),function(dt){
       reload("app_business_preorder_write_2222");
   });
}

//退菜操作
var returnFoodEvent=function(){
   var flds=[
      {label:"退菜原因类型",name:"food_return_type",xtype:"combo",code:"FOOD_RETURN_TYPE"},
      {label:"退菜原因",name:"food_return_reseon",xtype:"textarea"},
      {label:"订单明细ID",name:"order_list_id",hide:"form"}
   ];
   
   createOrderList("/order/order_returnfood.do",flds,getData("app_business_preorder_write_2222"),"退菜")
}
//上菜操作
var servFoodEvent=function(){
   doPost("/order/order_servfood.do",getDataArray("app_business_preorder_write_2222",["order_list_id"]),function(dt){
       reload("app_business_preorder_write_2222");
   });
}
   
//预定操作   
var preOrderEvent=function(){
    var dt={order_status:"2",order_type:"2"};
    createOrder("/order/order.do",dt);
};
//订单下单操作
var orderEvent=function(){
    var dt={order_status:"0",order_type:"0"};
    createOrder("/order/order.do",dt);
};

//电话预订
var telOrderEvent=function(){
    var dt={order_status:"2",order_type:"2"};
    createOrder("/order/order.do",dt);
};

//外卖
var outOrderEvent=function(){
    var dt={order_status:"0",order_type:"4"};
  var w=createFormWindow({cols:1,id:"app_business_order_pre_order",
     label:"外卖单",action:"/order/order.do",items:outOrderFields,
     width:500,height:400
     });
  w.show();
  getCmp("app_business_order_pre_order_win_form").getForm().setValues(dt);
  w.on("beforedestroy",function(){reload("app_business_preorder_write_3");});
};

//订单开单
var orderOpenEvent=function(d){

   var sdt=getData("app_business_preorder_write_3");
   if(!(sdt["order_status"]=="2")){
     $alt("请选择一个预定订单进行开单.");
     return;
   }
   
   var grid=createPageGrid({
      id       : "preorder_open_grid",
      title    : "餐位列表",
      items    : [{label:"餐位号",name:"set_no",hide:"all"},
                  {label:"餐位编号",name:"balcony_code"},
                  {label:"预定人数",name:"man_num",width:60},
                  {label:"当前最大座位数",name:"set_max",width:80},
                  {label:"楼层",name:"dining_floor",code:"DINING_FLOOR",width:60},
                  {label:"餐位名称",name:"balcony_name"}],
      region   : "center",
      urls     : "/shop/free_set3.do?set_no="+sdt["set_no"],
      filter   : false
   });
   
   var win=new Ext.Window({
      title     : "客户开单",
      items     : [grid],
      height    : 300,
      width     : 400,
      layout    : "border",
      bodyBorder: false,
      border    : false
   
   });
   
   win.addButton({text:"确定",handler:function(){
      var postData=Ext.apply(d,grid.getSelectionModel().getSelected().data);
      
      doPost("/order/customer_open.do",postData,function(e){
         reload("app_business_preorder_write_3"); 
         win.destroy();
      })
   }});
   
   win.addButton({text:"关闭",handler:function(){
      win.destroy();
   }});
   
   win.show();
   
};

var orderOpenEvent1=function(d){

   var sdt=getData("app_business_preorder_write_3");
   
   if(!(sdt["order_status"]=="2")){
     $alt("请选择一个预定订单进行开单.");
     return;
   }
　  var preOrderField12=[
      {label:"订单号",name:"order_no",width:150,hide:"form",value:sdt["order_no"]},
      {label:"订单号",name:"order_id",hide:"all",value:sdt["order_id"]},
      {label:"操作员",name:"staff_id",allowBlank:false,hide:"form"},
     　{label:"操作员",name:"staff_name",allowBlank:false,readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields},
      {label:"预定人姓名",name:"prearrange_name",value:sdt["prearrange_name"]},
      {label:"联系电话",name:"prearrange_phone",value:sdt["prearrange_phone"]},
     　{label:"预定人数",name:"prearrange_man_count",value:sdt["prearrange_man_count"]},
     　{label:"VIP卡号",name:"vip_card_no",value:sdt["vip_card_no"]},
     　{label:"订餐人地址",name:"prearrange_addr",hide:"all",value:sdt["prearrange_addr"],value:sdt["prearrange_name"]},
 //    　{label:"预定记录人",name:"record_staff_id",value:sdt["record_staff_id"]},
 	  {label:"订单类型",name:"order_type",code:"PREARRANGE_ORDER_TYPE",value:sdt["order_type"],hide:"form"}, 
      {label:"订单类型",name:"order_type_text",code:"PREARRANGE_ORDER_TYPE",value:sdt["order_type"]}, 
      {label:"操作时间",name:"operate_order_time",width:160,readOnly:true,value:sdt["operate_order_time"]},
      {label:"预抵时间",name:"prearrange_order_time",width:160 ,value:sdt["prearrange_order_time"]},
      {label:"下单时间",name:"order_time",allowBlank:false,xtype:"datetime",width:160,readOnly:true},
      {label:"预定座位",name:"set_no",value:sdt["set_no"],hide:"form"},
      {label:"预定座位",name:"balcony_name",id:"b_n",value:sdt["balcony_name"]},
      {label:"最低消费类型",name:"mincost_type",width:80,xtype:"combo",code:"MINCOST_TYPE",readOnly:true},
    	{label:"最低消费金额",name:"mincost_money",width:80,readOnly:true},
      {label:"订单状态",name:"order_status",hide:"form",code:"ORDER_STATUS",value:sdt["prearrange_name"]},
      {label:"来宾人数",name:"man_count",value:sdt["man_count"]},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form",value:sdt["hurry_times"]}
  
   ];
  var w=createFormWindow({cols:1,id:"app_business_order_pre_order11",
     label:"客户开单",action:"/order/customer_open.do",items:preOrderField12,
     width:500,height:500,data:sdt

     });
  w.show();
  getCmp("app_business_order_pre_order11_win_form").getForm().setValues({order_status:"0"});
  w.on("beforedestroy",function(){reload("app_business_preorder_write_3");});
   
};

var orderOpenEvent3=function(d){

   var sdt=getData("app_business_preorder_write_3");
   
   if(!(sdt["order_status"]=="2" || sdt["order_status"]=="0")){
     $alt("请选择一个预定订单进行修改.");
     return;
   }
　  var preOrderField12=[
      {label:"订单号",name:"order_no",width:150,hide:"form",value:sdt["order_no"]},
      {label:"订单号",name:"order_id",hide:"all",value:sdt["order_id"]},
      {label:"操作员",name:"staff_id",allowBlank:false,hide:"form"},
     　{label:"操作员",name:"staff_name",allowBlank:false,readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields},
      {label:"预定人姓名",name:"prearrange_name",value:sdt["prearrange_name"]},
      {label:"联系电话",name:"prearrange_phone",value:sdt["prearrange_phone"]},
     　{label:"预定人数",name:"prearrange_man_count",value:sdt["prearrange_man_count"]},
     　{label:"VIP卡号",name:"vip_card_no",value:sdt["vip_card_no"]},
     　{label:"订餐人地址",name:"prearrange_addr",hide:"all",value:sdt["prearrange_addr"],value:sdt["prearrange_name"]},
 //    　{label:"预定记录人",name:"record_staff_id",value:sdt["record_staff_id"]},
      {label:"订单类型",name:"order_type",code:"PREARRANGE_ORDER_TYPE",value:sdt["order_type"]}, 
      {label:"操作时间",name:"operate_order_time",width:160,readOnly:true,value:sdt["operate_order_time"]},
      {label:"预抵时间",name:"prearrange_order_time",width:160 ,value:sdt["prearrange_order_time"],readOnly:!equals(sdt,"order_status","2")},
      {label:"下单时间",name:"order_time",allowBlank:false,xtype:"datetime",width:160,readOnly:true},
      {label:"预定座位",name:"set_no",value:sdt["set_no"],hide:"form"},
      {label:"预定座位",name:"balcony_name",xtype:"trigger",tableURL:"/shop/free_set2.do",getData:function() {return {P_time:getFormValues("app_business_order_pre_order1111_win_form",["prearrange_order_time"]).prearrange_order_time};},columns:setFields},
      {label:"最低消费类型",name:"mincost_type",width:80,xtype:"combo",code:"MINCOST_TYPE",readOnly:true},
    	{label:"最低消费金额",name:"mincost_money",width:80,readOnly:true},
      {label:"订单状态",name:"order_status",hide:"form",code:"ORDER_STATUS",value:sdt["prearrange_name"]},
      {label:"来宾人数",name:"man_count",value:sdt["man_count"]},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form",value:sdt["hurry_times"]}
  
   ];
  var w=createFormWindow({cols:1,id:"app_business_order_pre_order1111",
     label:"订单修改",action:"/order/customer_edit.do",items:preOrderField12,
     width:500,height:500,data:sdt

     });
  w.show();
  getCmp("app_business_order_pre_order1111_win_form").getForm().setValues({order_status:"0"});
  w.on("beforedestroy",function(){reload("app_business_preorder_write_3");});
};

//客户退单
var orderCancelEvent=function(){
   var sdt=getData("app_business_preorder_write_3");
   if(!(sdt["order_status"]=="2")){
     $alt("请选择一个预定订单进行退单.");
     return;
   }
    
   var dt={order_id:sdt["order_id"],order_status:"3"};
   doPost("/order_info.modify.go",dt,function(o){
      reload("app_business_preorder_write_3");
   });
   
};  

var orderCancelEvent1=function(d){

   var sdt=getData("app_business_preorder_write_3");
   if(!(sdt["order_status"]=="2")){
     $alt("请选择一个预定订单进行退单.");
     return;
   }
   sdt["order_status"] ="3";
   sdt["can_order_time"] =new Date().dateFormat("Y-m-d H:i:s");
　  var canOrderField12=[
      {label:"订单号",name:"order_no",width:150,hide:"form",value:sdt["order_no"]},
      {label:"订单号",name:"order_id",hide:"all",value:sdt["order_id"]},
      {label:"操作员",name:"staff_id",allowBlank:false,hide:"form"},
     　{label:"操作员",name:"staff_name",readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields},
//      {label:"取消时间",name:"can_order_time",width:160,readOnly:true,hide:true},
      {label:"取消原因",name:"order_reseon1",width:160,xtype:"combo",readOnly:true,code:"ORDER_RESEON"},
      {label:"取消原因",name:"order_reseon2",width:160,xtype:"textarea"},
      {label:"订单状态",name:"order_status",width:160,readOnly:true,hide:true}
  
   ];
  var w=createFormWindow({cols:1,id:"app_business_order_can_order11",
     label:"客户退单",action:"/order/order_info_modify.do",items:canOrderField12,
     width:500,height:200,data:sdt

     });
  w.show();
 
  w.on("beforedestroy",function(){reload("app_business_preorder_write_3");});
   
};

//菜单打印
var foodPrintEvent=function(){
};

//酒水单打印
var drinkPrintEvent=function(){};

//外卖单打印
var outPrintEvent=function(){};
//客户结账
var orderPayEvent=function(){
    var dt=getData("app_business_preorder_write_3");
    var dts = [getData("app_business_preorder_write_3",["order_no"]).order_no,getData("app_business_preorder_write_3",["mincost_money"]).mincost_money,getData("app_business_preorder_write_3",["mincost_type"]).mincost_type,getData("app_business_preorder_write_3",["man_count"]).man_count];
    var w=cCalculatePanel(dts,cLoadEvent("app_business_preorder_write_3"));
    w.show(this);
};



/** 订单事件结束 **/ 

//订单信息显示
var orderForm= createFormPanel({
   id:"app_business_preorder_write_1111",
   title:"订单信息",
   region:"north",
   editable:true,
   height:300,
   cols : 1,
   items:orderFields1
});

function isDisabled(dt,flagAction,servingFlag){
    var b=true;
    if(flagAction && flagAction!=null) b =b && dt["food_action"]==flagAction;
    if(servingFlag) b =b && dt["serving_flag"]==servingFlag;
    return b
}

function isDisabled11(dt,flagAction,servingFlag,dt3){
    var b=true;
    if(dt3["order_status"] == 2 || dt3["order_status"] == 1)
    {
    	b=false;
    }
    else
    {
	    if(flagAction && flagAction!=null) b =b && dt["food_action"]==flagAction;
	    if(servingFlag) b =b && dt["serving_flag"]==servingFlag;
	  }
    return b
}

function isDisabled22(dt,flagAction,servingFlag,dt3){
    var b=true;
    if(dt3["order_status"] == 1 || dt3["order_status"] == 3 || dt3["order_status"] == 4)
    {
    	b=false;
    }
    else
    {
	    if(flagAction && flagAction!=null) b =b && dt["food_action"]==flagAction;
	    if(servingFlag) b =b && dt["serving_flag"]==servingFlag;
	  }
    return b
}

//订单明细列表
var orderDetailList=createPageGrid({
       	   title: "订单明细",
       	   id:"app_business_preorder_write_2222",
       	   urls:"/order/order_list.do",
           split:true,
           //page:true,
           height:500,
           width:500,
           filter:false,
           region:"center",
           checkbox:true,
           singleSelect:false, 
       	   items:orderDetailFields,
       	   
       	   bbar:["换菜的业务逻辑是：退菜，然后点菜.折扣按照百分比填写(比如:八五折就填写85)",{style:"padding:5px"}]
   });




/***
 * 订单查询条件定义
 */
var toolBars=["查询条件","&nbsp;","-",
						  createField({name:"query_type",xtype:"combo",code:"QUERY_TYPE",width:80}),
       	                  createField({name:"query_text",width:100}),
       	                  "&nbsp;",
       	                  "开始时间","&nbsp;","-",
       	                  createField({name:"starttime",xtype:"date",width:85}),
       	                  "&nbsp;",
       	                  "截至时间","&nbsp;","-",
       	                  createField({name:"endtime",xtype:"date",width:85}),
       	                  "&nbsp;",
       	                  createField({name:"order_type",xtype:"combo",code:"ORDER_TYPE",width:80}),
       	                  "&nbsp;",
       	                  createField({name:"order_status",xtype:"combo",code:"ORDER_STATUS",width:80}),
       	                  "&nbsp;",
       	                  createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})
       	                  
       	                  ];
   

function goClick(){
   var dt={};

   for(var i=0;i< toolBars.length;i++){
   	   var o=toolBars[i];
  
   	   if(typeof o == "object"){
   	       if(o.getName && o.getValue){
   	       
   	          dt[o.getName()]=o.getValue();
   	          
   	       }
   	   }
   	}

   	if(dt["query_type"] == "0"){
   		dt["balcony_name"] = dt["query_text"];

   	}
   	if(dt["query_type"] == "1"){
   		dt["prearrange_name"] = dt["query_text"];

   	}
   	if(dt["query_type"] == "2"){
   		dt["prearrange_phone"] = dt["query_text"];

   	}
   	if(dt["query_type"] == "3"){
   		dt["can_order_time"] = dt["query_text"];

   	}
   	if(dt["query_type"] == "4"){
   		dt["order_time"] = dt["query_text"];

   	}
   	
    
   load("app_business_preorder_write_32",dt);
  // 	orderList.getStore().baseParams=dt;
 //  	orderList.getStore().load();
}



var orderList=createPageGrid({
       	   title: "订单列表(预定和未结帐状态的订单)",
       	   id:"app_business_preorder_write_32",
       	   urls:"/order/order_pages.do",
           split:true,
           page:true,
           height:200,
           width:500,
           filter:false,
           
           region:"west", 
       	   items:orderFields,
       	   
       	   toolBarFields:toolBars,
       	   rowclick:function(){
       	      var dt={};
       	      getCmp("app_business_preorder_write_1111").getForm().setValues(getSelectedData("app_business_preorder_write_32"));
       	      
       	      dt["order_id"]=getSelectedData("app_business_preorder_write_32")["order_id"];
       	      
       	      getCmp("app_business_preorder_write_2222").getStore().baseParams=dt;
       	      
       	      reload("app_business_preorder_write_2222");
       	      
       	   }
   });



return [orderList,{region:"center",bodyBorder:false,width:400,layout:"border",items:[orderForm,orderDetailList]}];