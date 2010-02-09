<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<%--
	 * 流程定义
--%>
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
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			    	labelAlign: 'right', 
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/system/flower/flowerInfo.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/system/flower/flowerInfo.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '流程编号',
			            name: 'search.flowerdefineCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '流程名称',
			            name: 'search.flowerdefineName',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '创建人',
			            name: 'search.createMan',
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
			        title: '派车查询',
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
			    <tt:authority value="TJPCXX">
			    new Ext.Button({
			        text: '添 加',
			        handler: function(){
			        	parent.addTab('添加流程定义信息','addExpedite','/system/flower/flowerInfo.shtml?actionType=new','NO');
			        }
			    }).render(document.all.addPanel);
			    </tt:authority>
			})
		</c:if>
		
		function fun_delete(flowerdefineId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   	var url = '/system/flower/flowerInfo.shtml?actionType=delete&search.flowerdefineId='+flowerdefineId;
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
							alert("删除流程定义信息，操作成功！");
							parent.document.ifrm_FlowerInfo.window.location.reload();
						}else{
							alert("删除流程定义信息，操作失败！");
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
	<tt:grid id="flowerDefine" value="flowerDefineList" pagination="true">
		<tt:row >
			<tt:col name="流程编号" width="120">
				<ww:if test="null != flowerdefine_id">
					<a href="javascript:parent.addTab('流程信息', 'viewFlowerInfo', '/system/flower/flowerInfo.shtml?actionType=view&search.flowerdefineId=<ww:property value="flowerdefine_id"/>','NO')">
						<ww:property value="flowerdefine_code"/>
					</a>
				</ww:if>
			</tt:col>			 		
			<tt:col name="流程名称" property="flowerdefine_name" width="120"/>			
			<tt:col name="流程版本" property="flowerdefine_version" width="80"/>		
			<tt:col name="所属地市" property="city_name" width="100" />
			<tt:col name="创建时间" property="create_date" width="120"/>			
			<tt:col name="创建人" property="create_man_name" width="100"/>
			<c:if test="${param.action != 'report'}">
				<tt:col name="操作" align="center" width="100">			
					<ww:if test="null != flowerdefine_id">
							<tt:authority value="XGPCXX">
								<a href="javascript:parent.addTab('修改流程定义', 'editFlowerInfo', '/system/flower/flowerInfo.shtml?actionType=edit&search.flowerdefineId=<ww:property value="flowerdefine_id"/>','NO')">修改</a>
							</tt:authority>
							<tt:authority value="SCPCXX">
								<a href="javascript:fun_delete(<ww:property value="flowerdefine_id"/>)">删除</a>
							</tt:authority>
					</ww:if>
				</tt:col>
			</c:if>
		</tt:row>
	</tt:grid> 
</body>