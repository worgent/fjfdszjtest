<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	车辆维修申请
 -->
<ww:action name="'select'" id="select"></ww:action>

<form methd='POST' name='form1' action='/carmanage/servicingmanage/servicingApply.shtml' class="formcheck" onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>	
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					车辆维修申请
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right'>维修时间:</td>
				<td>
					<tt:DateFiled name="search.maintainDate" value="search.maintain_date" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right' width="17%">维修车辆编号:</td>
				<td width="33%">
					<tt:ComboBox name="search.carNoId" value="search.car_no_id+'|'+search.car_no_code" verify="empty" required="true" editable="true"
						onchange="ajaxGetGPSCarMileage(this.value)"
						sql="\"select concat(car_no_id, '|', car_no_code) id, car_no_code text 
							   from tf_car_info 
							  where state = 1
							    and car_state = 1
							    and city_id in (select city_id 
							    				  from tf_staff_city  
							    				 where staff_id = \" + #session['UserInfo'].staffId+\")
							  union all
							  select concat(car_no_id, '|', car_no_code) id, car_no_code text
							    from tf_car_info 
							   where state = 1
							     and car_no_id = \"+search.car_no_id"/>
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">申请人:</td>
				<td width="33%">
					<tt:ComboBox name="search.proposer" value="search.proposer" verify="empty" required="true" editable="true"
						sql="'select distinct staff_info_id id, staff_name text 
							   from tf_staff_info 
							  where state = 1
							    and city_id in (select city_id 
							    				  from tf_staff_city  
							    				 where staff_id=' + #session['UserInfo'].staffId+')'"/>
				</td>
				<td align='right' width="17%">维修厂:</td>
				<td width="33%">
					<tt:ComboBox name="search.coopUnitId" value="search.coop_unit_id" verify="empty" required="true" editable="true"
						sql="'select distinct coop_unit_id id, coop_unit_name text 
							   from tf_rec_unit_info 
							  where state = 1
							    and city_id in (select city_id 
							    				  from tf_staff_city  
							    				 where staff_id=' + #session['UserInfo'].staffId+')'"/>
				</td>
			</tr>
			<tr>
				<td align='right'>维修公里数:</td>
				<td colspan="3">
					<tt:TextField name="search.maintainMileage" value="search.maintain_mileage" verify='double+' required='true'/> 单位：公里
				</td>
			</tr>
			<tr>
				<td align='right'>维修原因:</td>
				<td colspan="3">
					<tt:TextArea name="search.maintainExcuse" value="search.maintain_excuse" width="400" height="50" required='true' msg='请填写维修原因！'/>
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
	<input type='hidden' name='search.maintainId' value='<ww:property value="search.maintain_id"/>'/>
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
<div style="color:red">
	&nbsp;&nbsp;&nbsp;&nbsp;注：“维修公里数”为申请维修时抄表的里程值。<br>
</div>

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

	function ajaxGetGPSCarMileage(carNoCode){
    	carNoCode = carNoCode.substring(carNoCode.indexOf('|')+1, carNoCode.length);
    	var url = '/basearchives/car/ajaxGetGPSCarMileage.shtml?search.carNoCode='+carNoCode;
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
				var rowSet = root.selectNodes("//GPSCarMileage");
				if (rowSet.length > 0){
					document.all("search.maintainMileage").value = rowSet.item(0).selectSingleNode("value").text;
				}else{
					alert('获取车辆里程时出错，请重试！');
				}
			}
		}catch(e){ 
			alert(e);
		}
    }
	
</script>