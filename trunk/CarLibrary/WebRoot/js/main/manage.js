Ext.BLANK_IMAGE_URL = '/ext/resources/images/default/s.gif';
var main, menu, header,bottom,onlineWindow,loginWin,messageWindow,photoPreview;
Ext.QuickTips.init();
Ext.form.Field.prototype.msgTarget='side';

OnlineMessageManager.me={id:"",
		name:""};
function togglecheck(btn, pressed){
  grid.addListener('beforeload',function(store,obj) {
    store.baseParams.aaa = '1';
   });
        grid.reconfigure({store:'store',colModel:'cm'});

        
    }

Global = {
	topicCategoryLoader : new Ext.tree.TreeLoader( {
		url : "topicCategory.ejf?cmd=getCategory&pageSize=-1&treeData=true",
		icon:"images/menuPanel/tag_blue4.gif",
		listeners : {
			'beforeload' : function(treeLoader, node) {
				treeLoader.baseParams.id = (node.id.indexOf('root')<0 ? node.id : "");
			}
		}
	}),
	albumCategoryLoader : new Ext.tree.TreeLoader( {
		url : "albumCategory.ejf?cmd=getCategory&pageSize=-1&treeData=true",
		icon:"images/menuPanel/tag_blue4.gif",
		listeners : {
			'beforeload' : function(treeLoader, node) {
				treeLoader.baseParams.id = (node.id.indexOf('root')<0 ? node.id : "");
			}
		}
	})
};

onlineWindow=new OnlineUserWindow({
	// tbar:[{text:"刷新1",handler:function(){alert(this.text);},scope:this},"-","查找"],
	// items:[, ]
});
/************暂时不知道功能如何使用
LoginWindow=Ext.extend(Ext.Window,{
 	title : '登陆系统',		
	width : 265,			
	height : 140,
	collapsible : true,
	closable:false,
	defaults : {			
		border : false
	},
	buttonAlign : 'center',	
	createFormPanel :function() {
		return new Ext.form.FormPanel({
			bodyStyle : 'padding-top:6px',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 55,
			labelPad : 0,
			frame : true,
			defaults : {
				allowBlank : false,
				width : 158,
				selectOnFocus:true
			},
			items : [{
					cls : 'user',
					name : 'userName',
					fieldLabel : '帐号',
					blankText : '帐号不能为空'
				}, {
					cls : 'key',
					name : 'password',
					fieldLabel : '密码',
					blankText : '密码不能为空',
					inputType : 'password'
				}]
		});
	},					
	login:function() {
			this.fp.form.submit({
					waitTitle:"请稍候",
					waitMsg : '正在登录......',
					url : 'portal.ejf?cmd=login&ext=true',
					success : function(form, action) {
						// window.location.href = 'manage.ejf';
						Ext.get("desktop").unmask();
						this.hide();
						OnlineMessageManager.me=action.result.data;
						if(OnlineMessageManager.stopRecive)OnlineMessageManager.start(OnlineMessageManager);
						bottom.currentUser.el.innerHTML="<b><font color=blue>"+OnlineMessageManager.me.name+"</font></b>";						
					},
					failure : function(form, action) {
						
						if (action.failureType == Ext.form.Action.SERVER_INVALID)
							Ext.MessageBox.alert('警告', action.result.errors.msg);
						form.findField("password").setRawValue("");
						form.findField("userName").focus(true);
					},
					scope:this
				});
		},
	initComponent : function(){
		this.keys={
			key: Ext.EventObject.ENTER,
		    fn: this.login,
		    scope: this};
        LoginWindow.superclass.initComponent.call(this);       
        this.fp=this.createFormPanel();
        this.add(this.fp);
        this.addButton('登陆',this.login,this);
        this.addButton('注册', function(){var win=new RegisterWindow();win.show();},this);
        this.addButton('退出',function(){Ext.Msg.confirm("提示","确认要退出系统吗？",function(btn){if(btn=="yes")window.location.href="portal.ejf";})},this);
	 } 	
 }); 
*********/
RegisterWindow=Ext.extend(Ext.Window,{
 	title : '新用户注册',		
	width : 265,			
	height : 260,
	modal:true,
	defaults : {			
		border : false
	},
	buttonAlign : 'center',	
	createFormPanel :function() {
		return new Ext.form.FormPanel({
			bodyStyle : 'padding-top:6px',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 55,
			labelPad : 0,
			frame : true,
			defaults : {
				allowBlank : false,
				width : 158,
				selectOnFocus:true
			},
			items : [{
					name : 'name',
					fieldLabel : '帐号',
					blankText : '帐号不能为空'
					},
					{
						name : 'password',
						fieldLabel : '密码',
						blankText : '密码不能为空',
						inputType : 'password'
					},
					{
						name : 'password1',
						fieldLabel : '确认密码',
						blankText : '密码不能为空',
						inputType : 'password'
					},
					{
						name : 'email',
						fieldLabel : '电子邮件',
						blankText : '密码不能为空'
					},
					{
						xtype:"textarea",
						name : 'intro',
						fieldLabel : '简介',
						blankText : '密码不能为空'
					}]
		});
	},					
	save:function() {
		var p1=this.fp.form.findField("password").getValue();
		var p2=this.fp.form.findField("password1").getValue();
		if(p1!=p2){this.fp.form.findField("password").markInvalid("两次输入的新密码不同!");this.fp.form.findField("password").focus(true);return;}
			this.fp.form.submit({
					waitTitle:"请稍候",
					waitMsg : '正在保存......',
					url : 'portal.ejf?cmd=saveRegister&ext=true',
					success : function(form, action) {		
						var name=this.fp.form.findField("name").getValue();			
						this.close();					
						if(loginWin){
							loginWin.fp.form.findField("userName").setValue(name);
							loginWin.fp.form.findField("password").focus();
						}	
					},
					failure : function(form, action) {
						// if (action.failureType ==
						// Ext.form.Action.SERVER_INVALID)
						// Ext.MessageBox.alert('警告', action.result.errors.msg);
						form.findField("name").focus(true);
					},
					scope:this
				});
		},
	initComponent : function(){
		this.keys={
			key: Ext.EventObject.ENTER,
		    fn: this.save,
		    scope: this};
        RegisterWindow.superclass.initComponent.call(this);       
        this.fp=this.createFormPanel();
        this.add(this.fp);
        this.addButton('保存',this.save,this);
        this.addButton('取消', function(){this.close();},this);
	 } 	
 }); 
 
ChangePasswordWindow=Ext.extend(Ext.Window,{
 	title : '修改密码',		
	width : 280,		
	iconCls:"icon-password",
	height : 165,	
	defaults : {			
		border : false
	},
	buttonAlign : 'center',	
	createFormPanel :function() {
		return new Ext.form.FormPanel({
			bodyStyle : 'padding-top:6px',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 70,
			labelPad : 0,
			frame : true,
			defaults : {
				allowBlank : false,
				width : 158,
				selectOnFocus:true
			},
			items : [{
					cls : 'key',
					name : 'oldPassword',
					fieldLabel : '旧密码',
					blankText : '密码不能为空',
					inputType : 'password'
				}, {
					cls : 'key',
					name : 'password',
					fieldLabel : '新密码',
					blankText : '密码不能为空',
					inputType : 'password'
				}, {
					cls : 'key',
					name : 'password1',
					fieldLabel : '确认密码',
					blankText : '密码不能为空',
					inputType : 'password'
				}]
		});
	},					
	save:function() {
			var p1=this.fp.form.findField("password").getValue();
			var p2=this.fp.form.findField("password1").getValue();

			if(p1!=p2){
				this.fp.form.findField("password").markInvalid("两次输入的新密码不同!");
				this.fp.form.findField("password").focus(true);
				return;
			}	
			var url = '/system/manage/modifyPassword.shtml?oldPassword='+this.fp.form.findField("oldPassword").getValue()+'&password='+p1;
			try{
				var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
				oXMLDom.async = false ;
				oXMLDom.load(url); 
				var root;
				if (oXMLDom.parseError.errorCode != 0) {
					var myErr = oXMLDom.parseError;
					return;
				} else {
					root = oXMLDom.documentElement;
				}
				if (null != root){
					var rowSet = root.selectNodes("//Result");
					if (1 == rowSet.item(0).selectSingleNode("errCode").text){
						Ext.Msg.alert("提示","密码修改成功，请重新登录系统!",function(){
							this.close();
							logout();
						},this);
					}else{
						Ext.Msg.alert("提示", rowSet.item(0).selectSingleNode("errMsg").text,function(){
							form.findField("oldPassword").focus(true);
						},this);
					}
				}
			}catch(e){ 
				alert(e);
			}

		},
	initComponent : function(){
		this.keys={
			key: Ext.EventObject.ENTER,
		    fn: this.save,
		    scope: this};
        ChangePasswordWindow.superclass.initComponent.call(this);       
        this.fp=this.createFormPanel();
        this.add(this.fp);
        this.addButton('保存',this.save,this);
        this.addButton('取消', function(){this.close();},this);
        this.on("close",function(){Ext.get("desktop").unmask();})
	 } 	
 }); 
BaseGridList=Ext.extend(Ext.Panel,{	
	layout:"fit",	
	loadData:false,
	pageSize:10,
	closable: true,
  	autoScroll:true,
  	gridViewConfig:{},
   	dateRender:function(format) {
    	format=format||"Y-m-d h:i";
    	return Ext.util.Format.dateRenderer(format);
    },
    userRender:function(v){
    	return v?v.name:"未知原因";
    },
    emailRender:function(v){
    	return v?v.email:"未知原因";
    },
	initComponent : function(){		
	BaseGridList.superclass.initComponent.call(this);
    this.store=new Ext.data.JsonStore({
		id:"id",
       	url:this.url,
       	root:"result",
  		totalProperty:"rowCount",
  		remoteSort:true,  		
  		fields:this.storeMapping});  		
      	this.store.paramNames.sort="orderBy";
	 	this.store.paramNames.dir="orderType";	  
      	this.cm.defaultSortable=true;  
      	var viewConfig=Ext.apply({forceFit:true},this.gridViewConfig);  
	    this.grid=new Ext.grid.GridPanel({
	        store: this.store,
	        cm: this.cm,
	        trackMouseOver:false,    
	        loadMask: true,
	        viewConfig:viewConfig,
	        bbar: new Ext.PagingToolbar({
	            pageSize: this.pageSize,
	            store: this.store,
	            displayInfo: true,
	            displayMsg: 'Displaying records {0} - {1} of {2}',
	            emptyMsg: "No record to display"
	        })
	   		});   		   		 
	this.add(this.grid);
	if(this.loadData)this.store.load();
	}     
});
 
MyCustomerList=Ext.extend(BaseGridList,{
	id:"myCustomerList",
	title:"我推荐的用户",
	
	url:"?cmd=myCustomerList",	
   	storeMapping:["id","im","user",{name:"userName",mapping:"user.name"},"tel","applyTime","paymentTime","sums","confirmTime","expireTime","remark","status","gift","delivery","paymentType","linkMan"], 
    userRender:function(v){
    	return v?v.name:"未知原因";
    },
    emailRender:function(v){
    	return v?v.email:"未知原因";
    },
	initComponent : function(){	
	this.cm=new Ext.grid.ColumnModel([    
    		{header: "申请人", sortable:true,width:100, dataIndex:"user",renderer:this.userRender},
    		{header: "状态", sortable:true,width: 60, dataIndex:"status"},
    		{header: "im", sortable:true,width: 100, dataIndex:"im"},
    		{header: "email", sortable:true,width: 100, dataIndex:"user",renderer:this.emailRender},
    		{header: "申请日期", sortable:true,width:100, dataIndex:"applyTime",renderer:this.dateRender()},
			{header: "支付日期", sortable:true,width: 100, dataIndex:"paymentTime",renderer:this.dateRender()},
			{header: "金额", sortable:true,width: 50, dataIndex:"sums"},
			{header: "确认日期", sortable:true,width:100, dataIndex:"confirmTime",renderer:this.dateRender()},
			{header: "备注", sortable:true, dataIndex:"remark"}
        ]);		
	MyCustomerList.superclass.initComponent.call(this);
	}     
});

ArticleList=Ext.extend(BaseGridList,{
	id:"articleList",
	title:"文章列表",	
	url:"topic.ejf?cmd=list",	
	gridViewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true,
            getRowClass : function(record, rowIndex, p, store){
                if(this.showPreview){
                    p.body = '<p>摘要1：'+record.data.intro+'</p><br/>';
                    return 'x-grid3-row-expanded';
                }
                return 'x-grid3-row-collapsed';
            }
        },
    storeMapping:["id","elite","title","intro","readTimes","category","inputTime","comments","content","vip",{name:"op",mapping:"id"}],
    userRender:function(v){
    	return v?v.name:"未知原因";
    },
    topicRender:function(value, p, record){    
    	return String.format('<b><a href="javascript:main.openUrl(\'portal.ejf?cmd=topicShow&id={0}&my=true\',\'viewArticle\',\'文章详情\')">{1}</a></b><br/>',record.id,value)
    },
    categoryRender:function(value,p,record)
	{
		if(!value)return "";
		else return value.name;
	},
	commentsRender:function(value, p, record){   
    	return value?value.length:0;
    }, 
    booleanRender:function(value, p, record){    
    	return value?"是":"否";
    }, 
    emailRender:function(v){
    	return v?v.email:"未知原因";csdfsdfs
    },
	initComponent : function(){	
	this.cm=new Ext.grid.ColumnModel([	  		
  		{        
           header: "主题",
           dataIndex: 'title',
           width:300,
           renderer:this.topicRender
        },
        {        
	           header: "VIP",
	           dataIndex: 'vip',
	           width:70,
	           renderer:this.booleanRender
	        },
	       {        
	           header: "推荐",
	           dataIndex: 'elite',
	           width:70,
	           renderer:this.booleanRender
	        },
	        {
           header: "所属栏目",
           dataIndex: 'category',
           width: 100,
           renderer:this.categoryRender
        }
        ,{
           header: "点击数",
           dataIndex: 'readTimes',
           width: 60,
           align: 'center'
        },{
           header: "评论数",
           dataIndex: 'comments',
           width: 60 ,
           renderer:this.commentsRender         
        },{          
           header: "发表时间",
           dataIndex: 'inputTime',
           width: 120,renderer:this.dateRender()
        }]);		
	ArticleList.superclass.initComponent.call(this);
	}     
});
PhotoList=Ext.extend(BaseGridList,{
	id:"photoList",
	title:"照片列表",	
	url:"album.ejf?cmd=list",	
	gridViewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true,
            getRowClass : function(record, rowIndex, p, store){
                if(this.showPreview){
                    p.body = '<div class="photo"><img src="'+record.data.path+'"></div><div class="sumary">摘要：'+record.data.intro+'</div>';
                    return 'x-grid3-row-expanded';
                }
                return 'x-grid3-row-collapsed';
            }
        },
    storeMapping:["id","title","intro","path","category","readTimes","inputTime","comments","content",{name:"op",mapping:"id"}],
    userRender:function(v){
    	return v?v.name:"未知原因";
    },
    titleRender:function(value, p, record){    
    	return String.format('<b><a href="javascript:previewPhoto(\'{0}\')">{1}</a></b><br/>',record.data.path,value);
    },
    categoryRender:function(value,p,record)
	{
		if(!value)return "";
		else return value.name;
	},
	commentsRender:function(value, p, record){   
    	return value?value.length:0;
    }, 
    booleanRender:function(value, p, record){    
    	return value?"是":"否";
    }, 
    emailRender:function(v){
    	return v?v.email:"未知原因";
    },
	initComponent : function(){	
	this.cm=new Ext.grid.ColumnModel([{        
           header: "主题",
           dataIndex: 'title',
           width: 300,
           renderer:this.titleRender
        },
        {        
           header: "Category",
           dataIndex: 'category',
           width:100,
           renderer:this.categoryRender
        },
        {
           header: "点击数",
           dataIndex: 'readTimes',
           width: 70,
           align: 'center'
        },{
           header: "评论数",
           dataIndex: 'comments',
           width: 70   ,
           renderer:this.commentsRender            
        },{          
           header: "发表时间",
           dataIndex: 'inputTime',
           width: 120,renderer:this.dateRender()
        }]); 		
	PhotoList.superclass.initComponent.call(this);
	}     
});

CodeListPanel=Ext.extend(Ext.Panel,{
	id:"codeListPanel",
	title:"实用源码",
	layout:"fit",	
	loadData:false,	
	closable: true,
	border:false,
	layoutConfig:{columns:2},
  	autoScroll:true,
  	openUrl:function(url){
  		if(url)
  		window.open(url);
  		
  	},
	initComponent : function(){
	var codes=[{title:"程序主框架",
				html:"用Viewport做的一个简单示例框架，其他应用可以直接使用该框架作为基础来直接搭建!",
				intro:"http://www.vifir.com/entry/view/917504.html",
				demo:"http://www.vifir.com/resources/records/codes/framework1/framework1.html",
				download:"http://www.vifir.com/portal.ejf?cmd=downloadVipFile&name=codes/framework1.zip"
				},
			   {title:"DWRStore",
			   intro:"http://www.vifir.com/entry/view/917505.html",
			   demo:"http://www.vifir.com/resources/records/codes/dwrstore/dwrstore.html",
			   download:"http://www.vifir.com/portal.ejf?cmd=downloadVipFile&name=codes/dwrstore.zip",
			   html:"直接可以用来处理基于远程脚本调用的表格数据，适合配合EasyJWeb中的远程脚本调用引擎、DWR框架等使用!"},
			   {title:"自动生成基于ExtJS的表格添删改查",
			   intro:"http://www.vifir.com/entry/view/917507.html",
			   demo:"http://www.blogjava.net/williamraym/archive/2008/03/20/187451.html",
			   download:"http://www.vifir.com/portal.ejf?cmd=downloadVipFile&name=codes/extjs-crud-template.zip",
			    html:"不需要建表、也不需要写JS，只需要一个Entity(实体模型)，然后一个命令就搞定添删改查应用!"},
			   {title:" 树的拖放处理及DWRTreeLoader",
			   intro:"http://www.vifir.com/entry/view/917506.html",
			   demo:"http://www.vifir.com/resources/records/codes/ddtree/ddtree.html",
			   download:"http://www.vifir.com/portal.ejf?cmd=downloadVipFile&name=codes/ddtree.zip",
			    html:"演示如何实现两棵树的拖放操作，拖放后如何处理等!"},
			    
			   {title:"ExtJS的远程交互演示",
			    html:""},  	
			    
			   {title:"基于EJS+ExtJS的简单构架",
			    demo:"http://www.vifir.com/resources/records/codes/ejs/ejse.html",
			   intro:"http://www.vifir.com/entry/view/1212416.html",
			   download:"http://www.vifir.com/portal.ejf?cmd=downloadVipFile&name=codes/ejse.zip",
			    html:"EasyJWeb1.1+Spring2.5+JPA(Hibernate3.2)+ExtJS2.0的应用程序骨架"},
			    
			   {title:"基于SSH+ExtJS的简单构架1",
			   	demo:"http://www.vifir.com/resources/records/codes/ssh2/ssh1.html",
			    intro:"http://www.vifir.com/entry/view/1179671.html",
			    download:"http://www.vifir.com/portal.ejf?cmd=downloadVipFile&name=codes/ssh1.zip",
			    html:"Struts1.2+Spring2+Hibernate3.2+ExtJS2.0的应用程序简单构架"},
			    
			   {title:"基于SSH+ExtJS的简单构架2",
			   demo:"http://www.vifir.com/resources/records/codes/ssh2/ssh2.html",
			   intro:"http://www.vifir.com/entry/view/1310720.html",
			   download:"http://www.vifir.com/portal.ejf?cmd=downloadVipFile&name=codes/ssh2.zip",
			    html:"Struts2.0+Spring2.5+Hibernate3.2+ExtJS2.0的应用程序骨架"},
			    {title:"Wlr单用户Blog综合实例1",
			   intro:"http://www.vifir.com/entry/view/8.html",
			   demo:"http://wlr1.easyjf.com",
			   download:"http://www.vifir.com/download.html",
			    html:"用ExtJS开发的单用户Blog系统，是一个比较综合的实例，比较适合有一定基础者参考，后台使用Java平台，技术构架为EJS。"},
			    {title:"Wlr单用户Blog综合实例2",
			     intro:"http://www.vifir.com/entry/view/1245184.html",
			   demo:"http://wlr2.easyjf.com",
			   download:"ftp://ftp1.easyjf.com/easyjweb/demo/blog2.zip",
			    html:"Wlr单用户Blog系统的另外一个版本，直接通过写java来开发基于ExtJS的应用，技术构架为EJS。"},
			    {title:"Wlr单用户Blog综合实例3",
			    intro:"http://www.vifir.com/entry/view/1245184.html",
			    demo:"http://www.vifir.com/resources/records/codes/wlrblog-net/wlrblog-net.html",
			    download:"http://www.vifir.com/download/extblog-net.zip",
			    html:"后台使用.Net平台的Wlr单用户Blog系统。"},
			    {title:"Wlr单用户Blog综合实例4",
			    html:"基于Php平台的Wlr单用户Blog系统。"} ];	
	for(var i=0;i<codes.length;i++)codes[i]=Ext.apply(codes[i],{border:true,height:115,tbar:[{text:"详情",cls:"x-btn-text-icon",icon:"images/xiangqing.gif",handler:this.openUrl.createCallback(codes[i].intro),disabled:!codes[i].intro},{text:"演示",cls:"x-btn-text-icon",icon:"images/yanshi.gif",handler:this.openUrl.createCallback(codes[i].demo),disabled:!codes[i].demo},{text:"下载",cls:"x-btn-text-icon",icon:"images/download.gif",handler:this.openUrl.createCallback(codes[i].download),disabled:!codes[i].download}]});
	var items1=[],items2=[],items3=[];
	for(var i=0;i<4;i++)items1[items1.length]=codes[i];
	for(var i=4;i<8;i++)items2[items2.length]=codes[i];
	for(var i=8;i<12;i++)items3[items3.length]=codes[i];
	CodeListPanel.superclass.initComponent.call(this);
	var p=new Ext.Panel({border:false,layout:"column",defaults:{border:false},items:[{columnWidth:.33,autoScroll:true,layout:"form",layoutConfig:{columns:5},items:items1},{columnWidth:.33,layout:"form",autoScroll:true,layoutConfig:{columns:5},items:items2},{columnWidth:.34,layout:"form",autoScroll:true,layoutConfig:{columns:5},items:items3}]});
	this.add(p);
	}     
});

MettingListPanel=Ext.extend(Ext.Panel,{
	id:"mettingListPanel",
	title:"在线课堂",
	layout:"fit",	
	loadData:false,
	closable: true,
	border:false,
	layoutConfig:{columns:2},
  	autoScroll:true,
  	save:function(){
  		var id=this.fp.form.findField("id").getValue();		
		this.fp.form.submit({
				waitMsg:"正在保存。。。",
	            url:"chatRoom.ejf?cmd="+(id?"update":"save"),
	            method:'POST',
	            success:function(){
	           	this.closeWin();
	           	this.refresh();          	
	            },
	            scope:this
		});	
  	},  	
  	closeWin:function(){
  		if(this.win)this.win.hide();
  	},
  	edit:function(r){
  		this.createWin();
  		this.fp.form.loadRecord(r);
  	},
  	refresh:function(){
  		if(this.panel.items){
  			this.remove(0);
  			this.panel=new Ext.Panel({autoScroll:true});
			this.add(this.panel);
			this.doLayout();
  		}
  		this.store.reload();
  	},
  	createWin:function(){
  		if(!this.win){
  			this.win=new Ext.Window({title:"申请新课堂",iconCls:"icon-application",width:500,height:440,closeAction:"hide"});
  			this.fp=new Ext.form.FormPanel({frame:true,defaultType:"textfield",
  			labelWidth:100,		
        	labelAlign:'right',
        	defaults:{width:330},
  			items:[
  				{xtype:"hidden",name:"id"},
	  			{fieldLabel:"课堂主题",name:"title"},
	  			{fieldLabel:"主讲",name:"teacher"},
	  			{fieldLabel:"最大在线人数",name:"maxUser"},
	  			{fieldLabel:"开始时间",xtype:"datefield",name:"beginTime",format:"Y-m-d G:i:s"},
	  			{fieldLabel:"结束时间",xtype:"datefield",name:"endTime",format:"Y-m-d G:i:s"},
	  			{fieldLabel:"刷新时间",name:"intervals"},
	  			{fieldLabel:"访问权限",name:"vrtype"},
	  			{fieldLabel:"课堂主题",xtype:"textarea",name:"intro",height:100},
	  			{fieldLabel:"课堂公告",xtype:"textarea",name:"announce"}
  			],
  			buttons:[{text:"保存",handler:this.save,scope:this},{text:"取消",handler:this.closeWin,scope:this}]});
  		
  		this.win.add(this.fp);
  		}
  		this.fp.form.reset();
  		this.win.show();
  	},
	initComponent : function(){	
	this.tbar=[{text:"申请新课堂",cls:"x-btn-text-icon",icon:"images/application.gif",handler:this.createWin,scope:this},{text:"刷新",cls:"x-btn-text-icon",icon:"images/core/refresh.gif",handler:this.refresh,scope:this}];
	this.bbar=[""];
	MettingListPanel.superclass.initComponent.call(this);
	this.store=new Ext.data.JsonStore({
    url: 'chatRoom.ejf?cmd=list',
    root: 'result',
    fields: ['id', 'title',"intro","announce","teacher","beginTime","endTime","talkMode","intervals","vrtype","owner","status","currentUsers","maxUser","inputTime","statusInfo"]
	});
	this.store.on("load",function(store,records){		
		for(var i=0;i<records.length;i++){
		this.panel.add({height:100,
			title:records[i].data.title,
			html:records[i].data.intro,
			tbar:["<img src='images/fettle.gif'/>","状态:","<font color=blue>"+records[i].data.statusInfo,"-",
			      "<img src='images/core/start.gif'/>","创建时间:","<font color=blue>"+records[i].data.inputTime.format("Y-m-d H:i:s"),"-","<img src='images/applicationpop.gif'/>","创建人:<font color=blue>"+records[i].data.owner+"</blue>",
			      "->",{text:"进入课堂",cls:"x-btn-text-icon",icon:"images/metting.gif",handler:MettingManager.joinMeeting.createDelegate(MettingManager,[records[i].data.id])},{text:"修改",cls:"x-btn-text-icon",icon:"images/core/edit.gif",handler:this.edit.createDelegate(this,[records[i]]),scope:this},{text:"删除",cls:"x-btn-text-icon",icon:"images/core/delete.gif",handler:MettingManager.removeMetting.createCallback(records[i].data.id)},{text:"启动",cls:"x-btn-text-icon",icon:"images/ho.gif",handler:MettingManager.startMetting.createCallback(records[i].data.id)},{text:"停止",cls:"x-btn-text-icon",icon:"images/logout.gif",handler:MettingManager.stopMetting.createCallback(records[i].data.id)},"   "]
			});	
		}
		this.panel.doLayout();
	},this);
	this.store.load();
	this.panel=new Ext.Panel({autoScroll:true});
	this.add(this.panel);
	/*var tpl = new Ext.XTemplate(
    '<tpl for=".">',
        '<div class="thumb-wrap" id="{id}"><h3>{title}   状态：{statusInfo}</h3>',
        '<div class="thumb">公告:{announce}</div>',
        '<span class="x-editable">简介：{intro}</span><a href="#" onclick="MettingManager.joinMeeting({id})">进入课堂>></a>    <a href="#" onclick="MettingManager.startMetting({id})">启动课堂</a></div><hr/>',
    '</tpl>',
    '<div class="x-clear"></div>'
	);
	this.add(new Ext.DataView({
        store: this.store,
        tpl: tpl,
        autoHeight:true,
        multiSelect: true,
        overClass:'x-view-over',
        itemSelector:'div.thumb-wrap',
        emptyText: '当前没有任何课堂开放!'
    }));*/
	},
	onRender : function(ct, position){
	 	MettingListPanel.superclass.onRender.call(this, ct, position);
	 	//this.el.on("click",function(){MettingManager.winMgr.hideAll();});
	 }     
});



UserCategoryManage=function()
{
this.storeMapping=["id","name","intro",{name:"op",mapping:"id"}	];
this.operationRender=function(obj){
	return !obj||obj=="-1"?"":"<a href='javascript:removeTopicCategory("+obj+")'>删除</a>";
	};
this.store=new Ext.data.DWRStore({		
  		id:"id",
  		fn:userCategoryService.getUserCategoryBy,  		
  		root:"result",
  		totalProperty:"rowCount",
  		remoteSort:true,  		
    	fields:this.storeMapping
  		});
this.cm=new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer({header:"序号",width:40,sortable:true}),
		{header: "分类名称",width:120,dataIndex:"name",editor: new Ext.form.TextField({				   
	               allowBlank: false
	           })},
		{header: "简介",dataIndex:"intro",editor:new Ext.form.TextField()}
		]);	
this.store.paramNames.sort="orderBy";// 改变排序参数名称
this.store.paramNames.dir="orderType";// 改变排序类型参数名称
this.cm.defaultSortable=true;
this.grid=new Ext.grid.EditorGridPanel({
			id:"userCategoryGrid",	
 			store:this.store,
  			cm:this.cm, 
        	loadMask: true,
        	clicksToEdit:1,
        	autoExpandColumn:2,
        	frame:true,
 			region:"center",
 			tbar: [
				{                 
                   text:"新增分类",
				   cls:"x-btn-text-icon",
				   icon:"images/core/add.gif",
                   handler:this.addCategory,
                   scope:this                 
				}, {                 
                   text:"删除分类",
				   cls:"x-btn-text-icon",
				   icon:"images/core/delete.gif",
                   handler:this.removeCategory,
                   scope:this      
				},
				{                 
                   text:"刷新",
				   cls:"x-btn-text-icon",
				   icon:"images/core/refresh.gif",
                   handler:this.refresh,
                   scope:this      
				},' ',new Ext.Toolbar.Fill(),"查询"
            ]
		});
this.grid.on("afteredit",this.afterEdit,this);
this.tree=new  Ext.tree.TreePanel({title:"分类主题",
 			region:"west",
 			width:150, 	
 			rootVisible:false,
 			lines:false,		
 			root:new Ext.tree.TreeNode({
 				id:"root",
   				expanded:true
   				})
 			});
this.tree.root.appendChild(new Ext.tree.TreeNode({id:"article",text:"文章分类",iconCls:"icon-article"}));
this.tree.root.appendChild(new Ext.tree.TreeNode({id:"photo",text:"照片分类",iconCls:"icon-img"}));
this.tree.root.appendChild(new Ext.tree.TreeNode({id:"movie",text:"视频分类",iconCls:"icon-video"}));
this.tree.on("click",function(node,eventObject){
	if(node!=this.tree.root){
	this.store.removeAll();
	this.store.baseParams={types:node.id};
	this.store.load();
	}
},this);
UserCategoryManage.superclass.constructor.call(this, {
		id:"userCategoryPanel",
		title:"分类信息管理",
		closable: true,
  		autoScroll:true,
  		layout:"border",
 		items:[this.tree, this.grid]           
   	 });
this.store.load({params:{types:"article"}});
}; 
Ext.extend(UserCategoryManage, Ext.Panel,{
	refresh:function(){
		this.store.removeAll();
		this.store.reload();
	},
	addCategory:function(){
	    var create=Ext.data.Record.create(this.storeMapping);
	  	var obj=new create({id:'-1',name:'',intro:''});
		this.grid.stopEditing();
		this.store.insert(this.store.getCount(),obj);	
		this.grid.startEditing(this.store.getCount()-1, 0);
   	},
    removeCategory:function(){
    	var cell=this.grid.getSelectionModel().getSelectedCell();
    	if(!cell){
    		Ext.Msg.alert("提示","请先选择要操作的行!");
    		return false;
    	}
    	var record=this.grid.getStore().getAt(cell[0]);
    	var id=record.get("id");
    	var tree=this.tree;
    	var store=this.store;
	   	var m=Ext.MessageBox.confirm("Confirm","是否真的要删除数据？",function(ret){	   		
			if(ret=="yes"){				
				userCategoryService.delUserCategory(id,function(ret){					
					store.remove(store.getById(id));
				});				
			}
		}
		);
   	},
   	afterEdit:function(obj){   		
	   	var r=obj.record;
		var id=r.get("id");
		var name=r.get("name");
		var c=this.record2obj(r);
		var tree=this.tree;
		var node=tree.getSelectionModel().getSelectedNode();
		c.types=node?node.id:"article";	
		if(id=="-1" && name!=""){					
			userCategoryService.addUserCategory(c,function(ret){
				if(ret)r.set("id",ret);
				obj.grid.store.reload();				
			});
		}
		else if(name!="")
		{	
			userCategoryService.updateUserCategory(r.get("id"),c,function(ret){					
			});
		}
   	},
   	record2obj:function(r)
	{
		return {name:r.get("name"),
		intro:r.get("intro")
		};
	}
});




function previewPhoto(url){
	photoPreview.el.dom.firstChild.src=url;
	photoPreview.el.center();
	photoPreview.el.show(true);
}
function changePassword(){
	var changePasswordWin=new ChangePasswordWindow();
	Ext.get("desktop").mask();
	changePasswordWin.show();	
}
/********暂时不知道功能如何使用
function login()
{
	OnlineMessageManager.stop();
	if(!loginWin)loginWin=new LoginWindow();
	Ext.get("desktop").mask("您还没有登录，请先登录!");	
	loginWin.show();
}
**********/
function logout(){
	Ext.Msg.confirm("友情提示","是否真的要注销当前用户?",function(btn){
		if(btn=="yes"){
			location = '/logout.jsp';
		}	
	});
}


function addTab(strTitle, strNodeId, strHref){
	main.add(new Ext.Panel({
                    id:strNodeId,
                    title:strTitle,
                    autoScroll:true,
                    closable:true,
                    html:"<div style='height:100%;height:100%'>"
                        +"<iframe id='ifrm_"+strNodeId+"' name='ifrm_"+strNodeId+"' src='"+strHref+"' " 
                        +"width='100%' height='100%' frameborder=0/>"
                        +"</div>"
                }))
}
