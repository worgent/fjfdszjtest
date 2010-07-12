var mFields=[
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"},
  {label:"联系电话",  name:"staff_phone"},
  {label:"家庭住址",  name:"staff_addr"},
  {label:"生日",  name:"staff_birthday"}
];


var roleFields=[
      {label:"员工权限id",name:"staff_priv_id",allowBlank:false,hide:"all"},
      {label:"员工ID",name:"staff_id",allowBlank:false,hide:"all"},
      {label:"权限ID",name:"priv_id",allowBlank:false,hide:"all"},
      {label:"权限名称",name:"priv_name",allowBlank:false},
      {label:"权限状态",name:"priv_status",allowBlank:false,code:"STATUS"},
      {label:"权限类型",name:"priv_type",allowBlank:false,code:"PRIV_TYPE"},
      {label:"权限描述",name:"priv_desc"}
   ];



var staffGrid=createPageGrid({
     id           : "system_staff_grid",
     title        : "员工列表",
     items        : mFields,
     urls         : "/staff_info.pages.go",
     region       : "west",
     split        : true,
     width       : 300,
     page         : true,
     rowclick     : function(){
                       var d=getData("system_staff_grid");
                       load("system_staffrole_grid",{staffid:d.staff_id});
                    }
});


var rolegrid=createPageGrid({
     id           : "system_staffrole_grid",
     title        : "角色列表",
     items        : roleFields,
     urls         : "/staff/role_all.do",
     firstLoad    : false,
     page         : true,
     filter       : false,
     split        : true,
     region       : "center",
     createFunction : editGrid({
                         data   : {staffid:"-1"},
                         title  : "添加角色",
                         action : function(o){
                            var d=getData("system_staff_grid",["staff_id"]);
                            d["priv_id"]=o.priv_id;
                            doPost("staff_role_map.insert.go",d,cLoadEvent("system_staffrole_grid"));
                         }
                      }),
                      
     deleteFunction : function(o){
                            var d=getData("system_staffrole_grid","staff_priv_id");
                            doPost("staff_role_map.delete.go",d,cLoadEvent("system_staffrole_grid"));
                       }             
});


function editGrid(o){

   return function(){
      var oo=Ext.apply({},o);
      var ab=getData("system_staff_grid",["staff_id"]);
      oo.data.staffid=ab["staff_id"];
      var d={};
      var w=createGridWindow({
       id        : "staffrow_edit",
       tableURL  : "staff/role_notin.do",
       columns   : roleFields,
       label     : oo.title,
       data      : oo.data,
       afterRowClick:function(a){Ext.apply(d,a);}
      });
      w.addButton({text:"保存",handler:function(){oo.action(d);w.destroy();}});
      w.show();
   }
}



return [staffGrid,rolegrid];   


