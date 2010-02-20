Ext.onReady(function() {
			header = new Ext.Panel( {
				border : true,
				region : 'north',
				layout:'anchor',
				height : 90,
				items : [{
					xtype:"box",
					border : true,
					el:"header",
            		anchor: 'none -23'
					},
					new Ext.Toolbar({items:[/* '文章查询:',
											{
												xtype:"textfield",
												width:200,
												id:"search"
											},{
												text:"搜索",
												cls:"x-btn-text-icon",
												icon:"images/search.gif"
											},*/"->",/*{
												text:"WebQQ",
												cls:"x-btn-text-icon",
												icon:"images/qq/im.gif",
												handler:function(){
													onlineWindow.show();
												}
											},"-",{
												text:'消息设置',
												cls:"x-btn-text-icon",
												icon:"images/OnlineMessageManager.gif",
												handler:OnlineMessageManager.config,
												scope:OnlineMessageManager
											},*/{
												text:"更改密码",
												cls:"x-btn-text-icon",
												icon:"images/key.gif",
												handler:changePassword
											},"",{
												text:"注销用户",
												cls:"x-btn-text-icon",
												icon:"images/stop.gif",
												handler:logout
											}/*,"-","更换皮肤:",{
												xtype:"combo",
												transform:"skins",
												lazyRender:true,
												triggerAction:"all",
												listeners:{"select":function(c){
													changeSkin(c.getValue());
												}
											}
										}*/]
									})
					]
			});
			changeSkin = function(value) {
				Ext.util.CSS
						.swapStyleSheet('window',
								'/plugins/extjs/ext-2.0/resources/css/' + value
										+ '.css');
			};
			menu = new MenuPanel();
			main = new MainPanel();
//			var currentUser=new Ext.Toolbar.TextItem(OnlineMessageManager.me.name);	
			bottom=	new Ext.Toolbar({id:"bottom",frame:true,region:"south",height:25,items:["当前用户：",currentUser,new Ext.Toolbar.Fill(),"版权所有　<font color=blue>福建国通信息科技有限公司</font>　　　　"]});
			bottom.currentUser=currentUser;
			var viewport = new Ext.Viewport( {
				layout : 'fit',
				items : [{id:"desktop",layout:"border",items:[header, menu, main,bottom]}]
			});
///判断是否登录	login();
						// OnlineMessageManager.openMessage({sender:OnlineMessageManager.me,content:"测试一下",inputTime:new Date()});
			//MettingManager.joinMeeting(1);
//			OnlineMessageManager.start.call(OnlineMessageManager);
			/***************
			setTimeout(function() {
				Ext.get('loading').remove();
				Ext.get('loading-mask').fadeOut( {
					remove : true
				});				
				photoPreview = new Ext.Resizable('photoPreview', {
		            wrap:true,
		            pinned:true,
		            minWidth:50,
		            minHeight: 50,
		            preserveRatio: true,
		            handles: 'all',
		            draggable:true,
		            dynamic:true
		        });
		       // document.body.insertBefore(customEl.dom,
				// document.body.firstChild);
		        photoPreview.el.on('dblclick', function(){
		            photoPreview.el.hide(true);
		        });
				photoPreview.el.hide();
				
				// var panel=new
				// Ext.Panel({renderTo:"msgPanel",title:"提示",frame:true,width:400,height:200});
				// panel.hide();
			}, 300);
			*****************/
		})