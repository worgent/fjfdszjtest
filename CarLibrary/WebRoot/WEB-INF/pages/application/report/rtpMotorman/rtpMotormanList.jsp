<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!--
	保养管理 
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
			        	this.getEl().dom.action='/report/rtpMotorman.shtml';
			        	this.getEl().dom.submit();
			        },
			        items: [{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '时间',
			            name: 'search.begTime',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endTime',
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
			        }],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
    			});
    			
    			var window = new Ext.Window({
			        title: '人员考勤',
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
	<tt:grid id="rtpMotorman" value="rtpMotormanList" pagination="true" xls="true">
		<tt:row >
			<tt:col name="人员编号" width="80">
				<ww:if test="null != driverId">
					<a href="javascript:parent.addTab('考勤信息', 'viewStaffCheckinManage', '/carmanage/staffcheckinmanage/staffCheckin.shtml?search.id=<ww:property value="Id"/>','NO')">
						<ww:property value="driverId"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="人员姓名" property="Name" width="80" />	
			<tt:col name="时间段" property="groupdata" width="150"/>		
			<tt:col name="签到次数" width="80">
			<ww:if test="null != driverId">
				<a href="javascript:parent.addTab('考勤信息', 'viewStaffCheckinManage', '/carmanage/staffcheckinmanage/staffCheckin.shtml?search.id=<ww:property value="Id"/>&search.begLoginTime=<ww:property value="begdata"/>&search.endLoginTime=<ww:property value="enddata"/>','NO')">
						<ww:property value="SLogin"/>	
				</a>
			</ww:if>
			</tt:col>	
			<tt:col name="签退次数" width="80">
			<ww:if test="null != driverId">
				<a href="javascript:parent.addTab('考勤信息', 'viewStaffCheckinManage', '/carmanage/staffcheckinmanage/staffCheckin.shtml?search.id=<ww:property value="Id"/>&search.begLogoutTime=<ww:property value="begdata"/>&search.endLogoutTime=<ww:property value="enddata"/>','NO')">
						<ww:property value="SLogOut"/>	
				</a>
			</ww:if>
			</tt:col>	
		</tt:row>
	</tt:grid> 
</body>