<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	车辆维修申请
 -->
<ww:action name="'select'" id="select"></ww:action>
<table class='simple' style='width: 80%' align='center'>
	<thead>
		<tr>
			<th colspan='4'>
				车辆维修申请信息
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align='right'>维修时间:</td>
			<td>
				<ww:property value="search.maintain_date"/>
			</td>
			<td align='right' width="17%">维修车辆编号:</td>
			<td width="33%">
				<ww:property value="search.car_no_code"/>
			</td>
		</tr>
		<tr>
			<td align='right' width="17%">申请人:</td>
			<td width="33%">
				<ww:property value="search.proposer_name"/>
			</td>
			<td align='right' width="17%">维修厂:</td>
			<td width="33%">
				<ww:property value="search.coop_unit_name"/>
			</td>
		</tr>
		<tr>
			<td align='right'>维修公里数:</td>
			<td>
				<ww:property value="search.maintain_mileage"/> 单位：公里
			</td>
			<td align='right'>
				所属地市:
			</td>
			<td>
				<ww:property value="search.city_name"/>
			</td>
		</tr>
		<tr>
			<td align='right'>维修原因:</td>
			<td colspan="3">
				<ww:property value="search.maintain_excuse"/>
			</td>
		</tr>
		<tr>
			<td align='right'>创建人:</td>
			<td>
				<ww:property value="search.create_man_name "/>
			</td>
			<td align='right'>创建时间:</td>
			<td>
				<ww:property value="search.create_date"/>
		    </td>
		</tr>
	</tbody>
</table>
<br>
<form methd='POST' name='form1' action='/carmanage/servicingmanage/servicingBooking.shtml' class="formcheck" onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>	
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					车辆维修登记信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">维修完毕时间:</td>
				<td width="33%">
					<tt:DateFiled name="search.maintainEndDate" value="search.maintain_end_date" verify='date' required='true'/>
				</td>
				<td align='right' width="17%">金额:</td>
				<td width="33%">
					<tt:TextField name="search.charge" value="search.charge" verify='double+' required='true' /> 单位：元
				</td>
			</tr>
			<tr>
				<td align='right'>维修结果:</td>
				<td colspan="3">
					<tt:TextArea name="search.maintainResult" value="search.maintain_result" width="400" height="50" required='true' msg='请填写维修结果！'/>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<table id='detailTable' class='simple' style='width: 98%' align='center'>
		<thead>
			<tr>
				<th colspan='8'>
					维修明细
				</th>
			</tr>
			<tr>
				<th width='40'>行号</th>
				<th width='200'>维修项目</th>
				<th width='55'>计量单位</th>
				<th width='50'>数量</th>
				<th width='50'>单价</th>
				<th width='50'>金额</th>
				<th>备注</th>
				<th width='60' style="cursor:hand" onclick='fun_addrow()'>[添加行]</th>
			</tr>
		</thead>
		<tbody id='detailTbody'>
			<ww:if test="null == search.detailList">
				<tr>
					<td align='center'>1</td>
					<td align='center'><input type='text' name='detail.maintainItem'class='check' verify='string' 	required='true' requiredColor='#ffffff' shade='true' value='' style='width:190px;border:1px solid #C0C0C0;'/></td>
					<td align='center'><input type='text' name='detail.unit'		class='check' verify='string' 	required='true' requiredColor='#ffffff' shade='true' value='' style='width:40px;border:1px solid #C0C0C0;'/></td>
					<td align='center'><input type='text' name='detail.num'			class='check' verify='int+' 	required='true' requiredColor='#ffffff' shade='true' id='num_1' onblur='acc_charge(this, $(price_1), $(charge_1))' value='' style='width:40px;border:1px solid #C0C0C0;'/></td>
					<td align='center'><input type='text' name='detail.price'		class='check' verify='double+' 	required='true' requiredColor='#ffffff' shade='true' id='price_1' onblur='acc_charge($(num_1), this, $(charge_1))' value='' style='width:40px;border:1px solid #C0C0C0;'/></td>
					<td align='center'><input type='text' name='detail.charge'		class='check' verify='double+' 	required='true' requiredColor='#ffffff' shade='true' id='charge_1' readonly value='' style='width:40px;border:1px solid #C0C0C0;'/></td>
					<td align='center'><TEXTAREA name='detail.memo' style='width:300px;height:40px;border:1px solid #C0C0C0;'></TEXTAREA></td>
					<td align='center'><A style='cursor:hand' onclick='del_row();'>删除</a></td>
				</tr>
			</ww:if>
			<ww:else>
				<ww:iterator value="search.detailList" status="detailList">
					<tr>
						<td align='center'><ww:property value="#detailList.count"/></td>
						<td align='center'><input type='text' name='detail.maintainItem'class='check' verify='string' 	required='true' requiredColor='#ffffff' shade='true' value='<ww:property value="maintain_item"/>' style='width:190px;border:1px solid #C0C0C0;'/></td>
						<td align='center'><input type='text' name='detail.unit' 		class='check' verify='string' 	required='true' requiredColor='#ffffff' shade='true' value='<ww:property value="unit"/>' style='width:40px;border:1px solid #C0C0C0;'/></td>
						<td align='center'><input type='text' name='detail.num' 		class='check' verify='int+' 	required='true' requiredColor='#ffffff' shade='true' id='num_<ww:property value="#detailList.count"/>' value='<ww:property value="num"/>' onblur='acc_charge(this, $(price_<ww:property value="#detailList.count"/>), $(charge_<ww:property value="#detailList.count"/>))' value='' style='width:40px;border:1px solid #C0C0C0;'/></td>
						<td align='center'><input type='text' name='detail.price' 		class='check' verify='double+' 	required='true' requiredColor='#ffffff' shade='true' id='price_<ww:property value="#detailList.count"/>' value='<ww:property value="price"/>' onblur='acc_charge($(num_<ww:property value="#detailList.count"/>), this, $(charge_<ww:property value="#detailList.count"/>))' value='' style='width:40px;border:1px solid #C0C0C0;'/></td>
						<td align='center'><input type='text' name='detail.charge' 		class='check' verify='double+' 	required='true' requiredColor='#ffffff' shade='true' id='charge_<ww:property value="#detailList.count"/>' value='<ww:property value="charge"/>' readonly value='' style='width:40px;border:1px solid #C0C0C0;'/></td>
						<td align='center'><TEXTAREA name='detail.memo' style='width:300px;height:40px;border:1px solid #C0C0C0;'><ww:property value="memo"/></TEXTAREA></td>
						<td align='center'><A style='cursor:hand' onclick='del_row(<ww:property value="maintain_detail_id"/>);'>删除</a></td>
					</tr>
				</ww:iterator>
			</ww:else>
		</tbody>
		<TFOOT id='detailFoot'>
			<TR>
				<TD colspan="8" ></TD>
			</TR>
		</TFOOT>
	</table>
	
	<input type='hidden' name='search.maintainId' value='<ww:property value="search.maintain_id"/>'/>
	<input type='hidden' name='search.carNoId' value='<ww:property value="search.car_no_id"/>'/>
	<input type='hidden' name='search.cityId' value='<ww:property value="search.city_id"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
	<ww:if test="'insert'.equals(action)">
		<input type='hidden' name='search.detailRowNum' value='1'/>
	</ww:if>
	<ww:elseif test="'update'.equals(action)">
		<input type='hidden' name='search.detailRowNum' value='<ww:property value="search.detailList.size()"/>'/>
	</ww:elseif>
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
        	if (fm2.isValid() && document.form1.checkSubmit()) {
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
	
	function acc_charge(v1, v2, v3){
		if (v1.value == 'undefined'){
			v3.value = 0 * v2.value;
			return;
		}
		if (v2.value == 'undefined'){
			v3.value = v1.value * 0;
			return;
		}
		v3.value = v1.value * v2.value;
	}
	// 删除行
	function del_row(value){
		if (!confirm('您确定是否删除，删除后该数据将无法恢复!')){
			return;
		}
		if (value != null){
			var url = '/carmanage/servicingmanage/servicingBooking.shtml?actionType=delDetail&search.maintainDetailId='+value;
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
						alert("删除车辆维修明细记录，操作成功！");
					}else{
						alert("删除车辆维修修明记录，操作失败！");
						return;
					}
				}
			}catch(e){ 
				alert(e);
			}
		}
		
		var num = Math.floor(document.form1('search.detailRowNum').value);
		num = num-1;
		document.form1('search.detailRowNum').value = num;
		
	   	event.cancelBubble=true;
	   	var the_obj = event.srcElement;
	   	var the_td	= get_Element(the_obj,"td");
	   	var the_tr	= the_td.parentElement;
	   	cur_row = the_tr.rowIndex;
	   	document.all.detailTable.deleteRow(cur_row);
	}
	// 被上个函数调用
	function get_Element(the_ele,the_tag){
	   the_tag = the_tag.toLowerCase();
	   if(the_ele.tagName.toLowerCase()==the_tag)return the_ele;
	   while(the_ele=the_ele.offsetParent){
	     if(the_ele.tagName.toLowerCase()==the_tag)return the_ele;
	   }
	   return(null);
	}
	
	function fun_addrow(){
		var num = Math.floor(document.form1('search.detailRowNum').value);
		num = num + 1;
		document.form1('search.detailRowNum').value = num;
		
		var oBody = $(detailTbody);
		document.all("detailTable").insertBefore(oBody,document.all("detailFoot"));
		var myTR =oBody.insertRow();
		var myTD1 = myTR.insertCell();
		myTD1.innerHTML = "<div align='center'>"+num+"</div>";
		var myTD2 = myTR.insertCell();
		myTD2.innerHTML = "<div align='center'><input type='text' name='detail.maintainItem' class='check' verify='string' 	required='true' requiredColor='#ffffff' shade='true' value='' style='width:190px;border:1px solid #C0C0C0;'/></div>";
		var myTD3 = myTR.insertCell();
		myTD3.innerHTML = "<div align='center'><input type='text' name='detail.unit'		class='check' verify='string' 	required='true' requiredColor='#ffffff' shade='true' value='' style='width:40px;border:1px solid #C0C0C0;'/></div>";
		var myTD4 = myTR.insertCell();
		myTD4.innerHTML = "<div align='center'><input type='text' name='detail.num'			class='check' verify='int+' 	required='true' requiredColor='#ffffff' shade='true' id='num_"+num+"' onblur='acc_charge(this, $(price_"+num+"), $(charge_"+num+"))' style='width:40px;border:1px solid #C0C0C0;'/></div>";
		var myTD5 = myTR.insertCell();
		myTD5.innerHTML = "<div align='center'><input type='text' name='detail.price'		class='check' verify='double+' 	required='true' requiredColor='#ffffff' shade='true' id='price_"+num+"' onblur='acc_charge($(num_"+num+"), this, $(charge_"+num+"))' style='width:40px;border:1px solid #C0C0C0;'/></div>";
		var myTD6 = myTR.insertCell();
		myTD6.innerHTML = "<div align='center'><input type='text' name='detail.charge'		class='check' verify='double+' 	required='true' requiredColor='#ffffff' shade='true' id='charge_"+num+"' readonly style='width:40px;border:1px solid #C0C0C0;'/></div>";
		var myTD7 = myTR.insertCell();
		myTD7.innerHTML = "<div align='center'><TEXTAREA name='detail.memo' style='width:300px;height:40px;border:1px solid #C0C0C0;'></TEXTAREA></div>";
		var myTD8 = myTR.insertCell();
		myTD8.innerHTML = "<div align='center'><A style='cursor:hand' onclick='del_row();'>删除</a></div>";
	}
</script>