Ext.ux.TabCloseMenu = function(){
    var tabs, menu, ctxItem;
    this.init = function(tp){
        tabs = tp;
        tabs.on('contextmenu', onContextMenu);
    }

    function onContextMenu(ts, item, e){
        if(!menu){ // create context menu on first right click
            menu = new Ext.menu.Menu([{
                id: tabs.id + '-close',
                text: '关闭所有',
                handler : function(){
                    tabs.items.each(function(item){
                    	     if(item.id=="myTaskList") return;
                             tabs.remove(item);
                    });
                    addWindow({id:"blank",title:"空白页"});
                }
            },{
                id: tabs.id + '-close-others',
                text: '关闭其他所有',
                handler : function(){
                    tabs.items.each(function(item){
                        if(item.closable && item != ctxItem){
                            tabs.remove(item);
                        }
                    });
                }
            },"-",{
                id: tabs.id + '-refresh',
                text: '刷新当前页',
                handler : function(){ 
                	var o=Ext.apply({},getCmp(ctxItem.id));
                    tabs.remove(ctxItem);
                    addWindow({url:o.urn,text:o.title,id:o.id});
                    
                	
                	
                }
            }]);
        }
        ctxItem = item;
        var items = menu.items;
        items.get(tabs.id + '-close').setDisabled(!item.closable);
        var disableOthers = true;
        tabs.items.each(function(){
            if(this != item && this.closable){
                disableOthers = false;
                return false;
            }
        });
        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
        menu.showAt(e.getPoint());
    }
};
  /**
   * 定义左边对象(mainSiderBar_xudahu)
   */
  var mainSiderBar_xudahu=new Ext.tree.TreePanel({
     region:"west",
     title:"导航",
     split:true,
     collapsible:true,
     width:150,
     useArrows:true,
     autoScroll:true,
     animate:true,
     enableDD:true,
     containerScroll: true,
     rootVisible : false,
     dataUrl:toURL("/staff/menus.do"),
     //dataUrl:toURL("/app/js/menu.js"),
     root: {
         nodeType: 'async',
         text: 'Ext JS',
         draggable:false,
         expandable:true,
         expanded:true,
         id:'source'
     }
  });
  //展开所有节点
  mainSiderBar_xudahu.expandAll();
  
  /**
   * 定义中间容器(mainContent_xudahu)
   */
  
  var mainContent_xudahu=new Ext.TabPanel({
  	 region:"center",
  	 bodyBorder:false,
  	 split:true,
     id:"mainContent_xudahu",
     defaults:{autoScroll: true},
     resizeTabs:true,
     activeTab:0,
     layoutOnTabChange:true,
     onStripMouseDown : function(e){
         if(e.button != 0){
             return;
         }
         e.preventDefault();
         var t = this.findTargets(e);
         if(t.close){
             this.remove(t.item);
             if(this.items.length==0) addWindow({id:"blank",title:"空白页"});
             return;
         }
         if(t.item && t.item != this.activeTab){
             this.setActiveTab(t.item);
         }
     },

     items:[{title:"空白页",id:"blank_tab"}],
     plugins:new Ext.ux.TabCloseMenu()
  });
  
  

  
  //定义新窗口添加函数
  function addWindow(obj){
      var tabId=((obj.id)?obj.id:"blank")+"_tab";
      var aTab;
      if(obj.url){
          aTab=new Ext.Panel({
    	       id:tabId,
             title:"&nbsp;&nbsp;"+obj.text+"&nbsp;&nbsp;",
             autoLoad:{url:toURL(obj.url+"?id="+tabId),autoScroll:true,scripts:true},
             layout:"fit",
             region:"center",
             closable:true,
             bodyBorder:false,
             autoScroll:true,
             style:"padding:4px",
             urn:obj.url
          });
        
     }else{
        aTab={id:tabId,title:"&nbsp;&nbsp;空白页&nbsp;&nbsp;"};
     }   
     if(tabId!=="blank_tab") mainContent_xudahu.remove("blank_tab");
     
      
     if(!mainContent_xudahu.getItem(tabId)){
           mainContent_xudahu.add(aTab);
      }   
      mainContent_xudahu.activate(tabId);
  }
  
  
  
  //定义目录树点击事件
  mainSiderBar_xudahu.on("click",function(node,obj){
      var obj=node.attributes;
      var tabId=((obj.id)?obj.id:"blank")+"_tab";
      if(mainContent_xudahu.getItem(tabId)){
          mainContent_xudahu.activate(tabId);
      }else {
          if(node.attributes.leaf==true && obj.url){
            if(mainContent_xudahu.items.length>6){
               Ext.MessageBox.alert('系统提醒', '最多只能打开7个窗口，请关闭一些窗口。');
               return;
            }
            addWindow(obj);
          }  
      }
  });
  /**
   * 创建视图
   */
  function documentReady(){
    Ext.QuickTips.init();
    //创建一个新的视图

    var mainViewer=new Ext.Viewport({
  	    layout:"border",
  	    hideBorders:true,
  	   
  	    items:[
  	        {region:"north",hideBorders:true,height:32,autoLoad:{url:"app/banner.jsp",scripts:true}},
  	        {region:"center",layout:"border",items:[mainSiderBar_xudahu,mainContent_xudahu]},
  	    		{region:"south",hideBorders:true,height:25,autoLoad:{url:"app/footer.jsp",scripts:true}}
  	    ]
  	});

  };