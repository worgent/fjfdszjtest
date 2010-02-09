<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<!-- 
	养路费管理
 -->
<form methd='POST' name='form1' action='/carmanage/maintainroad/maintainRoad.shtml' class="formcheck" onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>	
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					养路费信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right'>
					车辆编号:
				</td>
				<td colspan="3">
					<c:if test="${param.actionType == 'new'}">
						<tt:ComboBox name="search.carNoId" value="search.car_no_id" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true" editable="true"
							sql="'select distinct car_no_id id, car_no_code text 
								   from tf_car_info 
								  where state = 1
								    and city_id in (select city_id 
								    				  from tf_staff_city  
								    				 where staff_id=' + #session['UserInfo'].staffId+')'"/>
					</c:if>
					<c:if test="${param.actionType == 'edit'}">
						<input type='hidden' name='search.carNoId' value='<ww:property value="search.car_no_id"/>'/>
						<ww:property value="search.carNoId"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">
					收费项目名称:
				</td>
				<td width="33%">
					<tt:TextField name="search.chargeItemName" value="search.charge_item_name" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right' width="17%">
					收费标准:
				</td>
				<td width="33%">
					<tt:TextField name="search.chargeStandard" value="search.charge_standard" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					收费范围:
				</td>
				<td>
					<tt:TextField name="search.chargeBound" value="search.charge_bound" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right'>
					批准机关:
				</td>
				<td>
					<tt:TextField name="search.authorizeOrgan" value="search.authorize_organ" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
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
	<input type='hidden' name='search.maintainRoadId' value='<ww:property value="search.maintain_road_id"/>' />
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