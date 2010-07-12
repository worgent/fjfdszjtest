  var foodFields=[
  {label:"菜品ID",name:"food_id",hide:"all"},
  {label:"菜品名称",name:"food_name"},
  {label:"菜品单价",name:"food_price"},
  {label:"菜品类型",  name:"food_type",xtype:"combo",code:"FOOD_TYPE"}

];
var listField=[
{label:"电子优惠券ID",name:"cp_id",allowBlank:false,hide:"all"},
{label:"优惠券名称",name:"cp_name",allowBlank:false},
      {label:"电子优惠券编号",name:"cp_num",allowBlank:false,id:"cpnum2",
      	 listeners:{
       			"render":function(){
       				var a=Ext.getCmp("cpnum2");
       				a.disable();
       			}
       		}
      },
      {label:"折扣比例",name:"cp_disct",allowBlank:false,maxLength:2,maxLengthText:"输入超过了两个字",id:"cpdisct1",emptyText:"例：85折,输入85",
      		listeners:{
      		"blur":function(){
      		var a=Ext.getCmp("cpdisct1");
      		var b=Ext.getCmp("cpcash1");
      			if(a.getValue()!=0){
      				b.setValue(0);
      				b.disable(true);
      			}else{
      				b.setValue();
      				b.enable(false);
      			}
      		}
      	}
      	
      },
      {label:"优惠金额",name:"cp_cash",allowBlank:false,id:"cpcash1",
      		listeners:{
      		"blur":function(){
      		var a=Ext.getCmp("cpdisct1");
      		var b=Ext.getCmp("cpcash1");
      			if(b.getValue()!=0){
      				a.setValue(0);
      				a.disable(true);
      			}else{
      				a.setValue();
      				a.enable(false);
      			}
      		}
      	}
      },
       {label:"是否赠送",name:"cp_oper",id:"cp_opm",allowBlank:false,xtype:"combo",code:"COUPON_GIVE",
       		listeners:{
       			"select":function(){
       				var a=Ext.getCmp("cp_opm");
		      		var b=Ext.getCmp("pricem");
		      		if(a.getValue()==0){
		      			b.setValue("0.00");
	      				b.disable();
		      		}else{
		      			b.setValue();
      					b.enable(false);
		      		}
       			}
       		}
       
       },
      {label:"销售价格",name:"cp_money",id:"pricem"},
      {label:"状态",name:"cp_status",xtype:"combo",allowBlank:false,code:"COUPON_STATUS"},
      //{label:"菜品类型",name:"cond_type",id:"condtype1",xtype:"combo",code:"FOOD_TYPE"},
     //  {label:"菜品名称",name:"food_name",readOnly:true,xtype:"trigger",columns:foodFields,checkbox:true,singleSelect:false,afterRowClick:function(){},buttons:[{text:"确认",handler:function(){getCmp("dialogWindow_xudahu111").destroy();}}],map:{food_id:"scope_value"},tableURL:"/rule/value/all1.do",getData:function(){return {scope_value:Ext.getCmp("condtype1").getValue()};}},
      
      {label:"发布日期",name:"pb_time",xtype:"datetime"},
      {label:"开始时间",name:"start_time",xtype:"datetime"},
      {label:"截止时间",name:"end_time",allowBlank:false,xtype:"datetime"}
     
     //{label:"消费类型",name:"is_full",xtype:"combo",code:"IS_FULL"},
    //  {label:"金额",name:"cond_value"},
     // {label:"发放(张)",name:"send_num"}
   ];
var listField1=[
 	  {label:"优惠券名称",name:"cp_name",allowBlank:false},
      {label:"发布日期",name:"pb_time",allowBlank:false,value:new Date().dateFormat("Y-m-d H:i:s")},
      {label:"优惠券编号",name:"cp_num",id:"cpnum1",
       listeners:{
       			"blur":function(){
       				var a=Ext.getCmp("cpnum1");
       				doGet("/fav/coupon_info.check.do?cp_num="+a.getValue(),"",function(dt){
       					if(dt["flag"]=="YES"){
       						return true;
       					}else{	
       					$alt("优惠券编号已存在！");
       					}
       				});
       			}
       		},
       	invalidText:'优惠券编号已经被注册！' 
       		
       },
      {label:"开始时间",name:"start_time",allowBlank:false,xtype:"datetime"},
      {label:"截止时间",name:"end_time",allowBlank:false,xtype:"datetime"},
      {label:"起始编号",name:"start_no"},
      {label:"截止编号",name:"end_no",allowBlank:false},
      {label:"状态",name:"cp_status",xtype:"combo",allowBlank:false,code:"COUPON_STATUS"},
      {label:"折扣比率",name:"cp_disct",allowBlank:false,maxLength:2,maxLengthText:"输入超过了两个字",emptyText:"例：85折-输入85",id:"cpdisct2",
      		listeners:{
      		"blur":function(){
      		var a=Ext.getCmp("cpdisct2");
      		var b=Ext.getCmp("cpcash2");
      			if(a.getValue()!=0){
      				b.setValue(0);
      				b.disable(true);
      			}else{
      				b.setValue();
      				b.enable(false);
      			}
      		}
      	}
      
      },
      {label:"优惠金额",name:"cp_cash",allowBlank:false,id:"cpcash2",
	      listeners:{
	      		"blur":function(){
	      		var a=Ext.getCmp("cpdisct2");
	      		var b=Ext.getCmp("cpcash2");
	      			if(b.getValue()!=0){
	      				a.setValue(0);
	      				a.disable(true);
	      			}else{
	      				a.setValue();
	      				a.enable(false);
	      			}
	      		}
	      	}
      	},
      {label:"是否赠送",name:"cp_oper",allowBlank:false,xtype:"combo",code:"COUPON_GIVE",id:"cp_opo",
      	listeners:{
       			"select":function(){
       				var a=Ext.getCmp("cp_opo");
		      		var b=Ext.getCmp("priceo");
		      		if(a.getValue()==0){
		      			b.setValue("0.00");
	      				b.disable();
		      		}else{
		      			b.setValue();
      					b.enable(false);
		      		}
       			}
       		}
      },
    	 {label:"销售价格",name:"cp_money",allowBlank:false,id:"priceo"}
    //  {label:"菜品类型",name:"cond_type",id:"condtype2",xtype:"combo",code:"FOOD_TYPE"},
   	//   {label:"菜品名称",name:"food_name",readOnly:true,xtype:"trigger",columns:foodFields,checkbox:true,singleSelect:false,afterRowClick:function(){},buttons:[{text:"确认",handler:function(){getCmp("dialogWindow_xudahu111").destroy();}}],map:{food_id:"scope_value"},tableURL:"/rule/value/all1.do",getData:function(){return {scope_value:Ext.getCmp("condtype2").getValue()};}},
    
   	//  {label:"消费类型",name:"is_full",xtype:"combo",code:"IS_FULL"},
   	 // {label:"金额",name:"cond_value"},
     // {label:"发放(张)",name:"send_num"}
   ];
   function checkname(){
   var a=Ext.getCmp("cpnum");
   			doGet("/fav/coupon_info.check.do?cpnum="+a.getValue(),"",function(dt){
       				alert(dt["flag"]);
       					if(dt["flag"]=="YES"){
       						return true;
       					}else{	
       					return false;
       					}
       				});
   }
  
 var singlelistField1=[
 	  {label:"优惠券名称",name:"cp_name",allowBlank:false},
      {label:"发布日期",name:"pb_time",allowBlank:false,value:new Date().dateFormat("Y-m-d H:i:s")},
      {label:"开始时间",name:"start_time",allowBlank:false,xtype:"datetime"},
      {label:"截止时间",name:"end_time",allowBlank:false,xtype:"datetime"},
       {label:"优惠券编号",name:"cp_num",id:"cpnum",
       listeners:{
       			"blur":function(){
       				var a=Ext.getCmp("cpnum");
       				doGet("/fav/coupon_info.check.do?cpnum="+a.getValue(),"",function(dt){
       					if(dt["flag"]=="YES"){
       						return true;
       					}else{	
       					$alt("优惠券编号已存在！");
       					}
       				});
       			}
       		},
       	invalidText:'优惠券编号已经被注册！' 
       		
       },
      {label:"状态",name:"cp_status",xtype:"combo",allowBlank:false,code:"COUPON_STATUS"},
      {label:"折扣比率",name:"cp_disct",maxLength:2,maxLengthText:"输入超过了两个字",emptyText:"例：85折-输入85",allowBlank:false,id:"cpdisct",
      	listeners:{
      		"blur":function(){
      		var a=Ext.getCmp("cpdisct");
      		var b=Ext.getCmp("cpcash");
      			if(a.getValue()!=0){
      				b.setValue(0);
      				b.disable(true);
      			}else{
      				b.setValue();
      				b.enable(false);
      			}
      		}
      	}
      },
      {label:"优惠金额",name:"cp_cash",allowBlank:false,id:"cpcash",
      			listeners:{
      		"blur":function(){
      		var a=Ext.getCmp("cpdisct");
      		var b=Ext.getCmp("cpcash");
      			if(b.getValue()!=0){
      				a.setValue(0);
      				a.disable(true);
      			}else{
      				a.setValue();
      				a.enable(false);
      			}
      		}
      	}
      },
      {label:"是否赠送",name:"cp_oper",id:"cp_op",allowBlank:false,xtype:"combo",code:"COUPON_GIVE",
      listeners:{
      		"select":function(){
	      		var a=Ext.getCmp("cp_op");
	      		var b=Ext.getCmp("price");
	      		if(a.getValue()==0){
	      			b.setValue("0.00");
	      			b.disable();
	      		}else{
	      			b.setValue();
	      			b.enable();
	      		}
      		}
      	}},
      {label:"销售价格",name:"cp_money",id:"price",allowBlank:false}
   	//  {label:"菜品类型",name:"cond_type",id:"condtype",xtype:"combo",code:"FOOD_TYPE"},
   	 //  {label:"菜品名称",name:"food_name",readOnly:true,xtype:"trigger",columns:foodFields,checkbox:true,singleSelect:false,afterRowClick:function(){},buttons:[{text:"确认",handler:function(){getCmp("dialogWindow_xudahu111").destroy();}}],map:{food_id:"scope_value"},tableURL:"/rule/value/all1.do",getData:function(){return {scope_value:Ext.getCmp("condtype").getValue()};}},
      //  {label:"消费类型",name:"is_full",xtype:"combo",code:"IS_FULL"},
   	  //{label:"金额",name:"cond_value"},
     // {label:"发放(张)",name:"send_num"}
   ];  
 var addFunc=function(){
     createFormWindow({
        id        : "app_fav_coupon",
        label     : "新增优惠券",
        items     : singlelistField1,
        height	  :380,
        cols      : 1,
        action    : "/fav/coupon_info.insert.do",
        close     : cLoadEvent("app_fav_coupon")
     
     }).show();
 }
 
 var modFunc=function(){
      var d=getData("app_fav_coupon");
      createFormWindow({
        id        : "app_fav_coupon",
        label     : "修改优惠券",
        height	  :380,
        items     : listField,
        cols      : 1,
        action    : "coupon_info.modify.go",
        data      : d,
        close     : cLoadEvent("app_fav_coupon")
     
     }).show();
 }
 var multiadd=function(){
     createFormWindow({
        id        : "app_fav_coupon",
        label     : "批量增加优惠券",
        items     : listField1,
        height	  : 440,
        cols      : 1,
        action    : "/fav/coupon_info.insert1.do",
        close     : cLoadEvent("app_fav_coupon")
     
     }).show();
 }
 var delFunc=function(){
    var d=getData("app_fav_coupon",["cp_id"]);
     doPost("coupon_info.delete.go",d,cLoadEvent("app_fav_coupon"));
 }
   var toolBars=[ {text:"批量增加",iconCls:"gridmultiadd",handler:multiadd},"-",
   "查询条件","&nbsp;","-",
       	                  createField({name:"cp_num",width:70,emptyText:"起始编号"}),
       	                  "&nbsp;",
       	                  createField({name:"cp_num1",width:70,emptyText:"截止编号"}),
       	                   "-",
       	                  createField({name:"cp_name",width:70,emptyText:"优惠券名称"}),
      	                  "-",
       	                  createField({name:"cp_oper",width:70,xtype:"combo",code:"COUPON_GIVE",emptyText:"是否赠送"}),
       	                  "-",
       	                  createField({name:"pb_time",xtype:"datetime",format:"Y-m-d",code:"ORDER_TYPE",width:70,emptyText:"发布日期"}),
       	                  "-",
       	                  createField({name:"start_time",xtype:"datetime",format:"Y-m-d",width:70,emptyText:"开始时间"}),
       	                  "-",
       	                  createField({name:"end_time",xtype:"datetime",format:"Y-m-d",width:70,emptyText:"截止时间"}),
       	                  "-",
       	                  createField({name:"cp_status",code:"COUPON_STATUS",xtype:"combo",width:70,emptyText:"状态"}),
       	                  "&nbsp;",
       	                  createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick})
       	                  
       	                  ];
 	             
 var grid=createPageGrid({
     id            : "app_fav_coupon",
     label         : "优惠券列表",
     region        : "center",
     items         : listField,
     filter        : false,
     page          : true,
     urls          : "/fav/coupon_info.pages.do",
     createFunction: addFunc,
     modifyFunction: modFunc,
     deleteFunction: delFunc,
     toolBarFields :toolBars
 
 });
 function goClick(){
   var dt={};

   for(var i=0;i< toolBars.length;i++){
   	   var o=toolBars[i];
  
   	   if(typeof o == "object"){
   	       if(o.getName && o.getValue){
   	       
   	          dt[o.getName()]=o.getValue();
   	          
   	       }
   	   }
   	}
    
   load("app_fav_coupon",dt);

}
 return [grid];
   