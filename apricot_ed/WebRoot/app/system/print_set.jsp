var printConfigFields=[
   {label:"打印机ID",name:"printer_id",hide:"all"},
   {label:"菜品类型",name:"food_type",hide:"grid",code:"FOOD_TYPE",xtype:"combo"},
   {label:"打印机类型",xtype:"combo",name:"printer_type",hide:"grid",code:"PRINTER_TYPE"},
   {label:"打印机模式",xtype:"combo",name:"print_mode",hide:"grid",code:"PRINTER_MODE"},
   {label:"菜品类型",name:"food_type_text",hide:"form"},
   {label:"打印机中文",name:"printer_label"},
   {label:"打印机类型",name:"printer_type_text",hide:"form"},
   {label:"打印机名称",name:"printer_name"},
   {label:"打印机IP",name:"printer_ip"},
   {label:"打印机端口",name:"printer_port"}
];


var printSetFields=[
   {label:"打印时间间隔(毫秒)",name:"timesteep"},
   {label:"客户单打印机类型",name:"CashierDeskStream.printerType",xtype:"combo",code:"PRINTER_TYPE"},
   {label:"客户单打印机中文",name:"CashierDeskStream.printerLabel"},
   {label:"客户单打印机名称",name:"CashierDeskStream.printerName"},
   {label:"客户单打印机IP",name:"CashierDeskStream.printerHost"},
   {label:"客户单打印机端口",name:"CashierDeskStream.printerPort"}
];

var pcf={
   title:"菜品打印机设置",
      items:createTableForm({
      id:"print_config_set_tf",
      items:printConfigFields,
      tableURL:"app/system/printers.do",
      table:"print_config"
   }),
   border:false,
   layout:"border"
}

var form=createFormPanel({
   id:"print_config_set_tf_global",
   title:"全局打印机设置",
   cols:1,
   labelWidth:120,
   items:printSetFields
});

form.on("show",function(f){
   
   doGet("/print/global/getParameters.print",{},function(d){
      setFormValues("print_config_set_tf_global",d.data);
   });
});

form.addButton({text:"确定",handler:function(d){
    var o=Ext.apply(getFormData("print_config_set_tf_global"),getFormValues("print_config_set_tf_global"));
    doGet("/print/global/setParameters.print",o,function(d){
        alert("这是全局打印参数成功！");
    });
}});

form.addButton({text:"应用",handler:function(d){
    var o=Ext.apply(getFormData("print_config_set_tf_global"),getFormValues("print_config_set_tf_global"));
    
    doGet("/print/global/applyParameters.print",o,function(d){
        alert("这是全局打印参数成功！");
    });
}});


return [{xtype:"tabpanel",activeTab:0,region:"center",bodyStyle:"padding:2px",items:[pcf,form]}];


