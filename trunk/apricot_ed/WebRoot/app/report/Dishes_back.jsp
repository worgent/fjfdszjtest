var fld=[
      {label:"菜品名称",name:"food_name",allowBlank:false,width:100},
      {label:"退菜数量",name:"isum",allowBlank:false,width:80}
   ];

var toolBars=["退菜记录","&nbsp;","-",
       	                  createField({name:"order_time",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"订单开始 时间"}),
       	                  "&nbsp;","-",
       	                  createField({name:"order_time1",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"订单结束时间"}),     
       	                  "&nbsp;","-",
       	                  createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})     	                  
       	                  ];
       	                  
  var grid=createPageGrid({
      id           : "app_rp_dishes_back",
      title        : "退菜记录",
      toolBarFields:toolBars,
      items        : fld,
      urls          : "/report/dishes_back.do",
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
 load("app_rp_dishes_back",dt);
  // 	grid.getStore().baseParams=dt;
 //  	grid.getStore().load();
}




  return [grid];