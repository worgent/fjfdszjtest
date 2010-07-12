var setFields=[
  {label:"座位号",name:"set_no",hide:"all",readOnly:true},
  {label:"楼层",name:"dining_floor",readOnly:true},
  {label:"所属位置",name:"belong_to_text",readOnly:true},
  {label:"座位名称",name:"balcony_name",readOnly:true},
  {label:"座位状态",name:"man_num",readOnly:true},
  {label:"最低消费类型",name:"mincost_type",xtype:"combo",readOnly:true,code:"MINCOST_TYPE"},
  {label:"最低消费金额",name:"mincost_money",readOnly:true}
];
var staffFields=[
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"}
];
//点菜员
 var prme=[
 {label:"操作员",name:"staff_name",readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields}
 ];  
var orderFileds=[
  {label:"座位号",name:"set_no",readOnly:true},
  {label:"楼层",name:"dining_floor",readOnly:true},
  {label:"座位名称",name:"balcony_name",readOnly:true},
  {label:"所属位置",name:"belong_to_text",readOnly:true},
  {label:"订单号",name:"order_no",readOnly:true},
  {label:"订单号",name:"order_id",hide:"all"},
  {label:"宾客姓名",name:"prearrange_name",readOnly:true},
  {label:"联系电话",name:"prearrange_phone"},
  {label:"预定人数",name:"prearrange_man_count"},
  {label:"来宾人数",name:"man_count"},
  {label:"推荐人",name:"introducer"},
  {label:"订单时间",name:"order_time",readOnly:true},
  {label:"VIP号码",name:"vip_card_no",readOnly:true},
  {label:"积分",name:"cum_point",readOnly:true}
];
var orderDetailFields=[
  {label:"菜品名称",name:"food_name",sortable:true},
  {label:"数量",name:"food_count",width:50,sortable:true},
  {label:"菜品标志",name:"food_action",width:60,sortable:true,code:"FOOD_ACTION"},
  {label:"是否上菜",name:"serving_flag",width:60,sortable:true,code:"SERVING_FLAG"},
  {label:"当前价格",name:"food_price",width:60,sortable:true},
  {label:"口味备注",name:"food_memo",sortable:true},
  {label:"员工姓名",name:"modify_staff_name",sortable:true}
];

var staffFields=[
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"}
];
var orderPayFields=[
  {label:"收费项",name:"fee_item",width:200},
  {label:"收费值",name:"fee_value"}
];
//点菜员工
function createMer(urls){
	 var w=createFormWindow({cols:1,id:"app_business_order_pre_mer",
     label:"点菜员工",action:urls,items:prme,
     width:500,height:200
     });
  w.show();
  w.on("beforedestroy",function(){reload("app_business_set_view_order_detail");});
}
function createStore(bt){
var store = new Ext.data.JsonStore({
    url: toURL('/business/set.images.do')+"?belong_to="+bt,
    root: 'images',
    fields: ['name', 'url',"set_no","set_st","order_no","order_id","belong_to",'mincost_type','mincost_money','balcony_name','staff_id','staff_name'],
    autoLoad:true
});
return store;
}
var tpl = new Ext.XTemplate(
		'<tpl for=".">',
            '<div class="thumb-wrap" id="{name}" no="{set_no}" set_st="{set_st}">',
		    '<div class="thumb"><img src="{url}" title="{name}"></div>',
		    '<span class="x-editable">{name}</span></div>',
        '</tpl>',
        '<div class="x-clear"></div>'
	);

function dataViewClick(dv,i,el,e){
   var d={set_no:el.no};
   getCmp("app_business_set_view_order").getForm().reset();
   removeAll("app_business_set_view_order_detail");
   if(!(el.set_st=="set.busy")){ 
      return;
   } 
   
   doGet("/set/set_order_busy.do",d,function(data){
                                       load("app_business_set_view_order_detail",data);
                                       getCmp("app_business_set_view_order").getForm().setValues(data);
                                       
                                    });
}

/**
 * 客户点菜
 */
 function orderFood(da){
      var ods=new Array();
      var tit=new Array();
    
      for(var i=0;i< da.length;i++){
          var o=da[i].data;
          ods.push(o.order_id);
          //alert(urlEncode(o));
          tit.push("订单编号:");
          tit.push(o.order_no);
          tit.push("(座位号:");
          tit.push(o.belong_to);
          tit.push(")");
          tit.push(";");
      }
      
      tit.pop();
      

      var items=createOrderFoodPanel(ods);
      
      var win=new Ext.Window({
          title   : "客户点菜["+tit.join("")+"]",
          items   : items,
          width   : 700,
          height  : 500,

          layout  : "fit",
          frame   : true,
          border  :false,
          bodyBorder:false,
          modal   : true
      });
      
      win.addButton({text:"确定",handler:function(){
      											doGet("/order/order_a.do",null,function(dt){
      											    var oo=items.getData();
                                                     doPostByXML("/order/customer_food.do",oo,function(e){
                                                         if(e.success=="true"){
                                                            $alt("客户点菜完成");
                                                            load("app_business_set_view_order_detail",da[0].data);
                                                            
                                                            createMer("/order/order_b.do?m="+dt["max"]+"&id="+oo.order_id.join(",")+"");
                                                            win.close(); 
                                                         }
                                                     });
                                                   });
                                                   }
      });
      win.addButton({text:"关闭",handler:function(){win.close();}});
      win.show();
 }

 /**
  * 客户催菜
  */
  function customerReminderFood(d){
	var foodlist_fields = [
			{
			label :"菜单明细ID",
			name :"order_list_id",
			hide :"all"
		},
		{
			label :"菜品ID",
			name :"food_id",
			hide :"all"
		}, {
			label :"菜品名称",
			name :"food_name"
		}, {
			label :"菜品单价",
			name :"food_price",
			width :60
		}, {
			label :"是否上菜",
			name :"serving_flag",
			width :60
		}, {
			label :"菜品数量",
			name :"food_num",
			width :60
		} ];
	
	

	// 菜品明细列表
	var foodlist = cEditGrid( {
		title :"菜品列表",
		items :foodlist_fields,
		region :"center",
		id :"food_list_list",
		width :300,
		filter :false,
		region:"center",
		tbarDisable :true,
		check :true,
		rowselect:function(){},
		url   : "/order/food_list.do?order_id="+d.order_id,
		firstLoad:true
	});
	var win=new Ext.Window({
	   items    : [foodlist],
	   layout   : "border",
	   title    : "客户催菜:[订单号:"+d.order_no+"(座位号:"+d.belong_to+")]",
	   width    : 400,
	   height   : 360,
	   modal    : true,
	   bodyBorder:false,
	   border   : false
	});
	
	
	win.addButton({
	   text     : "确定",
	   handler  : function(){
	       var rd={};
	       rd["food_list"]=new Array();
	       
	       var a=getCmp("food_list_list").getStore().query("selected","true");
			
		   for(var i=0;i< a.length;i++){
				rd.food_list.push(a.item(i).data.order_list_id);
		   }
		   rd["order_list_id"]=rd.food_list.join(",");
		   
		   doPostByXML("/order/order_reminderfood.do",rd,function(e){
		       if(e.success=="true"){
		          $alt("客户催菜成功.");
		          load("app_business_set_view_order_detail",d);
		          win.destroy();
		       }
		   
		   });
	   
	   }
	});
	
	win.addButton({
	   text     : "关闭",
	   handler  : function(){win.destroy();}
	});
	win.show();
  }
  
 /**
  * 重新打印订单
  */
  function customerReprintFood(d){
	var foodlist_fields = [
			{
			label :"菜单明细ID",
			name :"order_list_id",
			hide :"all"
		},
		{
			label :"菜品ID",
			name :"food_id",
			hide :"all"
		}, {
			label :"菜品名称",
			name :"food_name"
		}, {
			label :"菜品单价",
			name :"food_price",
			width :60
		}, {
			label :"是否上菜",
			name :"serving_flag",
			width :60
		}, {
			label :"菜品数量",
			name :"food_num",
			width :60
		} ];
	
	

	// 菜品明细列表
	var foodlist = cEditGrid( {
		title :"菜品列表",
		items :foodlist_fields,
		region :"center",
		id :"food_list_list",
		width :300,
		filter :false,
		region:"center",
		tbarDisable :true,
		check :true,
		rowselect:function(){},
		url   : "/order/food_list.do?order_id="+d.order_id,
		firstLoad:true
	});
	var win=new Ext.Window({
	   items    : [foodlist],
	   layout   : "border",
	   title    : "重新打印:[订单号:"+d.order_no+"(座位号:"+d.belong_to+")]",
	   width    : 400,
	   height   : 360,
	   modal    : true,
	   bodyBorder:false,
	   border   : false
	});
	
	
	win.addButton({
	   text     : "确定",
	   handler  : function(){
	       var rd={};
	       rd["food_list"]=new Array();
	       
	       var a=getCmp("food_list_list").getStore().query("selected","true");
			
		   for(var i=0;i< a.length;i++){
				rd.food_list.push(a.item(i).data.order_list_id);
		   }
		   rd["order_list_id"]=rd.food_list.join(",");
		   
		   doPostByXML("/order/order_reprintfood.do",rd,function(e){
		       if(e.success=="true"){
		          $alt("重新打印标识重置成功。");
		          win.destroy();
		       }
		   
		   });
	   
	   }
	});
	
	win.addButton({
	   text     : "关闭",
	   handler  : function(){win.destroy();}
	});
	win.show();
  }
  /**
  * 重新打印客户订单
  */
  function customerReprintCustomerFood(d){
		   doPost("/order/order_reprintcustomerfood.do",d,function(e){
		       if(e.success=="true"){
		          $alt("重新打印客户单标识重置成功。");
		       }
		   
		   });

  }
 /**
  * 客户退菜
  */
  function customerCancelFood(d){
	var foodlist_fields = [
			{
			label :"菜单明细ID",
			name :"order_list_id",
			hide :"all"
		},
		{
			label :"菜品ID",
			name :"food_id",
			hide :"all"
		}, {
			label :"菜品名称",
			name :"food_name"
		}, {
			label :"菜品单价",
			name :"food_price",
			width :60
		}, {
			label :"是否上菜",
			name :"serving_flag",
			width :60
		}, {
			label :"菜品数量",
			name :"food_num",
			width :60
		} ];
	
	var foodForm=createFormPanel({
	    title   : "退菜原因",
	    items   : [{label:"退菜类型",name:"food_return_type",xtype:"combo",code:"FOOD_RETURN_TYPE"},{label:"退菜原因",name:"food_return_reseon"}],
	    region  : "north",
	    height  : 70,
	    id      : "customer_cancel_reson",
	    split   : false
	});	
	

	// 菜品明细列表
	var foodlist = cEditGrid( {
		title :"菜品列表",
		items :foodlist_fields,
		region :"center",
		id :"food_list_list",
		width :300,
		filter :false,
		region:"center",
		tbarDisable :true,
		check :true,
		rowselect:function(){},
		url   : "/order/food_list.do?order_id="+d.order_id,
		firstLoad:true
	});
	var win=new Ext.Window({
	   items    : [foodForm,foodlist],
	   layout   : "border",
	   title    : "客户退菜:[订单号:"+d.order_no+"(座位号:"+d.belong_to+")]",
	   width    : 400,
	   height   : 360,
	   modal    : true,
	   bodyBorder:false,
	   border   : false
	});
	
	
	win.addButton({
	   text     : "确定",
	   handler  : function(){
	       var rd=Ext.apply(foodForm.getForm().getValues(),{});
	       rd["food_list"]=new Array();
	       
	       var a=getCmp("food_list_list").getStore().query("selected","true");
			
		   for(var i=0;i< a.length;i++){
				rd.food_list.push(a.item(i).data);
		   }
		   
		   doPostByXML("/order/customer_cancel_food.do",rd,function(e){
		       if(e.success=="true"){
		          $alt("客户退菜成功.");
		          load("app_business_set_view_order_detail",d);
		          win.destroy();
		       }
		   
		   });
	   
	   }
	});
	
	win.addButton({
	   text     : "关闭",
	   handler  : function(){win.destroy();}
	});
	win.show();
  }
  
  /**
  * 报损
  */
  function customerDisFood(d){
	var foodlist_fields = [
			{
			label :"菜单明细ID",
			name :"order_list_id",
			hide :"all"
		},
		{
			label :"菜品ID",
			name :"food_id",
			hide :"all"
		}, {
			label :"菜品名称",
			name :"food_name"
		}, {
			label :"菜品单价",
			name :"food_price",
			width :60
		}, {
			label :"是否上菜",
			name :"serving_flag_text",
			width :60
		}, {
			label :"菜品数量",
			name :"food_num",
			width :60
		} ];
	
	var foodForm=createFormPanel({
	    title   : "报损原因",
	    items   : [
	    	{label:"授权人帐号",name:"loginname",allowBlank:false,blankText:'帐户不能为空'},
	    	{label:"密码",name:"password",allowBlank:false,blankText:"密码不能为空",inputType:"password"},
	    	{label:"原因",name:"food_return_reseon",width:100}
	    ],
	    region  : "north",
	    height  : 90,
	    id      : "customer_cancel_reson",
	    split   : false
	});	

	// 菜品明细列表
	var foodlist = cEditGrid( {
		title :"菜品列表",
		items :foodlist_fields,
		region :"center",
		id :"food_list_list",
		width :300,
		filter :false,
		region:"center",
		tbarDisable :true,
		check :true,
		url   : "/order/food_allList.do?order_id="+d.order_id,
		firstLoad:true
	});
	
	var win=new Ext.Window({
	   items    : [foodForm,foodlist],
	   layout   : "border",
	   title    : "报损:[订单号:"+d.order_no+"(座位号:"+d.belong_to+")]",
	   width    : 400,
	   height   : 360,
	   modal    : true,
	   bodyBorder:false,
	   border   : false
	});
	
	
	win.addButton({
	   text     : "确定",
	   handler  : function(){
	       var rd=Ext.apply(foodForm.getForm().getValues(),{});
	       rd["food_list"]=new Array();
	       
	       var a=getCmp("food_list_list").getStore().query("selected","true");
			
		   for(var i=0;i< a.length;i++){
				rd.food_list.push(a.item(i).data);
		   }
		   
		   doPostByXML("/order/customer_dis_food.do",rd,function(e){
		       if(e.success=="true"){
		          $alt("报损成功.");
		          load("app_business_set_view_order_detail",d);
		          win.destroy();
		       }
		   
		   });
	   
	   }
	});
	
	win.addButton({
	   text     : "关闭",
	   handler  : function(){win.destroy();}
	});
	win.show();
  }
 
 
 /**
  * 客户退菜
  */
  function customerUploadFood(d){
	var foodlist_fields = [
			{
			label :"菜单明细ID",
			name :"order_list_id",
			hide :"all"
		},
		{
			label :"菜品ID",
			name :"food_id",
			hide :"all"
		}, {
			label :"菜品名称",
			name :"food_name"
		}, {
			label :"菜品单价",
			name :"food_price",
			width :60
		}, {
			label :"菜品数量",
			name :"food_num",
			width :70
		} ];
	
	// 菜品明细列表
	var foodlist = cEditGrid( {
		title :"菜品列表",
		items :foodlist_fields,
		region :"center",
		id :"food_list_list",
		width :300,
		filter :false,
		region:"center",
		tbarDisable :true,
		check :true,
		rowselect:function(){},
		url   : "/order/food_list.do?order_id="+d.order_id,
		firstLoad:true
	});
	
	var win=new Ext.Window({
	   items    : [foodlist],
	   layout   : "border",
	   title    : "厨房上菜:[订单号:"+d.order_no+"(座位号:"+d.belong_to+")]",
	   width    : 400,
	   height   : 360,
	   modal    : true,
	   bodyBorder:false,
	   border   : false
	});
	
	
	win.addButton({
	   text     : "确定",
	   handler  : function(){
	       var rd={food_list:new Array()};
	       
	       var a=getCmp("food_list_list").getStore().query("selected","true");
			
		   for(var i=0;i< a.length;i++){
				rd.food_list.push(a.item(i).data);
		   }
		   
		   doPostByXML("/order/customer_upload_food.do",rd,function(e){
		       if(e.success=="true"){
		          $alt("厨房上菜成功.");
		          load("app_business_set_view_order_detail",d);
		       }
		   
		   });
	   
	   }
	});
	
	win.addButton({
	   text     : "关闭",
	   handler  : function(){win.destroy();}
	});
	win.show();
  }
 
 /**
  * 客户预定
  */
  
 function customerOrder(d,dv){
   doSyncRequest("/order/customer_order.do",{set_no:d.set_no},function(e){
       if(e.success=="true"){
           $alt("客户下单成功.");
           dv.reload();
       }else $alt("客户下单失败.");
       
   });
   

   
 } 
 
 var customerOrder1=function(d,dv){
     var ds=dv.getSelectedRecords();
     var arr=new Array();
     for(var i=0;i< ds.length;i++){
       arr.push(ds[i].data.set_no);
     }
     d["set_no"]=arr.join(",");
 	 if(d.mincost_type==1)
 	 {
 	 	d.mincost_type="人均";
 	 }
 	 else if(d.mincost_type==2)
 	 {
 	 	d.mincost_type="总金额";
 	 }
 	 else
 	 {
 	 	d.mincost_type="无";
 	 }
　  var customerOrderField12=[
      
  　	  {label:"宾客姓名",name:"prearrange_name",id:"prearrange_name_op"},
       {label:"联系电话",name:"prearrange_phone",id:"prearrange_phone_op",listeners:{"blur":function(){
      	doGet("/vip/vip_member_selectPhone.do?custPhone="+getValue("prearrange_phone_op"),null,function(date){
      	var vipNO=Ext.getCmp("vip_card_no_op");
      	var cumPoint=Ext.getCmp("cum_point_op");
      	var userName=Ext.getCmp("prearrange_name_op");
      	if(date["vip_no"] == NaN)
      	{
      	}
      	else
      	{
      		vipNO.setValue(date["vip_no"]);
      		cumPoint.setValue(date["cum_point"]);
      		userName.setValue(date["cust_name"]);
      	}
      	})
      }}},
       {label:"VIP卡号",name:"vip_card_no",id:"vip_card_no_op",listeners:{"blur":function(){
       	doGet("/vip/vip_member_selectVipCard.do?custVipNo="+getValue("vip_card_no_op"),null,function(date){
       	var phone=Ext.getCmp("prearrange_phone_op");
      	var cumPoint=Ext.getCmp("cum_point_op");
      	var userName=Ext.getCmp("prearrange_name_op");
      	if(date["cust_phone"] == NaN)
      	{
      	}
      	else
      	{
      		phone.setValue(date["cust_phone"]);
      		cumPoint.setValue(date["cum_point"]);
      		userName.setValue(date["cust_name"]);
      	}
       	})
       }}},
       {label:"积分",name:"cum_point",id:"cum_point_op",readOnly:true},
  　     {label:"订餐人地址",name:"prearrange_addr",hide:"all"},
      {label:"服务生",name:"staff_id",hide:"all"},
     　     {label:"服务生",name:"staff_name",readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields},
      {label:"推荐人",name:"introducer"},
      {label:"下单时间",name:"operate_order_time",width:160,readOnly:true,value:new Date().dateFormat("Y-m-d H:i:s")},
      {label:"下单座位",name:"set_no",value:d.set_no,hide:"form"},
      {label:"座位名称",name:"balcony_name",value:d.balcony_name,readOnly:true},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS"},
      {label:"来宾人数",name:"man_count",allowBlank:false},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form"},
      {label:"最低消费类型",name:"mincost_type_text",xtype:"combo",value:d.mincost_type,code:"MINCOST_TYPE",readOnly:true},
      {label:"最低消费金额",name:"mincost_money",value:d.mincost_money,readOnly:true},
      {label:"过几分钟上菜",name:"print_starttime",value:0}
   ];

  var w=createFormWindow({cols:2,id:"app_business_order_customer_order11",
     label:"客户下单",action:"/order/customer_order.do",items:customerOrderField12,
     width:720,height:320,data:d

     });
  w.show();
  w.on("beforedestroy",function(){dv.reload();});
   
};

 var customerEdit1=function(d,dv){
	 var ddd = getCmp("app_business_set_view_order").getForm().getValues();
　  var customerOrderField13=[
      {label:"订单号",name:"order_id",hide:"all",value:ddd.order_id},
      {label:"订单号",name:"order_no",value:ddd.order_id},
      {label:"客户姓名",name:"prearrange_name",id:"prearrange_name_ed",value:ddd.prearrange_name,readOnly:true},
      {label:"联系电话",name:"prearrange_phone",value:ddd.prearrange_phone,id:"prearrange_phone",allowBlank:false,listeners:{"blur":function(){
      	doGet("/vip/vip_member_selectPhone.do?custPhone="+getValue("prearrange_phone"),null,function(date){
      	var vipNO=Ext.getCmp("vip_card_no_ed");
      	var cumPoint=Ext.getCmp("cum_point_ed");
      	var userName=Ext.getCmp("prearrange_name_ed");
      	if(date["vip_no"] == NaN)
      	{
      	}
      	else
      	{
      		vipNO.setValue(date["vip_no"]);
      		cumPoint.setValue(date["cum_point"]);
      		userName.setValue(date["cust_name"]);
      	}
      	})
      }}},
       {label:"VIP卡号",name:"vip_card_no",value:ddd.vip_card_no,id:"vip_card_no_ed",listeners:{"blur":function(){
       	doGet("/vip/vip_member_selectVipCard.do?custVipNo="+getValue("vip_card_no_ed"),null,function(date){
       	var phone=Ext.getCmp("prearrange_phone");
      	var cumPoint=Ext.getCmp("cum_point_ed");
      	var userName=Ext.getCmp("prearrange_name_ed");
      	if(date["cust_phone"] == NaN)
      	{
      	}
      	else
      	{
      		phone.setValue(date["cust_phone"]);
      		cumPoint.setValue(date["cum_point"]);
      		userName.setValue(date["cust_name"]);
      	}
       	})
       }}},
       {label:"积分",name:"cum_point",value:ddd.cum_point,id:"cum_point_ed",readOnly:true},
     　{label:"订餐人地址",name:"prearrange_addr",hide:"all"},
      {label:"服务生工号",name:"staff_id",hide:"all"},
     　{label:"服务生工号",name:"staff_name",readOnly:true,xtype:"trigger",table:"staff_info",columns:staffFields},
      {label:"推荐人",name:"introducer",value:ddd.introducer},
      {label:"下单时间",name:"operate_order_time",width:160,readOnly:true,value:ddd.order_time},
      {label:"下单座位",name:"set_no",value:d.set_no,hide:"form"},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS"},
      {label:"来宾人数",name:"man_count",value:ddd.man_count,allowBlank:false},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form"},
      {label:"预定座位",name:"balcony_name",value:ddd.balcony_name,xtype:"trigger",tableURL:"/shop/free_set2.do",getData:function() {return {P_time:new Date().dateFormat("Y-m-d H:i:s")};},columns:setFields},
      {label:"最低消费类型",name:"mincost_type",value:d.mincost_type,xtype:"combo",code:"MINCOST_TYPE",readOnly:true},
      {label:"最低消费金额",name:"mincost_money",value:d.mincost_money,readOnly:true}
  
   ];

  var w=createFormWindow({cols:2,id:"app_business_order_customer_order1111",
     label:"修改订单",action:"/order/customer_edit.do",items:customerOrderField13,
     width:720,height:320,data:d

     });
  w.show();
  w.on("beforedestroy",function(){dv.reload();});
   
};

 /**
  * 客户预定单开单
  */
 

 function customerOpenPreOrder(d,dv){
    var gridCols=[
       {label:"订单号",name:"order_no",width:150},
       {label:"座位号",name:"set_no",hide:"all"},
       {label:"座位号",name:"order_id",hide:"all"},
       {label:"预抵时间",name:"prearrange_order_time"},
       {label:"订单状态",name:"order_status_text"},
       {label:"宾客姓名",name:"prearrange_name"},
       {label:"联系电话",name:"prearrange_phone"},
       {label:"VIP卡号",name:"vip_card_no"}
       
    ]; 
    
    var toolBar=["查询条件","&nbsp;","-"];
    
    toolBar.push("VIP卡")
    toolBar.push(createField({name:"vip_card_no",emptyText:"输入VIP卡号查询",width:100}));
    toolBar.push("&nbsp;"); 
    toolBar.push("姓名")
    toolBar.push(createField({name:"prearrange_name",emptyText:"输入预定人姓名查询",width:80}));  
    toolBar.push("&nbsp;"); 
    toolBar.push("电话")
    toolBar.push(createField({name:"prearrange_phone",emptyText:"输入预定人联系电话查询",width:80})); 
    toolBar.push("&nbsp;"); 
    toolBar.push(createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})) ;
    
    function goClick(){
       var dt={};
    
       for(var i=0;i< toolBar.length;i++){
       	   var o=toolBar[i];
      
       	   if(typeof o == "object"){
       	       if(o.getName && o.getValue){
       	          
       	          dt[o.getName()]=o.getValue();
       	       }
       	   }
       	}
        
        load("preorder_open_grid",dt);
    } ;
    var grid=createPageGrid({
      id       : "preorder_open_grid",
      title    : "预定单列表",
      items    : gridCols,
      region   : "center",
      urls     : "/order/get_preorder.do?set_no="+d.set_no,
      filter   : false,
      tbar     : toolBar
   });
   
    
   var win=new Ext.Window({
      title     : "客户开单",
      items     : [grid],
      height    : 400,
      width     : 600,
      layout    : "border",
      bodyBorder: false,
      border    : false,
      buttons   : [{text:"确定",handler:openOrder},{text:"关闭",handler:function(){win.destroy();}}]
   });
   
  function openOrder(){
      var d=getSelectedData("preorder_open_grid");
      if(!d || !d.order_id){
          $alt("请选择一个预定订单开单。");
          return;
      }
      doSyncRequest("/order/customer_open.do",d,function(e){
       if(e.success=="true"){
           $alt("客户开单成功.");
           dv.reload();
           win.destroy();
       }else $alt("客户开单失败.");
       
   });
   }
   
   win.show();
 } 

/**
 * 客户结账
 */
function customerPay(d,dv){
   var dts = [d.order_no,d.mincost_money,d.mincost_type,d.man_count,d.cum_point];
   var w=cCalculatePanel(dts,function(){dv.reload();});
   w.show(dv);
} 

/**
 * 订单合并
 */
 function unionOrder(dv){
    var d=dv.getSelectedRecords();

    if(!d.length || d.length<2){
       $alt("请选择至少两个以上的订单来进行合并.");
       return;
    }
    
    var arr=new Array();
    for(var i=0;i< d.length;i++){
       var dd=d[i].data;
       if(dd.set_st!="set.busy"){
          $alt(dd.name+"没有人用餐,不能进行订单合并.");
          return;
       }
       
       arr.push({order_id:dd.order_id});
    }
    
    doPost("/order/order_union.do",arr,function(){dv.reload();});
 }
 
  function splitOrder(dv){
    var d=dv.getSelectedRecords();

    if(!d.length || d.length<2){
       $alt("请选择至少一个订单进行拆分.");
       return;
    }
    
    var arr=new Array();
    for(var i=0;i<d.length;i++){
       var dd=d[i].data;
       if(dd.set_st!="set.busy"){
          $alt(dd.name+"没有人用餐,不能进行订单拆分.");
          return;
       }
       
       arr.push({order_id:dd.order_id});
    }
    
    doPost("/order/order_split.do",arr,function(){dv.reload();});
 }


 function DataViewContextMenu(ev,i,el,e){
 	var o=ev;
    var d=o.getSelectedRecords();
    var da=d;
    if(da.length>0) d=copyProps({},da[0].data);
    else return;
    //

    if(!o.contentMenu && o.contentMenu!=null){ o.contentMenu.destroy();o.contentMenu=null;}
       	      
     	      
    var mm=new Ext.menu.Menu({id:o.id+"_ctx_menu",shadow:false});
       	      
    mm.add({text:"客户下单",handler:function(){customerOrder1(d,ev);},icon:"",disabled:"set.free"!=d.set_st});
    mm.add({text:"客户开单",handler:function(){customerOpenPreOrder(d,ev);},icon:"",disabled:"set.pre"!=d.set_st});
    mm.add("-");
    mm.add({text:"订单修改",handler:function(){customerEdit1(d,ev);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.add({text:"订单合并",handler:function(){unionOrder(ev);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.add({text:"订单拆分",handler:function(){splitOrder(ev);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.add("-");
    mm.add({text:"客户点菜",handler:function(){orderFood(da);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.add({text:"客户退菜",handler:function(){customerCancelFood(d);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.add({text:"客户催菜",handler:function(){customerReminderFood(d);},icon:"",disabled:"set.busy"!=d.set_st});
     mm.add({text:"菜品重打",handler:function(){customerReprintFood(d);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.add({text:"厨房上菜",handler:function(){customerUploadFood(d);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.add({text:"重打客户单",handler:function(){customerReprintCustomerFood(d);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.add({text:"报损",handler:function(){customerDisFood(d);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.add("-");
    mm.add({text:"客户结账",handler:function(){customerPay(d,ev);},icon:"",disabled:"set.busy"!=d.set_st});
    mm.showAt(e.getPoint());
    o.contentMenu=mm;
 }
 
 


 var dating={
	   title: "大厅",
	   style:"background-color:red",
	   layout      : "fit",
	  
       items:new Ext.DataView({

            id          : "app_business_set_view_dating",
            store       : createStore("01"),
            tpl         : tpl,
            split       : true,
            style       : "overflow:auto",
            multiSelect : true,
            width       : 500,
            itemSelector: 'div.thumb-wrap',
            overClass   : 'x-view-over',
            layout      : "fit",
            listeners   : {
            	"click":{fn:dataViewClick,scope:this},
            	"contextmenu" :{fn:DataViewContextMenu,scope:this}
            	}
        })};
 
  

 var baoxiang={
	   title: "包厢",
	   style:"background-color:red",
	   layout      : "fit",
       items:new Ext.DataView({

            id          : "app_business_set_view_baoxiang",
            store       : createStore("02"),
            tpl         : tpl,
            split       : true,
            style       : "overflow:auto",
            multiSelect : true,
            width       : 500,
            itemSelector: 'div.thumb-wrap',
            overClass   : 'x-view-over',
            layout      : "fit",
            listeners   : {
            "click":{fn:dataViewClick,scope:this},
            "contextmenu" :{fn:DataViewContextMenu,scope:this}
            }
        })}; 

var setView=new Ext.TabPanel({
	    id   : "app_business_set_view_view_1",
        title:"餐厅座位布局图",
        region:"center",
        resizeTabs:true,
        activeTab:0,
        
        layoutOnTabChange:true,
        width : 500,
        items:[dating,baoxiang]
   });

var orderForm=createFormPanel({
       
       id        : "app_business_set_view_order",
       cols      : 1,
       region    : "north",
       height    : 240,
       labelWidth : 60,
       width     : 200,
       items     : orderFileds
   });
   
var orderDetailForm=createPageGrid({
       title      : "订单明细",
       id         : "app_business_set_view_order_detail",
       urls       : "/order/order_list.do",
       firstLoad  : false,
       region     : "center",
       height     : 200,
       tools:[{id:"minimize"},{id:"maximize"}],
       width      : 200,
       filter     : false,
       items      : orderDetailFields
   });
/**
var orderPayForm=createPageGrid({
       title     : "收费明细",
       id        : "app_bs_order_pay",
       urls      : "/order/order_list.do",
       firstLoad : false,
       region    : "south",
       height    : 200,
       width     : 200,
       filter    : false,
       split     : true,
       items     : orderPayFields,
       minSize   : 200
   }); 
**/
var detailView={ 
   region        : "east",
   split         : true,
   bodyBorder    : false,
    collapsible:true,
    title:"订单信息",
   width         : 480,
   layout        : "border",
   items         : [orderForm,orderDetailForm]
};


return [setView,detailView];