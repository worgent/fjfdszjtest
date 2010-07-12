return createTableForm({
   id:"app_shop_shop_printer",
   tableTitle:"打印机列表",
   formTitle:"打印机信息",
   horizontal:true,
   tableURL: "/storefront_printer.pages.go",
   deleteURL:"/storefront_printer.delete.go",
   table:"storefront_printer",
   formCols:1,
   items:[
    	 {label:"打印机标识",name:"printer_id",hide:"all"},
    	 {label:"部门",name:"printer_part",allowBlank:false,xtype:"combo",code:"PART_PRINT"},
    	 {label:"打印机名称",name:"printer_name",allowBlank:false,sortable:true},
    	 {label:"打印机地址",name:"server_address",width:200},
    	 {label:"是否启用",name:"usesd_state",allowBlank:false,xtype:"combo",code:"USED_STATE"},
    	 {label:"备注",name:"memo"}
    ]
});