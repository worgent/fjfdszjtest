<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%--
	 * 司机行车登记
 	 * @author FANGZL 
--%>
<%@ include file="/common/taglibs.jsp"%>
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
			        url:'/carmanage/expedite/driversms.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/expedite/driversms.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '司机',
			            name: 'search.driverStaffName',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '司机手机',
			            name: 'search.driverMobile',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '车牌号',
			            name: 'search.carMark',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: ' 终点时间',
			            name: 'search.begintransferDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endtransferDate',
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
		
		Ext.onReady(function(){
			new Ext.Button({
		        text: '查 询',
		        handler: showSearch
		    }).render(document.all.searchPanel);
		    <tt:authority value="Driversms_Add">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加司机行车信息','adddriversms','/carmanage/expedite/driversms.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		
		function fun_delete(driversmsId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   	var url = '/carmanage/expedite/driversms.shtml?actionType=delete&search.driversmsId='+driversmsId;
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
							alert("删除行车记录，操作成功！");
							parent.document.ifrm_Driversms.window.location.reload();
						}else{
							alert("删除行车记录，操作失败！");
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
	<tt:grid id="driversms" value="driversmsList" pagination="true" xls="true">
		<tt:row >
			<tt:col name="单据号" width="135">
				<ww:if test="null != driversms_id">
					<a href="javascript:parent.addTab('行车登记信息', 'viewdriversms', '/carmanage/expedite/driversms.shtml?actionType=view&search.driversmsId=<ww:property value="driversms_id"/>','NO')">
						<ww:property value="driversms_id"/>
					</a>
				</ww:if>
			</tt:col>	
			<tt:col name="车牌号" property="car_mark" width="80" />
			<tt:col name="司机" property="driver_staffname" width="80" />
			<tt:col name="司机手机" property="driver_mobile" width="80" visible="true"/>								 		
			<tt:col name="起点" property="first_locus" width="80" />	
			<tt:col name="起点时间" property="first_date" width="80" visible="true"/>
			<tt:col name="初始里程" property="first_mileage" width="80" visible="true"/>
			<tt:col name="终点" property="transfer_locur" width="80"/>
			<tt:col name="终点时间" property="transfer_date" width="80" visible="true"/>		
			<tt:col name="回车里程" property="transfer_mileage" width="80" visible="true"/>	
			<tt:col name="行驶里程" property="run_mileage" width="80" visible="true"/>	
			<tt:col name="加油量" property="use_oil_num" width="80"/>
			<tt:col name="加油金额" property="use_oil_charge" width="80"/>
			<tt:col name="路桥费" property="road_charge" width="80"/>
			<tt:col name="住宿费" property="reside_charge" width="80"/>		
			<tt:col name="停车费" property="stopcar_charge" width="80" visible="true"/>	
			<tt:col name="洗车费" property="washcar_charge" width="80" visible="true" />
				<tt:col name="操作" align="center" width="100">			
					<ww:if test="null != driversms_id">
					  		<tt:authority value="Driversms_Update">
					  			<a href="javascript:parent.addTab('修改行车记录', 'updatedriversms', '/carmanage/expedite/driversms.shtml?actionType=edit&search.driversmsId=<ww:property value="driversms_id"/>','NO')">修改</a>
					  		</tt:authority>
					  		<tt:authority value="Driversms_Delete">
					  		    <a href="javascript:fun_delete(<ww:property value="driversms_id"/>)">删除</a>
					    	</tt:authority>
					</ww:if>
				</tt:col>

		</tt:row>
	</tt:grid> 
</body>