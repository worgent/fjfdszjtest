

var deptFields=[
 	{label:"部门ID",name:"dept_id",hide:true,hidden:true},
	{label:"部门名称",name:"dept_name"},
	{label:"上级部门",name:"parent_id",hide:true,hidden:true},
	{label:"上级部门",name:"parent_name"}
];

var mFields=[

  {label:"部门ID",name:"dept_id",hide:true,hidden:true},
  {label:"部门名称",name:"dept_name"},
  {label:"部门状态",name:"is_valid",xtype:"combo",code:"STATUS"},
  {label:"上级部门",name:"parent_id",hide:"all"},
  {label:"上级部门",name:"parent_name",xtype:"trigger",tableURL:"/app/system/getParent_id.do",table:"dept_info",getData:function() {return {dept_id:getFormValues("app_system_staff_form",["dept_id"]).dept_id};},columns:deptFields,map:{dept_name:"parent_name",dept_id:"parent_id",parent_id:"temp",parent_name:"temp"}},
  {label:"部门部径编码",name:"path_code",hide:"all"},
  {label:"上上级部门",name:"temp",hide:true,hidden:true}
  
];


return createTableForm({
   id:"app_system_staff",
   tableTitle:"部门列表",
   formTitle:"部门信息",
   horizontal:true,
   tableURL:"/app/system/dept_info_pages.do",
   addURL:"/app/system/dept_info/add.do",
   modifyURL:"/app/system/dept_info/modify.do",
   table:"dept_info",
   formCols:2,
   items:mFields,
   horizontal:false,
 
   OnRowClick:function(){}

});
