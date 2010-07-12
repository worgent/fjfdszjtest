//订单列表域
var orderFields=[
      {label:"订单号",name:"order_no",hide:"form",width:140},
      {label:"订单号",name:"order_id",hide:"all"},
      {label:"餐位编号",name:"set_no",width:80},
      {label:"餐位名称",name:"balcony_name",width:80},
      {label:"服务员",name:"service_staff_id",hide:"all"},
//      {label:"服务员",name:"staff_name"},
      {label:"订单类型",name:"order_type",hide:"form",xtype:"combo",code:"ORDER_TYPE"},
      {label:"预定人数",name:"prearrange_man_count"},
      {label:"来宾人数",name:"man_count"},
      {label:"订单时间",name:"order_time",xtype:"datetime",width:160},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS"},
//      {label:"折扣",name:"order_discount",hide:"grid"},
//      {label:"折扣原因",name:"order_discount_reseon",hide:"grid"},
//      {label:"是否已经拿发票",name:"has_invoice",xtype:"combo",code:"IS_OR_NOT",hide:"all"},
      {label:"宾客姓名",name:"prearrange_name"},
      {label:"联系电话",name:"prearrange_phone"},
      {label:"VIP卡号",name:"vip_card_no"},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form"},
      {label:"订餐人地址",name:"prearrange_addr"}
   ];

var orderList=createPageGrid({
       	   title: "订单列表",
       	   id:"app_business_order_union_3",
       	   urls:"/order/order_getonorder.do",
           region:"center",
           firstLoad:false,
           filter:false,
       	   items:orderFields,
       	   checkbox:true,
       	   toolBarFields:[
       	      "查询条件","&nbsp;","-","&nbsp;",
       	      {xtype:"trigger",width:300,triggerClass:"x-form-search-trigger",
       	       emptyText:"输入餐位编号查询(逗号,隔开)...",
       	       onTriggerClick:function(e){
       	          load("app_business_order_union_3",{set_no:this.getValue()});
       	       }
       	       
       	       },
       	       "&nbsp",
       	       {xtype:"tbbutton",pressed:true,text:"合并订单",iconCls:"gridBarItemUnion",
       	        handler:function(e){
       	             var dt=getDataArray("app_business_order_union_3",["order_id"]);
       	             if(dt.length>1){
       	                $alt("至少选择2张订单合并.");
       	             }
       	             doPost("/order/order_union.do",dt,function(e){reload("app_business_order_union_3")});
       	          }
       	       }
       	   ]
   });


return [orderList];