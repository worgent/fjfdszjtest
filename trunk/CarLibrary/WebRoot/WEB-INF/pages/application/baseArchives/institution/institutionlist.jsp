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
    	//显示查询面板
		function showSearch(){
			Ext.onReady(function() {
				upCompanyData = [
					<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'MANAGE_ORGAN_ARCHIVES' and para_name = 'UP_COMPANY'\""/>
					<ww:iterator value="#select.selectList" status="upCompanyList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#upCompanyList.count != #select.selectList.size()">,</ww:if></ww:iterator>
				];
				var upCompany = new Ext.form.ComboBox({
					valueField :"id",
					displayField: "text",
					store:new Ext.data.SimpleStore({
						fields: ["id", "text"],
						data: upCompanyData
					}),
					editable:false,
					triggerAction: 'all',
					mode: 'local',
					blankText:'请选择',
					emptyText:'请选择',
					hiddenName:'search.upCompany',
					fieldLabel: '上级公司',
					name: 'test',
					width:100
				})
				companyPropertyData = [
					<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'MANAGE_ORGAN_ARCHIVES' and para_name = 'COMPANY_PROPERTY'\""/>
					<ww:iterator value="#select.selectList" status="companyPropertyList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#companyPropertyList.count != #select.selectList.size()">,</ww:if></ww:iterator>
				];
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
				var companyProperty = new Ext.form.ComboBox({
					valueField :"id",
					displayField: "text",
					store:new Ext.data.SimpleStore({
						fields: ["id", "text"],
						data: companyPropertyData
					}),
					editable:false,
					triggerAction: 'all',
					mode: 'local',
					blankText:'请选择',
					emptyText:'请选择',
					hiddenName:'search.companyProperty',
					fieldLabel: '公司性质',
					name: 'test',
					width:100
				})
				callingData = [
					<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'MANAGE_ORGAN_ARCHIVES' and para_name = 'CALLING'\""/>
					<ww:iterator value="#select.selectList" status="callingList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#callingList.count != #select.selectList.size()">,</ww:if></ww:iterator>
				];
				var calling = new Ext.form.ComboBox({
					valueField :"id",
					displayField: "text",
					store:new Ext.data.SimpleStore({
						fields: ["id", "text"],
						data: callingData
					}),
					editable:false,
					triggerAction: 'all',
					mode: 'local',
					blankText:'请选择',
					emptyText:'请选择',
					hiddenName:'search.calling',
					fieldLabel: '公司行业',
					name: 'test',
					width:100
				})
				proxyLevelData = [
					<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'MANAGE_ORGAN_ARCHIVES' and para_name = 'PROXY_LEVEL'\""/>
					<ww:iterator value="#select.selectList" status="proxyLevelList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#proxyLevelList.count != #select.selectList.size()">,</ww:if></ww:iterator>
				];
				var proxyLevel = new Ext.form.ComboBox({
					valueField :"id",
					displayField: "text",
					store:new Ext.data.SimpleStore({
						fields: ["id", "text"],
						data: proxyLevelData
					}),
					editable:false,
					triggerAction: 'all',
					mode: 'local',
					blankText:'请选择',
					emptyText:'请选择',
					hiddenName:'search.proxyLevel',
					fieldLabel: '代理级别',
					name: 'test',
					width:100
				})				
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/basearchives/institution/institution.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/basearchives/institution/institution.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '公司编号',
			            name: 'search.companyCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '公司名称',
			            name: 'search.companyName',
			            width:100  
			        },{
			            fieldLabel: '公司简称',
			            name: 'search.companyEasyName',
			            width:100  
			        },	 {
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			        	fieldLabel: '注册日期',
			        	name: 'search.regDate',
			        	width:100
			        },upCompany, companyProperty, calling, proxyLevel,cityId],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
				
			    var window = new Ext.Window({
			        title: '机构信息查询',
			        width: 300,
			        height:320,
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
		//创建按钮
		Ext.onReady(function(){
			new Ext.Button({
		        text: '查 询',
		        handler: showSearch
		    }).render(document.all.searchPanel);
		    <tt:authority value="SCGLJGXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加机构信息','addInstitution','/basearchives/institution/institution.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		//删除记录 AJAX方式调用
		function fun_delete(companyId){
			if (!confirm('您确定删除该信息!')){
					return;
			}else{
				var url = '/basearchives/institution/institution.shtml?actionType=delete&search.companyId='+companyId;
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
							alert("删除机构信息，操作成功！");
							parent.document.ifrm_InstitutionManage.window.location.reload();
						}else{
							alert("删除机构信息，操作失败！");
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
	<tt:grid id="institution" value="institutionList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="公司编号" width="100">
				<ww:if test="null != company_id">
					<a href="javascript:parent.addTab('查看机构信息', 'viewInstitution', '/basearchives/institution/institution.shtml?actionType=view&search.companyId=<ww:property value="company_id"/>','NO')"><ww:property value="company_code"/></a> 
				</ww:if>
			</tt:col>
			<tt:col name="公司名称" property="company_name" width="100"/>			
			<tt:col name="公司简称" property="company_easy_name" visible="true"/>
			<tt:col name="注册日期" property="reg_date" visible="true"/>
			<tt:col name="上级公司" property="upCompany" width="70"/>			
			<tt:col name="公司性质" property="companyProperty" width="70"/>
			<tt:col name="公司行业" property="CALLING2" width="70"/>
			<tt:col name="代理级别" property="proxyLevel" width="70"/>
			<tt:col name="所属地市" property="cityId" width="70"/>
			<tt:col name="联系人" property="link_man" width="70"/>
			<tt:col name="联系电话" property="link_phone" width="70"/>
			<tt:col name="操作" align="center" width="80">
				<ww:if test="null != company_id">
					<tt:authority value="TJGLJGXX">
						<a href="javascript:parent.addTab('修改机构信息', 'editInstitution', '/basearchives/institution/institution.shtml?actionType=edit&search.companyId=<ww:property value="company_id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="XGGLJGXX">
						<a href="javascript:fun_delete(<ww:property value="company_id"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>