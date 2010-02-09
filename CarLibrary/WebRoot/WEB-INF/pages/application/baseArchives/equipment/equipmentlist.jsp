<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	设备管理
 -->
 
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
		 		
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        
			        url:'/basearchives/equipment/equipment.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/basearchives/equipment/equipment.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '设备编号',
			            name: 'search.fixingCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '设备名称',
			            name: 'search.fixingName',
			            width:100  
			        }],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '设备信息查询',
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
		    <tt:authority value="TJSBXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加设备信息','addEquipment','/basearchives/equipment/equipment.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(equipmentId){
		 	if (!confirm('您确定删除该信息!')){
				return;
			}else{
				var url = '/basearchives/equipment/equipment.shtml?actionType=delete&search.fixingId='+equipmentId;
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
							alert("删除设备信息，操作成功！");
							parent.document.ifrm_EquipmentInfo.window.location.reload();
						}else{
							alert("删除设备信息，操作失败！");
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
	<tt:grid id="equipment" value="equipmentList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="设备编号" width="130">
				<ww:if test="null != fixing_id">
					<a href="javascript:parent.addTab('查看设备信息', 'editEquipment', '/basearchives/equipment/equipment.shtml?actionType=view&search.fixingId=<ww:property value="fixing_id"/>','NO')"><ww:property value="fixing_code"/></a>
				</ww:if>
			</tt:col>
			<tt:col name="设备名称" property="fixing_name" width="130"/>			
			<tt:col name="备注" property="memo" width="130"/>
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != fixing_id">
					<tt:authority value="XGSBXX">
						<a href="javascript:parent.addTab('修改设备信息', 'editEquipment', '/basearchives/equipment/equipment.shtml?actionType=edit&search.fixingId=<ww:property value="fixing_id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCSBXX">
						<a href="javascript:fun_delete(<ww:property value="fixing_id"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>