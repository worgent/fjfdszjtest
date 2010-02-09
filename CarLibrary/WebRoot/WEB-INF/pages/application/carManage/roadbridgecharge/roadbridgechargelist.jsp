<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	过路过桥费用管理
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
		 	chargeTypeData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para tt  where tt.para_type='ROAD_BRIDGE_CHARGE' and  tt.para_name = 'CHARGE_TYPE'\""/>
				<ww:iterator value="#select.selectList" status="chargeTypeList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#chargeTypeList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var chargeType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: chargeTypeData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.chargeType',
				fieldLabel: '费用类型',
				name: 'test',
				width:100
			})				  
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/roadbridgecharge/roadBridgeCharge.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [chargeType,{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: ' 收费时间',
			            name: 'search.begChargeDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endChargeDate',
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
			        title: '加油查询',
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
		
		<c:if test="${param.action != 'report'}">
			Ext.onReady(function(){
				new Ext.Button({
			        text: '查 询',
			        handler: showSearch
			    }).render(document.all.searchPanel);
			    <tt:authority value="TJGLGQFY">
			    new Ext.Button({
			        text: '添 加',
			        handler: function(){
			        	parent.addTab('添加过路过桥费用信息','addRoadBridgeCharge','/carmanage/roadbridgecharge/roadBridgeCharge.shtml?actionType=new','NO');
			        }
			    }).render(document.all.addPanel);
			    </tt:authority>
			})
		</c:if>
		
		function fun_delete(roadBridgeChargeId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   var url = '/carmanage/roadbridgecharge/roadBridgeCharge.shtml?actionType=delete&search.roadBridgeChargeId='+roadBridgeChargeId;
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
							alert("删除过路过桥费用信息，操作成功！");
							parent.document.ifrm_RoadBridgeCharge.window.location.reload();
						}else{
							alert("删除过路过桥费用信息，操作失败！");
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
	
	<tt:grid id="roadBridgeChargeList" value="roadBridgeChargeList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="单据号" width="120">
				<ww:if test="null != road_bridge_charge_id">
					<a href="javascript:parent.addTab('过路过桥费用信息', 'viewRoadBridgeCharge', '/carmanage/roadbridgecharge/roadBridgeCharge.shtml?actionType=view&search.roadBridgeChargeId=<ww:property value="road_bridge_charge_id"/>','NO')">
						<ww:property value="road_bridge_charge_id"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="车辆编号" property="car_no_code" width="100"/>
			<tt:col name="收费时间" property="charge_date" width="100"/>
			<tt:col name="收费类型" property="charge_type_desc" width="100"/>
			<tt:col name="费用" property="charge" width="100"/>
			<tt:col name="归属地" property="city_name" width="100"/>
			<c:if test="${param.action != 'report'}">
				<tt:col name="操作" width="100">
					<ww:if test="null != road_bridge_charge_id">
						<tt:authority value="XGGLGQFY">
							<a href="javascript:parent.addTab('修改过路过桥费用', 'editRoadBridgeCharge', '/carmanage/roadbridgecharge/roadBridgeCharge.shtml?actionType=edit&search.roadBridgeChargeId=<ww:property value="road_bridge_charge_id"/>','NO')">修改</a>
						</tt:authority>
						<tt:authority value="XCGLGQFY">
							<a href="javascript:fun_delete('<ww:property value="road_bridge_charge_id"/>')">删除</a>
						</tt:authority>
					</ww:if>
				</tt:col>
			</c:if>
		</tt:row>
	</tt:grid>
</body>