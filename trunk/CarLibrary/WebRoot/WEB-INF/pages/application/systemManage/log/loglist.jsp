<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
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
    <script language="javascript">
		function showSearch(){		   
			cityIdData = [
				<tt:setProperty name="#select.dynamicSql" value="'select city_id,city_name from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'"/>
				<ww:iterator value="#select.selectList" status="cityIdList">["<ww:property value="city_id"/>", "<ww:property value="city_name"/>"]<ww:if test="#cityIdList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var cityId = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: cityIdData
				}),
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'operateLog.cityId',
				fieldLabel: '所属地市',
				name: 'test',
				width:100
			})							
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/system/log/log.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/system/log/log.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '人员',
			            name: 'operateLog.staffId',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '业务操作名称',
			            name: 'operateLog.serviceName',
			            width:100  
			        },{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '操作时间',
			            name: 'operateLog.optTime',
			            width:100  
			        },cityId],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '日志查询',
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
	<tt:grid id="log" value="logList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="人员ID" property="STAFF_ID" width="100"/>
			<tt:col name="业务操作名称" property="SERVICE_NAME" width="100"/>
			<tt:col name="操作时间" property="OPT_TIME" width="120"/>			
			<tt:col name="操作结果" width="100">
				<ww:if test="OPT_RESULT.equals(\"1\")">操作成功</ww:if>
				<ww:elseif test="OPT_RESULT.equals(\"0\")">操作失败</ww:elseif>
				<ww:else>操作成功</ww:else>
			</tt:col>
			<tt:col name="登录终端号" property="OPT_TERM" width="100"/>
			<tt:col name="所属地市" property="cityId" width="100"></tt:col>		
		</tt:row>
	</tt:grid>
</body>