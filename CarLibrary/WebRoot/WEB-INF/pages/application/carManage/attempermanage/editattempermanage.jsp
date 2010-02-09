<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	加油管理
 -->
<ww:action name="'select'" id="select"></ww:action>

<form methd='POST' name='form1' action='/carmanage/attemperManage/attemper.shtml' class="formcheck" onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>	
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					调度记录信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="4" align="center">
					<a onclick="showSearchPlan()" href="javascript:void(0)" style="color:red">点击查询要调度的车辆</a>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">车辆编号:</td>
				<td width="33%">
					<label id="carNoCodeLab"></label>
					<input type="hidden" name="search.carNoCode" value="">
				</td>
				<td align='right' width="17%">司机:</td>
				<td width="33%">
					<label id="motorLab"></label>
					<input type="hidden" name="search.motorman" value="">
				</td>
			</tr>
			<tr>
				<td align='right'>调度地点:</td>
				<td colspan="3">
					<label id="attemperLocusLab"></label>
					<input type="hidden" name="search.attemperLocus" value="">
				</td>
			</tr>
			<tr>
				<td align='right'>用车部门:</td>
				<td>
					<div id="deptDIV"></div>
					<input type="hidden" name="search.deptId" value="">
				</td>
				<td align='right'>用车人:</td>
				<td>
					<tt:ComboBox name="search.useCarMan" value="search.use_car_man" verify="empty" required="true" editable="true"
						sql="'select distinct staff_info_id id, staff_name text 
							   from tf_staff_info 
							  where state = 1
							  	and staff_type in (1, 4)
							    and city_id in (select city_id 
							    				  from tf_staff_city  
							    				 where staff_id=' + #session['UserInfo'].staffId+')'"/>
				</td>
			</tr>
			<tr>
				<td align='right'>调度原因:</td>
				<td colspan="3">
					<tt:TextArea name="search.attemperCause" value="search.attemper_cause" width="400" height="50" required='true' msg='请填写调试原因！'/>
				</td>
			</tr>
			<tr>
				<td align='right'>短信内容:</td>
				<td colspan="3">
					<tt:TextArea name="search.smsContext" value="search.smsContext" width="400" height="50" required='true' msg='请填写短信内容以便通知司机！'/>
				</td>
			</tr>
			<tr>
				<td align='right'>备注:</td>
				<td colspan="3">
					<tt:TextArea name="search.memo" value="search.memo" width="400" height="50"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					所属地市:
				</td>
				<td colspan="3">
					<tt:ComboBox name="search.cityId" value="search.city_id" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true"
							sql="'select city_id id, city_name text from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'" />
				</td>
			</tr>	
		</tbody>
	</table>
	<input type='hidden' name='search.attemperManageId' value='<ww:property value="search.attemper_manage_id"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
</form>

<table style='width: 80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>
			&nbsp;
		</td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>

<script type="text/javascript" src="/js/TreeField.js"></script>
<script language="javascript">
	Ext.get('form1').un("submit", fm2.onSubmit, fm2);
	var saveButton =new Ext.Button({
        text: '保 存',
        handler: function(){
        	if (fm2.isValid()) {
        		if ($('search.carNoCode').value == ''){
        			Ext.Msg.show({
	 					title:'信息',
						msg: '请先选择要调度的车辆！',
						modal : true,
						buttons: Ext.Msg.OK
	 				});
	        		return ;
	        	}
	        	Ext.Msg.show({
				 	title:'再确认一下',
				 	modal : false,
				 	msg: '您确定资料正确吗?',
				 	buttons: Ext.Msg.OKCANCEL,
				 	fn: function(btn, text){
						if (btn == 'ok'){
					 		document.form1.submit();
					 	} 
				 	},
				 	animEl: 'buttonTD1'
			 	});
        	} else {
        		Ext.Msg.show({
 					title:'信息',
					msg: '请填写完整后再提交!',
					modal : true,
					buttons: Ext.Msg.OK
 				});
        	}
        }
    }).render(document.all.buttonTD1);
    
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.buttonTD2);
    
	//下拉型树型菜单
	Ext.onReady(function(){
		Ext.QuickTips.init();
		
		//部门
		var belongCompanyTree = new Ext.form.TreeField({
				minListHeight:200,
				dataUrl : '/basearchives/deptInfo/ajaxDeptInfo.shtml',
	            hiddenName : 'search.deptId',
	            valueField : 'id',
	            allowBlank:false,
	            blankText : '请选择用车部门！',
	            treeRootConfig : {
	            	id:'',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'<ww:property value="search.dept_id_name"/>',
	            value:'<ww:property value="search.dept_id"/>'
		});
		belongCompanyTree.render('deptDIV');	//输出到指定的对象中
	});
	
	var searchPlan = new Ext.Window();
	
	function showSearchPlan(){
		searchPlan.hidden = true;
		if (searchPlan.hidden){
			searchPlan = new Ext.Window({
		        title: '查询车辆位置',
		        width: 800,
		       	height:400,
		        layout: 'fit',
		        plain:true,
		        bodyStyle:'padding:5px;',
		        buttonAlign:'center',
		        items:[{
	                region:'center',
	                layout:'column',
	                baseCls:'x-plain',
	                autoScroll:true,
	                items:[{
			        	columnWidth:.7,
	                    baseCls:'x-plain',
	                    bodyStyle:'padding:5px 0 5px 5px',
	                    layout: 'form',
	                    labelWidth:120,
			        	items:[{
			        		xtype: 'textfield',
				            fieldLabel: '搜索车辆所在位置',
				            name: 'lastLocation',
				            width:300  // 设置宽度，百分比的需加‘号
			        	}]
			        },{
			        	columnWidth:.3,
	                    baseCls:'x-plain',
	                    bodyStyle:'margin:0px 0px 0px 0px',
			        	buttons: [{
				            text: '查询',
				            width:50,
				            handler: function(){
				            	document.all.GPSCarLocusIframe.src="/carmanage/attemperManage/attemper.shtml?actionType=findGPS&search.lastLocation="+document.all.lastLocation.value;
				            }
				        },{
				            text: '确定',
				            width:50,
				            handler: function(){
				            	if (document.all.GPSCarLocusIframe.src == ''){
				            		Ext.MessageBox.alert('提示', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请先查询!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
				            		return;
				            	}
				            	if (document.GPSCarLocusIframe.carNoCode.value == ''){
				            		Ext.MessageBox.alert('提示', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择车辆信息!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
				            		return;
				            	}
				            	document.all.carNoCodeLab.innerText = document.GPSCarLocusIframe.carNoCode.value;
				            	document.all.motorLab.innerText = document.GPSCarLocusIframe.motorman.value;
				            	document.all.attemperLocusLab.innerText = document.GPSCarLocusIframe.attemperLocus.value;
				            	
				            	document.all("search.carNoCode").value = document.GPSCarLocusIframe.carNoCode.value;
				            	document.all("search.motorman").value = document.GPSCarLocusIframe.motorman.value;
				            	document.all("search.attemperLocus").value = document.GPSCarLocusIframe.attemperLocus.value;
				            	searchPlan.close();
				            }
				        }]
			        },{
			        	columnWidth:1,
	                    baseCls:'x-plain',
	                    bodyStyle:'padding:5px',
	                    items:[{
	                    	width:"100%",
	                    	height:"100%",
	                        html: "<iframe id='GPSCarLocusIframe' name='GPSCarLocusIframe' src=''width='100%' height='285' frameborder=0/>"
	                    }]
			        }]
		        }]
		    });
	    }
		searchPlan.show();
	}
</script>