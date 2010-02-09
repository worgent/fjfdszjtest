<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	综合报表 -》 位置管理
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
    		carStateData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para a where para_type = 'CAR_INFO' and para_name = 'CAR_STATE'\""/>
				<ww:iterator value="#select.selectList" status="carStateList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#carStateList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var carState = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: carStateData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.carState',
				fieldLabel: '车辆状态',
				name: 'test',
				width:100
			})	
			
    		Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        labelAlign: 'right', 
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/report/localityManage.shtml';
			        	this.getEl().dom.submit();
			        },
			        items: [carState,{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '最后定位时间',
			            name: 'search.begLastTime',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endLastTime',
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
			        title: '财务单据查询',
			        width: 300,
			        height:230,
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
	
	<tt:grid id="localityList" value="localityList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="车辆编号" property="car_no_code" width="100"/>
			<tt:col name="公司" property="belong_to_company_name" width="100"/>
			<tt:col name="最后位置" property="LAST_LOCATION" width="100"/>
			<tt:col name="最后速度" property="LAST_SPEED" width="100"/>
			<tt:col name="最后定位时间" property="LAST_TIME" width="100"/>
			<tt:col name="最后定位状态" property="LAST_FLAG" width="100"/>
			<tt:col name="方向" property="LAST_ANGLE" width="100"/>
			<tt:col name="最后定位经度" property="LAST_LON" width="100"/>
			<tt:col name="最后定位纬度" property="LAST_LAT" width="100"/>
			<tt:col name="车辆状态" property="car_state_name" width="100"/>
		</tt:row>
	</tt:grid>
</body>