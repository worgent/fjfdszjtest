var rightFields=[
      {label:"权限ID",name:"priv_id",allowBlank:false,hide:"all"},
      {label:"权限名称",name:"priv_name",allowBlank:false},
      {label:"权限状态",name:"priv_status",width:40,allowBlank:false,code:"STATUS"},
      {label:"权限类型",name:"priv_type",width:40,allowBlank:false,code:"PRIV_TYPE"},
      {label:"权限描述",name:"priv_desc"}
   ];
   
var resourceField=[
      {label:"权限资源ID",name:"res_priv_id",hide:"all"},
      {label:"权限ID",name:"priv_id",allowBlank:false,hide:"all"},
      {label:"资源ID",name:"res_id",allowBlank:false,hide:"all"},
      {label:"资源名称",name:"res_name",allowBlank:false},
      {label:"资源状态",name:"res_status",width:40,allowBlank:false,code:"RES_STATUS"},
      {label:"资源类型",name:"res_type",width:40,code:"RES_TYPE"},
      {label:"资源中文名称",name:"res_lable"},
      {label:"资源路径",name:"res_path",width:200},
      {label:"父亲资源ID",name:"parent_res_id",hide:"all"}
   ]; 
var selectResourceField=[
      {label:"资源ID",name:"res_id",allowBlank:false,hide:"all"},
      {label:"资源名称",name:"res_name",allowBlank:false},
      {label:"资源状态",name:"res_status",allowBlank:false},
      {label:"资源类型",name:"res_type"},
      {label:"资源中文名称",name:"res_lable"},
      {label:"资源路径",name:"res_path"}
   ]; 

var resourceEditField=[
      {label:"权限ID",name:"priv_id",hide:"all"},
      {label:"资源ID",name:"res_id",hide:"all"},
      {label:"资源名称",name:"res_lable",xtype:"trigger",tableURL:"/staff/res_notin.do",getData:gData,columns:selectResourceField},
      {label:"操作类型",name:"oper_type",value:"00",allowBlank:false,xtype:"combo",code:"OPER_TYPE"},
      {label:"限制类型",name:"is_promission",value:"1",allowBlank:false,xtype:"combo",code:"IS_PROMISSION"},
      {label:"资源权限ID",name:"res_priv_id",allowBlank:false,hide:"all"}
   ];     

function gData(){
   var d=getData("system_resource_grid",["priv_id"]);
   return d;
}

var staffGrid=createPageGrid({
     id           : "system_resource_grid",
     title        : "权限列表",
     items        : rightFields,
     urls         : "/privilege_info.pages.go?priv_type=00",
     region       : "west",
     split        : true,
     width        : 300,
     page         : true,
     rowclick     : function(){
                       var d=getData("system_resource_grid",["priv_id"]);
                       load("system_staffrole_grid",d);
                    }
});


var rolegrid=createPageGrid({
     id           : "system_staffrole_grid",
     title        : "资源列表",
     items        : resourceField,
     urls         : "/staff/res_all.do",
     firstLoad    : false,
     page         : true,
     filter       : false,
     split        : true,
     region       : "center",
     createFunction : function(){
                         var w=createFormWindow({
                            id       : "system_staffrole_grid_edit",
                            title    : "添加资源",
                            items    : resourceEditField,
                            cols     : 1,
                            width    : 400,
                            height   : 300,
                            action   : "privilege_respirce_map.insert.go",
                            close    : cLoadEvent("system_staffrole_grid"),
                            data     : getData("system_resource_grid",["priv_id"])
                         });
                         w.show(this);
                      },
                      
     deleteFunction : function(o){
                            var d=getData("system_staffrole_grid","res_priv_id");
                            doPost("privilege_respirce_map.delete.go",d,cLoadEvent("system_staffrole_grid"));
                       }             
});

return [staffGrid,rolegrid];   


