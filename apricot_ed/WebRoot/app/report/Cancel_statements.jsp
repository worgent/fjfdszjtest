var fld=[
      {label:"订单编号",name:"order_no",width:80},
      {label:"预抵时间",name:"prearrange_order_time",allowBlank:false},
      {label:"宾客姓名",name:"prearrange_name",allowBlank:false,width:80},
      {label:"联系电话",name:"prearrange_phone",allowBlank:false},
      {label:"预定记录人",name:"precord_staff_id",allowBlank:false,width:80},
      {label:"取消记录人",name:"order_name",allowBlank:false},
      {label:"取消原因",name:"order_reseon",allowBlank:false,width:80}
   ];

var toolBars=["预定取消报表","&nbsp;","-",
       	                  createField({name:"order_time",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"订单开始日期"}),
       	                  "&nbsp;","-",
       	                  createField({name:"order_time1",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"订单截止日期"}),     
       	                  "&nbsp;","-",
       	                  createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})
       	                  
       	                  ];
       	                  
  var grid=createPageGrid({
      id           : "app_rp_cancel_ststements",
      title        : "预定取消报表",
      toolBarFields:toolBars,
      items        : fld,
      groupField   : "order_reseon",
      urls          : "/report/cancel_ststements.do",
      region       : "center"
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
 load("app_rp_cancel_ststements",dt);
  // 	grid.getStore().baseParams=dt;
 //  	grid.getStore().load();
}





  return [grid];