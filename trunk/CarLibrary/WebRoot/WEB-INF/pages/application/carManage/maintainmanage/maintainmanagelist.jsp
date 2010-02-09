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
			
			 nurseTypeData = [
				<tt:setProperty name="#select.dynamicSql" value="'select distinct * from tf_fixing_info '"/>
				<ww:iterator value="#select.selectList" status="nurseTypeList">["<ww:property value="fixing_id"/>", "<ww:property value="fixing_name"/>"]<ww:if test="#nurseTypeList.count != #select.selectList.size()">,</ww:if></ww:iterator>
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
				hiddenName:'search.nurseType',
				fieldLabel: '保养类型',
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
			        url:'/carmanage/maintainmanage/maintainManage.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/maintainmanage/maintainManage.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [nurseType,{
			            fieldLabel: '保养里程',
			            name: 'search.nurseMileage',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '保养日期',
			            name: 'search.nurseDate',
			            width:100  
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '车辆编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },cityId],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '保养查询',
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
		    <tt:authority value="TJBYXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加保养信息','addMaintainManage','/carmanage/maintainmanage/maintainManage.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(nurseId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   var url = '/carmanage/maintainmanage/maintainManage.shtml?actionType=delete&search.nurseId='+nurseId;
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
							alert("删除保养信息，操作成功！");
							parent.document.ifrm_CareManage.window.location.reload();
						}else{
							alert("删除保养信息，操作失败！");
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
	<tt:grid id="maintainManage" value="maintainManageList" pagination="true" xls="true">
		<tt:row >
			<tt:col name="单据日期" width="80">
				<ww:if test="null != nurse_id">
					<a href="javascript:parent.addTab('保养信息', 'viewMaintainManage', '/carmanage/maintainmanage/maintainManage.shtml?actionType=view&search.nurseId=<ww:property value="nurse_id"/>','NO')">
						<ww:property value="booking_date"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="保养类型" property="nurseType" width="80"/>			
			<tt:col name="保养里程(公里)" property="nurse_mileage" width="80"/>
			<tt:col name="所属地市" property="cityName" width="80"/>	
			<tt:col name="保养日期" property="nurse_date" width="80"/>		
			<tt:col name="车辆编号" property="carNoId" width="80"/>
			<tt:col name="保养金额" property="nurse_charge" width="100"/>
			<tt:col name="用途" property="purpose" width="100"/>
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != nurse_id">
					<tt:authority value="XGBYXX">
						<a href="javascript:parent.addTab('修改保养信息', 'editMaintainManage', '/carmanage/maintainmanage/maintainManage.shtml?actionType=edit&search.nurseId=<ww:property value="nurse_id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCBYXX">
						<a href="javascript:fun_delete(<ww:property value="nurse_id"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>