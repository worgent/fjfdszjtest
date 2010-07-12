var wFields=[
  {label:"店面ID",name:"sf_id",hidden:true,hide:true},
  {label:"店面名称",name:"sf_name",query:true},
  {label:"店面地址",  name:"sf_addr"}
];
var staffFields=[
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"}

];
var mFields=[
  {label:"店面ID",name:"sf_id",hidden:true,hide:true},
  {label:"店面名称",name:"sf_name",xtype:"trigger",table:"storefront_info",columns:wFields},
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"},
  {label:"联系电话",  name:"staff_phone"},
  {label:"家庭住址",  name:"staff_addr"},
  {label:"生日",  name:"staff_birthday"},
  {label:"角色ID",  name:"role_id",value:"0",hidden:true,hide:true},
  {label:"登陆密码",  name:"staff_password",hidden:true}

];


function passwordReset(){
   var fld=[
       {label:"新密码",width:60,name:"accounts_password",table:"staff_info",emptyText:"输入新密码",inputType:"password"},
       {label:"员工ID",name:"staff_id",hide:"all"}
   ];
   var d=getData("app_system_staff1_table","staff_id");
   var w=createFormWindow({
       id        : "staff_password_reset",
       label     : "密码重置",
       items     : fld,
       data      : d,
       action    : "/staff/keepaccounts.do",
       height    : 125,
       cols      : 1,
       width     : 280
   });
   
   w.show();
}

function accountsadd(){
    	var buyOrderListFields=[
      {label:"记账人姓名",name:"staff_name",xtype:"trigger",table:"staff_info",columns:staffFields},
       {label:"员工ID",name:"staff_id",hide:"all"}
   ];
      var d=getData("app_system_staff1_table","staff_id");
   var w=createFormWindow({
       id        : "accounts_add",
       label     : "添记记账用户",
       items     : buyOrderListFields,
       action    : "/staff/addkeepaccounts.do",
       data      : d,
       height    : 125,
       cols      : 1,
       width     : 280
   });
   
   w.show();
   w.on("beforedestroy",function(){reload("app_system_staff1_table");});
}
function kai_accounts(){
   doGet("/kai_account.do",null,function(dt){ $alt("成功开启记账功能.");});
}
function close_accounts(){
   doGet("/close_account.do",null,function(dt){ $alt("成功关闭记账功能.");});
}

return createTableForm1({
   id:"app_system_staff1",
   tableTitle:"记账员工列表",
   horizontal:true,
   tableURL:"/app/system/staff_pages1.do",
   deleteURL:"/staff/staff_delete.do",
   table:"staff_info",
   formCols:2,
   items:mFields,
   horizontal:false,
   toolBar :[{text:"添加",iconCls:"gridItemAdd",handler:accountsadd},"-",
   			 {text:"密码设置",iconCls:"password",handler:passwordReset},"-",
   			 {text:"开启记账功能",iconCls:"gridItemAdd",handler:kai_accounts},"-",
   			 {text:"关闭记账功能",iconCls:"gridItemAdd",handler:close_accounts}
   			]
});
