var fld=[
      {label:"订单时间",name:"order_time"},
      {label:"订单编号",name:"order_no",allowBlank:false},
      {label:"菜品名称",name:"food_name",allowBlank:false},
      {label:"退菜数量",name:"food_count",allowBlank:false},
      {label:"退菜原因类型",name:"food_return_type",allowBlank:false,xtype:"combo",code:"FOOD_RETURN_TYPE"},
      {label:"退菜原因",name:"food_return_reseon",allowBlank:false}
   ];

var toolBars=["退菜报表","&nbsp;","-",
       	                  createField({name:"order_time",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"订单开始 时间"}),
       	                  "&nbsp;","-",
       	                  createField({name:"order_time1",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"订单结束时间"}),     
       	                  "&nbsp;","-",
       	                  createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})     	                  
       	                  ];
       	                  
  var grid=createPageGrid({
      id           : "app_rp_dishes_statements",
      title        : "退菜报表",
      toolBarFields:toolBars,
      items        : fld,
      groupField   : "food_return_type",
      urls          : "/report/dishes_statements.do",
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
 load("app_rp_dishes_statements",dt);
  // 	grid.getStore().baseParams=dt;
 //  	grid.getStore().load();
}





  return [grid];