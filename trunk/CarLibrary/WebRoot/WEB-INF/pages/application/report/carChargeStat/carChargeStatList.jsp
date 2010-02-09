<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	综合报表 -》 车辆费用统计
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
			        	this.getEl().dom.action='/report/carChargeStat.shtml';
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
	
	<tt:grid id="carChargeStatList" value="carChargeStatList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="单据类型" property="source_order_type_name" width="100"/>
			<tt:col name="总费用(元)" width="100">
				<ww:if test="null != source_order_type">
					<ww:if test="\"1\".equals(source_order_type)">
						<a href="javascript:parent.addTab('财务单据信息-加油费用', 'viewSourceOrder_PutOnSteamManage', '/carmanage/putonsteam/putonsteam.shtml?action=report&search.carNoCode=${param['search.carNoCode']}','NO')">
							<ww:property value="charge"/>
						</a>
					</ww:if>
					<ww:if test="\"2\".equals(source_order_type)">
						<a href="javascript:parent.addTab('财务单据信息-维修费用', 'viewSourceOrder_MaintainBooking', '/carmanage/servicingmanage/servicingBooking.shtml?action=report&search.maintainState=2&search.carNoCode=${param['search.carNoCode']}','NO')">
							<ww:property value="charge"/>
						</a>
					</ww:if>
					<ww:if test="\"3\".equals(source_order_type)">
						<a href="javascript:parent.addTab('财务单据信息-保养费用', 'viewSourceOrder_MaintainManage', '/carmanage/maintainmanage/maintainManage.shtml?action=report&search.carNoCode=${param['search.carNoCode']}','NO')">
							<ww:property value="charge"/>
						</a>
					</ww:if>
					<ww:if test="\"4\".equals(source_order_type)">
						<a href="javascript:parent.addTab('财务单据信息-过路过桥费用', 'viewSourceOrder_RoadBridgeCharge', '/carmanage/roadbridgecharge/roadBridgeCharge.shtml?action=report&search.carNoCode=${param['search.carNoCode']}','NO')">
							<ww:property value="charge"/>
						</a>
					</ww:if>
				</ww:if>
			</tt:col>
			<tt:col name="单据数量" property="amount" width="100"/>
		</tt:row>
	</tt:grid>
	<div style="color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;注：点击每个类型的总费用值，查看相关类型下的单据明细
	</div>
</body>