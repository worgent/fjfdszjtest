
ArticleMenuPanel = function() {
	ArticleMenuPanel.superclass.constructor.call(this, {
		autoScroll : true,
		animate : true,
		border : false,
		rootVisible : false,
		root : new Ext.tree.TreeNode( {
			text : 'Blog信息管理',
			draggable : false,
			expanded : true
		})
	});
	this.root.appendChild(new Ext.tree.TreeNode( {
		text : "写新文章",
		iconCls:"icon-worknote",
		listeners:{'click':function(){
   			var panel=Ext.getCmp("writeTopicPanel");
   			if(!panel){
   				panel=new WriteTopicPanel();
   			}
   			main.openTab(panel);
   			}}	
		}));
	this.root.appendChild(new Ext.tree.TreeNode( {
		text : "上传照片",
		iconCls:"icon-unload",
		listeners:{'click':function(){
   			var panel=Ext.getCmp("writeAlbumPanel");
   			if(!panel){
   				panel=new WriteAlbumPanel();
   			}
   			main.openTab(panel);
   			}}	
		}));	
	this.articleNode=new Ext.tree.AsyncTreeNode( {
		text : "文章管理",
		iconCls:"icon-article_manager",
		id:"root_article",
		loader:new Ext.tree.TreeLoader({url:"?cmd=getUserCategory&types=article"})		
	});	
	this.photoNode=new Ext.tree.AsyncTreeNode( {
		text : "照片管理",
		iconCls:"icon-img_manager",
		id:"root_photo",
		loader:new Ext.tree.TreeLoader({url:"?cmd=getUserCategory&types=photo"})
	});
	this.root.appendChild(this.articleNode);
	this.root.appendChild(this.photoNode);
	this.on("click",function(node,eventObject)
  	 {
	   	if(node==this.articleNode||this.articleNode.contains(node)){
	   		var panel=Ext.getCmp("topicListPanel");
	   		if(!panel){
	   				panel=new TopicListManage();
	   				removeTopic=function(id){
	   					panel.grid.getSelectionModel().selectRecords([panel.store.getById(id)]);
	   					panel.removeData();};
	   				editTopic=function(id){
	   					panel.grid.getSelectionModel().selectRecords([panel.store.getById(id)]);
	   					panel.edit();
	   					};
	   			}
	   		main.openTab(panel);
	   		if(this.articleNode!==node){
			   		panel.store.baseParams.myCategoryId=node.id;
			   		panel.currentMyCategory={id:node.id,name:node.text};
			}else panel.currentMyCategory=null;
	   		panel.store.baseParams.mine=true;
	   		panel.store.removeAll();
	   		panel.store.reload();
	   	} else if(this.photoNode==node||this.photoNode.contains(node)){
	   		var panel=Ext.getCmp("albumListPanel");
			   		if(!panel){
			   				panel=new AlbumListManage();
			   				removeAlbum=function(id){
			   					panel.grid.getSelectionModel().selectRecords([panel.store.getById(id)]);
			   					panel.removeData();};
			   				editAlbum=function(id){
			   					panel.grid.getSelectionModel().selectRecords([panel.store.getById(id)]);
			   					panel.edit();
			   					};
			   			}
			   		main.openTab(panel);			   		
			   		if(this.photoNode!==node){
			   		panel.store.baseParams.myCategoryId=node.id;
			   		panel.currentMyCategory={id:node.id,name:node.text};
			   		}else panel.currentMyCategory=null;
			   		panel.store.baseParams.mine=true;
			   		panel.store.removeAll();
			   		panel.store.reload();
	   	}
  	 },this);
  	
	this.root.appendChild(new Ext.tree.TreeNode( {
		text : "分类管理",
		iconCls:"icon-fenlei",
		listeners:{'click':function(){
   			var panel=Ext.getCmp("userCategoryPanel");
   			if(!panel){
   				panel=new UserCategoryManage();
   			}
   			main.openTab(panel);
   			}
   		}
	}));
  this.root.appendChild(new Ext.tree.TreeNode( {
		text : "我的Blog预览",
		iconCls:"icon-blog",
		listeners:{'click':function(){
   			window.open("blog.ejf?id="+OnlineMessageManager.me.id);
   			}
   		}
	}));
	
}
Ext.extend(ArticleMenuPanel, Ext.tree.TreePanel);

VirfirMenu = function() {
	VirfirMenu.superclass.constructor.call(this, {
		autoScroll : true,
		animate : true,
		border : false,
		rootVisible : false,
		root : new Ext.tree.TreeNode( {
			text : 'Vifir.com',
			draggable : false,
			expanded : true
		})
	});
	this.articleNode=new Ext.tree.AsyncTreeNode( {
		text : "文章",
		iconCls:"icon-article",
		id:"root_1",
		loader:Global.topicCategoryLoader
	});
	this.root.appendChild(this.articleNode);
	this.photoNode=new Ext.tree.AsyncTreeNode( {
		text : "照片",
		iconCls:"icon-img",
		id:"root_2",
		loader:Global.albumCategoryLoader
	});
	this.root.appendChild(this.photoNode);
	this.movieNode=new Ext.tree.TreeNode( {
		text : "视频及录象",
		iconCls:"icon-video",
		listeners : {
			
		}
	});
	this.movieNode.appendChild(new Ext.tree.TreeNode( {
		text : "ExtJS",
		icon:"images/menuPanel/tag_blue4.gif",
		iconCls:"",
		listeners : {			
		}
	}));
	this.movieNode.appendChild(new Ext.tree.TreeNode( {
		text : "Struts2",
		icon:"images/menuPanel/tag_blue4.gif",
		listeners : {			
		}
	}));
	this.movieNode.appendChild(new Ext.tree.TreeNode( {
		text : "EasyJWeb",
		icon:"images/menuPanel/tag_blue4.gif",
		listeners : {			
		}
	}));
	this.movieNode.appendChild(new Ext.tree.TreeNode( {
		text : "Spring",
		icon:"images/menuPanel/tag_blue4.gif",
		listeners : {			
		}
	}));
	this.root.appendChild(this.movieNode);
	
	this.root.appendChild(new Ext.tree.TreeNode( {
		text : "开源作品展",
		icon:"images/blog/kaiyuan.gif",
		listeners : {
			"click":function(node){
				
			}
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode( {
		text : "源码下载",
		icon:"images/blog/code_download.gif",
		listeners : {
			"click":function(node){
				main.loadUrl("?cmd=download");
			}
		}
	}));
	this.on("click",function(node){
		if(this.articleNode==node || this.articleNode.contains(node)){// 点文章
			var panel = Ext.getCmp("articleList");
			if (!panel) {
				panel = new ArticleList();
			}else{panel.store.removeAll();}
			main.openTab(panel);
			panel.store.baseParams={categoryId:this.articleNode==node?"":node.id};
			panel.store.reload();				
		}else if(this.photoNode==node || this.photoNode.contains(node)){// 点照片
			var panel = Ext.getCmp("photoList");
			if (!panel) {
				panel = new PhotoList();
			}else{panel.store.removeAll();}
			main.openTab(panel);
			panel.store.baseParams={categoryId:this.photoNode==node?"":node.id};
			panel.store.reload();
						
		}else if(this.movieNode==node||this.movieNode.contains(node)){// 点视频
			
		}
	});	
}
Ext.extend(VirfirMenu, Ext.tree.TreePanel);

//2008.7.19国通车辆管理系统
//系统主菜单
SystemMenuPanel = function() {
	SystemMenuPanel.superclass.constructor.call(this, {
		autoScroll : true,
		animate : true,
		border : false,
		rootVisible : false,
		root : new Ext.tree.AsyncTreeNode( {
			text : '系统管理',
			draggable : false,
			expanded : true
		}),
		loader : new Ext.tree.TreeLoader({
			dataUrl:'/system/loadMenu.shtml?type=json',
			baseParams: {menuId:'Root'},
			clearOnLoad : false ,
			preloadChildren : false,
			requestMethod : "GET" 
		}),
		listeners : {
			beforeload :function(node){
				if ('ynode-13' != node.attributes.id)
					this.loader.baseParams.menuId = node.attributes.id;
			},
			click : function(node) {
				if (null != node.attributes.url){
					panel = new Ext.Panel({
                            id : node.attributes.id,
                            title : node.attributes.title,
                            autoScroll:true,
                            closable:true,
                            html:"<div style='height:100%;height:100%'>"
                                +"<iframe id='ifrm_"+node.attributes.id+"' name='ifrm_"+node.attributes.id+"' src='"+node.attributes.url+"' " 
                                +"width='100%' height='100%' frameborder=0/>"
                                +"</div>"
                        });
					main.openTab(panel);
				}
			}
		}
	});
	//loader.on("beforeload", function(treeLoader,node){
	//	this.baseParams.menuId = node.attributes.id;
	//}, loader);
}
Ext.extend(SystemMenuPanel, Ext.tree.TreePanel);

OtherMenuPanel = function() {
	OtherMenuPanel.superclass.constructor.call(this, {
		autoScroll : true,
		animate : true,
		border : false,
		rootVisible : false,
		root : new Ext.tree.TreeNode( {
			text : '系统管理',
			draggable : false,
			expanded : true
		})
	});
	var c=new Ext.tree.TreeNode( {
		text : "杂项管理",
		iconCls:"icon-zaxiang",
		listeners : {
			
		}
	});
	c.appendChild(new Ext.tree.TreeNode( {
		text : "评论管理",
		icon:"images/menuPanel/tag_blue4.gif",
		listeners : {
			'click' : function() {
				var panel = Ext.getCmp("commentPanel");
				if (!panel) {
					panel = new CommentPanel();
				}
				main.openTab(panel);
			}
		}
	}));
	c.appendChild(new Ext.tree.TreeNode( {
		text : "友情链接",
		icon:"images/menuPanel/tag_blue4.gif",
		listeners : {
			'click' : function() {
				var panel = Ext.getCmp("linkPanel");
				if (!panel) {
					panel = new LinkPanel();
				}
				main.openTab(panel);
			}
		}
	}));	
	this.root.appendChild(c);
	
	this.root.appendChild(new Ext.tree.TreeNode( {
		text : "Blog属性设置",
		iconCls:"icon-blog_attribute",
		listeners : {
			'click' : function() {
				var panel = Ext.getCmp("blogPropertiesPanel");
				if (!panel) {
					panel = new BlogProperties();
				}
				main.openTab(panel);
			}
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode( {
		text : "修改密码",
		icon:"images/system/key.gif",
		listeners : {"click":changePassword}
	}));
	this.root.appendChild(new Ext.tree.TreeNode( {
		text : "系统文档",
		iconCls:"icon-sys_document",
		listeners : {
			'click' : function() {		
				window.open('http://wlr.easyjf.com/doc.html','doc','');
			}
		}
	}));
}
Ext.extend(OtherMenuPanel, Ext.tree.TreePanel);

MenuPanel = function() {
	MenuPanel.superclass.constructor.call(this, {
		id : 'menu',
		region : 'west',
		title : "系统菜单",
		split : true,
		width : 170,
		minSize : 125,
		maxSize : 300,
		collapsible : true,
		margins : '0 0 5 5',
		cmargins : '0 0 0 0',		
		layout : "accordion",
		defaults:{autoScroll:true},
			layoutConfig : {
				titleCollapse : true,
				animate : true
			},
			items:[
			{
				title : "系统管理",
				iconCls:"icon-vip",
				items : [new SystemMenuPanel()]
			}]
		});
};
Ext.extend(MenuPanel, Ext.Panel);