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
			        url:'/carmanage/maintainroad/maintainRoad.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/maintainroad/maintainRoad.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '收费项目名称',
			            name: 'search.chargeItemName',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '收费标准',
			            name: 'search.chargeStandard',
			            width:100  
			        },{
			            fieldLabel: '收费范围',
			            name: 'search.chargeBound',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '批准机关',
			            name: 'search.authorizeOrgan',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '车辆编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },cityId,{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: ' 单据日期',
			            name: 'search.begBookingDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endBookingDate',
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
			        title: '部门养路费查询',
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
		    <tt:authority value="TJYLFXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加养路费信息','addMaintainRoad','/carmanage/maintainroad/maintainRoad.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(maintainRoadId){
		    if (!confirm('您确定删除该信息!')){
					return;
				}else{
				   var url = '/carmanage/maintainroad/maintainRoad.shtml?actionType=delete&search.maintainRoadId='+maintainRoadId;
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
						alert("删除养路费信息，操作成功！");
						parent.document.ifrm_MaintainRoadExpenses.window.location.reload();
					}else{
						alert("删除养路费信息，操作失败！");
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
	<tt:grid id="maintainroad" value="maintainRoadList" pagination="true" xls="true">
		<tt:row >
			<tt:col name="单据日期" width="80">
				<ww:if test="null != maintain_road_id">
					<a href="javascript:parent.addTab('养路费信息', 'viewMaintainRoad', '/carmanage/maintainroad/maintainRoad.shtml?actionType=view&search.maintainRoadId=<ww:property value="maintain_road_id"/>','NO')">
						<ww:property value="booking_date"/> 
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="收费项目名称" property="charge_item_name" width="100"/>			
			<tt:col name="收费标准" property="charge_standard" width="100"/>
			<tt:col name="所属地市" property="cityId" width="80"/>	
			<tt:col name="收费范围" property="charge_bound" width="100"/>		
			<tt:col name="车辆编号" property="carNoId" width="100"/>
			<tt:col name="批准机关" property="authorize_organ" width="100"/>
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != maintain_road_id">
					<tt:authority value="XGYLFXX">
						<a href="javascript:parent.addTab('修改养路费信息', 'editMaintainRoad', '/carmanage/maintainroad/maintainRoad.shtml?actionType=edit&search.maintainRoadId=<ww:property value="maintain_road_id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCYLFXX">
						<a href="javascript:fun_delete(<ww:property value="maintain_road_id"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>