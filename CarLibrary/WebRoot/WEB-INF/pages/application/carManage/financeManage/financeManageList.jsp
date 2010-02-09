<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	财务管理
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
    		sourceOrderTypeData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para a where para_type = 'FINANCE_PARA' and para_name = 'SOURCE_ORDER_TYPE'\""/>
				<ww:iterator value="#select.selectList" status="sourceOrderTypeList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#sourceOrderTypeList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var sourceOrderType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: sourceOrderTypeData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.sourceOrderType',
				fieldLabel: '源单类型',
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
			        	this.getEl().dom.action='/carmanage/finance/financeManage.shtml';
			        	this.getEl().dom.submit();
			        },
			        items: [{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '单据日期',
			            name: 'search.begBookingDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endBookingDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },sourceOrderType,{
			        	xtype: 'textfield',
			            fieldLabel: '原单据号',
			            name: 'search.sourceOrderCode',
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
	
	<tt:grid id="financeList" value="financeList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="单据号" width="120">
				<ww:if test="null != finance_id">
					<a href="javascript:parent.addTab('财务单据信息', 'viewFinance', '/carmanage/finance/financeManage.shtml?actionType=view&search.financeId=<ww:property value="finance_id"/>','NO')">
						<ww:property value="finance_id"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="单据日期" property="booking_date" width="80"/>
			<tt:col name="源单号" width="120">
				<ww:if test="\"1\".equals(source_order_type)">
					<a href="javascript:parent.addTab('财务单据信息-加油费用', 'viewSourceOrder_PutOnSteamManage', '/carmanage/putonsteam/putonsteam.shtml?actionType=view&search.putonsteamId=<ww:property value="source_order_code"/>','NO')">
						<ww:property value="source_order_code"/>
					</a>
				</ww:if>
				<ww:if test="\"2\".equals(source_order_type)">
					<a href="javascript:parent.addTab('财务单据信息-维修费用', 'viewSourceOrder_MaintainBooking', '/carmanage/servicingmanage/servicingBooking.shtml?actionType=view&search.maintainId=<ww:property value="source_order_code"/>','NO')">
						<ww:property value="source_order_code"/>
					</a>
				</ww:if>
				<ww:if test="\"3\".equals(source_order_type)">
					<a href="javascript:parent.addTab('财务单据信息-保养费用', 'viewSourceOrder_MaintainManage', '/carmanage/maintainmanage/maintainManage.shtml?actionType=view&search.nurseId=<ww:property value="source_order_code"/>','NO')">
						<ww:property value="source_order_code"/>
					</a>
				</ww:if>
				<ww:if test="\"4\".equals(source_order_type)">
					<a href="javascript:parent.addTab('财务单据信息-过路过桥费用', 'viewSourceOrder_RoadBridgeCharge', '/carmanage/roadbridgecharge/roadBridgeCharge.shtml?actionType=view&search.nurseId=<ww:property value="source_order_code"/>','NO')">
						<ww:property value="source_order_code"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="源单类型" property="source_order_type_name" width="100"/>
			<tt:col name="车辆编号" property="car_no_code" width="100"/>
			<tt:col name="金额" property="charge" width="100"/>
			<tt:col name="地市" property="city_name" width="100"/>
		</tt:row>
	</tt:grid>
</body>