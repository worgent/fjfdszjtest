<%@ page language="java"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String path = request.getContextPath();
%>
<html>
<head>
<title>网站商城</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path %>/css/ext.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=path %>/include/ext/resources/css/ext-all.css" />
<!-- 图标样式 CSS 信息 -->
    <!-- GC -->
 	<!-- LIBS -->
 	<script type="text/javascript" src="<%=path %>/include/ext/adapter/ext/ext-base.js"></script>
 	<!-- ENDLIBS -->
 	
    <script type="text/javascript" src="<%=path %>/include/ext/ext-all.js"></script>
    
	<!-- 中文提示信息支持 -->
	<script type="text/javascript" src="<%=path %>/include/ext/source/locale/ext-lang-zh_CN.js"></script>
   <script type="text/javascript" src="<%=path %>/include/ext/miframe.js"></script>
   <script type="text/javascript" src="<%=path %>/include/ext/ThemeChanger.js"></script>
   
  <script type='text/javascript' src='<%=path %>/dwr/interface/UserService.js'></script>
  <script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>



<script type="text/javascript">
<!--

Ext.BLANK_IMAGE_URL = '<%=path %>/include/ext/resources/images/default/s.gif';

Ext.example = function(){
	
    //----创建气泡
    var msgCt;

    function createBox(t, s){
        return ['<div class="msg">',
                '<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
                '<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><h3>', t, '</h3>', s, '</div></div></div>',
                '<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
                '</div>'].join('');
    }
    return {
        msg : function(title, format){
            if(!msgCt){
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
            }
            msgCt.alignTo(document, 't-t');
            var s = String.format.apply(String, Array.prototype.slice.call(arguments, 1));
            var m = Ext.DomHelper.append(msgCt, {html:createBox(title, s)}, true);
            m.slideIn('t').pause(1).ghost("t", {remove:true});
        }
    };
}();




//-----------EXT


Ext.onReady(function(){
  
   Ext.QuickTips.init();


   Ext.state.Manager.setProvider(new Ext.state.CookieProvider({
        path: "/",
        expires: new Date(new Date().getTime()+(1000*60*60*24*30)) //30 days
    }));

   Ext.form.Field.prototype.msgTarget = 'side';

   
   var form = new Ext.form.FormPanel({
        baseCls: 'x-plain',
        labelWidth: 80,
        defaultType: 'textfield',

        items: [
        {
            fieldLabel: '原密码',
            id: 'oldpass',
            name: 'oldpass',
            inputType: 'password',
            allowBlank:false,
            maxLength: 16,
            width: 200,
            blankText:'必须输入原密码！'
            
        },
        {
            fieldLabel: '新密码',
            id: 'newpass1',
            name: 'newpass1',
            width: 200,
            inputType: 'password',
            maxLength: 16,
            allowBlank:false,
            blankText:'请输入新密码！'
        },
        {
            fieldLabel: '重新确认密码',
            id: 'newpass2',
            name: 'newpass2',
            inputType: 'password',
            width: 200,  
            maxLength: 16,
            allowBlank:false,
            blankText:'请再次确认输入新密码！'
        }
        ]
    });


        var win = new Ext.Window({
        
        title: '修改用户登录密码',
        width: 380,
        height:180,
        minWidth: 300,
        minHeight: 150,
        layout: 'fit',
        iconCls: 'changepasswd',
        plain:true,
        modal : true,
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        items: form,
        animEl: 'changepwd',

        buttons: [{
                    text:'保存',
                    handler: function(){
         
                        
                        if(!form.getForm().isValid()) {
                           
                           Ext.Msg.alert("出错了!","无法保存数据，有必填项未输入!");
                           return;
                        }
                            
                        var oldpass=Ext.get('oldpass').dom.value;
                        var newpass1=Ext.get('newpass1').dom.value;
                        var newpass2=Ext.get('newpass2').dom.value;
                        
                        if(newpass1!=newpass2) {
                           
                           Ext.Msg.alert("出错了!","两次新密码输入的不一样!");
                           return;
                        } 
                        
                     
                        Ext.Msg.wait("请等候", "保存中", "操作进行中..."); 
                        UserService.changePwd('<%=request.getSession().getAttribute("adminId")%>', oldpass, newpass1, function(data) {
            		       if(data) {
		                      Ext.get('oldpass').dom.value="";
		                      Ext.get('newpass1').dom.value="";
		                      Ext.get('newpass2').dom.value="";
		                      Ext.Msg.alert("成功","恭喜，修改个人登录密码成功!");		                
		                
    				       } else {
							  Ext.Msg.alert("出错了!","对不起，修改个人登录密码失败!");
				           }
				           win.hide();
            	        });
                        
                     }
                },
                {
                    text: '取消',
                    handler: function(){
                        win.hide();
                     }
                }]
            	
    });

    
    
    
    //------------------菜单-------------------------------------------------------------
    
    var myThemeChanger = new Ext.ux.ThemeChanger({
        renderTo:'skin', // or use as a component in your layout
        preThemes: '<%=path %>/include/ext/resources/css/ext-all.css',
        //postThemes: ['css/app/corp.css','css/extux/Portal.css'],
        extThemes:  [
            ['默认', 'css/ext/xtheme-none.css'], // some non existent or blank file.
            ['灰色', '<%=path %>/include/ext/resources/css/xtheme-gray.css'],
            ['绿色', '<%=path %>/include/ext/resources/css/xtheme-olive.css'],
            ['紫色', '<%=path %>/include/ext/resources/css/xtheme-purple.css'],
            ['暗蓝色', '<%=path %>/include/ext/resources/css/xtheme-slate.css'],
            ['黑色', '<%=path %>/include/ext/resources/css/xtheme-black.css']
        ],
        defaultTheme: 0,
        cssId: 'myThemeId',
        width: 120
    });
    

    var task = {
       run: function(){
          doRefresh();
       },
       interval: 30000
    }
    
    var taskMsg = {
       run: function(){
          MsgService.getNewMessageCount('JGBH'
                         ,'YHBH',function(data) {
             if(data>0)
                 Ext.fly('msgcount').update("<font color='red'><b>" + data + "</b></font>");
             else
                 Ext.fly('msgcount').update(data);
          });
       },
       interval: 5000
    }
            
  	
  	new Ext.form.ComboBox({
        typeAhead: true,
        triggerAction: 'all',
        transform:'RefreshInterval',
        width:120,
        editable: false,
        mode: 'local',
        listeners:{
          blur: function(){
             task.interval=this.getValue()*1000;
          }
        }
    });
    
     var tb = new Ext.Toolbar('toolbar-div');
     
     tb.add(
        new Ext.Toolbar.SplitButton({
	    text: '系统',
	    cls: 'x-btn-text-icon blist',
	    iconCls: 'system',
	    width: 300,
	    menu : {
	    items: [
	      {id:'changepwd', text: '更改密码...', iconCls: 'changepasswd', handler: ChangePassword},
	       '-',
	      {text: '退出', iconCls: 'exit', handler: Logout}
	    ]}}),
	    '-',
	    <logic:present name="menu0list">
		<logic:iterate id="menu0" name="menu0list">
	    {
            text:'<bean:write name="menu0" property="MLMC"/>',
            iconCls: '<bean:write name="menu0" property="MLTB"/>',
            tooltip: {title:'<bean:write name="menu0" property="MLMC"/>',text:'<bean:write name="menu0" property="MLMS"/>'},
            handler: function (){
                 Ext.getCmp('leftFrame').expand();
  		         Ext.getCmp('leftFrame').setSrc('leftTree.do?mlbh=<bean:write name="menu0" property="MLBH"/>&mlmc=<bean:write name="menu0" property="MLMC"/>',true);
  	        }
        },
        '-',
	    </logic:iterate>
		</logic:present>
        '->',
	    new Ext.Toolbar.MenuButton({
	    text: '帮助',
	    cls: 'x-btn-text-icon blist',
	    iconCls: 'help',
	    menu : {items: [
	      {text: '关于...', iconCls: 'about', handler: AboutClick}
	    ]}})
	    
	  );
	  

	////////////////////////////////////////////////////////////////////////////////////////
    ////  用户消息 added by hongyanchuan
    ////////////////////////////////////////////////////////////////////////////////////////
    
    // 定义一个消息对象
    var Message = Ext.data.Record.create([
           {name: 'bh', type: 'string'}, //编号
           {name: 'bt', type: 'string'},  //标题
		   {name: 'nr', type: 'string'},  //内容
		   {name: 'zt', type: 'string'},  //状态
           {name: 'rq', type: 'string'}   //日期
    ]);
    
    //创建消息store
    
     var msgStore = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({url:'<%=path %>/userMsg.do?action=getData'}),
        
        reader: new Ext.data.XmlReader(
          {
          	totalProperty: 'msgCount',
          	record: 'msg'
          }
           , 
          Message
        )
    	
        //sortInfo:{field:'bh', direction:'ASC'}// 排序信息
     });
    
     msgStore.load({params:{start:0, limit:10}});
     
     var ztRender=function(value){
     	if(value=='2'){
     		return "<span style='color:green;font-weight:bold'>已读</span>";
     	}
     	return "<span style='color:red;font-weight:bold'>未读</span>";
     };
     
     //消息grid表头    
     var msgCM= new Ext.grid.ColumnModel([
     	{
     	   id:'bt',
           header: "标题",
           dataIndex: 'bt',
           width: 100
     	},
     	{
     	   id:'zt',
           header: "状态",
           dataIndex: 'zt',
           width: 40,
           renderer:ztRender
     	},
     	{
     	   id:'rq',
           header: "日期",
           dataIndex: 'rq',
           width: 100
     	}
     ]);
     
     //消息Grid    
     var msgGrid = new Ext.grid.GridPanel({
        //width:700,
        //height:500,
        title:'用户消息',
        store: msgStore,
        cm: msgCM,
        trackMouseOver:false,
        border:false,
        header:false,
        //sm: new Ext.grid.RowSelectionModel({selectRow:Ext.emptyFn}),
        loadMask: {msg:'正在载入消息...'},
        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true,
            getRowClass : function(record, rowIndex, p, store){
                if(this.showPreview){
                    p.body = '<p>'+record.data.nr+'</p>';
                    return 'x-grid3-row-expanded';
                }
                return 'x-grid3-row-collapsed';
            }
        },
        bbar: new Ext.PagingToolbar({
            pageSize: 25,
            store: msgStore,
            displayInfo: false
            //displayMsg: '{0} - {1} of {2}',
            //emptyMsg: "没有消息"
        }),
        tbar:[
        	{
        		text:"刷新",
        		iconCls:"refresh",
        		handler:function(){
        					msgStore.load();
        				}
        	},
        	{
        		text:"删除",
        		iconCls:"remove",
        		handler:function(){
        					 var record = msgGrid.getSelectionModel().getSelected();// 返回值为 Record 类型
                
				             if(!record){
								Ext.Msg.alert("提示","请先选择要删除的行!");
								return;
							 }else{
							 	Ext.MessageBox.confirm("确认删除","真的要删除该消息吗",
							 		function(btn){
							 			if(btn=='yes'){
											MsgService.deleteMsg(record.data.bh,function(value){
												if(value==true){
													msgStore.load();
												}
											});						 			
							 			}
							 		}
							 	);
							 }  
        				}
        	}
        ]
    });

	msgGrid.addListener(
		'rowdblclick',
		function(grid,rowIndex,event){
			
			var record = msgGrid.getSelectionModel().getSelected();
			var bh= record.data.bh;
			var zt= record.data.zt;
			
			MsgService.getMsg(bh,function(msg){     		
	     		Ext.MessageBox.show(
	     			{
		     		title:msg.VXxbt,
		     		msg:msg.VXxnr,
		     		width:400,
		     		heigth:300,
		     		buttons:Ext.MessageBox.OK,
		     		fn:function(btn){
		     				if(zt!='2'){  //如果消息状态不是已读
				     			MsgService.setMsgReaded(bh,function(){
				     				msgStore.load();
				     			});
				     		} 
		     			}
	     			}
	     		);
	     		    		
     		});
		}
	);

    
    ////////////////////////////////////////////////////////////////////////////////////////
    ////  用户消息 added by hongyanchuan 结束
    ////////////////////////////////////////////////////////////////////////////////////////


	  var viewport = new Ext.Viewport({
            layout:'border',
            items:[
            {
                region:'north',
                border:false,
                contentEl:'north-div',
                height:105,
                minSize: 100,
                maxSize: 200,
                collapsible: true,
                split:true,
                useSplitTips: true,
                collapseMode: 'mini',
                layout:'border',
                items:[
                new Ext.BoxComponent({
                    region:'center',
                    el:'north-center',
                    height:79
                }),
                new Ext.BoxComponent({
                    region:'south',
                    el:'north-south',
                    height:26,
                    tbar:tb
                })]
                
            },{
                region:'west',
                id:'west-panel',
                title:'菜单',
                iconCls: 'menu',
                split:true,
                width: 200,
                minSize: 160,
                maxSize: 400,
                collapsible: true,
                useSplitTips: true,
                useShim: true,
                layout:'accordion',
                layoutConfig:{
                    animate:true
                },
                items: [{
                    title:'导航',
                    autoScroll:true,
                    border:false,
                    iconCls:'nav',
                    xtype: 'iframepanel',
                    id:'leftFrame',
                    defaultSrc : 'login.do?status=LeftTree',                        
                    frameBorder : 0,
                    width : '100%',
                    height : '100%',
                    loadMask:{msg:'正在载入菜单...'}
                },{
                    title:'设置',
                    border:false,
                    autoScroll:true,
                    iconCls:'settings',
                    contentEl:'settings-div'
                }]
            },{
                region:'center',
                collapsible:true,
                contentEl:'center-div',
                xtype:"panel",
                title:"操作面板",
                header:false,
                iconCls: 'rightnav',
                tbar:[{
                      text:'返回',
                      iconCls: 'goback',
                      tooltip: {title:'返回上一页',text:'返回上一个页面'},
                      handler: doGoBack
                   },
                   '-',
                   {
                      text:'刷新',
                      iconCls: 'refresh',
                      tooltip: {title:'刷新',text:'刷新当前页面，请重新提交页面请求'},
                      handler: doRefresh
                   },
                   '-',
                   {
                      text:'自动刷新',
                      iconCls: 'autorefresh_s',
                      enableToggle:true,
                      tooltip: {title:'自动刷新',text:'能够自动定时刷新界面，自定义刷新时间请进设置'},
                      toggleHandler: doAutoRefresh
                   }]
            },{
                    region:'east',
                    id: 'rightpanel',
                    title: '个人消息',
                    iconCls: 'tabs',
                    //contentEl:'east-div',
                    collapsible: true,
                    //collapsed: true,
                    split:true,
                    width: 200,
                    minSize: 140,
                    maxSize: 400,
                    useSplitTips: true,
                    collapseMode: 'mini',
                    layout:'fit',
                    margins:'0 5 0 0',
                    items:msgGrid
            },
            
            
            new Ext.BoxComponent({
                region:'south',
                el:'south-div',
                height:24
            })]
        });
    
        
    function ChangePassword() {
       win.show();
    }
    
    function Logout(){   
        Ext.MessageBox.confirm('退出', 
		    	    	'你真的要退出本系统吗?', 
		    	    	function(btn) {
			    	     if(btn == 'yes') {// 选中了是按钮
			    	     	 window.location.href='<%=path %>/login.do?status=loginExit';
						 }
						}
					);
    }
  	
  	function AboutClick(item){
  		Ext.Msg.alert("关于","网站商城");
  	}
  	
  	function doRefresh() {
  	    top.mainFrame.location.reload();
  	}
  	
  	function doGoBack() {
  	    top.mainFrame.history.go(-1);
  	}
  	
  	function doAutoRefresh(item, pressed){
  	    if(pressed) { 
  	        this.setIconClass('autorefresh_b');
  	        Ext.example.msg('自动刷新','注意，自动刷新已经打开！');
            Ext.TaskMgr.start(task);
        } else {
            this.setIconClass('autorefresh_s');
            Ext.example.msg('自动刷新','注意，自动刷新已经关闭！');
            Ext.TaskMgr.stop(task);
        }
    }
    
    
    var btn = new Ext.CycleButton({
      showText: true,
      renderTo: 'btn',
      prependText: '监测',
      items: [{
            text:'打开',
            iconCls:'openmsg'
          },{
            text:'关闭',
            iconCls:'closemsg',
            checked:true
          }], 
      changeHandler:function(btn, item){
          if(item.text=="打开")
             Ext.TaskMgr.start(taskMsg);
          else
             Ext.TaskMgr.stop(taskMsg);
           
          Ext.Msg.alert("注意","消息提醒自动弹出状态已经切换到"+item.text);
      }
    });
    
    function delCookie(name){
      var date = new Date();
      date.setTime(date.getTime() - 10000);
      document.cookie = name + "=a; expires=" + date.toGMTString();
    }
    
    new Ext.Button({
        text: '删除',
        renderTo: 'btnDelCookie',
        handler: function() {
           delCookie("zycblogin");
           Ext.Msg.alert("注意","登录自动保存Cookie已经删除");
        },
        iconCls:'user-delete'
    });
    
    Ext.get('showmsgtab').on('click', function(){
         var RightPanel=Ext.getCmp('rightpanel');
         if(RightPanel.collapsed)
            RightPanel.expand();
         else
            RightPanel.collapse();
    });
    

});






-->
</SCRIPT> 


</head>
<BODY  scroll="no" >

  <div id="north-div">
   	<div id='north-center'> 	
   	    <table width="100%" height="80" cellspacing="0" cellPadding="0" border="0">
          <tr>
             <td background="<%=path %>/images/banner.jpg">
             </td>
             
          </tr>
        </table>
    </div>
  	<div id='north-south'><div id='toolbar-div'></div></div>
  </div>
  <div id="west-div"></div>
  <div id="center-div" >
  
     <IFRAME frameBorder="0" width="100%"  height="100%" id="mainFrame" name="mainFrame" scrolling="auto" src="<%=path %>/JspForm/welcome.jsp" style="HEIGHT:100%;VISIBILITY:inherit;WIDTH:100%;">
     </IFRAME>
     
  </div>
  <div id="south-div">
  
      <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
         <tr>
             <td>
                欢迎您登录&nbsp;
             </td>
             <td align="right">
                <div id="showmsgtab" style="cursor:pointer;">
                   <img src="<%=path %>/images/ext/user_comment.png" align="absmiddle">您当前有&nbsp;
                   <span id="msgcount">0</span>&nbsp;条新消息
                </div>
             </td>
          </tr>
      </table>
  </div>
  
  
  
  
  
  
  
  
  <div id="settings-div">
      <div style="margin-top:6px; margin-left:5px;">
        <table border="0" cellspacing="5" cellpadding="5">
          <tr>
            <td>
              更换系统样式：
            </td>
          </tr>
          <tr>
            <td>
               <div id="skin" style="width:120"></div>
		    </td>
		  </tr>
		  <tr>
            <td height="5">
		    </td>
		  </tr>
		  <tr>
		    <td>
		      自动刷新间隔：
		    </td>
		  </tr>
		  <tr>
		    <td>
		      <select id="RefreshInterval" name="RefreshInterval">
		       <option value="5">5秒</option>
		       <option value="10">10秒</option>
		       <option value="30" selected>30秒</option>
		       <option value="60">1分钟</option>
		       <option value="300">5分钟</option>
		       <option value="600">10分钟</option>
		      </select>
		    </td>
		  </tr>
		  <tr>
            <td height="5">
		    </td>
		  </tr>
		  <tr>
		    <td>
		      删除登录自动保存的Cookie：
		    </td>
		  </tr>
		  <tr>
            <td>
              <div id="btnDelCookie"></div>
		    </td>
		  </tr>
		  <tr>
            <td height="5">
		    </td>
		  </tr>
		  <tr>
		    <td>
		      消息提醒自动检测：
		    </td>
		  </tr>
		  <tr>
            <td>
              <div id="btn"></div>
		    </td>
		  </tr>
		</table>
    </div>
  </div>

</body>
</html>
