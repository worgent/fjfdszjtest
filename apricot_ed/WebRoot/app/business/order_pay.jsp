//订单列表域
var orderFields=[
      {label:"订单号",name:"order_no",hide:"form",width:140},
      {label:"订单号",name:"order_id",hide:"all"},
      {label:"餐位编号",name:"set_no"},
      {label:"餐位名称",name:"balcony_name",width:80},
      {label:"预订记录人",name:"record_staff_id",hide:"all"},
      {label:"预订记录人",name:"staff_name"},
      {label:"订单类型",name:"order_type",hide:"form",xtype:"combo",code:"ORDER_TYPE"},
      {label:"预定人数",name:"prearrange_man_count"},
      {label:"来宾人数",name:"man_count"},
      {label:"最低消费类型",name:"mincost_type",xtype:"combo",code:"MINCOST_TYPE"},
      {label:"最低消费类型",name:"mincost_type"},
      {label:"最低消费金额",name:"mincost_money"},
      {label:"订单时间",name:"order_time",xtype:"datetime",width:160},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS"},
//      {label:"折扣",name:"order_discount",hide:"grid"},
//      {label:"折扣原因",name:"order_discount_reseon",hide:"grid"},
//      {label:"是否已经拿发票",name:"has_invoice",xtype:"combo",code:"IS_OR_NOT",hide:"all"},
      {label:"宾客姓名",name:"prearrange_name"},
      {label:"联系电话",name:"prearrange_phone"},
      {label:"VIP卡号",name:"vip_card_no"},
      {label:"总积分",name:"cum_point"},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form"},
      {label:"订餐人地址",name:"prearrange_addr"}
   ];
 
var orderList=createPageGrid({
       	   title: "订单列表",
       	   id:"app_business_order_pay",
       	   urls:"/order/order_getonorder.do",
           region:"center",
           firstLoad:false,
           filter:false,
       	   items:orderFields,
       	   toolBarFields:[
       	      "查询条件","&nbsp;","-","&nbsp;",
       	      {xtype:"trigger",width:300,triggerClass:"x-form-search-trigger",
       	       emptyText:"输入卡号或电话查询",
       	       onTriggerClick:function(e){
       	          load("app_business_order_pay",{keyword:this.getValue()});
       	       }
       	       
       	       },
//       	       "&nbsp",
//       	       {type:"button",pressed:true,text:"订单群结账",iconCls:"gridBarItemPay",
//       	        handler:function(e){
       	             
//       	             var dt=getData("app_business_order_pay",["order_no"]);
//       	             if(!dt.order_no){
//      	                $alt("选择一个订单号进行结账!");
//       	                return;
//       	             }
//       	             if(dt.order_status=="0"){
//                     var w=cCalculatePanel(dt.order_no,cLoadEvent("app_business_order_pay"));
//                     w.show(this);
//                     }else bookPay(dt.order_no);
//       	          }
//       	       },
       	       "&nbsp",
       	       {type:"button",pressed:true,text:"订单结账",iconCls:"gridBarItemPay",
       	        handler:function(e){
       	             var dt=getData("app_business_order_pay",["order_no","order_status"]);
       	             if(!dt.order_no){
       	                $alt("选择一个订单号进行结账!");
       	                return;
       	             }
       	             if(dt.order_status=="0"){
					    var dd=getData("app_business_order_pay");
					    var dts = [getData("app_business_order_pay",["order_no"]).order_no,getData("app_business_order_pay",["mincost_money"]).mincost_money,getData("app_business_order_pay",["mincost_type"]).mincost_type,getData("app_business_order_pay",["man_count"]).man_count,getData("app_business_order_pay",["cum_point"]).cum_point,getData("app_business_order_pay",["order_type"]).order_type];
					    var w=cCalculatePanel(dts,cLoadEvent("app_business_order_pay"));
					    w.show(this);
                     }
       	          }
       	       }
       	   ]
   });
 

function bookPay(orderno){
    var orderFields1=[
        {label:"订单号",name:"pay_id",hide:"form"},
        {label:"订单号",name:"order_no",readOnly:true},
        {label:"客户名称",name:"book_man",readOnly:true},
        {label:"联系电话",name:"book_phone",readOnly:true},
        {label:"应收金额",name:"pay_total",readOnly:true},
        {label:"实收金额",name:"fact_pay_total"},
        {label:"找零11",name:"return_total",readOnly:true}
    ];
    
   	  var basic=new Ext.FormPanel({
        labelAlign  : 'left',
        frame       : true,
        title       : "客户结账",
        style   : 'padding:2px',
        region      : "center",
        defaultType : 'textfield',
        defaults    : {xtype:'textfield',anchor:'95%'},
        items       : orderFields1  	
	  	});
	 
	 
	 
	 doGet("/order/getBookBillInfo.do",{order_no:orderno},function(d){
	    basic.form.setValues(d);
	 })
	 

     var pay=false;
	  	
	 var w=new Ext.Window({
	     items : [basic],
	     width : 640,
	     height: 480,
	     layout:"border",
	     bodyBorder:false,
	     border:false,
	     
	     plugins: new function(){
          this.init = function(win){
             win.on('deactivate', function(){
                    var i=1;
                    this.manager.each(function(){i++});
                    this.setZIndex(this.manager.zseed + (i*10));
                })
           }
       }
	 });
	 
	 w.addButton({text:"算费",handler:function(){
	     pay=false;
	     var o=basic.form.getValues();
	     
	     var fp=parseInt(o.fact_pay_total);
	     
	     if(!fp||fp==NaN){
	        $alt("请在实收金额栏内输入金额数，数字型的。")
	        return;
	     }
	     
	     o["return_total"]=parseInt(o.fact_pay_total)-parseInt(o.pay_total);
	     
	     if(o.return_total<0){
	        $alt("金额不够，请确认输入！");
	        return ;
	     }
	     
	     pay=true;
	 }});
	 w.addButton({text:"结账",handler:function(){
	     if(!pay){
	        $alt("付款金额不够，或者输入有误，请确认！");
	        return;
	     }
	     doPost("/order/pay_book_order.do",basic.form.getValues());
	 }});
	 w.addButton({text:"关闭",handler:function(){window.destroy();}});
	 w.show();
    
}


return [orderList];