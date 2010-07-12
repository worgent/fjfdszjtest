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
  {label:"员工ID",name:"staff_id",id:"staff_id"},
  {label:"初始金额(￥)",name:"staff_cash",id:"staff_cash",readOnly:true},
   {label:"上一班遗留金(￥)",name:"priv_cash",id:"priv_cash",readOnly:true},
   {label:"优惠券金额(￥)",name:"youhui_total",id:"youhui_total",readOnly:true},
   {label:"现金金额(￥)",name:"fact_pay_total",id:"fact_pay_total",readOnly:true},
   {label:"刷卡金额(￥)",name:"fact_pay_card",id:"fact_pay_card",readOnly:true},
   {label:"赊账金额(￥)",name:"book_cash",id:"book_cash",readOnly:true},
   {label:"上班时间",name:"u_time",readOnly:true},
	{label:"日期",name:"r_date",readOnly:true}
];


function passwordReset(){
   var fld=[
  
   {label:"当日金额(￥)",id:"curMoney",width:60,name:"cur_Money",readOnly:true,
	   listeners:{
		   	"render":function(){
		   	var a=Ext.getCmp("curMoney");
		   		doGet("/staff/curr_money.do","",function(dt){
		   		a.setValue(dt["totalmoney"]);
		   		});
		   	}
	   }
   },
   {label:"实际金额(￥)",width:60,id:"factcash",name:"fact_cash",allowBlank:false,emptyText:"请输入实际金额(￥)",
   		listeners:{
   			"blur":function(){
   				var a=Ext.getCmp("factcash");
   				var b=Ext.getCmp("curMoney");
   				var t=Ext.getCmp("takecash");
   				var c=b.getValue()-a.getValue();
   				t.setValue(c);
   			}
   		}
   },
    {label:"截流金额(￥)",id:"takecash",width:60,name:"take_cash",readOnly:true},
     {label:"差异说明",id:"diffmemo",width:60,name:"diff_memo",xtype:"textarea"}
       
   ];
   var w=createFormWindow({
       id        : "staff_password_reset",
       label     : "核查营业金额",
       items     : fld,
       action    : "/staff/curr_over.do",
       height    : 280,
       cols      : 1,
       width     : 330
   });
   
   w.show();
}

function login(){

doGet("/staff/before_over.do",null,function(dt){
/**
		if(dt["flag"]=="YES"){
		$alt("交接班前请先核查营业金额！")
		}else{
*/
var fpt = getCmp("fact_pay_total").getValue();
var sc = getCmp("staff_cash").getValue();
var pc = getCmp("priv_cash").getValue();
var si = getCmp("staff_id").getValue();
var total = Number(fpt)+Number(sc)+Number(pc);
 var simple = new Ext.FormPanel({
    	        id:'win1',
				labelWidth: 75, 			
				baseCls: 'x-plain',
				defaults: {width: 150},
				defaultType: 'textfield',
				items: [{
						fieldLabel: '帐户',
						name: 'loginname',
						//anchor:'95%',
						allowBlank:false,
						blankText:'帐户不能为空'
					},{
						fieldLabel: '密码',
						//anchor:'95%',
						name: 'password',
						allowBlank:false,
						blankText:'密码不能为空',
						inputType:"password"
					},{fieldLabel:"员工ID",name:"staff_id",value:si},
					{
	            	id:"mon",
	                fieldLabel: '当日金额',
	                readOnly:true,
	                value:total,
	                name: 'dayMoney',
	                regex:/^\d$/,
	                regexText:"请输入数字"
	                },
	                {
	            	id:"balance",
	                fieldLabel: '遗留金额',
	                allowBlank:false,
	                name: 'balance',
	                value:'0',
	                regex:/^\d$/,
	                regexText:"请输入数字",
	                listeners:{"blur":function(){
	                		var fc = Ext.getCmp("factCash");
	                		fc.setValue(Number(total-getValue("balance")));
	                	}}
	                },
	                {
	            	id:"factCash",
	                fieldLabel: '实缴金额',
	                readOnly:true,
	                value:total,
	                name: 'factCash'
	                },
	                {
	            	id:"money",
	                fieldLabel: '初始金额',
	                allowBlank:false,
	                name: 'money'
	                },
	                {
	            	id:"diffmemo",
	            	xtype:"textarea",
	                fieldLabel: '备注',
	                name:"diff_memo"
	                }
				],

				buttons: [{
					text: '登陆',
					handler:function(){
					 doPost("/login1.do",getFormValues("win1"),function(rt){
					 window.location.href=currentPageBaseHREF+"/app/main.jsp";
					 },false);
					}
				},{
					text: '关闭',
					handler:function(){ getCmp("win").destroy();}
				}]
			});    

	 win = new Ext.Window({
						id:'win',
						title:'换班登陆',
						layout:'fit',					
						width:300,
						height:350,
						plain:true,
                        bodyStyle:'padding:5px;',
						maximizable:false,
						closeAction:'close',
						closable:false,
						collapsible:true,
						plain: true,
             		    modal:true ,
						buttonAlign:'center',
						items:simple
					});
					win.show();
//	}
		
});					
}

function noLogin(){

doGet("/staff/before_over.do",null,function(dt){
var fpt = getCmp("fact_pay_total").getValue();
var sc = getCmp("staff_cash").getValue();
var pc = getCmp("priv_cash").getValue();
var si = getCmp("staff_id").getValue();
var total = Number(fpt)+Number(sc)+Number(pc);
 var simple = new Ext.FormPanel({
    	        id:'win1',
				labelWidth: 75, 			
				baseCls: 'x-plain',
				defaults: {width: 150},
				defaultType: 'textfield',
				items: [
					{fieldLabel:"员工ID",name:"staff_id",value:si},
					{
	            	id:"mon",
	                fieldLabel: '当日金额',
	                readOnly:true,
	                value:total,
	                name: 'dayMoney',
	                regex:/^\d$/,
	                regexText:"请输入数字"
	                },
	                {
	            	id:"balance",
	                fieldLabel: '遗留金额',
	                allowBlank:false,
	                name: 'balance',
	                value:'0',
	                regex:/^\d$/,
	                regexText:"请输入数字",
	                listeners:{"blur":function(){
	                		var fc = Ext.getCmp("factCash");
	                		fc.setValue(Number(total-getValue("balance")));
	                	}}
	                },
	                {
	            	id:"factCash",
	                fieldLabel: '实缴金额',
	                readOnly:true,
	                value:total,
	                name: 'factCash'
	                },
	                {
	            	id:"money",
	                fieldLabel: '初始金额',
	                allowBlank:false,
	                name: 'money'
	                },
	                {
	            	id:"diffmemo",
	            	xtype:"textarea",
	                fieldLabel: '备注',
	                name:"diff_memo"
	                }
				],

				buttons: [{
					text: '上交',
					handler:function(){
					 doPost("/login2.do",getFormValues("win1"),function(rt){
					 window.location.href=currentPageBaseHREF+"/logout.jsp";
//					 window.location.href=currentPageBaseHREF+"/app/main.jsp";
					 },false);
					}
				},{
					text: '关闭',
					handler:function(){ getCmp("win").destroy();}
				}]
			});    

	 win = new Ext.Window({
						id:'win',
						title:'换班登陆',
						layout:'fit',					
						width:300,
						height:350,
						plain:true,
                        bodyStyle:'padding:5px;',
						maximizable:false,
						closeAction:'close',
						closable:false,
						collapsible:true,
						plain: true,
             		    modal:true ,
						buttonAlign:'center',
						items:simple
					});
					win.show();
//	}
		
});					
}

return createTableForm2({
   id:"app_system_staff2",
   tableTitle:"员工换班",
   horizontal:true,
   tableURL:"/staff_Reshuffle/page.do",
   table:"staff_business_info",
   formCols:2,
   items:mFields,
   horizontal:false,
   toolBar :[
//   			 {text:"核查营业金额",iconCls:"password",handler:passwordReset},"-",
   			 {text:"换班",iconCls:"password",handler:login},"-",
   			 {text:"上交",iconCls:"password",handler:noLogin},"-"
   			]
});
