var reshuffleFields=[
	{label:"交班人",name:"staff_name",readOnly:true},
	{label:"状态",name:"r_statu_text",readOnly:true},
	{label:"初始金额",name:"staff_cash",readOnly:true},
	{label:"接班人",name:"priv_staff_name",readOnly:true},
	{label:"交接金额",name:"take_cash",readOnly:true},
	{label:"交接时间",name:"d_time",readOnly:true}
];

var toolBars=["交班记录","&nbsp;","-",
	createField({name:"reshuffle_time",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"开始时间"}),
	"&nbsp;","-",
	createField({name:"reshuffle_time1",width:100,xtype:"datetime",format:"Y-m-d",emptyText:"结束时间"}),     
	"&nbsp;","-",
	createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})     	                  
];

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
 load("app_rp_reshuffle",dt);
  // 	grid.getStore().baseParams=dt;
 //  	grid.getStore().load();
}

var grid=createPageGrid({
    id           : "app_rp_reshuffle",
    title        : "交班记录",
    page		 : true,
    filter		 : false,
    toolBarFields:toolBars,
    items        : reshuffleFields,
    urls         : "/staff/reshuffle_report.do",
    region       : "center"
}); 

return [grid];