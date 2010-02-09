<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	综合报表 -》 车辆状态查询
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
    		Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        labelAlign: 'right', 
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/report/carStateQuery.shtml';
			        	this.getEl().dom.submit();
			        },
			        items: [{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '查询时间',
			            name: 'search.begDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endDate',
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
			        title: '车辆状态查询',
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
	
	<tt:grid id="carStateQueryList" value="carStateQueryList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="车辆编号" property="car_no_code" width="100"/>
			<tt:col name="所属地市" property="city_name" width="100"/>
			<tt:col name="派车次数" width="100">
				<ww:if test="null != car_no_id">
					 <a href="javascript:parent.addTab('派车次数', 'expedite_num', '/carmanage/expedite/expedite.shtml?action=report&search.carNoId=<ww:property value="car_no_id"/>&search.begExpediteDate=${param['search.begDate']}&search.endExpediteDate=${param['search.endDate']}')"><ww:property value="expedite_num"/></a>
				</ww:if>
			</tt:col>
			<tt:col name="回车次数" width="100">
				<ww:if test="null != car_no_id">
					 <a href="javascript:parent.addTab('回车次数', 'goback_num', '/carmanage/expedite/goback.shtml?action=report&search.carNoId=<ww:property value="car_no_id"/>&search.begGobackDate=${param['search.begDate']}&search.endGobackDate=${param['search.endDate']}')"><ww:property value="goback_num"/></a>
				</ww:if>
			</tt:col>
			<tt:col name="维修次数" width="100">
				<ww:if test="null != car_no_id">
					 <a href="javascript:parent.addTab('维修次数', 'maintain_num', '/carmanage/servicingmanage/servicingBooking.shtml?action=report&search.carNoId=<ww:property value="car_no_id"/>&search.begMaintainEndDate=${param['search.begDate']}&search.endMaintainDate=${param['search.endDate']}')"><ww:property value="maintain_num"/></a>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid>
	
	<div style="color:red">
		注：点击值查看明细
	</div>
</body>