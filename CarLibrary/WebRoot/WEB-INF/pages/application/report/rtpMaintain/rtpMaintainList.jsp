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
		 carNoData = [
				<tt:setProperty name="#select.dynamicSql" value="'select distinct * from tf_car_info where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'"/>
				<ww:iterator value="#select.selectList" status="carNoList">["<ww:property value="car_no_id"/>", "<ww:property value="car_no_code"/>"]<ww:if test="#carNoList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var carNoCode = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: carNoData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.carNoCode',
				fieldLabel: '车牌号',
				name: 'test',
				width:100
			})		
			 nurseTypeData = [
				<tt:setProperty name="#select.dynamicSql" value="'select PARA_VALUE,PARA_VALUE_DESC  from td_system_para where para_type =\"FIXING_PARA\" and para_name = \"MAINTAIN_PARAM\" '"/>
				<ww:iterator value="#select.selectList" status="nurseTypeList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#nurseTypeList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var nurseType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: nurseTypeData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.parmare',
				fieldLabel: '参数类型',
				name: 'test',
				width:100
			})				  		  
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
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.cityId',
				fieldLabel: '所属地市',
				name: 'test',
				width:100
			})							
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/report/rtpMaintain.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/report/rtpMaintain.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [nurseType,carNoCode,cityId],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '保养异常查询',
			        width: 300,
			        height:300,
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
	<tt:grid id="rtpMaintain" value="rtpMaintainList" pagination="true" xls="true">
		<tt:row >
			<tt:col name="车辆编号" width="80">
				<ww:if test="null != nurse_id">
					<a href="javascript:parent.addTab('保养信息', 'viewMaintainManage', '/carmanage/maintainmanage/maintainManage.shtml?actionType=view&search.nurseId=<ww:property value="nurse_id"/>','NO')">
						<ww:property value="car_no_code"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="车辆编号" property="car_no_id" width="80" visible="true"/>	
			<tt:col name="配件编号" property="fixing_id" width="80" visible="true"/>		
			<tt:col name="配件名称" property="fixing_name" width="80"/>			
			<tt:col name="所属地市" property="cityName" width="80"/>	
			<tt:col name="参数类型" property="parmare" width="80"/>	
			<tt:col name="保养日期(里程)" property="aa" width="100"/>		
			<tt:col name="参数值" property="bb" width="100"/>	
		</tt:row>
	</tt:grid> 
</body>