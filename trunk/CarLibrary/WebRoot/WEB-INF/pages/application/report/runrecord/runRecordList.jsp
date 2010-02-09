<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	综合报表 -》 行车记录
 -->
<ww:action name="'select'" id="select"></ww:action>

<head>
	<style type="text/css">
	    .x-panel-body p {
	        margin:10px;
	    }
	    #container {
	        padding:10px;
	    }
    </style>
    <script type="text/javascript" src="/js/TreeField.js"></script>
    <script language="javascript">
    	function showSearch(){
    		Ext.QuickTips.init();
		
			//部门
			var belongCompanyTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/basearchives/deptInfo/ajaxDeptInfo.shtml',
		            hiddenName : 'search.deptId',
		            valueField : 'id',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            fieldLabel: '用车部门',
		            value:''
			});

			
    		Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        labelAlign: 'right', 
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/report/runRecord.shtml';
			        	this.getEl().dom.submit();
			        },
			        items: [belongCompanyTree,{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '出车时间',
			            name: 'search.begExpediteDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endExpediteDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '回车时间',
			            name: 'search.begGobackDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endGobackDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '车辆编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        }],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
    			});
    			
    			var window = new Ext.Window({
			        title: '行车记录查询',
			        width: 300,
			        height:250,
			        minWidth: 300,
			        minHeight: 250,
			        layout: 'fit',
			        plain:true,
			        bodyStyle:'padding:5px;',
			        buttonAlign:'center',
			        items: schForm
			    });
			
			    window.show();
			});
    	}
    	Ext.onReady(function(){
			new Ext.Button({
		        text: '查 询',
		        handler: showSearch
		    }).render(document.all.searchPanel);
		})
    </script>
</head>

<body>
	<div id="searchPanel" style="margin:0px;width:100px;float:left;"></div>
	<div id="addPanel" style="margin:0px;width:100px;"></div>
	
	<tt:grid id="runRecordList" value="runRecordList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="车辆编号" property="car_no_code" width="100"/>
			<tt:col name="出车时间" property="expedite_date" width="100"/>
			<tt:col name="回车时间" property="goback_date" width="100"/>
			<tt:col name="里程数(米)" property="end_mileage" width="100"/>
			<tt:col name="用车时长" property="usr_time_len" width="100"/>
			<tt:col name="用车部门" property="dept_name" width="100"/>
		</tt:row>
	</tt:grid>
</body>