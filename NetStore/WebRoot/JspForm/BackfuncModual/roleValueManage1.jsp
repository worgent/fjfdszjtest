<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jslib/TreeCheckNodeUI.js"></script>
</head>
<body>
<div id="tree-ct"></div>
<script>
	Ext.BLANK_IMAGE_URL = '<%=request.getContextPath()%>/include/ext/resources/images/default/s.gif';   
	Ext.onReady(function(){   
	    var tree = new Ext.tree.TreePanel({   
			width:'100%',   
			height:400,   
			checkModel: 'cascade',   
			onlyLeafCheckable: false,   
			animate: false,   
			rootVisible: true,   
			autoScroll:true,   
			loader: new Ext.tree.TreeLoader({   
			    dataUrl:'<%=request.getContextPath()%>/JspForm/BackfuncModual/roleValue.do?status=getTree',
				baseParams: {superId:'Root'},
				clearOnLoad : false ,
				preloadChildren : false,
				requestMethod : "GET",
			    baseAttrs: { uiProvider: Ext.tree.TreeCheckNodeUI }   
			}),   
			root: new Ext.tree.AsyncTreeNode( {
				text : '系统管理',
				draggable : false,
				expanded : true
			}),
			listeners : {
				beforeload :function(node){
					if (node.attributes.id.indexOf('ynode') == -1)
						this.loader.baseParams.superId = node.attributes.id;
				}
			}
	    });   
	    /*	添加事件，可根据需要后期再补
	    tree.on("check",function(node,checked){
	    	alert(node.id+" = "+checked)
	    }); 
	    */
	    tree.getRootNode().expand(); 
	    
	    //创建表单
	    var form1 = new Ext.form.FormPanel({
			renderTo:'tree-ct',
			method:'POST', 	//提交方法
			url:'<%=request.getContextPath()%>/JspForm/BackfuncModual/roleValue.do',	
			onSubmit:Ext.emptyFn,
	        submit:function(){
	        	this.getEl().dom.action='<%=request.getContextPath()%>/JspForm/BackfuncModual/roleValue.do';
	        	this.getEl().dom.submit();
	        },
	        items: [tree,{
	        	name:'checkDate',
	        	xtype:'hidden'
	        },{
	        	name:'status',
	        	value:'addRoleValue',
	        	xtype:'hidden'
	        },{
	        	name:'rid',
	        	value:'${rid}',
	        	xtype:'hidden'
	        }],
	        buttons: [{
	            text: '保存',
	            handler: function(){
	            	document.all('checkDate').value = tree.getChecked();//tree必须事先创建好.
	            	form1.form.submit(); 
	            }
	        }]
		});  
		
		form1.getEl().center(); 
	});   
</script>  
</body>
