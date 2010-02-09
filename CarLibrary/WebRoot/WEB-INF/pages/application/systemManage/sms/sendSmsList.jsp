<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<!-- 
	获取接收到的记录数 
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
		 	sendStateData = [["0", "未发送"],["1","已发送"],["2","取消发送"]];
			var sendState = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: sendStateData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.sendState',
				fieldLabel: '发送状态',
				name: 'test',
				width:100
			})			
			
			sourceOrderTypeData = [["0", "其它"],["1","派车单短信"],["2","调度单短信"],["3","接收转发"],["4","IC卡异常"],
			["5","手动发送"],["6","非工作时间报警"],["7","越界报警"],["8","审批提醒"],["9","客户反馈"],["10","司机行车记录"],
			["11","停车超时"],["12","到站报警"],["13","短信临时派车"]];
			var sourceOrderType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: sourceOrderTypeData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.sourceOrderType',
				fieldLabel: '源单类型',
				name: 'test',
				width:100
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
			        	this.getEl().dom.action='/system/sms/sendSms.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [sendState,{
			        	xtype: 'textfield',
			            fieldLabel: '发送者',
			            name: 'search.staffName',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '发送时间',
			            name: 'search.begSendDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endSendDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },sourceOrderType,{
			        	xtype: 'textfield',
			            fieldLabel: '源单号',
			            name: 'search.sourceOrderCode',
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
			        title: '短信发送查询',
			        width: 300,
			        height:250,
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
		
		<c:if test="${param.action != 'ExpSuccess'}">
			Ext.onReady(function(){
				new Ext.Button({
			        text: '查 询',
			        handler: showSearch
			    }).render(document.all.searchPanel);
			})
		</c:if>
		
		
		function view(callPhone, sendDate, smsContent){
			var smsViewWindow = new Ext.Window({
			        title: '短信内容',
			        width: 300,
		        	height:200,
			        layout: 'fit',
			        plain:true,
			        bodyStyle:'padding:5px;',
			        buttonAlign:'center',
			        html: "接收者号码："+callPhone+"<br>"+
			        	  "发送时间："+sendDate+"<br>"+
			        	  "短信内容：<br>"+smsContent
			    });
			smsViewWindow.show();
		}
		
		function resend(optType, sendSmsId){
			var url = '/system/sms/sendSms.shtml?actionType='+optType+'&search.sendSmsId='+sendSmsId;
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
					if (optType == 'resend'){
						<c:if test="${param.action != 'ExpSuccess'}">
							parent.document.ifrm_SendSmsManage.window.location.reload();
						</c:if>
						<c:if test="${param.action == 'ExpSuccess'}">
							parent.document.sendSmsFrame.window.location.reload();
						</c:if>
					}
					if (optType == 'cancel'){
						if (1 == rowSet.item(0).selectSingleNode("value").text){
							alert("取消短信发送，操作成功!");
							
							<c:if test="${param.action != 'ExpSuccess'}">
								parent.document.ifrm_SendSmsManage.window.location.reload();
							</c:if>
							<c:if test="${param.action == 'ExpSuccess'}">
								parent.document.sendSmsFrame.window.location.reload();
							</c:if>
						}else{
							alert("操作失败!");
						}
					}
						
							
					
				}
			}catch(e){ 
				alert(e);
			}
		}
    </script>
</head>

<body>
		<div id="searchPanel" style="margin:0px;width:100px;float:left;"></div>
		
	<tt:grid id="smsList" value="smsList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="接收号码" property="call_phone" width="100"/>
			<tt:col name="接收者" property="call_staff_name" width="120"/>
			<tt:col name="创建时间" property="create_date" width="120"/>
			<tt:col name="发送时间" property="send_date" width="120"/>
			<tt:col name="源单类型" property="source_order_type_desc" width="100"/>
			<tt:col name="发送者" property="staff_id_name" width="100"/>
			<tt:col name="发送状态" property="send_state_desc" width="80"/>
			<tt:col name="操作" width="100">
				<ww:if test="null != send_sms_id">
					<a style="cursor:hand;" onclick="view('<ww:property value="call_phone"/>', '<ww:property value="send_date"/>', '<ww:property value="send_content"/>')">查看</a>
					<ww:if test="send_state == 0">
						<tt:authority value="CFDX">
						<a href="javascript:resend('resend', '<ww:property value="send_sms_id"/>')">重发</a>
						</tt:authority>
						<tt:authority value="QXDXFS">
							<a href="javascript:resend('cancel', '<ww:property value="send_sms_id"/>')">取消</a>
						</tt:authority>
					</ww:if>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid>
	
</body>