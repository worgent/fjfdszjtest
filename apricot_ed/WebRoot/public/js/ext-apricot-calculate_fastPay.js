/*
 * Apricot JS Library 1.1
 * Copyright(c) 2006-2008, Apricot JS, LLC.
 * xu.dahu@hotmail.com
 */

/**
 * 这一个文件里面主要定义和创建，费用计算面板和其他东西
 */
function cCalculatePanelFast(orderNo,cb){
    var topFields=[
      {label:"订单号",name:"order_no",value:orderNo[0]!=null?orderNo[0]:orderNo,readOnly:true},
      {label:"VIP卡号",name:"vip_card_no"},
//      {label:"优惠券卡号",name:"cp_num"},
      {label:"最低消费类型",name:"mincost_type",value:orderNo[2]!=null?orderNo[2]:"",xtype:"combo",readOnly:true,code:"MINCOST_TYPE"},
      {label:"最低消费金额",name:"mincost_money",value:orderNo[1]!=null?orderNo[1]:"",xtype:"number",readOnly:true},
      {label:"来宾人数",name:"man_count",value:orderNo[3]!=null?orderNo[3]:"",xtype:"number",readOnly:true},
      {label:"总金额",name:"total",xtype:"number",readOnly:true},
      
      {label:"使用积分",id:"point_check",name:"point_check",xtype:"checkbox",handler:changeState},
      {label:"总积分",name:"cum_point",xtype:"number",value:orderNo[4]!=null?orderNo[4]:"",readOnly:true},
      {label:"积分消费",id:"fact_pay_point",name:"fact_pay_point",xtype:"number",readOnly:true},
      {label:"结余积分",name:"fact_point",xtype:"number",readOnly:true},
      {label:"积分兑换金额",name:"point_money",xtype:"number",readOnly:true},
      
      {label:"优惠券金额",name:"youhui_total",value:"0",xtype:"number"},
      
      {label:"应收金额",name:"pay_total",xtype:"number",readOnly:true},
  	  {label:"现金支付",name:"fact_pay_total",xtype:"number",format:"#0.00"},
  	  {label:"刷卡支付",name:"fact_pay_card",xtype:"number",format:"#0.00"},
  	  
      {label:"结余金额",name:"return_total",xtype:"number",readOnly:true},
      {label:"返还积分",name:"dis_point",xtype:"number",readOnly:true}
//      {label:"优惠券编号",name:"cp_num"},
      
      
    ];
    function changeState()
	{
		if(Ext.getCmp("point_check").getValue() == true)
		{
			Ext.getCmp("fact_pay_point").getEl().dom.readOnly = false;
		}
		else
		{
			Ext.getCmp("fact_pay_point").setValue(0);
			Ext.getCmp("fact_pay_point").getEl().dom.readOnly = true;
		}
	}
    var ruleFields=[
      {label:"规则ID",name:"price_id",hide:"all"},
      {label:"规则名称",name:"rule_name"},
      {label:"适用范围",name:"rule_scope"},
      {label:"规则值",name:"rule_value"}
    ];
    var favConpou=[
      {label:"优惠券编号",name:"cp_num",hide:"all"},
      {label:"优惠券名称",name:"cp_name"},
      {label:"优惠金额",name:"cp_cash"},
      {label:"折扣比率",name:"cp_disct"},
      {label:"消费(每满)元",name:"cond_value"},
      {label:"发放(张)",name:"send_num"},
      {label:"开始时间",name:"start_time",hide:"all"},
      {label:"截止时间",name:"end_time"}
    ];
    var foodFields=[
       {label:"菜品名称",name:"food_name"},
       {label:"菜品数量",name:"food_count"},
       {label:"菜品单价",name:"food_price"},
       {label:"餐位名称",name:"balcony_name"}
    ];
      var toolBars=[  "消费(每满)元","&nbsp;",
      				  createField({name:"pay_money",code:"COND_VALUE",id:"pay_money1",xtype:"combo",width:70}),
      				  createField({name:"orderQuery",xtype:"button",pressed:true,iconCls:"gridBarItemQuery",label:"GO",width:60,handler:goClick}) ];
     function goClick(){
   var dt={};

   for(var k=0;k<toolBars.length;k++){
   	   var o=toolBars[k];
  
   	   if(typeof o == "object"){
   	       if(o.getName && o.getValue){
   	       
   	          dt[o.getName()]=o.getValue();
   	          
   	       }
   	   }
   	}
    
   load("calculate_panel_fast_f1",dt);
  // 	grid.getStore().baseParams=dt;
 //  	grid.getStore().load();
}
    function calculate(){
    	var d=new Array();
    	d.push(getFormValues("calculate_panel_fast_a")); 
    	//d.push(getFormValues("win1"));
//    	alert(getDataArrayStr("calculate_panel_fast_b1",["price_id"]));
    	var opP=getDataArrayStr("calculate_panel_fast_b1",["price_id"]);
    	d.push(opP);
    	if(opP.length>1){
    		$alt("可选优惠只能选择一个享受，请取消一个");
    		return;
    	}
    	
    	d=d.concat(getAllData("calculate_panel_fast_c1",["price_id"]));
//   		alert(d)
    	doGet("/calculate_calc.do",d,function(dt){
    		if(dt==88888888){
    		$alt("优惠券不存在或已使用过的！！");
    		}else{	
	    	        dt["return_total"]=((Number(dt.fact_pay_total)+Number(dt.fact_pay_card)+Number(dt.youhui_total))-Number(dt.pay_total)).toFixed(2);
	    	    
		    	    dt["fact_point"]=Number(dt.cum_point)+Number(dt.dis_point)-Number(dt.fact_pay_point);
		    	    getCmp("calculate_panel_fast_a").getForm().setValues(dt);
		    	    if(dt.mincost_type == 1)
		    	    {
		    	    	if(dt.total < (dt.man_count*dt.mincost_money))
		    	    	{
		    	    		$alt("人均消费金额未达到最低消费标准！");
		    	    	}
		    	    }
		    	    else if(dt.mincost_type == 2)
		    	    {
		    	    	if(dt.total < dt.mincost_money)
		    	    	{
		    	    		$alt("消费总金额未达到最低消费标准！");
		    	    	}
		    	    }
			       if(dt.cum_point < dt.fact_pay_point)
			       {
			       	 $alt("消费积分数不能大于总积分数！");
			       }
		    	   if(dt.isCan=="0"){
		    	  	 $alt("规则互斥，请先确认");
		    	   }
        	}	  
    	});
    	
    };
    
    function payoff(){
    	doGet("/fav/coupon_info.check.do",getFormValues("calculate_panel_fast_a"),function(dt){
    		if(dt["flag"]=="YES"){
    			$alt("优惠券不存在或已使用过的!!");
    		}else{
				    	var d=getFormValues("calculate_panel_fast_a");
				    	if(d.return_total<0){
				    		$alt("客户费用没有完全结账，请确认！");
				    		return;
				    	}
				    	
				    	if(!d.total || !d.return_total){
				    		$alt("还没有算费，不能结账！");
				    		return;
				    	}
				    	
				    	if(d.mincost_money != "0.00")
				 	    {
				  	    if(d.mincost_type == 1)
				  	    {
				  	    	if(d.total < (d.man_count*d.mincost_money))
				  	    	{
				  	    		$alt("消费总金额未达到最低消费标准！");
				  	    		return;
				  	    	}
				  	    }
				  	    else
				  	    {
				  	    	if(d.total < d.mincost_money)
				  	    	{
				  	    		$alt("消费总金额未达到最低消费标准！");
				  	    		return;
				  	    	}
				  	    }
				  	    if(dt.cum_point < dt.fact_pay_point)
				       	{
				       	 	$alt("消费积分数不能大于总积分数！");
				       	 	return;
				       	}
				  	  }
				    	
				    	doPost("/calculate_payoff.do",d,function(){
				    	    removeHandler("calculate_panel_fast_a_but1");
				    	    removeHandler("calculate_panel_fast_a_but2");
				    	    removeHandler("calculate_panel_fast_a_but3");
				    	});
    		}
    	});
    	
    	

    }
    
    
      function favCon(){
 		   var simple = new Ext.FormPanel({
    	        id:'win1',
				labelWidth: 85, 			
				baseCls: 'x-plain',
				defaults: {width: 150},
				defaultType: 'textfield',
				items: [{
						fieldLabel: '优惠券编号',
						name: 'cp_num',
						//anchor:'95%',
						allowBlank:false,
						blankText:'实物券编号不能为空'
					}
				],

				buttons: [{
					text: '使用',
					//handler:calculate
					handler:function(){
					 	//doGet("/calculate_calc.do",getFormValues("win1"),function(dt){});
											var d=new Array();
					    	d.push(getFormValues("calculate_panel_fast_a")); 
					    	d.push(getFormValues("win1"));
					//    	alert(getDataArrayStr("calculate_panel_fast_b1",["price_id"]));
					    	d.push(getDataArrayStr("calculate_panel_fast_b1",["price_id"]));
					//    	alert(d);
					    	d=d.concat(getAllData("calculate_panel_fast_c1",["price_id"]));
					//   		alert(d)
					    	doGet("/calculate_calc.do",d,function(dt){
					    		if(dt==88888888){
					    		$alt("优惠券中有使用过的！！");
					    		}else
					    		{
						    	    dt["return_total"]=(dt.fact_pay_total-dt.pay_total).toString().replace(/^(\d+\.\d{2})\d*$/,"$1");
						    	    dt["fact_point"]=Number(dt.cum_point)+Number(dt.dis_point)-Number(dt.fact_pay_point);
						    	    getCmp("calculate_panel_fast_a").getForm().setValues(dt);
						    	    if(dt.mincost_money != "0.00")
						    	    {
							    	    if(dt.mincost_type == 1)
							    	    {
							    	    	if(dt.total < (dt.man_count*dt.mincost_money))
							    	    	{
							    	    		$alt("消费总金额未达到最低消费标准！");
							    	    	}
							    	    }
							    	    else
							    	    {
							    	    	if(dt.total < dt.mincost_money)
							    	    	{
							    	    		$alt("消费总金额未达到最低消费标准！");
							    	    	}
							    	    }
							       }
							       if(dt.cum_point < dt.fact_pay_point)
							       {
							       	 $alt("消费积分数不能大于总积分数！");
							       }
						    	   if(dt.isCan=="0"){
						    	  	 $alt("规则互斥，请先确认");
						    	   }
						    	  
					    	  }
					    	});
					}
				},{
					text: '关闭',
					handler:function(){ getCmp("win").destroy();}
				}]
			});    
			       
				   win = new Ext.Window({
						id:'win',
						title:'优惠券使用',
						layout:'fit',					
						width:300,
						height:150,
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

    }
    
    function printOrder(){
		doGet("/print/printOrder.do",getFormValues("calculate_panel_fast_a"),function(dt){
			if(dt["flag"] == 0)
			{
				alert("打印成功");
			}
			else
			{
				alert("打印失败，请重试");
			}
			
		})
    }
    
    function bookFee(){
    doGet("/keepaccount_calc.do",null,function(dt){
    if(dt["kai_accounts"]==0)
    {
      //?????
          var simple = new Ext.FormPanel({
    	        id:'win1',
				labelWidth: 75, 			
				baseCls: 'x-plain',
				defaults: {width: 150},
				defaultType: 'textfield',
				items: [{
						fieldLabel: '帐户',
						name: 'name',
						//anchor:'95%',
						allowBlank:false,
						blankText:'帐户不能为空'
					},{
					    inputType:'password',
						fieldLabel: '密码',
						//anchor:'95%',
						name: 'pws',
						allowBlank:false,
						blankText:'密码不能为空'
					}
				],

				buttons: [{
					text: '授权',
					handler:function(){
					 var d={};
					  d=Ext.apply(d,getFormValues("win1"));
					 doPost("/checklogin.do",getFormValues("win1"),function(rt){
					 getCmp("win").destroy();
    	      		 
					        try{
					          d=Ext.apply(d,getFormValues("calculate_panel_fast_p1"));
					        }catch(e){
					        	toPanel.activate("calculate_panel_fast_p1");
					        	$alt("请填写记账信息,然后再记帐");
					        }
					        d=Ext.apply(d,getFormValues("calculate_panel_fast_a"));
					        if(d.book_man=="" || !d.book_man){
					     	   $alt("请填写记账信息,然后再记帐.");
					     	   return;
					        }
					    	doPost("/calculate_bookbill.do",d,function(){
					 	    removeHandler("calculate_panel_fast_a_but1");
					 	    removeHandler("calculate_panel_fast_a_but2");
					 	    removeHandler("calculate_panel_fast_a_but3");
					 	   });
    	    		},false);
					}
				},{
					text: '关闭',
					handler:function(){ getCmp("win").destroy();}
				}]
			});    
			       
				   win = new Ext.Window({
						id:'win',
						title:'记账登陆',
						layout:'fit',					
						width:300,
						height:150,
						plain:true,
                        bodyStyle:'padding:5px;',
						maximizable:false,//??????
						closeAction:'close',
						closable:false,//??????
						collapsible:true,//?????
						plain: true,
             		    modal:true ,
						buttonAlign:'center',
						items:simple//????????????????????
					});
					win.show();//???????
    	
    	
    }
    else
    { 
		 var d={};
        try{
          d=Ext.apply(d,getFormValues("calculate_panel_fast_p1"));
        }catch(e){
        	toPanel.activate("calculate_panel_fast_p1");
        	$alt("请填写记账信息,然后再记帐.");
        }
        d=Ext.apply(d,getFormValues("calculate_panel_fast_a"));
        if(d.book_man=="" || !d.book_man){
     	   $alt("请填写记账信息,然后再记帐.");
     	   return;
        }
    	doPost("/calculate_bookbill.do",d,function(){
 	    removeHandler("calculate_panel_fast_a_but1");
 	    removeHandler("calculate_panel_fast_a_but2");
 	    removeHandler("calculate_panel_fast_a_but3");
 	   });
    }
    });
    	
    	
       
     }

    
    var buttons=[
      {text:"算费",handler:calculate,id:"calculate_panel_fast_a_but1"},
      {text:"收费",handler:payoff,id:"calculate_panel_fast_a_but2"},
      {text:"记帐",handler:bookFee,id:"calculate_panel_fast_a_but3"},
      {text:"清空",handler:function(){calcPanel.getForm().getEl().dom.reset();},id:"calculate_panel_clean"},
      {text:"打印",handler:printOrder,id:"calculate_panel_fast_a_but4"}
    ];
    
    var vipMember=[
    	{label:"卡号",name:"vip_card_no",width:80},
    	{label:"姓名",name:"cust_name",width:80},
    	{label:"联系电话",name:"cust_phone",width:80},
    	{label:"积分",name:"cum_point",width:80}
    ];
    
    var orderSelectColumn=[
	  {label:"订单号",name:"order_no",hide:"form",width:80},
      {label:"订单号",name:"order_id",hide:"all"},
      {label:"餐位编号",name:"set_no",width:60},
      {label:"餐位名称",name:"balcony_name",width:60},
      {label:"订单类型",name:"order_type",hide:"form",xtype:"combo",code:"ORDER_TYPE",width:60},
      {label:"订单状态",name:"order_status",hide:"form",xtype:"combo",code:"ORDER_STATUS"},
      {label:"预定人数",name:"prearrange_man_count"},
      {label:"来宾人数",name:"man_count"},
      {label:"最低消费类型",name:"mincost_type",xtype:"combo",code:"MINCOST_TYPE"},
      {label:"最低消费类型",name:"mincost_type"},
      {label:"最低消费金额",name:"mincost_money"},
      {label:"订单时间",name:"order_time",xtype:"datetime",width:160},
      {label:"宾客姓名",name:"prearrange_name"},
      {label:"联系电话",name:"prearrange_phone"},
      {label:"VIP卡号",name:"vip_card_no"},
      {label:"总积分",name:"cum_point"},
      {label:"催菜次数",name:"hurry_times",value:"0",hide:"form"},
      {label:"订餐人地址",name:"prearrange_addr"}
	];
	
    function changeOrderNo(d){
    	load("calculate_panel_fast_b1",d);
    	load("calculate_panel_fast_c1",d);
    	load("calculate_panel_fast_d1",d);
    	load("calculate_panel_fast_e1",d);
    	load("calculate_panel_fast_f1",d);
    }
    function changeVipCard(d)
    {
    	var ddd=getCmp("calculate_panel_fast_a");
		var orderNo = order_no=ddd.getForm().getValues("order_no").order_no;
		d.order_no=orderNo;
		doGet("/order/customer_vipNo_modify.do",d,function(dt){
		})
    	load("calculate_panel_fast_b1",{order_no:orderNo});
    }
    
	var baiscFiled=[
		{
			label:"订单号",name:"order_no",xtype:"trigger",afterRowClick:changeOrderNo,tableURL:"/order/fast_pay_search.do",columns:orderSelectColumn
        },{
            label:"VIP卡号",name:"vip_card_no",xtype:"trigger",afterRowClick:changeVipCard,tableURL:"/vip/vip_member_select.do",columns:vipMember,disabled:false
        },{
        	label:"最低消费类型",name:"mincost_type",value:orderNo[2]!=null?orderNo[2]:"",xtype:"combo",readOnly:true,code:"MINCOST_TYPE"
        },{
            label:"最低消费金额",name:"mincost_money",value:orderNo[1]!=null?orderNo[1]:"",readOnly:true
        },{
            label:"来宾人数",name:"man_count",value:orderNo[3]!=null?orderNo[3]:"",readOnly:true
        },{
            label:"总金额",name:"total",readOnly:true
        },{
			label:"订单类型",name:"order_type", xtype:"textfield",readOnly:true
        },{
			label:"订单编号",name:"order_id", xtype:"textfield",hideMode:"display",hidden:true,hideLabel:true,readOnly:true
        }
    ]
    var pointFiled=[
    	{
            label:"使用积分",id:"point_check",name:"point_check",xtype:"checkbox",handler:changeState
        },{
            label:"总积分",name:"cum_point",value:orderNo[4]!=null?orderNo[4]:"",readOnly:true
        },{
            label:"积分消费",id:"fact_pay_point",name:"fact_pay_point",readOnly:true
        },{
            label:"结余积分",name:"fact_point",readOnly:true
        },{
            label:"积分兑换金额",name:"point_money",readOnly:true
        },{
            label:"优惠券金额",name:"youhui_total",value:"0"
        }
    ]
    var resultFileds=[
    	{
            label:"应收金额",name:"pay_total",readOnly:true
        },{
            label:"现金支付",name:"fact_pay_total",format:"#0.00"
        },{
            label:"刷卡支付",name:"fact_pay_card",format:"#0.00"
        },{
            label:"结余金额",name:"return_total",readOnly:true
        },{
            label:"返还积分",name:"dis_point",readOnly:true
        },{
            label:"打印份数",name:"print_count",value:1
        }
    ]
    for(var i=0;i<baiscFiled.length;i++){
	   baiscFiled[i]=createField(baiscFiled[i],"calculate_panel_fast_a");
	}
	for(var i=0;i<pointFiled.length;i++){
	   pointFiled[i]=createField(pointFiled[i],"calculate_panel_fast_a");
	}
	for(var i=0;i<resultFileds.length;i++){
	   resultFileds[i]=createField(resultFileds[i],"calculate_panel_fast_a");
	}

    
	var calcPanel=new Ext.FormPanel({
		labelAlign: 'top',
		frame:true,
		id            : "calculate_panel_fast_a",
    	title         : "结账信息",
    	width         : 700,
    	height        : 220,
    	labelWidth : 80,
    	split         : true,
    	region        : "north",
    	labelAlign:"left",
    	layout:'column',
    	items         : [{
    		
            xtype:'fieldset',
            title: '基本信息',
            collapsible: true,
            height:200,
            defaults: {width: 120},
            defaultType: 'textfield',
            columnWidth:.333,
            layout: 'form',
            items :baiscFiled
        },{
            xtype:'fieldset',
            title: '积分信息',
            collapsible: true,
            height:200,
            defaults: {width: 120},
            defaultType: 'textfield',
            columnWidth:.333,
            layout: 'form',
            items :pointFiled
        },{
            xtype:'fieldset',
            title: '结账信息',
            collapsible: true,
            height:200,
            defaults: {width: 120},
            defaultType: 'textfield',
            columnWidth:.333,
            layout: 'form',
            items :resultFileds
        }]
		
	});
    
    
    var bookPanel=createFormPanel({
    	id            : "calculate_panel_fast_p1",
    	title         : "记账信息",
    	cols          : 1,
    	height        : 180,
    	split         : true,
    	items         : [{label:"联系人",name:"book_man",allowBlank:false},
    	                 {label:"联系电话",name:"book_phone"},
    	                 {label:"挂账原因",name:"book_desc",code:"ACCOUT_REA",xtype:"combo"},
    	                 {label:"预计结账时间",name:"book_date",xtype:"datetime"},
    	                 {label:"备注",name:"book_remarks",xtype:"textarea"}],
    	labelWidth    : 80,
    	region        : "north"
    });
    
    var optionPanel=cGroupGrid({
    	id            : "calculate_panel_fast_b1",
    	title         : "可选折扣",
    	items         : ruleFields,
    	collapsible   : true,
    	selectFirst   : true,
    	url           : "/calculate_rules.do?price_type=2",
    	split         : true,
    	afterLoad     : calculate,
    	rowclick      : calculate,
//    	groupField    : "rule_name",
    	region        : "center"
    });
    

    
    
//    {bodyBorder:false,frame:false,layout:"border",style:"padding:2px",items:[toPanel,tabPanel]}

    var mustOptionPanel=cGroupGrid({
    	id            : "calculate_panel_fast_c1",
    	title         : "必选折扣",
    	items         : ruleFields,
    	region        : "south",
    	url           : "/calculate_rules.do?price_type=1",
    	split         : true,
    	groupField    : "rule_name",
    	selectFirst	  : false,
    	afterLoad     : calculate,
    	collapsible   : true
    });
    
    var noSumitFoodPanel=createPageGrid({
    	id            : "calculate_panel_fast_d1",
    	title         : "未上的菜",
    	items         : foodFields,
    	urls           : "/calculate_details.do?serving_flag=0",
    	region        : "center",
    	filter        : false
    });
    var sumitFoodPanel=createPageGrid({
    	id            : "calculate_panel_fast_e1",
    	title         : "已上的菜",
    	items         : foodFields,
    	urls           : "/calculate_details.do?serving_flag=1",
    	region        : "center",
    	filter        : false
    });
     var favCouponPanel=createPageGrid({
    	id            : "calculate_panel_fast_f1",
    	title         : "优惠券赠送",
    	items         : favConpou,
    	urls           : "/fav/coupon_info.pages1.do",
    	region        : "center",
    	//toolBarFields :toolBars,
    	filter        : false
    });
    
    var tabPanel=new Ext.TabPanel({
    	items         : [optionPanel,mustOptionPanel,noSumitFoodPanel,sumitFoodPanel],
    	region        : "center",
    	height        : 320,
    	activeTab     : 0
    });
    
    var toPanel=new Ext.TabPanel({
    	items         : [calcPanel,bookPanel],
    	activeTab     : 0,
    	height        : 260,
    	split         : true,
     	region        : "north",
     	bodyBorder    : false
    });
    
    
    
    var win=new Ext.Panel({
    	 title        : "客户结账",
    	 items        : [{bodyBorder:false,frame:false,layout:"border",style:"padding:2px",items:[toPanel,tabPanel]}],
    	 id           : "calculate_panel_fast_w1",
    	 layout       : "fit",
    	 hideBorders  : true,
    	 bodyBorder   : false,
    	 split         : true,
    	 buttons      : buttons,
    	 region       : "center"
    });
    return win;
}
