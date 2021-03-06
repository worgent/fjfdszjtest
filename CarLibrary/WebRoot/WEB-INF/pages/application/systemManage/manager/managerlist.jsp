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
				hiddenName:'userInfo.cityId',
				fieldLabel: '所属地市',
				name: 'test',
				width:100
			})							
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/system/manage/manager.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/system/manage/manager.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '管理员工号',
			            name: 'userInfo.staffNo',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '管理员姓名',
			            name: 'userInfo.staffName',
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
			        title: '系统管理员查询',
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
		    <tt:authority value="TJXTYWXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加管理员信息','addDeptInfo','/system/manage/manager.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(staffId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   	var url = '/system/manage/manager.shtml?actionType=delete&userInfo.staffId='+staffId;
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
							alert("删除管理员信息，操作成功！");
							parent.document.ifrm_SystemManager.window.location.reload();
						}else{
							alert("删除管理员信息，操作失败！");
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
	<tt:grid id="userInfo" value="userList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="工号" property="staffNo" width="100"/>
			<tt:col name="用户名" property="staffName" width="100"/>
			<tt:col name="联系电话" property="phone" width="100"/>
			<tt:col name="注册时间" property="registerTime" width="100"/>
			<tt:col name="所属地市" property="cityName" width="100"/>
			<tt:col name="操作" align="center" width="140">
				<ww:if test="null != staffId">
					<tt:authority value="XTYHGXQYFP">
						<a href="javascript:parent.addTab('分配管辖区域', 'areaAllot', '/system/manage/manager.shtml?actionType=city&userInfo.staffId=<ww:property value="staffId"/>&userInfo.staffNo=<ww:property value="staffNo"/>&userInfo.staffName=<ww:property value="staffName"/>', 'NO')">区域</a>
					</tt:authority>
					<tt:authority value="XTYHJSFP">
						<a href="javascript:parent.addTab('分配用户组', 'roleAllot', '/system/manage/manager.shtml?actionType=role&userInfo.staffId=<ww:property value="staffId"/>&userInfo.staffNo=<ww:property value="staffNo"/>&userInfo.staffName=<ww:property value="staffName"/>', 'NO')">用户组</a>
					</tt:authority> 
					<tt:authority value="XGXTWHXX">
						<a href="javascript:parent.addTab('修改系统管理员信息', 'editSystemManager', '/system/manage/manager.shtml?actionType=edit&userInfo.staffId=<ww:property value="staffId"/>', 'NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCXTYWXX">
						<a href="javascript:fun_delete(<ww:property value="staffId"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid>	
</body>