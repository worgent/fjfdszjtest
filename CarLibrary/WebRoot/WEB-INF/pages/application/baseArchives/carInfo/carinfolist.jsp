<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!--
	车辆信息管理 
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
		 terminalTypeData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'CAR_INFO' and para_name = 'TERMINAL_TYPE'\""/>
				<ww:iterator value="#select.selectList" status="terminalTypeList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#terminalTypeList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var terminalType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: terminalTypeData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.terminalType',
				fieldLabel: '终端类型',
				name: 'test',
				width:300
			})	
			colorData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para  t where t. para_type = 'CAR_INFO' and t.para_name = 'COLCOR'\""/>
				<ww:iterator value="#select.selectList" status="colorList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#colorList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var color = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: colorData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.color',
				fieldLabel: '车辆颜色',
				name: 'test',
				width:100
			})
		 belongCompanyData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'MANAGE_ORGAN_ARCHIVES' and para_name = 'UP_COMPANY'\""/>
				<ww:iterator value="#select.selectList" status="belongCompanyList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#belongCompanyList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var belongCompany = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: belongCompanyData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.belongCompany',
				fieldLabel: '所属公司',
				name: 'test',
				width:100
			})
			 carStateData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'CAR_INFO' and para_name = 'CAR_STATE'\""/>
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
			        url:'/basearchives/car/carInfo.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/basearchives/car/carInfo.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '车牌编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },terminalType,color,belongCompany,carState,{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '安装时间',
			            name: 'search.installDate',
			            width:100  
			        },{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '购买日期',
			            name: 'search.buyDate',
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
			        title: '车辆信息查询',
			        width: 300,
			        height:330,
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
		    <tt:authority value="TJCLXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加车辆信息','addCarInfo','/basearchives/car/carInfo.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(carNoId){
			if (!confirm('您确定删除该信息!')){
				return;
			}else{
				var url = '/basearchives/car/carInfo.shtml?actionType=delete&search.carNoId='+carNoId;
				try{
					var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
					oXMLDom.async = false ;
					oXMLDom.load(url); 
					var root;
					if (oXMLDom.parseError.errorCode != 0) {
						var myErr = oXMLDom.parseError;
						return;
					} else {
						root = oXMLDom.documentElement;
					}
					if (null != root){
						var rowSet = root.selectNodes("//delete");
						if (0 < rowSet.item(0).selectSingleNode("value").text){
							alert("删除车辆信息，操作成功！");
							parent.document.ifrm_CarInfo.window.location.reload();
						}else{
							alert("删除车辆信息，操作失败！");
						}
					}
				}catch(e){ 
					alert(e);
				}
			}
		}
	</script>
</head>
<body>
    <div id="searchPanel" style="margin:0px;width:100px;float:left;"></div>
	<div id="addPanel" style="margin:0px;width:100px;"></div>
	<tt:grid id="carInfo" value="carInfoList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="车牌编号" width="100">
				<ww:if test="null != car_no_id">
					<a href="javascript:parent.addTab('车辆信息', 'viewCarInfo', '/basearchives/car/carInfo.shtml?actionType=view&search.carNoId=<ww:property value="car_no_id"/>','NO')">
						<ww:property value="car_no_code"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="终端类型" property="terminalType" width="100"/>
			<tt:col name="车辆颜色" property="color2" width="80"/>
			<tt:col name="行驶里程初始(米)" property="run_mileage_init" width="80" visible="true"/>			
			<tt:col name="所属公司" property="belongCompany" width="100"/>
			<tt:col name="车辆状态" property="carState" width="80"/>
			<tt:col name="车载电话" property="car_phone" width="80"/>
			<tt:col name="安装时间" property="install_date" width="80" visible="true"/>
			<tt:col name="购买日期" property="buy_date" width="80" visible="true"/>	
			<tt:col name="所属地市" property="cityId" width="80"/>			
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != car_no_id">
					<tt:authority value="XGCLXX">
						<a href="javascript:parent.addTab('修改车辆信息', 'editCarInfo', '/basearchives/car/carInfo.shtml?actionType=edit&search.carNoId=<ww:property value="car_no_id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCCLXX">
						<a href="javascript:fun_delete(<ww:property value="car_no_id"/>)">删除</a>
					</tt:authority>
						<a href="javascript:parent.addTab('复制车辆信息', 'reCarInfo', '/basearchives/car/carInfo.shtml?actionType=renew&search.carNoId=<ww:property value="car_no_id"/>','NO')">复制</a>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>