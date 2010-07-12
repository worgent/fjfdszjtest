//订单列表域
var orderFields=[
      {label:"订单号",name:"order_no",hide:"form",width:200},
      {label:"订单号",name:"order_id",hide:"all"},
      {label:"餐位序号",name:"set_no"},
      {label:"服务员",name:"service_staff_id",hide:"all"},
      {label:"服务员",name:"staff_name"},
      {label:"订单类型",name:"order_type",hide:"form",xtype:"combo",code:"ORDER_TYPE"},
      {label:"来宾人数",name:"man_count"},
      {label:"订单时间",name:"order_time",xtype:"datetime",width:160},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS"},
      {label:"折扣",name:"order_discount",hide:"grid"},
      {label:"折扣原因",name:"order_discount_reseon",hide:"grid"},
      {label:"是否已经拿发票",name:"has_invoice",xtype:"combo",code:"IS_OR_NOT",hide:"all"},
      {label:"预定人名字",name:"prearrange_name"},
      {label:"预定人电话号码",name:"prearrange_phone"},
      {label:"预定人数",name:"prearrange_man_count"},
      {label:"预定VIP卡号",name:"vip_card_no"},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form"},
      {label:"订餐人地址",name:"prearrange_addr"}
   ];
 
var orderList=createPageGrid({
       	   title: "订单列表",
       	   id:"app_business_order_changeset",
       	   urls:"/order/order_getonorder.do",
           region:"center",
           firstLoad:false,
           filter:false,
       	   items:orderFields,
       	   toolBarFields:[
       	      "查询条件","&nbsp;","-","&nbsp;",
       	      {xtype:"trigger",width:300,triggerClass:"x-form-search-trigger",
       	       emptyText:"输入座位号查询...",
       	       onTriggerClick:function(e){
       	          load("app_business_order_changeset",{set_no:this.getValue()});
       	       }
       	       
       	       },
       	       "&nbsp",
       	       {type:"button",pressed:true,text:"申请换台",iconCls:"gridBarItemPay",
       	        handler:function(e){
       	             var dt=getData("app_business_order_changeset",["order_id"]);
       	             if(!dt.order_id){
       	               $alt("请选择一个订单进行转台!");
       	               return;
       	             }
       	             changeSetEvent(this,function(){reload("app_business_order_changeset");},dt);
       	          }
       	       }]
   });
   
 function changeSetEvent(o,f,order){
    var grid=createPageGrid({
       id:"app_business_order_changeset_grid",
       region:"center",
       urls:"/shop/set_freelist.do",
       filter:false,
       items:[
    	 {label:"餐位序号",name:"set_no",hide:true},
    	 {label:"楼层",name:"dining_floor",allowBlank:false,sortable:true},
    	 {label:"包厢代码",name:"balcony_code",allowBlank:false},
    	 {label:"所属位置",name:"belong_to",xtype:"combo",code:"BELONG_TO"},
    	 {label:"包厢名称",name:"balcony_name"},
    	 {label:"餐位最大容纳人数",name:"set_max"},
    	 {label:"预定方式",name:"pre_order_style",xtype:"combo",code:"PRE_ORDER_STYLE"},
    	 {label:"餐位状态",name:"set_status",code:"STATUS",xtype:"combo"}
       ],
       toolBarFields:["查询条件","&nbsp;","-","&nbsp;",
         {xtype:"trigger",name:"keyword",width:300,triggerClass:"x-form-search-trigger",
          emptyText:"请输入座位号查询当前空闲的座位(通过逗号分开)...",
          onTriggerClick:function(e){
             load("app_business_order_changeset_grid",{set_no:this.getValue()});
          }
         }
       ]
    });
    
    grid.addButton(createField({
       name:"changeSetBtn",
       text:"保存",
       type:"button",
       width:60,
       handler:function(e){
          var dt=getData("app_business_order_changeset_grid",["set_no"]);
          Ext.apply(dt,order);
          doPost("/order/order_changeset.do",dt);
          getCmp("changeSetWindow").destroy();
       }
    }));
    
    grid.addButton(createField({
       text:"关闭",
       width:60,
       handler:function(){getCmp("changeSetWindow").destroy();}
    }))
    
    var v=new Ext.Window({
            id:"changeSetWindow",
            title    : "选择座位",
            closable : true,
            width    : 400,
            height   : 300,
            layout   : 'border',
            hideBorders:true,
            bodyStyle: "padding:5px",
            modal    : true,
            items    : [grid]
    });
    
    v.show(o);
    v.on("beforedestroy",f);
 } 
 

return [orderList];