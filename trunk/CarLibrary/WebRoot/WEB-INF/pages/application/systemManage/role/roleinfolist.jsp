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
		createStaffData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select distinct t.staff_name,t.staff_id from tf_operator t\""/>
				<ww:iterator value="#select.selectList" status="createStaffList">["<ww:property value="staff_id"/>", "<ww:property value="staff_name"/>"]<ww:if test="#createStaffList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var createStaff = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: createStaffData
				}),
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.createStaff',
				fieldLabel: '创建者',
				name: 'test',
				width:300
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
			        url:'/system/role/roleInfo.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/system/role/roleInfo.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '角色名称',
			            name: 'search.roleName',
			            width:100  // 设置宽度，百分比的需加‘号
			        },createStaff,cityId],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '角色信息查询',
			        width: 300,
			        height:180,
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
		    <tt:authority value="TJJSXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加角色信息','addRoleInfo','/system/role/roleInfo.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(roleId){
		 if (!confirm('您确定删除该信息!')){
					return;
				}else{
			var url = '/system/role/roleInfo.shtml?actionType=delete&search.roleId='+roleId;
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
						alert("删除角色信息，操作成功！");
						parent.document.ifrm_UserRole.window.location.reload();
					}else{
						alert("删除角色信息，操作失败！");
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
	<tt:grid id="roleInfo" value="roleInfoList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="用户组名称" property="ROLE_NAME" width="100"/>						
			<tt:col name="创建者" property="createStaff" width="100"/>
			<tt:col name="角色说明" property="CONTENT" width="100"/>	
			<tt:col name="所属地市" property="cityId" width="100"/>		
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != ROLE_ID">
					<tt:authority value="JSQXFP">
						<a href="javascript:parent.addTab('分配用户组权限', 'editRoleInfo', '/system/role/roleallot.shtml?roleId=<ww:property value="ROLE_ID"/>&roleName=<ww:property value="ROLE_NAME"/>&content=<ww:property value="CONTENT"/>&cityName=<ww:property value="cityId"/>','NO')">权限</a>
					</tt:authority>
					<tt:authority value="XGJSXX">
						<a href="javascript:parent.addTab('修改用户组信息', 'editRoleInfo', '/system/role/roleInfo.shtml?actionType=edit&search.roleId=<ww:property value="ROLE_ID"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCJSXX">
						<a href="javascript:fun_delete(<ww:property value="ROLE_ID"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>