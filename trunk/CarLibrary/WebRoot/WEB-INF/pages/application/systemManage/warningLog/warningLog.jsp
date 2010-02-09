<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<!-- 
	预警管理
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
		 	optFlagData = [["1", "未处理"],["2","已处理"]];
			var optFlag = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: optFlagData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.optFlag',
				fieldLabel: '处理状态',
				name: 'test',
				width:100
			});			
				  
		 	warningTypeData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'WARNING_PARA' and para_name = 'WARNING_TYPE'\""/>
				<ww:iterator value="#select.selectList" status="belongCompanyList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#select.selectList.size() != #belongCompanyList.count">,</ww:if></ww:iterator>
			];
			var warningType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: warningTypeData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.warningType',
				fieldLabel: '预警类型',
				name: 'test',
				width:100
			});
			
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/system/warning/warningLog.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [optFlag,warningType,{
			        	xtype: 'textfield',
			            fieldLabel: '处理人',
			            name: 'search.staffName',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '预警时间',
			            name: 'search.begCreateDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endCreateDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '处理时间',
			            name: 'search.begOptDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endOptDate',
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
			        title: '加油查询',
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
		
		<c:if test="${param.action != 'homepage'}">
			Ext.onReady(function(){
				new Ext.Button({
			        text: '查 询',
			        handler: showSearch
			    }).render(document.all.searchPanel);
			})
		</c:if>
		
		<tt:authority value="CLJSDDX">
		function fun_opt(optType, warningLogId){
			var msg;
			if (optType == 'opt')
				msg = '您确定将该条预警置为已处理！';
				
		    if (!confirm(msg)){
				return;
			}else{
			   var url = '/system/warning/warningLog.shtml?actionType='+optType+'&search.warningLogId='+warningLogId;
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
						var rowSet = root.selectNodes("//result");
						if (0 < rowSet.item(0).selectSingleNode("value").text){
							alert("操作成功！");
							<c:if test="${param.action != 'homepage'}">
								parent.document.ifrm_WarningLogManage.window.location.reload();
							</c:if>
							<c:if test="${param.action == 'homepage'}">
								parent.document.warningLogIframe.window.location.reload();
							</c:if>
						}else{
							alert("操作失败！");
						}
					}
				}catch(e){ 
					alert(e);
				}
			}	
		}
		</tt:authority>
    </script>
</head>

<body>
	<div id="searchPanel" style="margin:0px;width:100px;float:left;"></div>
	
	<c:if test="${param.action != 'homepage'}">
		<tt:grid id="warningLogList" value="warningLogList" pagination="true" xls="false">
			<tt:row>
				<tt:col name="预警内容" width="120">
					<ww:if test="null != warning_log_id">
						<a href='<ww:property value="url"/>'><ww:property value="warning_content"/></a>
					</ww:if>
				</tt:col>
				<tt:col name="预警时间" property="create_date" width="120"/>
				<tt:col name="预警类型" property="warning_type_desc" width="90"/>
				<tt:col name="状态" property="opt_flag_desc" width="70"/>
				<tt:col name="预警地市" property="city_name" width="120"/>
				<tt:col name="处理人" property="staff_name" width="120"/>
				<tt:col name="处理时间" property="opt_date" width="120"/>
				<tt:col name="操作" width="50">
					<ww:if test="null != warning_log_id">
						<a href="javascript:fun_opt('opt', '<ww:property value="warning_log_id"/>')">处理</a>
					</ww:if>
				</tt:col>
			</tt:row>
		</tt:grid>
	</c:if>
	<c:if test="${param.action == 'homepage'}">
		<tt:grid id="warningLogList" value="warningLogList" pagination="true" xls="false" width="800">
			<tt:row>
				<tt:col name="预警内容" width="400">
					<ww:if test="null != warning_log_id">
						<a href='<ww:property value="url"/>'><ww:property value="warning_content"/></a>
					</ww:if>
				</tt:col>
				<tt:col name="预警时间" property="create_date" width="120"/>
				<tt:col name="预警类型" property="warning_type_desc" width="90"/>
				<tt:col name="状态" property="opt_flag_desc" width="70"/>
				<tt:col name="预警地市" property="city_name" width="80"/>
				<tt:col name="操作" width="50">
					<ww:if test="null != warning_log_id">
						<a href="javascript:fun_opt('opt', '<ww:property value="warning_log_id"/>')">处理</a>
					</ww:if>
				</tt:col>
			</tt:row>
		</tt:grid>
	</c:if>
</body>