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
		 	smsStateData = [["1", "未处理"],["2","已处理"]];
			var smsState = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: smsStateData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.smsState',
				fieldLabel: '处理状态',
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
			        	this.getEl().dom.action='/system/sms/inceptSms.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [smsState,{
			        	xtype: 'textfield',
			            fieldLabel: '发送者手机',
			            name: 'search.phoneCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '处理人',
			            name: 'search.staffName',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '接收时间',
			            name: 'search.begCollDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endCollDate',
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
			        title: '短信接收查询',
			        width: 300,
			        height:250,
			        minWidth: 300,
			        minHeight: 250,
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
		function fun_opt(optType, inceptSmsId){
			var msg;
			if (optType == 'delete')
				msg = '您确定将该条短信删除！';
			else if (optType == 'update')
				msg = '您确定将该条短信状态置为“处理”！';
				
		    if (!confirm(msg)){
				return;
			}else{
			   var url = '/system/sms/inceptSms.shtml?actionType='+optType+'&search.inceptSmsId='+inceptSmsId;
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
							<c:if test="${param.action == 'homepage'}">
								parent.document.inceptSmsIframe.window.location.reload();
							</c:if>
							<c:if test="${param.action != 'homepage'}">
								parent.document.ifrm_InceptSmsManage.window.location.reload();
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
		
		function view(phoneCode, inceptDate, smsContent){
			var smsViewWindow = new Ext.Window({
			        title: '短信内容',
			        width: 300,
			        <c:if test="${param.action == 'homepage'}">
			        	height:100,
			        </c:if>
			        <c:if test="${param.action != 'homepage'}">
			        	height:200,
			        </c:if>
			        layout: 'fit',
			        plain:true,
			        bodyStyle:'padding:5px;',
			        buttonAlign:'center',
			        html: "发送者号码："+phoneCode+"<br>"+
			        	  "发送日期："+inceptDate+"<br>"+
			        	  "短信内容："+smsContent
			    });
			smsViewWindow.show();
		}
    </script>
</head>

<body>
	<c:if test="${param.action != 'homepage'}">
		<div id="searchPanel" style="margin:0px;width:100px;float:left;"></div>
		
		<tt:grid id="inceptSmsList" value="smsList" pagination="true" xls="true">
			<tt:row>
				<tt:col name="发送者号码" property="phone_code" width="120"/>
				<tt:col name="发送者姓名" property="sms_staff_name" width="120"/>
				<tt:col name="发送时间" property="incept_date" width="110"/>
				<tt:col name="短信状态" property="sms_state_name" width="100"/>
				<tt:col name="采集时间" property="coll_date" width="110"/>
				<tt:col name="处理者" property="staff_name" width="100"/>
				<tt:col name="处理时间" property="tran_date" width="100"/>
				<tt:col name="操作" width="100">
					<ww:if test="null != incept_sms_id">
						<a onclick="view('<ww:property value="phone_code"/>', '<ww:property value="incept_date"/>', '<ww:property value="sms_content"/>')" href="javascript:void(0)">查看</a>
						<tt:authority value="CLJSDDX">
							<a href="javascript:fun_opt('update', '<ww:property value="incept_sms_id"/>')">处理</a>
							<a href="javascript:fun_opt('delete', '<ww:property value="incept_sms_id"/>')">删除</a>
						</tt:authority>
					</ww:if>
				</tt:col>
			</tt:row>
		</tt:grid>
	</c:if>
	
	<c:if test="${param.action == 'homepage'}">
		<tt:grid id="inceptSmsList" value="smsList" pagination="true" xls="false" width="570">
			<tt:row>
				<tt:col name="发送者号码" property="phone_code" width="90"/>
				<tt:col name="发送者姓名" property="sms_staff_name" width="90"/>
				<tt:col name="发送时间" property="incept_date" width="110"/>
				<tt:col name="短信状态" property="sms_state_name" width="70"/>
				<tt:col name="采集时间" property="coll_date" width="110"/>
				<tt:col name="操作" width="100">
					<ww:if test="null != incept_sms_id">
						<a onclick="view('<ww:property value="phone_code"/>', '<ww:property value="incept_date"/>', '<ww:property value="sms_content"/>')" href="javascript:void(0)">查看</a>
						<tt:authority value="CLJSDDX">
							<a href="javascript:fun_opt('update', '<ww:property value="incept_sms_id"/>')">处理</a>
							<a href="javascript:fun_opt('delete', '<ww:property value="incept_sms_id"/>')">删除</a>
						</tt:authority>
					</ww:if>
				</tt:col>
			</tt:row>
		</tt:grid>
	</c:if>
	<br>
	<div style="color:red">
		注：点击“处理”是将该条信息状态设置为已处理状态
	</div>
</body>