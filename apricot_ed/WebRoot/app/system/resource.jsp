var sFields=[
  {label:"资源ID",name:"res_id",hidden:true,hide:true},
  {label:"资源状态",name:"res_status",xtype:"combo",code:"STATUS"},
  {label:"资源类型",name:"res_type",xtype:"combo",code:"RES_TYPE"},
  {label:"资源名称",name:"res_lable"}
  ];

var mFields=[
  {label:"资源ID",name:"res_id",hidden:true,hide:true},
  {label:"资源名称",name:"res_name"},
  {label:"资源状态",name:"res_status",xtype:"combo",code:"STATUS"},
  {label:"资源类型",name:"res_type",xtype:"combo",code:"RES_TYPE"},
  {label:"资源中文名称",name:"res_lable"},
  {label:"资源路径",  name:"res_path"},
  {label:"上级资源",  name:"parent_res_id",hide:"all"},
  {label:"上级资源",  name:"parent_res_name",readOnly:true,
                     data:{res_type:"02"},xtype:"trigger",
                     table:"resource_info",
                     keyName : "res_lable",
                     columns:sFields,
                     map:{res_id:"parent_res_id",res_lable:"parent_res_name"},
                     checkValid:function(o){
                         var d=getFormValues("app_system_staff_form");
                         if(d.res_id==o.res_id){
                           $alt("当前资源不能选择自身作为上级资源!");
                           return false;
                         }
                         return true;
                     }
  }
];







return createTableForm({
   id:"app_system_resource",
   tableTitle:"权限资源列表",
   formTitle:"权限资源信息",
   horizontal:false,
   table:"resource_info",
   tableURL: "/resource/all.do",
   formCols:2,
   items:mFields,
   data : {res_status:"1",res_type:"02"}
});
