<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%--
	 * 违章管理
 	 * @author FANGZL 
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
			 peccancyCompanyData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'PECCANCY_TYPE' and para_name = 'PECCANCY_TYPE'\""/>
				<ww:iterator value="#select.selectList" status="belongCompanyList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#belongCompanyList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var peccancyCompany = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: peccancyCompanyData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.peccancyType',
				fieldLabel: '违章类型',
				name: 'test',
				width:100
			})						
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/carmanage/peccancymanage/peccancymanage.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/peccancymanage/peccancymanage.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '违章地点',
			            name: 'search.peccancyAddress',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '违章日期',
			            name: 'search.begPeccancyDate',
			            width:100  
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endPeccancyDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '车辆编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },peccancyCompany,cityId],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '违章查询',
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
		<c:if test="${param.action != 'report'}">
		Ext.onReady(function(){
			new Ext.Button({
		        text: '查 询',
		        handler: showSearch
		    }).render(document.all.searchPanel);
		    <tt:authority value="TJWZXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加违章信息','addInsurance','/carmanage/peccancymanage/peccancymanage.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		</c:if>
		
		function fun_delete(peccancyId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   	var url = '/carmanage/peccancymanage/peccancymanage.shtml?actionType=delete&search.peccancyId='+peccancyId;
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
							alert("删除违章信息，操作成功！");
							parent.document.ifrm_PeccancyManage.window.location.reload();
						}else{
							alert("删除违章信息，操作失败！");
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
	<tt:grid id="peccancy" value="peccancyList" pagination="true" xls="true">
		<tt:row >
			<tt:col name="单据日期" width="80">
				<ww:if test="null != peccancy_id">
					<a href="javascript:parent.addTab('违章信息', 'viewPeccancy', '/carmanage/peccancymanage/peccancymanage.shtml?actionType=view&search.peccancyId=<ww:property value="peccancy_id"/>','NO')">
						<ww:property value="peccancy_booking_date"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="违章类型" property="peccancy_type_desc" width="80"/>			
			<tt:col name="违章日期" property="peccancy_date" width="80"/>			
			<tt:col name="车辆编号" property="car_no_code" width="80"/>		
			<tt:col name="违章地点" property="peccancy_address" width="100" />
			<tt:col name="违章内容" property="peccancy_context" width="75"/>			
			<tt:col name="所属地市" property="city_name" width="80"/>
			<c:if test="${param.action != 'report'}">
				<tt:col name="操作" align="center" width="100">			
					<ww:if test="null != peccancy_id">
						<tt:authority value="XGWZXX">
							<a href="javascript:parent.addTab('修改违章信息', 'editInsurance', '/carmanage/peccancymanage/peccancymanage.shtml?actionType=edit&search.peccancyId=<ww:property value="peccancy_id"/>','NO')">修改</a>
						</tt:authority>
						<tt:authority value="SCWZXX">
							<a href="javascript:fun_delete(<ww:property value="peccancy_id"/>)">删除</a>
						</tt:authority>
					</ww:if>
				</tt:col>
			</c:if>
		</tt:row>
	</tt:grid> 
</body>