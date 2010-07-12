
var mFields=[
  
  {label:"订单编号",name:"order_no",readOnly:true},
   {label:"订单类型",name:"order_type",readOnly:true,code:"ORDER_TYPE"},
  {label:"订单状态",name:"order_status",width:60,code:"order_status"},
   {label:"餐位名称",name:"balcony_name",readOnly:true},
   {label:"菜名",name:"food_name",readOnly:true},
	{label:"数量",name:"food_count",readOnly:true},
	 //{label:"单位",name:"book_cash",readOnly:true},
	{label:"上菜标识",name:"food_action",code:"FOOD_ACTION",readOnly:true},
	 {label:"催菜次数",name:"hurry_times",readOnly:true},
	 {label:"菜品ID",name:"order_list_id",readOnly:true},
   {label:"打印标志",name:"is_print",readOnly:true,code:"IS_PRINT"}
 
];

function foodPrint(){
var selectedRows = grid.getSelectionModel().getSelections();  
//if(selectedRows.length==0){
//	alert("tf ");
//}
var str="";
for(var i=0;i< selectedRows.length;i++){
	str+=selectedRows[i].data.order_list_id+",";
}
str=str.substring(0,str.length-1);
//alert(str);
 
 
 
 
	var state=Ext.getCmp("orderstatus").getValue(); 
	var type=Ext.getCmp("ORDERTYPE").getValue(); 
	var orderno=Ext.getCmp("orderno").getValue(); 
	var balconyname=Ext.getCmp("balconyname").getValue(); 
	var FOODACTION=Ext.getCmp("foodaction").getValue(); 
	var hurry=Ext.getCmp("hurrytimes").getValue(); 
	var isprint=Ext.getCmp("isprint").getValue();
	//var firstID=getData("food_print",["order_list_id"]).order_list_id;
   //  grid.getSelectionModel().selectLastRow() ;
   //  var lastID=getData("food_print",["order_list_id"]).order_list_id;
       var WinPrint = window.open('../print/foodprint.jsp?str='+str+'&isprint='+isprint+'&type='+type+'&FOODACTION='+FOODACTION+'&hurry='+hurry+'&balconyname='+balconyname+'&orderno='+orderno+'&state='+state+'','','toolbar=no, status=no,menubar=no, scrollbars=yes,resizable=no');
}
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
    
   load("food_print",dt);
  
}
var toolBars=[ {text:"打印",iconCls:"gridmultiadd",handler:foodPrint},"-",
   "查询条件","&nbsp;","-",
       	                  createField({name:"order_no",id:"orderno",emptyText:"订单编号",width:90}),
       	                  "-",
       	                  createField({name:"balcony_name",id:"balconyname",emptyText:"餐位名称",width:90}),
       	                  "-",
       	                   createField({name:"order_type",xtype:"combo",id:"ORDERTYPE",code:"ORDER_TYPE",emptyText:"订单类型",width:90}),
       	                  "-",
       	                   createField({name:"order_status",xtype:"combo",id:"orderstatus",code:"order_status",emptyText:"订单状态",width:90}),
       	                  "-",
       	                  createField({name:"food_action",id:"foodaction",xtype:"combo",code:"FOOD_ACTION",emptyText:"请选择上菜标识",width:120}),
       	                   "-",
       	                   createField({name:"hurry_times",id:"hurrytimes",xtype:"combo",code:"IS_HURRY",emptyText:"请选择催菜标识",width:120}),
       	                   "-",
       	                   createField({name:"is_print",id:"isprint",xtype:"combo",width:90,code:"IS_PRINT",emptyText:"请选择打印标识"}),
       	                  createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})
       	                  
       	                  ];

var grid=createPageGrid({
     id            : "food_print",
     label         : "菜单打印列表",
     region        : "center",
     items         : mFields,
     checkbox:true,
    //sm:true,
     filter        : false,
   //  page          : true,
     urls          : "/print/foodpage.do",
     toolBarFields :toolBars
 
 });

 return [grid];