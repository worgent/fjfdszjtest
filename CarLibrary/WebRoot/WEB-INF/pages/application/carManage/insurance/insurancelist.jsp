<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%--
	 * 保险管理
 	 * @author zhengmh 
--%>
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
			 insuranceCompanyData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'INSURANCE_PARA' and para_name = 'INSURANCE_AGENT'\""/>
				<ww:iterator value="#select.selectList" status="belongCompanyList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#belongCompanyList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var insuranceCompany = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: insuranceCompanyData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.insuranceAgent',
				fieldLabel: '保险公司',
				name: 'test',
				width:100
			})						
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/carmanage/insurance/insurance.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/insurance/insurance.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '被保险人',
			            name: 'search.insurant',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '保险开始日期',
			            name: 'search.beginDate',
			            width:100  
			        },{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '保险结束日期',
			            name: 'search.endDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '车辆编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },insuranceCompany,cityId],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '保险查询',
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
		    <tt:authority value="TJCLBX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加保险信息','addInsurance','/carmanage/insurance/insurance.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(insuranceId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   	var url = '/carmanage/insurance/insurance.shtml?actionType=delete&search.insuranceId='+insuranceId;
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
							alert("删除保险信息，操作成功！");
							parent.document.ifrm_CarInsurance.window.location.reload();
						}else{
							alert("删除保险信息，操作失败！");
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
	<tt:grid id="insurance" value="insuranceList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="单据日期" width="80">
				<ww:if test="null != insurance_id">
					<a href="javascript:parent.addTab('保险信息', 'viewInsurance', '/carmanage/insurance/insurance.shtml?actionType=view&search.insuranceId=<ww:property value="insurance_id"/>','NO')">
						<ww:property value="booking_date"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="被保险人" property="insurant" width="80"/>			
			<tt:col name="保险开始日期" property="begin_date" width="80"/>
			<tt:col name="保险结束日期" property="end_date" width="80"/>	
			<tt:col name="保险车辆编号" property="carNoId" width="80"/>		
			<tt:col name="用途" property="purpose" width="100" visible="true"/>
			<tt:col name="保险金额" property="insurance_charge" width="75"/>
			<tt:col name="保险费" property="insurance_fee" width="75"/>
			<tt:col name="保险公司" property="insuranceAgent" width="100"/>
			<tt:col name="所属地市" property="cityName" width="80"/>
			<tt:col name="操作" align="center" width="100">			
				<ww:if test="null != insurance_id">
					<tt:authority value="XGCLBX">
						<a href="javascript:parent.addTab('修改保险信息', 'editInsurance', '/carmanage/insurance/insurance.shtml?actionType=edit&search.insuranceId=<ww:property value="insurance_id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="XCCLBX">
						<a href="javascript:fun_delete(<ww:property value="insurance_id"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>