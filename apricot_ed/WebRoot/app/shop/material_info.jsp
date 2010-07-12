return createTableForm({
   id:"app_shop_material_info",
   tableTitle:"源材料列表",
   formTitle:"源材料基本信息",
   horizontal:true,
   table:"material_info",
   width:400,
   formCols:1,
   items:[
       {label:"原材料ID",name:"mat_id",hidden:true,hide:true},
    	 {label:"原材料名称",name:"mat_name"},
    	 
    	 {label:"原材料类型",name:"mat_type",code:"MAT_TYPE",xtype:"combo",allowBlank:false},
    	 {label:"原材料计量单位",name:"mat_measure_unit",code:"MEASURE_UNIT",xtype:"combo",allowBlank:false},
    	 {label:"源材料价格",name:"mat_price",xtype:"number",allowBlank:false},
    	 {label:"原材料说明",name:"mat_memo",xtype:"textarea",hidden:true}
    ]
});

