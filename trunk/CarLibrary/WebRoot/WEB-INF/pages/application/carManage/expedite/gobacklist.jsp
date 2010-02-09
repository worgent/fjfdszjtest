<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%--
	 * 回车登记
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
			        url:'/carmanage/expedite/goback.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/expedite/goback.shtml';
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
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: ' 回车日期',
			            name: 'search.begGobackDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endGobackDate',
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
			        height:350,
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
		
		function fun_delete(gobackCarId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   	var url = '/carmanage/expedite/goback.shtml?actionType=delete&search.gobackCarId='+gobackCarId;
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
							alert("删除回车信息，操作成功！");
							parent.document.ifrm_GoBackCarBooking.window.location.reload();
						}else{
							alert("删除回车信息，操作失败！");
						}
					}
				}catch(e){ 
					alert(e);
				}
			}
		}
		
		/*时间2009-04-13,短信反馈*/
		function sms_feedback(goback_car_id,userman,usermobile,drivername,driver,cityid,billtype,drivernameex){
		    if (!confirm('您确定给 用车人:'+userman+' 联系方式:'+usermobile+' 发送该条客户反馈短信吗?')){
				return;
			}else{
			    if(billtype==0)
			     	var url = '/carmanage/expedite/goback.shtml?actionType=feedback&search.gobackCarId='+goback_car_id+'&search.useCarManName='+userman+'&search.useCarManmobile='+usermobile+'&search.drivername='+drivername+'&search.driver='+driver+'&search.cityid='+cityid;
			    else if(billtype=1)
			     	var url = '/carmanage/expedite/goback.shtml?actionType=feedback&search.gobackCarId='+goback_car_id+'&search.useCarManName='+userman+'&search.useCarManmobile='+usermobile+'&search.drivername='+drivernameex+'&search.driver='+driver+'&search.cityid='+cityid;
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
							alert("发送短信,操作成功！");
							parent.document.ifrm_GoBackCarBooking.window.location.reload();
						}else{
							alert("发送短信,操作失败！");
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
			<tt:col name="出车单据号" width="135">
				<ww:if test="null != expedite_car_id">
					<a href="javascript:parent.addTab('回车信息', 'viewGoBack', '/carmanage/expedite/goback.shtml?actionType=view&search.expediteCarId=<ww:property value="expedite_car_id"/>','NO')">
						<ww:property value="expedite_car_id"/>
					</a>
				</ww:if>
			</tt:col>						 		
			<tt:col name="派车日期" property="expedite_date" width="120"/>	
			<tt:col name="车辆编号" property="car_no_code" width="80"/>
			<tt:col name="起始里程(公里)" property="init_mileage" width="80"/>
			<tt:col name="终止里程(公里)" property="end_mileage" width="80"/>
			<tt:col name="用车时长(小时)" property="usr_time_len" width="80"/>		
			<tt:col name="回车日期" property="goback_date" width="120"/>	
			<tt:col name="客户反馈" align="center" width="100">
			    <ww:if test="''.equals(feedback) and 0==feedback_state">
			        <a href="javascript:sms_feedback('<ww:property value="goback_car_id"/>','<ww:property value="use_car_man_name"/>','<ww:property value="use_car_man_mobile"/>','<ww:property value="driver_name"/>','<ww:property value="driver"/>','<ww:property value="city_id"/>','<ww:property value="bill_type"/>','<ww:property value="driver_nameex"/>')">发送</a>
			    </ww:if>
			    <ww:if test="''.equals(feedback) and 1==feedback_state">
			         待反馈| <a href="javascript:sms_feedback('<ww:property value="goback_car_id"/>','<ww:property value="use_car_man_name"/>','<ww:property value="use_car_man_mobile"/>','<ww:property value="driver_name"/>','<ww:property value="driver"/>','<ww:property value="city_id"/>','<ww:property value="bill_type"/>','<ww:property value="driver_nameex"/>')">重发</a>
			    </ww:if>
			    <ww:else>
			      <ww:property value="feedback" />
				</ww:else>
			</tt:col>
			<c:if test="${param.action != 'report'}">
				<tt:col name="操作" align="center" width="100">			
					<ww:if test="null != expedite_car_id">
					  	<ww:if test="null == goback_car_id">
					  		<tt:authority value="DJHCXX">
					    		<a href="javascript:parent.addTab('登记回车信息', 'editGoBack', '/carmanage/expedite/goback.shtml?actionType=new&search.expediteCarId=<ww:property value="expedite_car_id"/>&carNoCode=<ww:property value="car_no_code"/>','NO')">登记</a>
					    	</tt:authority>
					  	</ww:if>
					  	<ww:else>
					  		<tt:authority value="XGHCXX">
					  			<a href="javascript:parent.addTab('修改回车信息', 'editGoBack', '/carmanage/expedite/goback.shtml?actionType=edit&search.gobackCarId=<ww:property value="goback_car_id"/>&carNoCode=<ww:property value="car_no_code"/>&search.expediteCarId=<ww:property value="expedite_car_id"/>','NO')">修改</a>
					  		</tt:authority>
						</ww:else>
					</ww:if>
				</tt:col>
			</c:if>
		</tt:row>
	</tt:grid> 
</body>