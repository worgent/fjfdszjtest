<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 违章管理
 	 * @author fangzl 
--%>
<form methd='POST' name='form1' action='/carmanage/peccancymanage/peccancymanage.shtml' class="formcheck" onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>	
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					添加新违章信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">
					违章类型:
				</td>
				<td >
					<tt:ComboBox name="search.peccancyType" value="search.peccancy_type" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true"
						sql="select PARA_VALUE id, PARA_VALUE_DESC text from TD_SYSTEM_PARA tt   where tt.para_type='PECCANCY_TYPE'  and tt.para_name='PECCANCY_TYPE'"/>
				</td>
				<td align='right' width="17%">
					违章车辆编号:
				</td>
				<td width="33%">
					<tt:ComboBox name="search.carNoId" value="search.car_no_id" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true" editable="true"
						sql="'select distinct car_no_id id, car_no_code text 
							   from tf_car_info 
							  where state = 1
							    and city_id in (select city_id 
							    				  from tf_staff_city  
							    				 where staff_id=' + #session['UserInfo'].staffId+')'"/>
				</td>
			</tr>
			<tr>
				
				<td align='right'>
					违章日期:
				</td>
				<td>
					<tt:DateFiled name="search.peccancyDate" value="search.peccancy_date" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right'>
					违章地点:
				</td>
				<td>
					<tt:TextField name="search.peccancyAddress" value="search.peccancy_address" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
			</tr>
            <tr>
				<td align='right'>
					违章内容:
				</td>
				<td colspan="3">
					<tt:TextArea name="search.peccancyContext" value="search.peccancy_context" width="400" height="50" verify='string' required='true' msg='请填写违章内容！'/>
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
	<input type='hidden' name='search.peccancyId'
		value='<ww:property value="search.peccancy_id"/>' />
	<input type='hidden' name='actionType'
		value='<ww:property value="action"/>' />
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

</script>