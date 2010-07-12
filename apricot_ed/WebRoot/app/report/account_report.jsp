var fld=[
      {label:"餐位序号",name:"set_no"},
      {label:"订单号",name:"order_no",allowBlank:false},
      {label:"来宾人数",name:"man_count",allowBlank:false},
      {label:"开单时间",name:"order_time",allowBlank:false},
      {label:"挂账金额(￥)",name:"total",allowBlank:false},
      {label:"联系人",name:"book_man"},
      {label:"联系电话",name:"book_phone"},
      {label:"订单类型",name:"order_type_text"},
      {label:"预计结账时间",name:"book_date"},
      {label:"挂账原因",name:"book_desc",width:80},
      {label:"员工工号",name:"cashier",hide:"all"},
       {label:"授权员工姓名",name:"book_name"},
      {label:"挂账周期(天)",name:"days"}
   ];
   /***
 * 订单查询条件定义
 */
var toolBars=["报表查询条件","&nbsp;","-",
       	                  createField({name:"order_time",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"开单起始日期"}),
       	                  "&nbsp;",
       	                  createField({name:"order_time1",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"开单截止日期"}),
       	                   "-",
       	                  createField({name:"book_date",xtype:"datetime",width:100,emptyText:"预计结账起始时间",format:"Y-m-d"}),
       	                  "&nbsp;",
       	                  createField({name:"book_date1",xtype:"datetime",width:100,emptyText:"预计结账截止时间",format:"Y-m-d"}),
       	                  "-",
       	                  createField({name:"order_type",xtype:"combo",code:"ORDER_TYPE",width:80,emptyText:"订单类型"}),
       	                  "-",
       	                  createField({name:"days",code:"ORDER_TYPE",width:80,emptyText:"挂账周期"}),
       	                  "&nbsp;",
       	                  createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})
       	                  
       	                  ];
   
   
  var grid=createPageGrid({
      id           : "app_rp_currday_order",
      title        : "记账报表",
      items        : fld,
      toolBarFields:toolBars,
      groupField   : "order_status_text",
      urls          : "/report/curr_day_order1.do",
      region       : "center",
       contextMenu:function(ev,o){
       	      if(!o.contentMenu && o.contentMenu!=null){ o.contentMenu.destroy();o.contentMenu=null;}
       	      
       	      var dt=getSelectedData("app_rp_currday_order");
       	      
       	      var mm=new Ext.menu.Menu({id:"app_rp_currday_order_cxt_menu",shadow:false});
       	      mm.add({text:"客户结账",icon:"",handler:orderPayEvent});
       	      
       	      mm.showAt(ev.getPoint());
       	      o.contentMenu=mm;
       	   }
      
  }); 
  //客户结账
var orderPayEvent=function(){
    var dt=getData("app_rp_currday_order",["order_no"]).order_no;
    var dts = [getData("app_rp_currday_order",["order_no"]).order_no,getData("app_rp_currday_order",

["mincost_money"]).mincost_money,getData("app_rp_currday_order",["mincost_type"]).mincost_type,getData

("app_rp_currday_order",["man_count"]).man_count];
    var w=cCalculatePanel(dts,cLoadEvent("app_rp_currday_order"));
    w.show(this);
};

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
    
   load("app_rp_currday_order",dt);
  // 	grid.getStore().baseParams=dt;
 //  	grid.getStore().load();
}
  
  
  return [grid];