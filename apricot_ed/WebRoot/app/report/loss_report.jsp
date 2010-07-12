var fld=[
   {label:"订单号",name:"order_no",allowBlank:false},
   {label:"订单时间",name:"order_time",allowBlank:false},
   {label:"订单状态",name:"order_status_text"},
   {label:"报损人员",name:"staff_name"},
   {label:"报损菜品",name:"food_name"},
   {label:"报损原因",name:"food_return_reseon"},
   {label:"数量",name:"food_count"}
];

var toolBars=["报表查询条件","&nbsp;","-",
               createField({name:"order_time",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"开单起始日期"}),
               "&nbsp;",
               createField({name:"order_time1",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"开单截止日期"}),
               "&nbsp;",
       	       createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})
    	     ]

var grid=cGroupGridJson({
    id           : "report_loss_detail",
    title        : "报损报表",
    items        : fld,
    url          : "/report/report_loss.do",
    region       : "center",
    toolBarFields: toolBars
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
    
   load("report_loss_detail",dt);
} 

return[grid];