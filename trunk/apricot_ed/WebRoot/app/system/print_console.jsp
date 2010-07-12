 var orderFields=[
{label:"订单号",name:"order_no",hide:"form",width:120},
{label:"订单号",name:"order_id",hide:"all"},
{label:"餐位序号",name:"set_no",hide:"all"},
{label:"座位名称",name:"balcony_name",width:80},
{label:"订单类型",name:"order_type_text"},
{label:"来宾人数",name:"man_count",width:60},
{label:"下单时间",name:"order_time",xtype:"datetime",width:110} ]; 
var foodFields=[
{label:"菜品ID",name:"food_id",hide:"all"},
{label:"菜品名称",name:"food_name"}, {label:"菜品单价",name:"food_price"},
{label:"菜品类型", name:"food_type_text",xtype:"combo",code:"FOOD_TYPE"},
{label:"点菜动作", name:"food_action_text",xtype:"combo",code:"FOOD_TYPE"} ];

var orderList=createPageGrid({
       	   title: "订单列表",
       	   id:"app_business_print_order",
       	   urls:"/order/order_getprintorder.do",
           region:"center",
           firstLoad:true,
           filter:false,
           page:true,
       	   items:orderFields,
       	   rowclick:function(){
       	   	   var o=getData("app_business_print_order",["order_id"]);
       	   	   load("app_business_print_order_detail",o);
       	   }

   });
   
var orderDetail=createPageGrid({
	       title: "订单明细",
       	   id:"app_business_print_order_detail",
       	   urls:"/order/order_getprintorderdetail.do",
           region:"east",
           firstLoad:false,
           filter:false,
             split:true,
       	   items:foodFields,
       	   width:400,
       	   page:false
       	   
       	   
	
}); 

var buttons=new Ext.Panel({buttonAlign:"center",border:false,region:"south",height:0});

buttons.addButton({text:"开启自动刷新"});  

return [orderList,orderDetail];


