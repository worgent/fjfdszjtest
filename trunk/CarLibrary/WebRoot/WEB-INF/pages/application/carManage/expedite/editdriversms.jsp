<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 司机行车记录
 	 * @author fjfdszj
--%>

<form methd='POST' name='form1' action='/carmanage/expedite/driversms.shtml'>
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					司机行车记录
				</th>
			</tr>
		</thead>
		<tbody>
		     <tr>
					<td align='right'>
						车牌号:
					</td>
					<td colspan="3"> 
						<tt:TextField name="search.carMark" value="search.car_mark" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
					</td>	
			</tr>
			<tr>
				<td align='right' width="17%">
					司机:
				</td>
				<td width="33%">
					<tt:ComboBox name="search.driverStaffid" value="search.driver_staffid"  verify="empty" required="true" editable="true" 
						sql="'select distinct staff_info_id id, staff_name text 
								from tf_staff_info  
							   where state = 1 
							     and city_id in (select city_id   
							     				   from tf_staff_city    
							     				  where staff_id=' + #session['UserInfo'].staffId+')'"/>
					
				</td>
				<td align='right' width="17%">
					司机手机:
				</td>
				<td width="33%">
				    <tt:TextField name="search.driverMobile" value="search.driver_mobile" width="200" verify='int+' required='true'/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					起点:
				</td>
				<td>
					<tt:TextField name="search.firstLocus" value="search.first_locus" width="200" verify='string' required='true'/>
				</td>
				<td align='right'>
					起点时间:
				</td>
				<td>
					<tt:TextField name="search.firstDate" value="search.first_date" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					终点:
				</td>
				<td>
					<tt:TextField name="search.transferLocur" value="search.transfer_locur" width="200" verify='string' required='true'/>
				</td>
				<td align='right'>
					终点时间:
				</td>
				<td>
					<!--
					<tt:DateFiled name="search.transferDate" value="search.transfer_date" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff' />
					-->
					<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="search.transferDate" type="text" value="<ww:property value="search.transfer_date"/>" onfocus="calendar();"  size="18">					
					
				</td>
			</tr>
            <tr>
				<td align='right'>
					司机初始里程:
				</td>
				<td>
					<tt:TextField name="search.firstMileage" value="search.first_mileage" width="200" cssClass="check" verify='double+' required='true' shade='true' requiredColor='#ffffff'  onblur="acc_runmileage();" />单位：公里
				</td>
				<td align='right'>
					司机回车里程:
				</td>
				<td>
					<tt:TextField name="search.transferMileage" value="search.transfer_mileage" cssClass="check" verify='double+' required='true' shade='true' requiredColor='#ffffff'  onblur="acc_runmileage();"  />单位：公里
				</td>
			</tr>
			<tr>
				<td align='right'>
					司机行驶里程:
				</td>
				<td>
					<tt:TextField name="search.runMileage" value="search.run_mileage" width="200" cssClass="check" verify='double+' required='true' shade='true' requiredColor='#ffffff' readonly="true"/>单位：公里
				</td>
				<td align='right'>
					加油量:
				</td>
				<td>
					<tt:TextField name="search.useOilNum" value="search.use_oil_num" cssClass="check" verify='double+0' required='true' shade='true' requiredColor='#ffffff' />
				</td>				
			</tr>
			<tr>
				<td align='right'>
					加油金额:
				</td>
				<td>
					<tt:TextField name="search.useOilCharge" value="search.use_oil_charge" width="200" cssClass="check" verify='double+0' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right'>
					路桥费:
				</td>
				<td>
					<tt:TextField name="search.roadCharge" value="search.road_charge" cssClass="check" verify='double+0' required='true' shade='true' requiredColor='#ffffff' />
				</td>				
			</tr>	
			<tr>
				<td align='right'>
					住宿费:
				</td>
				<td>
					<tt:TextField name="search.resideCharge" value="search.reside_charge" width="200" cssClass="check" verify='double+0' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right'>
					停车费:
				</td>
				<td>
					<tt:TextField name="search.stopcarCharge" value="search.stopcar_charge" cssClass="check" verify='double+0' required='true' shade='true' requiredColor='#ffffff' />
				</td>				
			</tr>	
			<tr>
				<td align='right'>
					洗车费:
				</td>
				<td> 
					<tt:TextField name="search.washcarCharge" value="search.washcar_charge" width="200" cssClass="check" verify='double+0' required='true' shade='true' requiredColor='#ffffff'/>
				</td>	
				<td align='right'>
					所属地市:
				</td>
				<td>
					<tt:ComboBox name="search.cityId" value="search.city_id" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true"
							sql="'select city_id id, city_name text from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'" />
				</td>	
			</tr>				
			<tr>
				<td align='right'>
					备注:
				</td>
				<td colspan="3">
					<tt:TextArea name="search.smsMemo" value="search.sms_memo" width="400" height="50"/>
				</td>	
			</tr>
		</tbody>
	</table>
	<input type='hidden' name='search.driversmsId' value='<ww:property value="search.driversms_id"/>' />
	
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
    
    function acc_runmileage()
	{
		var transfermileage = $('search.transferMileage').value == '' ? 0 : $('search.transferMileage').value;
		var firstmileage = $('search.firstMileage').value == '' ? 0 : $('search.firstMileage').value;
		$('search.runMileage').value =Subtr(transfermileage,firstmileage);
	}
	
	
</script>