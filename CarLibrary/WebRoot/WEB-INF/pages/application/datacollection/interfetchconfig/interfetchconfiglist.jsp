<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!--
	车辆信息管理 
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
		 fetchModeData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'INTER_FETCH_CONFIG' and para_name = 'FETCH_MODE'\""/>
				<ww:iterator value="#select.selectList" status="fetchModeList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#fetchModeList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var fetchMode = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: fetchModeData
				}),
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.fetchMode',
				fieldLabel: '采集方式',
				name: 'test',
				width:300
			})	
												
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/datacollection/interFatchConfig.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [fetchMode,{
			            fieldLabel: '规则名称',
			            name: 'search.interFetchConfigName',
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
			        title: '车辆GPS接口数据采集规则配置',
			        width: 300,
			        height:150,
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
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加GPS接口数据采集规则配置','addCarInfo','/datacollection/interFatchConfig.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		})
		
		function fun_delete(interFetchConfigId){
			if (!confirm('您确定删除该信息!')){
				return;
			}else{
				var url = '/datacollection/interFatchConfig.shtml?actionType=delete&search.interFetchConfigId='+interFetchConfigId;
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
							alert("删除GPS接口数据采集规则配置，操作成功！");
							parent.document.ifrm_InterFetchConfig.window.location.reload();
						}else{
							alert("删除GPS接口数据采集规则配置，操作失败！");
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
	<tt:grid id="interFatchConfigList" value="interFatchConfigList" pagination="true" xls="false">
		<tt:row>
			<tt:col name="流水号" property="inter_fetch_config_id" width="120"/>
			<tt:col name="采集规则名称" property="inter_fetch_config_name" width="200"/>
			<tt:col name="采集方式" property="fetch_mode_name" width="100"/>
			<tt:col name="操作" width="100">
				<ww:if test="null != inter_fetch_config_id">
					<a href="javascript:parent.addTab('修改车辆信息', 'editCarInfo', '/datacollection/interFatchConfig.shtml?actionType=edit&search.interFetchConfigId=<ww:property value="inter_fetch_config_id"/>','NO')">修改</a>
					<a href="javascript:fun_delete(<ww:property value="inter_fetch_config_id"/>)">删除</a>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid>
</body>
