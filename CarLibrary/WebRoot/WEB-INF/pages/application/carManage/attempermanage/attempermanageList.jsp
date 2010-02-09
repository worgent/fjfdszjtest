<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	调度管理
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
    <script type="text/javascript" src="/js/TreeField.js"></script>
    <script language="javascript">
    	function showSearch(){
    		Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        labelAlign: 'right', 
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/attemperManage/attemper.shtml';
			        	this.getEl().dom.submit();
			        },
			        items: [{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '调度时间',
			            name: 'search.begAttemperDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endAttemperDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '单据号',
			            name: 'search.attemperManageId',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '车辆编号',
			            name: 'search.carNoCode',
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
			        title: '财务单据查询',
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
    	Ext.onReady(function(){
			new Ext.Button({
		        text: '查 询',
		        handler: showSearch
		    }).render(document.all.searchPanel);
		    <tt:authority value="TJDDXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('创建调度记录','addAttemper','/carmanage/attemperManage/attemper.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(attemperManageId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   var url = '/carmanage/attemperManage/attemper.shtml?actionType=delete&search.attemperManageId='+attemperManageId;
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
							alert("删除调度记录，操作成功！");
							parent.document.ifrm_AttemperManage.window.location.reload();
						}else{
							alert("删除调度记录，操作失败！");
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
	
	<tt:grid id="attemperList" value="attemperList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="单据号" width="120">
				<ww:if test="null != attemper_manage_id">
					<a href="javascript:parent.addTab('车辆调度信息', 'viewAttemper', '/carmanage/attemperManage/attemper.shtml?actionType=view&search.attemperManageId=<ww:property value="attemper_manage_id"/>','NO')">
						<ww:property value="attemper_manage_id"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="单据时间" property="booking_date" width="120"/>
			<tt:col name="调度时间" property="attemper_date" width="120"/>
			<tt:col name="车辆编号" property="car_no_code" width="80"/>
			<tt:col name="用车部门" property="dept_id_name" width="80"/>
			<tt:col name="用车人" property="use_car_man_name" width="80"/>
			<tt:col name="司机" property="motor_id_name" width="80"/>
			<tt:col name="地市" property="city_name" width="80"/>
			<tt:col name="操作" width="80">
				<ww:if test="null != attemper_manage_id">
					<tt:authority value="SCDDXX">
						<a href="javascript:fun_delete(<ww:property value="attemper_manage_id"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid>
</body>