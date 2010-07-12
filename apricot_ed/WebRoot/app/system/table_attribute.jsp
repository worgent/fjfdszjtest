var wFields=[
  {label:"静态数据ID",name:"attr_id",hidden:true,hide:true},
  {label:"静态数据名称",name:"attr_name",query:true},
  {label:"静态编码",  name:"attr_code"}
];
var mFields=[
  {label:"映射ID",name:"tab_attr_id",hidden:true,hide:true},
  {label:"静态数据ID",name:"attr_id",hidden:true,hide:true},
  {label:"表名",name:"table_name",allowBlank:false},
  {label:"字段名",  name:"table_column",allowBlank:false},
  {label:"静态数据名称",  name:"attr_name",allowBlank:false,readOnly:true,xtype:"trigger",table:"sys_attribute",columns:wFields},
  {label:"静态数据编码",  name:"attr_code",allowBlank:false,readOnly:true}

];
return createTableForm({
   id:"app_system_table_attribute_value",
   tableTitle:"静态值列表",
   formTitle:"静态值信息",
   horizontal:true,
   tableURL:"/app/system/sys_table_attribute_pages.do",
   table:"sys_table_attribute",
   formCols:1,
   items:mFields,
   horizontal:false,
   onRowClick:function(f){}
});
