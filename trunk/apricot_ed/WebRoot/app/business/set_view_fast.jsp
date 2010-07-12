var mFields=[
  {label:"菜品ID",name:"food_id",hidden:true,hide:true},
  {label:"菜名",name:"food_name"},
  {label:"菜品类型",  name:"food_type",xtype:"combo",code:"FOOD_TYPE"},
  {label:"价格",  name:"food_price"}
];
var orderForm11=createPageGrid({
    title     : "菜单",
    id        : "app_business_food_menu",
    urls       : "/food_info.pages.go",
    cols      : 2,
    region    : "north",
    height    : 240,
    labelWidth : 60,
    width     : 260,
    items     : mFields,
    rowclick  : isSelectOrder,
    filter     : true,
    toolBarFields:[{text:"text"}]
});

function isSelectOrder()
{
	var orderInfo = getCmp("calculate_panel_fast_a").getForm();
	var orderNo = orderInfo.getValues("order_no").order_no;
	var orderId = orderInfo.getValues("order_no").order_id;
	if(orderId == "")
	{
		alert("请先选择订单！");
	}
	else
	{
		var d=getData("app_business_food_menu");
		d.food_num = 1;
		d.order_id = orderId;
		d.selected = true;
		var store = getCmp("app_business_set_view_order").getStore();
		store.add(new FoodRecord(d));
	}
}
function loadMenu(orderNo){
   load("calculate_panel_fast_b1",{order_no:orderNo});
   load("calculate_panel_fast_c1",{order_no:orderNo});
   load("calculate_panel_fast_d1",{order_no:orderNo});
   load("calculate_panel_fast_e1",{order_no:orderNo});
   load("calculate_panel_fast_f1",{order_no:orderNo});
}
var orderFields=[
	{label:"菜品ID",name:"food_id",hidden:true,hide:true},
	{label:"菜名",name:"food_name"},
	{label:"价格",  name:"food_price",xtype :"number",width :60},
	{label:"数量",  name:"food_num",xtype :"number",width :60}
];
var FoodRecord = Ext.data.Record.create( [
{
	name :'order_id',
	mapping :'order_id',
	hideMode:"display",
   	hidden:true
},{
	name :'food_id',
	mapping :'food_id'
}, {
	name :'food_name',
	mapping :'food_name'
}, {
	name :'food_num',
	mapping :'food_num'
}, {
	name :'food_price',
	mapping :'food_price'
}, {
	name :'selected',
	mapping :'selected'
} ]);
var orderDetailForm = cEditGrid( {
		title :"菜品列表",
		items :orderFields,
		region :"center",
		firstLoad:false,
		id :"app_business_set_view_order",
		width :200,
		filter :false,
		split :true,
		region:"center",
		tbarDisable :true,
		check :true,
		buttons: [{
			text: '确定',
			handler:function(){
				if(confirm("确认?"))
				{
					var  al=new Array();
					var foodList=getCmp("app_business_set_view_order").getStore().query("selected","true");
					for(var i = 0;i < foodList.length;i ++)
					{
						var o = foodList.item(i).data;
						al.push(foodList.item(i).data);
					}
					doGet("/order/customer_order_food.do",al,function(date){
						var store = getCmp("app_business_set_view_order").getStore();
						store.removeAll();
						var ddd=getCmp("calculate_panel_fast_a");
						loadMenu(ddd.getForm().getValues("order_no").order_no);
//						var dd=getCmp("calculate_panel_fast_a").getForm();
//						var dts = [dd.getValues("order_no").order_no,dd.getValues("order_no").mincost_money,dd.getValues("order_no").mincost_type,dd.getValues("order_no").man_count,dd.getValues("order_no").cum_point];
//						var calpanel=cCalculatePanelFast(dts,cLoadEvent("app_business_set_view_order"));
					})
				}
				else
				{
					return;
				}
			}
		},{
			text:'重置',
			handler:function(){
				var store = getCmp("app_business_set_view_order").getStore();
				store.removeAll();
			}
		}]
	});
 var detailView={ 
   region        : "east",
   split         : true,
   bodyBorder    : false,
   width         : 260,
   layout        : "border",
   items         : [orderForm11,orderDetailForm]
};
var dd=getData("app_business_set_view_order");
var dts = [getData("app_business_set_view_order",["order_no"]).order_no,getData("app_business_set_view_order",["mincost_money"]).mincost_money,getData("app_business_set_view_order",["mincost_type"]).mincost_type,getData("app_business_set_view_order",["man_count"]).man_count,getData("app_business_set_view_order",["cum_point"]).cum_point];
var calpanel=cCalculatePanelFast(dts,cLoadEvent("app_business_set_view_order"));
 
 return [detailView,calpanel];