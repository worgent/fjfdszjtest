var fld=[
      {label:"餐位序号",name:"set_no"},
       {label:"员工工号",name:"staff_id",hide:"all"},
       {label:"员工姓名",name:"staff_name"},
      {label:"订单号",name:"order_no",allowBlank:false},
     // {label:"来宾人数",name:"man_count",allowBlank:false},
      {label:"订单时间",name:"order_time",allowBlank:false},
      {label:"订单状态",name:"order_status_text"},
      //{label:"订单类型",name:"order_type_text"},
      {label:"宾客姓名",name:"prearrange_name"},
      {label:"联系电话",name:"prearrange_phone"},
     // {label:"预定人数",name:"prearrange_man_count"},
     // {label:"预定VIP卡号",name:"vip_card_no"},
      {label:"收费时间",name:"operate_order_time"},
      {label:"优惠前金额(￥)",name:"total"},
      {label:"优惠后金额(￥)",name:"pay_total"},
      {label:"优惠券金额(￥)",name:"youhui_total"},
      {label:"现金收费(￥)",name:"fact_pay_total"},
       {label:"刷卡收费(￥)",name:"fact_pay_card"}
     // {label:"订餐人地址",name:"prearrange_addr"}
   ];
    var toolBars=[ 
   "查询条件","&nbsp;","-",
       	                  createField({name:"cur_date",xtype:"datetime",width:90,id:"time2",format:'Y-m-d',emptyText:"输入时间"}),
       	                  "-",
       	                  createField({name:"cur_name",width:90,id:"staff_id",emptyText:"员工工号"}),
       	                  createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})
       	                  
       	                  ];
   
  var grid=cGroupGridJson({
      id           : "app_page_detail",
      title        : "营业员结账明细查询",
      items        : fld,
      groupField   : "order_status_text",
      url          : "/staff/super_detailpage.do",
      region       : "center",
      toolBarFields:toolBars
  }); 
  
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
    
   load("app_page_detail",dt);
  // 	grid.getStore().baseParams=dt;
 //  	grid.getStore().load();
}
  return [grid];