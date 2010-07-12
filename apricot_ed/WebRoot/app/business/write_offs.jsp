var wofields=[
	{label:"订单号",name:"order_no",hide:"form",width:120,readOnly:true},
    {label:"订单号",name:"order_id",hide:"all"},
    {label:"结账号",name:"pay_id",hide:"all"},
    {label:"订单类型",name:"order_type",hide:"form",xtype:"combo",code:"ORDER_TYPE",width:60},
    {label:"宾客姓名",name:"prearrange_name"},
    {label:"联系电话",name:"prearrange_phone"},
    {label:"VIP卡号",name:"vip_card_no"},
    {label:"金额",name:"total"},
    {label:"积分",name:"gain_point"},
    {label:"备注",name:"order_reseon",xtype:"textarea"},
    {label:"记录人",name:"staff_id",hide:"all"},
    {label:"记录人",name:"staff_name"}
];

var staffFields=[
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"}
];

var writeOffsgrid=createPageGrid({
   id:"app_write_offs",
   title:"充帐列表",
   urls:"/order/write_offs_order_pages.do",
   items:wofields,
   region: "center",
   createFunction : newAddWindow
   /**
   modifyFunction : newEditWindow,
   deleteFunction : function(){
	                    var d=getData("app_write_offs");
	                    doPost("/order/write_offs_order_delete.do",d,cLoadEvent("app_write_offs"));
	               }*/
});

function newAddWindow()
{
	var woAddFields=[
		{label:"授权人帐号",name:"loginname",allowBlank:false,blankText:'帐户不能为空'},
		{label:"密码",name:"password",allowBlank:false,blankText:'密码不能为空',inputType:"password"},
	    {label:"宾客姓名",name:"prearrange_name",id:"prearrange_name_add"},
       	{label:"联系电话",name:"prearrange_phone",id:"prearrange_phone_add",listeners:{"blur":function(){
	      	doGet("/vip/vip_member_selectPhone.do?custPhone="+getValue("prearrange_phone_add"),null,function(date){
	      	var vipNO=Ext.getCmp("vip_card_no_add");
	      	var userName=Ext.getCmp("prearrange_name_add");
	      	if(date["vip_no"] == NaN)
	      	{
	      	}
	      	else
	      	{
	      		vipNO.setValue(date["vip_no"]);
	      		userName.setValue(date["cust_name"]);
	      	}
	      	})
	      }}},
	       {label:"VIP卡号",name:"vip_card_no",id:"vip_card_no_add",listeners:{"blur":function(){
	       	doGet("/vip/vip_member_selectVipCard.do?custVipNo="+getValue("vip_card_no_add"),null,function(date){
	       	var phone=Ext.getCmp("prearrange_phone_add");
	      	var userName=Ext.getCmp("prearrange_name_add");
	      	if(date["cust_phone"] == NaN)
	      	{
	      	}
	      	else
	      	{
	      		phone.setValue(date["cust_phone"]);
	      		userName.setValue(date["cust_name"]);
	      	}
	       	})
	       }}},
	    {label:"金额",name:"total",allowBlank:false},
	    {label:"积分",name:"gain_point",allowBlank:false},
	    {label:"备注",name:"order_reseon",xtype:"textarea"}
	];
	var w=createFormWindow({
       id       : "write_offs_order_add",
       title    : "添加",
       items    : woAddFields,
       cols     : 1,
       width    : 400,
       height   : 300,
       action   : "/order/write_offs_order_add.do",
       close    : cLoadEvent("app_write_offs")
    });
    w.show(this);
}
function newEditWindow()
{
	var woEditFields=[
		{label:"订单号",name:"order_no",hide:"all"},
    	{label:"订单号",name:"order_id",hide:"all"},
		{label:"结账号",name:"pay_id",hide:"all"},
		{label:"授权人帐号",name:"loginname",allowBlank:false,blankText:'帐户不能为空'},
		{label:"密码",name:"password",allowBlank:false,blankText:'密码不能为空',inputType:"password"},
	    {label:"宾客姓名",name:"prearrange_name",id:"prearrange_name_edit"},
       	{label:"联系电话",name:"prearrange_phone",id:"prearrange_phone_edit",listeners:{"blur":function(){
	      	doGet("/vip/vip_member_selectPhone.do?custPhone="+getValue("prearrange_phone_edit"),null,function(date){
	      	var vipNO=Ext.getCmp("vip_card_no_edit");
	      	var userName=Ext.getCmp("prearrange_name_edit");
	      	if(date["vip_no"] == NaN)
	      	{
	      	}
	      	else
	      	{
	      		vipNO.setValue(date["vip_no"]);
	      		userName.setValue(date["cust_name"]);
	      	}
	      	})
	      }}},
	       {label:"VIP卡号",name:"vip_card_no",id:"vip_card_no_edit",listeners:{"blur":function(){
	       	doGet("/vip/vip_member_selectVipCard.do?custVipNo="+getValue("vip_card_no_edit"),null,function(date){
	       	var phone=Ext.getCmp("prearrange_phone_edit");
	      	var userName=Ext.getCmp("prearrange_name_edit");
	      	if(date["cust_phone"] == NaN)
	      	{
	      	}
	      	else
	      	{
	      		phone.setValue(date["cust_phone"]);
	      		userName.setValue(date["cust_name"]);
	      	}
	       	})
	       }}},
	    {label:"金额",name:"total",allowBlank:false},
	    {label:"积分",name:"gain_point",allowBlank:false},
	    {label:"备注",name:"order_reseon",xtype:"textarea"}
	];
	var w=createFormWindow({
       id       : "write_offs_order_modify",
       title    : "添加",
       items    : woEditFields,
       cols     : 1,
       width    : 400,
       height   : 300,
       action   : "/order/write_offs_order_modify.do",
       data     : getData("app_write_offs"),
       close    : cLoadEvent("app_write_offs")
    });
    w.show(this);
}
return[writeOffsgrid];