<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	加油管理
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
		 	putonsteamTypeData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para tt  where tt.para_type='BASE_PARA'    and  tt.para_name = 'PUT_ON_STEAM_TYPE'\""/>
				<ww:iterator value="#select.selectList" status="putonsteamTypeList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#putonsteamTypeList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var putonsteamType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: putonsteamTypeData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.putOnSteamType',
				fieldLabel: '加油类型',
				name: 'test',
				width:100
			})				  
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/carmanage/putonsteam/putonsteam.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/putonsteam/putonsteam.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: ' 加油时间',
			            name: 'search.begPutOnDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endPutOnDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '车辆编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },putonsteamType],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '加油查询',
			        width: 300,
			        height:230,
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
			    <tt:authority value="TJJYXX">
			    new Ext.Button({
			        text: '添 加',
			        handler: function(){
			        	parent.addTab('添加加油信息','addPutonsteam','/carmanage/putonsteam/putonsteam.shtml?actionType=new','NO');
			        }
			    }).render(document.all.addPanel);
			    </tt:authority>
			})
		</c:if>
		
		function fun_delete(putonsteamId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   var url = '/carmanage/putonsteam/putonsteam.shtml?actionType=delete&search.putonsteamId='+putonsteamId;
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
							alert("删除加油信息，操作成功！");
							parent.document.ifrm_PutOnSteamManage.window.location.reload();
						}else{
							alert("删除加油信息，操作失败！");
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
	<tt:grid id="putonsteam" value="putonsteamList" pagination="true" xls="true">
		<tt:row >
			<tt:col name="加油时间" width="80">
				<ww:if test="null != put_on_steam_id">
					<a href="javascript:parent.addTab('加油信息', 'viewPutOnSteamManage', '/carmanage/putonsteam/putonsteam.shtml?actionType=view&search.putonsteamId=<ww:property value="put_on_steam_id"/>','NO')">
						<ww:property value="put_on_date"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="上次加油里程" property="last_put_on_mileage" width="80"/>			
			<tt:col name="加油站" property="gasStation" width="100"/>
			<tt:col name="所属地市" property="cityName" width="80"/>	
			<tt:col name="加油类型" property="putOnType" width="80"/>		
			<tt:col name="车辆编号" property="carNoId" width="100"/>
			<tt:col name="加油时公里数(公里)" property="put_on_mileage" width="80" visible="true"/>
			<tt:col name="行驶里程(公里)" property="run_mileage" width="80" visible="true"/>
			<tt:col name="加油量" property="put_on_num" width="80"/>
			<tt:col name="百公里油耗" property="consume_100" width="80" visible="true"/>
			<tt:col name="单价" property="price" width="80"/>
			<tt:col name="金额(元)" property="charge" width="80"/>	
			<c:if test="${param.action != 'report'}">		
				<tt:col name="操作" align="center" width="100">
					<ww:if test="null != put_on_steam_id">
						<tt:authority value="XGJYXX">
							<a href="javascript:parent.addTab('修改加油信息', 'editPutOnSteamManage', '/carmanage/putonsteam/putonsteam.shtml?actionType=edit&search.putonsteamId=<ww:property value="put_on_steam_id"/>','NO')">修改</a>
						</tt:authority>
						<tt:authority value="SCJYXX">
							<a href="javascript:fun_delete(<ww:property value="put_on_steam_id"/>)">删除</a>
						</tt:authority>
					</ww:if>
				</tt:col>
			</c:if>
		</tt:row>
	</tt:grid> 
</body>