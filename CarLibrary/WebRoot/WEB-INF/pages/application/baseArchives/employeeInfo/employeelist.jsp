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
		  sexData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para  t where t. para_type = 'BASE_PARA' and t.para_name = 'sex'\""/>
				<ww:iterator value="#select.selectList" status="sexList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#sexList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var sex = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: sexData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.sex',
				fieldLabel: '性别',
				name: 'test',
				width:100
			})		
			 staffTypeData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'STAFF_INFO' and para_name = 'STAFF_TYPE'\""/>
				<ww:iterator value="#select.selectList" status="staffTypeList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#staffTypeList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var staffType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: staffTypeData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.staffType',
				fieldLabel: '员工类型',
				name: 'test',
				width:100
			})		
			
			 belongCompanyData = [
				<tt:setProperty name="#select.dynamicSql" value="'select * from tf_manage_organ_archives t where t.city_id in (select a.city_id from tf_staff_city a where a.staff_id = '+ #session['UserInfo'].staffId + ')'"/>
				<ww:iterator value="#select.selectList" status="belongCompanyList">["<ww:property value="company_id"/>", "<ww:property value="company_name"/>"]<ww:if test="#belongCompanyList.count != #select.selectList.size()">,</ww:if></ww:iterator>
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
			 belongDeptData = [
				<tt:setProperty name="#select.dynamicSql" value="'select distinct * from tf_dept_info t where t.city_id in (select a.city_id from tf_staff_city a where a.staff_id = ' + #session['UserInfo'].staffId + ')'"/>
				<ww:iterator value="#select.selectList" status="belongDeptList">["<ww:property value="dept_id"/>", "<ww:property value="dept_name"/>"]<ww:if test="#belongDeptList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var belongDept = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: belongDeptData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.belongDept',
				fieldLabel: '所属部门',
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
			        url:'/basearchives/employeeInfo/employee.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/basearchives/employeeInfo/employee.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '员工编号',
			            name: 'search.staffCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '员工姓名',
			            name: 'search.staffName',
			            width:100  
			        },sex,staffType,{
			        	xtype: 'textfield',
			            fieldLabel: '车辆编号',
			            name: 'search.carNo',
			            width:100  // 设置宽度，百分比的需加‘号
			        },belongCompany,belongDept,{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '入职时间',
			            name: 'search.accessionDate',
			            width:100  
			        },{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '离职时间',
			            name: 'search.dimissionDate',
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
			        title: '员工信息查询',
			        width: 300,
			        height:350,
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
		    <tt:authority value="TJYGXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加员工信息','addEmployeeInfo','/basearchives/employeeInfo/employee.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(staffId){
		    if (!confirm('您确定删除该信息!')){
					return;
				}else{
				   var url = '/basearchives/employeeInfo/employee.shtml?actionType=delete&search.staffInfoId='+staffId;
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
						alert("删除员工信息，操作成功！");
						parent.document.ifrm_EmployeeInfo.window.location.reload();
					}else{
						alert("删除员工信息，操作失败！");
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
	<tt:grid id="employeeinfo" value="employeeList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="员工编号" width="100">
				<ww:if test="null != staff_info_id">
					<a href="javascript:parent.addTab('员工信息', 'viewEmployeeInfo', '/basearchives/employeeInfo/employee.shtml?actionType=view&search.staffInfoId=<ww:property value="staff_info_id"/>','NO')"><ww:property value="staff_code"/></a>
				</ww:if>
			</tt:col>
			<tt:col name="员工姓名" property="staff_name" width="100"/>
			<tt:col name="系统用户" property="operator_name" width="70"/>
			<tt:col name="员工性别" property="sex2" visible="true" width="50"/>
			<tt:col name="员工地址" property="address" visible="true" width="150"/>
			<tt:col name="员工类型" property="staffType" width="80"/>	
			<tt:col name="证件类型" property="certificateType" visible="true" width="80"/>
			<tt:col name="证件号" property="certificate_no" visible="true" width="100"/>	
			<tt:col name="联系电话" property="link_phone" visible="true" width="80"/>	
			<tt:col name="其他联系电话" property="link_other" visible="true" width="80"/>	
			<tt:col name="短信手机" property="sms_phone" width="80"/>		
			<tt:col name="所属公司" property="belongCompany" width="120"/>
			<tt:col name="所属部门" property="belongDept" width="100"/>
			<tt:col name="车牌号" property="carNo" visible="true" width="100"/>
			<tt:col name="IC卡号" property="Ic_Code" visible="true" width="100"/>
			<tt:col name="入职时间" property="accession_date" visible="true" width="80"/>
			<tt:col name="离职时间" property="dimission_date" visible="true" width="80"/>
			<tt:col name="所属地市" property="cityId" width="80"/>	
			<tt:col name="备注" property="memo" visible="true" width="100"/>
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != staff_info_id">
					<tt:authority value="XGYGXX">
						<a href="javascript:parent.addTab('修改员工信息', 'editEmployeeInfo', '/basearchives/employeeInfo/employee.shtml?actionType=edit&search.staffInfoId=<ww:property value="staff_info_id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCYGXX">
						<a href="javascript:fun_delete(<ww:property value="staff_info_id"/>)">删除</a>
					</tt:authority>
					    <a href="javascript:parent.addTab('复制员工信息', 'reEmployeeInfo', '/basearchives/employeeInfo/employee.shtml?actionType=renew&search.staffInfoId=<ww:property value="staff_info_id"/>','NO')">复制</a>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>