 <%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	人员考勤管理
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
			            <ww:if test="\"1\".equals(method)">
			            	this.getEl().dom.action='/carmanage/staffcheckinmanage/staffCheckin.shtml';
			            </ww:if>
			            <ww:else>
			            	this.getEl().dom.action='/carmanage/staffcheckinmanage/staffCheckinex.shtml';
			            </ww:else>
			        	this.getEl().dom.submit();
			        },
			        items: [{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '签到时间',
			            name: 'search.begLoginTime',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endLoginTime',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '签退时间',
			            name: 'search.begLogoutTime',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endLogoutTime',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '车辆编号',
			            name: 'search.carMark',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '员工姓名',
			            name: 'search.staffName',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '员工工号',
			            name: 'search.driverID',
			            width:100  // 设置宽度，百分比的需加‘号
			        },new Ext.form.Hidden({//hidden  
               			id:'search.method',
                		name:'search.method',
                		value:<ww:property value="method"/>
                    })],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
    			});
    			
    			var window = new Ext.Window({
			        title: '车轮维修申请查询',
			        width: 300,
			        height:300,
			        minWidth: 300,
			        minHeight: 300,
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
	
	<tt:grid id="staffCheckinList" value="staffCheckinList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="GPS员工编号" property="DriverID" width="100"/>
			<tt:col name="GPS员工姓名" property="NAME" width="100"/>
			<tt:col name="车辆编号" property="car_mark" width="100"/>
			<tt:col name="签到时间" property="LOGIN_TIME" width="100"/>
			<tt:col name="签退时间" property="LOGOUT_TIME" width="100"/>
			<tt:col name="里程(公里)" property="DISTANCE" width="100"/>
			<tt:col name="下车经度" property="LOGOUT_LON" width="100"/>
			<tt:col name="下车纬度" property="LOGOUT_LAT" width="100"/>
		</tt:row>
	</tt:grid>
</body>