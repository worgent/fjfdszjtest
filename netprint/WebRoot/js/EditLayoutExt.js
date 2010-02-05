
LayoutExt = function(){   
  
    var Viewport = Ext.Viewport;   

    var root;   
    var layout;   

    return {   
        init: function(){   
           root = this; 
            Ext.state.Manager.setProvider(new Ext.state.CookieProvider());   
            layout = new Viewport({   
                layout: 'border',   
                items: [        
                {   
                    region: 'north', 
                    contentEl: 'north1',    
                    title: '显示隐藏栏', 
                    split: false,
                    collapsible: true,  
                    height: 300,
                    html:"<iframe id='warningLogIframe' name='warningLogIframe' src='/ippfManage/baseInfo/BaseInfo.shtml?actionType=edit' width='100%' height='100%' frameborder=0/>" ,
                    margins: '0 0 0 0'    
                },
                 new Ext.TabPanel({   
                                    region: 'center',   
                                    deferredRender: false,
                                     split: false,  
                                    activeTab: 0, 
                                    items: [{   
                                        contentEl: 'hyxx1',   
                                        title: '婚姻信息',   
                                        closable: false,
                                        html:"<iframe id='hyxxModifyId' name='hyxxNameModify' src='/ippfManage/baseInfo/BaseInfo.shtml?actionType=hyxxGridListModify' width='100%' height='100%' frameborder=0/>" , 
                                        autoScroll: true 
                                    }, {   
                                        contentEl: 'znxx1',   
                                        title: '子女信息',
                                        html:"<iframe id='znxxModifyId' name='znxxNameModify' src='/ippfManage/baseInfo/BaseInfo.shtml?actionType=znxxGridListModify' width='100%' height='100%' frameborder=0/>" ,  
                                        autoScroll: true  
                                    }, {   
                                        contentEl: 'jyxx1',   
                                        title: '节育信息',   
                                         html:"<iframe id='jyxxModifyId' name='jyxxNameModify' src='/ippfManage/baseInfo/BaseInfo.shtml?actionType=jyxxGridListModify' width='100%' height='100%' frameborder=0/>" ,  
                                        
                                        autoScroll: true  
                                    }, {   
                                        contentEl: 'zjxx1',   
                                        title: '证件信息', 
                                        html:"<iframe id='zjxxModifyId' name='zjxxNameModify' src='/ippfManage/baseInfo/BaseInfo.shtml?actionType=zjxxGridListModify' width='100%' height='100%' frameborder=0/>" ,  
                                          
                                        autoScroll: true  
                                    }, {   
                                        contentEl: 'scxx1',   
                                        title: '三查信息', 
                                        html:"<iframe id='scxxId' name='scxxName' src='/ippfManage/baseInfo/BaseInfo.shtml?actionType=scxxGridList' width='100%' height='100%' frameborder=0/>" ,   
                                        autoScroll: true  
                                    }, {   
                                        contentEl: 'jlxx1',   
                                        title: '奖励信息', 
                                        html:"<iframe id='jlxxModifyId' name='jlxxNameModify' src='/ippfManage/baseInfo/BaseInfo.shtml?actionType=jlxxGridListModify' width='100%' height='100%' frameborder=0/>" ,  
                                          
                                        autoScroll: true  
                                    }, {   
                                        contentEl: 'clxx1',   
                                        title: '处理信息',  
                                        html:"<iframe id='clxxId' name='scxxName' src='/ippfManage/baseInfo/BaseInfo.shtml?actionType=clxxGridList' width='100%' height='100%' frameborder=0/>" ,   
                                        autoScroll: true  
                                    }]   
                 })   
                ]   
            });   
        }   
    };   
}   
();   

Ext.onReady(LayoutExt.init, LayoutExt, true);  
