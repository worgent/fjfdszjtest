<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	车辆维修登记
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
    		var coopUnitTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/basearchives/recunit/ajaxRecunit.shtml',
		            hiddenName : 'search.coopUnitId',
		            valueField : 'id',
		            fieldLabel : '维修厂',
		            value : '',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            }
			});
			
    		Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        labelAlign: 'right', 
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/servicingmanage/servicingApply.shtml';
			        	this.getEl().dom.submit();
			        },
			        items: [{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: ' 维修时间',
			            name: 'search.begMaintainDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endMaintainDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },coopUnitTree,{
			        	xtype: 'textfield',
			            fieldLabel: '维修车辆编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '申请人',
			            name: 'search.proposer',
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
			        title: '车轮维修申请查询',
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
			})
		</c:if>
    	function fun_delete(maintainId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   var url = '/carmanage/servicingmanage/servicingBooking.shtml?actionType=delete&search.maintainId='+maintainId;
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
							alert("删除车辆维修记录，操作成功！");
							parent.document.ifrm_MaintainBooking.window.location.reload();
						}else{
							alert("删除车辆维修记录，操作失败！");
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
	
	<tt:grid id="servicingList" value="servicingList" pagination="true" xls="true">
		<tt:row>
			<tt:col name="单据号" width="120">
				<ww:if test="null != maintain_id">
					<ww:if test="maintain_state == 1">
						<a href="javascript:parent.addTab('车辆维修信息', 'viewMaintainApply', '/carmanage/servicingmanage/servicingApply.shtml?actionType=view&search.maintainId=<ww:property value="maintain_id"/>','NO')">
							<ww:property value="maintain_id"/>
						</a>
					</ww:if>
					<ww:if test="maintain_state == 2">
						<a href="javascript:parent.addTab('车辆维修信息', 'viewMaintainBooking', '/carmanage/servicingmanage/servicingBooking.shtml?actionType=view&search.maintainId=<ww:property value="maintain_id"/>','NO')">
							<ww:property value="maintain_id"/>
						</a>
					</ww:if>
				</ww:if>
			</tt:col>
			<tt:col name="维修时间" property="maintain_date" width="100"/>
			<tt:col name="维修完毕时间" property="maintain_end_date" width="100"/>
			<tt:col name="金额(元)" property="charge" width="60"/>
			<tt:col name="维修车辆编号" property="car_no_code" width="100"/>
			<tt:col name="申请人" property="proposer_name" width="100"/>
			<tt:col name="维修公里数(公里)" property="maintain_mileage" width="100"/>
			<tt:col name="维修厂" property="coop_unit_name" width="100"/>
			<tt:col name="维修原因" property="maintain_excuse" width="100"/>
			<c:if test="${param.action != 'report'}">
				<tt:col name="操作" width="100">
					<ww:if test="null != maintain_id">
						<ww:if test="maintain_state == 1">
							<tt:authority value="TJWXDJ">
								<a href="javascript:parent.addTab('车辆维修登记', 'newMaintainBooking', '/carmanage/servicingmanage/servicingBooking.shtml?actionType=new&search.maintainId=<ww:property value="maintain_id"/>','NO')">登记</a>
							</tt:authority>
						</ww:if>
						<ww:if test="maintain_state == 2">
							<tt:authority value="XGWXDJ">
								<a href="javascript:parent.addTab('修改车辆维修登记', 'editMaintainBooking', '/carmanage/servicingmanage/servicingBooking.shtml?actionType=edit&search.maintainId=<ww:property value="maintain_id"/>','NO')">修改</a>
							</tt:authority>
							<%-- <a href="javascript:fun_delete('<ww:property value="maintain_id"/>')">删除</a> --%>
						</ww:if>
					</ww:if>
				</tt:col>
			</c:if>
		</tt:row>
	</tt:grid>
</body>