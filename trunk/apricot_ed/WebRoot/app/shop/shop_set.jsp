var sfSelectColumn=[
  {name:"sf_name",label:"店面名称",query:true},
  {label:"店面地址",name:"sf_addr",width:200},
  {name:"sf_id",label:"店面标识",hidden:true}
];

var data={
   dining_floor  : "1",
   set_status    : "1" 
};

var setfields=[
       {label:"店面",name:"sf_id",hidden:true,hide:true},
    	 {label:"店面名称",name:"sf_name",xtype:"trigger",table:"storefront_info",columns:sfSelectColumn},
    	 {label:"餐位序号",name:"set_no",width:80,hide:"all"},
    	 {label:"楼层",name:"dining_floor",code:"FlOOR",xtype:"combo",width:80,allowBlank:false,sortable:true},
    	 {label:"餐位编号",name:"balcony_code",width:80,allowBlank:false},
    	 {label:"所属位置",name:"belong_to",width:80,xtype:"combo",code:"BELONG_TO",allowBlank:false},
    	 {label:"餐位名称",name:"balcony_name",handler:function(){alert('');}},
    	 {label:"餐位容纳人数",name:"set_max",width:80},
    	 {label:"最低消费类型",name:"mincost_type",width:80,xtype:"combo",code:"MINCOST_TYPE",allowBlank:false},
    	 {label:"最低消费金额",name:"mincost_money",width:80},
    	 {label:"网络预订",name:"pre_order_style",width:80,xtype:"combo",code:"PRE_ORDER_STYLE"},
    	 {label:"餐位状态",name:"set_status",width:80,code:"STATUS",xtype:"combo"}
    ];
    
var setfields_m=[
       {label:"店面",name:"sf_id",hidden:true,hide:true},
    	 {label:"店面名称",name:"sf_name",hidden:true,xtype:"trigger",table:"storefront_info",columns:sfSelectColumn},
    	 {label:"餐位序号",name:"set_no",width:80,hide:"all"},
    	 {label:"楼层",name:"dining_floor",value:"1",code:"FlOOR",xtype:"combo",width:80,allowBlank:false,sortable:true},
    	 {label:"开始编号",xtype:"number",name:"balcony_code_start",width:80,allowBlank:false},
    	 {label:"结束编号",xtype:"number",name:"balcony_code_end",width:80,allowBlank:false},
    	 {label:"所属位置",name:"belong_to",width:80,xtype:"combo",code:"BELONG_TO"},
    	 {label:"餐位名称",name:"balcony_name",handler:function(){alert('');}},
    	 {label:"餐位容纳人数",name:"set_max",width:80},
    	 {label:"最低消费类型",name:"mincost_type",width:80,xtype:"combo",code:"MINCOST_TYPE",allowBlank:false},
    	 {label:"最低消费金额",name:"mincost_money",width:80},
    	 {label:"网络预订",name:"pre_order_style",width:80,xtype:"combo",code:"PRE_ORDER_STYLE"},
    	 {label:"餐位状态",name:"set_status",width:80,code:"STATUS",xtype:"combo",hide:"all"}
    ];

if(getSessionData){
   data=Ext.apply(data,getSessionData());
}

function multiAdd(){
   
   var win=createFormWindow({
      items       : setfields_m,
      label       : "批量添加座位",
      id          : "app_shop_shop_set_m",
      data        : data,
      cols        : 1,
      close       : cLoadEvent("app_shop_shop_set_table"),
      action      : "/shop/dinner_set_multiadd.do"
   });
   win.show();
}

return createTableForm({
   id:"app_shop_shop_set",
   tableTitle:"座位列表",
   formTitle:"座位基本信息",
   horizontal:false,
   table:"dining_set_info",
   data:data,
   tableURL:"/shop/dinner_set_pages.do",
   formCols:2,
   items:setfields,
   checkValid:function(o){
       if(!o.mincost_money || o.mincost_money=="" || o.mincost_money==0){
           
           return true;
       }
       else
       {
       	if(!o.mincost_type || o.mincost_type=="")
       	{
       		$alt("请选择最低消费类型");
       		return false;
       	}
       	else
       	{
       		return true;
       	}
       }
   },
   toolBar:[
      {text:"批量增加",iconCls:"gridmultiadd",handler:multiAdd}
    ]
});