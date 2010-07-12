var mFields=[
  {label:"菜品ID",name:"food_id",hidden:true,hide:true},
  {label:"菜名",name:"food_name"},
  {label:"菜品类型",  name:"food_type",xtype:"combo",code:"FOOD_TYPE"},
  {label:"价格",  name:"food_price"}
];

var mFieldsSelected=[
  {label:"菜品ID",name:"food_id",hidden:true,hide:true},
  {label:"菜名",name:"food_name"},
  {label:"菜品类型",  name:"food_type",xtype:"combo",code:"FOOD_TYPE"},
  {label:"价格",  name:"food_price"},
  {label:"数量",  name:"food_count"}
];

var payList=cCalculatePanelFast(["1","2","3","4","5"],"");



var attrValueTableForm=createTableFormNoButton({
   id:"food_menu_grid",
   tableTitle:"菜单列表",
   formTitle:"已选菜单",
   horizontal:true,
   tableURL:"/food_info.pages.go",
   table:"sys_attribute_value",
   firstLoad:false,
   formCols:1,
   items:mFields,
   horizontal:false
});
   
   

var rightPart=new Ext.Panel({
    bodyBorder:false,
    layout:"border",
    region:"center",
    items:attrValueTableForm
});

return [rightPart,payList];   


