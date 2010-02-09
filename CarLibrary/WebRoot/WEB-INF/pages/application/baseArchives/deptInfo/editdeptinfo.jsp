<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!--
	部门管理 
 -->
<ww:action name="'select'" id="select"></ww:action>
<form methd='POST' name='form1' action='/basearchives/deptInfo/deptInfo.shtml' class="formcheck"  onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>	
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>添加新部门信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			    <td align='right'>部门编号:</td>
				<td colspan="3">
					<tt:TextField name="search.deptCode" value="search.dept_code" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">部门名称:</td>
				<td width="33%">
					<tt:TextField name="search.deptName" value="search.dept_name" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right' width="17%">所属公司:</td>
				<td width="33%">
					<div id="belongCompanyDIV"></div>
					<input type="hidden" name="search.belongCompany" value="">
			    </td>	
			</tr>	
			<tr>
				<td align='right'>备注：</td>
				<td colspan="3">
					<tt:TextArea name="search.memo" value="search.memo" width="400" height="50"/>
				</td>
			</tr>
			<tr>				
				<td align='right'>所属地市:</td>
				<td colspan="3">
					<tt:ComboBox name="search.cityId" value="search.city_id" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true" msg="请选择地市"
							sql="'select city_id id, city_name text from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'" />
				</td>
			</tr>	
		</tbody>
	</table>
	<input type='hidden' name='search.deptId' value='<ww:property value="search.dept_id"/>'/>
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
		var belongCompanyTree = new Ext.form.TreeField({
				minListHeight:200,
				dataUrl : '/basearchives/institution/ajaxInstitution.shtml',
	            hiddenName : 'search.belongCompany',
	            valueField : 'id',
	            allowBlank:false,
	            blankText : '请选择部门所属公司！',
	            treeRootConfig : {
	            	id:'',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'<ww:property value="search.belongCompany"/>',
	            value:'<ww:property value="search.belong_to_company"/>'
		});
		belongCompanyTree.render('belongCompanyDIV');	//输出到指定的对象中
	});
	
</script>