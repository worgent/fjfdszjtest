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
       {label:"密码",width:60,name:"accounts_password",table:"staff_info",allowBlank:false,inputType:"password"},
       {label:"确认密码",width:60,name:"accounts_password1",table:"staff_info",allowBlank:false,inputType:"password"}
//       {label:"时间",name:"p_time",value:new Date().dateFormat("Y-m-d H:i:s")},
//       {label:"员工ID",name:"staffid",value:getData("app_system_staff1_table",["staff_id"]).staff_id}
   ];
   var d=getData("app_system_staff1_table","staff_id");
   var w=createFormWindow({
       id        : "staff_password_reset",
       label     : "密码设置",
       items     : fld,
       data      : d,
       action    : "/staff/keepaccounts.do",
       height    : 140,
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
   doGet("/kai_account.do",null,function(dt){ 
   		$alt("成功开启记账功能.");
   		var k=Ext.getCmp("kai");
   		 var c=Ext.getCmp("close");
   		 k.setText("已开启记账功能");
   		 c.setText("关闭记账功能");
   		 k.setDisabled(true);
	 	 c.setDisabled(false);
   	});
}
function close_accounts(){
   doGet("/close_account.do",null,function(dt){ 
  	 $alt("成功关闭记账功能.");
  	 var c=Ext.getCmp("close");
  	 var k=Ext.getCmp("kai");
  	 c.setText("已关闭记账功能");
  	 k.setText("开启记账功能");
  	 k.setDisabled(false);
	 c.setDisabled(true);
  	 });
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
   			 {text:"开启记账功能",id:"kai",iconCls:"gridItemAdd",handler:kai_accounts,
   			 listeners:{
					"render":function(){
					doGet("/keepaccount_calc.do",null,function(dt){
						var k=Ext.getCmp("kai");
						var c=Ext.getCmp("close");
						if(dt["kai_accounts"]==0){
		   					k.setText("已开启记账功能");
		   					k.setDisabled(true);
		   					c.setDisabled(false);
						}
					});
					
					}   			 
   				 }
   			 },"-",
   			 {text:"关闭记账功能",id:"close",iconCls:"gridItemAdd",handler:close_accounts,
   			 	listeners:{
					"render":function(){
					doGet("/keepaccount_calc.do",null,function(dt){
						var c=Ext.getCmp("close");
						var k=Ext.getCmp("kai");
						if(dt["kai_accounts"]==1){
		   					c.setText("已关闭记账功能");
		   					c.setDisabled(true);
		   					k.setDisabled(false);
						}
					});
					
					}   			 
   				 }
   			 },"-"
   			]
});
