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
    <script type="text/javascript" src="/js/TreeField.js"></script>
    <script language="javascript">
		function showSearch(){
			var belongCompanyTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/basearchives/institution/ajaxInstitution.shtml',
		            hiddenName : 'search.belongCompany',
		            valueField : 'id',
		            fieldLabel : '所属公司',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            displayValue:'',
		            value:''
			});
			
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
			        url:'/basearchives/deptInfo/deptInfo.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/basearchives/deptInfo/deptInfo.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '部门编号',
			            name: 'search.deptCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '部门名称',
			            name: 'search.deptName',
			            width:100  
			        },belongCompanyTree,cityId],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '部门信息查询',
			        width: 300,
			        height:200,
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
		    <tt:authority value="TJBMXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加部门信息','addDeptInfo','/basearchives/deptInfo/deptInfo.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(deptId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   var url = '/basearchives/deptInfo/deptInfo.shtml?actionType=delete&search.deptId='+deptId;
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
							alert("删除部门信息，操作成功！");
							parent.document.ifrm_DeptInfoManage.window.location.reload();
						}else{
							alert("删除部门信息，操作失败！");
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
	<tt:grid id="deptinfo" value="deptInfoList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="部门编号" property="dept_code" width="100"/>
			<tt:col name="部门名称" property="dept_name" width="100"/>			
			<tt:col name="所属公司" property="belongCompany" width="100"/>
			<tt:col name="所属地市" property="cityId" width="100"/>	
			<tt:col name="备注" property="memo" width="100"/>
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != dept_id">
					<tt:authority value="XGBMXX">
						<a href="javascript:parent.addTab('修改部门信息', 'editDeptInfo', '/basearchives/deptInfo/deptInfo.shtml?actionType=edit&search.deptId=<ww:property value="dept_id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCBMXX">
						<a href="javascript:fun_delete(<ww:property value="dept_id"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>