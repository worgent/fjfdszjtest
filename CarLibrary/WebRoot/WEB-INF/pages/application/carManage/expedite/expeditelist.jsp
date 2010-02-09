<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<%--
	 * 派车登记
 	 * @author FANGZL 
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
			
			//部门
			var belongCompanyTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/basearchives/deptInfo/ajaxDeptInfo.shtml',
		            hiddenName : 'search.deptId',
		            valueField : 'id',
		            fieldLabel: '用车部门',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            value:''
			});
							
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			    	labelAlign: 'right', 
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/carmanage/expedite/expedite.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/expedite/expedite.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [belongCompanyTree,{
			            fieldLabel: '车辆编号',
			            name: 'search.carNoCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '派车人',
			            name: 'search.expediteMan',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '用车人',
			            name: 'search.useCarMan',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '驾驶员',
			            name: 'search.driver',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: ' 出车日期',
			            name: 'search.begExpediteDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endExpediteDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '派车申请单编号',
			            name: 'search.expediteapplyId',
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
			})
		</c:if>
		
		function fun_delete(expediteCarId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   	var url = '/carmanage/expedite/expedite.shtml?actionType=delete&search.expediteCarId='+expediteCarId;
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
							alert("删除派车信息，操作成功！");
							parent.document.ifrm_ExpediteCarBooking.window.location.reload();
						}else{
							alert("删除派车信息，操作失败！");
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
	<tt:grid id="expedite" value="expediteList" pagination="true" xls="true">
		<tt:row >
			<tt:col name="单据号" width="120">
				<ww:if test="null != expedite_car_id">
					<a href="javascript:parent.addTab('派车信息', 'viewExpedite', '/carmanage/expedite/expedite.shtml?actionType=view&search.expediteCarId=<ww:property value="expedite_car_id"/>&search.expediteapplyId=<ww:property value="expediteapply_id"/>','NO')">
						<ww:property value="expedite_car_id"/>
					</a>
				</ww:if>
			</tt:col>			 		
			<tt:col name="派车日期" property="expedite_date" width="120"/>			
			<tt:col name="派车人" property="expedite_man_name" width="80"/>		
			<tt:col name="用车部门" property="dept_name" width="100" />
			<tt:col name="用车人" property="use_car_man_name" width="75"/>			
			<tt:col name="车辆编号" property="car_no_code" width="80"/>			
			<tt:col name="驾驶员" property="driver_name" width="80"/>
			<tt:col name="起始里程(公里)" property="init_mileage" width="90"/>
			<tt:col name="预计回程时间" property="intending_date" width="120"/>

			<c:if test="${param.action != 'report'}">
				<tt:col name="操作" align="center" width="100">			
					<ww:if test="null != expedite_car_id">
						<ww:if test="expedite_state == 1">
							<tt:authority value="XGPCXX">
								<a href="javascript:parent.addTab('修改派车信息', 'editExpedite', '/carmanage/expedite/expedite.shtml?actionType=edit&search.expediteCarId=<ww:property value="expedite_car_id"/>&search.expediteapplyId=<ww:property value="expediteapply_id"/>','NO')">修改</a>
							</tt:authority>
						</ww:if>
					</ww:if>
				</tt:col>
			</c:if>
		</tt:row>
	</tt:grid> 
</body>