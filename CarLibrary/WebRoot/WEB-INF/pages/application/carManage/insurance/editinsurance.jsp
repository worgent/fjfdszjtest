<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 保险管理
 	 * @author zhengmh 
--%>

<form methd='POST' name='form1' action='/carmanage/insurance/insurance.shtml'>
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					保险信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">
					被保险人:
				</td>
				<td width="33%">
					<tt:TextField name="search.insurant" value="search.insurant" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right' width="17%">
					保险车辆编号:
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
					保险金额:
				</td>
				<td>
					<tt:TextField name="search.insuranceCharge" value="search.insurance_charge" width="200" verify='double+' required='true'/>
				</td>
				<td align='right'>
					保险开始日期:
				</td>
				<td>
					<tt:DateFiled name="search.begDate" value="search.begin_date" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff' onblur="if (compareDate(this.value, $('search.endDate').value)) Ext.Msg.show({title:'信息', msg: '保险结束日期必须大于开始日期！', modal : true, buttons: Ext.Msg.OK});"/>
				</td>
			</tr>
            <tr>
				<td align='right'>
					保险费:
				</td>
				<td>
					<tt:TextField name="search.insuranceFee" value="search.insurance_fee" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right'>
					保险结束日期:
				</td>
				<td>
					<tt:DateFiled name="search.endDate" value="search.end_date" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff' onblur="if (compareDate($('search.begDate').value, this.value)) Ext.Msg.show({title:'信息', msg: '保险结束日期必须大于开始日期！', modal : true, buttons: Ext.Msg.OK});"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					保险公司:
				</td>
				<td colspan="3">
					<tt:ComboBox name="search.insuranceAgent" value="search.insurance_agent" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true"
						sql="select PARA_VALUE id, PARA_VALUE_DESC text from TD_SYSTEM_PARA tt   where tt.para_type='INSURANCE_PARA'  and tt.para_name='INSURANCE_AGENT'"/>
				</td>				
			</tr>
			<tr>
				<td align='right'>
					用途:
				</td>
				<td colspan="3">
					<tt:TextArea name="search.purpose" value="search.purpose" width="400" height="50" required="true"/>
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
	<input type='hidden' name='search.insuranceId'
		value='<ww:property value="search.insurance_id"/>' />
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
        		if (compareDate($('search.begDate').value, $('search.endDate').value)) {
	        		Ext.Msg.show({
	 					title:'信息',
						msg: '保险结束日期必须大于开始日期！',
						modal : true,
						buttons: Ext.Msg.OK});
	        		return;
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

	function compareDate(DateOne,DateTwo){	
		var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ("-"));
		var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ("-")+1);
		var OneYear = DateOne.substring(0,DateOne.indexOf ("-"));
		
		var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ("-"));
		var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ("-")+1);
		var TwoYear = DateTwo.substring(0,DateTwo.indexOf ("-"));
		
		if (Date.parse(OneMonth+"/"+OneDay+"/"+OneYear)>= Date.parse(TwoMonth+"/"+TwoDay+"/"+TwoYear)){
			return true;
		}else{
			return false;
		}

	}
	
	
</script>