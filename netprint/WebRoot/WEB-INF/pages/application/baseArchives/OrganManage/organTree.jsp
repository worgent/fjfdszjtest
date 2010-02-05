<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
<!--
#tree-ct1 {
	position:absolute;
	width:915px;
	height:40px;
	z-index:1;
	left: 11px;
	top: 20px;
}
-->
</style>
<body>
<div id="tree-ct1"></div>

<input type="hidden" name="actionType" value=""/>
<input type="hidden" name="search.JGID" value=<ww:property value="search.JGID"/>>
<input type="hidden" name="search.JG_MC" value=<ww:property value="search.JG_MC"/>>
<!-- 
<center><font color="red">要对某一个机构做“修改”、“删除”，需先双击选中那一机构</font></center>
<input type="hidden" name="search.pid" value="">
 -->

<script type="text/javascript" src="/js/TreeCheckNodeUI.js"></script>
<script>  
	Ext.BLANK_IMAGE_URL = '/ext/resources/images/default/s.gif';   
	Ext.onReady(function(){   
	    var JGID=Ext.get('search.JGID').dom.value;
        var JG_MC=Ext.get('search.JG_MC').dom.value;
        var tree = new Ext.tree.TreePanel({
		    width:'100%',   
			height:300,   
			checkModel: 'cascade',   
			onlyLeafCheckable: false,   
			animate: false,   
			rootVisible: true,   
			autoScroll:true,   
			loader: new Ext.tree.TreeLoader({   
			    dataUrl:'/basearchives/organ/organManage.shtml?actionType=organTreeJson&a='+new Date(),
				baseParams: {sjjg_Id:JGID},
				clearOnLoad : false,
				preloadChildren : false,
				requestMethod : "GET"
			}),   
			root: new Ext.tree.AsyncTreeNode( {
			    id:JGID,
				text : JG_MC,
				draggable : false,
				expanded : true
			}),
			listeners : {
				beforeload :function(node){
					if ('ynode-39' != node.attributes.id){
						this.loader.baseParams.sjjg_Id = node.attributes.id;
					}
				}
			}
		});
	    tree.on('dblclick',function(node){  
		     Ext.get('search.pid').dom.value=node.attributes.id;
		}); 
	    tree.getRootNode().expand(); 
	    //创建表单
	    var form1 = new Ext.form.FormPanel({
	        title:'机构管理(要对某一个机构做“修改”、“删除”，需先双击选中那一机构)',
			renderTo:'tree-ct1',
			method:'POST', 	//提交方法
			url:'/basearchives/organ/organManage.shtml',	
			onSubmit:Ext.emptyFn,
	        submit:function(){
	            if (Ext.get('actionType').dom.value=='del'){
	        	    this.getEl().dom.action='/basearchives/organ/organManage.shtml?actionType=del';
	        	}
	        	this.getEl().dom.submit();
	        },
	        items: [tree,{
		        	name:'search.pid',
		        	value:'',
		        	xtype:'hidden'
	        		}
	        ],
	        buttons: [
	        <tt:authority value="AddOrganManage">
	        {
	            text: '修改',
	            handler: function(){
                    if(Ext.get('search.pid').dom.value==""||Ext.get('search.JGID').dom.value==""){
                        Ext.MessageBox.alert('提示', '请双击选择某一机构再做修改!');
                    }
                    else{
	            	    parent.addTab('修改机构部门','editOrgan','/basearchives/organ/organManage.shtml?actionType=edit&search.pid='+Ext.get('search.pid').dom.value,'NO');
	            	}
	            }
	        },
	        </tt:authority>
	        <tt:authority value="DelOrganManage">	        
	        {
	            text:'删除',
	            handler:function(){
	               if(Ext.get('search.pid').dom.value==""||Ext.get('search.JGID').dom.value==""){
                        Ext.MessageBox.alert('提示', '请双击选择某一机构再做删除!');
                    }
                    else{
                        /*输入参数类型
	                    Ext.Msg.prompt('提示', '请输入', function(btn, text){
						    if (btn == 'ok'){
						    }
						});
						*/
						Ext.MessageBox.confirm('提示', '是否确认删除？', function(btn) {
						    if (btn == 'yes'){
								 var url = '/basearchives/organ/organManage.shtml?actionType=del&search.pid='+Ext.get('search.pid').dom.value;
		                         try{
						             var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
						             oXMLDom.async = false ;
						             oXMLDom.load(url); 
						             var root;
						             if (oXMLDom.parseError.errorCode != 0) {
							             return;
						             } else {
							         root = oXMLDom.documentElement;
						             }
						             if (null != root){
							             var rowSet = root.selectNodes("//delete");
							             var val=rowSet.item(0).selectSingleNode("value").text
							             if (1 == val){
								             Ext.MessageBox.alert('提示', '删除机构部门信息，操作成功！', function(btn) {
								             	  parent.document.ifrm_OrganManage.window.location.reload();
								             });
							             }else if(7==val){
							                 Ext.MessageBox.alert('提示', '本机构有下级机构,不能删除！');
							             }
							             else if(8==val){
							                 Ext.MessageBox.alert('提示', '人员档案已经使用此机构,不能删除!');
							             }
							             else if(9==val){
							                 Ext.MessageBox.alert('提示', '有相关资产信息存在,不能删除');
							             }else if(6==val){
							                 Ext.MessageBox.alert('提示', '本机构有关联操作员,不能删除！');
							             }else{
							             	 Ext.MessageBox.alert('提示', '删除出错,请重试!');
							             }
						             }
					             }catch(e){ 
						             Ext.MessageBox.alert(e);
					             }						       
						    }
						});
						/*第三种可以有种按钮的
						Ext.Msg.show({
						   title:'删除确认?',
						   msg: '你是否确认删除该数据?',
						   buttons: Ext.Msg.YESNO,
						   fn: deldata(),
						   animEl: 'elId',
						   icon: Ext.MessageBox.QUESTION
						});
						*/
	            	}//end else
	            }
	        },
	        </tt:authority>
	        {
	            text: '添加',
	            handler: function(){
		          parent.addTab('添加机构','editOrgan','/basearchives/organ/organManage.shtml?actionType=new','NO');
		        }
	        }
	        
	        ]
	        
	       //html:'123'
		});
		//form1.getEl().center(); 
	}); 
</script>  
</body>
