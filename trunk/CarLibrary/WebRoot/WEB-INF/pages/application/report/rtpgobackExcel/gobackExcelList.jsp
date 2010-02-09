<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%--
	 * 回车登记
 	 * @author FANGZL 
--%>
<%@ include file="/common/taglibs.jsp"%>
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
			
			//部门
			var belongCompanyTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/basearchives/deptInfo/ajaxDeptInfo.shtml',
		            hiddenName : 'search.deptId',
		            valueField : 'id',
		            fieldLabel: '用车部门',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            value:''
			});
				
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			    	labelAlign: 'right', 
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/carmanage/expedite/goback.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/expedite/goback.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [belongCompanyTree,{
			            fieldLabel: '车辆编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '派车人',
			            name: 'search.expediteMan',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '用车人',
			            name: 'search.useCarMan',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '驾驶员',
			            name: 'search.driver',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: ' 出车日期',
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
			            fieldLabel: ' 回车日期',
			            name: 'search.begGobackDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endGobackDate',
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
			        title: '派车查询',
			        width: 300,
			        height:350,
			        minWidth: 300,
			        minHeight: 200,
			        layout: 'fit',
			        plain:true,
			        bodyStyle:'padding:5px;',
			        buttonAlign:'center',
			        items: schForm
			    });
			
			    window.show();
			});
		}
		<c:if test="${param.action != 'report'}">
			Ext.onReady(function(){
				new Ext.Button({
			        text: '查 询',
			        handler: showSearch
			    }).render(document.all.searchPanel);
			    
			})
		</c:if>
	
	
	</script>
</head>
<body>
    <div id="searchPanel" style="margin:0px;width:100px;float:left;"></div>
	<div id="addPanel" style="margin:0px;width:100px;"></div>
	<tt:grid id="expedite" value="expediteList" pagination="true" xls="true">
		<tt:row >
		   <tt:col name="出车单据号" width="135">
				<ww:if test="null != expedite_car_id">
					<a href="javascript:parent.addTab('回车信息', 'viewGoBack', '/carmanage/expedite/goback.shtml?actionType=view&search.expediteCarId=<ww:property value="expedite_car_id"/>','NO')">
						<ww:property value="expedite_car_id"/>
					</a>
				</ww:if>
			</tt:col>					 		
			<tt:col name="用车人" property="use_car_man_name" visible="true" width="120"/>	
			<tt:col name="用车部门" property="dept_name" visible="true" width="80"/>
			<tt:col name="司机" property="driver_name" width="80"/>
			<tt:col name="车牌号" property="car_no_code" width="80"/>
			<tt:col name="起点" property="first_locus" width="80"/>		
			<tt:col name="出车时间" property="first_date" width="80"/>
			<tt:col name="起公里数" property="first_mileage" width="80"/>
			<tt:col name="目的地时间" property="transfer_date" width="80"/>
			<tt:col name="止里程" property="transfer_mileage" width="80"/>	
			<tt:col name="实际行驶公里数" property="run_mileage" width="80"/>
			<tt:col name="实际用车时间" property="run_time" width="80"/>
			<tt:col name="驾驶员服务态度" property="feedback" width="80"/>				
		</tt:row>
	</tt:grid> 
</body>