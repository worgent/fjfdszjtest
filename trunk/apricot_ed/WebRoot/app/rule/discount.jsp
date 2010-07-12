var priceListField=[
      {label:"折扣ID",name:"price_id",hide:"all"},
      {label:"折扣名称",name:"price_name",width:200},
      {label:"店面ID",name:"sf_id",hide:"all"},
      {label:"定价类型",name:"price_type",code:"PRICE_TYPE"},
      {label:"有效开始日期",name:"eff_date"},
      {label:"有效结束日期",name:"exp_date"},
      {label:"规则状态",name:"price_state" ,code:"PRICE_STATE"}
   ];
 
 var priceFormField=[
      {label:"折扣ID",name:"price_id",hide:"all"},
      {label:"折扣名称",name:"price_name"},
      {label:"定价类型",name:"price_type",xtype:"combo",code:"PRICE_TYPE"},
      {label:"有效开始日期",name:"eff_date",xtype:"date"},
      {label:"有效结束日期",name:"exp_date",xtype:"date"},
      {label:"规则状态",name:"price_state",xtype:"combo",code:"PRICE_STATE"}
   ];
 
 
 var priceValueListField=[
      {label:"值ID",name:"value_id",hide:"all"},
      {label:"折扣ID",name:"price_id",hide:"all"},
      {label:"折扣值单位",name:"price_unit",width:200,xtype:"combo",code:"PRICE_UNIT"},
      {label:"折扣值",name:"price_value"},
      {label:"折扣值描述",name:"price_value_desc",width:200}
   ];
   
 var priceCondListField=[
      {label:"折扣ID",name:"price_id",hide:"all"},
      {label:"条件ID",name:"cond_id",hide:"all"},
      {label:"条件项",name:"cond_type",code:"COND_TYPE",xtype:"combo"},
      {label:"条件操作符",name:"cond_op",code:"COND_OP",xtype:"combo"},
      {label:"条件值",name:"cond_value"},
      {label:"条件值名称",name:"cond_value_text"},
      {label:"条件是否必备",name:"cond_must",code:"COND_MUST",xtype:"combo"}
   ];
 
 var priceScopeListField=[
      {label:"折扣ID",name:"price_id",hide:"all"},
      {label:"范围类型",name:"scope_type",code:"SCOPE_TYPE",xtype:"combo"},
      {label:"范围值",name:"scope_value",allowBlank:false,code:"SCOPE_VALUE"},
      {label:"开始时段",name:"start_time",xtype:"time",value:"00:00"},
      {label:"结束时段",name:"end_time",xtype:"time",value:"23:59"},
      {label:"范围ID",name:"scope_id",hide:"all"}
      
   ];

 var priceFoodTypeField=[
      {label:"折扣ID",name:"price_id",hide:"all"},
      {label:"范围类型",name:"scope_type",hide:"all"},
      {label:"菜品类型",name:"scope_value",code:"FOOD_TYPE",xtype:"combo"},
      {label:"开始时段",name:"start_time",xtype:"time",value:"00:00"},
      {label:"结束时段",name:"end_time",xtype:"time",value:"23:59"},
      {label:"范围ID",name:"scope_id",hide:"all"}
   ];
 var foodFields=[
  {label:"菜品ID",name:"food_id",hide:"all"},
  {label:"菜品名称",name:"food_name"},
  {label:"菜品单价",name:"food_price"},
  {label:"菜品类型",  name:"food_type",xtype:"combo",code:"FOOD_TYPE"}

];
 var priceFoodIdField=[
      {label:"折扣ID",name:"price_id",hide:"all"},
      {label:"范围类型",name:"scope_type",hide:"all"},
      {label:"范围类型",name:"scope_value",hide:"all"},
      {label:"菜品名称",name:"food_name",readOnly:true,xtype:"trigger",columns:foodFields,checkbox:true,singleSelect:false,afterRowClick:function(){},buttons:[{text:"确认",handler:function(){getCmp("dialogWindow_xudahu111").destroy();}}],map:{food_id:"scope_value"},tableURL:"/rule/value/all1.do",getData:function(){return {scope_value:getData("app_business_rule_discount_3",["scope_value"]).scope_value};}},
      {label:"开始时段",name:"start_time",xtype:"time",value:"00:00"},
      {label:"结束时段",name:"end_time",xtype:"time",value:"23:59"},
      {label:"范围ID",name:"scope_id",hide:"all"}
   ];
 var setFields=[
  {label:"座位号",name:"set_no",hide:"all",readOnly:true},
  {label:"座位号",name:"balcony_code",hide:"all",readOnly:true},
  {label:"楼层",name:"dining_floor",readOnly:true},
  {label:"所属位置",name:"belong_to_text",readOnly:true},
   {label:"所属位置",name:"belong_to",readOnly:true,hide:"all"},
  {label:"座位名称",name:"balcony_name",readOnly:true},
  {label:"座位状态",name:"man_num",readOnly:true},
  {label:"最低消费类型",name:"mincost_type",readOnly:true,code:"MINCOST_TYPE"},
  {label:"最低消费金额",name:"mincost_money",readOnly:true}
];  
 var BelongToField=[
      {label:"折扣ID",name:"price_id",hide:"all"},
      {label:"条件ID",name:"cond_id",hide:"all"},
      {label:"条件项",name:"cond_type",value:"setbelong",hide:"form"},
      {label:"条件操作符",name:"cond_op",value:"=",hide:"form"},
      {label:"条件值",name:"cond_value",xtype:"combo",code:"BELONG_TO" ,znName:"cond_value_text"},
      {label:"位置名称",name:"cond_value_text",hide:"all"},
      {label:"条件是否必备",name:"cond_must",code:"COND_MUST",xtype:"combo"}
   ];
   
   
    var FoodTypeField=[
      {label:"折扣ID",name:"price_id",hide:"all"},
      {label:"条件ID",name:"cond_id",hide:"all"},
      {label:"条件项",name:"cond_type",hide:"form"},
      {label:"条件操作符",name:"cond_op",value:"=",code:"COND_OP",xtype:"combo"},
      {label:"菜金",name:"cond_value"},
      {label:"菜金",name:"cond_value_text",hide:"form"},
      {label:"条件是否必备",name:"cond_must",code:"COND_MUST",xtype:"combo"}
   ];
   

   
var CDRField=[
	{label:"折扣ID",name:"price_id",hide:"all"},
	{label:"折扣名称",name:"price_name"},
	{label:"卡级别",name:"vip_level",xtype:"combo",code:"VIP_LEVEL"},
	{label:"关系类型",name:"relat_type",xtype:"combo",code:"relat_type",value:"0"}
]

var CDRField1=[
	{label:"折扣ID",name:"price_id",hide:"all"},
//	{label:"折扣名称",name:"price_name"},
	{label:"卡级别",name:"vip_level",xtype:"combo",code:"VIP_LEVEL"},
	{label:"关系类型",name:"relat_type",xtype:"combo",code:"relat_type"}
]
 
 var priceList=createPageGrid({
     id         : "app_business_rule_discount_1",
     title      : "折扣规则列表",
     region     : "north",
     items      : priceListField,
     urls       : "/rule/price_plan_define.pages.go",
     page       : true,
     collapsible: true,
     filter     : false,
     split      : true,
     height     : 400,
     width      : 400,
     createFunction : function(e){
                          var v=createFormWindow({
                                   items    : priceFormField,
                                   width    : 400,
                                   height   : 300,
                                   cols     : 1,
                                   id       : "app_business_rule_discount_1",
                                   label    : "添加规则",
                                   action   : "/rule/create.do"
                                });
                           
                           v.show(this);     
                           v.on("beforedestroy",function(){reload("app_business_rule_discount_1");});
                      },
     modifyFunction : function(e){
                          var d=getData("app_business_rule_discount_1");
                          
                          if(d.price_state!="1"){
                             $alt("您没有选中一条规则来或者没有选中有效状态的规则来修改，请确认您的选择！");
                             return;
                          } 
 
                          var v=createFormWindow({
                                   items    : priceFormField,
                                   width    : 400,
                                   height   : 300,
                                   cols     : 1,
                                   id       : "app_business_rule_discount_1",
                                   label    : "修改规则",
                                   action   : "/rule/modify.do",
                                   close    : function(){reload("app_business_rule_discount_1");},
                                   data     : d
                                });
                           
                           v.show();   
                      },
      toolBarFields  : [
                  {text:"启用",iconCls:"gridItemGo",handler:function(){setPriceState("2");}},
                  "-",
                  {text:"停用",iconCls:"gridItemDel",handler:function(){setPriceState("1");}},
                  "-",
                  {text:"设置互斥关系",iconCls:"gridmultiadd",handler:function(){setCDR();}},
                  "-",
                  {text:"查看互斥关系",iconCls:"gridmultiadd",handler:function(){showCDR();}}
                  
                  ],
      
      rowclick       : function(){
                           var d=getData("app_business_rule_discount_1",["price_id"]);
                           load("app_business_rule_discount_3",d);
                           load("app_business_rule_discount_4",d);
                           load("app_business_rule_discount_5",d);
                       }                                 
                      
  });
   
 function setPriceState(st){
    var d=getData("app_business_rule_discount_1",["price_id","price_state"]);
    d.price_state=st;
    doPost("/rule/modify.do",d,function(){reload("app_business_rule_discount_1");});
 }
 
 function setCDR(){
 	var d = getData("app_business_rule_discount_1");
 	var v=createFormWindow({
                                   items    : CDRField,
                                   width    : 400,
                                   height   : 200,
                                   cols     : 1,
                                   id       : "app_business_rule_discount_1",
                                   label    : "修改规则",
                                   action   : "/rule/setRelet.do",
                                   close    : function(){reload("app_business_rule_discount_1");},
                                   data     : d,
                                   afterRowClick:function(){}
                                });
                           
                           v.show();  
 }
 
function showCDR(){
 	var d = getData("app_business_rule_discount_1",["price_id","vip_level"]);
 	var v=createGridWindow({
          columns  : CDRField1,
          width    : 400,
          height   : 300,
          cols     : 1,
          id       : "app_business_rule_discount_1",
          label    : "查看规则",
          tableURL : "/rule/showRelet.do",
          data	   : d         
                                  
                                });
   v.show();  
 }
 //判断是否可以对记录进行编辑
 function ruleEditCheck(){
    var d=getData("app_business_rule_discount_1",["price_state"]);
    if(d.price_state=="1") return true;
    $alt("只有选择一条有效的规则才能进行配置，无效或者启用的规则不能配置！");
    return false;
 } 
 
 //创建窗口定义
function cWin(o){
   return function(){
      if(!ruleEditCheck()) return;
      var d=getData("app_business_rule_discount_1",["price_id"]);
      if(o.data)Ext.apply(d,o.data);
      var v=createFormWindow({
          items   : o.items,
          width   : nvl(o.width,300),
          height  : nvl(o.height,200),
          cols    : 1,
          id      : o.self,
          label   : o.label,
          action  : o.action,
          close   : function(){reload(o.self);},
          data    : d
      });
      v.show();
   }   
 }

//创建ToolBarItem
function cToolItem(o){
  return {
     text    : o.text,
     iconCls : o.iconCls,
     handler : cWin(o)
  };
} 
  
 var priceScopeList=createPageGrid({
     id         : "app_business_rule_discount_3",
     title      : "折扣规则适应范围列表",
     region     : "center",
     items      : priceScopeListField,
     filter     : false,
     urls       : "/rule/scope/all.do",
     firstLoad  : false,
     bodyBorder : true,
     split      : true,
     width      : 300,
     toolBarFields :[
    			
                      cToolItem({
                        text   : "添加菜品类型",
                        iconCls: "gridItemAdd",
                        self   : "app_business_rule_discount_3",
                        items  : priceFoodTypeField,
                        data   : {scope_type:"1"},
                        action : "/price_plan_scope.insert.go",
                        label  : "添加规则适应范围"
                      }),"-",
                      cToolItem({
                        text   : "添加指定菜品",
                        iconCls: "gridItemAdd",
                        data   : {scope_type:"2"},
                        self   : "app_business_rule_discount_3",
                        items  : priceFoodIdField,
                       // action : "/price_plan_scope.insert.go",
                        action : "/rule/price_Insert.do",
                        label  : "添加规则适应范围"
                      }),"-",
                      
                      {
                        text   : "删除规则范围",
                        iconCls: "gridItemDel",
                        handler: function(){
                                              if(!ruleEditCheck()) return;
                                              if(!getData("app_business_rule_discount_3").scope_id){
                                                 $alt("请选择一条记录进行删除操作！");
                                                 return;
                                              }
                                              
                                              doPost("/price_plan_scope.delete.go",
                                                   getData("app_business_rule_discount_3"),
                                                   function(){reload("app_business_rule_discount_3");});
                                           }
                      }]             
  });
 
 var priceValueList=createPageGrid({
     id         : "app_business_rule_discount_4",
     title      : "折扣值列表",
     region     : "center",
     items      : priceValueListField,
     firstLoad  : false,
     urls       : "/rule/value/all.do",
     filter     : false,
     bodyBorder : true,
     split      : true,
     width      : 300,
     toolBarFields :[
                      cToolItem({
                        text   : "添加折扣值",
                        iconCls: "gridItemAdd",
                        self   : "app_business_rule_discount_4",
                        items  : priceValueListField,
                        action : "/price_plan_value.insert.go",
                        label  : "添加折扣值"
                      }),"-",
                      {
                        text   : "删除折扣值",
                        iconCls: "gridItemDel",
                        handler: function(){
                                              if(!ruleEditCheck()) return;
                                              if(!getData("app_business_rule_discount_4").value_id){
                                                 $alt("请选择一条记录进行删除操作！");
                                                 return;
                                              }
                                              
                                              doPost("/price_plan_value.delete.go",
                                                   getData("app_business_rule_discount_4"),
                                                   function(){reload("app_business_rule_discount_4");});
                                           }
                      }]
  });
  
   
 var priceCondList=createPageGrid({
     id         : "app_business_rule_discount_5",
     title      : "折扣条件列表",
     region     : "center",
     items      : priceCondListField,
     filter     : false,
     firstLoad  : false,
     urls       : "/rule/cond/all.do",
     bodyBorder : true,
     split      : true,
     width      : 300,
     toolBarFields :[
     	 cToolItem({
                        text   : "添加餐位条件",
                        iconCls: "gridItemAdd",
                        data   : {cond_type:"setbelong",cond_op:"="},
                        self   : "app_business_rule_discount_5",
                        items  : BelongToField,
                       // action : "/price_plan_scope.insert.go",
                        action : "/price_plan_condition.insert.go",
                        label  : "添加规则适应范围"
                      }),"-",cToolItem({
                        text   : "添加菜金条件",
                        iconCls: "gridItemAdd",
                        data   : {cond_type:"foodtype",cond_op:"="},
                        self   : "app_business_rule_discount_5",
                        items  : FoodTypeField,
                       // action : "/price_plan_scope.insert.go",
                        action : "/price_plan_condition.insert.go",
                        label  : "添加折扣条件"
                      }),"-",
                      cToolItem({
                        text   : "添加折扣条件",
                        iconCls: "gridItemAdd",
                        self   : "app_business_rule_discount_5",
                        items  : priceCondListField,
                        action : "/price_plan_condition.insert.go",
                        label  : "添加折扣值"
                      }),"-",
                      {
                        text   : "删除折扣条件",
                        iconCls: "gridItemDel",
                        handler: function(){
                                              if(!ruleEditCheck()) return;
                                              if(!getData("app_business_rule_discount_5").cond_id){
                                                 $alt("请选择一条记录进行删除操作！");
                                                 return;
                                              }
                                             
                                              doPost("/price_plan_condition.delete.go",
                                                   getData("app_business_rule_discount_5","cond_id"),
                                                   function(){reload("app_business_rule_discount_5");});
                                           }
                      }]
  });
  
  
 var priceEditPanel=new Ext.TabPanel({
  	 region           : "center",
  	 //bodyBorder     : false,
  	 //border         : false,
  	 hideBorders      : true,
  	 split            : true,
     defaults         : {autoScroll: true},
     resizeTabs       : true,
     activeTab        : 0,
     layoutOnTabChange: true,
     items      : [  
                    priceScopeList,
                    priceValueList,
                    priceCondList
                  ]
 })
 
 
 /** 定义基本修改按钮 **/
 


 return [priceList,priceEditPanel];
