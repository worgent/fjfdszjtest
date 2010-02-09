<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>
<form methd='POST' name='form1' action='/basearchives/employeeInfo/employee.shtml' class="formcheck"  onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='8'>新员工信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			    <td align='right'>员工编号:</td>
				<td>
					<tt:TextField name="search.staffCode" value="search.staff_code" width="200" verify='string' required='true'/>
				</td>
				<td align='right'>系统用户:</td>
				<td>
					<input name="search.operatorId" type="hidden" value="<ww:property value="search.operator_id"/>">
					<input name="search.operatorName" type="text" value="<ww:property value="search.operator_name"/>" onfocus="showSearchPlan()">
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">员工姓名:</td>			
				<td width="33%">
					<tt:TextField name="search.staffName" value="search.staff_name" width="200" verify='string' required='true'/>
				</td>
				<td align='right' width="17%">性别:</td>
				<td width="33%">
					<tt:ComboBox name="search.sex" value="search.sex" verify="empty" required="true"
						sql="select para_value id, para_value_desc text from td_system_para  t where t.para_type = 'BASE_PARA' and para_name = 'SEX'"/>
			    </td>
			</tr>
			<tr>
				<td align='right'>证件类型:</td>
				<td>
					<tt:ComboBox name="search.certificateType" value="search.certificate_type" verify="empty" required="true"
						sql="select para_value id, para_value_desc text from td_system_para  t where t.para_type = 'BASE_PARA' and para_name = 'CERTIFICATE_TYPE'"/>
			    </td>
			    <td align='right'>员工类型:</td>
				<td>
					<tt:ComboBox name="search.staffType" value="search.staff_type" verify="empty" required="true"
						sql="select para_value id, para_value_desc text from td_system_para  t where t.para_type = 'STAFF_INFO' and para_name = 'STAFF_TYPE'"/>
				</td>
			</tr>
			<tr>
				<td align='right'>证件编号:</td>
				<td>
					<tt:TextField name="search.certificateNo" value="search.certificate_no" width="200" verify='string' required='true'/>
				</td>
				<td align='right'>联系电话:</td>
				<td>
					<tt:TextField name="search.linkPhone" value="search.link_phone" width="200" verify='string' required='true'/>
				</td>
			</tr>
			<tr>
				<td align='right'>所属公司:</td>
				<td>
					<div id="belongCompanyDIV"></div>
					<input type="hidden" name="search.belongCompany" value="">
			    </td>
				<td align='right'>其他联系电话:</td>
				<td>
					<tt:TextField name="search.linkOther" value="search.link_other" width="200"/>
				</td>   
			</tr>
			<tr>
				<td align='right'>所属部门:</td>
			    <td>
					<div id="belongDeptDIV"></div>
					<input type="hidden" name="search.belongDept" value="">
			    </td>	
			    <td align='right'>接收短信号码</td>
			    <td>
			    	<tt:TextField name="search.smsPhone" value="search.sms_phone" width="200" verify='mobile' required='true'/>
			    </td>
			</tr>
			<tr>
				<td align='right'>车牌号:</td>
				<td>
					<tt:ComboBox name="search.carNo" value="search.car_no" editable="true"
						sql="\"select distinct car_no_id id, car_no_code text 
							   from tf_car_info 
							  where state = 1
							    and car_no_id not in (select car_no 
							  							from tf_staff_info 
							  						   where staff_info_id <> '\" + search.staff_info_id + \"')
							  	and city_id in (select city_id 
							  					  from tf_staff_city  
							  					 where staff_id=\" + #session['UserInfo'].staffId + \")\""/>
				</td>
				<td align='right'>IC卡编号:</td>
			    <td>
			    	<tt:TextField name="search.Ic_Code" value="search.Ic_Code" width="200" verify='string' />
			    </td>
			</tr>
			<tr>
				<td align='right'>入职时间:</td>
				<td>
					<tt:DateFiled name="search.accessionDate" value="search.accession_date" verify='date' required='true'/>
				</td>
			    <td align='right'>离职时间:</td>
			    <td>
			    	<tt:DateFiled name="search.dimissionDate" value="search.dimission_date" verify='date'/>
			    </td>
			</tr>
			<tr>
				<td align='right'>通信地址:</td>
				<td colspan="3">
					<tt:TextField name="search.address" value="search.address" width="400" verify='string' required='true'/>
				</td>
			</tr>
			<tr>
				<td align='right'>备注：</td>
				<td  colspan="3">
					<tt:TextArea name="search.memo" value="search.memo" width="400" height="50"/>
				</td>
			</tr>	
			<tr>				
				<td align='right'>所属地市:</td>
				<td>
					<tt:ComboBox name="search.cityId" value="search.city_id" verify="empty" required="true"
							sql="'select city_id id, city_name text from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'" />
				</td>
			</tr>	
		</tbody>
	</table>
		<input type='hidden' name='search.staffInfoId' value='<ww:property value="search.staff_info_id"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>'/>
</form>
<table style='width:80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>&nbsp;</td>
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
		
		//所属公司
		var belongCompanyTree = new Ext.form.TreeField({
				minListHeight:200,
				dataUrl : '/basearchives/institution/ajaxInstitution.shtml',
	            hiddenName : 'search.belongCompany',
	            valueField : 'id',
	            allowBlank:false,
	            blankText : '请选择员工所属公司！',
	            treeRootConfig : {
	            	id:'',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'<ww:property value="search.belongCompany"/>',
	            value:'<ww:property value="search.belong_to_company"/>'
		});
		belongCompanyTree.render('belongCompanyDIV');	//输出到指定的对象中
		belongCompanyTree.tree.on('click', function(node){	//
			$(belongDeptDIV).innerHTML = '';
			crateDeptTree(node[belongCompanyTree.valueField]);
		});
		
		function crateDeptTree(param){
			var belongDeptTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/basearchives/deptInfo/ajaxDeptInfo.shtml?belongCompany='+param,
		            hiddenName : 'search.belongDept',
		            valueField : 'id',
		            allowBlank:false,
	            	blankText : '请选择部门所属部门！',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            displayValue:'<ww:property value="search.belongDept"/>',
		            value:'<ww:property value="search.belong_to_dept"/>'
			});
			belongDeptTree.render('belongDeptDIV');	//输出到指定的对象中
		}
		
		crateDeptTree();
	});
	
	//操作员信息表,单选
	var searchPlan = new Ext.Window();
		function showSearchPlan(){
		searchPlan.hidden = true;
		if (searchPlan.hidden){
			searchPlan = new Ext.Window({
		        title: '查询员工信息',
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
			        	columnWidth:.33,
	                    baseCls:'x-plain',
	                    bodyStyle:'padding:5px 0 5px 5px',
	                    layout: 'form',
	                    labelWidth:60,
			        	items:[{
			        		xtype: 'textfield',
				            fieldLabel: '员工编号',
				            name: 'staffNo',
				            width:100  // 设置宽度，百分比的需加‘号
			        	}]
			        },{
			        	columnWidth:.33,
	                    baseCls:'x-plain',
	                    bodyStyle:'padding:5px 0 5px 5px',
	                    layout: 'form',
	                    labelWidth:60,
			        	items:[{
			        		xtype: 'textfield',
				            fieldLabel: '员工姓名',
				            name: 'staffName',
				            width:100  // 设置宽度，百分比的需加‘号
			        	}]
			        },{
			        	columnWidth:.33,
	                    baseCls:'x-plain',
	                    bodyStyle:'margin:0px 0px 0px 0px',
			        	buttons: [{
				            text: '查询',
				            width:50,
				            handler: function(){
				            	document.all.EmployeeIframe.src="/system/manage/manager.shtml?search.action=extSignSearch"+
				            																				"&userInfo.staffNo="+document.all.staffNo.value+
				            																				"&userInfo.staffName="+document.all.staffName.value;
				            }
				        },{
				            text: '确定',
				            width:50,
				            handler: function(){
				            	if (document.all.EmployeeIframe.src ==''){
				            		Ext.MessageBox.alert('提示', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请先查询!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
				            		return;
				            	}

				            	if(document.all("search.operatorId").value.indexOf(document.EmployeeIframe.staffId.value)==0)
				            	{	
									//清除原数据
									document.all("search.operatorId").value=" ";
									document.all("search.operatorName").value=" ";
				            	}else
				            	{
						            document.all("search.operatorId").value = document.EmployeeIframe.staffId.value;
					            	document.all("search.operatorName").value = document.EmployeeIframe.staffName.value;			            	
				            	}
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
	                        html: "<iframe id='EmployeeIframe' name='EmployeeIframe' src=''width='100%' height='285' frameborder=0/>"
	                    }]
			        }]
			        }]
		    });
	    }
		searchPlan.show();
	}
	
</script>